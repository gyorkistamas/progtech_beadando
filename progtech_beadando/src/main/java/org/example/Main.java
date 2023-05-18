package org.example;

import LandingPage.LandingPage;
import org.apache.log4j.Logger;

public class Main {
    private static Logger logger = Logger.getLogger("Main logger");
    public static void main(String[] args) {
        LandingPage form = new LandingPage();
        logger.info("Landing page shown");
    }
}