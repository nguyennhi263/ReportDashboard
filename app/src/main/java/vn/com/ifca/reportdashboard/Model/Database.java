package vn.com.ifca.reportdashboard.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ReportDashboard.db";
    public static final String TABLE_NAME = "ReportDashboard_Table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "URL";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, URL TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, url);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){

            return false;
        }
        else{

            return true;
        }
    }
    public boolean updateData(String id, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, url);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[] {id});
        return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor dat = db.rawQuery("select * from " + TABLE_NAME, null);
        return dat;
    }
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> b54735bc6a18254f9a9df8608c0d1403f0445e06
