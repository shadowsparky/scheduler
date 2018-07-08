package ru.shadowsparky.scheduler.carolinescheduler.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import ru.shadowsparky.scheduler.carolinescheduler.Exceptions.AuthDataNotFoundException;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.IAuthData;
import ru.shadowsparky.scheduler.carolinescheduler.R;

public class Auth extends AppCompatActivity implements View.OnClickListener, IAuthData {
    static final int AUTH_DATA_SIZE = 2;
    static final int LOGIN_INDEX = 0;
    static final int PASSWORD_INDEX = 1;
    static final String TAG = "MainTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Log.isLoggable(TAG, Log.DEBUG);
        initListeners();
        tryAuth();
    }

    private void initListeners(){
        Button AuthButton = (Button) findViewById(R.id.pushbutton);
        Button RegButton = (Button) findViewById(R.id.regbutton);
        AuthButton.setOnClickListener(this);
        RegButton.setOnClickListener(this);
    }

    private boolean tryAuth(){
        String[] authData;
        try {
            authData = loadPreference();
            auth(authData[LOGIN_INDEX], authData[PASSWORD_INDEX]);
        } catch (AuthDataNotFoundException e) {
            Log.d(TAG, e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        String Login = ((TextView) findViewById(R.id.LoginET)).getText().toString();
        String Password = ((TextView) findViewById(R.id.Password_ET)).getText().toString();
        switch (view.getId()){
            case R.id.pushbutton:
                auth(Login, Password);
                break;
            case R.id.regbutton:
                Snackbar.make(view, getResources().getString(R.string.Auth_Failed), Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    private void auth(String Login, String Password){
        if ((Login.equals("test")) && (Password.equals("1111"))){
            // ok
            Boolean Check_Remember = ((CheckBox) findViewById(R.id.rememberbox)).isChecked();
            if (Check_Remember){
                try {
                    savePreference(Login, Password);
                } catch(InternalError e){
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Error_In_The_Save), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, e.getMessage());
                }
            }
            goToMainActivity();
        } else {
            Log.d(TAG, "auth failed");
            Snackbar.make(getCurrentFocus(), getResources().getString(R.string.Incorrect_login_or_password), Snackbar.LENGTH_SHORT).show();
        }
    }
    private void goToMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void savePreference(String Login, String Password) throws InternalError {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        Boolean checkSave = sharedPreferences.edit()
                .putString(LOGIN_TAG, Login)
                .putString(PASSWORD_TAG, Password)
                .commit();
        if (!checkSave){
            throw new InternalError("Save don't available");
        }
    }

    @Override
    public String[] loadPreference() throws AuthDataNotFoundException {
        String result[] = new String[AUTH_DATA_SIZE];
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        result[LOGIN_INDEX] = sp.getString(LOGIN_TAG, null);
        result[PASSWORD_INDEX] = sp.getString(PASSWORD_TAG, null);
        if ((result[LOGIN_INDEX] == null) || (result[PASSWORD_INDEX] == null)){
            throw new AuthDataNotFoundException("Auth data not found");
        }
        return result;
    }
}