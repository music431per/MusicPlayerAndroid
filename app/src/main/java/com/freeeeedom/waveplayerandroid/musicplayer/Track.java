package com.freeeeedom.waveplayerandroid.musicplayer;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eri on 2017/01/08.
 */

public class Track {

    private long id;            //コンテントプロバイダに登録されたID
    private long albumId;       //同じくトラックのアルバムのID
    private long artistId;      //同じくトラックのアーティストのID
    private String path;        //実データのPATH
    private String title;       //トラックタイトル
    private String album;       //アルバムタイトル
    private String artist;      //アーティスト名
    private Uri uri;            // URI
    private long duration;      // 再生時間(ミリ秒)
    private int trackNo;        // アルバムのトラックナンバ

    private Track(Cursor cursor) {
        id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
        path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
        title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
        album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
        artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
        albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
        artistId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));
        duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
        trackNo = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.TRACK));
        uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
    }

    private static final String[] COLUMNS = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.TRACK,
    };

    public static List getItems(Context activity) {

        List tracks = new ArrayList();
        ContentResolver resolver = activity.getContentResolver();

        Cursor cursor = resolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                Track.COLUMNS,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            if (cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)) < 3000) {
                continue;
            }
            tracks.add(new Track(cursor));
        }
        cursor.close();

        return tracks;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(int trackNo) {
        this.trackNo = trackNo;
    }

}
