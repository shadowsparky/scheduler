package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

public class MainPresenter implements IMainContracts.MainPresenterContract {
    IMainContracts.MainViewContract view;
    MainModel mainModel;

    public MainPresenter(IMainContracts.MainViewContract view) {
        this.view = view;
        mainModel = new MainModel();
    }
}
