package ru.shadowsparky.scheduler.carolinescheduler.Interfaces;

import java.util.List;

import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

public interface ICallbacks {
    @FunctionalInterface
    interface ILoadCallback {
        void setLoading(boolean result);
    }
    @FunctionalInterface
    interface IDialogCallback {
        void getResult(String result);
    }
    @FunctionalInterface
    interface ISelectingCallback{
        void getElement(SchedulesTable element);
    }
}
