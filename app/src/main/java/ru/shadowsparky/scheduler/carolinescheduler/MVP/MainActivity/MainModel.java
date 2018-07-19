package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.DAO.ScheduleDao;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Schedules_Database;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Threads.AsyncDelete;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Threads.AsyncSelect;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.DatabaseConfig;

public class MainModel implements IMainContracts.MainModelContract {
    @Override public ScheduleDao qryinit(Context _context){
        Schedules_Database db = DatabaseConfig.getDb(_context);
        return db.schedule_dao();
    }
    @Override public List<SchedulesTable>getElements(ICallbacks.ILoadCallback callback, Context context){
        AsyncSelect thread = new AsyncSelect(qryinit(context), callback);
        thread.execute();
        try {
            return thread.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override public void deleteElement(ICallbacks.ILoadCallback callback, Context context, SchedulesTable element) {
        AsyncDelete thread = new AsyncDelete(qryinit(context), callback, element);
        thread.execute();
    }
}
