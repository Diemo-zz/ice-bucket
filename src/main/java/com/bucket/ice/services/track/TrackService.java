package com.bucket.ice.services.track;

import com.bucket.ice.dtos.Artist;
import com.bucket.ice.dtos.Track;
import com.bucket.ice.entities.TrackEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface TrackService {

    public Track deleteTrack();
    Track updateTrack();

    Flux<Track> getAllTracks(Long artistId);

    Mono<Track> addTrack(TrackEntity track);

}
