package com.freeeeedom.waveplayerandroid.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;


public class MusicService extends Service {

    private final IBinder mBinder = new MyBinder();
    private MediaPlayer mMediaPlayer = null;

    public MusicService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    // 再生中かどうか
    public boolean isPlaying() {
        boolean isPlaying = false;
        if (mMediaPlayer != null) {
            isPlaying = mMediaPlayer.isPlaying();
        }
        return isPlaying;
    }

    // 音楽を作成
    public void play(String path) {
        if (mMediaPlayer != null) {
            stop();
        }
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(path);
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.start();
    }

    // 音楽を停止
    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
