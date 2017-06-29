package com.epam.pages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.epam.locators.FeedsWorkBenchQueryLocators;
import com.epam.locators.LoginPageLocators;
import com.epam.utils.CommonFunctions;
import com.epam.utils.FrameworkExceptions;
import com.epam.utils.TestDataFieldNames;

public class FeedsWorkBenchQueryPage extends PageClass{

    private static Logger LOGGER = LoggerFactory.getLogger(FeedsWorkBenchQueryPage.class);
    public FeedsWorkBenchQueryLocators getPage() { return initElements(FeedsWorkBenchQueryLocators.class); }
    private LoginPageLocators getPageLogin() { return initElements(LoginPageLocators.class); }
    private CommonFunctions getBusinessFunctionsPage() { return initElements(CommonFunctions.class); }

    public void feedsQueryWorkBench() throws FrameworkExceptions{
        try{
            getPage().getEnvironment().selectByVisibleText(TestDataFieldNames.Environment);
            getPage().getApiVersion().selectByVisibleText(TestDataFieldNames.ApiVersion);
            getPage().getAgreeForTerms().check();
            getPage().getLoginWithSalesForce().click();
            getPageLogin().getUserName().setText(TestDataFieldNames.userName);
            getPageLogin().getPassword().setText(TestDataFieldNames.password);
            getPageLogin().getLoginButton().click();
            getPage().getQueryTab().isDisplayed();
            getBusinessFunctionsPage().getMousehover(getPage().getQueryLink(), getPage().getQueryTab());
            getPage().getObject().selectByVisibleText(TestDataFieldNames.Object);
            String [] NoOfFields = TestDataFieldNames.Fields.split(",");
            for(int i = 0;i<NoOfFields.length-1;i++)
            {
                getPage().getFileds().selectByIgnoringSpecialCharacters(NoOfFields[i]);
            }
            getPage().getFilterResults().selectByVisibleText(TestDataFieldNames.FilterResults);
            getPage().getCreatedId().setText(TestDataFieldNames.CreatedId);
            getPage().getBulkCsv().click();
            getPage().getQueryBtn().click();
            getPage().getDownLoadFileImg().isLoaded();
            getPage().getDownLoadFileImg().click();
        }
        catch (Exception e) {
            LOGGER.error("Failed to login to WorkBench developer force " + e );
            throw new FrameworkExceptions("Failed in feedsQueryWorkBench() method of feedsQueryWorkBench class");
        }
    }
}