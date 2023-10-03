package com.bucket.ice.controllers;

import com.bucket.ice.dtos.Track;
import com.bucket.ice.services.track.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class TrackController {
    @Autowired
    TrackService trackService;

    @GetMapping("/")
    public Flux<Track> getTracks() {
        return trackService.getAllTracks();
    }

    @PostMapping("/")
    public Mono<Track> addTrack() {
        return trackService.addTrack(new Track("test"));
    }
}
