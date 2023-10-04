package com.bucket.ice.services.artist;

import com.bucket.ice.dtos.Artist;
import com.bucket.ice.dtos.Track;
import com.bucket.ice.repositories.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class ArtistServiceImpl implements ArtistService{

    private final Logger LOGGER = LoggerFactory.getLogger(ArtistServiceImpl.class);

    private ArtistRepository artistRepository;

    @Override
    public Mono<Artist> addArtist(Artist artist) {
        return null;
    }

    @Override
    public Flux<Track> getTracks(Artist artist) {
        return null;
    }

    @Override
    public Mono<Artist> getArtist() {
        return null;
    }

    @Override
    public Mono<Artist> getDailyArtist() {
        return artistRepository.count().map(
                count -> {
                    var time = Instant.now().getEpochSecond()/86400L;
                    LOGGER.info("{}", time);
                    return time;
                }
        ).map(e -> new Artist("test2"));
    }
}
