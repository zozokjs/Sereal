/**
 * <pre>
 * COPYRIGHT (C) 1999-2015 PoiznKorea CO., LTD. ALL RIGHTS RESERVED
 * File Name	   : CommonUtil.java
 * Create Date     : 2015. 3. 5.
 * Initial Creator : Administrator
 * Change History
 * -------------------------------------------------------------------------------------
 * Date    : 2015. 3. 5.
 * Author  : Administrator
 * Version : 1.0   First release.
 * -------------------------------------------------------------------------------------
 *
 * Description
 * -------------------------------------------------------------------------------------
 * </pre>
 */

package com.innc.cmm.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;

import com.innc.user.model.UserSessionModel;


public class CommonUtil {

    /**
     * <pre>
     * 문자열 Null 처리
     * </pre>
     *
     * @param arg
     * @return
     */
    public static String fixNull(String arg) {
        if (arg == null) {
            return "";
        } else {
            return arg;
        }
    }

    /**
     * <pre>
     * Null or Blank 인 경우  true
     * </pre>
     *
     * @param arg
     * @return
     */
    public static boolean isNullBlank(String arg) {
        if ("".equals(fixNull(arg))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <pre>
     * 문자열을 Array 로 변환
     * </pre>
     *
     * @param arg
     * @param regex
     * @return
     */
    public static String[] getStringSplit(String arg, String regex) {
        if (isNullBlank(arg)) {
            return null;
        } else {
            return arg.split(regex);
        }
    }

    /**
     * <pre>
     * 문자열을 Int로 변환
     * </pre>
     *
     * @param arg
     * @return
     */
    public static int parseInt(String arg) {
        if (isNullBlank(arg) || !isNumeric(arg)) {
            return 0;
        } else {
            return Integer.parseInt(arg);
        }
    }

    /**
     * <pre>
     * 문자열을 double 로 변환
     * </pre>
     *
     * @param arg
     * @return
     */
    public static double parseDouble(String arg) {
        if (isNullBlank(arg) || !isNumeric(arg)) {
            return 0;
        } else {
            return Double.parseDouble(arg);
        }
    }

    /**
     * <pre>
     * 문자열을 long 로 변환
     * </pre>
     *
     * @param arg
     * @return
     */
    public static long parseLong(String arg) {
        if (isNullBlank(arg) || !isNumeric(arg)) {
            return 0;
        } else {
            return Long.parseLong(arg);
        }
    }

    /**
     * <pre>
     * 실수형 반환
     * </pre>
     *
     * @param arg
     * @return
     */
    public static float parseFloat(String arg) {
        if (isNullBlank(arg) || !isNumeric(arg)) {
            return 0;
        } else {
            return Float.parseFloat(arg);
        }
    }

    /**
     * <pre>
     * Numeric Check
     * </pre>
     *
     * @param arg
     * @return
     */
    public static boolean isNumeric(String arg) {
        return arg.matches("^[-+]?\\d+(\\.\\d+)?$");
    }

    /**
     * <pre>
     * 입력받은 문자 밑 정규 표현식으로 구분자 분리 후 Map으로 변환
     * </pre>
     *
     * @param str
     * @param regex
     * @return
     */
    public static Map<String, Object> strCollection(String str, String regex) {
        Map<String, Object> maps = new HashMap<String, Object>();

        String[] strs = str.split(regex);
        int keyIdx = 0;
        for (String s : strs) {
            maps.put(String.valueOf(keyIdx++), s);
        }
        return maps;
    }

    /**
     * <pre>
     * Message Property에 등록된 Grid Title 정보 처리
     * </pre>
     *
     * @param messageSource
     * @param locale
     * @param code
     * @param regex
     * @return
     */
    public static Map<String, Object> strCollection(MessageSource messageSource, Locale locale, String code, String regex) {
        Map<String, Object> maps = new HashMap<String, Object>();
        String str;
        try {
            str = messageSource.getMessage(code, null, locale);
        } catch (Exception e) {
            str = "";
        }

        if (!CommonUtil.isNullBlank(str)) {
            String[] strs = str.split(regex);
            int keyIdx = 0;
            for (String s : strs) {
                maps.put(String.valueOf(keyIdx++), s);
            }
        }

        return maps;
    }

    /**
     * <pre>
     * UUID Generating
     * </pre>
     *
     * @return
     */
    public static String generateKey() {
        return UUID.randomUUID().toString();
    }

    /**
     * 시간 키 조회
     * @return
     */
    public static String generateTimeKey() {
        return DateTimeUtil.getDayMilliSeqTime();
    }

    /**
     * <pre>
     * Map to Object convert
     * </pre>
     *
     * @param map
     * @param objClass
     * @return
     */
    public static Object convertMapToObject(Map<String, Object> map, Object objClass) {
        String keyAttribute = null;
        String setMethodString = "set";
        String methodString = null;

        Iterator itr = map.keySet().iterator();

        while (itr.hasNext()) {
            keyAttribute = (String) itr.next();
            methodString = setMethodString + keyAttribute.substring(0, 1).toUpperCase() + keyAttribute.substring(1);

            try {
                Method[] methods = objClass.getClass().getDeclaredMethods();
                String parameterType = "";
                String values = "";
                for (int i = 0; i <= methods.length - 1; i++) {
                    if (methodString.equals(methods[i].getName())) {
                        parameterType = methods[i].getParameterTypes()[0].getName();
                        values = (String) map.get(keyAttribute);
                        if ("String".equals(parameterType)) {
                            methods[i].invoke(objClass, values);
                        } else if ("int".equals(parameterType)) {
                            methods[i].invoke(objClass, parseInt(values));
                        } else if ("long".equals(parameterType)) {
                            methods[i].invoke(objClass, parseLong(values));
                        } else if ("double".equals(parameterType)) {
                            methods[i].invoke(objClass, parseDouble(values));
                        } else if ("float".equals(parameterType)) {
                            methods[i].invoke(objClass, parseFloat(values));
                        } else {
                            methods[i].invoke(objClass, map.get(keyAttribute));
                        }
                    }
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return objClass;
    }

    /**
     * Object 정보를 Map으로 변경한다.
     * 
     * @param map
     * @param objClass
     * @return
     */
    public static Map<String, Object> convertObjectToMap(Map<String, Object> map, Object objClass) {
        Field[] fields = objClass.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(objClass));
                //System.out.println("--------------convertMap-------------------");
                //System.out.println(field.getName());
                //System.out.println(field.get(objClass));                
                //System.out.println("--------------convertMap-------------------");

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
    
    /**
     * <pre>
     * Sketch.js 로 생성 된 서명 파일 생성
     * </pre>
     *
     * @param path
     * @param fileName
     * @param fileStr
     */
    public static boolean signFileCreate(String path, String fileName, String fileStr) {
        boolean isCreated = true;
        Path dir = new File(path).toPath();
        try {
            // File Directory가 존재하지 않는 경우 하위 디렉토리까지 생성
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            signFileDelete(path, fileName);

            try (BufferedWriter out = new BufferedWriter(new FileWriter(path + File.separator + fileName + ".txt"))) {
                if (!isNullBlank(fileStr)) {
                    out.write(fileStr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            isCreated = false;
        }
        return isCreated;
    }

    /**
     * <pre>
     * Sign file 삭제
     * </pre>
     *
     * @param path
     * @param fileName
     */
    public static void signFileDelete(String path, String fileName) {
        try {

            fileName += ".txt";
            File signFile = new File(path + File.separator + fileName);

            if (signFile.exists()) {
                signFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <pre>
     * Fill Zero value
     * 자리수 만큼 0으로 채운다
     * zeroFill(1380,7) = 0001380
     * </pre>
     *
     * @param seqno
     * @param size
     * @return
     */
    public static String zeroFill(int seqno, int size) {
        StringBuffer convert = new StringBuffer();
        int maxSize = (int) Math.pow(10, size); // size=5 이면maxSize=100,000

        if (seqno >= maxSize) {
            convert.append(Integer.toString(maxSize - 1));
        } else {
            int seqnoSize = (Integer.toString(seqno)).length();
            for (int i = 0; i < (size - seqnoSize); i++) {
                convert.append("0");
            }
            convert.append(seqno);
        } // end if
        return convert.toString();
    } // end zeroFill

    /**
     * <pre>
     * 비율정보를 수치값을 문자로 반환
     * </pre>
     *
     * @param cash
     * @return
     */
    public static String convertRate(double rate) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(rate) + "%";
    }

    /**
     * @param s
     * @return
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static String toSeperate(String s) {
        if (s == null)
            return null;

        StringBuffer buf = new StringBuffer();
        char[] c = s.toCharArray();
        int len = c.length;

        for (int i = 0; i < len; i++) {
            switch (c[i]) {
                case '\\' :
                    buf.append("/");
                    break;
                case '\'' :
                    buf.append("&#039;");
                    break;
                case '\n' :
                    buf.append("<BR>");
                    break;
                default :
                    buf.append(c[i]);
                    break;
            }
        }

        String resultStr = buf.toString();
        return resultStr;
    }

    /**
     * 입력 문자열을 변경하도록 한다.
     * 
     * @param fromStr
     * @param regex
     * @param replacement
     * @return
     */
    public static String replaceString(String fromStr, String regex, String replacement) {
        return fixNull(fromStr).replaceAll(regex, replacement);
    }

    /**
     * 단어의 첫글자는 대문자, 나머지는 소문자로 변환
     * 
     * @param _str
     * @return
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static String getCharConvert(String _str) {
        if (_str.length() > 0) {
            _str = _str.substring(0, 1).toUpperCase() + _str.substring(1).toLowerCase();
        }
        return _str;
    }

    /**
     * <pre>
     * 구분자로 연결된 부분을 제외한 후, 각 Block 별 첫 글자는 대문자, 나머지는 소문자로 변경
     * </pre>
     * 
     * @param _str
     * @param regex
     * @return
     */
    public static String getCharConvert(String _str, String regex) {
        StringBuffer sb = new StringBuffer();
        String[] ary = _str.split(regex);
        if (ary != null && ary.length > 0) {
            for (String s : ary) {
                sb.append(getCharConvert(s));
            }
        } else {
            sb.append(getCharConvert(_str));
        }
        return sb.toString();
    }

    /**
     * <pre>
     * 정규식이 없는 경우 처리
     * </pre>
     * 
     * @param _str
     * @return
     * @see #getCharConvert(String)
     * @see #getCharConvert(String, String)
     */
    public static String getCharConvertNoRegex(String _str) {
        if (_str.indexOf("_") >= 0) {
            return getCharConvert(_str, "_");
        } else {
            if (_str.indexOf(" ") >= 0) {
                return getCharConvert(_str, " ");
            } else {
                if (_str.indexOf("-") >= 0) {
                    return getCharConvert(_str, "-");
                } else {
                    return getCharConvert(_str, ".");
                }
            }
        }
    }

    /**
     * <pre>
     * 구문의 첫글자는 소문자. 다음 블럭부터는 첫글자 대문자로
     * </pre>
     * 
     * @param _str
     * @param regex
     * @return
     */
    public static String getFirstLowerCharConvert(String _str, String regex) {
        StringBuffer sb = new StringBuffer();
        String[] ary = _str.split(regex);
        int i = 0;
        if (ary != null && ary.length > 0) {
            for (String s : ary) {
                if (i == 0) {
                    sb.append(s.toLowerCase());
                } else {
                    sb.append(getCharConvert(s));
                }
                i++;
            }
        } else {
            sb.append(_str.toLowerCase());
        }
        return sb.toString();
    }

    /**
     * <pre>
     * 정규식이 없는 경우 처리
     * </pre>
     * 
     * @param _str
     * @return
     * @see #getFirstCharConvert(String)
     * @see #getFirstLowerCharConvert(String, String)
     */
    public static String getFirstLowerCharConvertNoRegex(String _str) {
        if (_str.indexOf("_") >= 0) {
            return getFirstLowerCharConvert(_str, "_");
        } else {
            if (_str.indexOf(" ") >= 0) {
                return getFirstLowerCharConvert(_str, " ");
            } else {
                if (_str.indexOf("-") >= 0) {
                    return getFirstLowerCharConvert(_str, "-");
                } else {
                    return getFirstLowerCharConvert(_str, ".");
                }
            }
        }
    }

    /**
     * 단어의 첫글자를 대문자로 변환
     * 
     * @param _str
     * @return
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static String getFirstCharConvert(String _str) {
        if (_str.length() > 0) {
            _str = _str.substring(0, 1).toUpperCase() + _str.substring(1);
        }
        return _str;
    }

    /**
     * 단어의 첫글자를 소문자로 변환
     * 
     * @param _str
     * @return
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static String getFirstCharLower(String _str) {
        if (_str.length() > 0) {
            _str = _str.substring(0, 1).toLowerCase() + _str.substring(1);
        }
        return _str;
    }
    
    /**
     * <pre>
     * client IP 정보를 조회
     * </pre>
     * 
     * @param request
     * @return
     */
    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * 사용자 접속 브라우저 정보 조회
     * @param request
     * @return
     */
    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }
    
    /**
     * Page File 명이 폴더를 포함하여 구성되며, 각 폴더 기준이 delim에 따라 변경된 page path 반환
     * @param viewpage
     * @param delim
     * @return
     */
    public static String getPagePath(String viewpage, String delim) {
        StringBuilder sb = new StringBuilder();
        String[] str = viewpage.split(delim);
        int aryLength = str.length;
        for(int i = 0; i < (aryLength - 1); i++) {
            sb.append(str[i]);
            sb.append("/");
        }
        sb.append(viewpage);
        return sb.toString();
    }
    
    /**
     * Path Folder 포함된 경로 반환
     * @param pathFolder
     * @param viewpage
     * @param delim
     * @return
     */
    public static String getPagePath(String pathFolder, String viewpage, String delim) {
        StringBuilder sb = new StringBuilder();
        if (!CommonUtil.isNullBlank(pathFolder)) {
            sb.append(pathFolder);
            sb.append("/");
        }
        String[] str = viewpage.split(delim);
        int aryLength = str.length;
        for(int i = 0; i < (aryLength - 1); i++) {
            sb.append(str[i]);
            sb.append("/");
        }
        sb.append(viewpage);
        return sb.toString();
    }    
    
    /**
     * Path Folder 포함된 경로 반환
     * 
     * @param rootDir
     * @param pathFolder
     * @param viewpage
     * @param delim
     * @return
     */
    public static String getPagePath(String rootDir, String folder, String viewpage, String delim) {
        StringBuilder sb = new StringBuilder();
        if (!CommonUtil.isNullBlank(rootDir)) {
            sb.append(rootDir);
            sb.append("/");
        }
        if (!CommonUtil.isNullBlank(folder)) {
            sb.append(folder);
            sb.append("/");
        }
        String[] str = viewpage.split(delim);
        int aryLength = str.length;
        for(int i = 0; i < (aryLength - 1); i++) {
            sb.append(str[i]);
            sb.append("/");
        }
        sb.append(viewpage);
        return sb.toString();
    } 
    /**
     * Path Folder 포함된 경로 반환
     * 
     * @param rootDir
     * @param pathFolder
     * @param viewpage
     * @param delim
     * @return
     */
    public static String getPagePathNoDelim(String rootDir, String folder, String viewpage) {
        StringBuilder sb = new StringBuilder();
        if (!CommonUtil.isNullBlank(rootDir)) {
            sb.append(rootDir);
            sb.append("/");
        }
        if (!CommonUtil.isNullBlank(folder)) {
            sb.append(folder);
            sb.append("/");
        }
        if (!CommonUtil.isNullBlank(viewpage)) {
            sb.append(viewpage);
        }
        return sb.toString();
    }     
    /**
     * <pre>
     * 사용자 세션정보를 parameter에 설정한다.
     * </pre>
     * 
     * @param paramMap
     * @param request
     */
    public static void setUserInfoMap(Map<String, Object> params, HttpServletRequest request) {
        UserSessionModel checkSession = (UserSessionModel) request.getSession().getAttribute("userSession");
        if (checkSession != null) {
            params.put("userId", checkSession.getUserInfo().getUserId());
            params.put("userIp", checkSession.getUserInfo().getUserIp());
            params.put("userStCd", checkSession.getUserInfo().getUserStCd());
            params.put("regId", checkSession.getUserInfo().getUserId());
            params.put("regIp", checkSession.getUserInfo().getUserIp());
            params.put("regiId", checkSession.getUserInfo().getUserId());
            params.put("regiIpAddr", checkSession.getUserInfo().getUserIp());
            params.put("updIp", checkSession.getUserInfo().getUserIp());
            params.put("updId", checkSession.getUserInfo().getUserId());
            params.put("RG_ID", checkSession.getUserInfo().getUserId());
            params.put("RG_IP_ADR", checkSession.getUserInfo().getUserIp());
        }
    }
    
    /**
     * <pre>
     * 문자열로 받은 file size를 long type으로 변환
     * </pre>
     *
     * @param arg
     * @param type
     * @return
     */
    /*
    public static long castFileSize(String arg, String type) {
        if (isNullBlank(arg) && !isNumeric(arg)) {
            return 0;
        } else {
            if (CommonConstants.FILE_MBYTE.equals(type)) {
                return Long.parseLong(arg) * 1024 * 1024;
            } else if (CommonConstants.FILE_BYTE.equals(type)) {
                return Long.parseLong(arg);
            } else if (CommonConstants.FILE_KBYTE.equals(type)) {
                return Long.parseLong(arg) * 1024;
            } else if (CommonConstants.FILE_GBYTE.equals(type)) {
                return Long.parseLong(arg) * 1024 * 1024 * 1024;
            } else {
                return Long.parseLong(arg);
            }
        }
    }*/
    
    /**
     * 파일 확장자 구함
     * @param fileName
     * @return
     */
    public static String getFileExtension(String fileName) {
        int idx = fileName.lastIndexOf(".");
        return fileName.substring(idx + 1);
    }
    
    /**
     * 문자열을 byte 단위로 잘라서 반환한다
     * @param s
     * @param startIdx
     * @param bytes
     * @return
     */
    public static String getByteString(String s, int startIdx, int bytes) {
        String returnStr = new String(s.getBytes(), startIdx, bytes);
        return returnStr.trim();
    }
    
    /**
     * <pre>
     * 사용자 세션정보를 parameter에 설정한다.
     * </pre>
     * 
     * @param paramMap
     * @param request
     */
    public static void setUserInfoMapID(Map<String, Object> params, HttpServletRequest request) {
        UserSessionModel checkSession = (UserSessionModel) request.getSession().getAttribute("userSession");
        if (checkSession != null) {
            params.put("USER_ID", checkSession.getUserInfo().getUserId());
            params.put("USER_IP", checkSession.getUserInfo().getUserIp());
            params.put("USER_ST_CD", checkSession.getUserInfo().getUserStCd());
            params.put("REG_ID", checkSession.getUserInfo().getUserId());
            params.put("REG_IP", checkSession.getUserInfo().getUserIp());
            params.put("REGI_ID", checkSession.getUserInfo().getUserId());
            params.put("REGI_IP_ADDR", checkSession.getUserInfo().getUserIp());
            params.put("UPD_IP", checkSession.getUserInfo().getUserIp());
            params.put("UPD_ID", checkSession.getUserInfo().getUserId());
            params.put("RG_ID", checkSession.getUserInfo().getUserId());
            params.put("RG_IP", checkSession.getUserInfo().getUserIp());
        }
    }
    
    /**
     * 다중인서트를 위해 데이터가 넘어오는 경우의 처리를 위하여 params에서 대상을 뽑아 List로 변경한다.
     * @param params
     * @return
     */
    public static List<Map<String, Object>> convertParamMapList(Map<String, Object> params) {
        List<Map<String, Object>> paramMapList = new ArrayList<Map<String, Object>>();
        int paramMapListSize = parseInt((String)params.get("paramMapListSize"));
        String column = (String)params.get("paramMapListCols");
        String[] cols = column.split(",");
        int colsSize = cols.length;
        Map<String, Object> map = null;
        String keys = null;
        for (int i = 0 ; i < paramMapListSize ; i++) {
            map = new HashMap<String, Object>();
            for (int j = 0 ; j < colsSize ; j++) {
                keys = "paramMapList[" + i + "][" + cols[j] + "]";
                map.put(cols[j], (String)params.get(keys));
            }
            map.put("RG_ID", params.get("RG_ID"));
            map.put("RG_IP_ADR", params.get("RG_IP_ADR"));
            
            paramMapList.add(map);
        }
        return paramMapList;
    }
}
