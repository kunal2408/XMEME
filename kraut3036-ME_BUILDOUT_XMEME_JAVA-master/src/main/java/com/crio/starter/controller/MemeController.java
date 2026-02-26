package com.crio.starter.controller;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.MemeRequest;
import com.crio.starter.exchange.MemeResponse;
import com.crio.starter.service.MemeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/memes")
public class MemeController {

  private final MemeService memeService;

  public MemeController(MemeService memeService) {
    this.memeService = memeService;
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public MemeResponse createMeme(@RequestBody MemeRequest request) {
    MemeEntity saved = memeService.createMeme(request);
    return new MemeResponse(saved.getId());
  }

  @GetMapping("/")
  public List<MemeEntity> getMemes() {
    return memeService.getLatestMemes();
  }

  @GetMapping("/{id}")
  public MemeEntity getMeme(@PathVariable String id) {
    return memeService.getMemeById(id);
  }
}