package com.bucket.ice.controllers;

import com.bucket.ice.dtos.Track;
import com.bucket.ice.entities.TrackEntity;
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
public class TrackController {
    @Autowired
    TrackService trackService;

    private final Logger LOGGER = LoggerFactory.getLogger(TrackController.class);

    @GetMapping("/tracks")
    public Flux<Track> getTracks() {
        return trackService.getAllTracks();
    }

    @PostMapping(value = "/track/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Track> addTrack(@Valid @RequestBody TrackEntity track) {
        LOGGER.info("{}", track);
        return trackService.addTrack(track);
    }
}
