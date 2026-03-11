package com.crio.starter.service;

import com.crio.starter.entity.MemeEntity;
import com.crio.starter.repository.MemeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class MemeService {

  private final MemeRepository memeRepository;

  public MemeService(MemeRepository memeRepository) {
    this.memeRepository = memeRepository;
  }

  public MemeEntity createMeme(MemeEntity meme) {
    if (meme.getName() == null || meme.getCaption() == null || meme.getUrl() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name, caption and URL are required");
    }

    // Check if the exact meme already exists
    memeRepository.findByNameAndCaptionAndUrl(
                    meme.getName(), meme.getCaption(), meme.getUrl())
            .ifPresent(existing -> {
              throw new ResponseStatusException(HttpStatus.CONFLICT, "Meme already exists");
            });

    // Save the entity **as-is** (client-supplied id is preserved)
    return memeRepository.save(meme);
  }

  public List<MemeEntity> getLatestMemes() {
    return memeRepository.findTop100ByOrderByCreatedAtDesc();
  }

  public MemeEntity getMemeById(String id) {
    return memeRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Meme not found"));
  }

  public void deleteMeme(String id) {
    MemeEntity meme = getMemeById(id);
    memeRepository.delete(meme);
  }
}