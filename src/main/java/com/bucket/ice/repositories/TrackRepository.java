package com.bucket.ice.repositories;

import com.bucket.ice.entities.TrackEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends ReactiveCrudRepository<TrackEntity, Long> {
}


