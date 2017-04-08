# TinyExoPlayer
Small wrapper around Exoplayer library with very basic functionality

## Synopsis
This is a small Android library that uses Exoplayer with the following features
* Play DASH files
* Pause and Play playback
* Allow the user to select a video quality level instead of using automatic quality switching

## Design decisions and assumptions

* Hugely based on Google's demo app for Exoplayer 1, mainly on DASH's `RendererBuilder` and `DemoPlayer` class. The `TinyExoPlayer` class is almost as the `DemoPlayer` but with some modifications.

* We don't want to depend on a specific Exoplayer version. This is why the lib declares the exoplayer dependency as `provided`. The lib expects Exoplayer to be present at run time. As Exoplayer is OSS we could include all of its sources onto our library and thus having only one big fat library the client code would need to include.

* We only support DASH streams. Also we don't care about subtitles or DRM for this project.

* As we want to provide a layer of abstraction, as much "bloat code" as possible is moved onto our player code. This code is mainly from Google's demo `PlayerActivity` class. This of course leads to a less customizable player, but the goal of this player is to reduce the complexity even if it implies a less out of the box flexible solution.

* To ease the usage of this player, a custom view (`TinyExoPlayerLayout`) is provided that should be used by the client code instead of dealing with the built-in exoplayer views and the SurfaceView.


## Code Example
The following code snippet exemplifies how to use the TinyExoPlayer by the app's code.

First, include a TinyExoPlayerLayout in your layout file.

```xml
    <com.github.jtreveset.tinyexoplayer.TinyExoPlayerLayout
        android:id="@+id/tinyExoplayerLayout"
        android:layout_width="wrap_content"
        android:layout_height="300dp" />
```

Then, in your Activiy's `onCreate`, create the player and pass it the Layout reference.

```java
    private TinyExoPlayer player;
    // ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ...
        
        tinyExoplayerLayout = (TinyExoPlayerLayout) findViewById(R.id.tinyExoplayerLayout);
        // Create player passing Context
        player = new TinyExoPlayer(this);        
        // Bind the layout to the player
        player.setPlayerLayout(tinyExoplayerLayout);
    }
```

You can now set the url to play and prepare the player for playback. If you want it to start right away, invoke the `play()` method.

```java
    player.setUrl("http://example.com/resource.mpd");
    player.prepare();
    player.play(); // This will start playback
```

## Project structure

There are two modules in the project.

- tinyexoplayer: 
 		The library itself
- app:
		Demo project. It contains a sample chooser activity and a player activity.
        
## API Reference
TinyExoPlayer

Public methods:

```java
	/**
     * Stores the resource url.
     * This resource will be loaded in the {@link #prepare()} method.
     *
     * It only supports DASH streams
     * @param resource the resource to load
     */
    public void setUrl(String resource);
    
    /**
     * Bind this TinyExoPlayer to the given View
     * @param tinyExoplayerLayout view that this player will use to render the video on
     */
    public void setPlayerLayout(TinyExoPlayerLayout tinyExoplayerLayout);
    
    /**
     * Plays (or resumes) playback
     */
    public void play();
    
    /**
     * Pauses playback
     */
    public void pause();
    
    /**
     * Returns whether the player is currently playing or not
     * @return whether the player is currently playing or not
     */
    public boolean isPlaying();
    
    /**
     * Returns a List of {@link VideoQuality}s available for this video
     * @return list with available video quality levels
     */
    public List<VideoQuality> getVideoQualityLevels();
    
    /**
     * Sets the playback video quality.
     * @param quality Quality to set. This is one of the values returned by {@link #getVideoQualityLevels()}
     */
    public void setSelectedVideoQualityLevel(VideoQuality quality);
```

