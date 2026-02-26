package com.crio.starter;

import com.crio.starter.service.MemeService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupCleaner {

  private final MemeService memeService;

  public StartupCleaner(MemeService memeService) {
    this.memeService = memeService;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void clearDb() {
    memeService.clearAll();
  }
}