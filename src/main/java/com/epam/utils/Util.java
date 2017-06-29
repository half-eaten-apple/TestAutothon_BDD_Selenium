package com.epam.utils;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by vsharma on 22-09-2016.
 */
public class Util {

	public static int currentIterationRow = 0;

	public static String getProjectRootPath() {
		String projectRoot = System.getProperty("user.dir");

		return projectRoot;
	}

	public static String getFileSeperatedPath(String slashedPath) {
		String fullPath = getProjectRootPath() + File.separator + slashedPath;
		return fullPath.replace("/", File.separator);
	}


}
