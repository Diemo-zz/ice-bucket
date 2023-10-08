package com.bucket.ice.services.track;

import com.bucket.ice.dtos.Artist;
import com.bucket.ice.dtos.Track;
import com.bucket.ice.entities.TrackEntity;
import com.bucket.ice.repositories.ArtistRepository;
import com.bucket.ice.repositories.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.util.List;

@Service
public class TrackServiceImpl  implements TrackService {

    private final Logger LOGGER = LoggerFactory.getLogger(TrackServiceImpl.class);
    @Autowired
    TrackRepository trackRepository;

    @Autowired
    ArtistRepository artistRepository;

    public Track deleteTrack() {
        return new Track("test", new Artist("name"));
    }

    public Track updateTrack() {
        return new Track("test", new Artist("name"));
    }

    public Flux<Track> getAllTracks(Long artistId) {
        var artistMono = artistRepository.getById(artistId);
        return artistMono.flatMapMany(artist-> trackRepository.getAllByArtistId(artist.getId())
                .map(e-> Tuples.of(artist, e))
                .map(tuple -> {
                    var artistEntity = tuple.getT1();
                    var trackEntity = tuple.getT2();
                    return new Track(trackEntity.getId(),
                            trackEntity.getTitle(),
                            new Artist(artistEntity.getId(), artistEntity.getName()));
                }));
    }

    public Mono<Track> addTrack(TrackEntity track) {
        return trackRepository.save(track)
                .doOnNext(e -> LOGGER.info("ADDED TRACK {}", e))
                .flatMap(this::fromEntity) ;
    }

    private Mono<Track> fromEntity(TrackEntity entity) {
        return artistRepository.getById(entity.getArtistId())
                .map(artist -> new Track(entity.getId(),
                        entity.getTitle(),
                        new Artist(artist.getId(), artist.getName())));

    }
}
