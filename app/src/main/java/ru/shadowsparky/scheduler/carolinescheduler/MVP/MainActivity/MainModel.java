package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.DAO.ScheduleDao;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Schedules_Database;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.DatabaseConfig;

public class MainModel implements IMainContracts.MainModelContract {
    ICallbacks.ILoadCallback callback;
    Schedules_Database db;
    ScheduleDao query;

    public List<SchedulesTable>getElements(ICallbacks.ILoadCallback callback, Context context){
        this.callback = callback;
        db = DatabaseConfig.getDb(context);
        query = db.schedule_dao();
        AsyncSelect thread = new AsyncSelect(query, callback);
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
}
class AsyncSelect extends AsyncTask<Void, Void, List<SchedulesTable>>{
    ScheduleDao query;
    ICallbacks.ILoadCallback callback;

    public AsyncSelect(ScheduleDao query, ICallbacks.ILoadCallback callback) {
        this.query = query;
        this.callback = callback;
    }

    @Override
    protected List<SchedulesTable> doInBackground(Void... voids) {
        return query.getAll();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callback.setLoading(true);
    }

    @Override
    protected void onPostExecute(List<SchedulesTable> schedulesTables) {
        super.onPostExecute(schedulesTables);
        callback.setLoading(false);
    }
}
