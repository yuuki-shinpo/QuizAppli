package com.example.sample.quizappli;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 問題表示画面
 */
public class QuestionActivity extends AppCompatActivity {

    //全問題数 Randomで使用
    int allQuestion = 10;
    //出題数
    int Shutudaisuu = 10;
    //問題の解答回数
    int Kaitousuu = 1;
    //ユーザがどの問題を選んだかをテーブル名で判断
    String SelectQuestionTable;
    //問題をすでに解答したかどうかを判別
    int[] list = new int[11];
    //問題を正解した順番を格納
    int[] listAFLG = new int[11];
    //解答した順番を格納し、次のactivityに渡す
    int[] answerOrder = new int[11];

    String QuestionNo;
    int randomQuestionNo;
    String ANSWER;

    //効果音再生
    private SoundPool mSoundPool;
    private int[] mSoundId = new int[2];//使う効果音の数だけ配列作成

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //タイトルバーを非表示にする。setContentViewの前でないとエラー起きる場合あり
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //レイアウトをセットする
        setContentView(R.layout.activity_question);

        //ステージセレクトActivityから送られてきたデータを取得 受け取るデータは選択した問題のテーブル名　これをもとに、どの問題を出題するかを決める
        Intent intent = getIntent();
        SelectQuestionTable = intent.getStringExtra("Question");
        QuestionNo = Integer.toString(getRandomQuestionNo());
        //問題文セット処理呼び出し
        setQuestion();
    }


    @Override
    protected void onResume() {
        super.onResume();

        //効果音を使えるように読み込む
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        mSoundId[0] = mSoundPool.load(getApplicationContext(), R.raw.se_seikai, 1);
        mSoundId[1] = mSoundPool.load(getApplicationContext(), R.raw.se_huseikai, 1);

        //問題文セット処理呼び出し
        //setQuestion();
    }


    /**
     * 問題文セット処理
     */
    protected void setQuestion() {

        //作成したDatabaseHelperクラスに読み取り専用でアクセス
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //SELECT文　テーブル名から　_idとQuestionNoがマッチする項目を取得する条件式
        String sql = "SELECT QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4 , AFLG, DFLG FROM " + SelectQuestionTable + " WHERE _id=" + QuestionNo;

        //SQL文を実行してカーソルを取得
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        //データベースからとってきたデータを変数にセット
        String QUESTION = c.getString(c.getColumnIndex("QUESTION"));//問題文
        String CHOICE1 = c.getString(c.getColumnIndex("CHOICE1"));//選択肢１
        String CHOICE2 = c.getString(c.getColumnIndex("CHOICE2"));
        String CHOICE3 = c.getString(c.getColumnIndex("CHOICE3"));
        String CHOICE4 = c.getString(c.getColumnIndex("CHOICE4"));

        ANSWER = c.getString(c.getColumnIndex("ANSWER"));//答えをSeikaiに格納

        //データベースクローズ
        c.close();
        db.close();

        //画面↑にあるテキストを「クイズNo,+問題Noで表示」
        ((TextView) findViewById(R.id.textNo)).setText("クイズNo." + Kaitousuu);
        ((TextView) findViewById(R.id.textQuestion)).setText(QUESTION);//問題文をテキストビューにセット
        ((TextView) findViewById(R.id.A)).setText(CHOICE1);//選択肢1をボタンビューにセット
        ((TextView) findViewById(R.id.B)).setText(CHOICE2);//選択肢2をボタンビューにセット
        ((TextView) findViewById(R.id.C)).setText(CHOICE3);//選択肢3をボタンビューにセット
        ((TextView) findViewById(R.id.D)).setText(CHOICE4);//選択肢4をボタンビューにセット

        answerOrder[Kaitousuu]=Integer.parseInt(QuestionNo);
        QuestionNo = Integer.toString(nextQuestionNo());//次の問題の問題番号取得
    }

    public int nextQuestionNo() {
        //解答数を加算
        Kaitousuu++;
        //問題文情報を、PRYMARY　KEYであるQuestionNoで取得しているため、加算して次の問題へ
        return getRandomQuestionNo();
    }

    /**
     * @return 問題をランダム表示するために、乱数生成とその乱数に対応する問題がすでに使用されたかを確認し、
     * 重複がない場合はその生成した乱数を返す
     */
    public int getRandomQuestionNo() {

        Random rand = new Random();
        int bound = allQuestion + 1;
        do {
            do {
                //問題をランダムで選定するため、乱数を生成
                randomQuestionNo = rand.nextInt(bound);
            } while (randomQuestionNo == 0);
        } while (list[randomQuestionNo] == 1);//DFLG=重複フラグが1の場合、違う問題を選ぶ


        return randomQuestionNo;
    }

    //選択肢がクリックされた時の処理
    public void onClick(View v) {
        //解答済みフラグを1にする
        list[Integer.parseInt(QuestionNo)] = 1;
        //作成したDatabaseHelperクラスに読み取り専用でアクセス
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //押されたボタンのテキストと正解を比較
        if (((Button) v).getText().equals(ANSWER)) {
            //正解の処理 正解音再生
            mSoundPool.play(mSoundId[0], 1.0f, 1.0f, 0, 0, 1.0f);

            //正解リストのフラグを1にする
            listAFLG[Integer.parseInt(QuestionNo)] = 1;
        } else {
            //不正解の処理
            mSoundPool.play(mSoundId[1], 1.0f, 1.0f, 0, 0, 1.0f);
        }


        //正解・不正解にかかわらず、次の問題へ
        //次の問題をDBから呼び出し、セットする
        if (Kaitousuu <= Shutudaisuu) {//出題数が既定の数へ達していない場合
            setQuestion();
        } else {
            //DFLG、AFLGをそれぞれのlistに従い、書き換える
            setlist();
            setListAFLG();

            //既定の回数出し終わったとき 結果画面へ遷移
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            //アクティビティ間で値を受け渡す処理
            intent.putExtra("Question", SelectQuestionTable);
            intent.putExtra("answerOrder", answerOrder);
            startActivity(intent);
        }
    }

    /**
     * 正解した問題のAFLGを1に書き換える
     */
    public void setListAFLG() {
        //作成したDatabaseHelperクラスに読み取り+書き取りでアクセス　
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (int i = 1; i <= Shutudaisuu; i++) {
            int aflg = listAFLG[i];
            if (aflg == 1) {//1=正解した問題　のため、FLG書き換え

                //SELECT文　テーブル名から　_idと生成した乱数がマッチする項目を取得する
                String sql = "SELECT AFLG FROM " + SelectQuestionTable + " WHERE _id=" + i;
                //    データベースを更新処理
                ContentValues values = new ContentValues();
                //AFLG 0→１　に書き換え、正解の状態にする
                values.put("AFLG", 1);
                //カラム選択
                String whereClause = "_id = ?";

                //データベース更新
                int ret;
                try {
                    ret = db.update(/*table名*/SelectQuestionTable, values, whereClause, new String[]{String.valueOf(i)});
                } finally {
                }

            }
        }
        db.close();
    }


    /**
     * 解答済みの問題のDFLGを1に書き換える
     */
    public void setlist() {
        //作成したDatabaseHelperクラスに読み取り+書き取りでアクセス　
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (int i = 1; i <= Shutudaisuu; i++) {
            int aflg = listAFLG[i];
            if (aflg == 1) {//1=正解した問題　のため、FLG書き換え

                //SELECT文　テーブル名から　_idと生成した乱数がマッチする項目を取得する
                String sql = "SELECT DFLG FROM " + SelectQuestionTable + " WHERE _id=" + i;
                //    データベースを更新処理
                ContentValues values = new ContentValues();
                //AFLG 0→１　に書き換え、正解の状態にする
                values.put("DFLG", 1);
                //カラム選択
                String whereClause = "_id = ?";

                //データベース更新
                int ret;
                try {
                    ret = db.update(/*table名*/SelectQuestionTable, values, whereClause, new String[]{String.valueOf(i)});
//                    ret = db.update(/*table名*/SelectQuestionTable, values, whereClause, new String[]{String.valueOf((Integer.parseInt(QuestionNo)))});
                } finally {
                }

            }
        }
        db.close();
    }


    /**
     * 効果音に関するものはすべて解放する
     */
    @Override
    protected void onPause() {
        super.onPause();

        //SoundPool解放
        mSoundPool.unload(mSoundId[0]);
        mSoundPool.unload(mSoundId[1]);

        mSoundPool.release();

    }
}
