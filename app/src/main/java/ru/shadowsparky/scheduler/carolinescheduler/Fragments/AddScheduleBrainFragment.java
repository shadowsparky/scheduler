package ru.shadowsparky.scheduler.carolinescheduler.Fragments;

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
import io.reactivex.subjects.PublishSubject;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.AddScheduleActivity.IAddScheduleContracts;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.ShowScheduleActivity.IShowScheduleContract;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.ShowScheduleActivity.ShowScheduleView;
import ru.shadowsparky.scheduler.carolinescheduler.R;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.DatabaseConfig;

public class AddScheduleBrainFragment extends Fragment {
    @BindView(R.id.addDate) Button date;
    @BindView(R.id.addTime) Button time;
    @BindView(R.id.addImportanceLevel) Button level;
    private PublishSubject<Integer> _subject;
    private IAddScheduleContracts.IContractSheduleView view;
    private IShowScheduleContract.ShowScheduleViewContract showView;
    public void setView(IAddScheduleContracts.IContractSheduleView view){
        this.view = view;
    }

    public void setView(IShowScheduleContract.ShowScheduleViewContract showScheduleView, PublishSubject<Integer> _subject) {
        this.showView = showScheduleView;
        this._subject = _subject;
        DatabaseConfig.LOG("Fragment initialize");
    }
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
        if (view != null) {
            view.showChooseTimeDialog();
        } else if (showView !=null){
            showView.showChooseTimeDialog();
        }
    }
    @OnClick(R.id.addDate) public void showChooseDateDialog(){
        if (view != null) {
            view.showChooseDateDialog();
        } else if (showView != null){
            showView.showChooseDateDialog();
        }
    }
    @OnClick(R.id.addImportanceLevel) public void showChooseLevelDialog(){
        if (view != null) {
            view.showChooseLevelDialog();
        } else if (showView != null){
            showView.showChooseLevelDialog();
        }
    }
    @Nullable
    @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.addschedulefragment, container, false);
        ButterKnife.bind(this, view);
        if (this.view == null)
            _subject.onNext(0);
        DatabaseConfig.LOG("Fragment controls binded");
        return view;
    }
}
