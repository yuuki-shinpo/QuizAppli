package com.example.sample.quizappli;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

/**
 * 問題選択画面
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //タイトルバーを非表示にする
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }




    public void feQuestionClick(View view){
        // テーブル名を持たせる
        String tableName = "TABLE_FE";
        //intent作成
        Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
        intent.putExtra("Question",tableName);
        //遷移
        startActivity(intent);
    }

    public void javaQuestionClick(View view){
        // テーブル名を持たせる
        String tableName = "TABLE_JAVA";
        //intent作成
        Intent intent = new Intent(MainActivity.this,QuestionActivity.class);
        intent.putExtra("Question",tableName);
        //遷移
        startActivity(intent);
    }
}
