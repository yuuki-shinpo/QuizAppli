package com.example.sample.quizappli;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {


    //問題ステージ
    String tableName;
    //解説順のid配列を取得
    int[] idOrder ;
    //出題数
    int Shutudaisuu=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        //問題ステージ取得
        tableName = intent.getStringExtra("Question");
        //解説順のid配列を取得
        idOrder = intent.getIntArrayExtra("answerOrder");

        //正解数
        int correctNum=0;

        // DB取得
        DatabaseHelper helper = new DatabaseHelper(ResultActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase();

        //正解数をDBから取得する
        try {
            String sql = "SELECT COUNT(*) as correct_sum FROM " + tableName + " WHERE AFLG = 1";
            //String sql = "SELECT COUNT(*)  FROM　" + tableName + "  WHERE AFLG = 1";

            Cursor cursor = db.rawQuery(sql, null);

            while(cursor.moveToNext()) {

                int indexColumn = cursor.getColumnIndex("correct_sum");

                correctNum = cursor.getInt(indexColumn);
            }

            //正答数をtextViewにセット
            TextView tvCorrectNum = findViewById(R.id.TV_correctNum_id);
            String str_correct = "正解数 ： " + String.valueOf(correctNum);
            tvCorrectNum.setText(str_correct);

            //正答率計算しtextViewにセット
            TextView tvCorrectPer = findViewById(R.id.TV_correctPer_id);
            String str_correctPer ="正解率 : "+String.valueOf(correctNum*100/Shutudaisuu)+ "%";
                    tvCorrectPer.setText(str_correctPer);
        }finally {
            db.close();
        }
    }

    public void onClick(View view){
        int id = view.getId();
        Intent intent= null;
        //押下されたボタンで画面遷移を分岐
        switch (id){
            case R.id.button_correctSample:
                intent = new Intent(ResultActivity.this,CorrectSampleActivity.class);
                intent.putExtra("Question",tableName);
                intent.putExtra("answerOrder",idOrder);
                startActivity(intent);
                finish();
                break;

            case R.id.button_top:
                //フラグ初期化
                setListAFLG();
                setlistDFLG();
                finish();
            break;
        }

    }



    /**
     *すべてのAFLGを0に書き換える
     */
    public void setListAFLG() {
        //作成したDatabaseHelperクラスに読み取り+書き取りでアクセス　
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (int i = 1; i <= Shutudaisuu; i++) {
                //SELECT文　テーブル名から　_idと生成した乱数がマッチする項目を取得する
                String sql = "SELECT AFLG FROM " + tableName + " WHERE _id=" + i;
                //    データベースを更新処理
                ContentValues values = new ContentValues();
                //AFLG 0→１　に書き換え、正解の状態にする
                values.put("AFLG", 0);
                //カラム選択
                String whereClause = "_id = ?";

                //データベース更新
                int ret;
                try {
                    ret = db.update(tableName, values, whereClause, new String[]{String.valueOf(i)});
                } finally {
                }

        }
        db.close();
    }


    /**
     * すべてのAFLGを0に書き換える
     */
    public void setlistDFLG() {
        //作成したDatabaseHelperクラスに読み取り+書き取りでアクセス　
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (int i = 1; i <= Shutudaisuu; i++) {

                //SELECT文　テーブル名から　_idと生成した乱数がマッチする項目を取得する
                String sql = "SELECT DFLG FROM " + tableName + " WHERE _id=" + i;
                //    データベースを更新処理
                ContentValues values = new ContentValues();
                //AFLG 0→１　に書き換え、正解の状態にする
                values.put("DFLG", 0);
                //カラム選択
                String whereClause = "_id = ?";

                //データベース更新
                int ret;
                try {
                    ret = db.update(tableName, values, whereClause, new String[]{String.valueOf(i)});
                } finally {

            }
        }
        db.close();
    }


}

