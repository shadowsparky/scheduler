package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.shadowsparky.scheduler.carolinescheduler.Dialogs.LoadingDialog;
import ru.shadowsparky.scheduler.carolinescheduler.Dialogs.ShowDateDialog;
import ru.shadowsparky.scheduler.carolinescheduler.Dialogs.ShowTimeDialog;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.R;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.DatabaseConfig;

public class AddScheduleView extends AppCompatActivity implements IAddScheduleContracts.IContractSheduleView, Serializable {
    public static final String[] LEVELS_LIST = {"Mission Critical", "Business Critical", "Non Critical"};
    public static final String LEVEL_DIALOG_HEADER = "Выберите уровень важности";
    private LoadingDialog dialog;
    private IAddScheduleContracts.IContractShedulePresenter presenter;
    @BindView(R.id.RememberedET) EditText caption;
    AddScheduleBrainFragment _fragment;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        ButterKnife.bind(this);
        initCaptionListeners();
        initFragment();
        initToolbar();
        presenter = new AddSchedulePresenter(this, new AddScheduleModel(this));
    }
    @Override public AddScheduleBrainFragment get_fragment() {
        return _fragment;
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
    MenuItem menuItem;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_schedule, menu);
        menuItem = menu.findItem(R.id.addScheduleButton_test);
        menuItem.setOnMenuItemClickListener((view)->{
            scheduleAddClick();
            return true;
        });
        menuItem.setVisible(false);
        return true;
    }

    @Override public void initCaptionListeners(){
        caption.setOnEditorActionListener((v, id, event)->{
            if (id == EditorInfo.IME_ACTION_DONE){
                showFragment();
                DatabaseConfig.LOG("Fragment show");
                menuItem.setVisible(true);
            }
            return true;
        });
        caption.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                hideFragment();
                menuItem.setVisible(false);
                DatabaseConfig.LOG("Fragment hide");
            }
            @Override public void afterTextChanged(Editable editable) {

            }
        });
        caption.setImeOptions(EditorInfo.IME_ACTION_DONE);
        caption.setRawInputType(InputType.TYPE_CLASS_TEXT);
    }
    @Override public void initFragment(){
        _fragment = new AddScheduleBrainFragment();
        _fragment.setView(this);
    }
    @Override public void showFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.addScheduleContainer, _fragment);
        transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        transaction.commit();
    }
    @Override public void hideFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.remove(_fragment);
        transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        transaction.commit();
    }
    @Override public void initToolbar() {
        Toolbar _toolbar = findViewById(R.id.testesttesttest);
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
        builder.setTitle(LEVEL_DIALOG_HEADER);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        adapter.addAll(LEVELS_LIST);
        builder.setAdapter(adapter, ((dialog, which) -> _fragment.getLevel().setText(adapter.getItem(which))));
        builder.show();
    }
    @Override public void scheduleAddClick() {
        presenter.addSchedule();
        finish();
    }
    @Override public String getCaption() {
        return caption.getText().toString();
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