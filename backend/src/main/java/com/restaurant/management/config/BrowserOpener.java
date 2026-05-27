package com.restaurant.management.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BrowserOpener {

    @PostConstruct
    public void openBrowser() {
        log.info("请手动访问: http://localhost:8080");
    }
}
