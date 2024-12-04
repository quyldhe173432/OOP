/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package p0072;

import controller.Manager;
import java.util.Date;
import model.User;
import util.Validation;

/**
 *
 * @author quyld
 */
public class P0072 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Manager manager = new Manager();
        int choice;
        do {
            System.out.println("============ Login Program ============\n"
                    + "1. Add User\n"
                    + "2. Login\n"
                    + "3. Exit");
            choice = Validation.getAnInteger("Input the choice: ", "", 1, 3);
            switch (choice) {
                case 1:
                    System.out.println("---------- Add User --------");
                    String userName;

                    while (true) {
                        userName = Validation.getString("Account: ",
                                "Account Name is required.");

                        User user = manager.searchUserObjectByName(userName);

                        if (user != null) {
                            System.err.println("User name is already exist. Please input another one.");
                        } else {
                            break;
                        }
                    }

                    String password = Validation.getString("Password: ",
                            "Password is required.");

                    String name = Validation.getValidString("Name: ",
                            "Please input valid name.", "[A-Za-z\\s]+");

                    String phone = Validation.getValidString("Input phone number: ",
                            "Phone is number with 10 or 11 characters",
                            "^\\d{10,11}$");

                    String email = Validation.getValidString("Email: ",
                            "Format of email is <account name>@<domain>.",
                            "^[A-Za-z0-9+._-]+@[A-Za-z.-]+\\.[A-Za-z]{2,}$");

                    String address = Validation.getString("Address: ",
                            "Address is required");
                    Date DOB = Validation.getBirthDate("DOB: ", "Format of Date is dd/MM/yyyy.");
                    User user = new User(userName, Validation.MD5Encryption(password), name, phone, email, address, DOB);
                    manager.addAccound(user);
                    break;
                case 2:
                    String account = Validation.getString("Account: ",
                            "Account Name is required.");

                    String pass = Validation.getString("Password: ",
                            "Password is required.");
                    User userLogin = manager.findAccound(account, pass);

                    if (userLogin != null) {
                        System.out.println("------------ Wellcome -----------");
                        if (Validation.getChoice("Hi " + account + ", do you want change password now? Y/N: ",
                                "Please input Y/y or N/n", "Y", "N")) {
                                String oldPassword;
                                do {
                                    oldPassword = Validation.getString("Old Password: ", "Old Password is required.");

                                    if (!Validation.MD5Encryption(oldPassword).equalsIgnoreCase(userLogin.getPassword())) {
                                        System.err.println("Old Password is incorrect. Please try again.");
                                    }
                                } while (!Validation.MD5Encryption(oldPassword).equalsIgnoreCase(userLogin.getPassword()));

                                // Proceed with changing the password
                                String newPassword;
                                do {
                                    newPassword = Validation.getString("New Password: ", "New Password is required.");

                                    if (newPassword.equalsIgnoreCase(oldPassword)) {
                                        System.err.println("Old Password and New Password cannot be the same. Please enter a different password.");
                                    }
                                } while (newPassword.equalsIgnoreCase(oldPassword));

                                String reNewPassword;
                                do {
                                    reNewPassword = Validation.getString("Renew Password: ", "New Password is required.");

                                    if (!newPassword.equalsIgnoreCase(reNewPassword)) {
                                        System.err.println("New Password and Renew Password must be the same. Please try again.");
                                    }
                                } while (!newPassword.equalsIgnoreCase(reNewPassword));

                                // Set the new password after all checks pass
                                userLogin.setPassword(Validation.MD5Encryption(newPassword));
                                System.out.println("Password changed successfully!");
                            }
                        
                    } else {
                        System.err.println("Invalid username or password.");
                    }
                    break;
                case 3:
                    System.out.println("Bye Bye, See you in next time");
                    break;
            }
        } while (choice != 3);

    }

}
