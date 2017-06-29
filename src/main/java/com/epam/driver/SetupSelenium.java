package com.epam.driver;/**/

import com.epam.utils.Config;
import com.epam.utils.Constants;
import com.epam.utils.LogEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by vsharma on 20-09-2016.
 */

public class SetupSelenium {
	private static Logger LOGGER = LoggerFactory.getLogger(SetupSelenium.class);
	private static final ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL;
	private static WebDriver driver = null;
	public static boolean browserInitialized = false;

	static {
		WEB_DRIVER_THREAD_LOCAL = new ThreadLocal<>();
	}

	private SetupSelenium() {}

	public static void initializeDriver(String browser) {

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			LOGGER.info("Started Firefox Browser successfully.");
		} else if (browser.equalsIgnoreCase("chrome")) {
			driver = initiateChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "src/main/resources/iedriver.exe");
			driver = new InternetExplorerDriver();
			LOGGER.info("Started IE Browser successfully.");
		}
		driver = new EventFiringWebDriver(driver).register(new LogEventListener());
		setDriver(driver);
		browserInitialized = true;
	}

	private static WebDriver initiateChromeDriver() {
		WebDriver driver = null;
		try {
			String chromePath = Config.getConfig().getConfigProperty(Constants.CHROMEDRIVERPATH);
			System.setProperty("webdriver.chrome.driver", chromePath);

			ChromeDriverService chromeService = ChromeDriverService.createDefaultService();
			String commandSwitches = "WebDriverCommandSwitch";

			if (!commandSwitches.isEmpty() || commandSwitches.contains("--")) {
				LOGGER.info("User had specified [" + commandSwitches + "] command switch for Chrome Browser");
				ChromeOptions options = new ChromeOptions();
				String[] commandList = commandSwitches.split(",");
				for (String command : commandList) {
					options.addArguments(command);
					options.addArguments("--test-type");
					options.addArguments("chrome.switches",	"--disable-extensions");
				}

				driver = new ChromeDriver(chromeService, options);
				LOGGER.info("Started Google Chrome Driver with command switches successfully");
			} else {
				LOGGER.debug("Starting the Chrome Driver");
				driver = new ChromeDriver(chromeService);
				LOGGER.info("Started Google Chrome Browser successfully");
			}
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} catch (Exception e) {
			LOGGER.error("Failed to initiate Chrome Driver"+e);
		}
		return driver;
	}

	private static void setDriver(WebDriver driver) {
		WEB_DRIVER_THREAD_LOCAL.set(driver);
	}

	public static WebDriver getDriver() {
		driver = WEB_DRIVER_THREAD_LOCAL.get();
		if (driver == null)
			throw new IllegalStateException("Driver not set...");
		return driver;
	}

	public static void gotoUrl(String url) {
		getDriver().navigate().to(url);
	}
}
