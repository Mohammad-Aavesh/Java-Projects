import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
    final static String password = "iteg1234567";
    static Validation v = new Validation();
    static String pass;
    static int tries = 3;
    static int choice = 100;
    final static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Admin Panel.");
        System.out.print("Enter the Password :");
        pass = sc.nextLine();
        if(checkPassword(pass)){
            try {
                admin_Panel();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }else{
            if(tries > 0){
                 System.out.println("Try Again!");
                 tries--;
                 Admin.main(args);
            }else{
                System.out.println(" Try Again next time");
                return;
            }
        }
    }
    public static void admin_Panel() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/project1";
        Connection conn = DriverManager.getConnection(url, "root", "mohammad");
        int choice;
        do {
            System.out.println("---------------------------------------");
			System.out.println("| To Add New Students use           1 |");
			System.out.println("| To Update Student Data use        2 |");
			System.out.println("| To Delete Student Data use        3 |");
            System.out.println("| To View All Students Data use     4 |");
            System.out.println("| To Add Student Marks use          5 |");
            System.out.println("| To Update Student Marks use       6 |");
            System.out.println("| To View Marks of all Student use  7 |");
            System.out.println("| To Generate Students Result use   8 |");
            System.out.println("| To go Back use                    9 |");
			System.out.println("---------------------------------------");
			System.out.print("Enter the choice :");
			choice = Validation.checker(0);

            switch (choice) {
                case 1:
                    Admin.add_Student(conn);
                    break;

                case 2:
                    Update_Data.main(conn);
                    break;
            
                case 3:
                    Admin.delete_Student(conn);
                    break;
            
                case 4:
                    Admin.view_Student(conn);
                    break;
            
                case 5:
                    Admin.add_Update_Marks(conn,5);
                    break;
            
                case 6:
                    Admin.add_Update_Marks(conn,6);
                    break;

                case 7:
                    Admin.View_Marks(conn);
                    break;
            
                case 8:
                    Admin.generate_Result(conn);
                    break;
            
                case 9:
                    conn.close();
                        return;
            
                default:
                    System.out.println("Enter the right choice.");
                    break;
            }
            
        } while (choice != 9);
        
		return;
	}
    
    public static void add_Student(Connection conn) throws SQLException {
        String n = Validation.get_Name();
        String fn = Validation.get_Fname();
        String dob = Validation.get_Date_Of_Birth();
        int C = Validation.get_Class();
        String c = C+"th";
        String s = Validation.get_Section();
        String a = Validation.get_Address();
        String m = Validation.get_Number();
        
        CallableStatement cs = conn.prepareCall("{CALL Enter_Data(?,?,?,?,?,?,?)}");
        cs.setString(1, n);
        cs.setString(2, fn);
        cs.setString(3, dob);
        cs.setString(4, c);
        cs.setString(5, s);
        cs.setString(6, a);
        cs.setString(7, m);
        cs.executeQuery();
        System.out.println("Student added Successfully!");
        cs.close();
    }


    public static void delete_Student(Connection conn) throws SQLException {
        System.out.print("To delete student data ");
        int roll = Validation.get_roll_No();

        CallableStatement calls = conn.prepareCall("{CALL get_roll_no()}");
        ResultSet rs = calls.executeQuery();

        while(rs.next()){
            if(rs.getInt("roll_no") == roll){
                CallableStatement cs = conn.prepareCall("{CALL Delete_Data(?)}");
                cs.setInt(1, roll);
                cs.executeUpdate();
                cs.close();
                rs.close();
                calls.close();
                System.out.println("Student Data Deleted Successfully!");
                return;
            }
        }
        rs.close();
        calls.close();
        System.out.println("Error : the Student doesn't Exist!");
    }

    public static void view_Student(Connection conn) throws SQLException {
    CallableStatement calls = conn.prepareCall("{CALL Retrieve_Data()}");
    ResultSet rs = calls.executeQuery();

    if (!rs.next()) {
        System.out.println("ERROR : NO RECORD IS AVAILABLE!!!");
        rs.close();
        calls.close();
        return;
    }

    String format =
        "| %-6s | %-20s | %-18s | %-6s | %-7s | %-12s | %-15s | %-10s |%n";

    System.out.printf(format,
        "roll", "name", "father_name", "class", "section", "dob", "address", "mobile");

    System.out.println("-----------------------------------------------------------------------------------------------");

    do {
        System.out.printf(format,
            rs.getInt("roll_no"),
            trim(rs.getString("name"), 20),
            trim(rs.getString("father_name"), 18),
            trim(rs.getString("class"), 6),
            trim(rs.getString("section"), 7),
            trim(rs.getString("date_of_birth"), 12),
            trim(rs.getString("address"), 15),
            trim(rs.getString("mobile_no"), 10)
        );
    } while (rs.next());

    rs.close();
    calls.close();
}

