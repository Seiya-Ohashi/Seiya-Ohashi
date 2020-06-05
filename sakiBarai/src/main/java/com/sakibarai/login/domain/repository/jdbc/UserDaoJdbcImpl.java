package com.sakibarai.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sakibarai.login.domain.model.User;
import com.sakibarai.login.domain.repository.UserDao;

//インターフェースを実装したクラス
//JdbcTemplateはSpringが用意しているため、
//既にBean定義がされている
//そのため、@Autowiredするだけで使えるようになる
//このクラスのメソッドを使って、SQLを実行していく

@Repository
public class UserDaoJdbcImpl implements UserDao{
	@Autowired
	JdbcTemplate jdbc;
	@Autowired
	PasswordEncoder passwordEncoder;

	// ･Objectの取得
	// カウントの結果や、カラムを１つだけ取得してくるような場合には、
	// queryForObjectメソッドを使う
	// 第1引数にSQL文、第2引数に戻り値のオブジェクトのclassを指定
	//---------------------------------------------------------------
	//		Map<String,Object>map = jdbc.queryForMap("SELECT `user_name` FROM `user_info` "
	//+ "WHERE `user_name` = ?", userName);

	@Override
	public boolean selectDuplicationName(String userName)throws DataAccessException{
		//1件取得
		int countName = jdbc.queryForObject("SELECT COUNT(*) FROM `user_info` "
				+ "WHERE `user_name` = ?", Integer.class, userName);
		boolean nameExists;
		if(countName == 1){
			nameExists = true;
		}else{
			nameExists = false;
		}
		return nameExists;
	}

	@Override
	public boolean selectDuplicationMail(String mailAddress)throws DataAccessException{
		//1件取得
		int countMail = jdbc.queryForObject("SELECT COUNT(*) FROM `user_info` "
				+ "WHERE `mail_address` = ?", Integer.class, mailAddress);
		boolean mailExists;
		if(countMail == 1){
			mailExists = true;
		}else{
			mailExists = false;
		}
		return mailExists;
	}

	//Userテーブルにデータを1件insertするメソッド(ユーザー新規登録に使う)
	@Override
	public int insertOne(User user)throws DataAccessException{
		//パスワード暗号化
		String password = passwordEncoder.encode(user.getPassword());
		//ユーザーテーブルに1件登録するSQL
		String sql = "INSERT INTO "
				+"user_info(user_name,"
				+"mail_address,"
				+"password)"
				+"VALUES(?,?,?)";
		//1件挿入
		int rowNumber = jdbc.update(sql
				,user.getUserName()
				,user.getMailAddress()
				,password);
				return rowNumber;
	}

	//Userテーブルのデータを1件取得だけ取得するメソッド(ログイン後ホーム遷移時のみ使用)
	@Override
	public User selectOne(String mailAddress)throws DataAccessException{
		//1件取得
		Map<String,Object>map = jdbc.queryForMap("SELECT * FROM `user_info` "
				+ "WHERE `mail_address` = ?", mailAddress);
		//結果返却用の変数
		User user = new User();
		//取得したデータを結果返却用の変数にセットしていく
		user.setUserId((Integer)map.get("user_id"));// ユーザーID
		user.setUserName((String)map.get("user_name"));// ユーザー名
		user.setMailAddress((String)map.get("mail_address"));// メールアドレス
		user.setPassword((String)map.get("password"));// パスワード

		return user;
	}
	//Userテーブルのデータを1件取得だけ取得するメソッド
	@Override
	public User selectOneId(int userId)throws DataAccessException{
		//1件取得
		Map<String,Object>map = jdbc.queryForMap("SELECT * FROM `user_info` "
				+ "WHERE `user_id` = ?", userId);
		//結果返却用の変数
		User user = new User();
		//取得したデータを結果返却用の変数にセットしていく
		user.setUserId((Integer)map.get("user_id"));// ユーザーID
		user.setUserName((String)map.get("user_name"));// ユーザー名
		user.setMailAddress((String)map.get("mail_address"));// メールアドレス
		user.setPassword((String)map.get("password"));// パスワード

		return user;
	}
	// ･1件取得
	// 1件のレコードを取得するには、queryForMapメソッドを使う
	// 戻り値はMap<String,Object>型
	// 第1引数にSQL文、第2引数以降にPreparedStatementを指定
	// 戻り値のMapのgetメソッドにカラム名を指定することで、値を取得
	// 複数件取得する場合と、使い方はほとんど一緒
	//---------------------------------------------------------------

