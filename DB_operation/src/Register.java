import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Register")
public class Register extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		//文字列→util.Date型→SetDate用にsqlDate型へ変換
		String birthDay = request.getParameter("birthday");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilBirthDay = new java.util.Date();
		try {
			utilBirthDay = sdf.parse(birthDay);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		java.sql.Date sqlBirthDay = new java.sql.Date(utilBirthDay.getTime());

		int age = Integer.parseInt(request.getParameter("age"));

		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/task1";
		String user = "ai";
		String password = "01000400";
		try{
			  // ドライバの読み込み(MySQL)
			Class.forName("com.mysql.cj.jdbc.Driver");
			  // DB接続
			con = DriverManager.getConnection(url, user, password);
			  // SQL文の実行(挿入)
			String sql = "INSERT INTO employee (id, name, birthday, age) values (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			  // ｢？｣に設定
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setDate(3, sqlBirthDay);
			ps.setInt(4, age);

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

