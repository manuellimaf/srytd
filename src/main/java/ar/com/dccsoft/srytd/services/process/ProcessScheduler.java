package ar.com.dccsoft.srytd.services.process;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.utils.Config;

public class ProcessScheduler {
	
	private static Logger logger = LoggerFactory.getLogger(ProcessScheduler.class);
	private static Scheduler sched = null;
	
	public synchronized static void init() {
		logger.info("Initializing Job Scheduler");
		if(sched == null) {
			try {
				initialize();
				logger.info("Job Scheduler initialized");
			} catch (SchedulerException e) {
				throw new RuntimeException("Error initializing scheduler", e);
			}
		} else {
			logger.info("Scheduler is already initialized");
		}
	}
	
	private static void initialize() throws SchedulerException {
		SchedulerFactory sf = new StdSchedulerFactory();
		sched = sf.getScheduler();
		
		JobDetail job = newJob(ProcessorJob.class)
				.withIdentity("r318-processor", "group")
				.build();

		String expression = Config.getJobExpression();
		
		Trigger trigger = newTrigger()
				.withIdentity("r318-trigger", "group")
				.withSchedule(cronSchedule(expression))
				.build();

		logger.info(String.format("Job Scheduler: Using '%s' as expression.", expression));

		sched.scheduleJob(job, trigger);
		sched.start();
		
	}

	public static void terminate() {
		try {
			sched.shutdown(true);
		} catch (SchedulerException e) {
			logger.error("Error shutting down job scheduler", e);
		}
	}

}
