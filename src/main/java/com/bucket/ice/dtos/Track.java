package com.bucket.ice.dtos;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class Track {
    private Long id;
    @NonNull
    private String title;

    @NonNull
    private Artist artist;

    public Track(String title, Artist artist) {
        this.title=title;
        this.artist = artist;
    }

    public Track(Long id, String title, Artist artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }
}
