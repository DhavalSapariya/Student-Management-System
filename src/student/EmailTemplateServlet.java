package student;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmailTemplateServlet
 */
@WebServlet("/EmailTemplateServlet")
public class EmailTemplateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
    	String currentDate = dateFormat.format(new Date());
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />");
        out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\" />");
        out.println("<title>Static Template</title>");
        out.println("<link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap\" rel=\"stylesheet\" />");
        out.println("</head>");
        out.println("<body style=\"margin: 0; font-family: 'Poppins', sans-serif; background: #ffffff; font-size: 14px;\">");
        out.println("<div style=\"max-width: 680px; margin: 0 auto; padding: 45px 30px 60px; background: #f4f7ff; background-image: url(https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661497957196_595865/email-template-background-banner); background-repeat: no-repeat; background-size: 800px 452px; background-position: top center; font-size: 14px; color: #434343;\">");
        out.println("<header>");
        out.println("<table style=\"width: 100%\">");
        out.println("<tbody>");
        out.println("<tr style=\"height: 0\">");
        out.println("<td>");
        out.println("</td>");
        out.println("<td style=\"text-align: right\">");
        out.println("<span style=\"font-size: 16px; line-height: 30px; color: #ffffff\">" + currentDate + "</span>");
        out.println("</td>");
        out.println("</tr>");
        out.println("</tbody>");
        out.println("</table>");
        out.println("</header>");
        out.println("<main>");
        out.println("<div style=\"margin: 0; margin-top: 70px; padding: 92px 30px 115px; background: #ffffff; border-radius: 30px; text-align: center;\">");
        out.println("<div style=\"width: 100%; max-width: 489px; margin: 0 auto\">");
        out.println("<h1 style=\"margin: 0; font-size: 24px; font-weight: 500; color: #1f1f1f;\">Your OTP</h1>");
        out.println("<p style=\"margin: 0; margin-top: 17px; font-size: 16px; font-weight: 500;\">Hey Tomy,</p>");
        out.println("<p style=\"margin: 0; margin-top: 17px; font-weight: 500; letter-spacing: 0.56px;\">Thank you for choosing Archisketch Company. Use the following OTP to complete the procedure to change your email address. OTP is valid for <span style=\"font-weight: 600; color: #1f1f1f\">5 minutes</span>. Do not share this code with others, including Archisketch employees.</p>");
        out.println("<p style=\"margin: 0; margin-top: 60px; font-size: 40px; font-weight: 600; letter-spacing: 25px; color: #ba3d4f;\">123456</p>");
        out.println("</div>");
        out.println("</div>");
        out.println("<p style=\"max-width: 400px; margin: 0 auto; margin-top: 90px; text-align: center; font-weight: 500; color: #8c8c8c;\">Need help? Ask at <a href=\"mailto:archisketch@gmail.com\" style=\"color: #499fb6; text-decoration: none\">archisketch@gmail.com</a>");
        out.println("or visit our <a href=\"\" target=\"_blank\" style=\"color: #499fb6; text-decoration: none\">Help Center</a></p>");
        out.println("</main>");
        out.println("<footer style=\"width: 100%; max-width: 490px; margin: 20px auto 0; text-align: center; border-top: 1px solid #e6ebf1;\">");
        out.println("<p style=\"margin: 0; margin-top: 40px; font-size: 16px; font-weight: 600; color: #434343;\">Archisketch Company</p>");
        out.println("<p style=\"margin: 0; margin-top: 8px; color: #434343\">Address 540, City, State.</p>");
        out.println("<div style=\"margin: 0; margin-top: 16px\">");
        out.println("<a href=\"\" target=\"_blank\" style=\"display: inline-block\"><img width=\"36px\" alt=\"Facebook\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661502815169_682499/email-template-icon-facebook\" /></a>");
        out.println("<a href=\"\" target=\"_blank\" style=\"display: inline-block; margin-left: 8px\"><img width=\"36px\" alt=\"Instagram\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661504218208_684135/email-template-icon-instagram\" /></a>");
        out.println("<a href=\"\" target=\"_blank\" style=\"display: inline-block; margin-left: 8px\"><img width=\"36px\" alt=\"Twitter\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661503043040_372004/email-template-icon-twitter\" /></a>");
        out.println("<a href=\"\" target=\"_blank\" style=\"display: inline-block; margin-left: 8px\"><img width=\"36px\" alt=\"Youtube\" src=\"https://archisketch-resources.s3.ap-northeast-2.amazonaws.com/vrstyler/1661503195931_210869/email-template-icon-youtube\" /></a>");
        out.println("</div>");
        out.println("<p style=\"margin: 0; margin-top: 16px; color: #434343\">Copyright © 2022 Company. All rights reserved.</p>");
        out.println("</footer>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
  

}