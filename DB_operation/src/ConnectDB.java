import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	public static void main(String[] args) {
		Connection con = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/task1",
				"ai", "01000400");
			System.out.println("DB接続が成功しました。");
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