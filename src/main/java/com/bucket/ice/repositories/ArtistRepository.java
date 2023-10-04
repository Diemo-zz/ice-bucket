package com.bucket.ice.repositories;

import com.bucket.ice.dtos.Artist;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends ReactiveCrudRepository<Artist, Long> {
}
