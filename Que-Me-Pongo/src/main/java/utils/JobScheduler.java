package utils;

import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class JobScheduler {
    Logger logger = Logger.getLogger(this.getClass().getName());

    private final ScheduledExecutorService executor;

    private JobScheduler(ScheduledExecutorService executor) {
        this.executor = executor;
    }
}
