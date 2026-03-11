package com.crio.starter.controller;

import com.crio.starter.entity.MemeEntity;
import com.crio.starter.service.MemeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/memes")
public class MemeController {

  private final MemeService memeService;

  public MemeController(MemeService memeService) {
    this.memeService = memeService;
  }

  @PostMapping
  public ResponseEntity<MemeEntity> createMeme(@RequestBody MemeEntity meme) {
    MemeEntity saved = memeService.createMeme(meme);
    return ResponseEntity.ok(saved);
  }

  @GetMapping
  public ResponseEntity<List<MemeEntity>> getMemes() {
    return ResponseEntity.ok(memeService.getLatestMemes());
  }

  @GetMapping("/{id}")
  public ResponseEntity<MemeEntity> getMeme(@PathVariable String id) {
    return ResponseEntity.ok(memeService.getMemeById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteMeme(@PathVariable String id) {
    memeService.deleteMeme(id);
    return ResponseEntity.ok("Meme deleted successfully");
  }
}