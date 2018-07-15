package ru.shadowsparky.scheduler.carolinescheduler.Utils;

import android.app.Application;
import android.arch.persistence.room.Room;

import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Schedules_Database;

public class DatabaseConfig extends Application {
    public static DatabaseConfig Instance;
    public static Schedules_Database db;

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
        db = Room.databaseBuilder(this, Schedules_Database.class, "db").build();
    }
    public static DatabaseConfig getInstance(){
        return Instance;
    }
    public static Schedules_Database getDb(){
        return db;
    }
}
