package com.hsbc.view;


import com.hsbc.exceptions.EmployeeNotFoundException;
import com.hsbc.exceptions.UserNotFoundException;

import java.util.Scanner;

public class UI {
    public static String loginMenu(Scanner sc) {
        System.out.println("\nPlease Login to Continue\n");
        System.out.print("Email id: ");
        String email = sc.next();
        System.out.print("Password: ");
        String password = sc.next();

        String role = null;

//        try {
//            role = LoginService.login(email, password);
//        } catch (EmployeeNotFoundException e) {
//            System.out.println(e.getMessage());
//        }

        //TODO: If email and password are incorrect ask them to login again

//        if(role == null){
//
//        }

        return role;
    }

    public static void adminMenu(Scanner sc){
        while(true) {
            System.out.println("-----Welcome to Admin Panel-------");
            System.out.println("1. Import Users");
            System.out.println("2. Show all Users");
            System.out.println("3. Add User");
            System.out.println("4. Remove User");
            System.out.println("5. Import Doctors");
            System.out.println("6. Show all Doctors");
            System.out.println("7. Add Doctor");
            System.out.println("8. Remove Doctor");
            System.out.println("9. Show Doctor Schedule");
            System.out.println("10. Update Doctor Schedule");
            System.out.println("11. Show all Patients");
            System.out.println("12. Show all Appointments");
            System.out.println("13. Cancel Appointment");
            System.out.println("14. Exit");

            System.out.print("\nEnter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    //importUsers();
                    break;
                case 2:
                    //showAllUsers();
                    break;
                case 3:
                    //addUser();
                    break;
                case 4:
                    //removeUser();
                    break;
                case 5:
                    //importDoctors();
                    break;
                case 6:
                    //showAllDoctors();
                    break;
                case 7:
                    //addDoctor();
                    break;
                case 8:
                    //removeDoctor();
                    break;
                case 9:
                    //showDoctorSchedule();
                    break;
                case 10:
                    //updateDoctorSchedule();
                    break;
                case 11:
                    //showAllPatients();
                    break;
                case 12:
                    //getAllAppointments();
                    break;
                case 13:
                    //cancelAppointment();
                    break;
                case 14:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void doctorMenu(Scanner sc){
        while(true) {
            System.out.println("-----Welcome to Doctor Panel-------");
            System.out.println("1. View Appointments");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. Suggest Medications");
            System.out.println("4. Suggest Medical Test");
            System.out.println("5. Add Schedule");
            System.out.println("6. Update Schedule");
            System.out.println("7. View Profile");
            System.out.println("8. Exit");

            System.out.print("\nEnter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    //viewAppointments();
                    break;
                case 2:
                    //cancelAppointment();
                    break;
                case 3:
                    //suggestMedications();
                    break;
                case 4:
                    //suggestMedicalTest();
                    break;
                case 5:
                    //addSchedule();
                    break;
                case 6:
                    //updateSchedule();
                    break;
                case 7:
                    //viewProfile();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void userMenu(Scanner sc){
        while(true) {
            System.out.println("-----Welcome to User Panel-------");
            System.out.println("1. Register Patient");
            System.out.println("2. Book Appointment");
            System.out.println("3. View all patients");
            System.out.println("4. View Appointments");
            System.out.println("5. Cancel Appointment");
            System.out.println("6. View all doctors");
            System.out.println("7. View Profile");
            System.out.println("8. Get Patient Details");
            System.out.println("8. Exit");

            System.out.print("\nEnter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    //registerPatient();
                    break;
                case 2:
                    //bookAppointment();
                    break;
                case 3:
                    //viewAllPatients();
                    break;
                case 4:
                    //viewAppointments();
                    break;
                case 5:
                    //cancelAppointment();
                    break;
                case 6:
                    //viewAllDoctors();
                    break;
                case 7:
                    //viewProfile();
                    break;
                case 8:
                    //getPatientDetails();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("----------------Welcome to MultiThreaders Hospital----------------");

        String role = loginMenu(sc);

        if(role != null){
            switch(role){
                case "admin":
                    adminMenu(sc);
                    break;
                case "doctor":
                    doctorMenu(sc);
                    break;
                case "user":
                    userMenu(sc);
                    break;
                default:
                    System.out.println("Invalid role");
            }
        } else {
            System.out.println("Invalid credentials");

        }
    }
}
