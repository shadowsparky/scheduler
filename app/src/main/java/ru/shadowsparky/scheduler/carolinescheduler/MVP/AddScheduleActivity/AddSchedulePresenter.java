package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

public class AddSchedulePresenter implements IAddScheduleContracts.IContractShedulePresenter {
    private IAddScheduleContracts.IContractSheduleView view;
    private IAddScheduleContracts.IContractSheduleModel model;

    public AddSchedulePresenter(IAddScheduleContracts.IContractSheduleView view, IAddScheduleContracts.IContractSheduleModel model) {
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
        data.setImportance_Level(view.get_fragment().getLevel().getText().toString());
        data.setDate(view.get_fragment().getDate().getText().toString());
        data.setTime(view.get_fragment().getTime().getText().toString());
        data.setEmail("foo@bar.com");
        model.addScheduleLogic(data, callback);
    }
}
