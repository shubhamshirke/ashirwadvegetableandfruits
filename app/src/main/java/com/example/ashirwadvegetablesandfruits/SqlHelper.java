package com.example.ashirwadvegetablesandfruits;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;

public class SqlHelper extends SQLiteOpenHelper {
    public static final String dname = "Database";
    public static final String tbname = "customerdetails";
    public static final String col1 = "customername";
    public static final String col2 = "societyname";
    public static final String col3 = "paid";
    public static final String col4 = "balance";
    public static final String col5 = "total";
    public SqlHelper(@Nullable Context context) {
        super(context, dname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + tbname + "(id INTEGER PRIMARY KEY AUTOINCREMENT,customername TEXT,societyname TEXT,paid TEXT,balance TEXT,total INTEGER);";
        sqLiteDatabase.execSQL(sql);
        Log.e(TAG, "onCreate: Table is created successfully", null);
    }

    public boolean insert(String customername, String societyname, String paid, String balance, Integer total) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues val = new ContentValues();
        val.put(col1, customername);
        val.put(col2, societyname);
        val.put(col3, paid);
        val.put(col4, balance);
        val.put(col5, total);


        long rs = db.insert(tbname, null, val);
        if (rs == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor search_data(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cu = db.query(tbname, new String[]{"id",col1,col2,col3,col4,col5},"customername LIKE ?",new String[]{name},null,null,null,null);
        return cu;
    }
    public int delete(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String[]whereArgs={name};
        Log.d("Name", "delete: name"+name);
        int count=db.delete(tbname,col1+" = ?",whereArgs);
        return count;
    }
    public void update(String paid, String balance,Integer total,String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(paid,col3);
        cv.put(balance,col4);
            cv.put(total.toString(),col5);
        String[] args = new String[]{name};
       // db.execSQL("UPDATE "+tbname+" SET paid = "+"'"+paid+"',balance = "+"'"+balance+"',total = "+"'"+total+"'"+ "WHERE salary = "+"'"+name+"'");
    db.update(tbname,cv,"customername=?",args);
    }

    public Cursor fetch_data() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cu = db.rawQuery("select * from " + tbname, null);
        return cu;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newversion) {
//String sql="ALTER TABLE "+tbname+"ADD COLUMN total INTEGER;";
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS customerdetails");
        onCreate(sqLiteDatabase);
     /*   oldversion=1;
        newversion=2;
        Log.d("version", "onUpgrade: New version and oldversion"+oldversion+"new version"+newversion);
        if (newversion > oldversion) {
            sqLiteDatabase.execSQL("ALTER TABLE customerdetails ADD COLUMN total INTEGER");

        }*/
    }
}
