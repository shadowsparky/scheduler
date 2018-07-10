package ru.shadowsparky.scheduler.carolinescheduler.Interfaces;


import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;

import ru.shadowsparky.scheduler.carolinescheduler.Exceptions.AuthDataNotFoundException;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.SchedulerModel;

public interface IContracts {
    interface ContractView_Auth {
        void init();
        void onAuthButtonClick();
        void onRegButtonClick();
        void showLoading();
        void hideLoading();
        void goMainActivity();
        boolean getCheckboxStatus();
        String[] getAccountData();
        SharedPreferences getSharedPref();
        void raiseAuthError();
    }
    interface ContractModel{
        void savePrivateData(SharedPreferences sp, String _login, String _password) throws InternalError;
        String[] loadPrivateData(SharedPreferences sp) throws AuthDataNotFoundException;
        void auth(String login, String password, SchedulerModel.IAuthCallback callback);
    }
    interface ContractPresenter {
        void authClicked(String[] accountData, int control);
        void regClicked();
        boolean rememberedAuth();
    }
}
