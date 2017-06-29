package com.epam.pages;

import com.epam.locators.DisclosureAgreementLocators;
import com.epam.locators.InteractionPageLocators;
import com.epam.utils.CommonFunctions;
import com.epam.utils.FrameworkExceptions;
import com.epam.utils.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vsharma on 01-12-2016.
 */
public class InteractionPage_DP extends PageClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(InteractionPage.class);

    private InteractionPageLocators getPage() { return initElements(InteractionPageLocators.class); }
    private DisclosureAgreementLocators getDisclosureAgreementPage() {
        return initElements(DisclosureAgreementLocators.class);
    }
    private CommonFunctions getCommonFunctions() { return initElements(CommonFunctions.class); }

    public boolean createNewInteraction(String interactionCategory, String interactionType, String locationType,
                                     String startDate, String startTime, String endDate) throws FrameworkExceptions {
        try {
            //Services.waitForPageLoadJS();
            getPage().getInteractionTab().clickLink();
            Services.jsExecutor("arguments[0].click();", getPage().getInteractionBtnNew().getElement());

            if(Services.waitForElementInWhile(getPage().getNewInteractionInteractionCategory().getElement(), 3))
                Services.waitForPageLoadJS();

            getPage().setNewInteractionInteractionCategory(interactionCategory);
            if (getPage().getNewInteractionInterActionType().waitUntilSelectOptionsPopulated())
                getPage().setNewInteractionInterActionType(interactionType);
            getPage().setNewInteractionLocationType(locationType);
            getPage().setNewInteractionStartDate(startDate);
            getPage().setNewInteractionStartTime(startTime);
            getPage().setNewInteractionEndDate(endDate);
            getPage().getNewInteractionSave().click();
            return getPage().getInteractionTab().isLoaded();
        } catch(Exception e) {
            LOGGER.error("Failed while creating interaction " + e );
            throw new FrameworkExceptions("Failed in createNewInteraction() method of InteractionPage class");
        }
    }
}
