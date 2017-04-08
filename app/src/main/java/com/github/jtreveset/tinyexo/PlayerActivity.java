package com.github.jtreveset.tinyexo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.github.jtreveset.tinyexoplayer.TinyExoPlayer;
import com.google.android.exoplayer.AspectRatioFrameLayout;

public class PlayerActivity extends AppCompatActivity implements SurfaceHolder.Callback, TinyExoPlayer.Listener {

    private View shutterView;
    private AspectRatioFrameLayout videoFrame;
    private SurfaceView surfaceView;
    private TinyExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        shutterView = findViewById(R.id.shutter);
        videoFrame = (AspectRatioFrameLayout) findViewById(R.id.video_frame);
        surfaceView = (SurfaceView) findViewById(R.id.surface_view);

        surfaceView.getHolder().addCallback(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        preparePlayer(true);
        player.getPlayerControl().start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.getPlayerControl().pause();
    }

    private void preparePlayer(boolean playWhenReady) {
        if (player == null) {
            player = new TinyExoPlayer(this);
        }
        //player.prepare();
        player.load("http://dash.edgesuite.net/envivio/dashpr/clear/Manifest.mpd");

        player.addListener(this);
        player.setSurface(surfaceView.getHolder().getSurface());
        player.setPlayWhenReady(playWhenReady);
    }

    // SurfaceHolder.Callback methods
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        player.setSurface(holder.getSurface());
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    // TinyExoPlayer.Listener methods
    @Override
    public void onStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
        float ratio;
        if (height == 0) {
            ratio = 1;
        } else {
            ratio = (width * pixelWidthHeightRatio) / height;
        }
        videoFrame.setAspectRatio(ratio);
        shutterView.setVisibility(View.GONE);
    }
}
