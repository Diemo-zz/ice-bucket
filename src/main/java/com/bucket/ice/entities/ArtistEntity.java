package com.bucket.ice.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(value="artists")
public class ArtistEntity {
    @Id
    private Long id;
    private String name;

    public ArtistEntity(String name) {
        this.name = name;
    }
}
