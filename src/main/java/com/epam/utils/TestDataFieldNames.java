package com.epam.utils;
import com.epam.utils.TestDataReader;


/**
 * Created by vsharma on 17-10-2016.
 */
public class TestDataFieldNames {
    public static String userName, password, interactionCategory, interactionType, locationType, startDate,
                        startTime, endDate, interactionStatus, locationPriority, participantRole, locationStatus,
                        logisticNeed, logisticComponent, logisticEstimationCount, logisticVendor, logisticProvider,
                        estimationAmount, estimationSpendType, recordTypeOfNewRecord, newExpenseSpendType, newExDateOfExpense,
                        newExpenseAmount, newExpenseStatus, participantsRole, numberOfRoles, speakerType, ahmProduct, newInteractionValidateErrorMessage,
                        productTopicValidateErrorMessage,participantValidateErrorMessage,DownloadPath,DestinationPath,InboundPath,
                        Environment,ApiVersion,Object,Fields,FilterResults,CreatedId,RequestFileName,EndPointURL,RequestHeaders,Appenders,RequestType,ValidateResponse,
                        ResponseType ;


    public static void readVariables()
    {
        try{
            //LoginPage
            userName = TestDataReader.getMap().get("UserName");
            password = TestDataReader.getMap().get("Password");

            //InteractionPage
            interactionCategory = TestDataReader.getMap().get("InteractionCategory");
            interactionType = TestDataReader.getMap().get("InteractionType");
            locationType = TestDataReader.getMap().get("LocationType");
            startDate = TestDataReader.getMap().get("StartDate");
            startTime = TestDataReader.getMap().get("StartTime");
            endDate = TestDataReader.getMap().get("EndDate");
            interactionStatus = TestDataReader.getMap().get("Status");

            //LocationPage
            locationPriority = TestDataReader.getMap().get("LocationPriority");
            participantRole = TestDataReader.getMap().get("ParticipantsRole");
            locationStatus = TestDataReader.getMap().get("LocationStatus");

            //LogisticsPage
            logisticNeed = TestDataReader.getMap().get("LogisticNeed");
            logisticComponent = TestDataReader.getMap().get("LogisticComponent");
            logisticEstimationCount = TestDataReader.getMap().get("LogisticEstCount");
            logisticVendor = TestDataReader.getMap().get("LogisticVendor");
            logisticProvider = TestDataReader.getMap().get("LogisticProvider");

            //NewEstimatePage
            estimationAmount = TestDataReader.getMap().get("EstAmount");
            estimationSpendType = TestDataReader.getMap().get("EstSpendType");

            //NewExpensePage
            recordTypeOfNewRecord = TestDataReader.getMap().get("RecordTypeOfNewRecord");
            newExpenseSpendType = TestDataReader.getMap().get("NewExSpendType");
            newExDateOfExpense = TestDataReader.getMap().get("NewExDateOfExpense");
            newExpenseAmount = TestDataReader.getMap().get("NewExAmount");
            newExpenseStatus = TestDataReader.getMap().get("NewExStatus");

            //ParticipantPage
            participantsRole = TestDataReader.getMap().get("ParticipantsRole");
            numberOfRoles = TestDataReader.getMap().get("NoOfRoles");
            speakerType = TestDataReader.getMap().get("SpeakerType");

            //ProductTopicPage
            ahmProduct = TestDataReader.getMap().get("AHMProduct");
            newInteractionValidateErrorMessage = TestDataReader.getMap().get("NewInteractionValidateErrorMessage");
            productTopicValidateErrorMessage = TestDataReader.getMap().get("ProductTopicValidateErrorMessage");
            participantValidateErrorMessage = TestDataReader.getMap().get("ParticipantValidateErrorMessage");

            //Feeds Validation
            DownloadPath = TestDataReader.getMap().get("DownloadPath");
            DestinationPath = TestDataReader.getMap().get("DestinationPath");
            InboundPath = TestDataReader.getMap().get("InboundPath");
            Environment = TestDataReader.getMap().get("Environment");
            ApiVersion = TestDataReader.getMap().get("Apiversion");
            Object = TestDataReader.getMap().get("Object");
            Fields = TestDataReader.getMap().get("Fields");
            FilterResults = TestDataReader.getMap().get("Filterresults");
            CreatedId = TestDataReader.getMap().get("Createdid");

            //WebService
            RequestFileName = TestDataReader.getMap().get("RequestFileName");
            EndPointURL = TestDataReader.getMap().get("EndPointURL");
            RequestHeaders = TestDataReader.getMap().get("RequestHeaders");
            Appenders = TestDataReader.getMap().get("Appenders");
            RequestType = TestDataReader.getMap().get("RequestType");
            ValidateResponse = TestDataReader.getMap().get("ValidateResponse");
            ResponseType = TestDataReader.getMap().get("ResponseType");

        }
        catch (Exception e)
        {

        }


    }


}
