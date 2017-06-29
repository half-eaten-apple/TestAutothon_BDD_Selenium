package com.epam.scenarios;

import com.epam.driver.BaseTest;
import com.epam.pages.AllPages;
import com.epam.pages.ExcelSheets;
import com.epam.pages.FeedsWorkBenchQueryPage;
import com.epam.pages.GetOutBoundFileName;
import com.epam.utils.Config;
import com.epam.utils.Constants;
import com.epam.utils.TestDataReader;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by vsharma on 17-11-2016.
 */
public class FeedsValidationScenario extends BaseTest{

    @Test(enabled = false)
    public void feeds() throws Exception{
        try{
            TestDataReader dataReader = new TestDataReader();
            logInfo("Starting Feeds Validation Scenario", "Starting Speaker Program Scenario");
            startingMethodPreReq("FeedsValidationScenario");
            dataReader.readScenarioData("FeedsValidationScenario");
            Map<String, String> treeMap = dataReader.getEntityData("FeedsValidationScenario", "Data", TestDataReader.currentIterationRow);

            AllPages.getPage(FeedsWorkBenchQueryPage.class).feedsQueryWorkBench();
            logInfo("Downloaded Latest Feed File", "Downloaded Latest Feed File");
            Thread.sleep(2000);

            AllPages.getPage(GetOutBoundFileName.class).getFileName();
            logInfo("Latest CSV File name fetched from downloaded Path", "Latest CSV File name fetched from downloaded Path");

            AllPages.getPage(GetOutBoundFileName.class).convertCSVFileToXlSX();
            logInfo("Latest CSV File converted to xlsx File", "Latest CSV File converted to xlsx File");

            AllPages.getPage(ExcelSheets.class).excelDataValidation();
            logInfo("Compared Inbound and Outbound for Feeds Data", "Compared Inbound and Outbound for Feeds Data");

            logInfo("Ending  FeedsValidationScenario Scenario", "Ending  FeedsValidationScenario Scenario");

        }
        catch (Exception e) {
            LOGGER.error("Failed in Feeds Validation scenario"+e);
        }
    }
}
