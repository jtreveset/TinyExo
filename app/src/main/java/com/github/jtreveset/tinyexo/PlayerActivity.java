package com.github.jtreveset.tinyexo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.github.jtreveset.tinyexoplayer.TinyExoPlayer;
import com.github.jtreveset.tinyexoplayer.TinyExoPlayerLayout;
import com.google.android.exoplayer.AspectRatioFrameLayout;

public class PlayerActivity extends AppCompatActivity {

    // TinyExoPlayer instance
    private TinyExoPlayer player;

    // TinyExoPlayerLayout, defined in activity_player.xml
    private TinyExoPlayerLayout tinyExoplayerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        tinyExoplayerLayout = (TinyExoPlayerLayout) findViewById(R.id.tinyExoplayerLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        preparePlayer(true);
        player.play();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    private void preparePlayer(boolean playWhenReady) {
        if (player == null) {
            player = new TinyExoPlayer(this);
            player.setPlayerLayout(tinyExoplayerLayout);
        }

        player.load("http://dash.edgesuite.net/envivio/dashpr/clear/Manifest.mpd");
        player.setPlayWhenReady(playWhenReady);
    }
}
