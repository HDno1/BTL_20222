package dz.phamtuanvan.btl_2022.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import dz.phamtuanvan.btl_2022.Fragment.HomeFragment;
import dz.phamtuanvan.btl_2022.model.Student;

public class SqlLogin extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "login2.db";


    private static final int VERSION = 1;

    private SQLiteDatabase db;
    public SqlLogin(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        db.execSQL(" create Table users (id integer primary key autoincrement,email TEXT , password TEXT, name TEXT, msv TEXT)");
        db.execSQL("create table admin  (userAd TEXT primary key, passAd TEXT)");
        db.execSQL("create table StudentInfo (id integer primary key autoincrement, name text, score text)");

        //Tài khoản Admin
        db.execSQL("insert into admin values ('Admin','1')");
        //Tải khoản mẫu
        db.execSQL("insert into users values (1,'tuanvan@gmail.com','12345','Phạm Tuấn Văn','191202620')");
        db.execSQL("insert into users values (2,'dangchien@gmail.com','12345','Nguyễn Đăng Chiến','191560560')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("drop table if exists StudentInfo");
        db.execSQL("drop table if exists admin");
        onCreate(db);
    }

    public boolean checkAdmin(String userAd, String passAd){
        db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from admin where userAd = ? and passAd = ?", new String[]{userAd,passAd});
        if (c.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkusernamepassword(String email, String password){
        db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from users where email = ? and password = ?", new String[]{email,password});
        if (c.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkusername(String email){
        db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from users where email = ?", new String[]{email});
        if (c.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean addUser(String email, String pass, String name, String msv){
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email",email);
        values.put("password",pass);
        values.put("name",name);
        values.put("msv",msv);
        long result = db.insert("users",null,values);

        if (result == -1) return false;
        else
            return true;
    }

    public boolean deleUser(int id){
        db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from users where id = ?",new String[]{String.valueOf(id)});
        if (c.getCount()>0){
            long result = db.delete("users","id=?",new String[]{String.valueOf(id)});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    public boolean updateUser(String email,String pass){
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password",pass);
        long result = db.update("users",values,  "email = ?",new String[]{email});

        if (result == -1) return false;
        else
            return true;
    }

    /*private int getID(String name, String job){
        db = getReadableDatabase();
    Cursor c = db.query("StudentInd",new String[]{"_id"} "name =? AND job=?",new String[]{name,job},null,null,null,null);
    if (c.moveToFirst()) //if the row exist then return the id
        return c.getInt(c.getColumnIndex("_id"));
    return -1;
    }*/

    public boolean addStudentTest(String msv, String score){
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",msv);
        values.put("score",score);

        long result = db.insert("StudentInfo",null,values);

        if (result == -1) return false;
        else
            return true;
    }

    /*public boolean addStudentTest(String msv, String score){
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("msv",msv);
        values.put("score",score);
        int id = (int) db.insertWithOnConflict("StudentInfo", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        if (id == -1) {
        long result = db.update("StudentInfo", values, "msv=?", new String[] {msv});
        // number 1 is the _id here, update to variable for your code
            if (result == -1) return false;
        else
            return true;
}
        *//*long result = db.insert("StudentInfo",null,values);

        if (result == -1) return false;
        else
            return true;*//*
    }*/

    public boolean deleteData(int id){
        db = getWritableDatabase();
        Cursor c = db.rawQuery("select * from StudentInfo where id = ?",new String[]{String.valueOf(id)});
        if (c.getCount()>0){
            long result = db.delete("StudentInfo","id=?",new String[]{String.valueOf(id)});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    public ArrayList<Student> getaAllData(){
        ArrayList<Student> arrayList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM StudentInfo",null);

        while (c.moveToNext()){
            Integer id = c.getInt(0);
            String name = c.getString(1);
            String score = c.getString(2);

            Student student = new Student(id,name,score);

            arrayList.add(student);
        }

        return arrayList;

    }

    public ArrayList<Student> arrangeScore(){
        ArrayList<Student> arrayList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.query("StudentInfo",null,null,null,null,null,"score"+ " DESC");

        while (c.moveToNext()){
            Integer id = c.getInt(0);
            String name = c.getString(1);
            String score = c.getString(2);

            Student student = new Student(id,name,score);

            arrayList.add(student);
        }

        return arrayList;

    }

    public ArrayList<Student> arrangeScore1(){
        ArrayList<Student> arrayList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.query("StudentInfo",null,null,null,null,null,"score"+ " ASC");

        while (c.moveToNext()){
            Integer id = c.getInt(0);
            String name = c.getString(1);
            String score = c.getString(2);

            Student student = new Student(id,name,score);

            arrayList.add(student);
        }

        return arrayList;

    }
    /*public ArrayList<Student> getaMSVNameScore(){
        ArrayList<Student> arrayList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM StudentInfo",null);

        while (c.moveToNext()){
            Integer id = c.getInt(0);
            String msv = c.getString(1);
            String score = c.getString(2);
            String name = c.getString(3);
            String sex = c.getString(4);

            Student student = new Student(msv,score,name);

            arrayList.add(student);
        }

        return arrayList;

    }*/

    /*public ArrayList<Student> getaMSVName(){
        ArrayList<Student> arrayList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM users",null);

        while (c.moveToNext()){
            Integer id = c.getInt(0);
            String msv = c.getString(1);
            String score = c.getString(2);
            String name = c.getString(3);
            String sex = c.getString(4);

            Student student = new Student(msv,score,name,sex);

            arrayList.add(student);
        }

        return arrayList;

    }*/
    public ArrayList<Student> getMSV(){
        ArrayList<Student> arrayList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM users",null);

        while (c.moveToNext()){
            Integer id = c.getInt(0);
            String email = c.getString(1);
            String namesv = c.getString(3);

            Student student = new Student(id,namesv);

            arrayList.add(student);
        }

        return arrayList;

    }
    public ArrayList<Student> getName(String email){
        ArrayList<Student> arrayList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.query("users",null,"email =?", new String[]{email},null,
                null,null);

        while (c.moveToNext()){
            Integer id = c.getInt(0);
            String namesv = c.getString(3);

            Student student = new Student(id,namesv);

            arrayList.add(student);
        }

        return arrayList;

    }


    public ArrayList<Student> getPass(String username){
        ArrayList<Student> arrayList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.query("users",null,"email =?", new String[]{username},null,
                null,null);

        while (c.moveToNext()){
            String pass = c.getString(2);

            Student student = new Student(pass);

            arrayList.add(student);
        }

        return arrayList;

    }


}
