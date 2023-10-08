package com.bucket.ice.dtos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

@Data
public class Track {
    @Id
    private Long id;
    @NonNull
    private String title;

    @NonNull
    private Artist artist;

    public Track(String title, Artist artist) {
        this.title=title;
        this.artist = artist;
    }
}
