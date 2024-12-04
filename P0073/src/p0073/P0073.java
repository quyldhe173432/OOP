/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package p0073;

import controller.Manager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Expenses;
import util.Validation;

/**
 *
 * @author quyld
 */
public class P0073 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Manager manager = new Manager();
        int id = manager.getExList().size() + 1;
        while (true) {
            System.out.println("1. Add an expense");
            System.out.println("2. Display all expenses");
            System.out.println("3. Delete an expense");
            System.out.println("4. Quit");
            int choice = Validation.getAnInteger("Enter Choice: ", "", 1, 4);
            switch (choice) {
                case 1:
                    String Date = Validation.getStringFormat("Enter Date: ", "Format of date is dd-Arp-yyyy.",
                             "^([0-2]?\\d|3[01])-[a-zA-Z]{3}-\\d{4}$");
                    double amount = Validation.getADouble("Enter Amount: ", "", 0, Double.MAX_VALUE);
                    String content = Validation.getString("Enter Content: ", "");
                    Expenses ex = new Expenses(id, Date, amount, content);
                    manager.addExpenses(ex);
                    id++;
                    break;
                case 2:
                    System.out.printf("%-7s%-20s%-20s%-20s\n", "ID", "Date", "Amount of money", "Content");
                    manager.displayExpense();
                    double total = manager.displayTotal();
                    System.out.printf("Total:  %-20.0f\n", total);
                    break;
                case 3:
                    int idDelete = Validation.getAnInteger("Enter ID: ", "", 1, manager.getExList().size());
                    manager.deleteEx(idDelete);
                    id--;
                    break;
                case 4:
                    return;
            }
        }
    }

}
