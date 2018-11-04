package com.example.sample.quizappli;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class DatabaseHelper extends SQLiteOpenHelper {

    //データベースファイル作成
    public DatabaseHelper(Context context) {
//データベースファイル名 = MyTable.db
//バージョン = 1
        super(context,"MyQuestionTable2.db",null,1);
    }

    //最初の処理
    public void onCreate(SQLiteDatabase db) {
//基本情報技術者試験のテーブル作成
        db.execSQL("CREATE TABLE TABLE_FE " +
                "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", QUESTION TEXT" +   //問題文
                ", ANSWER   TEXT" +   //正解
                ", CHOICE1  TEXT" +   //選択肢①
                ", CHOICE2  TEXT" +   //選択肢②
                ", CHOICE3  TEXT" +   //選択肢③
                ", CHOICE4  TEXT" +   //選択肢④
                ", AFLG  INTEGER" +   //正解・不正解フラグ
                ", DFLG  TEXT" +   //解答済みフラグ
                ", KAISETU  TEXT" +   //解説
                ")");

//初期データ投入
        db.execSQL("INSERT INTO TABLE_FE(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('データを一時的に保存することで、処理速度を速める仕組みをなんという？','キャッシュ','クッキー','スループット','キャッシュ','プロセス',0,0,'Aのクッキーは、Web上の閲覧履歴をPCが記憶してくれる機能のことです。\r\nBのスループットは、単位時間当たりの処理能力のことです。\r\nDのプロセスは、実行中のプログラムのことです。');");
        db.execSQL("INSERT INTO TABLE_FE(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('ファイルがあちこちに分かれて断片化してしまう状態のことをなんという？','フラグメンテーション','デフラグ','フラグメンテーション','プラッタ','ストライピング',0,0,'Aのデフラグは、フラグメンテーションを解消するために行う作業のことです。\r\nCのプラッタは、ハードディスクドライブに内蔵されている円盤状の部品のことです。\r\nDのストライピングは、１つのデータを2台以上のディスクに分散させて書き込む手法のことです。');");
        db.execSQL("INSERT INTO TABLE_FE(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('次のうち、OSでないものはどれ？','iPhone','UNIX','Windows','Android','iPhone',0,0,'OSとは、コンピュータの入出力や同時並行処理などを管理するプログラムのことです。\r\nまた、iPhoneのOSはiOSです。');");
        db.execSQL("INSERT INTO TABLE_FE(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('データベースにおいて、一連の処理をひとまとめにしたものをなんという？','トランザクション','トランザクション','PID制御','ブロック','パイプライン処理',0,0,'BのPID制御は、自動制御方式の一つで、操作対象が一つで目標値と操作量に比例関係がほぼ成り立つ場合に摘要できる古典制御法のことです。\r\nCのブロックは、データのまとまりのことです。\r\nDのパイプライン処理は、複数の命令を並行して実行する手法のことです。');");
        db.execSQL("INSERT INTO TABLE_FE(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('LANの規格として、現在最も普及しているものはどれ？','イーサネット','トークンリング','イーサネット','クライアントサーバーシステム','Wi-Fi',0,0,'Aのトークリングは、リング状の接続形態を用いるLANの規格の一つです。\r\nCのクライアントサーバーシステムは、コンピュータをサーバーとクライアントに分け、役割分担をして運用する仕組みのことです。\r\nDのWi-Fiは、無線（ワイヤレス）でLANに接続する技術のことです。');");
        db.execSQL("INSERT INTO TABLE_FE(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('ネットワークを通じて、コンピュータ同士がやり取りするための約束事をなんという？','プロトコル','パケット','アライアンス','プロトコル','ルータ',0,0,'Aのパケットは、ネットワークを流れるひとかたまりのデータのことです。\r\nBのアライアンスは、企業同士が連携して共同で事業を行うことです。\r\nDのルータは、データを2つ以上の異なるネットワーク間に中継する通信機器のことです。');");
        db.execSQL("INSERT INTO TABLE_FE(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('指紋や声紋、虹彩などの身体的特徴を使って個人を識別する認証方法をなんという？','バイオメトリクス認証','バイオメトリクス認証','ワンタイムパスワード','コールバック','フィジカル認証',0,0,'Bのワンタイムパスワードは、使い捨てのパスワードを用いる認証方法のことです。\r\nCのコールバックは、着信側から再送信させることで、アクセス権を確認する認証方法のことです\r\nDのフィジカル認証は、存在しない用語です。');");
        db.execSQL("INSERT INTO TABLE_FE(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('人の心理的不注意をついて、情報資産を盗み出す行為をなんという？','ソーシャルエンジニアリング','ショルダーハック','クラッキング','ソーシャルエンジニアリング','スキャべンジング',0,0,'Aのショルダーハックは、他人がパスワードや暗証番号を入力しているところを肩越しに盗み見ることです。\r\nBのクラッキングは、システムへ不正に侵入したり、コンピュータシステムを破壊・改竄するなど、コンピュータを不正に利用することです。\r\nDのスキャビンジングは、ゴミ箱を漁って有用な情報を盗み出すことです。');");
        db.execSQL("INSERT INTO TABLE_FE(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('コンピュータの中で扱うデータの最小単位はどれ？','ビット','バイト','ビット','トークン','ウイルス',0,0,'Aのバイトは、8ビットのことです。\r\nCのトークンは、プログラミングでソースコードを解析する際に、それ以上細かい単位に分解できない最小単位のことです。\r\nDのウイルスは、コンピュータに対して破壊活動を" +
                "行ったり、トラブルを引き起こしたりするプログラムのことです。');");
        db.execSQL("INSERT INTO TABLE_FE(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('最後に格納したデータから順に処理を行う、後入れ先出し方式のデータ構造をなんという？','スタック','キュー','ツリー構造','リスト','スタック',0,0,'Aのキューは、最初に格納したデータから順に処理を行う、先入れ先出し方式のデータ構造です。\r\nBのツリー構造は、1つの親に対して複数の子を持つ、枝分かれして広がっていく構造のことです。\r\nCのリストは、データの格納場所を示すポインタによって複数のデータが連結されたものです。');");

//JAVAのテーブル作成
        db.execSQL("CREATE TABLE TABLE_JAVA " +
                "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", QUESTION TEXT" +   //問題文
                ", ANSWER   TEXT" +   //正解
                ", CHOICE1  TEXT" +   //選択肢①
                ", CHOICE2  TEXT" +   //選択肢②
                ", CHOICE3  TEXT" +   //選択肢③
                ", CHOICE4  TEXT" +   //選択肢④
                ", AFLG  INTEGER" +   //正解・不正解フラグ
                ", DFLG  TEXT" +   //解答済みフラグ
                ", KAISETU  TEXT" +   //解説
                ")");

//初期データ投入
        db.execSQL("INSERT INTO TABLE_JAVA(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('物(オブジェクト)を構成要素として、それらの相互作用により機能を設計する設計手法をなんという？','オブジェクト指向設計','プロセス中心設計','データ中心設計','構造化設計','オブジェクト指向設計',0,0,'Aのプロセス中心設計は、業務システムの設計手法の一つで、主に業務の過程や手順に着眼して設計を行うことです。\r\nBのデータ中心設計は、業務で扱われるデータの構造や流れを先に明確化し、それに従ってシステム機能を導き出す方法のことです。\r\nCの構造化設計は、プログラムをいくつかの単位に分け、あるプログラムから別のプログラムを呼び出す構造とすることにより、プログラムを理解しやすい構造にすることです。');");
        db.execSQL("INSERT INTO TABLE_JAVA(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('計算結果の値などを格納する入れ物をなんという？','変数','ブラックボックス','変数','インデックス','ホワイトボックス',0,0,'Aのブラックボックスは、内部構造や動作原理が不明な装置やソフトウェア・システムなどのことです。\r\nCのインデックスは、配列中の位置を表す数字のことです。\r\nDのホワイトボックスは、内部構造や動作原理・仕様などが公開されたり明らかになっている装置やソフトウェア・システムなどのことです。');");
        db.execSQL("INSERT INTO TABLE_JAVA(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('属性や操作などが定義されている設計書のようなものをなんという？','クラス','クラス','ドキュメント','インスタンス','オブジェクト',0,0,'Bのドキュメントは、ソフトウェア仕様書・開発の過程を記録した文書・使用方法の説明書などの総称のことです。\r\nCのインスタンスは、クラスから生成される実体のことです。\r\nDのオブジェクトは、Cのインスタンスと同義です。');");
        db.execSQL("INSERT INTO TABLE_JAVA(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('クラスを元に実体を作ることをなんという？','インスタンス化','パッケージ化',' カプセル化',' 実装','インスタンス化',0,0,'Aのパッケージ化は、クラスを一つのパッケージにまとめることです。\r\nBのカプセル化は、オブジェクト内部のデータ・オブジェクトの振る舞い・オブジェクトの実際の型を隠蔽することです。\r\nCの実装は、設計書を元にプログラミングをすることです。');");
        db.execSQL("INSERT INTO TABLE_JAVA(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('既に定義してあるクラスを拡張して、新しいクラスを定義することをなんという？','継承','継承','ポリモーフィズム','多態性','オーバーライド',0,0,'Bのポリモーフィズムは、引数を受け取ったインスタンスがクラスによって違う振る舞いをすることです。\r\nCの多態性は、Bのポリモーフィズムと同義です。\r\nDのオーバーライドは、スーパークラスで定義されたメソッドをサブクラスで再定義することです。');");
        db.execSQL("INSERT INTO TABLE_JAVA(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('クラスをインスタンス化した際、最初に呼び出される処理をなんという？','コンストラクタ','メソッド','コンストラクタ','イニシャライザ','ファンクション',0,0,'Aのメソッドは、オブジェクト指向における「操作」を定義したもののことです。\r\nCのイニシャライザは、クラスをインスタンス化する前に呼び出される処理のことです。\r\nDのファンクションは、様々な処理を1つにまとめて、名前をつけることができるものです。');");
        db.execSQL("INSERT INTO TABLE_JAVA(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('次のキーワードのうち、何も参照していないことを表すものはどれ？','null','NaN','Infinity','null','どれも当てはまらない',0,0,'AのNaNは、実数でない値を表すキーワードです。\r\nBのInfinityは、正の無限大を表すキーワードです。');");
        db.execSQL("INSERT INTO TABLE_JAVA(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('次のキーワードのうち、繰り返し文で使用されないものはどれ？','switch','for','switch','foreach','while',0,0,'Bのswitchは、条件分岐で使用されるキーワードです。');");
        db.execSQL("INSERT INTO TABLE_JAVA(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('次のアクセス修飾子のうち、制限が最も厳しいものはどれ？','private','public','指定なし','protected','private',0,0,'アクセス修飾子は、クラスやそのメンバーがどこからアクセス可能であるかを定義したものです。\r\n制限の厳しさは private > 指定なし > protected > public となっています。');");
        db.execSQL("INSERT INTO TABLE_JAVA(QUESTION, ANSWER, CHOICE1, CHOICE2, CHOICE3, CHOICE4, AFLG, DFLG, KAISETU) values ('不要になったメモリ領域を自動的に解放する機能をなんという？','ガベージコレクション','ガベージコレクション','オーバーロード','アサーション','フラッシュメモリ',0,0,'Bのオーバーロードは、同じクラスの中でメソッド名と戻り値の型が同じで、引数の型や数・並び順が違うメソッドを２つ以上定義することです。\r\nCのアサーションは、プログラムの仕様をソースコードに明記することです。\r\nDのフラッシュメモリは、電気的に内容を消去したり、書き換えられる不揮発性のメモリーのことです。');");
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
