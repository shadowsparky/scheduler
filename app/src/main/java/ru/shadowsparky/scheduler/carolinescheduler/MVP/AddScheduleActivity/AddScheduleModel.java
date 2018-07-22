package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import android.content.Context;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.NotificationsBrain.ScheduleNotification;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Threads.InsertThread;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

public class AddScheduleModel implements IAddScheduleContracts.IContractSheduleModel {
    private Context context;

    public AddScheduleModel(Context context) {
        this.context = context;
    }

    public void addScheduleLogic(SchedulesTable data, ICallbacks.ILoadCallback callback) {
        InsertThread thread = new InsertThread(callback, context);
        thread.execute(data);
        ScheduleNotify(data);
    }

    public void ScheduleNotify(SchedulesTable data) {
        ScheduleNotification sn = new ScheduleNotification(context);
        sn.scheduleNotification(sn.getNotification(data.getCaption()), 1, data.getSchedule_ID());
    }
}