package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

public class AddSchedulePresenter implements IAddSheduleContracts.IContractShedulePresenter {
    private IAddSheduleContracts.IContractSheduleView view;
    private IAddSheduleContracts.IContractSheduleModel model;

    public AddSchedulePresenter(IAddSheduleContracts.IContractSheduleView view, IAddSheduleContracts.IContractSheduleModel model) {
        this.view = view;
        this.model = model;
    }
    public void addSchedule() {
        // TODO: add inputed values checker
        ICallbacks.ILoadCallback callback = (result) -> {
            if (!result){
                view.disableLoading();
            } else {
                view.enableLoading();
            }
        };
        SchedulesTable data = new SchedulesTable();
        data.setCaption(view.getCaption());
        data.setName(view.getHeader());
        data.setImportance_Level(view.getLevel());
        data.setDate(view.getDate());
        data.setTime(view.getTime());
        data.setEmail("foo@bar.com");
        model.addScheduleLogic(data, callback);
    }
}
