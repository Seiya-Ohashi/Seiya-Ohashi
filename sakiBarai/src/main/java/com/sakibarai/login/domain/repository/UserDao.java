package com.sakibarai.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.sakibarai.login.domain.model.User;

// リポジトリークラスのインターフェースを作成
// 後で中身の実装クラスを
// JdbcTemplateとNamedParameterJdbcTemplateで
// 簡単に切り替えられるようにするため

public interface UserDao {
	// DataAccessException

	// Userテーブルにデータを1件insert
	public int insertOne(User user) throws DataAccessException;

	// Userテーブルのデータを1件取得(ログイン後ホーム遷移時のみ使用)
	public User selectOne(String mailAddress) throws DataAccessException;

	// Userテーブルのデータを1件取得
	public User selectOneId(int userId) throws DataAccessException;

	// Userテーブルの全データを取得
	public List<User> selectMany() throws DataAccessException;

	// Userテーブルを1件更新
	public int updateOne(User user) throws DataAccessException;
}
//Springでは、データベース操作で例外が発生した場合、
//Springが提供しているDataAccessExceptionを投げる
