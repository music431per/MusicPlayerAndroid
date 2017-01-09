package com.freeeeedom.waveplayerandroid.musicplayer;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class PlaybackControlsFragment extends Fragment implements View.OnClickListener {

    private final static String TAG = "PlaybackControlsFragment";

    private TextView mMusicTitle;
    private TextView mArtistTitle;
    private ImageButton mImageButton;

    public interface OnOkBtnClickListener {
        boolean onOkClicked();
    }

    private OnOkBtnClickListener mListener;

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
        mImageButton = (ImageButton) rootView.findViewById(R.id.play_button);
        mImageButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mListener = ((OnOkBtnClickListener) context);
    }

    public void onMetadataChanged(Track track) {
        mMusicTitle.setText(track.getTitle());
        mArtistTitle.setText(track.getArtist());
        mImageButton.setImageResource(R.mipmap.stop);
    }


    @Override
    public void onClick(View v) {
        boolean isPlaying = false;
        if (mListener != null) {
            isPlaying = mListener.onOkClicked();
        }
        if (isPlaying) {
            // 再生中
            mImageButton.setImageResource(R.mipmap.stop);
        } else {
            // 停止中
            mImageButton.setImageResource(R.mipmap.play);
        }
    }
}
