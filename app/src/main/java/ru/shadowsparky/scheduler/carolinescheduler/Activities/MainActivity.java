package ru.shadowsparky.scheduler.carolinescheduler.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.SortedList;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.InputStream;
import java.util.Map;

import ru.shadowsparky.scheduler.carolinescheduler.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
