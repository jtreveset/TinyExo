package com.github.jtreveset.tinyexoplayer;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.exoplayer.AspectRatioFrameLayout;
import com.google.android.exoplayer.text.SubtitleLayout;


/**
 * TinyExoPlayerLayout wraps the views needed by the Exoplayer.
 */
public class TinyExoPlayerLayout extends FrameLayout {

    private AspectRatioFrameLayout aspectRatioFrameLayout;
    private SurfaceView surfaceView;
    private View shutter;
    private SubtitleLayout subtitleLayout;

    public TinyExoPlayerLayout(Context context) {
        super(context);
        createChildViews(context, null);
    }

    public TinyExoPlayerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        createChildViews(context, attrs);
    }

    private void createChildViews(Context c, AttributeSet attrs) {
        aspectRatioFrameLayout = new AspectRatioFrameLayout(c, attrs);

        FrameLayout.LayoutParams matchParentParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        // Surface view
        surfaceView = new SurfaceView(c, attrs);
        surfaceView.setLayoutParams(matchParentParams);

        // Shutter
        shutter = new View(c, attrs);
        shutter.setLayoutParams(matchParentParams);
        shutter.setBackgroundColor(Color.BLACK);

        // Subtitle Layout
        subtitleLayout = new SubtitleLayout(c, attrs);
        subtitleLayout.setLayoutParams(matchParentParams);

        // Add childs
        aspectRatioFrameLayout.addView(surfaceView);
        aspectRatioFrameLayout.addView(shutter);
        aspectRatioFrameLayout.addView(subtitleLayout);

        addView(aspectRatioFrameLayout);
    }

    public AspectRatioFrameLayout getAspectRatioFrameLayout() {
        return aspectRatioFrameLayout;
    }

    public SurfaceView getSurfaceView() {
        return surfaceView;
    }

    public View getShutter() {
        return shutter;
    }

    public SubtitleLayout getSubtitleLayout() {
        return subtitleLayout;
    }
}
