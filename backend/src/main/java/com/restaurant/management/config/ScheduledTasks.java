package com.restaurant.management.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class ScheduledTasks {

    @Scheduled(cron = "0 0 23 * * *")
    public void dailyClosing() {
        log.info("执行日结任务...");
    }
}