private static String trim(String s, int len) {
    if (s == null) return "";
    return s.length() > len ? s.substring(0, len - 1) + "…" : s;
}
    public static void add_Update_Marks(Connection conn,int n) throws SQLException {
        System.out.print("To add student marks ");
        int roll = Validation.get_roll_No();

        CallableStatement calls = conn.prepareCall("{CALL fetch_roll_no()}");
        ResultSet rs = calls.executeQuery();

        while(rs.next()){
            if(rs.getInt("roll_no") == roll){
                int hindi = Validation.get_Marks("Hindi");
                int english = Validation.get_Marks("english");
                int maths = Validation.get_Marks("maths");
                int science = Validation.get_Marks("science");
                int computer = Validation.get_Marks("computer");
                int total = hindi+english+maths+science+computer;
                int per = total/5;
                String percentage = per+"%";
                String grade = Validation.get_Grade(per);

                CallableStatement cs = conn.prepareCall("{CALL Enter_Marks(?,?,?,?,?,?,?,?,?)}");
                cs.setInt(1, hindi);
                cs.setInt(2, english);
                cs.setInt(3, maths);
                cs.setInt(4, science);
                cs.setInt(5, computer);
                cs.setInt(6, total);
                cs.setString(7, percentage);
                cs.setString(8, grade);
                cs.setInt(9, roll);
                cs.executeQuery();
                if(n == 5){
                    System.out.println("Marks added Successfully!");
                }else{
                   System.out.println("Marks updated Successfully!"); 
                }
                cs.close();
                rs.close();
                calls.close();
                return;
            }
        }
        rs.close();
        calls.close();
        System.out.println("Error : the Student doesn't Exist!");
    }

    public static void View_Marks(Connection conn) throws SQLException {
    CallableStatement calls = conn.prepareCall("{CALL Retrieve_Marks()}");
    ResultSet rs = calls.executeQuery();

    String header = String.format(
        "| %-8s | %-5s | %-7s | %-5s | %-7s | %-8s | %-7s | %-11s | %-8s | %-16s |",
        "roll_no", "hindi", "english", "maths", "science", "computer", "total", "percentage", "grade", "result generated"
    );

    System.out.println(header);
    System.out.println("-----------------------------------------------------------------------------------------------------------");

    boolean found = false;

    while (rs.next()) {
        found = true;

        if (rs.getString("percentage") != null) {
            System.out.println(String.format(
                "| %-8d | %-5d | %-7d | %-5d | %-7d | %-8d | %-7d | %-11s | %-8s | %-16b |",
                rs.getInt("roll_no"),
                rs.getInt("hindi"),
                rs.getInt("english"),
                rs.getInt("maths"),
                rs.getInt("science"),
                rs.getInt("computer"),
                rs.getInt("total"),
                rs.getString("percentage"),
                rs.getString("grade"),
                rs.getBoolean("res")
            ));
        }
    }

    if (!found) {
        System.out.println("ERROR : NO RECORD IS AVAILABLE!!!");
    }

    rs.close();
    calls.close();
}
    public static void generate_Result(Connection conn) throws SQLException {
        System.out.print("To Generate result ");
        int roll = Validation.get_roll_No();

        CallableStatement calls = conn.prepareCall("{CALL get_res(?)}");
        calls.setInt(1, roll);
        ResultSet rs = calls.executeQuery();
            while(rs.next()){
            if(rs.getBoolean("res")){
                System.out.println("Your result has been already generated!");
                rs.close();
                calls.close();
                return;
                }else if(!rs.getBoolean("res")){
                    CallableStatement cs = conn.prepareCall("{CALL get_total(?)}");
                    cs.setInt(1, roll);
                    ResultSet r = cs.executeQuery();
                    while(r.next()){
                        if(r.getInt("total") > 0){
                        CallableStatement call = conn.prepareCall("{CALL Generate_Result(?)}");
                        call.setInt(1, roll);
                        call.executeQuery();
                        System.out.println("Your result has been generated!");
                        call.close();
                        r.close();
                        cs.close();
                        rs.close();
                        calls.close();
                        return;
                        }
                    }
            }
        }
            rs.close();
            calls.close();
            System.out.println("ERROR :Result is not generated yet or the Student doesn't Exist!.");
            return;
    }
    
    public static boolean checkPassword(String pass){
		if(pass.equals(password))
			return true;
		else
			return false;
	}
}
