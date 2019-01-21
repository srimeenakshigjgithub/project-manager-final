package com.gjsm.projectmanager.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gjsm.projectmanager.constants.ProjectManagerConstants;

public class DateUtil {
	
		private final static Logger logger = LoggerFactory.getLogger(DateUtil.class);
		
		public static String dateToString(Date date) {
			String dateInStr = ProjectManagerConstants.EMPTY;
			try {
				if(null != date) {
					SimpleDateFormat sdf = new SimpleDateFormat(ProjectManagerConstants.YYYYMMDD);
					dateInStr = sdf.format(date);
				}
			} catch(Exception e) {
				//logger.error("Error - DateUtil : " + e);
			}
			return dateInStr;
		}
		
		public static Date stringToDate(String dateInStr) {
			Date date = null;
			try {
				if(null != dateInStr) {
					SimpleDateFormat sdf = new SimpleDateFormat(ProjectManagerConstants.YYYYMMDD);
					date = sdf.parse(dateInStr);
				}
			} catch(Exception e) {
				//logger.error("Error - DateUtil : " + e);
			}
			return date;
		}
		
	}

