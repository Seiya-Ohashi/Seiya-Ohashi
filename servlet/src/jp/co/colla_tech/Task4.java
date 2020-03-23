package jp.co.colla_tech;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Task4")
public class Task4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		String result[] = {"大吉","中吉","小吉","末吉","凶","大凶"};
		Random random = new Random();
		int randomValue = random.nextInt(6);

		HtmlTemplate.header(out);
		out.print("<p>↓占い結果↓</p>");
		out.print("<h1>");
		out.print(result[randomValue]);
		out.print("</h1>");
		HtmlTemplate.footer(out);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
