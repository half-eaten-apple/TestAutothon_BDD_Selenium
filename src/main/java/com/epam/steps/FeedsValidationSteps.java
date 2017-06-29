package com.epam.steps;

import com.epam.driver.BaseTest;
import com.epam.pages.AllPages;
import com.epam.pages.ExcelSheets;
import com.epam.pages.FeedsWorkBenchQueryPage;
import com.epam.pages.GetOutBoundFileName;
import com.epam.utils.FrameworkExceptions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;

/**
 * Created by vsharma on 14-11-2016.
 */
public class FeedsValidationSteps extends BaseTest {

    @When("user is logged in and triggers query")
    public void feeds() throws FrameworkExceptions{
        try{
            AllPages.getPage(FeedsWorkBenchQueryPage.class).feedsQueryWorkBench();
            logInfo("Downloaded Latest Feed File", "Downloaded Latest Feed File");
            Thread.sleep(2000);
        } catch (Exception frameworkExceptions) {
            LOGGER.error("Failed to login and fire the query"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }

    @Then("download the feeds result file")
    public void downloadFeedFiles() throws FrameworkExceptions{
        try{
            AllPages.getPage(GetOutBoundFileName.class).getFileName();
            logInfo("Latest CSV File name fetched from downloaded Path", "Latest CSV File name fecteched from downloaded Path");

            AllPages.getPage(GetOutBoundFileName.class).convertCSVFileToXlSX();
            logInfo("Latest CSV File converted to xlsx File", "Latest CSV File converted to xlsx File");
        }  catch (FrameworkExceptions frameworkExceptions) {
            LOGGER.error("Failed to convert the file from CSV to XLSX"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }
    @Then("compare inbound and outbound files")
    public void validateFiles() throws FrameworkExceptions, IOException {
        try {
            AllPages.getPage(ExcelSheets.class).excelDataValidation();
            logInfo("Compared Inbound and Outbound for Feeds Data", "Compared Inbound and Outbound for Feeds Data");
            logInfo("Ending  FeedsValidationScenario Scenario", "Ending  FeedsValidationScenario Scenario");
        } catch (Exception frameworkExceptions) {
            LOGGER.error("Failed as data in both Inbound and OutBound don't match"+frameworkExceptions);
            throw new FrameworkExceptions(frameworkExceptions);
        }
    }
}
