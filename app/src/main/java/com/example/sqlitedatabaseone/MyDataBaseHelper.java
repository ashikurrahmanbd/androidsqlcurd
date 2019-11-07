package com.example.sqlitedatabaseone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DB_NAME = "student.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "student_details";

    //column name
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String PROFESSION = "profession";

    //create table variable
    private  static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" varchar(255), "+AGE+" int(11), "+PROFESSION+" varchar(255) );";

    //Drop Table
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    //select Query
    private static final String SELECT_QUERY = "SELECT * FROM "+TABLE_NAME;

    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{

            Toast.makeText(context, "DataBase Created", Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);

        }catch(Exception e){

            Toast.makeText(context, "Db has not created"+e, Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try{
            Toast.makeText(context, "OnUpgrade is called ", Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e){

            Toast.makeText(context, "Db has not Updated "+e, Toast.LENGTH_SHORT).show();
        }



    }

    //insert Method
    public long insertData(String name, String age, String profession){

        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(AGE, age);
        contentValues.put(PROFESSION, profession);

        long rowId =  sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return rowId;

    }


    //displaying data
    public Cursor displayAllData(){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
        return cursor;


    }

    //udpate Data
    public boolean updateData(String name, String age, String profession, String rowId){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, rowId);
        contentValues.put(NAME, name);
        contentValues.put(AGE, age);
        contentValues.put(PROFESSION, profession);

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID +" = ?", new String[]{rowId});

        return true;

    }

    //DeleteData

    public int deleteData(String id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       return sqLiteDatabase.delete(TABLE_NAME, ID+" = ?", new String[]{id});

    }

}
