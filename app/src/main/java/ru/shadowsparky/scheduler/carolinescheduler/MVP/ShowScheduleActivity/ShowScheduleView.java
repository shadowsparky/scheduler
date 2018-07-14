package ru.shadowsparky.scheduler.carolinescheduler.MVP.ShowScheduleActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import ru.shadowsparky.scheduler.carolinescheduler.R;

public class ShowScheduleView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_schedule);
        Integer ID = getIntent().getIntExtra("ID", -1);
        if (ID != -1){

        } else {

        }
    }
}
