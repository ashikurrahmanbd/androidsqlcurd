package com.example.sqlitedatabaseone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, age, profession, rowIdd;
    Button saveButton;
    Button showData, updateData, deleteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDataBaseHelper.getWritableDatabase();


        name = (EditText) findViewById(R.id.nameId);
        age = (EditText) findViewById(R.id.ageId);
        profession = (EditText) findViewById(R.id.professionId);
        saveButton = (Button) findViewById(R.id.saveBtnId);
        showData = (Button) findViewById(R.id.showData);

        updateData = (Button) findViewById(R.id.updateData);
        rowIdd = (EditText) findViewById(R.id.rowEditId);

        deleteData = (Button) findViewById(R.id.deleteDataId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pname = name.getText().toString();
                String page = age.getText().toString();
                String pprofession = profession.getText().toString();

               long rowId =  myDataBaseHelper.insertData(pname, page, pprofession);
               if(rowId == -1){

                   Toast.makeText(MainActivity.this, "Data has Not inserted", Toast.LENGTH_SHORT).show();
               }else{

                   Toast.makeText(MainActivity.this, "Data has inserted Successfully", Toast.LENGTH_SHORT).show();
                   name.setText("");
                   age.setText("");
                   profession.setText("");
               }

            }
        });

        showData = (Button) findViewById(R.id.showData);

        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, StudentDetails.class);
                startActivity(intent);

            }
        });

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pname = name.getText().toString();
                String page = age.getText().toString();
                String pprofession = profession.getText().toString();

                String rowId = rowIdd.getText().toString();

               Boolean isUpdated =  myDataBaseHelper.updateData(pname, page, pprofession, rowId);

               if(isUpdated == true){
                   Toast.makeText(MainActivity.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(MainActivity.this, "Data has not updated", Toast.LENGTH_SHORT).show();
               }




            }
        });

        //delete data
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String RowId = rowIdd.getText().toString();

               int value =  myDataBaseHelper.deleteData(RowId);

               if(value > 0){

                   Toast.makeText(MainActivity.this, "Data has Deleted", Toast.LENGTH_SHORT).show();

               }else{
                   Toast.makeText(MainActivity.this, "Data has not deleted", Toast.LENGTH_SHORT).show();
               }

            }
        });



    }
}
