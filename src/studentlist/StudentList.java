/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentlist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class StudentList {

    static ArrayList<Student> students = new ArrayList();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //readInStudents(students);
        commandLoop();

    }

    public static void readInStudents(ArrayList<Student> students) {
        students.clear();
        try {
            Scanner sc = new Scanner(new File("student.txt"));
            int n = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < n; i++) {
                String s = sc.nextLine();
                Student student = new Student();
                student.deserialize(s);
                students.add(student);
            }
            Collections.sort(students);
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void commandLoop() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        String[] params = new String[5];
        while (!command.equalsIgnoreCase("quit")) {
            if (command.equalsIgnoreCase("list")) {
                listStudents();
            } else if (command.equalsIgnoreCase("delete")) {
                int id = Integer.parseInt(params[1]);
                deleteStudent(id);
            } else if (command.equalsIgnoreCase("save")) {
                saveStudents();
            } else if (command.equalsIgnoreCase("add")) {
                Student student = inputStudentInfo(sc, ">>Student Info");
                students.add(student);
            } else if (command.equalsIgnoreCase("load")) {
                readInStudents(students);
            }
            System.out.print(">> ");
            String line = sc.nextLine();
            params = line.split(" ");
            command = params[0];
        }

    }

    private static Student inputStudentInfo(Scanner sc, String prompt) {
        // TODO Auto-generated method stub

        Student student = new Student();

        System.out.print(prompt + " id: ");
        student.id = sc.nextInt();
        sc.nextLine();
        System.out.println(prompt + " name: ");
        student.name = sc.nextLine();
        System.out.println(prompt + " birthday (dd/mm/yyyy)");
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        try {
            student.birthday = format.parse(sc.nextLine());
        } catch (Exception e) {
            student.birthday = new Date();
        }
        System.out.println(prompt + "address (roomnumber, streetnumber street, city, province postalcode");
        student.address = new Address();
        student.address.deserialize(sc.nextLine());
        System.out.println(prompt + "phone: country code-(areacode)code");
        student.phone = new Phone();
        student.phone.deserialize(sc.nextLine());

        System.out.println(prompt + "Courses");
        inputCurrentCourseInfo(sc, prompt, "current course", student.currentCourses);
        return student;
    }

    private static void saveStudents() {

        try {
            PrintWriter pw = new PrintWriter("student.txt");
            pw.println(students.size());
            for (Student student : students) {
                pw.println(student.serialize());
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Save file error");
        }

    }

    private static void deleteStudent(int id) {
        Student student = new Student();
        student.id = id;
        int index = Collections.binarySearch(students, student);
        if (index >= 0) {
            students.remove(index);
        }
    }

    private static void listStudents() {

        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));

        }

    }

    

    private static void inputCurrentCourseInfo(Scanner sc, String prompt, String prompt2, ArrayList<Course> currentCourses) {
        while (true) {
            System.out.println(prompt + "input current course");
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("n")) {
                break;
            } else if (line.equalsIgnoreCase("y")) {
                Course course = inputCourseInfo(sc, prompt + prompt2);
                currentCourses.add(course);
            }
        }
    }

    private static Course inputCourseInfo(Scanner sc, String prompt) {
        
    }
}
