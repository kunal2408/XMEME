package com.crio.starter.service;

import java.time.LocalDateTime;
import java.util.List;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.MemeRequest;
import com.crio.starter.repository.MemeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MemeService {

  private final MemeRepository memeRepository;

  public MemeService(MemeRepository memeRepository) {
    this.memeRepository = memeRepository;
  }

  public MemeEntity createMeme(MemeRequest request) {

    if (request.getName() == null
        || request.getCaption() == null
        || request.getUrl() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    memeRepository.findByNameAndCaptionAndUrl(
        request.getName(), request.getCaption(), request.getUrl())
        .ifPresent(m -> {
          throw new ResponseStatusException(HttpStatus.CONFLICT);
        });

    MemeEntity meme = new MemeEntity();
    meme.setName(request.getName());
    meme.setCaption(request.getCaption());
    meme.setUrl(request.getUrl());

    return memeRepository.save(meme);
  }

  public List<MemeEntity> getLatestMemes() {
    return memeRepository.findTop100ByOrderByCreatedAtDesc();
  }

  public MemeEntity getMemeById(String id) {
    return memeRepository.findById(id)
        .orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public void clearAll() {
    memeRepository.deleteAll();
  }
}