package ru.shadowsparky.scheduler.carolinescheduler.Interfaces;

public interface IAuth {
    void raiseAuthError();
    void goToMainActivity();
    void auth(String login, String password, int AuthControl);
    boolean tryAuth();
    void initListeners();
}
