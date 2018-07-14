package ru.shadowsparky.scheduler.carolinescheduler.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import ru.shadowsparky.scheduler.carolinescheduler.R;

public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.loading);
        this.setCanceledOnTouchOutside(false);
    }
}
