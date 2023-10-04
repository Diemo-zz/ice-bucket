package com.bucket.ice.controllers;

import com.bucket.ice.dtos.Track;
import com.bucket.ice.services.track.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@RestController
public class TrackController {
    @Autowired
    TrackService trackService;

    @GetMapping("/tracks")
    public Flux<Track> getTracks() {
        return trackService.getAllTracks();
    }

    @PostMapping(value = "/track/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Track> addTrack(@Valid @RequestBody Track track) {
        return trackService.addTrack(track);
    }
}
