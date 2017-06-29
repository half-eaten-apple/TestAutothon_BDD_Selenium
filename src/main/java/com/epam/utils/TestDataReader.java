package com.epam.utils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestDataReader {
	private static Logger LOGGER = Logger.getLogger(TestDataReader.class);
	public static TreeMap<String, String> treeMap = new TreeMap<>();

	private String path;
	private FileInputStream file;
	private static FileInputStream fis = null;
	private FileOutputStream fileOut = null;
	private HSSFSheet sheet = null;
	private static HSSFWorkbook workbook = null;
	private HSSFRow row = null;
	private HSSFCell cell = null;
    public static int currentIterationRow = 0;

	public TestDataReader() {
		try {
			fis = new FileInputStream(Config.getConfig().getConfigProperty(Constants.TESTDATAPATH));
			workbook = new HSSFWorkbook(fis);
			fis.close();
		} catch (Exception e) {
			LOGGER.info("Please check the excel file and excel sheet" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*Function Name : getEntityData
	 * Returns		: Scenarios specified data in the form of ArrayList of ArrayList Strings
	 * Arguments	: SheetName(Sheet Data to read),ScenarioID(The scenario against which data need to be fetched)
	 */

	public static Map<String, String> getEntityData(String scenarioName, String sheetName, int row)
			throws Exception {
		try
		{
			int firstRowNumber, lastRowNumber, firstCellNumber, noofcolumns = 0, tempcols;
			HSSFRow headerRow = null;
			HSSFRow tempRow = null;
			HSSFRow firstRow = null;
			ArrayList<String> arrayListData = null;

			ArrayList<String> arrayListScenario = null;
			ArrayList<ArrayList<String>> arrayListScenarios = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> arrayListsData = new ArrayList<ArrayList<String>>();

			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheet(sheetName);
			firstRowNumber = sheet.getFirstRowNum();
			lastRowNumber = sheet.getLastRowNum();

			firstRow = sheet.getRow(lastRowNumber);

			firstCellNumber = firstRow.getFirstCellNum();
			for (int irow = (firstRowNumber+1) ;irow <= lastRowNumber; irow++)
			{
				tempRow = sheet.getRow(irow);
				tempcols = tempRow.getLastCellNum();
				if (noofcolumns < tempcols)
				{
					noofcolumns = tempcols;
				}
			}


			//            for(int row = (firstRowNumber+1) ;row <= lastRowNumber; row++)
			//            {
			tempRow = sheet.getRow(row);
			arrayListScenario = new ArrayList<String>();
			for(int col = (firstCellNumber);col<noofcolumns;col++)
			{
				if(tempRow.getCell(col) == null)
				{
					arrayListScenario.add("");
				}
				else
				{
					arrayListScenario.add(tempRow.getCell(col).toString());
				}
			}
			arrayListScenarios.add(arrayListScenario);
			//            }
			for(ArrayList<String> individualScenarios : arrayListScenarios )
			{
				if(individualScenarios.get(0).equalsIgnoreCase(scenarioName))
				{
					arrayListData = new ArrayList<String>();
					for(int i = 1;i<individualScenarios.size();i++)
					{
						headerRow = sheet.getRow(0);
						HSSFCell ColumHeader = headerRow.getCell(i);
						String strValue = individualScenarios.get(i);

						//Get date
						if(strValue.toUpperCase().startsWith("CDATE_"))
						{
							strValue = getDate(strValue);
						}

						//Get time
						if(strValue.toUpperCase().startsWith("CTIME_"))
						{
							strValue = getTime(strValue);
						}

						//Get unique data with timestamp
						if(strValue.toUpperCase().startsWith("UNIQUE"))
						{
							strValue = getUniqueValue(strValue);
						}

						//						if((strValue != null) && (!strValue.equalsIgnoreCase("Null")) && (strValue.trim().length()!=0) &&
						//								(ColumHeader != null) && (! ColumHeader.toString().equalsIgnoreCase("Null")) &&(ColumHeader.toString().trim().length()!=0))
						//						{
						//Adding to map object
						treeMap.put(headerRow.getCell(i).toString(), strValue);
						strValue = "";
						//						}

					}
					arrayListsData.add(arrayListData);
					break;
				}
			}
		}
		catch(NullPointerException ne)
		{
			System.out.println("Exception while reading test data from sheet "+sheetName);
			throw ne;
		}
		catch(Exception e)
		{
			throw e;
		}
		return treeMap;
	}

    public static void readScenarioData(String scenarioName) {
        try {
            try (FileInputStream fileInputStream = new FileInputStream(Config.getConfig().getConfigProperty(Constants.TESTDATAPATH)))
			{
                HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
                HSSFSheet sheet = workbook.getSheet("Data");
                int testCasesCount = sheet.getLastRowNum();
                for (int tcid = 1; tcid <= testCasesCount; tcid++) {
                    if (sheet.getRow(tcid).getCell(0).toString().equalsIgnoreCase(scenarioName)) {
                        String scenario = sheet.getRow(tcid).getCell(0).toString();
                        TreeMap<String, TreeMap<String, String>> scenarioDataMap = TestDataReader.getEntityData(scenario, "Data");
                        int iterationcount = 0;
                        for (Map.Entry<String, TreeMap<String, String>> entry : scenarioDataMap.entrySet()) {
                            iterationcount++;
                            currentIterationRow = Integer.parseInt(entry.getKey());
                        }
                    }
                }
                fileInputStream.close();
            }
        }   catch(Exception e)     {

        }
    }

	/*	  1. Current Date:CDATE_TODAY -(Returns current date)
          2.Future Date:CDATE_TODAY#4(Adds 4 days to the current date and
              returns the value in date format specified).
          3.Past Date: CDATE_TODAY_4(Subtract 4 days to the current date and
              returns the value in date format specified). */
	public static String getDate(String strValue) throws ArrayIndexOutOfBoundsException,Exception
	{
		int intDays;
		try
		{
			Calendar objCal = Calendar.getInstance();

			SimpleDateFormat objSdf = new SimpleDateFormat("MM/dd/yyyy");

			objCal.setTime(objSdf.parse(getSystemDate()));

			if (strValue.trim().toUpperCase().equalsIgnoreCase("CDATE_TODAY"))
			{
				strValue = objSdf.format(objCal.getTime());
			}else if (strValue.trim().toUpperCase().contains("CDATE_TODAY_"))
			{
				String [] arrValues = strValue.toUpperCase().split("DAY_");
				intDays = Integer.parseInt(arrValues[1]);
				objCal.add(Calendar.DATE, -intDays);
				strValue = objSdf.format(objCal.getTime());
			}else if (strValue.trim().toUpperCase().contains("CDATE_TODAY#"))
			{
				String [] arrValues = strValue.toUpperCase().split("DAY#");
				intDays = Integer.parseInt(arrValues[1]);
				objCal.add(Calendar.DATE, intDays);
				strValue = objSdf.format(objCal.getTime());
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			throw new RuntimeException(e.toString());
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.toString());
		}
		return strValue;
	}

	/* 1.CTIME_TODAY : Current time
      2.CTIME_TODAY_HOURS_3:(Decrement of 3 hours to current time).
      3.CTIME_TODAY_HOURS#3(Increment of 3 hours to current time)
      4.CTIME_TODAY_MIN_3(Decrement of 3 minutes to current time)
      5.CTIME_TODAY_MIN#3(Increment of 3 minutes to current time)
      6.CTIME_TODAY_SECONDS_3(Decrement of 3 seconds to current time)
      7.CTIME_TODAY_SECONDS#3(Increment of 3 seconds to current time)  */
	public static String getTime(String strValue) throws ArrayIndexOutOfBoundsException,Exception
	{
		int intHours;
		int intMinutes;
		int intSeconds;
		try
		{
			Calendar objCal = Calendar.getInstance();

			SimpleDateFormat objSdf = new SimpleDateFormat("hh:mm a");

			if (strValue.trim().toUpperCase().equalsIgnoreCase("CTIME_TODAY"))
			{
				strValue = objSdf.format(objCal.getTime());
			}
			else if (strValue.trim().toUpperCase().contains("CTIME_TODAY_HOURS_"))
			{
				String [] arrValues = strValue.toUpperCase().split("HOURS_");
				intHours = Integer.parseInt(arrValues[1]);
				objCal.add(Calendar.HOUR, -intHours);
				strValue = objSdf.format(objCal.getTime());
			}
			else if (strValue.trim().toUpperCase().contains("CTIME_TODAY_HOURS#"))
			{
				String [] arrValues = strValue.toUpperCase().split("HOURS#");
				intHours = Integer.parseInt(arrValues[1]);
				objCal.add(Calendar.HOUR, intHours);
				strValue = objSdf.format(objCal.getTime());
			}
			else if (strValue.trim().toUpperCase().contains("CTIME_TODAY_MIN_"))
			{
				String [] arrValues = strValue.toUpperCase().split("MIN_");
				intMinutes = Integer.parseInt(arrValues[1]);
				objCal.add(Calendar.MINUTE, -intMinutes);
				strValue = objSdf.format(objCal.getTime());
			}
			else if (strValue.trim().toUpperCase().contains("CTIME_TODAY_MIN#"))
			{
				String [] arrValues = strValue.toUpperCase().split("MIN#");
				intMinutes = Integer.parseInt(arrValues[1]);
				objCal.add(Calendar.MINUTE, intMinutes);
				strValue = objSdf.format(objCal.getTime());
			}
			else if (strValue.trim().toUpperCase().contains("CTIME_TODAY_SECONDS_"))
			{
				String [] arrValues = strValue.toUpperCase().split("SECONDS_");
				intSeconds = Integer.parseInt(arrValues[1]);
				objCal.add(Calendar.SECOND, -intSeconds);
				strValue = objSdf.format(objCal.getTime());
			}
			else if (strValue.trim().toUpperCase().contains("CTIME_TODAY_SECONDS#"))
			{
				String [] arrValues = strValue.toUpperCase().split("SECONDS#");
				intSeconds = Integer.parseInt(arrValues[1]);
				objCal.add(Calendar.SECOND, intSeconds);
				strValue = objSdf.format(objCal.getTime());
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			throw new RuntimeException(e.toString());
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.toString());
		}
		return strValue;
	}

	/* input should start with 'unique' keyword ex : UniqueSamuel */
	public static String getUniqueValue(String strValue) throws InterruptedException
	{
		Thread.sleep(1000);
		Calendar objCalNow = Calendar.getInstance();
		if (strValue.equalsIgnoreCase(""))
		{
			return strValue;
		}
		else
		{
			strValue = strValue + objCalNow.get(Calendar.YEAR)
					+ (objCalNow.get(Calendar.MONTH)+1)
					+ objCalNow.get(Calendar.DAY_OF_MONTH)
					+ objCalNow.get(Calendar.HOUR)
					+ objCalNow.get(Calendar.MINUTE)
					+ objCalNow.get(Calendar.SECOND)
			;
			objCalNow = null;
			strValue = strValue.replace("UNIQUE", "");
			strValue = strValue.replace("unique", "");
			return strValue;
		}
	}


	public static String getSystemDate()
	{
		String strValue = "";
		try
		{
			Calendar objCal = Calendar.getInstance();
			SimpleDateFormat objSdf = new SimpleDateFormat("MM/dd/yyyy");
			strValue = objSdf.format(objCal.getTime());
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.toString());
		}

		return strValue;
	}

	public static String removeLeadingCharacters(String strInput, String strLeadingCharacter, String strDelimitor)
	{
		try
		{
			String strArray[] = null;
			if (strDelimitor.length() == 0)
			{
				strArray = new String[1];
				strArray[0] = strInput;
			}
			else
			{
				strArray = strInput.split(strDelimitor);
			}
			String strTemp = "";
			strInput = "";
			for (int i= 0 ;i< strArray.length; i++)
			{
				strTemp = strArray[i].replaceAll("^"+strLeadingCharacter+"+", "");
				//				strTemp = StringUtils.stripStart(strArray[i],strTrailingCharacter);
				if ( i!=strArray.length-1)
				{
					strInput = strInput+strTemp+strDelimitor;
				}
				else
				{
					strInput = strInput+strTemp;
				}
			}

		}
		catch(Exception e)
		{
			throw new RuntimeException(e.toString());
		}

		return strInput;
	}
	/*Function Name : getEntityData
	 * Returns		: Scenarios specified data in the form of ArrayList of ArrayList Strings
	 * Arguments	: SheetName(Sheet Data to read),ScenarioID(The scenario against which data need to be fetched)
	 */

	public static TreeMap<String, TreeMap<String, String>> getEntityData(String senarioName,String sheetName)
			throws Exception {
		TreeMap<String, TreeMap<String, String>> scenarioDataMap = new TreeMap<String, TreeMap<String, String>>();
		TreeMap<String, String> rowdata = new TreeMap<String, String>();
		try {
			int firstRowNumber, lastRowNumber, firstCellNumber, noofcolumns = 0, tempcols;
			HSSFRow headerRow = null;
			HSSFRow tempRow = null;
			HSSFRow firstRow = null;
			ArrayList<String> arrayListData = null;

			ArrayList<String> arrayListScenario = null;

			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheet(sheetName);
			firstRowNumber = sheet.getFirstRowNum();
			lastRowNumber = sheet.getLastRowNum();

			firstRow = sheet.getRow(lastRowNumber);

			firstCellNumber = firstRow.getFirstCellNum();
			for (int row = (firstRowNumber+1); row <= lastRowNumber; row++) {
				tempRow = sheet.getRow(row);
				tempcols = tempRow.getLastCellNum();
				if (noofcolumns < tempcols) {
					noofcolumns = tempcols;
				}
			}


			String iteration;
			int scenarioiterator = 1;
			headerRow = sheet.getRow(0);
			for (int row = (firstRowNumber + 1); row <= lastRowNumber; row++)
			{

				tempRow = sheet.getRow(row);

				if (tempRow!=null && tempRow.getCell(0).toString().equalsIgnoreCase(senarioName))
				{
					rowdata = new TreeMap<String, String>();
					iteration = String.valueOf(row); //"row" + scenarioiterator;
					arrayListScenario = new ArrayList<String>();
					for (int col = (firstCellNumber); col < noofcolumns; col++)
					{
						if (tempRow.getCell(col) == null)
						{
							arrayListScenario.add("");
						}
						else
						{
							String strValue = tempRow.getCell(col).toString();
							//Get date
							if (strValue.toUpperCase().startsWith("CDATE_")) {
								strValue = getDate(strValue);
							}

							//Get time
							if (strValue.toUpperCase().startsWith("CTIME_")) {
								strValue = getTime(strValue);
							}

							//Get unique data with timestamp
							if (strValue.toUpperCase().startsWith("UNIQUE")) {
								strValue = getUniqueValue(strValue);
							}

							//                            arrayListScenario.add(strValue);
							rowdata.put(headerRow.getCell(col).toString(), strValue);

						}
					}

					scenarioiterator++;
					scenarioDataMap.put(iteration, rowdata);
				}
			}
		}
		catch(NullPointerException ne)
		{
			System.out.println("Exception while reading test data from sheet "+sheetName);
			throw ne;
		}
		catch(Exception e)
		{
			throw e;
		}
		return scenarioDataMap;
	}

	public static Map<String, String> getMap() throws FrameworkExceptions {
		if(!treeMap.isEmpty())
			return treeMap;
		else
			throw new FrameworkExceptions("Map is not initialized");
	}
}
