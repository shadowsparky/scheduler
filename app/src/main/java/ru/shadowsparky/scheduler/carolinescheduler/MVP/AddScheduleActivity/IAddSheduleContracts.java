package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import android.content.Context;
import android.view.View;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

public interface IAddSheduleContracts {
    interface IContractSheduleView{
        String getHeader();
        String getCaption();
        String getDate();
        String getTime();
        String getLevel();
        void pushShedule(View view);
        void selectTime();
        void selectDate();
        void enableLoading();
        void disableLoading();
        Context getContext();
    }
    interface IContractShedulePresenter {
        void addSchedule();
    }
    interface IContractSheduleModel{
        void addScheduleLogic(SchedulesTable data, ICallbacks.ILoadCallback callback);
    }
}
