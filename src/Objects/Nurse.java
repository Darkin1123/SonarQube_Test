/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import Validation.Check;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Nurse extends Person {
    private String staffID ;
    private String department;
    private int shift;
    private double salary;
    Scanner sc = new Scanner(System.in);
    
    public Nurse(){}
    
    public Nurse(String name, int age, String gender, String address,
            String phone,String staffID, String department, int shift, double salary){
        super(staffID, name, age, gender, address, phone);
        this.id=staffID;
        setStaffID(staffID);
        setDepartment(department);
        setShift(shift);
        setSalary(salary);
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        if (staffID == null) {
            throw new IllegalArgumentException("StaffID cannot be null");
        }
        this.staffID = staffID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department == null) {
            throw new IllegalArgumentException("Department cannot be null");
        }
        this.department = department;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        if (shift <= 0) {
            throw new IllegalArgumentException("Shift must be positive number!");
        }
        this.shift = shift;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary must be a positive number!");
        }
        this.salary = salary;
    }
    
    
@Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-15s %-15s %-15s", staffID,
                name, age, phone, department, salary );
    }
}
