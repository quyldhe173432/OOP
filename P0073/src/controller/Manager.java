/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import model.Expenses;

/**
 *
 * @author quyld
 */
public class Manager {

    private final ArrayList<Expenses> ExList = new ArrayList<>();
    private static final String FILE_NAME = "expenses.txt";

    public Manager() {
        loadExpensesFromFile();
    }

    public ArrayList<Expenses> getExList() {
        return ExList;
    }

    public void addExpenses(Expenses ex) {
        if (isExpenseExist(ex.getContent())) {
        System.out.println("Khoản chi với nội dung này đã tồn tại!");
        return;
    }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(ex.getId() + "," + ex.getDate() + "," + ex.getAmount() + "," + ex.getContent());
            writer.newLine();
            System.out.println("Đã thêm khoản chi thành công và lưu vào tệp!");
        } catch (IOException e) {
            System.out.println("Lỗi khi thêm khoản chi vào tệp.");
        }
    }

    public String displayExpense() {
        if (ExList.isEmpty()) {
            return null;
        }
        for (Expenses ex : ExList) {
            System.out.println(ex.toString());
        }
        return null;
    }

    public double displayTotal() {
        double total = 0;
        for (Expenses ex : ExList) {
            total += ex.getAmount();
        }
        return total;
    }

    public void deleteEx(int id) {
        while (true) {
            Expenses ex = searchExObjectById(id);
            if (ex != null) {
                ExList.remove(ex);
                for (int i = id - 1; i < ExList.size(); i++) {
                    ExList.get(i).setId(i + 1);
                }
                saveExpensesToFile();
                System.out.println("Expenses deleted successfully!!");
                break;
            } else {
                System.out.println("The expenses ID is not exist!!");
            }
        }
    }

    private Expenses searchExObjectById(int id) {
        if (ExList.isEmpty()) {
            return null;
        }

        for (Expenses ex : ExList) {
            if (ex.getId() == id) {
                return ex;
            }
        }
        return null;
    }

    //Method to load expenses from a file
    private void loadExpensesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                int id = Integer.parseInt(data[0]);
                String date = data[1];
                double amount = Double.parseDouble(data[2]);
                String content = data[3];
                ExList.add(new Expenses(id, date, amount, content));
            }
        } catch (IOException e) {
            System.out.println("No previous expenses found.");
        }
    }
    // Method to save expenses to a file

    private void saveExpensesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Expenses ex : ExList) {
                writer.write(ex.getId() + "," + ex.getDate() + "," + ex.getAmount() + "," + ex.getContent());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving expenses to file.");
        }
    }
    
    private boolean isExpenseExist(String content) {
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            String existingContent = data[3];  // Giả định content là phần tử thứ 4
            if (existingContent.equalsIgnoreCase(content)) {  // So sánh không phân biệt hoa thường
                return true;  // Tìm thấy content trùng
            }
        }
    } catch (IOException e) {
        System.out.println("Lỗi khi kiểm tra khoản chi trong tệp.");
    }
    return false;  // Không tìm thấy content trùng
}
}
