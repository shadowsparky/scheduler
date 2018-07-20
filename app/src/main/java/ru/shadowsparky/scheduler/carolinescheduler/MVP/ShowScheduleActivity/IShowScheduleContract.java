package ru.shadowsparky.scheduler.carolinescheduler.MVP.ShowScheduleActivity;

import android.content.Context;
import android.widget.EditText;

import ru.shadowsparky.scheduler.carolinescheduler.Fragments.AddScheduleBrainFragment;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

public interface IShowScheduleContract {
    interface ShowScheduleViewContract{
        void initFragment();
        void initControls();
        void showChooseLevelDialog();
        void showChooseDateDialog();
        void showChooseTimeDialog();
        void initToolbar();
        void enableLoading();
        void disableLoading();
        Context getContext();
        void updateDataRequest();
        AddScheduleBrainFragment get_fragment();
        SchedulesTable getElement();
        EditText getCaption();
    }
    interface ShowSchedulePresenterContract{
        void updateData();
    }
    interface ShowScheduleModelContract{
        void update(ICallbacks.ILoadCallback callback, SchedulesTable element, Context context);
    }
}
