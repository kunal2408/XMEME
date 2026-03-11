package com.crio.starter.repository;

import com.crio.starter.entity.MemeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface MemeRepository extends MongoRepository<MemeEntity, String> {
  Optional<MemeEntity> findByNameAndCaptionAndUrl(String name, String caption, String url);
  List<MemeEntity> findTop100ByOrderByCreatedAtDesc();
}