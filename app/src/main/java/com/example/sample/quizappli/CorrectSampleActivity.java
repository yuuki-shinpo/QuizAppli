package com.example.sample.quizappli;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CorrectSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_sample);
    }

    // Intent取得
    Intent intent = getIntent();
    String tableName= intent.getStringExtra("Question");
    //解説順のid配列を取得
    int[] id_order = intent.getIntArrayExtra("LIST");

    //DB取得
    DatabaseHelper helper = new DatabaseHelper(CorrectSampleActivity.this);
    SQLiteDatabase db = helper.getReadableDatabase();

    //解説の配列を初期化
    String[] textlist = new String[10];

    //count初期化
    int count = 0;

    //解説文の初期化
    String text=null;

    //解説を取得
    try{
        for(int id : id_order) {
            String sql = "SELECT TEXT FROM " + tableName + " WHERE _id = " + id;

            //SQL実行
            Cursor cursor = db.rawQuery(sql, null);

            //データ取得
            while (cursor.moveToNext()) {
                int indexColumn = cursor.getColumnIndex("TEXT");
                text = cursor.getString(indexColumn);
            }

            textlist[count]="問題"+count+" :\r\n" + text ;

            TextView textView = findViewById(R.id.text+count);
            textView.setText(text);

            count++;
        }
    }



}
