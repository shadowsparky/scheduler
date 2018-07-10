package ru.shadowsparky.scheduler.carolinescheduler.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import ru.shadowsparky.scheduler.carolinescheduler.Interfaces.ISelectionChange;

public class SchedulerItemsFragment extends ListFragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ISelectionChange listener = (ISelectionChange)getActivity();
        listener.OnSelectionChanged(position);
    }
}
