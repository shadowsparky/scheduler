package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import android.util.Log;

import java.util.List;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.DAO.ScheduleDao;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Schedules_Database;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Threads.InsertThread;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.DatabaseConfig;

public class AddScheduleModel implements IAddSheduleContracts.IContractSheduleModel {

    public void addScheduleLogic(SchedulesTable data, ICallbacks.ILoadCallback callback) {
        InsertThread thread= new InsertThread(callback);
        thread.execute(data);
        getAll();
    }
    @Deprecated
    /*TODO: DELETE ME PLEASE*/
    private void getAll(){
        Schedules_Database db = DatabaseConfig.getInstance().getDb();
        ScheduleDao query = db.schedule_dao();
        Runnable testThread = () -> {
            List<SchedulesTable> d = query.getAll();
            Log.isLoggable("test", Log.DEBUG);
            for (int i = 0; i < d.size(); i++)
                Log.d("test", d.get(i).getCaption());
        };
    }
}
