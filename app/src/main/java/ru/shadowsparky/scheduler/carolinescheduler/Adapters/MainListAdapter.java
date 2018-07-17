package ru.shadowsparky.scheduler.carolinescheduler.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ru.shadowsparky.scheduler.carolinescheduler.R;
import ru.shadowsparky.scheduler.carolinescheduler.SQLite.Tables.SchedulesTable;
import ru.shadowsparky.scheduler.carolinescheduler.Utils.Schedule_Element;

public class MainListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<SchedulesTable> elements;

    public MainListAdapter(Context mContext, ArrayList<SchedulesTable> elements) {
        this.mContext = mContext;
        this.elements = elements;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Object getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_item, parent, false);
        TextView _textView = row.findViewById(R.id.text);
        _textView.setText(elements.get(position).getName());
        ImageView _imageView = row.findViewById(R.id.image);
        _imageView.setImageResource(android.R.color.background_dark);
        return row;
    }
}
