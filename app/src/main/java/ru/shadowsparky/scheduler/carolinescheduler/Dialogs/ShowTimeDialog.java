package ru.shadowsparky.scheduler.carolinescheduler.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.R;

public class ShowTimeDialog extends Dialog {
    @BindView(R.id.TimePicker)
    TimePicker tp;
    ICallbacks.IDialogCallback callback;

    @BindView(R.id.timeDialogOkButton)
    Button _ok;


    public ShowTimeDialog(@NonNull Context context, ICallbacks.IDialogCallback callback) {
        super(context);
        this.callback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_dialog);
        ButterKnife.bind(this);
        tp.setIs24HourView(true);
        _ok.setOnClickListener((view)->{
            String currentTime = String.valueOf(tp.getCurrentHour()) + ":" + String.valueOf(tp.getCurrentMinute());
            callback.getResult(currentTime);
            this.hide();
        });
    }
}
