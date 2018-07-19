package ru.shadowsparky.scheduler.carolinescheduler.SQLite.Threads;

import android.os.AsyncTask;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.DAO.ScheduleDao;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

public class AsyncDelete extends AsyncTask<Void, Void, Void> {
    ScheduleDao query;
    ICallbacks.ILoadCallback callback;
    SchedulesTable element;

    public AsyncDelete(ScheduleDao query, ICallbacks.ILoadCallback callback, SchedulesTable element) {
        this.query = query;
        this.callback = callback;
        this.element = element;
    }
    @Override protected void onPreExecute() {
        super.onPreExecute();
        callback.setLoading(true);
    }
    @Override protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        callback.setLoading(false);
    }
    @Override protected Void doInBackground(Void... voids) {
        query.delete(element);
        return null;
    }
}
