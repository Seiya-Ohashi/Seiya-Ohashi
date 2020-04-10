package pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CountCookie")
public class CountCookie extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset = \"UTF-8!\">");
		out.println("<title>Task3</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>訪問回数テスト(クッキー)</h1>");

		Cookie [] cookies = request.getCookies();
		Cookie visitedCookie = null;

		if(cookies != null){
			for(int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("count")) {
					visitedCookie = cookies[i];
				}
			}
			//2回目以降の訪問ならvisitedクッキーから値を取得+加算→代入→変換して出力
			if(visitedCookie != null){
				int visitedCount= Integer.parseInt(visitedCookie.getValue()) + 1;
				out.println("<p>" + visitedCount + "回目の訪問です</p>");
				//クッキーの値を再設定
				visitedCookie.setValue(Integer.toString(visitedCount));
				visitedCookie.setMaxAge(300);
				response.addCookie(visitedCookie);
			}
		}else{
			out.println("<p>初めての訪問です</p>");
			Cookie newCookie = new Cookie("count","1");
			newCookie.setMaxAge(300);
			response.addCookie(newCookie);
		}

		out.println("<a href=/dataOperation/CountCookie>");
		out.println("画面を再訪問");
		out.println("</a>");
		out.println("</body>");
		out.println("</html>");
	}
}
