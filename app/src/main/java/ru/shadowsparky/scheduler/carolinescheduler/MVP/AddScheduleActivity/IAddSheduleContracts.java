package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import android.content.Context;
import android.view.View;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

public interface IAddSheduleContracts {
    interface IContractSheduleView{
        String getCaption();
        String getDate();
        String getTime();
        String getLevel();
        void enableLoading();
        void disableLoading();
        Context getContext();
        void initToolbar();
        void showChooseTimeDialog();
        void showChooseDateDialog();
        void showChooseLevelDialog();
        void scheduleAddClick();
    }
    interface IContractShedulePresenter {
        void addSchedule();
    }
    interface IContractSheduleModel{
        void addScheduleLogic(SchedulesTable data, ICallbacks.ILoadCallback callback);
    }
}
