package student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		ServletContext Context = getServletContext();
		String DriverName = Context.getInitParameter("DriverName");
		String connectionSTR = Context.getInitParameter("connectionSTR");
		String Id = Context.getInitParameter("Id");
		String Pass = Context.getInitParameter("Pass");
		
		int sroll = Integer.parseInt(request.getParameter("sroll"));
		String sname = request.getParameter("sname");
		String scity = request.getParameter("scity");
		int smarks = Integer.parseInt(request.getParameter("smarks"));


		
		
		
		try {

			Class.forName(DriverName);
			Connection conn = DriverManager.getConnection(connectionSTR, Id, Pass);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO student (sroll, sname ,scity, smarks) VALUES (?,?,?,?)");

			ps.setInt(1,sroll );
			ps.setString(2, sname);
			ps.setString(3, scity);
			ps.setInt(4,smarks );


			int i = ps.executeUpdate();

			if (i > 0) {
				response.sendRedirect("home.html");
			} else {
				out.print("something wrong....");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}