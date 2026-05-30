import java.util.InputMismatchException;
import java.util.Scanner;
public class Validation {
	static int buffer = 1;
    static int choice = 20;
    static Scanner sc = new Scanner(System.in);
    public static int checker(int choice){
			try{
				choice = sc.nextInt();
				return choice;
			}catch(InputMismatchException e){
				System.out.println(e+"\n");
				System.out.print("Enter the choice correctly:");
				sc.next();
				return checker(0);
			}
	}
	public static int get_roll_No() {
            int roll = 0;
        System.out.print("Enter the roll Number : ");
        try {
            roll = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Enter the Right Value.");
            sc.next();
            roll = get_roll_No();
        }
        return roll;
    }
    
    public static String get_Name(){
        if(buffer == 1){
            sc.nextLine();
			buffer--;
        }
        System.out.print("Enter the Name : ");
        String name = "";
        try {
            name = sc.nextLine();
            if(name.isEmpty()){
                return name = get_Name().trim();
            }else{
                return name.trim();
            }
        } catch (Exception e) {
			System.out.println("Enter the name correctly.");
            name = get_Name();
        }
        return name.trim();
    }
	public static String get_Fname(){
        if(buffer == 0){
            buffer++;
        }else if(buffer == 1){
            sc.nextLine();
            buffer--;
        }
        System.out.print("Enter the Father name : ");
        String name = "";
        try {
            name = sc.nextLine();
            if(name.isEmpty()){
                return name = get_Fname().trim();
            }else{
                return name.trim();
            }
        } catch (Exception e) {
            name = get_Fname();
        }
        return name.trim();
    }

	public static String get_Date_Of_Birth(){
        System.out.print("Enter the Date of birth in given format(dd/mm/yyyy) :");
        String date = "";
        try {
            date = sc.nextLine();
            if(date.isEmpty()||date.trim().charAt(2) != '/'||date.trim().charAt(5) != '/'||date.trim().length() != 10){
                return date = get_Date_Of_Birth().trim();
            }else{
                return date.trim();
            }
        } catch (Exception e) {
            date = get_Date_Of_Birth();
        }
        return date.trim();
    }

	public static int get_Class() {
            int clas = 0;
        System.out.print("Enter the class(1 to 12) : ");
        try {
            clas = sc.nextInt();
			if(clas < 1 || clas > 12){
				System.out.println("Enter the Right Class.");
                return clas = get_Class();
            }else{
                return clas;
            }
        } catch (Exception e) {
            System.out.println("Enter the Right Class.");
            sc.next();
            clas = get_Class();
        }
        return clas;
    }

	public static String get_Section( ){
        System.out.print("Enter the section of class(A, B, C or D) :");
           char c = sc.next().charAt(0);
        if(c == 'A' ||c == 'a' ||c == 'B' ||c == 'b'||c == 'C' ||c == 'c'||c == 'D' ||c == 'd'){
            String w = String.valueOf(c);
            return w;
        }else{
            System.out.println("Enter the Right Character.");
            String w = get_Section();
            return w;
        }
    }

	public static String get_Address(){
		if(buffer == 1){
            sc.nextLine();
			buffer--;
        }
        System.out.print("Enter the Address : ");
        String add = "";
        try {
            add = sc.nextLine();
            if(add.isEmpty()){
                return add = get_Address().trim();
            }else{
                return add.trim();
            }
        } catch (Exception e) {
            add = get_Address();
        }
        return add.trim();
    }

	public static String get_Number(){
		if(buffer == 0){
			buffer++;
        }else if(buffer == 1){
            sc.nextLine();
            buffer--;
        }
        System.out.print("Enter the Number : ");
        String mno = "";
        try {
            mno = sc.nextLine();
            if(mno.isEmpty()|| mno.trim().length() !=10 || isNum(mno)){
				System.out.println("the number must contain 10 digits");
                return mno = get_Number().trim();
            }else{
                return mno.trim();
            }
        } catch (Exception e) {
            mno = get_Number();
        }
        return mno.trim();
    }
	public static boolean isNum(String mno) {
		try{
		Long n = Long.parseLong(mno);
		return false;
		}catch(Exception e){
			return true;
		}
	}
    public static int get_Marks(String s){
        int marks = 0;
        System.out.print("Enter "+s+" Marks :");
        try {
            marks = sc.nextInt();
            if(marks > 100 || marks < 0){
                return marks = get_Marks(s);
            }else{
                return marks;
            }        } catch (Exception e) {
            System.out.println("Enter the Right marks.");
            sc.next();
            marks = get_Marks(s);
        }
        return marks;
    }
    public static String get_Grade(int per) {
        if(per >= 90){
            return "A+";
        }else if(per >= 75){
            return "A";
        }else if(per >= 60){
            return "B";
        }else if(per >= 50){
            return "C";
        }else{
            return "Fail";
        }
    }

    public static void Enter_Admin() throws Exception{
        do{
            System.out.println("---------------------------------");
			System.out.println("| Enter 1 to Start Admin Panel. |");
			System.out.println("| Enter 0 to go back.           |");
			System.out.println("---------------------------------");
			System.out.print("Enter the choice :");
            choice = Validation.checker(0);
        switch (choice) {
            case 1:
                Admin.main(null);
                break;

            case 0:
                return;
        
            default:System.out.println("Enter the right choice!");
                break;
        }
        }while(choice != 0);
    }

    public static void Enter_Student() throws Exception{
        do{
            System.out.println("-----------------------------------");
			System.out.println("| Enter 1 to Start Student Panel. |");
			System.out.println("| Enter 0 to go back.             |");
			System.out.println("-----------------------------------");
			System.out.print("Enter the choice :");
            choice = Validation.checker(0);
        switch (choice) {
            case 1:
                Student.main(null);
                break;

            case 0:
                return;

            default:System.out.println("Enter the right choice!");
                break;
        }
        }while(choice != 0);
    }
}