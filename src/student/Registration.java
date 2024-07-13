package student;

import java.io.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.util.Properties;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

@WebServlet("/Registration")
public class Registration extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
		String currentDate = dateFormat.format(new Date());

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		ServletContext Context = getServletContext();

		String DriverName = Context.getInitParameter("DriverName");
		String connectionSTR = Context.getInitParameter("connectionSTR");
		String Id = Context.getInitParameter("Id");
		String Pass = Context.getInitParameter("Pass");

		String otp = generateOTP(); // Generate OTP

		RequestDispatcher rd;

		String name = request.getParameter("name").trim();
		String phone = request.getParameter("phone").trim();
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();

		String messageBody = "<!DOCTYPE html>" + "<html lang=\"en\">" + "<head>" + "<meta charset=\"UTF-8\" />"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />"
				+ "<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\" />" + "<title>Static Template</title>"
				+ "<link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap\" rel=\"stylesheet\" />"
				+ "</head>"
				+ "<body style=\"margin: 0; font-family: 'Poppins', sans-serif; background: #ffffff; font-size: 14px;\">"
				+ "<div style=\"max-width: 680px; margin: 0 auto; padding: 45px 30px 60px; background: #f4f7ff; background-image: url(https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661497957196_595865/email-template-background-banner); background-repeat: no-repeat; background-size: 800px 452px; background-position: top center; font-size: 14px; color: #434343;\">"
				+ "<header>" + "<table style=\"width: 100%\">" + "<tbody>" + "<tr style=\"height: 0\">"
				+ "<td style=\"text-align: right\">"
				+ "<span style=\"font-size: 16px; line-height: 30px; color: #ffffff\">Current Date : " + currentDate
				+ "</span>" + "</td>" + "</tr>" + "</tbody>" + "</table>" + "</header>" + "<main>"
				+ "<div style=\"margin: 0; margin-top: 70px; padding: 92px 30px 115px; background: #ffffff; border-radius: 30px; text-align: center;\">"
				+ "<div style=\"width: 100%; max-width: 489px; margin: 0 auto\">"
				+ "<p style=\"margin: 0; margin-top: 17px; font-size: 20px; font-weight: 500;\">Welcome " + name
				+ "</p>"
				+ "<h3 style=\"margin-top: 25px; font-size: 24px; font-weight: 500; color: #ba3d4f;\">Your OTP</h3>"
				+ "<p style=\"margin: 0; margin-top: 30px; font-size: 25px; font-weight: 600; letter-spacing: 10px; color: #ba3d4f;\">"
				+ otp + "</p>"
				+ "<p style=\"margin: 0; margin-top: 17px; font-size: 17px; font-weight: 500; letter-spacing: 0.56px;\">Do not share this code with others .... &#128274;</p>"
				+ "</div>" + "</div>"
				+ "<p style=\"max-width: 400px; margin: 0 auto; margin-top: 90px; text-align: center; font-weight: 500; color: #8c8c8c;\">Need help? Ask at <a href=\"shivampatel635272@gmail.com\" style=\"color: #499fb6; text-decoration: none\">shivampatel635272@gmail.com</a>"
				+ "</main>"
				+ "<footer style=\"width: 100%; max-width: 490px; margin: 20px auto 0; text-align: center; border-top: 1px solid #e6ebf1;\">"
				+ "<p style=\"margin: 0; margin-top: 40px; font-size: 16px; font-weight: 600; color: #434343;\">Shivam Menpara</p>"
				+ "<div style=\"margin: 0; margin-top: 16px\">"
				+ "<a href=\"\" target=\"_blank\" style=\"display: inline-block\"><img width=\"36px\" alt=\"Facebook\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661502815169_682499/email-template-icon-facebook\" /></a>"
				+ "<a href=\"\" target=\"_blank\" style=\"display: inline-block; margin-left: 8px\"><img width=\"36px\" alt=\"Instagram\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661504218208_684135/email-template-icon-instagram\" /></a>"
				+ "<a href=\"\" target=\"_blank\" style=\"display: inline-block; margin-left: 8px\"><img width=\"36px\" alt=\"Twitter\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661503043040_372004/email-template-icon-twitter\" /></a>"
				+ "<a href=\"\" target=\"_blank\" style=\"display: inline-block; margin-left: 8px\"><img width=\"36px\" alt=\"Youtube\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661503195931_210869/email-template-icon-youtube\" /></a>"
				+ "</div>"
				+ "<p style=\"margin: 0; margin-top: 16px; color: #434343\">Copyright � 2023 SDMenpara. All rights reserved.</p>"
				+ "</footer>" + "</div>" + "</body>" + "</html>";

		try {
			Class.forName(DriverName);
			Connection conn = DriverManager.getConnection(connectionSTR, Id, Pass);
			PreparedStatement ps = conn.prepareStatement("insert into studentregistration values(?,?,?,?)");

			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, phone);

			HttpSession session = request.getSession();
			session.setAttribute("otp", otp);
			session.setAttribute("Email", email);
			session.setAttribute("userID", name);
			session.setAttribute("Password", password);
			session.setAttribute("PhoneNumber", phone);

			if (ps.executeUpdate() > 0) {
				// Send OTP to the provided email
				sendEmail(email, "OTP Verification", messageBody);

				// Redirect to OTP verification page
				response.sendRedirect("OTP.html");
				response.sendRedirect("login.html");

			} else {
				// out.print("<script>alert('Registration Not
				// Successfully');</script>");
				rd = request.getRequestDispatcher("./registration.html");
				rd.include(request, response);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void sendEmail(String toEmail, String subject, String body) throws MessagingException {

		final String fromEmail = "dhavalshapariya7693@gmail.com";
		final String password = "gurv nvqo rnpk znsn";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getInstance(props, auth);

		MimeMessage msg = new MimeMessage(session);
		msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		msg.addHeader("format", "flowed");
		msg.addHeader("Content-Transfer-Encoding", "8bit");

		msg.setFrom(new InternetAddress(fromEmail));
		msg.setReplyTo(InternetAddress.parse(fromEmail, false));
		msg.setSubject(subject, "UTF-8");
		msg.setContent(body, "text/html");
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
		Transport.send(msg);
	}

	private String generateOTP() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}
}
