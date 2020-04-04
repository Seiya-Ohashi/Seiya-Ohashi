package practice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Task2")
public class Task2 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/hello.jsp");
		dispatcher.forward(request, response);
	}
}
