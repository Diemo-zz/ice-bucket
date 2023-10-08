package com.bucket.ice.repositories;

import com.bucket.ice.entities.ArtistEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ArtistRepository extends ReactiveCrudRepository<ArtistEntity, Long> {
    Mono<ArtistEntity> getById(Long id);
}
