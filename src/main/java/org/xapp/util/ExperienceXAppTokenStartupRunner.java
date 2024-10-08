package org.xapp.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ExperienceXAppTokenStartupRunner implements CommandLineRunner {

    public static Logger logger = LogManager.getLogger(ExperienceXAppTokenStartupRunner.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("ExperienceXAppTokenStartupRunner startup runner");
    }
}
