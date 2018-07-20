package ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.shadowsparky.scheduler.carolinescheduler.R;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.DatabaseConfig;

public class AddScheduleBrainFragment extends Fragment {
    @BindView(R.id.addDate) Button date;
    private IAddScheduleContracts.IContractSheduleView view;
    public void setView(IAddScheduleContracts.IContractSheduleView view){
        this.view = view;
    }

    @BindView(R.id.addTime) Button time;
    @BindView(R.id.addImportanceLevel) Button level;
    public Button getDate() {
        return date;
    }
    public Button getTime() {
        return time;
    }
    public Button getLevel() {
        return level;
    }
    @OnClick(R.id.addTime) public void showChooseTimeDialog(){
        view.showChooseTimeDialog();
    }
    @OnClick(R.id.addDate) public void showChooseDateDialog(){
        view.showChooseDateDialog();
    }
    @OnClick(R.id.addImportanceLevel) public void showChooseLevelDialog(){
        view.showChooseLevelDialog();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addschedulefragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
