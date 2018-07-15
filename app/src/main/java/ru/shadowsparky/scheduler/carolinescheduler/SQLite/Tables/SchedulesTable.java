package ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "schedulestable")
public class SchedulesTable {
    @PrimaryKey(autoGenerate = true)
    private int Schedule_ID;
    private String Name;
    private String Caption;
    private String Importance_Level;
    private String Date;
    private String Time;
}
