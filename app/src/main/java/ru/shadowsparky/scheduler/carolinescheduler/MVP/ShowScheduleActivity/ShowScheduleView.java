package ru.shadowsparky.scheduler.carolinescheduler.MVP.ShowScheduleActivity;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import ru.shadowsparky.scheduler.carolinescheduler.Dialogs.LoadingDialog;
import ru.shadowsparky.scheduler.carolinescheduler.Dialogs.ShowDateDialog;
import ru.shadowsparky.scheduler.carolinescheduler.Dialogs.ShowTimeDialog;
import ru.shadowsparky.scheduler.carolinescheduler.Fragments.AddScheduleBrainFragment;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity.AddScheduleView;
import ru.shadowsparky.scheduler.carolinescheduler.R;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.DatabaseConfig;

public class ShowScheduleView extends AppCompatActivity implements IShowScheduleContract.ShowScheduleViewContract {
    SchedulesTable element;
    AddScheduleBrainFragment _fragment;
    @BindView(R.id.showScheduleInputCaption)
    EditText caption;
    private LoadingDialog dialog;
    IShowScheduleContract.ShowSchedulePresenterContract _presenter;

    public SchedulesTable getElement() {
        return element;
    }
    public EditText getCaption() {
        return caption;
    }
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_schedule);
        element = (SchedulesTable) getIntent().getSerializableExtra("Element");
        ButterKnife.bind(this);
        initFragment();
        initToolbar();
        _presenter = new ShowSchedulePresenter(this, new ShowScheduleModel());
    }
    @Override public void initFragment(){
        DatabaseConfig.LOG("FRAGMENT BEFORE INITIALIZE");
        _fragment = new AddScheduleBrainFragment();
        PublishSubject<Integer> _subject = PublishSubject.create();
        _subject.subscribe((i)->initControls());
        _fragment.setView(this, _subject);
        DatabaseConfig.LOG("FRAGMENT INITIALIZED");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.showScheduleContainer, _fragment);
        ft.commit();
    }
    @Override public void initControls(){
        DatabaseConfig.LOG("VOID INIT CONTROLS");
        caption.setText(element.getCaption());
        _fragment.getDate().setText(element.getDate());
        _fragment.getLevel().setText(element.getImportance_Level());
        _fragment.getTime().setText(element.getTime());
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
    @Override public void initToolbar() {
        Toolbar _toolbar = findViewById(R.id.showScheduleToolbar);
        setTitle("");
        _toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        setSupportActionBar(_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
    }
    @Override public void showChooseTimeDialog() {
        ICallbacks.IDialogCallback callback = result -> _fragment.getTime().setText(result);
        ShowTimeDialog dialog = new ShowTimeDialog(this, callback);
        dialog.show();
    }
    @Override public void showChooseDateDialog() {
        ICallbacks.IDialogCallback callback = result -> _fragment.getDate().setText(result);
        ShowDateDialog dialog = new ShowDateDialog(this, callback);
        dialog.show();
    }
    @Override public void showChooseLevelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(AddScheduleView.LEVEL_DIALOG_HEADER);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapter.addAll(AddScheduleView.LEVELS_LIST);
        builder.setAdapter(adapter, ((dialog, which) -> _fragment.getLevel().setText(adapter.getItem(which))));
        builder.show();
    }
    @Override public void enableLoading() {
        dialog = new LoadingDialog(this);
        dialog.show();
    }
    @Override public void disableLoading() {
        if (dialog != null);
            dialog.dismiss();
    }
    @Override public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void updateDataRequest() {
        _presenter.updateData();
        finish();
    }

    @Override public AddScheduleBrainFragment get_fragment() {
        return _fragment;
    }
    MenuItem menuItem;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_schedule, menu);
        menuItem = menu.findItem(R.id.showSchedulePushUpdate);
        menuItem.setOnMenuItemClickListener((view)->{
            updateDataRequest();
            return true;
        });
        return true;
    }

    // Update data Model;
    // Add menus;
}