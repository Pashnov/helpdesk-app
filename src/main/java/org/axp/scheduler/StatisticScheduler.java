package org.axp.scheduler;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StatisticScheduler {

    @Scheduled(cron="0 0 20 ? * SUN *")
    void calculateWeeklyStatistics() {
        // Виконайте обчислення тут
    }

}
