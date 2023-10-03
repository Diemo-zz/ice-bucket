package com.bucket.ice.dtos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "tracks")
public class Track {
    @Id
    private Long id;
    private String title;

    public Track(String title) {
        this.title=title;
    }
}
