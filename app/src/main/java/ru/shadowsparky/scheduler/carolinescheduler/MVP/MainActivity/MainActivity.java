package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.shadowsparky.scheduler.carolinescheduler.R;

public class MainActivity extends AppCompatActivity implements IMainContracts.MainViewContract {

    @BindView(R.id.toolbar)
    Toolbar _toolbar;
    public Context getContext() {
        return getApplicationContext();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_toolbar);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
    }
    public void setToolbar() {
        setSupportActionBar(_toolbar);
    }
}
