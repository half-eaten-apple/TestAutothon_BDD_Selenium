package com.epam.driver;

import com.epam.utils.Config;
import com.epam.utils.Constants;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by vsharma on 15-11-2016.
 */
public class BaseTest {

    public static Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
    String methodName = "";
    public Assertion hardAssert;
    public SoftAssert softAssert;
    @BeforeClass
    public void init() {
        try {
            System.out.println("In Before Class");
            //LOGGER = Log4jLogger.getLog4jInstance();
            hardAssert = new Assertion();
            softAssert = new SoftAssert();
            logTemplate("TEST STARTS NOW");
        } catch(Exception e) {
            System.out.println("Exceptions:  $e");
        }
    }

    @AfterMethod
    void getMethodName(ITestResult result) {
        methodName = result.getMethod().getMethodName();
    }

    @AfterMethod
    public void closeMethodsOpenedBrowserIfAny() {
        methodName = methodName + "  TEST METHOD ENDS ";
        if(SetupSelenium.browserInitialized && SetupSelenium.getDriver() != null) {
            SetupSelenium.getDriver().close();
            SetupSelenium.getDriver().quit();
        }
        logTemplate(methodName);
        freeMemorySpace();
    }

    private void logTemplate(String strTextToBeDisplayed) {
        String logTemplate = "================================ " + strTextToBeDisplayed + "  =========================================";
        logInfo(logTemplate, logTemplate);
    }

    public void logInfo(String strLog4jLog, String strReporterLog) {
        LOGGER.info(strLog4jLog);
    }

    public void startingMethodPreReq(String ScenarioName) {
        try {
            String browser;
            System.out.println("*** Starting Scenario : " + ScenarioName + " ***");
            if(System.getenv("BROWSER") == null || System.getenv("BROWSER").equalsIgnoreCase("null")) {
                browser = Config.getConfig().getConfigProperty(Constants.BROWSERTYPE);
            }
            else{
                browser = System.getenv("BROWSER");
            }
            SetupSelenium.initializeDriver(browser);
            if (ScenarioName.equalsIgnoreCase("FeedsValidationScenario")) {
                SetupSelenium.gotoUrl(Config.getConfig().getConfigProperty(Constants.FEEDSURL));
            } else {
                SetupSelenium.gotoUrl(Config.getConfig().getConfigProperty(Constants.APPURL));
            }
            SetupSelenium.getDriver().manage().window().maximize();
            logTemplate(" TEST METHOD STARTS");
        }
        catch (Exception e)
        {
            LOGGER.error("Failed to start the scenario");
        }
    }
    private void freeMemorySpace()
    {
        Runtime r = Runtime.getRuntime();
        long freeMem = r.freeMemory();
        r.freeMemory();
        System.gc();
    }
}
