package com.hsbc.view;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.models.Employee;
import com.hsbc.service.EmployeeService;

import java.util.Scanner;

public class UI {
    public static EmployeeEnums.Role loginMenu(Scanner sc) {
        while(true) {
            System.out.println("\nPlease Login to Continue\n");
            System.out.print("Email id: ");
            String email = sc.next();
            System.out.print("Password: ");
            String password = sc.next();

            Employee emp;

            EmployeeService employeeService = new EmployeeService();


            emp = employeeService.employeeLogin(email, password);
            EmployeeEnums.Role role = emp.getRole();
            if(role != null){
                System.out.println("Logged in as " + role);
                return role;
            } else {
                System.out.println("Invalid credentials");
            }
            return null;
        }

    }

    public static void adminMenu(Scanner sc){
        while(true) {
            System.out.println("-----Welcome to Admin Panel-------");
            System.out.println("1. Import Users");
            System.out.println("2. Show all Users");
            System.out.println("3. Add User");
            System.out.println("4. Remove User");
            System.out.println("5. Update User");
            System.out.println("6. Import Doctors");
            System.out.println("7. Show all Doctors");
            System.out.println("8. Add Doctor");
            System.out.println("9. Remove Doctor");
            System.out.println("10. Update Doctor");
            System.out.println("11. Show Doctor Schedule");
            System.out.println("12. Update Doctor Schedule");
            System.out.println("13. Show all Patients");
            System.out.println("14. Show all Appointments");
            System.out.println("15. Cancel Appointment");
            System.out.println("16. Generate Doctor Report");
            System.out.println("17. Generate Patient Report");
            System.out.println("18. Generate User Report");
            System.out.println("19. Exit");

            System.out.print("\nEnter your choice: ");
            int ch = sc.nextInt();

            switch(ch){
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
                    //updateUser();
                    break;
                case 6:
                    //importDoctors();
                    break;
                case 7:
                    //showAllDoctors();
                    break;
                case 8:
                    //addDoctor();
                    break;
                case 9:
                    //removeDoctor();
                    break;
                case 10:
                    //updateDoctor();
                    break;
                case 11:
                    //showDoctorSchedule();
                    break;
                case 12:
                    //updateDoctorSchedule();
                    break;
                case 13:
                    //showAllPatients();
                    break;
                case 14:
                    //showAllAppointments();
                    break;
                case 15:
                    //cancelAppointment();
                    break;
                case 16:
                    //generateDoctorReport();
                    break;
                case 17:
                    //generatePatientReport();
                    break;
                case 18:
                    //generateUserReport();
                    break;
                case 19:
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

        EmployeeEnums.Role role = loginMenu(sc);

//        if (role != null) {
//            switch (role) {
//                case ADMIN:
//                    adminMenu(sc);
//                    break;
//                case DOCTOR:
//                    doctorMenu(sc);
//                    break;
//                case USER:
//                    userMenu(sc);
//                    break;
//                default:
//                    System.out.println("Invalid role");
//                    break;
//            }
//        } else {
//            System.out.println("Invalid credentials");
//        }
    }
}
