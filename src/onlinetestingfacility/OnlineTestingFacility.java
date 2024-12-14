/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package onlinetestingfacility;

import java.util.Scanner;

/**
 *
 * @author David
 */
public class OnlineTestingFacility {

    public void start() {
        DatabaseManager.getConnection();
        
        String anotherRequest = "yes";
        
        while (anotherRequest.equalsIgnoreCase("yes")) {
            
//            Scanner scanChoice = new Scanner(System.in);
//            System.out.println("Enter 1 to manage department, "
//                + "2 to manage employee");
//            int choice = scanChoice.nextInt();
//            
//            switch (choice) {
//                case 1 -> this.manageDepartment();
//                case 2 -> this.manageEmployee();
//                default -> System.out.println("Invalid operation.");
//            }
            
            System.out.println("Another request? ");
            Scanner scanRequest = new Scanner(System.in);
            anotherRequest = scanRequest.next();
        }
    }
    
    public static void main(String[] args) {
        new OnlineTestingFacility().start();
    }
}
