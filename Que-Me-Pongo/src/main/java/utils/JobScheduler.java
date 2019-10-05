package utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Logger;

public class JobScheduler {
	Logger logger = Logger.getLogger(this.getClass().getName());

	private final ScheduledExecutorService executor;
	private final long initialDelay;
	private final long interval;
	private final TimeUnit unit;

	public JobScheduler() {
		this(Executors.newScheduledThreadPool(1), 0, 1, TimeUnit.MINUTES);
	}
	
	public JobScheduler(long initialDelay, long interval, TimeUnit unit) {
		this(Executors.newScheduledThreadPool(1), initialDelay, interval, unit);
	}

	private JobScheduler(ScheduledExecutorService executor, long initialDelay, long interval, TimeUnit unit) {
		this.executor = executor;
		this.initialDelay = initialDelay;
		this.interval = interval;
		this.unit = unit;
	}

	public void run(Function<Void, Void> func) {
		executor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Void t = null;
				func.apply(t);
			}
		}, this.initialDelay, interval, unit);
	}
}
