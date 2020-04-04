package practice;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Task4")
public class Task4 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		final String fortuneResult = "/WEB-INF/jsp/fotrune_result.jsp";
		String[] fortune = new String[]{"大吉","中吉","小吉","末吉","凶","大凶"};
		Random rand = new Random();
		int r = rand.nextInt(6);
		Date now = new Date();

		FortuneBean fortuneBean = new FortuneBean();
		fortuneBean.setToday(now);
		fortuneBean.setFortune(fortune[r]);
		request.setAttribute("resultToday", fortuneBean);

		RequestDispatcher dispatcher = request.getRequestDispatcher(fortuneResult);
		dispatcher.forward(request, response);
	}
}
