
package Menu;

import Validation.Check;

public class Main {
    public static void main(String[] args) {
        HospitalManager hospital = new HospitalManager();
        int choice;
        while(true){
            displayMenu();
            choice = Check.checkWithLimitation("Enter your choice: ", "Try again",1,10);
            switch(choice){
                    case 1:
                        hospital.createNurse();
                        break;
                    case 2:
                        hospital.findNurse();
                        break;
                    case 3:
                        hospital.updateNurse();
                        break;
                    case 4:
                        hospital.deleteNurse();
                        break;
                    case 5:
                        hospital.addPatient();
                        break;
                    case 6:
                        hospital.displayPatient();
                        break;
                    case 7:
                        hospital.sortPatient();
                        break;
                    case 8:
                        hospital.loadData();
                        break;
                    case 9:
                        hospital.saveData();
                        break;
                    case 10:
                        System.exit(0);
                        break;
            }
        }
    }
    
    public static void displayMenu() {
        System.out.println("                               MENU\n"
                + "==========================================================================\n"
                + "1 Create a nurse\n"
                + "2 Find the nurse\n"
                + "3 Update the nurse\n"
                + "4 Delete the nurse\n"
                + "5 Add patient\n"
                + "6 Display patient\n"
                + "7 Sort patient\n"
                + "8 Save data\n"
                + "9 Load data\n"
                + "10. Exit \n"
                + "==========================================================================");
    }
}
