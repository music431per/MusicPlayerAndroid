package com.freeeeedom.waveplayerandroid.musicplayer;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PlaybackControlsFragment extends Fragment {

    private final static String TAG = "PlaybackControlsFragment";

    private TextView mMusicTitle;
    private TextView mArtistTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_playback_controls, container, false);

        mMusicTitle = (TextView) rootView.findViewById(R.id.music_name);
        mArtistTitle = (TextView) rootView.findViewById(R.id.artist_name);

        return rootView;
    }

    public void onMetadataChanged(Track track) {
        mMusicTitle.setText(track.getTitle());
        mArtistTitle.setText(track.getArtist());
    }

}
