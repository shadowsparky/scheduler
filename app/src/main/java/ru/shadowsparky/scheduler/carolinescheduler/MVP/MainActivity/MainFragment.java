package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import ru.shadowsparky.scheduler.carolinescheduler.Adapters.MainListAdapter;
import ru.shadowsparky.scheduler.carolinescheduler.R;

public class MainFragment extends android.support.v4.app.ListFragment implements IMainContracts.MainFragmentContract{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
     }

    @Override
    public void setAdapter(MainListAdapter adapter) {
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.single_item_test, null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ((MainActivity)getActivity()).itemClick(position);
    }
}
