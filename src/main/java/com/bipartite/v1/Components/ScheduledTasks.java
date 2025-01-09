package com.bipartite.v1.Components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bipartite.v1.Service.DatabaseCleanupService;

@Component
public class ScheduledTasks {
    @Autowired
    DatabaseCleanupService databaseCleanupService;
    @Scheduled(cron = "0 0 * * * ?")
    public void cleanDB(){
        databaseCleanupService.pruneUnnecessaryData();
    }
}
