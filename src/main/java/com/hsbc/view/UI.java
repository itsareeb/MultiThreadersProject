package com.hsbc.view;

import com.hsbc.Enums.AppointmentEnums;
import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.Enums.PatientEnums;
import com.hsbc.models.*;
import com.hsbc.service.AppointmentService;
import com.hsbc.service.EmployeeService;
import com.hsbc.service.PatientService;
import com.hsbc.service.ScheduleService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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

            EmployeeService employeeService = new EmployeeService();
            PatientService patientService = new PatientService();
            AppointmentService appointmentService = new AppointmentService();
            ScheduleService scheduleService = new ScheduleService();

            switch(ch){
                case 1:
                    List<User> users = Arrays.asList(
                            new User(EmployeeEnums.Role.user, "John Doe", "john@123", true, "1234567890", "john@doe.com", EmployeeEnums.Department.general, EmployeeEnums.Shift.day),
                            new User(EmployeeEnums.Role.user, "Jane Doe", "jane@123", true, "1234567890", "jane@dae.com", EmployeeEnums.Department.general, EmployeeEnums.Shift.night)
                    );
                    employeeService.importUsers(users);
                    break;
                case 2:
                    employeeService.getAllUsers();
                    break;
                case 3:
                    employeeService.addUser("John Done", "john@1234", "john@done.com", "1234567890", EmployeeEnums.Shift.day, EmployeeEnums.Department.general);
                    break;
                case 4:
                    employeeService.deleteEmployee(1);
                    break;
                case 5:
                    User user = new User(EmployeeEnums.Role.user, "John Doe", "john@12345" , true, "1234567890", "john@doe.com" ,EmployeeEnums.Department.general, EmployeeEnums.Shift.day);
                    employeeService.updateUser(user);
                    break;
                case 6:
                    List<Doctor> doctors = Arrays.asList(
                            new Doctor(EmployeeEnums.Role.doctor, "Dr. John Doe", "john@123", true, "1234567890", "john@drdoe.com" , "MBBS", "Cardiologist", EmployeeEnums.Department.general),
                            new Doctor(EmployeeEnums.Role.doctor, "Dr. Jane Doe", "jane@123", true, "1234567890", "jane@drdoecom", "MBBS", "Dentist", EmployeeEnums.Department.general)
                    );
                    employeeService.importDoctors(doctors);
                    break;
                case 7:
                    employeeService.getAllDoctors();
                    break;
                case 8:
                    employeeService.addDoctor("Dr. John Done", "john@1234", true, "1234567890", "john@drdone.com", "MBBS", "Cardiologist", EmployeeEnums.Department.general);
                    break;
                case 9:
                    employeeService.removeDoctor(1);
                    break;
                case 10:
                    Doctor doctor = new Doctor(EmployeeEnums.Role.doctor, "Dr. John Doe", "john@12345", true, "1234567890", "john@drdoe.com", "MBBS", "Cardiologist", EmployeeEnums.Department.general);
                    employeeService.updateDoctor(doctor);
                    break;
                case 11:
                    scheduleService.getDoctorSchedule(1,  LocalDate.of(2024,8,26));
                    break;
                case 12:
                    scheduleService.updateDoctorSchedule(1, true);
                    break;
                case 13:
                    patientService.getAllPatients();
                    break;
                case 14:
                    appointmentService.getAllAppointments();
                    break;
                case 15:
                    appointmentService.cancelAppointment(1);
                    break;
                case 16:
                    employeeService.getDoctorReport(1);
                    break;
                case 17:
                    patientService.getPatientReport(1);
                    break;
                case 18:
                    employeeService.getUserReport(1);
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

        AppointmentService appointmentService = new AppointmentService();
        ScheduleService scheduleService = new ScheduleService();
        EmployeeService emp = new EmployeeService();

        while(true) {
            System.out.println("-----Welcome to Doctor Panel-------");
            System.out.println("1. View Appointments");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. Suggest Medications");
            System.out.println("4. Suggest Medical Test");
            System.out.println("5. Add Schedule");
            System.out.println("6. Update Schedule");
            System.out.println("7. View Profile");
            System.out.println("8. Get Medications");
            System.out.println("8. Exit");

            System.out.print("\nEnter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    appointmentService.getAllAppointments();
                    break;
                case 2:
                    appointmentService.cancelAppointment(1);
                    break;
                case 3:
                    Medication medication = new Medication(1, "Paracetamol", "500mg", "2 times a day");
                    appointmentService.suggestMedications(medication);
                    break;
                case 4:
                    Test test = new Test(1, "Blood Test");
                    appointmentService.suggestTests(test);
                    break;
                case 5:
                    List<DoctorSchedules> dsList = Arrays.asList(
                            new DoctorSchedules(1, LocalDate.of(2024,8,26), Arrays.asList(1, 2)),
                            new DoctorSchedules(1, LocalDate.of(2024,8,27), Arrays.asList(1, 2, 3)),
                            new DoctorSchedules(1, LocalDate.of(2024,8,28), Arrays.asList(1, 2, 3, 4))
                    );
                    scheduleService.addDoctorSchedule(dsList);
                    break;
                case 6:
                    scheduleService.updateDoctorSchedule(1, true);
                    break;
                case 7:
                    emp.getDoctorDetails(1);
                    break;
                case 8:
                    appointmentService.getMedications(1);
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void userMenu(Scanner sc){

        AppointmentService appointmentService = new AppointmentService();
        PatientService patientService = new PatientService();
        EmployeeService employeeService = new EmployeeService();

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
            System.out.println("9. Get Available Slots");
            System.out.println("10. Get Appointment Details");
            System.out.println("11. Update appointment status");
            System.out.println("12. Exit");

            System.out.print("\nEnter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    patientService.registerPatient("John Doe", PatientEnums.Gender.male, 24, "1234567890", 1);
                    break;
                case 2:
                    Appointment appointment = new Appointment(1, 1, 1, 1);
                    appointmentService.bookAppointment(appointment);
                    break;
                case 3:
                    patientService.getAllPatients();
                    break;
                case 4:
                    appointmentService.getAllAppointments();
                    break;
                case 5:
                    appointmentService.cancelAppointment(1);
                    break;
                case 6:
                    employeeService.getAllDoctors();
                    break;
                case 7:
                    employeeService.getUserDetails(1);
                    break;
                case 8:
                    patientService.getPatientDetails("John Doe", "1234567890");
                    break;
                case 9:
                    appointmentService.getAvailableSlots(1, LocalDate.of(2024,8,26));
                    break;
                case 10:
                    appointmentService.getAppointment(1);
                    break;
                case 11:
                    appointmentService.updateAppointment(1, AppointmentEnums.Status.completed);
                    break;
                case 12:
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

        switch (role) {
            case admin:
                adminMenu(sc);
                break;
            case doctor:
                doctorMenu(sc);
                break;
            case user:
                userMenu(sc);
                break;
            default:
                System.out.println("Invalid role");
                break;
        }
    }
}
