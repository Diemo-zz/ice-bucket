package com.bucket.ice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table(value="artist_names")
public class ArtistAlias {
    private Long id;
    private String name;
}
