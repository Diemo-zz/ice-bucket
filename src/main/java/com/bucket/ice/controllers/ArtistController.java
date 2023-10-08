package com.bucket.ice.controllers;

import com.bucket.ice.dtos.Artist;
import com.bucket.ice.dtos.Track;
import com.bucket.ice.services.artist.ArtistService;
import com.bucket.ice.services.track.TrackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class ArtistController {

    private final Logger LOGGER = LoggerFactory.getLogger(ArtistController.class);
    @Autowired
    ArtistService artistService;

    @Autowired
    TrackService trackService;

    @GetMapping("/artist/tracks")
    public Flux<Track> getAllTracks(Artist artist) {
        return trackService.getAllTracks(artist.getId());
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

    @PostMapping(value="/artist/edit")
    public Mono<Artist> editArtistName(@RequestParam Long artistId, @RequestParam String newName) {
        return artistService.addName(artistId, newName);
    }

}
