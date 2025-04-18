import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String empId = request.getParameter("empId");

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "password");

      String query = "SELECT * FROM employees WHERE id = ?";
      PreparedStatement ps = con.prepareStatement(query);
      ps.setString(1, empId);
      ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        out.println("<h2>Employee Found:</h2>");
        out.println("ID: " + rs.getInt("id") + "<br/>");
        out.println("Name: " + rs.getString("name") + "<br/>");
        out.println("Department: " + rs.getString("department") + "<br/>");
      } else {
        out.println("<h2>No employee found with ID: " + empId + "</h2>");
      }

      con.close();
    } catch (Exception e) {
      out.println("Error: " + e.getMessage());
    }
  }
}