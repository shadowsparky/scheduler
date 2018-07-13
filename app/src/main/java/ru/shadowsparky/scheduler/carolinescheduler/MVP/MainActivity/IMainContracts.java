package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import android.content.Context;
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
        void itemClick(int position);
        void itemFragmentLoad();
        Context getContext();
    }
    interface MainPresenterContract{

    }
    interface MainModelContract{

    }
}
