
package Validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Check {
    
    static Scanner sc = new Scanner(System.in);
    
    public static final String STAFFID_REGEX = "[a-zA-Z0-9 ]+";
    public static final String NAME_REGEX = "[a-zA-Z ]+";
    public static final String GENDER_REGEX = "[fFmM]";
    public static final String ADDRESS_REGEX = "[a-zA-Z0-9./, ]+";
    public static final String PHONE_REGEX = "^0\\d{9}$";
    public static final String DEPARTMENT_REGEX = "^.{3,50}$";
    public static final String PATIENTID_REGEX = "[P]\\d{4}";
    public static final String DIAGNOSIS_REGEX = ".+";
    public static final String SELECT_NURSE_REGEX = "(\\d+[,][ ]*\\d+)|(\\d+)";

    public static final String YESNO_REGEX = "[yYnN]";
    public static String DATE_REGEX = "\\d{1,2}[/]\\d{1,2}[/]\\d{1,4}";
    
    public static int checkInteger(String message, String error){
        int num;
        String str;
        while(true){
            try{
                System.out.println(message);
                str = sc.nextLine();
                num=Integer.parseInt(str);
                if(num >=0 ){
                    return num;
                }
                else{
                    System.out.println("The value must be an Integer greater than 0!"); 
                }
            }catch (NumberFormatException e){
                System.out.println(error);
            }
        }
    }
    
    public static double checkDouble(String message, String error){
        double num;
        String str;
        while(true){
            try{
                System.out.println(message);
                str = sc.nextLine();
                num = Double.parseDouble(str);
                if(num>=0){
                    return num;
                }
                else{
                    System.out.println("The value must be Double greater than 0!");
                }
            }catch(NumberFormatException e){
                System.out.println(error);
            }
        }
    }
    
    public static String checkString(String message, String error, String pattern){
        while(true){
            System.out.println(message);
            String str = sc.nextLine().trim();
            if(str.isEmpty()){
                System.out.println("Please input a String value!");
            }else if(str.matches(pattern)){
                return str;
            }else{
                System.out.println(error);
            }
        }
    }
    
    public static String checkDate(String message, String error){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date;
        while(true){
            System.out.println(message);
            date = sc.nextLine();
            if(date.matches(DATE_REGEX)==false){
                System.out.println(error);
            }
            else if(checkDateExist(date, dateFormat)==false){
                System.out.println("The date is not exist!");
            }
            else{
                return date;
            }
        }
    }
    
    public static boolean checkDateExist(String dateInput, 
        SimpleDateFormat dateFormat) {
        dateFormat.setLenient(false);
        try {
            Date date = dateFormat.parse(dateInput);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
    
    public static boolean checkUpdate(String attribute){
        boolean updated = checkString("Do you want to update"+attribute+" [Y/N]",
                "Must be Y or N",YESNO_REGEX).equalsIgnoreCase("Y");
        return updated;
    }
    
    public static int checkWithLimitation(String message, String error, int min, int max){
        int num;
        String str;
        while(true){
            try{
                System.out.println(message);
                str = sc.nextLine();
                num=Integer.parseInt(str);
                if(num >=0 ){
                    if(num >= min && num <= max){
                    return num;
                    }
                    else {
                    System.out.println("Number must in range " + min + " - " + max);
                }
                }
                else{
                    System.out.println("The value must be an Integer greater than 0!"); 
                }
            }catch (NumberFormatException e){
                System.out.println(error);
            }
        }
    }
}
