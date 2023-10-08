package com.bucket.ice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;


@Data
public class Artist {
    private Long id;
    @NonNull
    @JsonProperty
    private String name;

    @JsonProperty
    @Nullable
    private List<String> aliases;
    @JsonProperty
    @Nullable
    private List<Long> trackIds;

    public Artist(String name) {
        this.name = name;
    }

    public Artist() { // Needed for some reason to stop exception

    }

    public Artist(Long id, String name) {
        this.id = id;
        this.name =name;
    }

    public Artist(Long id, String name, List<String> aliases) {
        this.id = id;
        this.name = name;
        this.aliases = aliases;
    }
}
