package ru.shadowsparky.scheduler.carolinescheduler.MVP.ShowScheduleActivity;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

public class ShowSchedulePresenter implements IShowScheduleContract.ShowSchedulePresenterContract {
    IShowScheduleContract.ShowScheduleViewContract _view;
    IShowScheduleContract.ShowScheduleModelContract _model;

    public ShowSchedulePresenter(IShowScheduleContract.ShowScheduleViewContract _view, IShowScheduleContract.ShowScheduleModelContract _model) {
        this._view = _view;
        this._model = _model;
    }

    @Override public void updateData() {
        ICallbacks.ILoadCallback callback = (result)->{
            if (result)
                _view.enableLoading();
            else
                _view.disableLoading();
        };
        SchedulesTable updatedElement = _view.getElement();
        updatedElement.setCaption(_view.getCaption().getText().toString());
        updatedElement.setImportance_Level(_view.get_fragment().getLevel().getText().toString());
        updatedElement.setDate(_view.get_fragment().getDate().getText().toString());
        updatedElement.setTime(_view.get_fragment().getTime().getText().toString());
        _model.update(callback, updatedElement, _view.getContext());
    }
}
