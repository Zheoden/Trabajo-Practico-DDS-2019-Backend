package main;

import java.util.ArrayList;
import java.util.Optional;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import http.routes.Router;
import modelo.clases.Guardarropas;
import modelo.clases.Prenda;
import modelo.clases.SuscripcionPremium;
import modelo.clases.Usuario;
import modelo.interfaces.Suscripcion;
import repository.UsuarioRepository;
import spark.Spark;
import utils.Utils;

public class Application {
	public static void main(String[] args) {
		Spark.port(7071);
		Router.register();

		// Manda el mail cada 5 minutos
//		try
//		{
//			JobDetail job = JobBuilder.newJob(Usuario.class)
//					.withIdentity("UsuarioJob")
//					.build();
//
//			Trigger trigger = TriggerBuilder.newTrigger()
//					.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
//					.build();
//
//			SchedulerFactory schFactory = new StdSchedulerFactory();
//			org.quartz.Scheduler  sch = schFactory.getScheduler();
//			sch.start();
//			sch.scheduleJob(job, trigger);
//		} catch (SchedulerException e) {
//			e.printStackTrace();
//		}
	}
}
