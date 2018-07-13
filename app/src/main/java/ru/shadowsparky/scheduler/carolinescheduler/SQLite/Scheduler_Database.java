package ru.shadowsparky.scheduler.carolinescheduler.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Scheduler_Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SCHEDULER_DATABASE";
    public static final int DATABASE_CURRENT_VERSION = 1;
    public static final String SCHEDULES_TABLE = "Schedules";
    public static final String TASKS_TABLE = "Tasks";
    private Context _context;

    public Scheduler_Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_CURRENT_VERSION);
        this._context = context;
    }
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createDatabase(sqLiteDatabase);
    }
    private void createDatabase(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL (
                "CREATE TABLE " + SCHEDULES_TABLE + " " +
                "( " +
                "Shedule_ID INTEGER NOT NULL " +
                "CONSTRAINT Shedule_ID PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT NOT NULL, " +
                "Caption TEXT NOT NULL, " +
                "Importance_Level NUMERIC NOT NULL, " +
                "Date TEXT NOT NULL, " +
                "Time NONE NOT NULL, " +
                "User_Email NONE NOT NULL " +
                "); " +
                        "CREATE TABLE " + TASKS_TABLE +
                "( " +
                "Task_ID INTEGER NOT NULL " +
                "CONSTRAINT Task_ID PRIMARY KEY AUTOINCREMENT, " +
                "Task_Name TEXT NOT NULL, " +
                "Task_Caption NONE NOT NULL " +
                ");"
        );
    }
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        _context.deleteDatabase(DATABASE_NAME);
        createDatabase(sqLiteDatabase);
    }
}