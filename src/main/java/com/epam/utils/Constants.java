package com.epam.utils;
/**
 * Created by vsharma on 22-09-2016.
 */
public class Constants {

	public static final String PERFORM_TABLE_ACTIONS_XPATH = "(//input[@type='checkbox'])[position()=";
	public static final String GET_TABLE_INTERACTION_PAGE ="//h3[text()='Participants']/ancestor::div/following-sibling::div/table[@class='list']//";

	/** The Constant CONFIGFILE. */
	public static final String CONFIGFILEPATH = Util.getFileSeperatedPath("Configuration/config.xml");

	/** The Constant TESTDATAPATH. */
	public static final String TESTDATAPATH= "TestDataFilePath";

	/** The Constant ReportFilePath. */

	/** The Constant URL. */

	public static final String FEEDSURL = "FeedsURL";

	/** The Constant BrowserType. */
	public static final String BROWSERTYPE="BrowserType";

	/** The Constant BrowserType. */
	public static final String APPURL="AppURL";


	/**The Driver path**/
	public static final String CHROMEDRIVERPATH= "ChromeDriverExecutablePath"; //ChromeDriverExecutablePath
	public static final String IEEDRIVERPATH32Bit="IEEDRIVERPATH32Bit";
	public static final String IEEDRIVERPATH64bit="IEEDRIVERPATH64bit";

	/** The Constant BrowserType. */
	public static final String EXTENTREPORTS="ExtentReports";

	public static final String DATEFORMAT = "yyyyMMdd";

	public static final String TIMEFORMAT = "HHmmssS";
	public static final String DATETIMEFORMAT = "yyyyMMdd_HHmmssS";
	public static final String APPLICATION_NAME = "applicationName";
	public static final String APPLICATION_TYPE = "applicationType";
	public static final String TESTENVIRONMENT = "testEnvironment";
	public static final String BUILDID = "buildID";
	public static final String AUTOMATIONTOOL = "automationTool";

	public static final String DATEFORMATFOLDERNAME = "yyyyMMddHHmmssS";
	public static final String TESTRESULTPATH = "testResultPath";

	public static final String OUTBOUNDPATH = "outboundpath";
	public static final String INBOUNDPATH ="inboundpath";
	public static final String FEEDSAPPURL ="FeedsAppURL";
	public static final String ELEMENTWAITTIME ="ElementWaitTime";
	public static final String SELECTLOGMESSAGE = " is Selected";
	public static final String SELECTLOGMESSAGE_SUCCESSS = " selected successfully";
	public static final String SELECTLOGMESSAGE_FAILURE = " selection failed";
	public static final String ISLOADEDLOGMESSAGE_FAILURE = " loading failed";
	public static final String ELEMENTLOGMESSAGE = "Element element: ";
	public static final String HYPERLINKLOGMESSAGE = "HyperLink element: ";
	public static final String LABELLOGMESSAGE = "Label Element: ";
	public static final String SETTEXTLOGMESSAGE = " set text successfully";
	public static final String SCENARIOEMPLOYEEEDUCATION = "EmployeeEducation";
	public static final String SCENARIOFEEDSVALIDATION = "FeedsValidationScenario";
	public static final String SCENARIOSREGIONALEXHIBIT = "RegionalExhibit";
	public static final String NEGATIVESCENARIO = "NegativeScenarios";
	public static final String SCENARIOOFFSITEEVENING = "OffsiteEveningSpeakerProgramAZ";
	public static final String SCENARIOSPEAKERPROGRAM ="SpeakerProgram_AZ";
	public static final String SCENARIOTELECONFERENCE ="TeleconferenceAZ";
	public static final String TEXTFIELDLOGMESSAGE="TextField element: ";
	/** web Service Constants **/
	public static final String WEBSERVICEHOSTNAME ="webServicesHostName";
	public static final String WEBSERVICEPORT ="webServicesPort";
	public static final String WEBSERVICEAUTHENTICATION ="webServicesAuthentication";
	public static final String WEBSERVICEUSERKEY ="webServicesUserKey";
	public static final String WEBSERVICEPASSWORDKEY ="webServicesPasswordKey";
	public static final String WEBSERVICEUSER ="webServicesUser";
	public static final String WEBSERVICEPASSWORD ="webServicesPassword";

	public static final String RESTSERVICESAUTHENTICATION = "RestServicesAuthentication";
	public static final String RESTSERVICESUSER = "RestServicesUser";
	public static final String RESTSERVICESPASSWORD = "RestServicesPassword";


}
