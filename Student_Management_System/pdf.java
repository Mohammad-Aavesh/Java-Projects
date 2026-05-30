import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Element;
import com.itextpdf.text.BaseColor;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;

public class pdf {
    public static void Download_MarkSheet(int roll_no, Connection conn) throws SQLException, DocumentException, java.io.IOException {
    CallableStatement calls = conn.prepareCall("{CALL get_MarkSheet(?)}");
    calls.setInt(1, roll_no);
    ResultSet rs = calls.executeQuery();
    
    if (rs.next() && rs.getString("percentage") != null) {
        String fileName = "Marksheet_of_" + roll_no + ".pdf";
        String downloadsDir = System.getProperty("user.home") + "/Downloads/";
        new File(downloadsDir).mkdirs();
        String filePath = downloadsDir + fileName;
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        
        // School header
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
        Paragraph p = new Paragraph("SUNRAYS HIGH SECONDARY SCHOOL HARDA (M.P)",boldFont);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        document.add(new Paragraph(" "));
        
        // Student details
        document.add(new Paragraph("Student Name : " + rs.getString("name"), boldFont));
        document.add(new Paragraph("Roll Number : " + rs.getInt("roll_no")));
        document.add(new Paragraph("Father Name : " + rs.getString("father_name")));
        document.add(new Paragraph("Class : " + rs.getString("class")));
        document.add(new Paragraph("Section : " + rs.getString("section")));
        document.add(new Paragraph("Date of Birth : " + rs.getString("date_of_birth")));
        document.add(new Paragraph("Address : " + rs.getString("address")));
        document.add(new Paragraph(" "));
        
        // Marks table
        PdfPTable marksTable = new PdfPTable(4);
        marksTable.setWidthPercentage(100);
        marksTable.setSpacingBefore(10f);
        
        // Headers
        PdfPCell header1 = new PdfPCell(new Phrase("S.No", boldFont));
        header1.setHorizontalAlignment(Element.ALIGN_CENTER);
        header1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        marksTable.addCell(header1);
        PdfPCell header2 = new PdfPCell(new Phrase("Subjects", boldFont));
        header2.setHorizontalAlignment(Element.ALIGN_CENTER);
        header2.setBackgroundColor(BaseColor.LIGHT_GRAY);
        marksTable.addCell(header2);
        PdfPCell header3 = new PdfPCell(new Phrase("Max Marks", boldFont));
        header3.setHorizontalAlignment(Element.ALIGN_CENTER);
        header3.setBackgroundColor(BaseColor.LIGHT_GRAY);
        marksTable.addCell(header3);
        PdfPCell header4 = new PdfPCell(new Phrase("Obtained Marks", boldFont));
        header4.setHorizontalAlignment(Element.ALIGN_CENTER);
        header4.setBackgroundColor(BaseColor.LIGHT_GRAY);
        marksTable.addCell(header4);
        
        // Data rows
        marksTable.addCell("1"); marksTable.addCell("Hindi"); marksTable.addCell("100"); marksTable.addCell("" + rs.getInt("hindi"));
        marksTable.addCell("2"); marksTable.addCell("English"); marksTable.addCell("100"); marksTable.addCell("" + rs.getInt("english"));
        marksTable.addCell("3"); marksTable.addCell("Maths"); marksTable.addCell("100"); marksTable.addCell("" + rs.getInt("maths"));
        marksTable.addCell("4"); marksTable.addCell("Science"); marksTable.addCell("100"); marksTable.addCell("" + rs.getInt("science"));
        marksTable.addCell("5"); marksTable.addCell("Computer"); marksTable.addCell("100"); marksTable.addCell("" + rs.getInt("computer"));
        
        document.add(marksTable);
        document.add(new Paragraph(" "));
        
        // Summary
        String res = rs.getString("grade").equals("Fail") ? "Fail" : "Pass";
        document.add(new Paragraph("Total: " + rs.getInt("total"), boldFont));
        document.add(new Paragraph("Percentage: " + rs.getString("percentage"), boldFont));
        document.add(new Paragraph("Grade: " + rs.getString("grade"), boldFont));
        document.add(new Paragraph("Result: " + res, boldFont));
        
        document.close();
        
        System.out.println("✅ Marksheet saved: " + filePath);
        
        // Auto-open (Java 6+)
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop.getDesktop().open(new File(filePath));
        }
    } else {
        System.out.println("No marksheet data found!");
    }
    rs.close();
    calls.close();
}
}