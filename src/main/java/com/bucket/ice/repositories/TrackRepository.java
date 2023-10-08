package com.bucket.ice.repositories;

import com.bucket.ice.dtos.Track;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends ReactiveCrudRepository<Track, Long> {
}


