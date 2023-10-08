package com.bucket.ice.controllers;

import com.bucket.ice.dtos.Artist;
import com.bucket.ice.dtos.Track;
import com.bucket.ice.services.artist.ArtistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class ArtistController {

    private final Logger LOGGER = LoggerFactory.getLogger(ArtistController.class);
    @Autowired
    ArtistService artistService;

    @GetMapping("/artist/tracks")
    public Flux<Track> getAllTracks(Artist artist) {
        return Flux.empty();
    }

    @GetMapping("/artist/daily")
    public Mono<Artist> getDailyArtist() {
        return artistService.getDailyArtist();
    }

    @PostMapping(value = "/artist/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Artist> addArtist(@RequestBody @Valid Artist artist) {
        LOGGER.info("{}", artist);
        return artistService.addArtist(artist);
    }

}
