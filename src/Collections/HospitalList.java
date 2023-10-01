
package Collections;

import Objects.Nurse;
import Objects.Patient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalList {
    HashMap<String, Nurse> hashMapNurse;
    HashMap<String, Patient> hashmapPatient;
    
    public HospitalList() {
    hashMapNurse = new HashMap<>();
    hashmapPatient = new HashMap<>();
    }

    public HashMap<String, Nurse> getHashMapNurse() {
        return hashMapNurse;
    }

    public void setHashMapNurse(HashMap<String, Nurse> hashMapNurse) {
        this.hashMapNurse = hashMapNurse;
    }

    public HashMap<String, Patient> getHashmapPatient() {
        return hashmapPatient;
    }

    public void setHashmapPatient(HashMap<String, Patient> hashmapPatient) {
        this.hashmapPatient = hashmapPatient;
    }
    
    public boolean checkDuplicateStaffID(String staffID) {
        for (Nurse nurse : hashMapNurse.values()) {
            if (nurse.getStaffID().equals(staffID)) {
                return true;
            }
        }
        return false;
    }
    
    public void addNurse(Nurse nurse) {
    hashMapNurse.put(nurse.getId(), nurse);
    }
    
    public HashMap<String, Nurse> findNurseByName(String name) {
    HashMap<String, Nurse> hashMapFound = new HashMap<>();
        name = name.toUpperCase();
        for (Nurse nurse : hashMapNurse.values()) {
            if (nurse.getName().toUpperCase().contains(name)) {
                hashMapFound.put(nurse.getId(), nurse);
            }
        }
        return hashMapFound;
    }
    
    public Nurse findNurseByStaffId(String staffID) {
        for (Nurse nurse : hashMapNurse.values()) {
            if (nurse.getStaffID().equalsIgnoreCase(staffID)) {
                return nurse;
            }
        }
        return null;
    }
    
    public void removeNurse(Nurse nurse) {
        hashMapNurse.remove(nurse.getId());
    }
    
    public boolean checkDuplicatedPatent(String id) {
        for (Map.Entry<String, Patient> entry : hashmapPatient.entrySet()) {
            String key = entry.getKey();
            Patient patient = entry.getValue();
            if (patient.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    
    public void addPatient(Patient patient) {
        hashmapPatient.put(patient.getId(), patient);
    }
    
    public boolean checkDuplicatePatent(String id) {
        for (Map.Entry<String, Patient> entry : hashmapPatient.entrySet()) {
            String key = entry.getKey();
            Patient patient = entry.getValue();
            if (patient.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
    
    public HashMap<String, Patient> getPatientByAdmissionDate(String startDate, String endDate) {
        HashMap<String, Patient> hashMapFound = new HashMap<>();
        for (Map.Entry<String, Patient> entry : hashmapPatient.entrySet()) {
            String key = entry.getKey();
            Patient patient = entry.getValue();
            if (patient.getAdmissionDate().compareTo(startDate) >= 0
                    && patient.getAdmissionDate().compareTo(endDate) <= 0) {
                hashMapFound.put(patient.getId(), patient);
            }
        }
        return hashMapFound;
    }
    
        public boolean checkNurseTask(Nurse nurse) {
        for (Map.Entry<String, Patient> entry : hashmapPatient.entrySet()) {
            String key = entry.getKey();
            Patient value = entry.getValue();
            
            List<Nurse> nursesAssigned = value.getNurseAssigned();
            for (Nurse nurse1 : nursesAssigned) {
                if (nurse1.getId().equalsIgnoreCase(nurse.getId())) {
                    return true;
                }
            }
        }
        return false;
    }
}
