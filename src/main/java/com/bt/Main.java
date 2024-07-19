package com.bt;

import com.bt.processor.RouterLocationProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The entry point of the RouterLinker application.
 * This class initializes and starts the process.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * The main method which serves as the entry point for the RouterLinker application.
     * It initializes the {@link RouterLocationProcessor} and starts the data processing.
     *
     * @param args Command line arguments (not used).
     */
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
