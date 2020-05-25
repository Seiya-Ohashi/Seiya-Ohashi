package com.sakibarai.login.domain.model;

import javax.validation.GroupSequence;

@GroupSequence({ ValidGroup1.class, ValidGroup2.class, ValidGroup3.class })
public interface GroupOrder {

}
//グループの実行順序バリデーションをグループ実行するためには、
//実行順序を設定するインターフェースに@GroupSequenceアノテーションを付ける
//アノテーションのパラメーターに、各グループの.classを指定する
//指定した順番に（左から順番に）バリデーションが実行されていく