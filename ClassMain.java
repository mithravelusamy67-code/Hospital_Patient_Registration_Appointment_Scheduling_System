package com.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static List<Patient> patients = new ArrayList<>();
    static List<Appointment> appointments = new ArrayList<>();

    // Patient Class
    static class Patient {
        int id;
        String name;
        int age;

        Patient(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Patient ID: " + id
                    + ", Name: " + name
                    + ", Age: " + age;
        }
    }

    // Appointment Class
    static class Appointment {
        int appointmentId;
        int patientId;
        String patientName;
        String doctorName;
        String date;

        Appointment(int appointmentId, int patientId,
                    String patientName, String doctorName,
                    String date) {
            this.appointmentId = appointmentId;
            this.patientId = patientId;
            this.patientName = patientName;
            this.doctorName = doctorName;
            this.date = date;
        }

        @Override
        public String toString() {
            return "Appointment ID: " + appointmentId
                    + ", Patient ID: " + patientId
                    + ", Patient Name: " + patientName
                    + ", Doctor: " + doctorName
                    + ", Date: " + date;
        }
    }

    // Add Patient
    static void addPatient() {
        System.out.print("Enter Patient ID: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid Patient ID!");
            sc.nextLine();
            return;
        }

        int id = sc.nextInt();
        sc.nextLine();

        for (Patient p : patients) {
            if (p.id == id) {
                System.out.println("Patient ID already exists!");
                return;
            }
        }

        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter Patient Age: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid age!");
            sc.nextLine();
            return;
        }

        int age = sc.nextInt();
        sc.nextLine();

        if (id <= 0 || age <= 0 || age > 120 || name.isEmpty()) {
            System.out.println("Please enter valid patient details!");
            return;
        }

        patients.add(new Patient(id, name, age));
        System.out.println("Patient registered successfully!");
    }

    // View Patients
    static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
            return;
        }

        System.out.println("\n----- Patient List -----");

        for (Patient p : patients) {
            System.out.println(p);
        }
    }

    // Book Appointment
    static void bookAppointment() {
        System.out.print("Enter Appointment ID: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid Appointment ID!");
            sc.nextLine();
            return;
        }

        int aid = sc.nextInt();
        sc.nextLine();

        if (aid <= 0) {
            System.out.println("Appointment ID must be positive.");
            return;
        }

        for (Appointment a : appointments) {
            if (a.appointmentId == aid) {
                System.out.println("Appointment ID already exists!");
                return;
            }
        }

        System.out.print("Enter Patient ID: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid Patient ID!");
            sc.nextLine();
            return;
        }

        int pid = sc.nextInt();
        sc.nextLine();

        Patient selectedPatient = null;

        for (Patient p : patients) {
            if (p.id == pid) {
                selectedPatient = p;
                break;
            }
        }

        if (selectedPatient == null) {
            System.out.println("Patient not found. Register first.");
            return;
        }

        System.out.print("Enter Doctor Name: ");
        String doctor = sc.nextLine().trim();

        System.out.print("Enter Appointment Date (DD-MM-YYYY): ");
        String date = sc.nextLine().trim();

        if (doctor.isEmpty() || date.isEmpty()) {
            System.out.println("Doctor name and date cannot be empty!");
            return;
        }

        // Check for duplicate doctor bookings on the same date
        for (Appointment a : appointments) {
            if (a.doctorName.equalsIgnoreCase(doctor)
                    && a.date.equalsIgnoreCase(date)) {
                System.out.println(
                    "Booking conflict! Doctor already has an appointment on this date."
                );
                return;
            }
        }

        appointments.add(new Appointment(
                aid, pid, selectedPatient.name, doctor, date));

        System.out.println("Appointment booked successfully!");
    }

    // View Appointments
    static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments booked.");
            return;
        }

        System.out.println("\n----- Appointment List -----");

        for (Appointment a : appointments) {
            System.out.println(a);
        }
    }

    // Main Method
    public static void main(String[] args) {

        while (true) {
            System.out.println("\n==================================");
            System.out.println(" HOSPITAL MANAGEMENT SYSTEM");
            System.out.println("==================================");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. Book Appointment");
            System.out.println("4. View Appointments");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addPatient();
                    break;

                case 2:
                    viewPatients();
                    break;

                case 3:
                    bookAppointment();
                    break;

                case 4:
                    viewAppointments();
                    break;

                case 5:
                    System.out.println("Thank you for using the system!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Select 1 to 5.");
            }
        }
    }
}