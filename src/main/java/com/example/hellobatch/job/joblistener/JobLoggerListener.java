package com.example.hellobatch.job.joblistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class JobLoggerListener implements JobExecutionListener {

    private static final String BEFORE_MSG = "{} Job is Running";
    private static final String AFTER_MES = "{} Job is Done. (Status: {})";

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info(BEFORE_MSG, jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info(AFTER_MES,
                jobExecution.getJobInstance().getJobName(),
                jobExecution.getStatus());

        if (jobExecution.getStatus() == BatchStatus.FAILED) {
            // webhook을 통한 알림등을 여기다가 구현하기
            log.info("BATCH JOB FAILED!");
        }
    }
}
