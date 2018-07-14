package ru.shadowsparky.scheduler.carolinescheduler.Interfaces;

public interface ICallbacks {
    interface ILoadCallback {
        void setLoading(boolean result);
    }
    @FunctionalInterface
    interface IDialogCallback {
        void getResult(String result);
    }
}
