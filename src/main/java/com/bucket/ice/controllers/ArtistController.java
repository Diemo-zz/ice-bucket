package com.bucket.ice.controllers;

import com.bucket.ice.dtos.Artist;
import com.bucket.ice.dtos.Track;
import com.bucket.ice.services.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ArtistController {
    @Autowired
    ArtistService artistService;

    @GetMapping("/artists")
    public Flux<Track> getAllTracks(Artist artist) {
        return Flux.empty();
    }

    @GetMapping("/artist/daily")
    public Mono<Artist> getDailyArtist() {
        return artistService.getDailyArtist();
    }

    @PostMapping(value = "/artist/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Artist> addArtist() {
        return artistService.addArtist(new Artist("test"));
    }

}
