package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.shadowsparky.scheduler.carolinescheduler.Dialogs.ShowDateDialog;
import ru.shadowsparky.scheduler.carolinescheduler.Dialogs.ShowTimeDialog;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.R;

public class AddScheduleView extends AppCompatActivity {
    @BindView(R.id.AddDate_ET)
    TextView date;
    @BindView(R.id.AddTime_ET)
    TextView time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.AddDate_ET)
    public void selectDate(){
        ICallbacks.IDialogCallback callback = (result) -> {
            date.setText(result);
        };
        ShowDateDialog dialog = new ShowDateDialog(this, callback);
        dialog.show();
    }
    @OnClick(R.id.AddTime_ET)
    public void selectTime(){
        ICallbacks.IDialogCallback callback = (result)->{
            time.setText(result);
        };
        ShowTimeDialog dialog = new ShowTimeDialog(this, callback);
        dialog.show();
    }
}
