package ru.shadowsparky.scheduler.carolinescheduler.MVP.LoginActivity;

import ru.shadowsparky.scheduler.carolinescheduler.Exceptions.AuthDataNotFoundException;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;

import static ru.shadowsparky.scheduler.carolinescheduler.MVP.LoginActivity.SchedulerModel.AUTH_DATA_SIZE;
import static ru.shadowsparky.scheduler.carolinescheduler.MVP.LoginActivity.SchedulerModel.LOGIN_INDEX;
import static ru.shadowsparky.scheduler.carolinescheduler.MVP.LoginActivity.SchedulerModel.PASSWORD_INDEX;

public class SchedulerPresenter implements ILoginContracts.ContractPresenter_Auth {
    public static final int REMEMBERED_AUTH = 1;
    private ILoginContracts.ContractView_Auth _viewAuth;
    private ILoginContracts.ContractModel_Auth _model;

    // Auth Init
    public SchedulerPresenter(ILoginContracts.ContractView_Auth view, ILoginContracts.ContractModel_Auth _model) {
        this._viewAuth = view;
        this._model = _model;
    }

    @Override
    public boolean rememberedAuth(){
        String[] accountData;
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
        if (control != REMEMBERED_AUTH)
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
            if (control != REMEMBERED_AUTH)
                _viewAuth.hideLoading();
        };
        // auth
        if ((!accountData[LOGIN_INDEX].equals("")) && (!accountData[PASSWORD_INDEX].equals("")))
            _model.auth(accountData[LOGIN_INDEX], accountData[PASSWORD_INDEX], callback);
    }

    @Override
    public void regClicked() {
    }
}