package ru.shadowsparky.scheduler.carolinescheduler.Interfaces;

import android.app.ProgressDialog;

public interface ILoad {
    void disableLoading(final ProgressDialog pd);
    void enableLoading(final ProgressDialog pd);
}
