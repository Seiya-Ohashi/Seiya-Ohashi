
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));

		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/task1";
		String user = "ai";
		String password = "01000400";
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			String sql = "DELETE FROM employee WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			int num = ps.executeUpdate();
			ps.close();

		}catch(SQLException e){
			System.out.println("DB接続やSQL処理失敗時の処理");
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			System.out.println("JDBCドライバが見つからない時の処理");
			e.printStackTrace();
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					System.out.println("DB切断失敗時の処理");
					e.printStackTrace();
				}
			}
		}
    }
}

