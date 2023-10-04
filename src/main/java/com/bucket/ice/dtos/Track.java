package com.bucket.ice.dtos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.NonNull;

@Data
@Table(name = "tracks")
public class Track {
    @Id
    private Long id;
    @NonNull
    private String title;

    @NonNull
    private Long artistId;

    public Track(String title, Long artistId) {
        this.title=title;
        this.artistId = artistId;
    }
}