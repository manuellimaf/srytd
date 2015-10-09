package ar.com.dccsoft.srytd.services.process;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessorJob implements Job {

	private Logger logger = LoggerFactory.getLogger(ProcessorJob.class);
	private static String USER = "job";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
	    JobKey jobKey = context.getJobDetail().getKey();
	    logger.info(jobKey + " - Processor job execution started");

		Date from = DateUtils.truncate(new Date(), Calendar.HOUR_OF_DAY);
		new Processor().start(from, USER);

		logger.info(jobKey + " - Processor job execution finished");
	}
}
