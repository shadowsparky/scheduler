package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
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
import ru.shadowsparky.scheduler.carolinescheduler.MVP.LoginActivity.Auth;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.ShowScheduleActivity.ShowScheduleView;
import ru.shadowsparky.scheduler.carolinescheduler.R;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.DatabaseConfig;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.Schedule_Element;

import static ru.shadowsparky.scheduler.carolinescheduler.MVP.LoginActivity.Auth.ACCOUNT_DATA;

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
    private PublishSubject<SchedulesTable> _subject = PublishSubject.create();
    @Override public Context getContext() {
        return getApplicationContext();
    }
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_toolbar);
        setContentView(R.layout.activity_main);
        setTitle(getResources().getString(R.string.ShowSchedules));
        ButterKnife.bind(this);
        setToolbar();
        _refreshList.setOnRefreshListener(this);
        _presenter = new MainPresenter(this, new MainModel());
        onRefresh();
        _presenter.initSwipe(_list);
        subjectInit();
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
    @Override public void subjectInit(){
        DatabaseConfig.LOG("SUBSCRIBE INITIALIZE");
        _subject = PublishSubject.create();
        _subject.subscribe(view-> {_presenter.showViewScheduleActivity(view);
            DatabaseConfig.LOG("Activity open");});
    }
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drop_account_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.exititem);
        SpannableString s = new SpannableString(menuItem.getTitle());
        s.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), android.R.color.black)), 0, s.length(), 0);
        menuItem.setTitle(s);
        menuItem.setOnMenuItemClickListener((view)->{
            SharedPreferences sp = getSharedPreferences(ACCOUNT_DATA, MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent i = new Intent(this, Auth.class);
            startActivity(i);
            finish();
            return true;
        });
        return true;
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
