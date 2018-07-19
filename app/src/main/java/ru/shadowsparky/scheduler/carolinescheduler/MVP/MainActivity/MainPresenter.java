package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity.AddScheduleView;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.LoginActivity.Auth;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.ShowScheduleActivity.ShowScheduleView;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.DatabaseConfig;


public class MainPresenter implements IMainContracts.MainPresenterContract {
    IMainContracts.MainViewContract view;
    IMainContracts.MainModelContract _model;
    List<SchedulesTable> elements;

    public void initSwipe(RecyclerView _r){
        ItemTouchHelper _swipe = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT) {
            @Override public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }
            @Override public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                SchedulesTable cacheElement = elements.get(viewHolder.getLayoutPosition());
                Snackbar.make(viewHolder.itemView, "Вы удалили: " + cacheElement.getCaption(), Snackbar.LENGTH_SHORT).show();
                _model.deleteElement((result -> {
                    if (result)
                        view.enableRefreshing();
                    else
                        view.disableRefreshing();
                }), view.getContext(), cacheElement);
                view.onRefresh();
            }
        });
        _swipe.attachToRecyclerView(_r);
    }
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
            if (elements.size() == 0) {
                view.HideList();
            } else {
                view.setAdapter(elements);
                view.ShowList();
            }
    }
    // TODO: RENAME OR DELETE;
    public void showAddScheduleActivity() {
        Intent i = new Intent(view.getContext(), AddScheduleView.class);
        view.openActivity(i);
    }
    public void showViewScheduleActivity(int position) {
        Intent i = new Intent(view.getContext(), ShowScheduleView.class);
        i.putExtra("ID", position);
        view.openActivity(i);
    }
}
