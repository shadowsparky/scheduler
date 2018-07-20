package ru.shadowsparky.scheduler.carolinescheduler.MVP.LoginActivity;

import android.content.SharedPreferences;

import ru.shadowsparky.scheduler.carolinescheduler.Exceptions.AuthDataNotFoundException;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ICallbacks;

public interface ILoginContracts {
    String LOGIN_TAG = "Login";
    String PASSWORD_TAG = "Password";
    interface ContractView_Auth {
        void onAuthButtonClick();
        void onRegButtonClick();
        void goMainActivity();
        @Deprecated
        boolean getCheckboxStatus();
        String[] getAccountData();
        SharedPreferences getSharedPref();
        void raiseAuthError();
        void showLoading();
        void hideLoading();
    }
    interface ContractModel_Auth{
        void savePrivateData(SharedPreferences sp, String _login, String _password) throws InternalError;
        String[] loadPrivateData(SharedPreferences sp) throws AuthDataNotFoundException;
        void auth(String login, String password,
                  SchedulerModel.IAuthCallback callback
        );
    }
    interface ContractPresenter_Auth {
        void authClicked(String[] accountData, int control);
        void regClicked();
        boolean rememberedAuth();
    }
}
