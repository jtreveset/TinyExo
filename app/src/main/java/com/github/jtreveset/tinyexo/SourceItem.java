package com.github.jtreveset.tinyexo;

/**
 * Container class that represents a playable asset
 */
public class SourceItem {

    private String name;
    private String url;

    public SourceItem(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
