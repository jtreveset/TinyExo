package com.github.jtreveset.tinyexo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity that just presents a ListView containing hard-coded resources.
 * When one of these resources is tapped, a new {@link PlayerActivity} is created in order to
 * perform the playback.
 */
public class SourceSelectionActivity extends AppCompatActivity {

    public static final String EXTRA_RESOURCE_URL = "EXTRA_RESOURCE_URL";

    private ListView sourcesListView;

    private static final String [] SOURCES_NAMES = new String[]{
            "Smurfs trailer",
            "Red bull"
    };

    private static final String [] SOURCES_URLS = new String[]{
            "http://demo.unified-streaming.com/video/smurfs/smurfs.ism/smurfs.mpd",
            "http://dash.edgesuite.net/envivio/dashpr/clear/Manifest.mpd"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_source_selection);

        sourcesListView = (ListView) findViewById(R.id.listview_sources);

        ArrayAdapter<SourceItem> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                buildSourceItems());

        sourcesListView.setAdapter(adapter);

        sourcesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SourceSelectionActivity.this, PlayerActivity.class);
                i.putExtra(EXTRA_RESOURCE_URL, SOURCES_URLS[position]);
                startActivity(i);
            }
        });

    }

    private List<SourceItem> buildSourceItems () {
        List<SourceItem> items = new ArrayList<>(SOURCES_URLS.length);

        for (int i = 0; i < SOURCES_NAMES.length ; i++) {
            items.add(new SourceItem(SOURCES_NAMES[i], SOURCES_URLS[i]));
        }

        return items;
    }
}
