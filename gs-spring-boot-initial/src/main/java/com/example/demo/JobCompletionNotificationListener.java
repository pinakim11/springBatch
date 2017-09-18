/**
 * 
 */
package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author SR4PXM
 *
 */
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
	
	private static Logger log =  LoggerFactory.getLogger(JobCompletionNotificationListener.class);
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void JobCompletionNotificationListener(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution){
		if(jobExecution.getStatus() == BatchStatus.COMPLETED){
			log.info("!!! JOB FINISHED! Time to verify the results");
			
		}
		
	}

}
