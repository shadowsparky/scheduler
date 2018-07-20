package ru.shadowsparky.scheduler.carolinescheduler.SQLite.Threads;

import android.os.AsyncTask;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.DAO.ScheduleDao;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

public class AsyncUpdate extends AsyncTask<Void, Void, Void> {
    ICallbacks.ILoadCallback callback;
    SchedulesTable element;
    ScheduleDao query;

    public AsyncUpdate(ScheduleDao query, ICallbacks.ILoadCallback callback, SchedulesTable element) {
        this.callback = callback;
        this.element = element;
        this.query = query;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        query.update(element);
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callback.setLoading(true);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        callback.setLoading(false);
    }
}
