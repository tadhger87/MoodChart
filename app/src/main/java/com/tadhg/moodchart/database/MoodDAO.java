package com.tadhg.moodchart.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tadhg.moodchart.model.Mood;

import java.util.ArrayList;

/**
 * Created by Tadhg on 06/10/2015.
 */
public class MoodDAO extends MoodDBDAO {

    private static final String WHERE_ID_EQUALS = DataBaseHelper._ID
            + " =?";
    private static final String MOOD_TABLE = DataBaseHelper.MOOD_TABLE ;


    String[] cols = {DataBaseHelper._ID, DataBaseHelper.DATE_COLUMN, DataBaseHelper.GREAT_COLUMN,
            DataBaseHelper.GOOD_COLUMN, DataBaseHelper.AVERAGE_COLUMN,DataBaseHelper.BAD_COLUMN,
            DataBaseHelper.TERRIBLE_COLUMN};


    public MoodDAO(Context context) {
        super(context);
    }


    public long save(Mood mood) {

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.DATE_COLUMN, mood.getDate());
        values.put(DataBaseHelper.GREAT_COLUMN, mood.getGreat());
        values.put(DataBaseHelper.GOOD_COLUMN, mood.getGood());
        values.put(DataBaseHelper.AVERAGE_COLUMN, mood.getAverage());
        values.put(DataBaseHelper.BAD_COLUMN, mood.getBad());
        values.put(DataBaseHelper.TERRIBLE_COLUMN, mood.getTerrible());


        return database.insert(MOOD_TABLE, null, values);
    }

 /*
    public void createRow(Mood mood) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DataBaseHelper2.MESSAGE_COLUMN, "code");
        initialValues.put(DataBaseHelper2.LAT, 53.348647);
        initialValues.put(DataBaseHelper2.LNG, -6.257222);
        database.insert(MESSAGE_TABLE, null, initialValues);
    }
*/

    //USING query() method
    public ArrayList<Mood> getMoods() {
        ArrayList<Mood> moods = new ArrayList<Mood>();

        Cursor cursor = database.query(MOOD_TABLE,
                new String[]{DataBaseHelper._ID,
                        DataBaseHelper.DATE_COLUMN,
                        DataBaseHelper.GREAT_COLUMN,
                        DataBaseHelper.GOOD_COLUMN,
                        DataBaseHelper.AVERAGE_COLUMN,
                        DataBaseHelper.BAD_COLUMN,
                        DataBaseHelper.TERRIBLE_COLUMN}, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            Mood mood = new Mood();
            mood.setId(cursor.getInt(0));
            mood.setDate(cursor.getString(1));
            mood.setGreat(cursor.getInt(2));
            mood.setGood(cursor.getInt(3));
            mood.setAverage(cursor.getInt(4));
            mood.setBad(cursor.getInt(5));
            mood.setTerrible(cursor.getInt(6));

            moods.add(mood);
        }
        return moods;
    }

/*
    //WHERE I SEND THE MARKERS FROM

    public ArrayList<Message> getMyMarkers() {
        ArrayList<Message> markers = new ArrayList<Message>();
        Cursor cursor = database.query(MESSAGE_TABLE, cols, null, null, null, null, null);

        cursor.moveToPosition(3);
        while (!cursor.isAfterLast()) {
            Message m = cursorToMarker(cursor);
            markers.add(m);
            cursor.moveToNext();
        }
        cursor.close();
        return markers;
    }


//WHERE I TRY TO SELECT THE LAT LNG MESSAGES

    public ArrayList<Message> getLLMessages(String lat, String lng) {
        ArrayList<Message> llmessages = new ArrayList<Message>();
        Cursor cursor = database.query(MESSAGE_TABLE,
                new String[]{DataBaseHelper2._ID,
                        DataBaseHelper2.MESSAGE_COLUMN,
                        DataBaseHelper2.TIMESTAMP,
                        DataBaseHelper2.LAT,
                        DataBaseHelper2.LNG},
                LAT + "=?" + " and " + LNG + "=?",

                new String[]{String.valueOf(lat),
                        String.valueOf(lng)},
                null, null,
                null, null);

        Message messagell = new Message();

        if (cursor != null) {

            if (!cursor.moveToFirst()) {

                return null;
            }
            messagell.setId(cursor.getInt(0));
            messagell.setMessage(cursor.getString(1));
            messagell.setTimestamp(cursor.getString(2));
            messagell.setLat(cursor.getDouble(3));
            messagell.setLng(cursor.getDouble(4));

            llmessages.add(messagell);
        } else {
            return null;
        }
        Log.d("getLLMessages(" + lat + lng + ")", messagell.toString());

        return llmessages;
    }


    private Message cursorToMarker(Cursor cursor) {
        Message m = new Message();
        m.setLat(cursor.getDouble(3));
        m.setLng(cursor.getDouble(4));

        return m;
    }





    //Retrieves a single MESSAGE record with the given id
    public Mood getMood(String lat, String lng) {
        Mood mood = null;

        String sql = "SELECT * FROM " + DataBaseHelper.MOOD_TABLE
                + " WHERE " + DataBaseHelper.LAT + " = ?" + "and" + DataBaseHelper2.LNG + " =?";

        Cursor cursor = database.rawQuery(sql, new String[]{lat + "", lng + ""});

        if (cursor.moveToNext()) {
            message = new Message();
            message.setId(cursor.getInt(0));
            message.setMessage(cursor.getString(1));
            message.setTimestamp(cursor.getString(2));
            message.setLat(cursor.getDouble(3));
            message.setLng(cursor.getDouble(4));


        }
        return message;
    }
    */
}

