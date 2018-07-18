package ru.shadowsparky.scheduler.carolinescheduler.SQLite;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.shadowsparky.scheduler.carolinescheduler.SQLite.DAO.ScheduleDao;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

@Database(entities = {SchedulesTable.class}, version = 2)
public abstract class Schedules_Database extends RoomDatabase {
    public abstract ScheduleDao schedule_dao();
}
