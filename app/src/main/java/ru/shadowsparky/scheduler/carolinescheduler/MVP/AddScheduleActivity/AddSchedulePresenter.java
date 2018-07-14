package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Scheduler_Database;

public class AddSchedulePresenter implements IAddSheduleContracts.IContractShedulePresenter {
    public static final int HEADER_INDEX = 0;
    public static final int CAPTION_INDEX = 1;
    public static final int LEVEL_INDEX = 2;
    public static final int DATE_INDEX = 3;
    public static final int TIME_INDEX = 4;
    private IAddSheduleContracts.IContractSheduleView view;
    private IAddSheduleContracts.IContractSheduleModel model;

    public AddSchedulePresenter(IAddSheduleContracts.IContractSheduleView view, IAddSheduleContracts.IContractSheduleModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void addSchedule() {
        // TODO: add inputed values checker
        ICallbacks.ILoadCallback callback = (result) -> {
            if (result){
                view.disableLoading();
            } else {
                view.enableLoading();
            }
        };
        Scheduler_Database db = new Scheduler_Database(view.getContext());
        String[] data = {view.getHeader(), view.getCaption(), view.getLevel(), view.getDate(), view.getTime()};
        model.addScheduleLogic(data, db, callback);
    }
}
