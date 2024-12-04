/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import model.User;
import util.Validation;


/**
 *
 * @author quyld
 */
public class Manager {
    private final ArrayList<User> userList = new ArrayList<>();

    public void addAccound(User user) {
        userList.add(user);
        System.out.println("Add success!!!");
    }

    public User searchUserObjectByName(String userName) {
        if (userList.isEmpty()) {
            return null;
        }

        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                return user;
            }
        }

        return null;
    }
//
//    public void login(String userName,String pass) {
//        if (userList.isEmpty()) {
//            System.err.println("User List is empty. Can not login now.");
//            return;
//        }
//       
//    }

    public User findAccound(String userName, String password) {
        if (userList.isEmpty()) {
            return null;
        }
        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(userName)
                    && user.getPassword().equalsIgnoreCase(Validation.MD5Encryption(password))) {
                return user;
            } else {
                return null;
            }
        }
        return null;
    }

//    public static String MD5Encryption(String password) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] digest = md.digest(password.getBytes());
//
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : digest) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) {
//                    hexString.append('0');
//                }
//                hexString.append(hex);
//            }
//            return hexString.toString();
//        } catch (NoSuchAlgorithmException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
    
    
}
