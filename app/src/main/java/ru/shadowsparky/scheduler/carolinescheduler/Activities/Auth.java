package ru.shadowsparky.scheduler.carolinescheduler.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.TimeUnit;

import ru.shadowsparky.scheduler.carolinescheduler.Exceptions.AuthDataNotFoundException;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.IAuth;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.IAuthData;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.IContracts;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ILoad;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.SchedulerPresenter;
import ru.shadowsparky.scheduler.carolinescheduler.R;

public class Auth extends AppCompatActivity implements IContracts.ContractView_Auth {
    public static final String ACCOUNT_DATA = "ACCOUNT_DATA";
    public static final int FIRST_AUTH = 0;
    private Button _authButton;
    private Button _regButton;
    private ProgressDialog _progressDialog;
    private IContracts.ContractPresenter _presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new SchedulerPresenter(this);
        if (!_presenter.rememberedAuth()) {
            setTheme(R.style.AppThemeWithStatusBarColor);
            setContentView(R.layout.activity_auth);
            init();
        }
    }

    @Override
    public void init(){
        _authButton = findViewById(R.id.authButton);
        _regButton = findViewById(R.id.regButton);
        _authButton.setOnClickListener(view-> onAuthButtonClick());
        _regButton.setOnClickListener(view -> onRegButtonClick());
    }

    @Override
    public void onAuthButtonClick() {
        _presenter.authClicked(getAccountData(), FIRST_AUTH);
    }

    @Override
    public void onRegButtonClick() {
        Snackbar.make(getCurrentFocus(), getResources().getString(R.string.Registration_Not_Available), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        _progressDialog = new ProgressDialog(this);
        _progressDialog.setMessage(getResources().getString(R.string.auth));
        _progressDialog.setCanceledOnTouchOutside(false);
        _progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (_progressDialog != null){
            _progressDialog.dismiss();
        }
    }

    @Override
    public void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean getCheckboxStatus() {
        return ((CheckBox) findViewById(R.id.rememberbox)).isEnabled();
    }

    @Override
    public String[] getAccountData() {
        String[] data = { ((TextView)findViewById(R.id.LoginET)).getText().toString(),
                ((TextView)findViewById(R.id.Password_ET)).getText().toString()};
        return data;
    }

    @Override
    public SharedPreferences getSharedPref() {
        return getSharedPreferences(ACCOUNT_DATA, MODE_PRIVATE);
    }

    @Override
    public void raiseAuthError() {
        Snackbar.make(getCurrentFocus(), getResources().getString(R.string.Incorrect_login_or_password), Snackbar.LENGTH_SHORT).show();
    }
}