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
        fastAuth();
    }

    private void initListeners(){
        Button AuthButton = (Button) findViewById(R.id.pushbutton);
        Button RegButton = (Button) findViewById(R.id.regbutton);
        AuthButton.setOnClickListener(this);
        RegButton.setOnClickListener(this);
    }

    private boolean fastAuth(){
        String[] authData;
        try {
            authData = loadPreference();
            auth(authData[LOGIN_INDEX], authData[PASSWORD_INDEX]);
        } catch (AuthDataNotFoundException e) {
            e.printStackTrace();
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
                Snackbar.make(view, "Регистрация временно недоступна", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    private void auth(String Login, String Password){
        if ((Login.equals("test")) && (Password.equals("1111"))){
            // ok
            Boolean Check_Remember = ((CheckBox) findViewById(R.id.rememberbox)).isChecked();
            if (Check_Remember){
                savePreference(Login, Password);
            }
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        } else {
            Log.d(TAG, "auth failed");
            Snackbar.make(getCurrentFocus(), "Неправильный логин или пароль", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void savePreference(String Login, String Password) {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        Boolean checkSave = sharedPreferences.edit()
                .putString(LOGIN_TAG, Login)
                .putString(PASSWORD_TAG, Password)
                .commit();
        if (!checkSave){
            Toast.makeText(getApplicationContext(), "При сохранении данных произошла ошибка", Toast.LENGTH_SHORT).show();
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
