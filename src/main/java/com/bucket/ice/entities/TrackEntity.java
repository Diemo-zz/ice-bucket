package com.bucket.ice.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Data
@Table(name = "tracks")
public class TrackEntity {
    @Id
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private Long artistId;

    public TrackEntity(String title, Long id) {
        this.title = title;
        this.artistId = id;
    }
}
