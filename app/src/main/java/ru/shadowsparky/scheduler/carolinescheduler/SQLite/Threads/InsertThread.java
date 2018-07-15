package ru.shadowsparky.scheduler.carolinescheduler.SQLite.Threads;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.DAO.ScheduleDao;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Schedules_Database;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.DatabaseConfig;
public class InsertThread extends AsyncTask<SchedulesTable, Void, Void> {
    ICallbacks.ILoadCallback callback;
    Schedules_Database db;
    ScheduleDao query;

    public InsertThread(ICallbacks.ILoadCallback callback) {
        db = DatabaseConfig.getInstance().getDb();
        query = db.schedule_dao();
        this.callback = callback;
    }
    protected void onPreExecute() {
        callback.setLoading(true);
    }
    protected void onPostExecute(Void result) {
        callback.setLoading(false);
    }
    protected Void doInBackground(SchedulesTable... data) {
        query.insert(data[0]);
        return null;
    }
}
