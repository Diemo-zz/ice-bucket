package com.bucket.ice.repositories;

import com.bucket.ice.entities.ArtistEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends ReactiveCrudRepository<ArtistEntity, Long> {
}
