package pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/countSession")
public class countSession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset = \"UTF-8!\">");
		out.println("<title>Task4</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>訪問回数テスト(セッション)</h1>");

		HttpSession session = request.getSession();
		Integer num =(Integer)session.getAttribute("count");
		if(num != null){
			out.println("<p>" + num + "回目の訪問です</p>");
			session.setAttribute("count", num + 1);
		}else{
			out.println("<p>初めての訪問です</p>");
			HttpSession newSession = request.getSession();
			newSession.setAttribute("count", 2);
		}
		out.println("<a href=/dataOperation/countSession>");
		out.println("画面を再訪問");
		out.println("</a>");
		out.println("</body>");
		out.println("</html>");
	}
}
