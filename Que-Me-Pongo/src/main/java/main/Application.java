package main;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import http.routes.Router;
import modelo.clases.Usuario;
import spark.Spark;

public class Application {
	public static void main(String[] args) {
		Spark.port(7071);
		Router.register();
		
		try 
		{
			JobDetail job = JobBuilder.newJob(Usuario.class)
					.withIdentity("UsuarioJob")
					.build();
			 
			Trigger trigger = TriggerBuilder.newTrigger()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
									.withIntervalInSeconds(30)
									.repeatForever())
					.build();

			SchedulerFactory schFactory = new StdSchedulerFactory();
			org.quartz.Scheduler  sch = schFactory.getScheduler();
			sch.start();
			sch.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
}
}
