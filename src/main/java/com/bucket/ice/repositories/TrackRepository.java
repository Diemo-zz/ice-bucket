package com.bucket.ice.repositories;

import com.bucket.ice.entities.TrackEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TrackRepository extends ReactiveCrudRepository<TrackEntity, Long> {
    Flux<TrackEntity> getAllByArtistId(Long artistId);

    Flux<TrackEntity> getFirstByArtistId(Long artistId);
}


