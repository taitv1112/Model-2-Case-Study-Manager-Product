package model;

import java.io.Serializable;

public class Manager implements Serializable {
    public static final String ADMIN = "ADMIN";
    public static final String STAFF = "STAFF";
    public static final String USER = "USER";
    private String userManager;
    private String passwords;
    private String role;
    private String name;
    private String dateOfBirth;
    private int age;
    private double salary;
    private String phone;
    private String address;
    private String email;

    public String getPasswords() {
        return passwords;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public Manager(String userManager, String passwords, String name, String dateOfBirth, int age, double salary, String phone, String address, String email) {
        this.userManager = userManager;
        this.role=STAFF;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.salary = salary;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Manager(String userManager, String passwords, String name, String dateOfBirth, int age, String phone, String address, String email) {
        this.userManager = userManager;
        this.passwords = passwords;
        this.salary = 0;
        this.role = USER;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Manager(String userManager, String passwords) {
        this.userManager = userManager;
        this.passwords = passwords;
        this.role = ADMIN;
    }

    public String getUserManager() {
        return userManager;
    }
    public void setUserManager(String userManager) {
        this.userManager = userManager;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "idManager='" + userManager + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
