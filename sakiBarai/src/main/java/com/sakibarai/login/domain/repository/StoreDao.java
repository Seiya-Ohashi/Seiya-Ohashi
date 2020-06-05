package com.sakibarai.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.sakibarai.login.domain.model.Store;

// リポジトリークラスのインターフェースを作成

public interface StoreDao {

	// store_infoテーブルのデータを1件取得
	public Store selectOneStore(int storeId) throws DataAccessException;

	// store_infoテーブルの全データを取得
	public List<Store> selectManyStore() throws DataAccessException;

}