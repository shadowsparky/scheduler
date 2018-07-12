package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import ru.shadowsparky.scheduler.carolinescheduler.Adapters.MainListAdapter;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Scheduler_Database;

public interface IMainContracts {
    interface MainFragmentContract{
        void setAdapter(MainListAdapter adapter);
    }
    interface MainViewContract{
        void setToolbar();
        Scheduler_Database getDbEngine();
        SQLiteDatabase getReadableDatabase();
        SQLiteDatabase getWriteableDatabase();
        void itemClick(int position);
        void itemFragmentLoad();
    }
    interface MainPresenterContract{

    }
    interface MainModelContract{

    }
}
