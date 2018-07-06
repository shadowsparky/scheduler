package ru.shadowsparky.scheduler.carolinescheduler.Interfaces;

import ru.shadowsparky.scheduler.carolinescheduler.Exceptions.AuthDataNotFoundException;

public interface IAuthData {
    String LOGIN_TAG = "Login";
    String PASSWORD_TAG = "Password";
    void savePreference(String Login, String Password);
    String[] loadPreference() throws AuthDataNotFoundException;
}
