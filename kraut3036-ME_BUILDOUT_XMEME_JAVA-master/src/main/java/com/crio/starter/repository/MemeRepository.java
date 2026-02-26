package com.crio.starter.repository;

import java.util.List;
import java.util.Optional;
import com.crio.starter.data.MemeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemeRepository extends MongoRepository<MemeEntity, String> {

  Optional<MemeEntity> findByNameAndCaptionAndUrl(
      String name, String caption, String url);

  List<MemeEntity> findTop100ByOrderByCreatedAtDesc();
}