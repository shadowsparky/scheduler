package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
//    @BindView(R.id.AddHeaderName_ET)
//    EditText _header;
//
//    @BindView(R.id.AddCaptionName_ET)
//    EditText _caption;
//
//    @BindView(R.id.ImportanceLevel)
//    EditText _level;
//
//    @BindView(R.id.AddDate_ET)
//    TextView date;
//
//    @BindView(R.id.AddTime_ET)
//    TextView time;

    private LoadingDialog dialog;
    private IAddSheduleContracts.IContractShedulePresenter presenter;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @BindView(R.id.RememberedET) EditText caption;
    @BindView(R.id.addDate) Button date;
    @BindView(R.id.addTime) Button time;
    @BindView(R.id.addImportanceLevel) Button level;
    @BindView(R.id.addScheduleButton) Button push;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        initToolbar();
        ButterKnife.bind(this);
        caption.setImeOptions(EditorInfo.IME_ACTION_DONE);
        caption.setRawInputType(InputType.TYPE_CLASS_TEXT);
        presenter = new AddSchedulePresenter(this, new AddScheduleModel(this));
    }
    @Override public void initToolbar() {
        Toolbar _toolbar = findViewById(R.id.testesttesttest);
        setTitle(getResources().getString(R.string.auth));
        _toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        setSupportActionBar(_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
    }
    @OnClick(R.id.addTime)
    @Override public void showChooseTimeDialog() {
        ICallbacks.IDialogCallback callback = result -> time.setText(result);
        ShowTimeDialog dialog = new ShowTimeDialog(this, callback);
        dialog.show();
    }
    @OnClick(R.id.addDate)
    @Override public void showChooseDateDialog() {
        ICallbacks.IDialogCallback callback = result -> date.setText(result);
        ShowDateDialog dialog = new ShowDateDialog(this, callback);
        dialog.show();
    }
    @OnClick(R.id.addImportanceLevel)
    @Override public void showChooseLevelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("testtest");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapter.addAll("Mission Critical", "Business Critical", "Non Critical");
        builder.setAdapter(adapter, ((dialog, which) -> level.setText(adapter.getItem(which))));
        builder.show();
    }
    @OnClick(R.id.addScheduleButton)
    @Override public void scheduleAddClick() {
        presenter.addSchedule();
    }
    @Override public String getCaption() {
        return caption.getText().toString();
    }
    @Override public String getDate() {
        return date.getText().toString();
    }
    @Override public String getTime() {
        return time.getText().toString();
    }
    @Override public String getLevel() {
        return level.getText().toString();
    }
    @Override public void enableLoading() {
        dialog = new LoadingDialog(this);
        dialog.show();
    }
    @Override public void disableLoading() {
        if (dialog != null)
            dialog.dismiss();
    }
    @Override public Context getContext() {
        return getApplicationContext();
    }
}