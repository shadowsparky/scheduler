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

public class MainListAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private String[] itemHeader;
    private String[] itemDescription;

    public MainListAdapter(@NonNull Context context, int resource, @NonNull String[] objects, @NonNull String[] itemDescription) {
        super(context, resource, objects);
        this.mContext = context;
        this.itemHeader = objects;
        this.itemDescription = itemDescription;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_item, parent, false);
        TextView _textView = row.findViewById(R.id.text);
        _textView.setText(itemHeader[position]);
        ImageView _imageView = row.findViewById(R.id.image);
        _imageView.setImageResource(android.R.color.background_dark);
        return row;
    }
}
