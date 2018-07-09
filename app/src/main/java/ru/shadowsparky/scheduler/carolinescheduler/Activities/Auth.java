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
    private Button AuthButton;
    private Button RegButton;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

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
    public void onStart(){
        super.onStart();
        mUser = mAuth.getCurrentUser();
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
        pd.setCanceledOnTouchOutside(false);
        pd.show();
    }

    @Override
    public void disableLoading(final ProgressDialog pd){
        pd.hide();
        AuthButton.setEnabled(true);
    }

    @Override
    public boolean tryAuth(){
        String[]result;
        try {
            result = loadPreference();
        } catch (AuthDataNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        AuthTask at = new AuthTask(this, REPETITIVE_AUTH);
        at.execute(result);
        return true;
    }

    @Override
    public void auth(final String login, final String password, int AuthControl){
        mAuth.signInWithEmailAndPassword(login, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    mUser = mAuth.getCurrentUser();
                    goToMainActivity();
                    savePreference(login, password);
                } else { raiseAuthError(); }
            }
        });
    }

    @Override
    public void onClick(View view) {
//        ProgressDialog pd = new ProgressDialog(this);
        String Login = ((TextView) findViewById(R.id.LoginET)).getText().toString();
        String Password = ((TextView) findViewById(R.id.Password_ET)).getText().toString();
        switch (view.getId()){
            case R.id.pushbutton:
                AuthTask at = new AuthTask(this, FIRST_AUTH);
                String[] executeData = {Login, Password};
                at.execute(executeData);
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

class AuthTask extends AsyncTask<String, String, String>{
    public static final String THREAD_END = "end";
    private Auth _auth;
    private int t;
    private ProgressDialog pd;

    public AuthTask(Auth auth, int t){
        this._auth = auth;
        this.t = t;
        if (t != Auth.REPETITIVE_AUTH)
            pd = new ProgressDialog(_auth);
    }
    @Override
    protected String doInBackground(String... strings) {
        _auth.auth(strings[Auth.LOGIN_INDEX], strings[Auth.PASSWORD_INDEX], t);
        return THREAD_END;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (t != Auth.REPETITIVE_AUTH)
            _auth.enableLoading(pd);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPreExecute();
        if (t != Auth.REPETITIVE_AUTH)
            _auth.disableLoading(pd);
    }
}