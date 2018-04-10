package cn.edu.gdmec.boxuegu.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apple on 18/4/10.
 */

public class SQLiteHelper extends SQLiteOpenHelper{
    public static final int DB_VERSION = 1;
    public static String DB_NAME = "bxg.db";
    public static final String U_USERINFO = "userinfo";

    public SQLiteHelper(Context context){
        super(context, DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + U_USERINFO +"("
                +"_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"userName VARCHAR,"
                +"nickName VARCHAR,"
                +"sex VARCHAR,"
                +"signature VARCHAR"
                +")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + U_USERINFO);
        onCreate(db);
    }
}
