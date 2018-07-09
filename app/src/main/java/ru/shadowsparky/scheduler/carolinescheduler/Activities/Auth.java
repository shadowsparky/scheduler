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
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ILoad;
import ru.shadowsparky.scheduler.carolinescheduler.R;

public class Auth extends AppCompatActivity implements View.OnClickListener, IAuthData, IAuth, ILoad{
    public static final int AUTH_DATA_SIZE = 2;
    public static final int LOGIN_INDEX = 0;
    public static final int PASSWORD_INDEX = 1;
    public static final String TAG = "MainTag";
    public static final int REPETITIVE_AUTH = 1;
    public static final int FIRST_AUTH = 0;
    private AuthThread _thread;
    private Button AuthButton;
    private Button RegButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        if (!tryAuth()) {
            setTheme(R.style.AppThemeWithStatusBarColor);
            setContentView(R.layout.activity_auth);
            Log.isLoggable(TAG, Log.DEBUG);
            initListeners();
        }
    }

    @Override
    public void initListeners(){
        AuthButton = (Button) findViewById(R.id.pushbutton);
        RegButton = (Button) findViewById(R.id.regbutton);
        AuthButton.setOnClickListener(this);
        RegButton.setOnClickListener(this);
    }

    @Override
    public void enableLoading(final ProgressDialog pd){
        AuthButton.setEnabled(false);
        pd.setTitle(getResources().getString(R.string.auth) + "...");
        pd.setMessage(getResources().getString(R.string.AuthChecker));
        pd.setButton(Dialog.BUTTON_NEGATIVE, "Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                _thread.cancel(true);
            }
        });
        pd.show();
    }

    @Override
    public void disableLoading(final ProgressDialog pd){
        pd.hide();
        AuthButton.setEnabled(true);
    }

    @Override
    public boolean tryAuth(){
        String[] authData;
        try {
            authData = loadPreference();
            auth(authData[LOGIN_INDEX], authData[PASSWORD_INDEX], REPETITIVE_AUTH);
        } catch (AuthDataNotFoundException e) {
            Log.d(TAG, e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void auth(String login, String password, int AuthControl){
        String[] res = {login, password};
        _thread = new AuthThread(this, AuthControl, mAuth);
        _thread.execute(res);
    }

    @Override
    public void onClick(View view) {
        String Login = ((TextView) findViewById(R.id.LoginET)).getText().toString();
        String Password = ((TextView) findViewById(R.id.Password_ET)).getText().toString();
        switch (view.getId()){
            case R.id.pushbutton:
                auth(Login, Password, FIRST_AUTH);
                break;
            case R.id.regbutton:
                Snackbar.make(view, getResources().getString(R.string.Registration_Not_Available), Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void goToMainActivity(){
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
    public boolean savePreferenceChecker(String Login, String Password){
        boolean check = ((CheckBox) findViewById(R.id.rememberbox)).isChecked();
        if (check) {
            savePreference(Login, Password);
        }
        return check;
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

    @Override
    public void raiseAuthError(){
        Snackbar.make(getCurrentFocus(), getResources().getString(R.string.Incorrect_login_or_password), Snackbar.LENGTH_SHORT).show();
    }
}

class AuthThread extends AsyncTask<String, Integer, String> implements OnCompleteListener<AuthResult>{
    public static final String USER_NOT_EXISTS = "user not Exists";
    public static final String INTERRUPTED = "interrupted";
    public static final String USER_EXISTS = "User Exists";
    private Auth _auth;
    private Task checkAuth;
    private int _authControl;
    private ProgressDialog pd;
    private FirebaseAuth _mAuth;

    AuthThread(Auth auth, int AuthControl, FirebaseAuth AuthVar){
        _auth = auth;
        _authControl = AuthControl;
        _mAuth = AuthVar;
        if (_authControl == Auth.FIRST_AUTH)
            pd = new ProgressDialog(_auth);
    }

    @Override
    protected void onPreExecute() {
        if (_authControl == Auth.FIRST_AUTH)
            _auth.enableLoading(pd);
    }

    @Override
    protected void onPostExecute(String s) {
        if (_authControl == Auth.FIRST_AUTH)
            _auth.disableLoading(pd);
        switch (s){
            case USER_EXISTS:
                _auth.goToMainActivity();
                break;
            case USER_NOT_EXISTS:
                _auth.raiseAuthError();
                break;
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        _mAuth.signInWithEmailAndPassword(strings[Auth.LOGIN_INDEX],strings[Auth.PASSWORD_INDEX])
                .addOnCompleteListener(this);
        if (isCancelled()) return INTERRUPTED;
        return "test";
//        onComplete(checkAuth);
//        if (checkAuth.isSuccessful()){
//            return USER_EXISTS;
//        } else {
//            return USER_NOT_EXISTS;
//        }
    }

    @Override
    protected void onCancelled() {
        _auth.disableLoading(pd);
        Log.d(Auth.TAG, INTERRUPTED);
    }

    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful()){
            _auth.goToMainActivity();
        } else {
            _auth.raiseAuthError();
        }
    }
}