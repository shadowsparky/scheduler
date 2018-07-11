package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import ru.shadowsparky.scheduler.carolinescheduler.Adapters.MainListAdapter;
import ru.shadowsparky.scheduler.carolinescheduler.R;

public class MainFragment extends ListFragment implements IMainContracts.MainFragmentContract{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //TODO: REMOVE THIS SHEET
        String[] data = new String[] {"one", "two", "three"};
        MainListAdapter adapter = new MainListAdapter(getActivity(),R.layout.single_item, data);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, data);
        setAdapter(adapter);
    }

    @Override
    public void setAdapter(ArrayAdapter<String> adapter) {
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.single_item_test, null);
    }
}
