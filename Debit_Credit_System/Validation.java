import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Validation {
    private static Scanner sc = new Scanner(System.in);
    private static int buffer = 1;
    private static LocalDate local = LocalDate.now();

    public static int getInteger() {
            int amt = 0;
        System.out.print("Enter the amount : ");
        try {
            amt = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Enter the Right Value.");
            sc.next();
            amt = getInteger();
        }
        return amt;
    }
    public static char getCharacter( ){
        System.out.print("Enter the Type of Transaction(C for Credit/D for Debit) : ");
           char c = sc.next().charAt(0);
        if(c == 'c' ||c == 'C' ||c == 'd' ||c == 'D'){
            return c;
        }else{
            System.out.println("Enter the Right Character.");
            c = getCharacter();
        }
        return c;
    }

    public static String getDate() {
       String s = local.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
       return s;
    }
    
    
    public static String getDescription(){
        if(buffer == 1){
            sc.nextLine();
        }
        System.out.print("Enter the Description : ");
        String d = "";
        try {
            d = sc.nextLine();
            if(d.isEmpty()){
                getDescription();
            }else{
                return d;
            }
            return d;
        } catch (Exception e) {
            getDescription();
        }
        return d;
    }
    public static void main(String[] args) {
        getDescription();
    }
}
