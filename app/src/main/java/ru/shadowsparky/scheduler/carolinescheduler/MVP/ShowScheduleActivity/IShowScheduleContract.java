package ru.shadowsparky.scheduler.carolinescheduler.MVP.ShowScheduleActivity;

import android.content.Context;

import ru.shadowsparky.scheduler.carolinescheduler.Fragments.AddScheduleBrainFragment;

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
    }
    interface ShowSchedulePresenterContract{
        void updateData();
    }
    interface ShowScheduleModelContract{
//        void updateBrain();
    }
}
