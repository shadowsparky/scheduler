package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import ru.shadowsparky.scheduler.carolinescheduler.Adapters.MainListAdapter;
import ru.shadowsparky.scheduler.carolinescheduler.Adapters.MainRVAdapter;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity.AddScheduleView;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.ShowScheduleActivity.ShowScheduleView;
import ru.shadowsparky.scheduler.carolinescheduler.R;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.DatabaseConfig;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.Schedule_Element;

public class MainActivity extends AppCompatActivity implements IMainContracts.MainViewContract, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar _toolbar;
    @BindView(R.id.RV_List)
    RecyclerView _list;
    @BindView(R.id.EmptyList)
    TextView _empty;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout _refreshList;
    private IMainContracts.MainPresenterContract _presenter;

    @Override public Context getContext() {
        return getApplicationContext();
    }
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_toolbar);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        _refreshList.setOnRefreshListener(this);
        _presenter = new MainPresenter(this, new MainModel());
        onRefresh();
        _presenter.initSwipe(_list);
        test();
    }
    @Override public void setToolbar() {
        setSupportActionBar(_toolbar);
    }
    @Override public void ShowList() {
        _list.setVisibility(View.VISIBLE);
        _empty.setVisibility(View.INVISIBLE);
    }
    @Override public void HideList() {
        _list.setVisibility(View.INVISIBLE);
        _empty.setVisibility(View.VISIBLE);
    }
    @Override public void enableRefreshing() {
        _refreshList.setRefreshing(true);
    }
    @Override public void disableRefreshing() {
        _refreshList.setRefreshing(false);
    }
    private PublishSubject<SchedulesTable> _subject = PublishSubject.create();
    private void test(){
        DatabaseConfig.LOG("SUBSCRIBE INITIALIZE");
        _subject = PublishSubject.create();
        _subject.subscribe(view-> {_presenter.showViewScheduleActivity(view.getSchedule_ID());
            DatabaseConfig.LOG("Activity open");});
    }

    @Override public void setAdapter(List<SchedulesTable> elements) {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        _list.setLayoutManager(llm);
        _list.setHasFixedSize(false);
        MainRVAdapter adapter = new MainRVAdapter(elements, getContext(), _subject);
        elements = adapter.getData();
        _list.setAdapter(adapter);
    }
    @Override public void openActivity(Intent intent) {
        startActivity(intent);
    }
    @OnClick(R.id.fab)
    @Override public void FloatActionBarClicked(View view){
        _presenter.showAddScheduleActivity();
    }
    @Override protected void onPostResume() {
        super.onPostResume();
        onRefresh();
    }
    @Override public void onRefresh() {
        _presenter.getDataToList();
    }
}
