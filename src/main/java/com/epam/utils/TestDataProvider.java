package com.epam.utils;

import org.testng.annotations.DataProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.epam.utils.TestDataReader.getSystemDate;

/**
 * Created by vsharma on 01-12-2016.
 */
public class TestDataProvider {
    @DataProvider
    public static Object[][] speakerProgram() throws ParseException {
        return new Object[][]  {
                {   "SpeakerProgram_AZ", "madhavan_moorthi@az.prod.qa", "d@111", "Speaker Program",
                        "Speaker_Program_AZ", "Venue Based", "United States", "United States",
                        getDate("CDATE_TODAY#10"), "2:00 PM", getDate("CDATE_TODAY#10"), "4:00 PM", "U.S. Dollar", "--None--",
                        "Accepted;In Progress;Setup Complete;Closeout Required", "Brilinta", "Preferred;Alternate", "Speaker;Attendee", "1;4",
                        "HCP"
                }
        };
    }

    @DataProvider
    public static Object[][] speakerProgramOffsiteEvening() throws ParseException{
        return new Object[][]  {
                {   "OffsiteEveningSpeakerProgramAZ", "madhavan_moorthi@az.prod.qa", "test@111", "Speaker Program",
                        "Speaker_Program_AZ", "Venue Based", "United States", "United States",
                        getDate("CDATE_TODAY#10"), "2:00 PM", getDate("CDATE_TODAY#10"), "4:00 PM", "U.S. Dollar", "--None--",
                        "Accepted;In Progress;Setup Complete;Closeout Required", "Brilinta", "Preferred;Alternate", "Speaker;Attendee", "1;4",
                        "HCP"
                }
        };
    }

    public static String getDate(String strValue) throws ParseException {
        int intDays;
        Calendar objCal = Calendar.getInstance();

        SimpleDateFormat objSdf = new SimpleDateFormat("MM/dd/yyyy");

        objCal.setTime(objSdf.parse(getSystemDate()));

        if (strValue.trim().toUpperCase().equalsIgnoreCase("CDATE_TODAY"))
        {
            strValue = objSdf.format(objCal.getTime());
        } else if (strValue.trim().toUpperCase().contains("CDATE_TODAY_")) {
            String [] arrValues = strValue.toUpperCase().split("DAY_");
            intDays = Integer.parseInt(arrValues[1]);
            objCal.add(Calendar.DATE, -intDays);
            strValue = objSdf.format(objCal.getTime());
        } else if (strValue.trim().toUpperCase().contains("CDATE_TODAY#")) {
            String [] arrValues = strValue.toUpperCase().split("DAY#");
            intDays = Integer.parseInt(arrValues[1]);
            objCal.add(Calendar.DATE, intDays);
            strValue = objSdf.format(objCal.getTime());
        }
        return strValue;
    }
}
