package com.bucket.ice.repositories;

import com.bucket.ice.dtos.Track;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TrackRepository extends ReactiveCrudRepository<Track, Long> {
}


