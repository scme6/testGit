package com.houyikj.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "jiaKao.db";

    public static final int DB_VERSION = 1;

    public static final String TABLE_STUDENT = "total";

    //创建 students 表的 sql 语句
    private static final String STUDENTS_CREATE_TABLE_SQL = "create table " + TABLE_STUDENT + "("
            + "id integer primary key autoincrement,"
            + "name varchar(20) not null,"
            + "tel_no varchar(11) not null,"
            + "cls_id integer not null"
            + ");";

    private static final String SQL = "CREATE TABLE web_note2"+"("+"_ID integer" +
            ",Type integer" +
            ",intNumber varchar(255)" +
            ",strTppe varchar(255)" +
            ",strType_l varchar(255)" +
            ",LicenseType varchar(50)" +
            ",Question varchar(255)" +
            ",An1 varchar(255)" +
            ",An2 varchar(255)" +
            ",An3 varchar(255)" +
            ",An4 varchar(255)" +
            ",An5 varchar(255)" +
            ",An6 varchar(255)" +
            ",An7 varchar(255)" +
            ",AnswerTrue varchar(255)" +
            ",_explain varchar(255)" +
            ",BestAnswerId varchar(255)" +
            ",kemu integer" +
            ",jieshi_from varchar(255)" +
            ",moretypes varchar(255)" +
            ",chapterid integer" +
            ",sinaimg varchar(100)" +
            ",video_url varchar(255)" +
            ",diff_degree integer" +
            ",cityid integer" +
            ",gs varchar(255)" +
            ",keyword varchar(255));";


    private static final String SQL1 = "CREATE TABLE totaldata(_id INTEGER PRIMARY KEY AUTOINCREMENT,type INTEGER ,yesOrNo INTEGER ,userSelect VARCHAR(200) ,title VARCHAR(200) NOT NULL,subA VARCHAR(200) NOT NULL,subB VARCHAR(200) NOT NULL,subC VARCHAR(200),subD VARCHAR(200),pic VARCHAR(200),plain VARCHAR(200) NOT NULL,answer VARCHAR(200) NOT NULL,chapter VARCHAR(200) NOT NULL,shouCang INTEGER,kemu INTEGER,answerType INTEGER);";


    public SQLiteDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(STUDENTS_CREATE_TABLE_SQL);
//        db.execSQL(SQL);
        db.execSQL(SQL1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                // do something
                break;
            default:
                break;
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // 启动外键
            db.execSQL("PRAGMA foreign_keys = 1;");

//            //或者这样写
//            String query = String.format("PRAGMA foreign_keys = %s", "ON");
//            db.execSQL(query);
        }
    }
}