package com.bucket.ice.services.track;

import com.bucket.ice.dtos.Track;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface TrackService {

    public Track deleteTrack();
    public Track updateTrack();

    public Flux<Track> getAllTracks();

    public Mono<Track> addTrack(Track track);

}