	//Userテーブルの全データを取得するメソッド
	@Override
	public List<User>selectMany()throws DataAccessException{
		//複数件のselect
		//user_infoテーブルのデータを全件取得
		List<Map<String,Object>>getList = jdbc.queryForList("SELECT * "
		+ "FROM user_info");
		//結果返却用の変数
		List<User>userList = new ArrayList<>();
		//取得したデータを結果返却用のListに格納していく
		for(Map<String,Object>map:getList){
		//Userインスタンスの生成
		User user = new User();
		//Userインスタンスに取得したデータをセットする
		//ユーザーID
		user.setUserId((Integer)map.get("user_id"));
		//ユーザー名
		user.setUserName((String)map.get("user_name"));
		//メールアドレス
		user.setMailAddress((String)map.get("mail_address"));
		//パスワード
		//user.setPassword((String)map.get("password"));
		//アカウント権限
		user.setRole((String)map.get("role"));
		//結果返却用のListに追加
		userList.add(user);
	}
	return userList;
}
	// ･複数件のselect
	// 複数件のselectをする場合は、queryForListメソッドを使う
	// 戻り値の型にはList<Map<String,Object>>を指定する
	// Listが行を表していて、Mapが列を表している
	// Mapのgetメソッドでテーブルのカラム名を指定することで値を取得できる

	// 引数を追加すれば、PreparedStatementを使うこともできる
	// 上記の場合は、拡張for文を使って、
	// List<Map<String,Object>>をList<User>に変換している
	//---------------------------------------------------------------

	//Userテーブルを1件更新するメソッド(プロフィール編集に使う)
	@Override
	public int updateOne(User user) throws DataAccessException {
		if(user.getPassword() == "") {
			//1件更新するSQL(パスワードは更新しない場合)
			String sql = "UPDATE user_info "
					+"SET "
					+"user_name = ?, "
					+"mail_address = ? "
					+"WHERE user_id = ?";
			//1件更新
			int rowNumber = jdbc.update(sql
			,user.getUserName()
			,user.getMailAddress()
			,user.getUserId());
			System.out.println("パスワード以外を更新");
			return rowNumber;
		} else {
			//パスワード暗号化
			String password = passwordEncoder.encode(user.getPassword());
			//1件更新するSQL(パスワードも更新する場合)
			String sql = "UPDATE user_info "
					+"SET "
					+"user_name = ?, "
					+"mail_address = ?, "
					+"password = ? "
					+"WHERE user_id = ?";
			//1件更新
			int rowNumber = jdbc.update(sql
			,user.getUserName()
			,user.getMailAddress()
			,password
			,user.getUserId());
			System.out.println("パスワードも更新");
			return rowNumber;
		}
	}
    // Userテーブルを1件削除
    @Override
    public int deleteOne(int userId) throws DataAccessException {

        //1件削除
        int rowNumber = jdbc.update("DELETE FROM user_info WHERE user_id = ?", userId);

        return rowNumber;
    }
}

// JdbcTemplateクラスを使って登録（insert）するには、updateメソッドを使う
// updateメソッドは更新、削除にも使う
// 使い方は、第1引数にSQL文を入れる
// 第2引数以降には、PreparedStatementを使う
// PreparedStatementには、SQL文の？の部分に入れる変数を引数にセットしていく
// 引数にセットした順番にSQL文に代入されていく
// updateメソッドの戻り値には、登録したレコード数が返ってくる
