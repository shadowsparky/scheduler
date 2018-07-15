package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.shadowsparky.scheduler.carolinescheduler.Dialogs.LoadingDialog;
import ru.shadowsparky.scheduler.carolinescheduler.Dialogs.ShowDateDialog;
import ru.shadowsparky.scheduler.carolinescheduler.Dialogs.ShowTimeDialog;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.R;

public class AddScheduleView extends AppCompatActivity implements IAddSheduleContracts.IContractSheduleView {
    @BindView(R.id.AddHeaderName_ET)
    EditText _header;

    @BindView(R.id.AddCaptionName_ET)
    EditText _caption;

    @BindView(R.id.ImportanceLevel)
    EditText _level;

    @BindView(R.id.AddDate_ET)
    TextView date;

    @BindView(R.id.AddTime_ET)
    TextView time;

    private LoadingDialog dialog;
    private IAddSheduleContracts.IContractShedulePresenter presenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        presenter = new AddSchedulePresenter(this, new AddScheduleModel());
        dialog = new LoadingDialog(this);
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
    public void enableLoading() {
        dialog.show();
    }
    public void disableLoading() {
        dialog.dismiss();
    }
    public Context getContext() {
        return this;
    }
    @OnClick(R.id.AddTime_ET)
    public void selectTime(){
        ICallbacks.IDialogCallback callback = (result)->{
            time.setText(result);
        };
        ShowTimeDialog dialog = new ShowTimeDialog(this, callback);
        dialog.show();
    }
    @OnClick(R.id.pushschedule)
    public void pushShedule(View view){
        presenter.addSchedule();
    }
    public String getHeader() {
        return _header.getText().toString();
    }
    public String getCaption() {
        return _caption.getText().toString();
    }
    public String getDate() {
        return date.getText().toString();
    }
    public String getTime() {
        return time.getText().toString();
    }
    public String getLevel() {
        return _level.getText().toString();
    }
}
