package com.bucket.ice.services.artist;

import com.bucket.ice.dtos.Artist;
import com.bucket.ice.entities.ArtistAlias;
import com.bucket.ice.entities.ArtistEntity;
import com.bucket.ice.repositories.ArtistNamesRepository;
import com.bucket.ice.repositories.ArtistRepository;
import com.bucket.ice.repositories.TrackRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService{

    private final ArtistRepository artistRepository;

    private final ArtistNamesRepository artistNamesRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository, ArtistNamesRepository artistNamesRepository) {
        this.artistRepository = artistRepository;
        this.artistNamesRepository = artistNamesRepository;
    }

    @Override
    public Mono<Artist> addArtist(Artist artist) {
        var artistEntityToSave = new ArtistEntity(artist.getName());
        var artistEntityMono = artistRepository.save(artistEntityToSave).cache();
        var names= Flux.fromIterable(Optional.ofNullable(artist.getAliases()).orElse(List.of()));

        var saveAliases = artistEntityMono.flatMapMany(
                m -> names.map(name -> new ArtistAlias(m.getId(), name))
                        .flatMap(artistAlias -> artistNamesRepository.save(artistAlias)))
                .map(ArtistAlias::getName)
                .collectList();

        return getArtistMono(artistEntityMono, saveAliases);
    }

    private Mono<Artist> getArtistMono(Mono<ArtistEntity> artistEntityMono, Mono<List<String>> saveAliases) {
        return Mono.zip(artistEntityMono, saveAliases)
                .map(tuple -> {
                    var artistEntity = tuple.getT1();
                    var artistNames = tuple.getT2();
                    return new Artist(artistEntity.getId(), artistEntity.getName(), artistNames);
                });
    }

    @Override
    public Mono<Artist> addName(Long artistId, String newAlias) {
        var aliases = artistNamesRepository.save(new ArtistAlias(artistId, newAlias))
                .flatMapMany(artistAlias -> artistNamesRepository.getAllById(artistId))
                .map(ArtistAlias::getName)
                .collectList();

        var artistInfo = artistRepository.getById(artistId);

        return getArtistMono(artistInfo, aliases);
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
