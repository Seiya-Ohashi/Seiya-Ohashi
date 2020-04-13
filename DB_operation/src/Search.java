import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Search")
public class Search extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		PrintWriter out = response.getWriter();

		String searchName = request.getParameter("name");

		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/task1";
		String user = "ai";
		String password = "01000400";
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM employee WHERE name LIKE ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%" + searchName + "%");

			ResultSet rs = ps.executeQuery();
			out.println("検索結果");
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Date birthday = rs.getDate("birthday");
				int age = rs.getInt("age");
				out.println("ID:" + id);
				out.println("名前:" + name);
				out.println("生年月日:" + birthday);
				out.println("年齢:" + age);
			}
			ps.close();
			rs.close();

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
