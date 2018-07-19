package ru.shadowsparky.scheduler.carolinescheduler.Utils;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Schedules_Database;

import static java.security.AccessController.getContext;

public class DatabaseConfig extends Application {
    public static final String MAIN_LOG = "MAIN_LOG";
    public static Schedules_Database Instance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Schedules_Database getDb(Context context){
        if (Instance == null)
            Instance = Room.databaseBuilder(context.getApplicationContext(), Schedules_Database.class, "db").fallbackToDestructiveMigration().build();
        return Instance;
    }

    public static void LOG(final String message){
        Log.println(Log.DEBUG, MAIN_LOG, message);
    }
}
