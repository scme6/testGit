package com.houyikj.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5dbf629b");
        AssetsDBUtils.getPath(this);

        SQLiteDbHelper helper = new SQLiteDbHelper(getApplicationContext());
        SQLiteDatabase database = helper.getWritableDatabase();

//        Cursor cusor = database.rawQuery("SELECT * FROM web_note where strTppe in(1,2,3,4,5,6,7) and kemu in (1,4)", null);
//        database.beginTransaction();
//        while (cusor.moveToNext()) {
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("title",cusor.getString(cusor.getColumnIndex("Question")));
//            contentValues.put("subA",cusor.getString(cusor.getColumnIndex("An1")));
//            contentValues.put("subB",cusor.getString(cusor.getColumnIndex("An2")));
//            contentValues.put("subC",cusor.getString(cusor.getColumnIndex("An3")));
//            contentValues.put("subD",cusor.getString(cusor.getColumnIndex("An4")));
//            contentValues.put("pic",cusor.getString(cusor.getColumnIndex("sinaimg")));
//            contentValues.put("plain",cusor.getString(cusor.getColumnIndex("explain")));
//            contentValues.put("answer",cusor.getString(cusor.getColumnIndex("AnswerTrue")));
//            contentValues.put("chapter",cusor.getString(cusor.getColumnIndex("strTppe")));
//            contentValues.put("kemu",cusor.getInt(cusor.getColumnIndex("kemu")));
//         database.insert("totaldata",null,contentValues);
//        }
//        database.setTransactionSuccessful();
//        database.endTransaction();

        Cursor cusor2 = database.rawQuery("SELECT *  FROM totaldata", null);
        while (cusor2.moveToNext()){
            Log.e("result",cusor2.getString(cusor2.getColumnIndex("title"))
            +":"+cusor2.getString(cusor2.getColumnIndex("subA"))
            +":"+cusor2.getString(cusor2.getColumnIndex("subB"))
            +":"+cusor2.getString(cusor2.getColumnIndex("subC"))
            +":"+cusor2.getString(cusor2.getColumnIndex("subD"))
            +":"+cusor2.getString(cusor2.getColumnIndex("pic"))
            +":"+cusor2.getString(cusor2.getColumnIndex("plain"))
            +":"+cusor2.getString(cusor2.getColumnIndex("answer"))
            +":"+cusor2.getString(cusor2.getColumnIndex("chapter"))
            +":"+cusor2.getInt(cusor2.getColumnIndex("kemu"))

            );
        }

        database.close();
        helper.close();
    }

}