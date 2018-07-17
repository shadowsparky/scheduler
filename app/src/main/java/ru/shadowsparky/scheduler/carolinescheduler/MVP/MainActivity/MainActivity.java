package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import ru.shadowsparky.scheduler.carolinescheduler.Adapters.MainListAdapter;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity.AddScheduleView;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.ShowScheduleActivity.ShowScheduleView;
import ru.shadowsparky.scheduler.carolinescheduler.R;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.Schedule_Element;

public class MainActivity extends AppCompatActivity implements IMainContracts.MainViewContract, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar _toolbar;
    @BindView(R.id.MainList)
    ListView _list;
    @BindView(R.id.EmptyList)
    TextView _empty;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout _refreshList;
    private IMainContracts.MainPresenterContract _presenter;

    public Context getContext() {
        return getApplicationContext();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_toolbar);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        _refreshList.setOnRefreshListener(this);
        _presenter = new MainPresenter(this, new MainModel());
        onRefresh();
    }

    public void setToolbar() {
        setSupportActionBar(_toolbar);
    }
    public void ShowList() {
        _list.setVisibility(View.VISIBLE);
        _empty.setVisibility(View.INVISIBLE);
    }
    public void HideList() {
        _list.setVisibility(View.INVISIBLE);
        _empty.setVisibility(View.VISIBLE);
    }
    public void enableRefreshing() {
        _refreshList.setRefreshing(true);
    }
    public void disableRefreshing() {
        _refreshList.setRefreshing(false);
    }
    public void setAdapter(ArrayList<SchedulesTable> elements) {
        MainListAdapter adapter = new MainListAdapter(this, elements);
        _list.setAdapter(adapter);
    }
    public void openActivity(Intent intent) {
        startActivity(intent);
    }

    @OnItemClick(R.id.MainList)
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        _presenter.showViewScheduleActivity(position);
    }
    @OnClick(R.id.fab)
    public void FloatActionBarClicked(View view){
        _presenter.showAddScheduleActivity();
    }
    public void onRefresh() {
        Runnable run = ()->
            _presenter.getDataToList();
        new Thread(run).start();
    }
}
