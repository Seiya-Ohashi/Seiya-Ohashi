package com.sakibarai.login.domain.repository.jdbc;

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

	//Userテーブルにデータを1件insertするメソッド(ユーザー新規登録に使う)
	@Override
	public int insertOne(User user)throws DataAccessException{
		//パスワード暗号化
		String password=passwordEncoder.encode(user.getPassword());
		//ユーザーテーブルに1件登録するSQL
		String sql="INSERT INTO "
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

	//Userテーブルのデータを1件取得だけ取得するメソッド(①ホームのユーザー名表示、プロフィール画面に使う)
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
	// ･1件取得
	// 1件のレコードを取得するには、queryForMapメソッドを使う
	// 戻り値はMap<String,Object>型
	// 第1引数にSQL文、第2引数以降にPreparedStatementを指定
	// 戻り値のMapのgetメソッドにカラム名を指定することで、値を取得
	// 複数件取得する場合と、使い方はほとんど一緒
	// 次→ユーザーサービスクラスに1件取得用のメソッドを追加(②)
	//---------------------------------------------------------------

//a TODO Userテーブルの全データを取得するメソッド(※店一覧取得に流用)
	@Override
	public List<User>selectMany()throws DataAccessException{
		return null;
	}
//a	public List<User>selectMany()throws DataAccessException{
//a		//複数件のselect
//a		//info_userテーブルのデータを全件取得
//a		List<Map<String,Object>>getList = jdbc.queryForList("SELECT * "
//a		+ "FROM user_info");
//a		//結果返却用の変数
//a		List<User>userList = new ArrayList<>();
//a		//取得したデータを結果返却用のListに格納していく
//a		for(Map<String,Object>map:getList){
//a		//Userインスタンスの生成
//a		User user = new User();
//a		//Userインスタンスに取得したデータをセットする
//a		//ユーザーID
//a		user.setUserId((String)map.get("user_id"));
//a		//ユーザー名
//a		user.setUserName((String)map.get("user_name"));
//a		//メールアドレス
//a		user.setMailAddress((String)map.get("mail_address"));
//a		//パスワード
//a		user.setPassword((String)map.get("password"));
//a		//アカウントカテゴリー
//a		user.setUserCategoryFlag((Integer)map.get("user_category_flag"));
//a		//結果返却用のListに追加
//a		userList.add(user);
//a	}
//a	return userList;
//a }

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
		//パスワード暗号化
		String password = passwordEncoder.encode(user.getPassword());
		//1件更新するSQL
		String sql = "UPDATE user_info "
				+"SET "
				+"user_name=?, "
				+"mail_address=?, "
				+"password=? "
				+"WHERE user_id=?";
		//1件更新
		int rowNumber = jdbc.update(sql
		,user.getUserName()
		,user.getMailAddress()
		,password
		,user.getUserId());
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
//---------------------------------------------------------------
