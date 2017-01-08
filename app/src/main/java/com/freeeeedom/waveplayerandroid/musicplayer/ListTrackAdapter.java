package com.freeeeedom.waveplayerandroid.musicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by eri on 2016/10/02.
 */
public class ListTrackAdapter extends ArrayAdapter<Track> {

    LayoutInflater mInflater;

    public ListTrackAdapter(Context context, List item) {
        super(context, 0, item);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        Track item = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.trackTextView = (TextView) convertView.findViewById(R.id.music_name);
            holder.artistTextView = (TextView) convertView.findViewById(R.id.artist_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.artistTextView.setText(item.getArtist());
        holder.trackTextView.setText(item.getTitle());

        return convertView;
    }

    static class ViewHolder {
        TextView trackTextView;
        TextView artistTextView;
    }

}
