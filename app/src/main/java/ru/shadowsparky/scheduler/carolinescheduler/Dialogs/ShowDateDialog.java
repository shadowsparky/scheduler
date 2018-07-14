package ru.shadowsparky.scheduler.carolinescheduler.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.CalendarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.R;

public class ShowDateDialog extends Dialog implements CalendarView.OnDateChangeListener{
    @BindView(R.id.dialogOkButton)
    Button yesButton;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    ICallbacks.IDialogCallback callback;
    String ChoosedDate;

    public ShowDateDialog(@NonNull Context context, ICallbacks.IDialogCallback callback) {
        super(context);
        setContentView(R.layout.date_dialog);
        ButterKnife.bind(this);
        initDate();
        yesButton.setOnClickListener((result)-> {
            this.hide();
            callback.getResult(ChoosedDate);
        });
        this.callback = callback;
        calendarView.setOnDateChangeListener(this);
    }
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
        ChoosedDate = String.valueOf(dayOfMonth) + "." + String.valueOf(month+1) + "." + String.valueOf(year);
    }
    private void initDate(){
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        ChoosedDate = df.format(Calendar.getInstance().getTime());
    }
}
