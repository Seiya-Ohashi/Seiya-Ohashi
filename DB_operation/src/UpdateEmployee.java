
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


@WebServlet("/UpdateEmployee")
public class UpdateEmployee extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");

		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/task1";
		String user = "ai";
		String password = "01000400";
		try{
			  // ドライバの読み込み(MySQL)
			Class.forName("com.mysql.cj.jdbc.Driver");
			  // DB接続
			con = DriverManager.getConnection(url, user, password);
			  // SQL文の実行(指定したIDのnameを別のnameに変更)
			String sql = "UPDATE employee SET name = ? WHERE id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, id);

			ps.executeUpdate();
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

