package com.bucket.ice.services.track;

import com.bucket.ice.dtos.Track;
import com.bucket.ice.repositories.TrackRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TrackServiceImpl  implements TrackService {

    @Autowired
    TrackRepository trackRepository;

    public Track deleteTrack() {
        return new Track("test");
    }

    public Track updateTrack() {
        return new Track("test");
    }

    public Flux<Track> getAllTracks() {
        return Flux.fromIterable(List.of(new Track("test")));
    }

    public Mono<Track> addTrack(Track track) {
        var LOG = LoggerFactory.getLogger("TE");
        LOG.info("TEST");
        return trackRepository.save(track);
    }
}
