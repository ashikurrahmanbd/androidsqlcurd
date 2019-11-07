package com.example.sqlitedatabaseone;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StudentDetails extends AppCompatActivity {

    ListView listView;

    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);



        final MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDataBaseHelper.getWritableDatabase();

        listView = (ListView) findViewById(R.id.studentListViewId);

        Cursor cursor = myDataBaseHelper.displayAllData();
        if(cursor.getCount() == 0){

            //data not found
            return;
        }

        dataList = new ArrayList<String>();
        if(cursor.moveToFirst()){
            do{

                String studentName = cursor.getString(cursor.getColumnIndex("name"));
                String studentAge = cursor.getString(cursor.getColumnIndex("age"));
                String studentProfession = cursor.getString(cursor.getColumnIndex("profession"));
                String studentId = cursor.getString(cursor.getColumnIndex("id"));
                dataList.add(studentId + " "+ studentName + " "+ studentAge + " " +studentProfession );

            }while(cursor.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);

        listView.setAdapter(adapter);


    }










}
