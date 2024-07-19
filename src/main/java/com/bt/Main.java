package com.bt;

import com.bt.processor.RouterLocationProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Starting RouterLinker application");
        try {
            new RouterLocationProcessor().process();
        } catch (Exception e) {
            logger.error("An unexpected error occurred during processing", e);
        }
        logger.info("RouterLinker application finished");
    }
}