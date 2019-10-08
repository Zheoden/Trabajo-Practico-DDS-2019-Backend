package test.modelo.utils;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Tests para el Parser de Jackson")
public class JobSchedulerTest {

	@Test
	@DisplayName("Tests para un Job Scheduler que muestre un mensaje en pantalla")
	public void getObjectFromJSON() {
//		JobScheduler job = Mockito.mock(JobScheduler.class);
//		job.run(this::printConsole);
//		verify(job).run(this::printConsole);
	}
	
	public Void printConsole(Void t) {
		System.out.println("Este es el test de job");
		return t;
	}
}
