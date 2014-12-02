package com.web.util;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormatUtil {

	public static final String CASE_TYPE_NORMAL = "NORMAL";
	public static final String CASE_TYPE_UPPER = "UPPER";
	public static final String CASE_TYPE_LOWER = "LOWER";

	private HttpServletRequest request;
	private HttpServletResponse response;

	public FormatUtil() {

	}

	public FormatUtil(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public static String trimAndUpperString(String str) {
		if (str != null)
			return str.trim().toUpperCase();
		else
			return str;
	}

	public static String nullToBlank(String str) {
		if (str != null)
			return str.trim();
		else
			return "";
	}

	public static String toStringTrim(String str) {
		if (str != null)
			return str.trim();
		else
			return "";
	}

	public static String dateToString(Date dDate, String stFormat) {
		String stDate = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(stFormat, Locale.US);
			stDate = sdf.format(dDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stDate;
	}

	public static String getParameterToStringUpper(HttpServletRequest request,
			String parameter) {
		return getParameterToString(request, parameter, "", CASE_TYPE_UPPER);
	}

	public static String getParameterToString(HttpServletRequest request,
			String parameter, String _default, String _caseType) {
		if (request != null && parameter != null && !"".equals(parameter)) {
			return convertStringToCaseType(nullToStr(request
					.getParameter(parameter), _default), _caseType);
		} else {
			return convertStringToCaseType(_default, _caseType);
		}
	}

	public static String nullToStr(String str) {
		return (str == null ? "" : str.trim());
	}

	public static String nullToStr(String str, String initStr) {
		return (str == null ? initStr : str.trim());
	}

	public static String chkNullForCheckBox(String str) {
		return str == null || str.equals("") ? "N" : str;
	}

	public static String convertStringToCaseType(String s, String caseType) {
		if (!isEmptyString(s)) {
			if (CASE_TYPE_UPPER.equals(caseType)) {
				return s.trim().toUpperCase();
			} else if (CASE_TYPE_LOWER.equals(caseType)) {
				return s.trim().toLowerCase();
			} else {
				return s.trim();
			}
		} else {
			return "";
		}
	}

	// ##02 BEGIN
	/**
	 * Returns true, if the input string is empty, otherwise false
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmptyString(String s) {
		if (s == null || "".equals(s.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param s
	 * @return
	 */
	public static String stringToDb(String s) {
		return stringToDb(s, "");
	}

	/**
	 * Returns follow as _default string, if the input string is empty,
	 * otherwise the input string
	 * 
	 * @param s
	 * @param _default
	 * @return
	 */
	public static String stringToDb(String s, String _default) {
		return ((FormatUtil.isEmptyString(s)) ? _default : s.trim());
	}

	/**
	 * @param map
	 * @param field
	 * @return
	 */
	public static String dbToString(Map map, String field) {
		return dbToString(map, field, "");
	}

	/**
	 * @param map
	 * @param field
	 * @param _default
	 * @return
	 */
	public static String dbToString(Map map, String field, String _default) {
		String outValue = null;
		try {
			if (map != null && map.size() > 0 && !isEmptyString(field)) {
				outValue = (String) map.get(field);
				outValue = (isEmptyString(outValue)) ? _default : outValue;
			} else {
				outValue = _default;
			}
		} catch (Exception e) {
			outValue = _default;
		}
		return outValue;
	}

	public static String dateFormat(String av_date, String av_currFormat,
			String av_toFormat) {
		System.out.println("[FormatUtil][dateFormat][Begin]");

		SimpleDateFormat dt = null;
		Date date = null;
		SimpleDateFormat dt1 = null;
		String dateFormat = null;

		try {
			if (av_date == null || av_date.equals("")) {
				dateFormat = "";
			} else {
				dt = new SimpleDateFormat(av_currFormat);
				date = dt.parse(av_date);
				dt1 = new SimpleDateFormat(av_toFormat, Locale.US);

				dateFormat = dt1.format(date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("[FormatUtil][dateFormat][End]");
		}

		return dateFormat;
	}

	public void writeMSG(String msg) {
		PrintWriter print = null;
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			
			print = this.response.getWriter();
			print.write(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String statusRecDesc(String status) {
		System.out.println("[FormatUtil][statusRecDesc][Begin]");

		String statusDesc = "";

		try {
			System.out.println("[FormatUtil][statusRecDesc] status :: "
					+ status);
			if (status != null && !status.equals("")) {
				if (status.equals("A")) {
					statusDesc = "Active";
				} else if (status.equals("P")) {
					statusDesc = "Pending";
				} else {
					statusDesc = "Expired";
				}
				// statusDesc = status.equals("A") ? "Active":"Expired";
			}
		} catch (Exception e) {
			e.printStackTrace();
			;
		} finally {
			System.out.println("[FormatUtil][statusRecDesc][End]");
		}
		return statusDesc;
	}

	public static String sanitizeURLString(String av_val) {

		System.out.println("[FormatUtil][sanitizeURLString][Begin]");

		String[] la_symbol = { "%", "<", ">", "#", "{", "}", "|", "\\", "^",
				"~", "[", "]", "`", ";", "/", "?", ":", "@", "=", "&", "$" };
		String[] la_replace = { "%25", "%3C", "%3E", "%23", "%7B", "%7D",
				"%7C", "%5C", "%5E", "%7E", "%5B", "%5D", "%60", "%3B", "%2F",
				"%3F", "%3A", "%40", "%3D", "%26", "%24" };
		String lv_return = "";
		String lv_char = "";

		try {
			loop1: for (int i = 0; i < av_val.length(); i++) {
				lv_char = av_val.substring(i, (i + 1));
				loop2: for (int j = 0; j < la_symbol.length; j++) {
					if (lv_char.indexOf(la_symbol[j]) > -1) {
						lv_char = lv_char.replaceAll(la_symbol[j],
								la_replace[j]);
						break loop2;
					}
				}
				lv_return = lv_return + lv_char;
			}

			System.out.println("[FormatUtil][sanitizeURLString] lv_return :: "
					+ lv_return);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("[FormatUtil][sanitizeURLString][End]");
		}

		return lv_return;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
}
