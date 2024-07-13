package student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowDataServlet")
public class ShowDataServlet extends HttpServlet {

	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        PrintWriter out = response.getWriter();
	        response.setContentType("text/html"); // Set content type to HTML

	        String searchQuery = request.getParameter("search"); // Get search query from request parameter

	        ServletContext context = getServletContext();
	        String DriverName = context.getInitParameter("DriverName");
	        String connectionSTR = context.getInitParameter("connectionSTR");
	        String Id = context.getInitParameter("Id");
	        String Pass = context.getInitParameter("Pass");

	        try {
	            Class.forName(DriverName);
	            Connection conn = DriverManager.getConnection(connectionSTR, Id, Pass);
	            Statement st = conn.createStatement();

	            String sqlQuery = "SELECT * FROM student";

	            if (searchQuery != null && !searchQuery.isEmpty()) {
	                sqlQuery += " WHERE sname LIKE '%" + searchQuery + "%'";
	            }

	            ResultSet rs = st.executeQuery(sqlQuery);

	            out.println("<html>");
	            out.println("<head>");
	            out.println("<title>Data</title>");
	            out.println("<style>");
	            out.println("table {");
	            out.println("    width: 100%;");
	            out.println("    border-collapse: collapse;");
	            out.println("}");
	            out.println("th, td {");
	            out.println("    padding: 15px;");
	            out.println("    text-align: left;");
	            out.println("}");
	            out.println("th {");
	            out.println("    background-color: #f2f2f2;");
	            out.println("}");
	            out.println("tr:nth-child(odd) {");
	            out.println("    background-color: #f2f2f2;");
	            out.println("}");
	            out.println("tr:hover {");
	            out.println("    background-color: #ddd;");
	            out.println("}");
	            out.println(".button {");
	            out.println("    background-color: red;");
	            out.println("    border: none;");
	            out.println("    color: white;");
	            out.println("    padding: 10px 20px;");
	            out.println("    text-align: center;");
	            out.println("    text-decoration: none;");
	            out.println("    display: inline-block;");
	            out.println("    font-size: 16px;");
	            out.println("    cursor: pointer;");
	            out.println("}");
	            out.println(".search-form {");
	            out.println("    margin-bottom: 20px;");
	            out.println("    margin-top: 20px;");
	            out.println("}");
	            out.println(".search-input {");
	            out.println("    padding: 10px;");
	            out.println("    font-size: 16px;");
	            out.println("}");
	            out.println(".search-button {");
	            out.println("    padding: 10px 20px;");
	            out.println("    font-size: 16px;");
	            out.println("    cursor: pointer;");
	            out.println("    color: white;");
	            out.println("    border: none;");
	            out.println("}");
	            out.println(".button2 {");
	            out.println("    font-size: 26px;");
	            out.println("    cursor: pointer;");
	            out.println("    color: blue;");
	            out.println("    border: none;");
	            out.println("    background-color: #fafafa;");

	            out.println("}");
	            out.println("</style>");
	            out.println("</head>");
	            out.println("<body>");

	            // Search Form
	            out.println("<form method='post' class='search-form'>");
	            out.println("<input type='text' name='search' placeholder='Enter name to search' class='search-input'>");
	            out.println("<button type='submit' class='search-button'>&#128269</button>");
	            out.println("<button type='button' class='button2' onclick='window.location.href=window.location.href'>&#8635</button>");
	            out.println("</form>");

	            out.println("<table>");
	            out.println("<tr>");
	            out.println("<th>Roll No</th>");
	            out.println("<th>Name</th>");
	            out.println("<th>City </th>");
	            out.println("<th>Marks</th>");
	            out.println("<th>Action</th>");
	            out.println("</tr>");

	            
	            while (rs.next()) {
	                out.println("<tr>");
	                out.println("<td>" + rs.getInt(1) + "</td>");
	                out.println("<td>" + rs.getString(2) + "</td>");
	                out.println("<td>" + rs.getString(3) + "</td>");
	                out.println("<td>" + rs.getInt(4) + " &#8377;" + "</td>");
	                out.println("<td><form action='DeleteServlet' method='post' onsubmit='return confirmDelete()'><input type='hidden' name='sroll' value='" + rs.getInt(1) + "'><button class='button' type='submit' name='delete'>&#128465</button></form></td>");
	                out.println("</tr>");
	            }
	            
	         
	            out.println("</table>");
	            
	            out.println("<script>");
	            out.println("function confirmDelete() {");
	            out.println("    return confirm('Are you sure you want to delete this record?');");
	            out.println("}");
	            out.println("</script>");
	            
	            out.println("</body>");
	            out.println("</html>");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      doGet(request, response);
  }
}