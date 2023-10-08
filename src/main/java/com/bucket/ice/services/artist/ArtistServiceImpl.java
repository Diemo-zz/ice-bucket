package com.bucket.ice.services.artist;

import com.bucket.ice.dtos.Artist;
import com.bucket.ice.dtos.Track;
import com.bucket.ice.entities.ArtistAlias;
import com.bucket.ice.entities.ArtistEntity;
import com.bucket.ice.repositories.ArtistNamesRepository;
import com.bucket.ice.repositories.ArtistRepository;
import com.bucket.ice.repositories.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService{

    private final Logger LOGGER = LoggerFactory.getLogger(ArtistServiceImpl.class);

    private final ArtistRepository artistRepository;

    private final ArtistNamesRepository artistNamesRepository;

    private final TrackRepository trackRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository, ArtistNamesRepository artistNamesRepository, TrackRepository trackRepository) {
        this.artistRepository = artistRepository;
        this.artistNamesRepository = artistNamesRepository;
        this.trackRepository = trackRepository;
    }

    @Override
    public Mono<Artist> addArtist(Artist artist) {
        var artistEntityToSave = new ArtistEntity(artist.getName());
        var artistEntityMono = artistRepository.save(artistEntityToSave).cache();
        var names= Flux.fromIterable(Optional.ofNullable(artist.getAliases()).orElse(List.of()));

        var saveAliases = artistEntityMono.flatMapMany(
                m -> names.map(name -> new ArtistAlias(m.getId(), name))
                        .doOnNext(e -> LOGGER.info("BEFORE {}", e))
                        .flatMap(artistAlias -> artistNamesRepository.save(artistAlias))
                        .doOnNext(e -> LOGGER.info("{}", e))
                )
                .map(ArtistAlias::getName)
                .collectList();

        return Mono.zip(artistEntityMono, saveAliases)
                .map(tuple -> {
                    var artistEntity = tuple.getT1();
                    var artistNames = tuple.getT2();
                    return new Artist(artistEntity.getId(), artistEntity.getName(), artistNames);
                });
    }

    @Override
    public Mono<Artist> getDailyArtist() {
        var artist = artistRepository.count().flatMap(
                count -> {
                    var time = Instant.now().getEpochSecond()/86400L;
                    var artistId = time % count;
                    return artistRepository.getById(artistId + 1 );
                }
        ).cache();

        var artistNames = artist.flatMapMany(
                artistEntity -> artistNamesRepository.getAllById(artistEntity.getId())
        ).map(ArtistAlias::getName).collectList();

        return Mono.zip(artist, artistNames)
                        .map(tuple -> {
                            var art = tuple.getT1();
                            var names = tuple.getT2();
                            return new Artist(art.getId(), art.getName(), names);
                        });
    }
}
