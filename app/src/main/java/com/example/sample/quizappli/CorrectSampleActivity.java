package com.example.sample.quizappli;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CorrectSampleActivity extends AppCompatActivity {

    //テーブル名
    String tableName;
    //問題順のid
    int[] idOrder;
    //count初期化
    int count = 0;
    //1~10に増える
    int idnumber = 1;
    //出題数
    int Shutudaisuu = 10;

    //解説文の初期化
    String[] text = new String[11];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_sample);


// Intent取得
        Intent intent = getIntent();
        tableName = intent.getStringExtra("Question");
//解説順のid配列を取得
        idOrder = intent.getIntArrayExtra("answerOrder");

//DB取得
        DatabaseHelper helper = new DatabaseHelper(CorrectSampleActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase();


//解説を取得
        for (int id = 1; id < 11; id++) {

            String sql = "SELECT KAISETU FROM " + tableName + " WHERE _id = " + idOrder[id];

//SQL実行
            Cursor cursor = db.rawQuery(sql, null);

//データ取得
            while (cursor.moveToNext()) {
                int indexColumn = cursor.getColumnIndex("KAISETU");
                StringBuilder sb = new StringBuilder();
                sb.append("問題");
                count++;
                sb.append(count);
                sb.append(": ");
                sb.append(cursor.getString(indexColumn));
//解説文を解答順に配列に格納
                text[count] = new String(sb);
            }
        }
        onClicknextkaisetu();
    }

    public void onClick(View view) {
        int id = view.getId();
        Intent intent = null;
//押下されたボタンで画面遷移を分岐
        switch (id) {
            case R.id.button_nextkaisetu://最後の問題まで出し切ったらボタンを使用不可能にする
                onClicknextkaisetu();
                break;

            case R.id.button_result:
                intent = new Intent(CorrectSampleActivity.this, ResultActivity.class);
                intent.putExtra("Question", tableName);
                intent.putExtra("answerOrder", idOrder);
                startActivity(intent);
                finish();
                break;

            case R.id.button_top:
//AFLG,DFLGの初期化処理を行う
                setListAFLG();
                setlistDFLG();
                finish();
                break;
        }
    }

    /*
     * 次の解説文を表示
     */
    public void onClicknextkaisetu() {
        TextView textView = findViewById(R.id.text1);
//textView.setText("　呼ばれました　"+idnumber);

        textView.setText(text[idnumber]);
        Button kaisetu_btn = findViewById(R.id.button_nextkaisetu);
        if (idnumber >= Shutudaisuu) {
            kaisetu_btn.setEnabled(false);
        }
        idnumber++;
    }


    /**
     * すべてのAFLGを0に書き換える
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



