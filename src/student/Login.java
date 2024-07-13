package student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Login")
public class Login extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		ServletContext Context = getServletContext();
		
		String DriverName = Context.getInitParameter("DriverName");
		String connectionSTR = Context.getInitParameter("connectionSTR");
		String Id = Context.getInitParameter("Id");
		String Pass = Context.getInitParameter("Pass");

		String email =request.getParameter("email").trim();
		String password = request.getParameter("password").trim();



		
		
		
		try {

			Class.forName(DriverName);
			Connection conn = DriverManager.getConnection(connectionSTR, Id, Pass);
			PreparedStatement ps = conn.prepareStatement("select * from studentregistration where email='"+email+"' and password='"+password+"' ");
			ResultSet rs = ps.executeQuery();
			
			


			if (rs.next()) {
			    String name = rs.getString("name");
			    out.print("<script>localStorage.setItem('userID', '" + name + "');</script>");
			    
			    // Close resources
			    rs.close();
			    ps.close();
			    conn.close();
			    
			    // Delay redirect to ensure JavaScript execution
			    out.print("<script>window.onload = function() { window.location.href = 'home.html'; }</script>");
			} else {
			    // Incorrect ID or password handling
			    out.print("<script>alert('Incorrect pass or id')</script>");
			    RequestDispatcher rd = request.getRequestDispatcher("LogIn.html");
			    rd.include(request, response);
			}




		} catch (Exception e) {
			e.printStackTrace();

		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
