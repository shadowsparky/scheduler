package ru.shadowsparky.scheduler.carolinescheduler.SQLite.Threads;

import android.os.AsyncTask;

import java.util.List;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.DAO.ScheduleDao;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

public class AsyncSelect extends AsyncTask<Void, Void, List<SchedulesTable>> {
    ScheduleDao query;
    ICallbacks.ILoadCallback callback;

    public AsyncSelect(ScheduleDao query, ICallbacks.ILoadCallback callback) {
        this.query = query;
        this.callback = callback;
    }
    @Override protected List<SchedulesTable> doInBackground(Void... voids) {
        return query.getAll();
    }
    @Override protected void onPreExecute() {
        super.onPreExecute();
        callback.setLoading(true);
    }
    @Override protected void onPostExecute(List<SchedulesTable> schedulesTables) {
        super.onPostExecute(schedulesTables);
        callback.setLoading(false);
    }
}
