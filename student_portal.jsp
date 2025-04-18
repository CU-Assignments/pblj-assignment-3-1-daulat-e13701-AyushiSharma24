<html>
<head><title>Student Attendance</title></head>
<body>
  <form action="AttendanceServlet" method="post">
    Student ID: <input type="text" name="studentId" /><br/>
    Date: <input type="date" name="date" /><br/>
    Present: <input type="checkbox" name="present" value="yes" /><br/>
    <input type="submit" value="Submit Attendance" />
  </form>
</body>
</html>