package com.tadhg.moodchart.database;

/**
 * Created by Tadhg on 01/10/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;



public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mooddb";
    private static final int DATABASE_VERSION = 1;


    public static final String MOOD_TABLE = "mood";


    public static final String _ID = "id";
    public static final String DATE_COLUMN = "date";
    public static final String GREAT_COLUMN = "great";
    public static final String GOOD_COLUMN = "good";
    public static final String AVERAGE_COLUMN = "average";
    public static final String BAD_COLUMN = "bad";
    public static final String TERRIBLE_COLUMN = "terrible";


    public static final String CREATE_MOOD_TABLE = "CREATE TABLE "
            + MOOD_TABLE + "(" + _ID + " INTEGER PRIMARY KEY, "
            + DATE_COLUMN + " TEXT , " + GREAT_COLUMN + " INTEGER, " + GOOD_COLUMN + " INTEGER, "
            + AVERAGE_COLUMN + " INTEGER, "
            + BAD_COLUMN + " INTEGER, "
            + TERRIBLE_COLUMN + " INTEGER, " + ")";


    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DataBaseHelper(context);
        return instance;
    }

  /*  public void createRow(String code, String name) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("code", MESSAGE_COLUMN);
        initialValues.put("name", LAT);
        initialValues.put("name", LNG);
        .insert(MESSAGE_TABLE, null, initialValues);
    }*/


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOOD_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //  Log.w(DEBUG_TAG, "Upgrading database. Existing contents will be lost. ["
        //         + oldVersion + "]->[" + newVersion + "]");
        db.execSQL("DROP TABLE IF EXISTS " + MOOD_TABLE);
        onCreate(db);
    }

    public Cursor query(String tableName, String orderedBy) {
        String[] projection = {_ID, DATE_COLUMN, GREAT_COLUMN, GOOD_COLUMN, AVERAGE_COLUMN, BAD_COLUMN, TERRIBLE_COLUMN};
        return getReadableDatabase().query(tableName, projection, null, null, null, null, orderedBy);
    }

    public boolean saveMood (String date, int great, int good, int average, int bad, int terrible)
    {
        Cursor cursor = getMood(_ID);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("date", DATE_COLUMN);
        contentValues.put("great", GREAT_COLUMN);
        contentValues.put("good", GOOD_COLUMN);
        contentValues.put("average", AVERAGE_COLUMN);
        contentValues.put("bad", BAD_COLUMN);
        contentValues.put("terrible", TERRIBLE_COLUMN);

        long result;
        if (cursor.getCount() == 0) { // Record does not exist
            contentValues.put("id", _ID);
            result = db.insert("moods", null, contentValues);
        } else { // Record exists
            result = db.update("moods", contentValues, "dates=?", new String[] { _ID });
        }

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getMood(String date){

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM moods WHERE date=?";

        return db.rawQuery(sql, new String[] { date } );
    }
}
