package ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "schedulestable")
public class SchedulesTable implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int Schedule_ID;
//    private String Name;
    private String Caption;
    private String Importance_Level;
    private String Date;
    private String Time;
    private String Email;
    public int getSchedule_ID() {
        return Schedule_ID;
    }
    public void setSchedule_ID(int schedule_ID) {
        Schedule_ID = schedule_ID;
    }
//    public String getName() {
//        return Name;
//    }
//    public void setName(String name) {
//        Name = name;
//    }
    public String getCaption() {
        return Caption;
    }
    public void setCaption(String caption) {
        Caption = caption;
    }
    public String getImportance_Level() {
        return Importance_Level;
    }
    public void setImportance_Level(String importance_Level) {
        Importance_Level = importance_Level;
    }
    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }
    public String getTime() {
        return Time;
    }
    public void setTime(String time) {
        Time = time;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
}
