package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import android.content.Context;
import android.util.Log;

import java.util.List;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.DAO.ScheduleDao;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Schedules_Database;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Threads.InsertThread;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.DatabaseConfig;

public class AddScheduleModel implements IAddSheduleContracts.IContractSheduleModel {
    private Context context;

    public AddScheduleModel(Context context) {
        this.context = context;
    }

    public void addScheduleLogic(SchedulesTable data, ICallbacks.ILoadCallback callback) {
        InsertThread thread= new InsertThread(callback, context);
        thread.execute(data);
    }
}
