package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import java.util.ArrayList;

import ru.shadowsparky.scheduler.carolinescheduler.Utils.Schedule_Element;

public interface IMainContracts {

    interface MainViewContract{
        void setToolbar();
        void ShowList();
        void HideList();
        void setAdapter(ArrayList<Schedule_Element> elements);
    }
    interface MainPresenterContract{

    }
    interface MainModelContract{

    }
}
