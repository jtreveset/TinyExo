package com.github.jtreveset.tinyexo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.github.jtreveset.tinyexoplayer.TinyExoPlayer;
import com.github.jtreveset.tinyexoplayer.TinyExoPlayerLayout;
import com.github.jtreveset.tinyexoplayer.VideoQuality;

import java.util.List;

import static com.github.jtreveset.tinyexo.SourceSelectionActivity.EXTRA_RESOURCE_URL;

public class PlayerActivity extends AppCompatActivity {

    // TinyExoPlayer instance
    private TinyExoPlayer player;

    // TinyExoPlayerLayout, defined in activity_player.xml
    private TinyExoPlayerLayout tinyExoplayerLayout;

    // Buttons
    private Button buttonPlay;
    private Button buttonPause;
    private Button buttonSelectQuality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        tinyExoplayerLayout = (TinyExoPlayerLayout) findViewById(R.id.tinyExoplayerLayout);

        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonPause = (Button) findViewById(R.id.buttonPause);
        buttonSelectQuality = (Button) findViewById(R.id.buttonSelectQuality);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.play();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
            }
        });

        buttonSelectQuality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Query the player for the available video quality levels
                List<VideoQuality> availableQualityLevels = player.getVideoQualityLevels();

                // Create adapter to map the values to list items
                final ArrayAdapter<VideoQuality> qualityArrayAdapter = new ArrayAdapter<>(
                            PlayerActivity.this,
                            android.R.layout.simple_list_item_1,
                            availableQualityLevels);

                // Create dialog to show levels
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(PlayerActivity.this);
                alertBuilder.setTitle("Pick a video quality");
                alertBuilder.setAdapter(qualityArrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Pick selected quality level
                        VideoQuality quality = qualityArrayAdapter.getItem(which);
                        player.setSelectedVideoQualityLevel(quality);
                    }
                });
                alertBuilder.show();
            }
        });
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

        Intent i =  getIntent();
        String url = i.getStringExtra(EXTRA_RESOURCE_URL);

        player.load(url);
        player.setPlayWhenReady(playWhenReady);
    }
}
