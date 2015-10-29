package com.tadhg.moodchart.database;

/**
 * Created by Tadhg on 01/10/2015.
 */
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.tadhg.moodchart.database.DataBaseHelper;

public class MoodDBDAO {

    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context mContext;

    public MoodDBDAO(Context context) {
        this.mContext = context;
        dbHelper = DataBaseHelper.getHelper(mContext);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DataBaseHelper.getHelper(mContext);
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
        database = null;
    }
}

