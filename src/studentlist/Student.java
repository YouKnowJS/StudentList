package studentlist;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Student implements Comparable<Student> {

    int id = 0;
    String name = null;
    Date birthday = new Date();
    Address address = new Address();
    Phone phone = new Phone();
    ArrayList <Course> currentCourses = new ArrayList();
    // ArrayList <Course> takenCourses = new ArrayList();

    void deserialize(String s) {
        String[] field = s.split("\\|");
        this.id = Integer.parseInt(field[0]);
        this.name = field[1];
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        try {
            this.birthday = format.parse(field[2]);
        } catch (Exception e) {
            this.birthday = new Date();
        }
        this.address.deserialize(field[3]);
        this.phone.deserialize(field[4]);
    }

    String serialize() {
        //String s = id + "," + name + ",";
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        return id + "|" + name + "|" + format.format(birthday) +"|"+ address.toString() + "|" + phone.toString();
    }

    @Override
    public int compareTo(Student o) {
        return this.id - o.id;

    }
    @Override
    public String toString(){
        String s = "ID: "+this.id+"\n";
        s+="Name: "+this.name+"\n";
        SimpleDateFormat format = new SimpleDateFormat();
        s+=format.format(this.birthday)+"\n";
        s+="Address: "+this.address+"\n";
        s+="Phone: "+this.phone+"\n";
        return s;
    }

}
