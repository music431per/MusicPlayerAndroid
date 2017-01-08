package com.freeeeedom.waveplayerandroid.musicplayer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    final static String TAG = "MainActivity";
    final static int MY_PERMISSIONS_REQUEST = 1;

    private PlaybackControlsFragment mControlsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // パーミッションチェック
        if (checkPermission()) {
            musicListLoader();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");

        mControlsFragment = (PlaybackControlsFragment) getFragmentManager().findFragmentById(R.id.fragment_playback_controls);
        if (mControlsFragment == null) {
            throw new IllegalStateException("Mising fragment with id 'controls'. Cannot continue.");
        }
    }

    private boolean checkPermission() {
        Log.d(TAG, "checkPermission()");
        // マシュマロ以上だった場合
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 許可されているかチェック
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // 権限がなければ権限を求めるダイアログを表示
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult()");
        // 権限を求めるコード
        if (requestCode == MY_PERMISSIONS_REQUEST) {
            // 許可されたら
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 許可されたら
                reload();
            } else {
                // 許可されなかったら
                Toast.makeText(this, "許可しないと利用できません。", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    private void musicListLoader() {
        Log.d(TAG, "musicListLoader()");
        // song部分の実装{
        List tracks = Track.getItems(this);
        ListView trackList = (ListView) findViewById(R.id.song_list);
        ListTrackAdapter adapter = new ListTrackAdapter(this, tracks);
        trackList.setAdapter(adapter);
        trackList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView listView = (ListView) parent;
        Track track = (Track) listView.getItemAtPosition(position);
        mControlsFragment.onMetadataChanged(track);
    }
}
