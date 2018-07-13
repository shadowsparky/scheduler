package ru.shadowsparky.scheduler.carolinescheduler.MVP.LoginActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity.MainActivity;
import ru.shadowsparky.scheduler.carolinescheduler.R;

public class Auth extends AppCompatActivity implements ILoginContracts.ContractView_Auth {
    public static final String ACCOUNT_DATA = "ACCOUNT_DATA";
    public static final int FIRST_AUTH = 0;

    @BindView(R.id.authLoading)
    ProgressBar pb;

    private ILoginContracts.ContractPresenter_Auth _presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new SchedulerPresenter(this, new SchedulerModel());
        if (!_presenter.rememberedAuth()) {
            setTheme(R.style.AppThemeWithStatusBarColor);
            setContentView(R.layout.activity_auth);
            ButterKnife.bind(this);
        }
    }

    @Override
    @OnClick(R.id.authButton)
    public void onAuthButtonClick() {
        _presenter.authClicked(getAccountData(), FIRST_AUTH);
    }

    @Override
    @OnClick(R.id.regButton)
    public void onRegButtonClick() {
        Snackbar.make(getCurrentFocus(), getResources().getString(R.string.Registration_Not_Available), Snackbar.LENGTH_SHORT).show();
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
         return new String[] { ((TextView)findViewById(R.id.LoginET)).getText().toString(),
                ((TextView)findViewById(R.id.Password_ET)).getText().toString()};
    }

    @Override
    public SharedPreferences getSharedPref() {
        return getSharedPreferences(ACCOUNT_DATA, MODE_PRIVATE);
    }

    @Override
    public void raiseAuthError() {
        Snackbar.make(getCurrentFocus(), getResources().getString(R.string.Incorrect_login_or_password), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pb.setVisibility(View.INVISIBLE);
    }

}