import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update_Data {
    static int choice = 100;
    public static void main(Connection conn) throws SQLException {
        System.out.print("To update student data ");
        int roll = Validation.get_roll_No();
        do {
            System.out.println("---------------------------------------");
			System.out.println("| To update name of student use     1 |");
			System.out.println("| To update father name use         2 |");
			System.out.println("| To update class use               3 |");
            System.out.println("| To update section use             4 |");
            System.out.println("| To update Student Address use     5 |");
            System.out.println("| To update mobile number use       6 |");
            System.out.println("| To update Date of Birth use       7 |");
            System.out.println("| To update everything use          8 |");
            System.out.println("| To go Back use                    0 |");
			System.out.println("---------------------------------------");
			System.out.print("Enter the choice :");
			choice = Validation.checker(0);

            switch (choice) {
                case 1:
                    Update_Data.update_name(conn,roll);
                    break;

                case 2:
                    Update_Data.Update_Father_name(conn,roll);
                    break;
                
                case 3:
                    Update_Data.update_class(conn,roll);
                    break;
            
                case 4:
                    Update_Data.update_section(conn, roll);
                    break;

                case 5:
                    Update_Data.update_Address(conn, roll);
                    break;
            
                case 6:
                    Update_Data.update_number(conn, roll);
                    break;
            
                case 7:
                    Update_Data.update_DOB(conn,roll);
                    break;
            
                case 8:
                    Update_Data.update_Student(conn,roll);
                    break;

                case 0:
                    return;
            
                default:System.out.println("Enter the right choice");
                    break;
            }
        } while (choice != 0);
    }
    public static void update_name(Connection conn, int roll) throws SQLException {
    CallableStatement calls = conn.prepareCall("{CALL get_roll_no()}");
    ResultSet rs = calls.executeQuery();

    while (rs.next()) {
        if (rs.getInt("roll_no") == roll) {
            String n = Validation.get_Name();
            CallableStatement cs = conn.prepareCall("{CALL update_name(?,?)}");
            cs.setString(1, n);
            cs.setInt(2, roll);
            cs.executeUpdate();
            System.out.println("Student name updated Successfully!");
            cs.close(); rs.close(); calls.close();
            return;
        }
    }
    System.out.println("Error : the Student doesn't Exist!");
}
    public static void Update_Father_name(Connection conn, int roll) throws SQLException {
    CallableStatement calls = conn.prepareCall("{CALL get_roll_no()}");
    ResultSet rs = calls.executeQuery();

    while (rs.next()) {
        if (rs.getInt("roll_no") == roll) {
            String fn = Validation.get_Fname();
            CallableStatement cs = conn.prepareCall("{CALL update_father_name(?,?)}");
            cs.setString(1, fn);
            cs.setInt(2, roll);
            cs.executeUpdate();
            System.out.println("Student father name updated Successfully!");
            cs.close(); rs.close(); calls.close();
            return;
        }
    }
    System.out.println("Error : the Student doesn't Exist!");
}
    public static void update_class(Connection conn, int roll) throws SQLException {
    CallableStatement calls = conn.prepareCall("{CALL get_roll_no()}");
    ResultSet rs = calls.executeQuery();

    while (rs.next()) {
        if (rs.getInt("roll_no") == roll) {
            int C = Validation.get_Class();
            String clas = C + "th";
            CallableStatement cs = conn.prepareCall("{CALL update_class(?,?)}");
            cs.setString(1, clas);
            cs.setInt(2, roll);
            cs.executeUpdate();
            System.out.println("Student class updated Successfully!");
            cs.close(); rs.close(); calls.close();
            return;
        }
    }
    System.out.println("Error : the Student doesn't Exist!");
}
    public static void update_section(Connection conn, int roll) throws SQLException {
    CallableStatement calls = conn.prepareCall("{CALL get_roll_no()}");
    ResultSet rs = calls.executeQuery();

    while (rs.next()) {
        if (rs.getInt("roll_no") == roll) {
            String sec = Validation.get_Section();
            CallableStatement cs = conn.prepareCall("{CALL update_section(?,?)}");
            cs.setString(1, sec);
            cs.setInt(2, roll);
            cs.executeUpdate();
            System.out.println("Student section updated Successfully!");
            cs.close(); rs.close(); calls.close();
            return;
        }
    }
    System.out.println("Error : the Student doesn't Exist!");
}
    public static void update_Address(Connection conn, int roll) throws SQLException {
    CallableStatement calls = conn.prepareCall("{CALL get_roll_no()}");
    ResultSet rs = calls.executeQuery();

    while (rs.next()) {
        if (rs.getInt("roll_no") == roll) {
            String addr = Validation.get_Address();
            CallableStatement cs = conn.prepareCall("{CALL update_address(?,?)}");
            cs.setString(1, addr);
            cs.setInt(2, roll);
            cs.executeUpdate();
            System.out.println("Student address updated Successfully!");
            cs.close(); rs.close(); calls.close();
            return;
        }
    }
    System.out.println("Error : the Student doesn't Exist!");
}
    public static void update_number(Connection conn, int roll) throws SQLException {
    CallableStatement calls = conn.prepareCall("{CALL get_roll_no()}");
    ResultSet rs = calls.executeQuery();

    while (rs.next()) {
        if (rs.getInt("roll_no") == roll) {
            String mobile = Validation.get_Number();
            CallableStatement cs = conn.prepareCall("{CALL update_mobile(?,?)}");
            cs.setString(1, mobile);
            cs.setInt(2, roll);
            cs.executeUpdate();
            System.out.println("Student mobile number updated Successfully!");
            cs.close(); rs.close(); calls.close();
            return;
        }
    }
    System.out.println("Error : the Student doesn't Exist!");
}
    private static void update_DOB(Connection conn, int roll) throws SQLException {
        CallableStatement calls = conn.prepareCall("{CALL get_roll_no()}");
        ResultSet rs = calls.executeQuery();

        while (rs.next()) {
            if(rs.getInt("roll_no") == roll){
                
                String dob = Validation.get_Date_Of_Birth();

                CallableStatement cs = conn.prepareCall("{CALL update_dob(?,?)}");
                cs.setString(1, dob);
                cs.setInt(2, roll);
                cs.executeQuery();
                System.out.println("Student Date of birth updated Successfully!");
                cs.close();
                rs.close();
                calls.close();
                return;
            }
        }System.out.println("Error : the Student doesn't Exist!");

    }
    public static void update_Student(Connection conn,int roll) throws SQLException {

        CallableStatement calls = conn.prepareCall("{CALL get_roll_no()}");
        ResultSet rs = calls.executeQuery();

        while (rs.next()) {
            if(rs.getInt("roll_no") == roll){
                String n = Validation.get_Name();
                String fn = Validation.get_Fname();
                String dob = Validation.get_Date_Of_Birth();
                int C = Validation.get_Class();
                String c = C+"th";
                String s = Validation.get_Section();
                String a = Validation.get_Address();
                String m = Validation.get_Number();

                CallableStatement cs = conn.prepareCall("{CALL Update_Data(?,?,?,?,?,?,?,?)}");
                cs.setString(1, n);
                cs.setString(2, fn);
                cs.setString(3, dob);
                cs.setString(4, c);
                cs.setString(5, s);
                cs.setString(6, a);
                cs.setString(7, m);
                cs.setInt(8, roll);
                cs.executeQuery();
                System.out.println("Student data updated Successfully!");
                cs.close();
                rs.close();
                calls.close();
                return;
            }
        }System.out.println("Error : the Student doesn't Exist!");
    }
}
