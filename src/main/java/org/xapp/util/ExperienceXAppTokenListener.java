package org.xapp.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class ExperienceXAppTokenListener implements ApplicationListener<ApplicationReadyEvent> {

    public static Logger logger = LogManager.getLogger(ExperienceXAppTokenListener.class);

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("ExperienceXAppTokenListener Application is fully ready!");
    }
}
