package com.n26.trx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by carlos on 4/21/17.
 *
 * Main spring boot app class.
 */

@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        logger.info("Starting trx application...");
        SpringApplication.run(Application.class,args);
    }
}
