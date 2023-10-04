package com.bucket.ice.dtos;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;


@Data
@Table(value = "artists")
public class Artist {
    @Id
    private Long id;
    @NonNull
    private String name;
    @Nullable
    private List<String> aliases;
    @Nullable
    private List<Long> trackIds;
}
