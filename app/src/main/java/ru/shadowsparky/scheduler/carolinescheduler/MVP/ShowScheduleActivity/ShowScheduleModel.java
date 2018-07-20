package ru.shadowsparky.scheduler.carolinescheduler.MVP.ShowScheduleActivity;

import android.content.Context;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.DAO.ScheduleDao;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Schedules_Database;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Threads.AsyncUpdate;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.DatabaseConfig;

public class ShowScheduleModel implements IShowScheduleContract.ShowScheduleModelContract {
    public void update(ICallbacks.ILoadCallback callback, SchedulesTable element, Context context){
        Schedules_Database db = DatabaseConfig.getDb(context);
        ScheduleDao query = db.schedule_dao();
        AsyncUpdate thread = new AsyncUpdate(query, callback, element);
        thread.execute();
    }
}
