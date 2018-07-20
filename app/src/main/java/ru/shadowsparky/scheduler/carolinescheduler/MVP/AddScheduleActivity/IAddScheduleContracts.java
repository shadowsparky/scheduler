package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import android.content.Context;

import ru.shadowsparky.scheduler.carolinescheduler.Fragments.AddScheduleBrainFragment;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

public interface IAddScheduleContracts {
    interface IContractSheduleView{
        String getCaption();
        AddScheduleBrainFragment get_fragment();
        void enableLoading();
        void disableLoading();
        Context getContext();
        void initToolbar();
        void showChooseTimeDialog();
        void showChooseDateDialog();
        void showChooseLevelDialog();
        void scheduleAddClick();
        void showFragment();
        void hideFragment();
        void initFragment();
        void initCaptionListeners();
    }
    interface IContractShedulePresenter {
        void addSchedule();
    }
    interface IContractSheduleModel{
        void addScheduleLogic(SchedulesTable data, ICallbacks.ILoadCallback callback);
    }
}
