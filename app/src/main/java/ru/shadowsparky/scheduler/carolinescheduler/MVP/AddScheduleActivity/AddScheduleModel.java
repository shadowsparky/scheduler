package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import android.content.ContentValues;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.InsertThread;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Scheduler_Database;

import static ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity.AddSchedulePresenter.CAPTION_INDEX;
import static ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity.AddSchedulePresenter.DATE_INDEX;
import static ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity.AddSchedulePresenter.HEADER_INDEX;
import static ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity.AddSchedulePresenter.LEVEL_INDEX;
import static ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity.AddSchedulePresenter.TIME_INDEX;

public class AddScheduleModel implements IAddSheduleContracts.IContractSheduleModel {

    public void addScheduleLogic(String[] data, Scheduler_Database db, ICallbacks.ILoadCallback callback) {
        ContentValues values = new ContentValues();
        values.put("Name", data[HEADER_INDEX]);
        values.put("Caption", data[CAPTION_INDEX]);
        values.put("Importance_Level", data[LEVEL_INDEX]);
        values.put("Date", data[DATE_INDEX]);
        values.put("Time", data[TIME_INDEX]);
        values.put("User_Email", "foo@bar.ru");
        InsertThread thread = new InsertThread(db, "Schedules", values, callback);
        thread.execute();
    }
}
