package ru.shadowsparky.scheduler.carolinescheduler.SQLite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;

public class InsertThread extends AsyncTask<String, Void, Long> {
    SQLiteDatabase db;
    String TableName;
    ContentValues values;
    ICallbacks.ILoadCallback callback;

    public InsertThread(SQLiteDatabase db, String tableName, ContentValues values) {
        this.db = db;
        this.TableName = tableName;
        this.values = values;
    }
    protected void onPreExecute() {
        callback.setLoading(true);
    }
    protected void onPostExecute(Long result) {
        callback.setLoading(false);
    }
    protected Long doInBackground(String... strings) {
        return db.insert(TableName, null, values);
    }
}
