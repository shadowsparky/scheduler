package ru.shadowsparky.scheduler.carolinescheduler.Adapters;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import ru.shadowsparky.scheduler.carolinescheduler.R;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;

public class MainRVAdapter extends RecyclerView.Adapter<MainRVAdapter.SchedulesViewHolder>{
    private List<SchedulesTable> data;
    public MainRVAdapter(List<SchedulesTable> data) {
        this.data = data;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public SchedulesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item, viewGroup, false);
        SchedulesViewHolder svh = new SchedulesViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull SchedulesViewHolder schedulesViewHolder, int i) {
        schedulesViewHolder._section.setVisibility(View.GONE);
        schedulesViewHolder._importanceLevel.setText(data.get(i).getImportance_Level());
        schedulesViewHolder._content.setText(data.get(i).getCaption());
        schedulesViewHolder._datetime.setText(data.get(i).getDate() + ", " + data.get(i).getTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class SchedulesViewHolder extends RecyclerView.ViewHolder{
        LinearLayout _section;
        ImageView _image;
        TextView _content;
        TextView _importanceLevel;
        TextView _datetime;
        public SchedulesViewHolder(@NonNull View itemView) {
            super(itemView);
            _section = itemView.findViewById(R.id.RV_Sections);
            _image = itemView.findViewById(R.id.RV_Image);
            _content = itemView.findViewById(R.id.RV_ContentTextView);
            _importanceLevel = itemView.findViewById(R.id.RV_ImportanceTextView);
            _datetime = itemView.findViewById(R.id.RV_DateTimeTextView);
        }
    }

}
