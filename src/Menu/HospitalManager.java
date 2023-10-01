
package Menu;

import Collections.HospitalList;
import Validation.Check;
import Objects.Nurse;
import Objects.Patient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class HospitalManager {
    
    HospitalList list = new HospitalList();
    Scanner sc = new Scanner(System.in);

    void createNurse() {
        
        boolean cont = true;
        String str;
        String staffID;
        
        while (cont) {
            while (true) {
                staffID = Check.checkString("Enter nurse staff ID: ", "Try Again",
                        Check.STAFFID_REGEX);
                if (list.checkDuplicateStaffID(staffID)) {
                    System.out.println("Staff id must be unique");
                } else {
                    break;
                }
            }

            String name = Check.checkString("Enter nurse name: ", "Try again",
                    Check.NAME_REGEX);
            
            int age = Check.checkInteger("Enter nurse age: ", "Try again");

            String gender = Check.checkString("Enter nurse gender: "
                    + " [M/F]: ", "Try again, must be M or F", Check.GENDER_REGEX);

            String address = Check.checkString("Enter nurse address: ",
                    "Try again", Check.ADDRESS_REGEX);
            
            String phone = Check.checkString("Enter nurse phone: ",
                    "Try again", Check.PHONE_REGEX);
            
            String department = Check.checkString("Enter nurse department: ",
                    "Try again", Check.DEPARTMENT_REGEX);
            
            int shift = Check.checkInteger("Enter nurse shift: ", "Try again");
            
            double salary = Check.checkDouble("Enter nurse salary: ", "Try again");

            Nurse nurse = new Nurse(name, age, gender, address, phone, staffID,
                    department, shift, salary);
            list.addNurse(nurse);
            
            cont = Check.checkString("Do you want to add another nurse? [Y/N]: ",
                    "Try again, must be Y or N", Check.YESNO_REGEX)
                    =="y" ? true : false;
        }
    }
    
    public void findNurse(){
        String name = Check.checkString("Enter nurse name: ", "Try again",Check.NAME_REGEX).trim();
        
        HashMap<String, Nurse> hashMapFound = list.findNurseByName(name);
        
        if (hashMapFound.isEmpty()) {
            System.out.println("The nurse does not exist.");
        } else {
            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n",
                    "ID", "Name", "Age", "Phone", "Department", "Salary");
            for (Nurse nurse : hashMapFound.values()) {
                System.out.println(nurse);
            }
        }
    }
    
    public void updateNurse() {
        
        String staffId = Check.checkString("Enter nurse staff ID: ", "Try again",
                Check.STAFFID_REGEX);
        Nurse nurse = list.findNurseByStaffId(staffId);
        if (nurse == null) {
            System.out.println("The nurse does not exist");
            return;
        }

        if (Check.checkUpdate("name")) {
            String name = Check.checkString("Enter nurse name: ", "Try again",
                    Check.NAME_REGEX);
            nurse.setName(name);
        }

        if (Check.checkUpdate("age")) {
            int age = Check.checkInteger("Enter nurse age: ", "Try again");
            nurse.setAge(age);
        }

        if (Check.checkUpdate("gender")) {
            String gender = Check.checkString("Enter nurse gender"
                    + " [M/F]: ", "Try again, must be M or F", Check.GENDER_REGEX);
            nurse.setGender(gender);
        }
        if (Check.checkUpdate("address")) {
            String address = Check.checkString("Enter nurse address: ",
                    "Try again", Check.ADDRESS_REGEX);
            nurse.setAddress(address);
        }
        if (Check.checkUpdate("phone")) {
            String phone = Check.checkString("Enter nurse phone: ",
                    "Try again", Check.PHONE_REGEX);
            nurse.setPhone(phone);
        }
        if (Check.checkUpdate("department")) {
            String department = Check.checkString("Enter nurse department: ",
                    "Try again", Check.DEPARTMENT_REGEX);
            nurse.setDepartment(department);
        }

        if (Check.checkUpdate("shift")) {
            int shift = Check.checkInteger("Enter nurse shift: ", "Try again");
            nurse.setShift(shift);
        }
        if (Check.checkUpdate("salary")) {
            double salary = Check.checkDouble("Enter nurse salary: ", "Try again");
            nurse.setSalary(salary);
        }

    }
    
    public void deleteNurse() {
        String staffId = Check.checkString("Enter nurse staff ID: ", "Try again",
                Check.STAFFID_REGEX);
        Nurse nurse = list.findNurseByStaffId(staffId);
        if (nurse == null) {
            System.out.println("The nurse does not exist");
            return;
        }

        boolean checkDeleteConfirm = Check.checkString("Do you want to delete the nurse [Y/N]?: ",
                "Try again, must be Y or N",
                Check.YESNO_REGEX) == "y" ? true : false;
        if (checkDeleteConfirm) {
            if(list.checkNurseTask(nurse)){
                System.out.println("Nurse have task(s), cannot delete !!");
            }else{
                list.removeNurse(nurse);
                System.out.println("Delete successful !!");
            }
        }
    }
    
    public void addPatient() {
        boolean cont = true; 
        String id = null;
        
        while (cont) {
            while (true) {
                id = Check.checkString("Enter patient ID (format PXXXX): ",
                        "Try again, must be format PXXXX (example: P0001)",
                        Check.PATIENTID_REGEX);
                if (list.checkDuplicatePatent(id)) {
                    System.out.println("Id must be unique");
                } else {
                    break;
                }
            }
            
            String name = Check.checkString("Enter patient name: ", "Try again",
                    Check.NAME_REGEX);
            
            int age = Check.checkInteger("Enter patient age: ", "Try again");
            
            String gender = Check.checkString("Enter patient gender"
                    + " [M/F]: ", "Try again, must be M or F", Check.GENDER_REGEX);
            
            String address = Check.checkString("Enter patient address: ",
                    "Try again", Check.ADDRESS_REGEX);
            
            String phone = Check.checkString("Enter patient phone: ",
                    "Try again", Check.PHONE_REGEX);
            
            String diagnosis = Check.checkString("Enter patient diagnosis: ",
                    "Try Again", Check.DIAGNOSIS_REGEX);
            
            String admissionDate = Check.checkDate("Enter patient's admission date: ",
                    "Date must be in format (dd/MM/yyyy)");
            
            String dischargeDate = Check.checkDate("Enter patient's discharge date: ",
                    "Date must be in format (dd/MM/yyyy)");

            List<Nurse> nurseAssigned = getNurseAssigned();

            
            Patient patient = new Patient(id,name, age, gender, address, phone, diagnosis,
                    admissionDate, dischargeDate, nurseAssigned);

            list.addPatient(patient);

            cont = Check.checkString("Do you want to add another patient? (y/n): ",
                    "Wrong, must be Y or N", Check.YESNO_REGEX)
                    =="y" ? true : false;
        }
    }
    
    public List<Nurse> getNurseAssigned() {
        HashMap<String, Nurse> hashMapNurse = list.getHashMapNurse();
        List<Nurse> listNurseAssign = new ArrayList<>();
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s\n", "No", "Staff Id",
                "Name", "Age", "Phone", "Department", "Salary");
        
        int i = 1;
        for (Nurse nurse : hashMapNurse.values()) {
            System.out.format("%-15s %s\n", i, nurse);
            i++;
        }
        
        boolean choice = true;
        String[] selects = null;
        while (choice) {
            choice = false;
            String userOption = Check.checkString("Please give me your choice (ex: 1,10): ",
                    "Must be in format (X,X) (X is digit and have comma between them)",
                    Check.SELECT_NURSE_REGEX);
            selects = userOption.split("[,]");

            if (selects.length > 2 || selects.length == 0) {
                System.out.println("Your choice must be like example (eg: 1,10)");
                choice = true;
            } else {
                for (String select : selects) {
                    int selectNumber = Integer.parseInt(select.trim());
                    if (selectNumber > hashMapNurse.size() || selectNumber <= 0) {
                        System.out.println("Your choice must be like example (eg: 1 10)");
                        choice = true;
                    }
                }
            }
        }
        
        for (int j = 0; j <= selects.length; j++) {
            int selectNumber = Integer.parseInt(selects[j].trim());
            i = 1;
            for (Nurse nurse : hashMapNurse.values()) {
                if (selectNumber == i) {
                    listNurseAssign.add(nurse);
                }
                i++;
            }
        }
        return listNurseAssign;
    }
    
    public void displayPatient() {

        String startDate = Check.checkDate("Enter start date: ",
                "Date must be in format (dd/MM/yyyy)");

        String endDate = Check.checkDate("Enter end date: ",
                "Date must be in format (dd/MM/yyyy)");

        HashMap<String, Patient> hashMapPatient = list.getPatientByAdmissionDate(startDate, endDate);
        if (hashMapPatient.isEmpty()) {
            System.out.println("No patients were admitted to the hospital from "
                    + startDate + " to " + endDate);
            return;
        }
        
        System.out.format("LIST OF PATIENTS\n"
                + "Start date: %s\n"
                + "End date: %s\n", startDate, endDate);

        System.out.format("%-15s %-15s %-15s %-15s %-15s %-15s\n", "No",
                "Patient Id", "Admission Date", "Full name", "Phone",
                " Diagnosis");
        int count = 1;
        for (Map.Entry<String, Patient> entry : hashMapPatient.entrySet()) {
            String key = entry.getKey();
            Patient patient = entry.getValue();
            System.out.format("%-15s %-15s %-15s %-15s %-15s %-15s\n",
                    count,
                    patient.getId(),
                    patient.getAdmissionDate(),
                    patient.getName(),
                    patient.getPhone(),
                    patient.getDiagnosis());
            count++;
        }
    }
    
    public void sortPatient() {
        String sortField;
        LinkedHashMap<String, Patient> sortedMap = new LinkedHashMap<>();
        while (true) {
            sortField = Check.checkString("Enter sort field (id or name): ",
                    "", ".+");
            if (!sortField.equalsIgnoreCase("id")
                    && !sortField.equalsIgnoreCase("name")) {
                System.out.println("Must be id or name");
            } else {
                break;
            }
        }

        boolean isAsc = Check.checkInteger("Enter sort field (1: ASC, 2: DESC): ",
                "Must be 1 or 2 only") == 1 ? true : false;

        if (sortField.equalsIgnoreCase("id")) {
            List<Map.Entry<String, Patient>> entryList = new ArrayList<>(list.getHashmapPatient().entrySet());

            Collections.sort(entryList, new Comparator<Map.Entry<String, Patient>>() {
            @Override
                public int compare(Map.Entry<String, Patient> entry1, Map.Entry<String, Patient> entry2) {
                    if (isAsc) {
                        return entry1.getValue().getId().compareTo(entry2.getValue().getId());
                    } else {
                        return entry2.getValue().getId().compareTo(entry1.getValue().getId());
                    }
                }
            });
            
            for (Map.Entry<String, Patient> entry : entryList) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }
        }

        if (sortField.equalsIgnoreCase("name")) {
            List<Map.Entry<String, Patient>> entryList = new ArrayList<>(list.getHashmapPatient().entrySet());

            Collections.sort(entryList, new Comparator<Map.Entry<String, Patient>>() {
            @Override
                public int compare(Map.Entry<String, Patient> entry1, Map.Entry<String, Patient> entry2) {
                    if (isAsc) {
                        return entry1.getValue().getName().compareToIgnoreCase(entry2.getValue().getName());
                    } else {
                        return entry2.getValue().getName().compareToIgnoreCase(entry1.getValue().getName());
                    }
                }
            });

            for (Map.Entry<String, Patient> entry : entryList) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }
        }
        
        System.out.format("%-15s %-15s %-15s %-15s %-15s\n",
                "Patient Id", "Admission Date", "Full name", "Phone",
                " Diagnosis");
        for (Map.Entry<String, Patient> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            Patient patient = entry.getValue();

            System.out.format("%-15s %-15s %-15s %-15s %-15s\n",
                    patient.getId(),
                    patient.getAdmissionDate(),
                    patient.getName(),
                    patient.getPhone(),
                    patient.getDiagnosis());
        }
    }
    
    public void saveData() {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("nurses.dat");
            objOutputStream = new ObjectOutputStream(fileOutputStream);
            objOutputStream.writeObject(list.getHashMapNurse());
            
            fileOutputStream = new FileOutputStream("patients.dat");
            objOutputStream = new ObjectOutputStream(fileOutputStream);
            objOutputStream.writeObject(list.getHashMapNurse());
            
            System.out.println("Data written to file successfully.");
            
        } catch (IOException e) {
            System.out.println("Error writing data to file: " + e.getMessage());
        }finally {
            try {
                objOutputStream.close();
                fileOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(HospitalManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    public void loadData() {
        FileInputStream fileInputStream = null;
        ObjectInputStream objInputStream = null;
        try {
            fileInputStream = new FileInputStream("nurses.dat");
            objInputStream = new ObjectInputStream(fileInputStream);

            list.setHashMapNurse((HashMap<String, Nurse>) objInputStream.readObject());
            
            fileInputStream = new FileInputStream("patients.dat");
            objInputStream = new ObjectInputStream(fileInputStream);
            list.setHashmapPatient((HashMap<String, Patient>) objInputStream.readObject());

        } catch (Exception e) {
            System.out.println("Error when load data: " + e.getMessage());
        } finally {
            try {
                objInputStream.close();
                fileInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(HospitalManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
