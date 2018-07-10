package ru.shadowsparky.scheduler.carolinescheduler.MVP;

import android.support.design.widget.Snackbar;

import ru.shadowsparky.scheduler.carolinescheduler.Exceptions.AuthDataNotFoundException;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.IContracts;

import static ru.shadowsparky.scheduler.carolinescheduler.MVP.SchedulerModel.AUTH_DATA_SIZE;
import static ru.shadowsparky.scheduler.carolinescheduler.MVP.SchedulerModel.LOGIN_INDEX;
import static ru.shadowsparky.scheduler.carolinescheduler.MVP.SchedulerModel.PASSWORD_INDEX;

public class SchedulerPresenter implements IContracts.ContractPresenter {
    public static final int REMEMBERED_AUTH = 1;
    private IContracts.ContractView_Auth _viewAuth;
    private SchedulerModel _model;

    // Auth Init
    public SchedulerPresenter(IContracts.ContractView_Auth view) {
        this._viewAuth = view;
        this._model = new SchedulerModel();
    }
    public SchedulerPresenter(){

    }

    @Override
    public boolean rememberedAuth(){
        String[] accountData = new String[AUTH_DATA_SIZE];
        try {
            accountData = _model.loadPrivateData(_viewAuth.getSharedPref());
        } catch (AuthDataNotFoundException e) {
            return false;
        }
        authClicked(accountData, REMEMBERED_AUTH);
        return true;
    }
    @Override
    public void authClicked(final String[] accountData, int control){
        _viewAuth.showLoading();
        SchedulerModel.IAuthCallback callback = (result) -> {
            if (result){
                if (control != REMEMBERED_AUTH)
                    if (_viewAuth.getCheckboxStatus()){
                        _model.savePrivateData(_viewAuth.getSharedPref(), accountData[LOGIN_INDEX], accountData[PASSWORD_INDEX]);
                    }
                _viewAuth.goMainActivity();
            }
            else
                _viewAuth.raiseAuthError();
        };
        if ((!accountData[LOGIN_INDEX].equals("")) && (!accountData[PASSWORD_INDEX].equals("")))
            _model.auth(accountData[LOGIN_INDEX], accountData[PASSWORD_INDEX], callback);
        _viewAuth.hideLoading();
    }
    @Override
    public void regClicked() {
    }
}