import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

public class Student {
    static int choice = 10;

    public static void main(String[] args)
            throws ClassNotFoundException, SQLException, DocumentException, IOException {

        Scanner sc = new Scanner(System.in);

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/project1";
        Connection conn = DriverManager.getConnection(url, "root", "mohammad");

        System.out.print("Enter the roll number : ");
        int roll_no = Validation.checker(0);

        if (isavailable(roll_no, conn)) {
            do {
                System.out.println("----------------------------------");
                System.out.println("| Enter 1 to see Result.         |");
                System.out.println("| Enter 2 to Download MarkSheet. |");
                System.out.println("| Enter 3 to go back.            |");
                System.out.println("----------------------------------");

                System.out.print("Enter your choice : ");
                choice = Validation.checker(0);

                switch (choice) {
                    case 1:
                        View_Result(roll_no, conn);
                        break;

                    case 2:
                        pdf.Download_MarkSheet(roll_no, conn);
                        break;

                    case 3:
                        conn.close();
                        return;

                    default:
                        System.out.println("Enter the Right choice.");
                        break;
                }
            } while (choice != 3);
        }

        sc.close();
    }

    public static void View_Result(int roll_no, Connection conn) throws SQLException {
        CallableStatement calls = conn.prepareCall("{CALL get_MarkSheet(?)}");
        calls.setInt(1, roll_no);
        ResultSet rs = calls.executeQuery();

        while (rs.next()) {
            if (rs.getString("percentage") != null) {
                System.out.println("School Name : SUNRAYS HIGH SECONDARY SCHOOL HARDA (M.P)");
                System.out.println("Student Name : " + rs.getString("name"));
                System.out.println("Roll Number : " + rs.getInt("roll_no"));
                System.out.println("Father Name : " + rs.getString("father_name"));
                System.out.println("Class : " + rs.getString("class"));
                System.out.println("Section : " + rs.getString("section"));
                System.out.println("Date of Birth : " + rs.getString("date_of_birth"));
                System.out.println("Address : " + rs.getString("address"));
                System.out.println("---------------------------------------------");
                System.out.println("|Sno| Subjects | Max Marks | Obtained Marks |");
                System.out.println("| 1. | Hindi    |    100    |       " + rs.getInt("hindi") + "      |");
                System.out.println("| 2. | English  |    100    |       " + rs.getInt("english") + "      |");
                System.out.println("| 3. | Maths    |    100    |       " + rs.getInt("maths") + "      |");
                System.out.println("| 4. | Science  |    100    |       " + rs.getInt("science") + "      |");
                System.out.println("| 5. | Computer |    100    |       " + rs.getInt("computer") + "      |");
                System.out.println("---------------------------------------------");
                System.out.println("|           total            |     " + rs.getInt("total") + "     |");
                System.out.println("|          Percentage        |     " + rs.getString("percentage") + "     |");
                System.out.println("|           grade            |      " + rs.getString("grade") + "      |");
                System.out.println("---------------------------------------------");

                String res;
                if (rs.getString("grade").equals("Fail")) {
                    res = "Fail";
                } else {
                    res = "Pass";
                }

                System.out.println("|            result          |     " + res + "     |");
                System.out.println("---------------------------------------------");
            }
        }

        rs.close();
        calls.close();
    }

    private static boolean isavailable(int roll_no, Connection conn) throws SQLException {
        CallableStatement calls = conn.prepareCall("{CALL get_res(?)}");
        calls.setInt(1, roll_no);
        ResultSet rs = calls.executeQuery();

        while (rs.next()) {
            if (rs.getBoolean("res")) {
                return true;
            } else if (rs.getString("name") != null) {
                System.out.println("Error: The result hasn't been generated yet!.");
                return false;
            } else if (!rs.getBoolean("res")) {
                System.out.println("Error: No such roll number present in the database.");
                return false;
            }
        }

        System.out.println("Error: No such roll number present in the database.");
        return false;
    }

}