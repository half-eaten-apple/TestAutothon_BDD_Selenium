package com.epam.pages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.utils.Config;
import com.epam.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.epam.locators.FeedsWorkBenchQueryLocators;
import com.epam.utils.FrameworkExceptions;

public class GetOutBoundFileName extends PageClass {
    private static Logger LOGGER = LoggerFactory.getLogger(GetOutBoundFileName.class);
    public FeedsWorkBenchQueryLocators getPage() { return initElements(FeedsWorkBenchQueryLocators.class); }


    public static File getFileName() throws FrameworkExceptions {
        try{
            File dir = new File("C://Users//" + System.getProperty("user.name") + "//Downloads//");
            File[] files = dir.listFiles();
            if (files == null || files.length == 0) {
                return null;
            }
            File lastModifiedFile = files[0];
            for (int i = 1; i < files.length; i++) {
                if(files[i].getName().contains(".csv")){
                    if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                        lastModifiedFile = files[i];
                    }
                }
            }
            return lastModifiedFile;
        }
        catch (Exception e) {
            LOGGER.error("Failed in getFileName() method of GetOutBoundFileName class " + e );
            throw new FrameworkExceptions("Failed in getFileName() method of GetOutBoundFileName class");
        }
    }
    public static Iterable<MatchResult> allMatches(final Pattern p, final CharSequence input) {
        return new Iterable<MatchResult>() {
            public Iterator<MatchResult> iterator() {
                return new Iterator<MatchResult>() {
                    // Use a matcher internally.
                    final Matcher matcher = p.matcher(input);
                    // Keep a match around that supports any interleaving of hasNext/next calls.
                    MatchResult pending;

                    public boolean hasNext() {
                        // Lazily fill pending, and avoid calling find() multiple times if the
                        // clients call hasNext() repeatedly before sampling via next().
                        if (pending == null && matcher.find()) {
                            pending = matcher.toMatchResult();
                        }
                        return pending != null;
                    }

                    public MatchResult next() {
                        // Fill pending if necessary (as when clients call next() without
                        // checking hasNext()), throw if not possible.
                        if (!hasNext())
                        {
                            throw new NoSuchElementException();
                        }
                        // Consume pending so next call to hasNext() does a find().
                        MatchResult next = pending;
                        pending = null;
                        return next;
                    }

                    /** Required to satisfy the interface, but unsupported. */
                    public void remove() { throw new UnsupportedOperationException(); }
                };
            }
        };
    }

    public static void convertCSVFileToXlSX() throws FrameworkExceptions {
        XSSFWorkbook workBook = null;
        BufferedReader br = null;
        try {
            workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("Inbound_FeedsData");
            String currentLine = null;
            ArrayList<Integer> arrIndex = new ArrayList<Integer>();
            int RowNum=-1;
            String dummy = "";
            int StartIndex = 0;
            int EndIndex = 0;
            boolean blnReplaceComma = false;
            boolean blnManiP;
            String csvFileAddress = getFileName().getAbsolutePath().toString();
            br = new BufferedReader(new FileReader(csvFileAddress));
            while ((currentLine = br.readLine()) != null)
            {
                blnManiP = false;
                if(currentLine.contains("\""))
                {
                    blnManiP = true;
                    for(MatchResult match : allMatches(Pattern.compile("\""), currentLine))
                    {
                        arrIndex.add(match.start());
                    }
                    blnReplaceComma = false;
                    dummy = "";
                    StartIndex = 0;
                    EndIndex = currentLine.length();
                    if(StartIndex == arrIndex.get(0))
                    {
                        blnReplaceComma = true;
                    }
                    for(int i=0;i<arrIndex.size();i++)
                    {
                        if(blnReplaceComma)
                        {
                            dummy = dummy.concat(currentLine.substring(StartIndex, arrIndex.get(i)).replace(",", "COMMA"));
                            blnReplaceComma = false;
                        }
                        else
                        {
                            dummy = dummy.concat(currentLine.substring(StartIndex, arrIndex.get(i)));
                            blnReplaceComma = true;
                        }
                        StartIndex = arrIndex.get(i)+1;
                    }
                    dummy = dummy.concat(currentLine.substring(StartIndex, EndIndex));
                    currentLine = dummy;
                    arrIndex.clear();
                }

                String str[] = currentLine.split(",");
                RowNum++;
                XSSFRow currentRow=sheet.createRow(RowNum);
                for(int i=0;i<str.length;i++)
                {
                    if(blnManiP)
                    {
                        currentRow.createCell(i).setCellValue(str[i].replaceAll("COMMA", ","));
                    }
                    else
                    {
                        currentRow.createCell(i).setCellValue(str[i]);
                    }
                }
                FileOutputStream fileOutputStream = new FileOutputStream((Config.getConfig().getConfigProperty(Constants.OUTBOUNDPATH)));
                workBook.write(fileOutputStream);
                fileOutputStream.close();

            }
            LOGGER.info("Converted from CSV to XlSX");
            workBook.close();
            br.close();
        }
        catch (Exception e) {
            LOGGER.error("Failed in convertCSVFileToXlsx() method of GetOutBoundFileName class " + e );
            throw new FrameworkExceptions("Failed in convertCSVFileToXlsx() method of GetOutBoundFileName class");
        }
    }
}

