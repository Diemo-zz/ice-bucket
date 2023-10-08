package com.bucket.ice.services.artist;

import com.bucket.ice.dtos.Artist;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface ArtistService {

    Mono<Artist> addArtist(Artist artist);

    Mono<Artist> getDailyArtist();

    Mono<Artist> addName(Long artistId, String newAlias);
}
