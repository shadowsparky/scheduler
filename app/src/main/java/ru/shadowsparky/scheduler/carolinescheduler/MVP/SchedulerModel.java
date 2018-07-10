package ru.shadowsparky.scheduler.carolinescheduler.MVP;

import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import ru.shadowsparky.scheduler.carolinescheduler.Exceptions.AuthDataNotFoundException;
import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.IContracts;

import static android.content.Context.MODE_PRIVATE;
import static ru.shadowsparky.scheduler.carolinescheduler.Interfaces.IAuthData.LOGIN_TAG;
import static ru.shadowsparky.scheduler.carolinescheduler.Interfaces.IAuthData.PASSWORD_TAG;

public class SchedulerModel implements IContracts.ContractModel {
    public interface IAuthCallback{
        void onAuthCompleteListener(boolean result);
    }
    public static final int AUTH_DATA_SIZE = 2;
    public static final int LOGIN_INDEX = 0;
    public static final int PASSWORD_INDEX = 1;

    @Override
    public void savePrivateData(SharedPreferences sp, String _login, String _password) throws InternalError{
        Boolean checkSave = sp.edit()
                .putString(LOGIN_TAG, _login)
                .putString(PASSWORD_TAG, _password)
                .commit();
        if (!checkSave){
            throw new InternalError("Save don't available");
        }
    }
    @Override
    public String[] loadPrivateData(SharedPreferences sp) throws AuthDataNotFoundException {
        boolean t = ((sp.getString(LOGIN_TAG, null) == null)
                || (sp.getString(PASSWORD_TAG, null) == null))? true:false;
        if (!t)
            return new String[] { sp.getString(LOGIN_TAG, null), sp.getString(PASSWORD_TAG, null) };
        else
            throw new AuthDataNotFoundException("AuthError");
    }
    @Override
    public void auth(String login, String password, IAuthCallback callback) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        AuthThread at = new AuthThread(callback, mAuth);
        at.execute(new String[] {login, password});
    }

    class AuthThread extends AsyncTask <String, Void, Void> {
        IAuthCallback callback;
        FirebaseAuth mAuth;

        public AuthThread(IAuthCallback callback, FirebaseAuth mAuth) {
            this.callback = callback;
            this.mAuth = mAuth;
        }

        @Override
        protected Void doInBackground(String... strings) {
            mAuth.signInWithEmailAndPassword(strings[LOGIN_INDEX], strings[PASSWORD_INDEX]).addOnCompleteListener((task) -> {
                if (task.isSuccessful()) {
                    callback.onAuthCompleteListener(true);
                } else
                    callback.onAuthCompleteListener(false);
            });
            return null;
        }
    }
}
