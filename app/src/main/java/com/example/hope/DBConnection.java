package com.example.hope;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.jar.Attributes;

public class DBConnection extends SQLiteOpenHelper {
    private static final int version = 1;
    private static final String DbName ="patient_info";
    public DBConnection(Context context){
        super  (context,DbName,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("create table IF NOT EXISTS Patient(id INTEGER primary key AUTOINCREMENT,Name  TEXT  NOT NULL ,username TEXT NOT NULL,Age INTEGER NOT NULL,Password TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("Drop table if EXISTS Patient");
    onCreate(sqLiteDatabase);
    }

    public void deleteR(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from Patient where id=" + Integer.toString(id));
    }

    public void updateName (String name ,Integer id ){
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("update Patient set Name = '" + name + "' where id = " + Integer.toString(id));
    }
    public void updatePassword (String password ,Integer id ){
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("update Patient set Password = '" + password + "' where id = " + Integer.toString(id));
    }
    public void updateAge (String Age ,Integer id ){
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("update Patient set Age = '" + Age + "' where id = " + Integer.toString(id));
    }
    public void updateUserName (String userName ,Integer id ){
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("update Patient set username = '" + userName + "' where id = " + Integer.toString(id));
    }
    public void insertROWpatient(String name, int age, String username, String password){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("Name",name);
        contentValues.put("Age",age);
        contentValues.put("username",username);
        contentValues.put("Password",password);
        db.insert("Patient",null,contentValues);


    }

    public ArrayList getAllrecord(){
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Patient",null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            arrayList.add(res.getString(res.getColumnIndex("id")));
            arrayList.add(res.getString(res.getColumnIndex("Name")));
            arrayList.add(res.getString(res.getColumnIndex("username")));
            arrayList.add(res.getString(res.getColumnIndex("Age")));
            arrayList.add(res.getString(res.getColumnIndex("Password")));


            res.moveToNext();
        }

        return  arrayList;
    }
    public String getID(String name){
        String ID="";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select id from Patient where username = '"+ name +"'" ,null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            ID = res.getString(res.getColumnIndex("id"));
            res.moveToNext();
        }
        return ID;
    }
    public String getPassword(String name){
        String password="";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Password from Patient where username = '"+ name +"'" ,null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            password = res.getString(res.getColumnIndex("Password"));
            res.moveToNext();
        }
        return password;
    }
    public String getName(int id){
        String name = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Name from Patient where id = "+ id ,null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            name = res.getString(res.getColumnIndex("Name"));
            res.moveToNext();
        }
        return name;
    }
    public String getAge(int id){
        String Age = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Age from Patient where id = "+ id ,null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            Age = res.getString(res.getColumnIndex("Age"));
            res.moveToNext();
        }
        return Age;
    }
    public String getUsername(int id){
        String username = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select username from Patient where id = "+ id ,null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            username = res.getString(res.getColumnIndex("username"));
            res.moveToNext();
        }
        return username;
    }
    public boolean checkUsername(String name){
        String username ;
        boolean ID =false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select username from Patient " ,null);
        res.moveToFirst();

            while (!res.isAfterLast()){
                username = res.getString(res.getColumnIndex("username"));
                if (username.equals(name)) {
                    ID = true;
                }
                res.moveToNext();
            }


        return ID;
    }
}
