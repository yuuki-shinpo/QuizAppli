package com.example.sample.quizappli;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {


    String tableName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        tableName = intent.getStringExtra("Question");


        //出題数
        int Shutudaisuu=10;

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
                startActivity(intent);

                break;

            case R.id.button_top:
                finish();
            break;
        }

    }
}

