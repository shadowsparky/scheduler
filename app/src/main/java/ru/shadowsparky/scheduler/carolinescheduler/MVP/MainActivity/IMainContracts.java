package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.DAO.ScheduleDao;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.Schedule_Element;

public interface IMainContracts {

    interface MainViewContract{
        void setToolbar();
        void ShowList();
        void HideList();
        void enableRefreshing();
        void disableRefreshing();
        Context getContext();
        void setAdapter(List<SchedulesTable> elements);
        void openActivity(Intent intent);
        void onRefresh();
        void FloatActionBarClicked(View view);
        void subjectInit();

    }
    interface MainPresenterContract{
        void getDataToList();
        void showAddScheduleActivity();
        void showViewScheduleActivity(SchedulesTable element);
        void initSwipe(RecyclerView _r);
    }
    interface MainModelContract{
        List<SchedulesTable> getElements(ICallbacks.ILoadCallback callback, Context context);
        void deleteElement(ICallbacks.ILoadCallback callback, Context context, SchedulesTable element);
        ScheduleDao qryinit(Context _context);
    }
}
