package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.Schedule_Element;

public interface IMainContracts {

    interface MainViewContract{
        void setToolbar();
        void ShowList();
        void HideList();
        void enableRefreshing();
        void disableRefreshing();
        Context getContext();
        void setAdapter(List<SchedulesTable> elements);
        void openActivity(Intent intent);
    }
    interface MainPresenterContract{
        void getDataToList();
        void showAddScheduleActivity();
        void showViewScheduleActivity(int Position);
    }
    interface MainModelContract{
        List<SchedulesTable> getElements(ICallbacks.ILoadCallback callback, Context context);
    }
}
