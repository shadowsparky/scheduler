package ru.shadowsparky.scheduler.carolinescheduler.SQLite.DAO;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Schedules_Database;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

public interface ScheduleDao {
    @Query("SELECT * FROM schedulestable")
    List<SchedulesTable> getAll();
    @Query("SELECT * FROM schedulestable WHERE Schedule_ID = :id")
    SchedulesTable getById(long id);
    @Insert
    void insert(SchedulesTable employee);
    @Update
    void update(SchedulesTable employee);
    @Delete
    void delete(SchedulesTable employee);
}