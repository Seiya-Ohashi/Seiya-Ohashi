package com.sakibarai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sakibarai.login.domain.model.Store;
import com.sakibarai.login.domain.repository.StoreDao;

@Service
public class StoreService {
	@Autowired
	StoreDao storedao;
	// store_infoテーブルのデータを1件取得
	public Store selectOneStore(int storeId){
		return storedao.selectOneStore(storeId);
	}
	// store_infoテーブルのデータを全件取得
	public List<Store>selectManyStore(){
		return storedao.selectManyStore();
	}

	//// store_infoテーブルのデータの件数を取得
	public int storeCount(){
		return storedao.storeCount();
	}

	// store_infoテーブルのデータの件数を取得
	public List<Store> selectManyStoreLimit(int dataNum, int limitNum){
		return storedao.selectManyStoreLimit(dataNum, limitNum);
	}
}
