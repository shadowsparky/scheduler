package ru.shadowsparky.scheduler.carolinescheduler.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ru.shadowsparky.scheduler.carolinescheduler.R;

public class ListFragmentAdapter extends ArrayAdapter <String> {
    private final LayoutInflater mInflater;

    public ListFragmentAdapter(@NonNull Context context) {
        super(context, android.R.layout.simple_gallery_item);
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate(R.layout.single_item, parent, false);
        } else
            view = convertView;
        ((TextView)view.findViewById(R.id.text)).setText("test");
        return view;
    }

    @Override
    public void add(@Nullable String object) {
        super.add(object);
    }
}
