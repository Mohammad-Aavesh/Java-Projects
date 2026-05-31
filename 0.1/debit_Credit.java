import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class debit_Credit {
    private static int Num = 0;
    private static int Balance = 0;
    private static Validation s = new Validation();
    private static Scanner sc = new Scanner(System.in);
    private static List<Integer> Amount = new ArrayList<>();
    private static List<Character> Type = new ArrayList<>();
    private static List<String> Date = new ArrayList<>();
    private static List<String> Description = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/RECORDS";
        Connection conn = DriverManager.getConnection(url, "root", "mohammad");
        System.out.println("==================================");
        System.out.println("   CREDIT / DEBIT RECORD SYSTEM   ");
        System.out.println("==================================");
        int choice = 0;
        do {
            System.out.println(" 1. Add Transaction ");
            System.out.println(" 2. View Transaction ");
            System.out.println(" 3. View Balance");
            System.out.println(" 4. Exit\n");
            System.out.print(" Enter Your Choice : ");
            try{
            choice = sc.nextInt();
            }catch(Exception e){
                choice = ChoiceLoop();
            }
            
            switch (choice) {
                case 1:
                    try {
                        Add_Transaction(conn);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                
                case 2:
                    View_Transaction(conn);
                    break;
                
                case 3:
                    View_Balance();
                    break;
            
                case 4:
                    conn.close();
                    System.out.println("Thank you for Using Debit / Credit Record System.");
                    break;
            
                default:
                    System.out.println("Enter the Right choice.");
            }
        } while (choice!=4);
    }

    public static void Add_Transaction(Connection conn)throws Exception{
        char typ = s.getCharacter();

        int amt = s.getInteger();

        if(typ == 'c' ||typ == 'C'){
            Balance+=amt;
        }else{
            if(amt > Balance){
                System.out.println("ERROR : Due to Insufficient Balance Transaction Procedure Has Stopped.");
                return;
            }else{
                Balance-=amt;
            }
        }

        String date = s.getDate();

        String Descri = s.getDescription();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO transactions (Date, Type, Amount, Descrip) VALUES (?, ?, ?, ?)");
        ps.setString(1, date);
         if(typ == 'c' ||typ == 'C'){
            ps.setString(2, "CREDIT");
        }else{
            ps.setString(2, "DEBIT ");
        }
        
        ps.setInt(3, amt);
        ps.setString(4, Descri);
        ps.executeUpdate();
        Num++;
        ps.close();
        System.out.println("Data has been Successfully Entered!\n");
    }

    public static void View_Transaction(Connection conn) throws Exception{
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("Select * from transactions");
        if(Num > 0){
            int i = 0;
            System.out.println();
            System.out.println("================================================");
            System.out.println("|| S.No |    DATE   |  TYPE  | Amount| DESCRIPTION ||");
            System.out.println("================================================");
            while (rs.next()) {
                i++;
                if(rs.getString("Descrip").length() < 12){
                    if(i < 10){
                        System.out.println(" "+i+" | "+rs.getString("Date")+" | "+rs.getString("Type")+"  |  "+rs.getInt("Amount")+" | "+rs.getString("Descrip")+"  |");
                    }else{
                        System.out.println(" "+i+"| "+rs.getString("Date")+" | "+rs.getString("Type")+"  |  "+rs.getInt("Amount")+" | "+rs.getString("Descrip")+"  |");
                    }
                }else{
                        String D = rs.getString("Descrip");
                    if(i < 10){
                        System.out.println(" "+i+" | "+rs.getString("Date")+" | "+rs.getString("Type")+"  |  "+rs.getInt("Amount")+" | "+D.substring(0, 10)+"..  |");
                    }else{
                        System.out.println(" "+i+"| "+rs.getString("Date")+" | "+rs.getString("Type")+"  |  "+rs.getInt("Amount")+" | "+D.substring(0, 10)+"..  |");
                    }
                }
            }
        }else{
            System.out.println("No Transaction has been Recorded.");
        }  
        stat.close();
         rs.close();   
    }

    public static void View_Balance(){
        System.out.println();
        System.out.print("Current Balance : ");
        System.out.println(Balance);
        System.out.println();
    }
    public static int ChoiceLoop(){
        sc.next();
        System.out.println("Enter the Right Choice.");
        System.out.print(" Enter Your Choice : ");
        int choice = 0;
            try{
            choice = sc.nextInt();
            }catch(Exception e){
                choice = ChoiceLoop();
                return choice;
            }
        return choice;
    }
}
