package ru.shadowsparky.scheduler.carolinescheduler.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ru.shadowsparky.scheduler.carolinescheduler.R;

public class SchedulerItemsFragment_test extends ListFragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList<HashMap<String, Object>> mapArrayList = new ArrayList<>();
        HashMap<String, Object> map;
        for (int i = 0; i < 5; i++){
            map = new HashMap<>();
            map.put("ImageColor", android.R.color.holo_blue_light);
            map.put("test", "test " + i);
            mapArrayList.add(map);
        }
        String[] mapKeys = {"ImageColor", "test"};
        int[] mapIds = {R.id.image, R.id.text};
        ListAdapter mListAdapter = new SimpleAdapter(getContext(), mapArrayList, R.layout.single_item, mapKeys, mapIds);
        setListAdapter(mListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.single_item_test, null);
    }
}
