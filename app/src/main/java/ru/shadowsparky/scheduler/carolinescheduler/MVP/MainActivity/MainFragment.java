package ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import butterknife.OnClick;
import ru.shadowsparky.scheduler.carolinescheduler.Adapters.MainListAdapter;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.NewScheduleFragment.NewSchedule;
import ru.shadowsparky.scheduler.carolinescheduler.R;

public class MainFragment extends android.support.v4.app.ListFragment implements IMainContracts.MainFragmentContract{
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
     }
    public void setAdapter(MainListAdapter adapter) {
        setListAdapter(adapter);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.single_item_test, null);
    }
    public void onListItemClick(ListView l, View v, int position, long id) {
        ((MainActivity)getActivity()).itemClick(position);
    }
    @OnClick(R.id.fab)
    public void clickFab(View view){
        Fragment ns = new NewSchedule();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        ft.replace(R.id.fragment_Container, ns);
        ft.addToBackStack(null);
        ft.commit();
    }
}
