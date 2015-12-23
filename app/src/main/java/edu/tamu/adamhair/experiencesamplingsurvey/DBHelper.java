package edu.tamu.adamhair.experiencesamplingsurvey;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "survey_results";
    public static final String TABLE_NAME = "survey_results";
    public static final String TABLE_ROW_ID = "id";
    public static final String TABLE_ROW_ONE = "activity_response";
    public static final String TABLE_ROW_TWO = "likert_response";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table survey_results " +
                "(id INTEGER PRIMARY KEY, activity_response TEXT, likert_response INTEGER, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS survey_results");
        onCreate(db);
    }

    public boolean insert  (String response, Integer likert)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("activity_response", response);
        contentValues.put("likert_response", likert);
        db.insert("survey_results", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from survey_results where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean update (Integer id, String response, Integer likert)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("activity_response", response);
        contentValues.put("likert_response", likert);
        db.update("survey_results", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer delete (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("survey_results",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAll()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from survey_results", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(TABLE_ROW_ONE)));
            res.moveToNext();
        }
        return array_list;
    }
}
