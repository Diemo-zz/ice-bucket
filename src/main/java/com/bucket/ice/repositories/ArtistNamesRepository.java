package com.bucket.ice.repositories;

import com.bucket.ice.entities.ArtistAlias;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistNamesRepository extends ReactiveCrudRepository<ArtistAlias, Long> {
}
