
package Objects;

import java.util.List;

public class Patient extends Person {
    private String diagnosis, admissionDate, dischargeDate;
    private List<Nurse> nurseAssigned;
    
    public Patient(){}
    
    public Patient(String id, String name, int age, String gender, String address, String phone, 
            String diagnosis, String admissionDate, String dischargeDate,
            List<Nurse> nurseAssigned) {
        super(id, name, age, gender, address, phone);
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.nurseAssigned = nurseAssigned;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        if (diagnosis == null) {
            throw new IllegalArgumentException("Diagnosis cannot be null");
        }
        this.diagnosis = diagnosis;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        if (admissionDate == null) {
            throw new IllegalArgumentException("Admission Date cannot be null");
        }
        this.admissionDate = admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        if (dischargeDate == null) {
            throw new IllegalArgumentException("Discharge Date cannot be null");
        }
        this.dischargeDate = dischargeDate;
    }

    public List<Nurse> getNurseAssigned() {
        return nurseAssigned;
    }

    public void setNurseAssigned(List<Nurse> nurseAssigned) {
        if (nurseAssigned == null) {
            throw new IllegalArgumentException("Nurse Assigned cannot be null");
        }
        this.nurseAssigned = nurseAssigned;
    }
    
}
