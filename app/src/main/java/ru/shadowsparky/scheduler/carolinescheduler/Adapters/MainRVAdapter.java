package ru.shadowsparky.scheduler.carolinescheduler.Adapters;
import android.content.Context;
import android.content.Intent;
import android.service.autofill.SaveCallback;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.MainActivity.IMainContracts;
import ru.shadowsparky.scheduler.carolinescheduler.MVP.ShowScheduleActivity.ShowScheduleView;
import ru.shadowsparky.scheduler.carolinescheduler.R;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.DAO.ScheduleDao;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.DatabaseConfig;

public class MainRVAdapter extends RecyclerView.Adapter<MainRVAdapter.SchedulesViewHolder>{
    public static class SchedulesViewHolder extends RecyclerView.ViewHolder{
        LinearLayout _section;
        ImageView _image;
        TextView _content;
        TextView _importanceLevel;
        TextView _datetime;
        CardView _card;
        RecyclerView _rv;
        public SchedulesViewHolder(@NonNull View itemView) {
            super(itemView);
            _section = itemView.findViewById(R.id.RV_Sections);
            _image = itemView.findViewById(R.id.RV_Image);
            _content = itemView.findViewById(R.id.RV_ContentTextView);
            _importanceLevel = itemView.findViewById(R.id.RV_ImportanceTextView);
            _datetime = itemView.findViewById(R.id.RV_DateTimeTextView);
            _card = itemView.findViewById(R.id.RV_CardView);
            _rv = itemView.findViewById(R.id.RV_List);
        }
    }
    private List<SchedulesTable> data;
    public List<SchedulesTable> getData() {
        return data;
    }
    private Context _context;
    private PublishSubject<SchedulesTable> _subject;
    public MainRVAdapter(List<SchedulesTable> data, Context _context, PublishSubject<SchedulesTable> _subject) {
        this.data = data;
        this._context = _context;
        this._subject = _subject;
    }

    @Override public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override public SchedulesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item, viewGroup, false);
        SchedulesViewHolder svh = new SchedulesViewHolder(v);
        return svh;
    }
    @Override public void onBindViewHolder(@NonNull SchedulesViewHolder schedulesViewHolder, int i) {
        if ((i==0) || (!data.get(i).getImportance_Level().equals(data.get(i-1).getImportance_Level()))){
            schedulesViewHolder._section.setVisibility(View.VISIBLE);
            schedulesViewHolder._importanceLevel.setText(data.get(i).getImportance_Level());
        } else {
            schedulesViewHolder._section.setVisibility(View.GONE);
        }
        schedulesViewHolder._content.setText(data.get(i).getCaption());
        schedulesViewHolder._datetime.setText(data.get(i).getDate() + ", " + data.get(i).getTime());
        setAnimation(schedulesViewHolder.itemView, i);
        DatabaseConfig.LOG(data.get(i).getCaption() + " subscribed");
        schedulesViewHolder._card.setOnClickListener(view->{
            _subject.onNext(data.get(i));
            DatabaseConfig.LOG("Item clicked");
        });
    }
    @Override public int getItemCount() {
        return data.size();
    }
    private int lastPosition = -1;
    private void setAnimation(View view, int position) {
        if (position > lastPosition){
            Animation _anim = AnimationUtils.loadAnimation(_context, android.R.anim.slide_in_left);
            view.startAnimation(_anim);
            lastPosition = position;
        }
    }
}
