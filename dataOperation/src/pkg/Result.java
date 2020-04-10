package pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Result")
public class Result extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");

		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String contactType = request.getParameter("contactType");
		String content = request.getParameter("content");

		out.println("以下の内容でお問い合わせしました。回答をお待ち下さい。");
		out.println("名前:" + name + "様");
		out.println("性別:" + sex);
		out.println("お問い合わせ種類:" + contactType);
		out.println("お問い合わせ内容:" + content);
	}

}