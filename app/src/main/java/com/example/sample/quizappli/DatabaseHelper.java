package com.example.sample.quizappli;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context){
    }

    public void onCreate(SQLiteDatabase db){

    }



    /*
     * onUpgradeメソッド
     * onUpgrade()メソッドはデータベースをバージョンアップした時に呼ばれる
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //親クラスの抽象メソッドの実装
        // とりあえず今回は空でOK
    }
}
