package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import android.content.Context;
import android.content.Intent;
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
import ru.shadowsparky.scheduler.carolinescheduler.Utils.Schedule_Element;

public class MainActivity extends AppCompatActivity implements IMainContracts.MainViewContract {

    @BindView(R.id.toolbar)
    Toolbar _toolbar;
    @BindView(R.id.MainList)
    ListView _list;
    @BindView(R.id.EmptyList)
    TextView _empty;

    public Context getContext() {
        return getApplicationContext();
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_toolbar);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        checkAdapter();
    }
    /* TODO: DELETE THIS SHEET PLEASE
    * PLEASE
    * PLEASE
    * PLEASE */
    private ArrayList<Schedule_Element> elements;

    private void checkAdapter(){
        elements = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            Schedule_Element el = new Schedule_Element();
            el.setSchedule_Description("test");
            el.setSchedule_Header("test" + i);
            el.setID(i + 500);
            elements.add(el);
        }
        setAdapter(elements);
    }
    /*------------------------------------------*/

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
    @Override
    public void setAdapter(ArrayList<Schedule_Element> elements) {
        MainListAdapter adapter = new MainListAdapter(this, elements);
        _list.setAdapter(adapter);
    }
    @OnItemClick(R.id.MainList)
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent i = new Intent(this, ShowScheduleView.class);
        i.putExtra("ID", elements.get(position).getID());
        startActivity(i);
    }
    @OnClick(R.id.fab)
    public void FloatActionBarClicked(View view){
        Intent i = new Intent(this, AddScheduleView.class);
        startActivity(i);
    }
}
