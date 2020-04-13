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
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String contactType = request.getParameter("contactType");
		String content = request.getParameter("content");

		if(sex == "male"){
			sex = "男性";
		}else{
			sex = "女性";
		}

		if(contactType == "About product"){
			contactType = "製品について";
		}else if(contactType == "Bugs or complaints"){
			contactType = "不具合やクレーム";
		}else{
			contactType = "アフターサポート";
		}

		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Result</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>以下の内容でお問い合わせしました。回答をお待ち下さい。</p>");
		out.println("<p>名前:" + name + "様</p>");
		out.println("<p>性別:" + sex + "</p>");
		out.println("<p>お問い合わせ種類:" + contactType + "</p>");
		out.println("<p>お問い合わせ内容:" + content + "</p>");
		out.println("</body>");
		out.println("</html>");

	}

}