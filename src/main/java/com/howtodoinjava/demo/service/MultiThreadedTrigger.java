package com.howtodoinjava.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MultiThreadedTrigger {

	private static final Logger LOGGER = LoggerFactory.getLogger(MultiThreadedTrigger.class);
	
	@Autowired
    private ApplicationContext context;
	
	public void run(int numberOfRequests, int numberOfThreads) throws Exception {
        LOGGER.debug("Spring Boot multithreaded example has started....");
        LOGGER.debug("Number of requests: " + numberOfRequests);
        LOGGER.debug("Number of threads: " + numberOfThreads);
        LOGGER.debug("Number of processors: " + Runtime.getRuntime().availableProcessors());

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<PopulateMasterDataThread> tasks = new ArrayList<>(numberOfRequests);

        for (int i = 0; i < numberOfRequests; i++) {
        	PopulateMasterDataThread wt = context.getBean(PopulateMasterDataThread.class, String.valueOf(i));
            tasks.add(wt);
        }

        List<Future<String>> futures = executorService.invokeAll(tasks);

        for (Future<String> future : futures) {
            String result = future.get(10000, TimeUnit.MILLISECONDS);
            LOGGER.debug("Thread reply results [" + result + "]");
        }

        executorService.shutdown();

        LOGGER.debug("Spring Boot multithreaded example has ended....");
    }
}
