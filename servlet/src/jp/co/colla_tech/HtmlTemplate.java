package jp.co.colla_tech;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/HtmlTemplate")
public class HtmlTemplate extends HttpServlet {
	public static void header(PrintWriter out) {
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Servlet Kadai</title>");
		out.println("</head>");
		out.println("<body>");
	}
	public static void footer(PrintWriter out) {
		out.println("</body>");
		out.println("</html>");
	}
}