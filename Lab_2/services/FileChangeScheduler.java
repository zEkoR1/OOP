package Lab_2.services;

import java.util.concurrent.*;

public class FileChangeScheduler {
    private ScheduledExecutorService scheduler;

    public FileChangeScheduler() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void start(String folderPath) {
        scheduler.scheduleAtFixedRate(new FileChangeDetector(folderPath), 0, 5, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }
}

