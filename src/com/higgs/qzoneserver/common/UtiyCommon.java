package com.higgs.qzoneserver.common;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UtiyCommon {
	public static String dataFormt(Date oDate) {
		return dataFormt(oDate, "");
	}
	public static String dataFormt(Date oDate,String foramtString) {
		String result = "";
		if (oDate!=null) {
			String foramt = "yyyy-MM-dd hh:mm:ss";
			if (foramtString!=null&&foramtString.length()>0) {
				foramt = foramtString;
			}
		
			SimpleDateFormat oDateFormat = new SimpleDateFormat(foramt);
			result = oDateFormat.format(oDate);
		}
		return result;
	}
	public static Date stringParseDate(String date,String foramtString) {
		Date result = null;
		if (!isEmpty(date)) {
			String foramt = "yyyy-MM-dd hh:mm:ss";
			if (foramtString!=null&&foramtString.length()>0) {
				foramt = foramtString;
			}
		
			SimpleDateFormat oDateFormat = new SimpleDateFormat(foramt);
			try {
				result = oDateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	public static Date stringParseDate(String date) {
		return stringParseDate(date,"");
	}
	public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
	
	public static String getDateFileName() {
		int romdomNum = (int)(Math.random()*1000000);
		return dataFormt(new Date(),"yyyyMMddhhmmss")+String.valueOf(romdomNum);
	}
	public static int getParseInt(String value,int defaultValue) {
		int num=defaultValue;
		if (value!=null&&(!value.equals(""))&&value.length()>0) {
			try {
				num = Integer.parseInt(value);
			} catch (Exception e) {
				// TODO: handle exception
				num = defaultValue;
			}
		}
		return num;
	}
	public static int getParseInt(String value) {
		return getParseInt(value,0);
	}
	public static int getParseInt(Object value,int defaultValue) {
		int num = defaultValue;
		if (value!=null) {
			num=getParseInt(value.toString(), defaultValue);
		}
		return num;
	}
	public static int getParseInt(Object value) {
		int num = 0;
		if (value!=null) {
			num=getParseInt(value.toString(), 0);
		}
		return num;
	}
	
}
