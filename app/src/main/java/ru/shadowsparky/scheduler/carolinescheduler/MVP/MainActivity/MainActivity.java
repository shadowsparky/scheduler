package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.SortedList;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.InputStream;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import ru.shadowsparky.scheduler.carolinescheduler.Adapters.MainListAdapter;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.ChoosedItemFragment.ChoosedItem;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.NewScheduleFragment.NewSchedule;
import ru.shadowsparky.scheduler.carolinescheduler.R;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Scheduler_Database;

public class MainActivity extends AppCompatActivity implements IMainContracts.MainViewContract {

    @BindView(R.id.toolbar)
    Toolbar _toolbar;
    private MainFragment _fragment;
    public Context getContext() {
        return getApplicationContext();
    }
    public void itemClick(int position) {
        itemFragmentLoad();
    }
    public void itemFragmentLoad() {
        Fragment ci = new ChoosedItem();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        ft.replace(R.id.fragment_Container, ci);
        ft.addToBackStack(null);
        ft.commit();
    }
    public void setMainListAdapter(MainListAdapter adapter) {
        _fragment.setAdapter(adapter);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_toolbar);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        _fragment = new MainFragment();
        ft.replace(R.id.fragment_Container, _fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    public void setToolbar() {
        setSupportActionBar(_toolbar);
    }
}
