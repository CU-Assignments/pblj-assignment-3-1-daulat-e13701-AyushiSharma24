import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String studentId = request.getParameter("studentId");
    String date = request.getParameter("date");
    boolean present = request.getParameter("present") != null;

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "password");

      String query = "INSERT INTO attendance (student_id, date, present) VALUES (?, ?, ?)";
      PreparedStatement ps = con.prepareStatement(query);
      ps.setString(1, studentId);
      ps.setString(2, date);
      ps.setBoolean(3, present);

      int rows = ps.executeUpdate();
      if (rows > 0) {
        out.println("<h2>Attendance recorded successfully!</h2>");
      } else {
        out.println("<h2>Failed to record attendance.</h2>");
      }

      con.close();
    } catch (Exception e) {
      out.println("Error: " + e.getMessage());
    }
  }
}