package com.anmol.jobtracker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JobApplicationTracker {
    private static final String DATA_FILE = "applications.dat";
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Application> applications = loadApplications();

    public static void main(String[] args) {
        System.out.println("\nWelcome to Job Application Tracker");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addApplication();
                case "2" -> viewApplications();
                case "3" -> updateStatus();
                case "4" -> deleteApplication();
                case "5" -> {
                    saveApplications();
                    running = false;
                    System.out.println("Your applications have been saved. Good luck!");
                }
                default -> System.out.println("Please choose a number from 1 to 5.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Add application");
        System.out.println("2. View applications");
        System.out.println("3. Update status");
        System.out.println("4. Delete application");
        System.out.println("5. Save and exit");
        System.out.print("Choose an option: ");
    }

    private static void addApplication() {
        System.out.print("Company: ");
        String company = scanner.nextLine().trim();
        System.out.print("Role: ");
        String role = scanner.nextLine().trim();
        System.out.print("Location: ");
        String location = scanner.nextLine().trim();
        System.out.print("Status (Applied, Interview, Rejected, Offer): ");
        String status = scanner.nextLine().trim();

        int id = applications.stream().mapToInt(Application::getId).max().orElse(0) + 1;
        applications.add(new Application(id, company, role, location, LocalDate.now().toString(), status));
        saveApplications();
        System.out.println("Application added successfully.");
    }

    private static void viewApplications() {
        if (applications.isEmpty()) {
            System.out.println("No applications saved yet.");
            return;
        }

        System.out.printf("%-4s %-22s %-24s %-16s %-14s %s%n", "ID", "Company", "Role", "Location", "Applied", "Status");
        System.out.println("-".repeat(100));
        applications.forEach(System.out::println);
    }

    private static void updateStatus() {
        Application application = findApplicationById(readId("Enter application ID to update: "));
        if (application == null) {
            System.out.println("No application found with that ID.");
            return;
        }

        System.out.print("New status: ");
        application.setStatus(scanner.nextLine().trim());
        saveApplications();
        System.out.println("Status updated.");
    }

    private static void deleteApplication() {
        Application application = findApplicationById(readId("Enter application ID to delete: "));
        if (application == null) {
            System.out.println("No application found with that ID.");
            return;
        }

        applications.remove(application);
        saveApplications();
        System.out.println("Application deleted.");
    }

    private static int readId(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException exception) {
            return -1;
        }
    }

    private static Application findApplicationById(int id) {
        return applications.stream().filter(application -> application.getId() == id).findFirst().orElse(null);
    }

    @SuppressWarnings("unchecked")
    private static List<Application> loadApplications() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            return (List<Application>) input.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            return new ArrayList<>();
        }
    }

    private static void saveApplications() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            output.writeObject(applications);
        } catch (IOException exception) {
            System.out.println("Could not save applications: " + exception.getMessage());
        }
    }
}
