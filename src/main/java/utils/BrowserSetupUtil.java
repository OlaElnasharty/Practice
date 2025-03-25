package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.net.URL;
import java.util.logging.Logger;
/////////utility classs
public class BrowserSetupUtil {

    private static final Logger logger = Logger.getLogger(BrowserSetupUtil.class.getName());

    public static WebDriver setupDriver(String executionType, String executionAddress, String browser) {
        WebDriver driver = null;

        try {
            logger.info("Starting browser setup...");

            if ("remote".equalsIgnoreCase(executionType)) {
                logger.info("Execution type: Remote");
                logger.info("Connecting to Selenium Grid at: " + executionAddress);

                if ("chrome".equalsIgnoreCase(browser)) {
                    logger.info("Browser selected: Chrome");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    driver = new RemoteWebDriver(new URL(executionAddress), chromeOptions);
                    logger.info("Chrome RemoteWebDriver initialized successfully.");
                } else if ("firefox".equalsIgnoreCase(browser)) {
                    logger.info("Browser selected: Firefox");
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driver = new RemoteWebDriver(new URL(executionAddress), firefoxOptions);
                    logger.info("Firefox RemoteWebDriver initialized successfully.");
                } else {
                    logger.severe("Unsupported browser: " + browser);
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
                }

            } else {
                logger.severe("Local execution is not supported in this utility.");
                throw new UnsupportedOperationException("Local execution is not supported in this utility.");
            }

        } catch (Exception e) {
            logger.severe("Failed to initialize the WebDriver: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize the WebDriver!");
        }

        logger.info("Browser setup completed successfully.");
        return driver;
    }
}
