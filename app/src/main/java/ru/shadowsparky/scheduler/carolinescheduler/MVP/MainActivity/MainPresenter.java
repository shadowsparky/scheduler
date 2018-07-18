package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity.AddScheduleView;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.ShowScheduleActivity.ShowScheduleView;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;


public class MainPresenter implements IMainContracts.MainPresenterContract {
    IMainContracts.MainViewContract view;
    IMainContracts.MainModelContract _model;
    List<SchedulesTable> elements;

    public MainPresenter(IMainContracts.MainViewContract view, IMainContracts.MainModelContract model) {
        this.view = view;
        _model = model;
    }

    public void getDataToList(){
        ICallbacks.ILoadCallback callback = (result) ->{
            if (result)
                view.enableRefreshing();
            else
                view.disableRefreshing();
        };
        elements = _model.getElements(callback, view.getContext());
        if (elements != null)
            view.setAdapter(elements);
    }
    public void showAddScheduleActivity() {
        Intent i = new Intent(view.getContext(), AddScheduleView.class);
        view.openActivity(i);
    }
    public void showViewScheduleActivity(int position) {
        Intent i = new Intent(view.getContext(), ShowScheduleView.class);
        i.putExtra("ID", elements.get(position).getSchedule_ID());
        view.openActivity(i);
    }
}
