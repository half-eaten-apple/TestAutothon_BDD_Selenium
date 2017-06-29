package com.epam.pages;

import com.epam.utils.FrameworkExceptions;
import com.epam.utils.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.epam.locators.DisclosureAgreementLocators;
import com.epam.locators.LoginPageLocators;

public class DisclosureAgreementPage extends PageClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisclosureAgreementPage.class);
    private DisclosureAgreementLocators getPage() { return initElements(DisclosureAgreementLocators.class);
    }
    private LoginPageLocators getPageLogin() {
        return initElements(LoginPageLocators.class);
    }
    public void addDisclosureAgreement() throws FrameworkExceptions {
        try {
            Services.waitForPageLoadJS();
            getPage().getCloseOutSummary().isLoaded();
            getPage().getCloseOutSummary().click();
            getPage().getDisclosureAgreement().click();
            getPage().getDisAckAcceptKnowledgement().click();
            getPage().getPartReconciled().check();
            getPage().getSheetSubmitted().check();
            getPage().getExpReconciled().check();
            getPage().getSurveyCompleted().check();
            getPage().getIntSummaryCloseOut().click();


        } catch (Exception e) {
            LOGGER.error("Failed to update accept status interaction " + e);
            throw new FrameworkExceptions("Failed in addDisclosureAgreement() method of InteractionPage class");
        }

    }

    public void validateInteractionIsComplete() throws FrameworkExceptions {
        {
            try {
                String strInteraction = getPage().getInterActionStatus().getText();
                if (("Completed").equalsIgnoreCase(strInteraction)) {
                    LOGGER.info("Interaction Completed successfully");
                } else {
                    LOGGER.error("Failed to complete interaction");
                }
                getPageLogin().getTestOperations().click();
                getPageLogin().getLogOut().click();
            } catch (Exception e) {
                LOGGER.error("Failed to complete the interaction " + e);
                throw new FrameworkExceptions("Failed in validateInteractionIsComplete() method of InteractionPage class");
            }
        }
    }
}