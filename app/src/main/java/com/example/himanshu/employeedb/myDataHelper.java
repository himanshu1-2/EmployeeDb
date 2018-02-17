package com.example.himanshu.employeedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by himanshu on 1/5/2018.
 */

public class myDataHelper extends SQLiteOpenHelper{

Context context;
SQLiteDatabase db;
    public myDataHelper(Context context) {
        super(context, "ourDatabase", null, 1);
        this.context=context;
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuerry=
           "create table Employees1(id integer PRIMARY KEY,name text,salary integer)";
        db.execSQL(sqlQuerry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addEmployee(int id, String name, int salary) {
        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("name",name);
        values.put("salary",salary);
      long rid=   db.insert("Employees1",null,values);
        if(rid<0)
        {
            Toast.makeText(context, "insert issue"+rid, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "insert suessfull", Toast.LENGTH_SHORT).show();
        }
    }

    public String getAllEmpl() {
        Cursor cursor=db.query("Employees1",null,null,null,null,null,null,null);
        { int sum=0;
            StringBuffer resultSet = new StringBuffer();
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                do {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    int salary=cursor.getInt(2);
                    //resultSet.append("id" + id + "name" + name + "\n");
                            resultSet.append("id" + id + "name" + name +salary+ "\n");
                } while (cursor.moveToNext());
                //cursor.moveToFirst();
                /*do {
                    int salary=cursor.getInt(2);
                    sum=sum+salary;
                    resultSet.append("sum"+sum);
                }while (cursor.moveToNext());*/

            } else
                return "0 rows in table";
            return resultSet.toString();
        }

    }

    public void UpdateEmployee(int id, String name) {
        ContentValues cv=new ContentValues();
        cv.put("id",id);
        cv.put("name",name);
        long rid=db.update("Employees1",cv,"id="+id,null);
        if(rid==0){
            Toast.makeText(context, "0 row updated", Toast.LENGTH_SHORT).show();


        }
        else
        {
            Toast.makeText(context, ""+rid+"rows"+"Updated sucss", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteEmployee(int id) {

        long rid=db.delete("Employees","id="+id,null);
        if(rid==0){
            Toast.makeText(context, "0 row updated", Toast.LENGTH_SHORT).show();


        }
        else
        {
            Toast.makeText(context, ""+rid+"rows"+"delete sucss", Toast.LENGTH_SHORT).show();
        }
    }
}
