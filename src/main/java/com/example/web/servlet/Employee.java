/* Copyright © 2015 Oracle and/or its affiliates. All rights reserved. */
package com.example.web.servlet;


import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author luperalt
 */
public class Employee implements Serializable {

    private long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String role;
    private String department;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean blocked;
    private Date createTime;
    private static final AtomicLong counter = new AtomicLong(100);

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isBlocked() {
        return blocked;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    public Employee(){}

    public Employee(String firstName, String lastName, String birthDate, String role,
                    String department, String email,
                    long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.role = role;
        this.department = department;
        this.email = email;
        this.phoneNumber = "212-85-06";
        this.password = "1111";
        this.blocked = false;
        this.createTime = new Date();
        this.id = id;
    }

    public Employee(String firstName, String lastName, String birthDate, String role,
                    String department, String email, String phoneNumber, String password,
                    long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.role = role;
        this.department = department;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.blocked = false;
        this.createTime = new Date();
        this.id = id;
    }
    
    public Employee(String firstName, String lastName, String birthDate, String role,
                    String department, String email, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.role = role;
        this.department = department;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.blocked = false;
        this.createTime = new Date();
        this.id = counter.incrementAndGet();
    }

    public Employee(String firstName, String lastName, String birthDate, String role,
                    String department, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.role = role;
        this.department = department;
        this.email = email;
        this.phoneNumber = "2-12-85-06";
        this.password = "password";
        this.blocked = false;
        this.createTime = new Date();
        this.id = counter.incrementAndGet();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + firstName +
                ", lastName=" + lastName + ", birthDate=" + birthDate +
                ", role=" + role + ", department=" + department +
                ", email=" + email + ", phone number=" + phoneNumber +
                ", password=" + password + ", blocked=" +
                (blocked ? "blocked" : "unblocked ") + '}';
    }

}
