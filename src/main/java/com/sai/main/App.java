package com.sai.main;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.sai.entity.Student;

public class App {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== STUDENT CRUD MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            Session session = factory.openSession();
            Transaction tx = null;

            switch (choice) {

                case 1:
                    // INSERT
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter City: ");
                    String city = sc.nextLine();

                    Student student = new Student(name, city);

                    tx = session.beginTransaction();
                    session.persist(student);
                    tx.commit();

                    System.out.println("Student Added Successfully!");
                    break;

                case 2:
                    // READ
                    System.out.print("Enter Student ID: ");
                    int readId = sc.nextInt();

                    Student found = session.get(Student.class, readId);

                    if (found != null) {
                        System.out.println("ID: " + found.getId());
                        System.out.println("Name: " + found.getName());
                        System.out.println("City: " + found.getCity());
                    } else {
                        System.out.println("Student Not Found!");
                    }
                    break;

                case 3:
                    // UPDATE
                    System.out.print("Enter Student ID to Update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    Student updateStudent = session.get(Student.class, updateId);

                    if (updateStudent != null) {

                        System.out.print("Enter New City: ");
                        String newCity = sc.nextLine();

                        tx = session.beginTransaction();
                        updateStudent.setCity(newCity);
                        session.merge(updateStudent);
                        tx.commit();

                        System.out.println("Student Updated Successfully!");
                    } else {
                        System.out.println("Student Not Found!");
                    }
                    break;

                case 4:
                    // DELETE
                    System.out.print("Enter Student ID to Delete: ");
                    int deleteId = sc.nextInt();

                    Student deleteStudent = session.get(Student.class, deleteId);

                    if (deleteStudent != null) {

                        tx = session.beginTransaction();
                        session.remove(deleteStudent);
                        tx.commit();

                        System.out.println("Student Deleted Successfully!");
                    } else {
                        System.out.println("Student Not Found!");
                    }
                    break;

                case 5:
                    session.close();
                    factory.close();
                    sc.close();
                    System.out.println("Application Closed.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

            session.close();
        }
    }
}