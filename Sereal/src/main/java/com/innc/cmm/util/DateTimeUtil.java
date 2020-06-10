package com.innc.cmm.util;

/**
 * <pre>
 * COPYRIGHT (C) 1999-2015 PoiznKorea CO., LTD. ALL RIGHTS RESERVED
 * File Name       : DateTimeUtil.java
 * Create Date     : 2015. 3. 5.
 * Initial Creator : Administrator
 * Change History
 * -------------------------------------------------------------------------------------
 * Date    : 2015. 10. 23.
 * Author  : Administrator
 * Version : 1.0   First release.
 * -------------------------------------------------------------------------------------
 *
 * Description
 * -------------------------------------------------------------------------------------
 *
 * </pre>
 */

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * 
 * <pre>
 * 날짜 및 시간 관련 Method를 관리하는 Class<br>
 * 
 * </pre>
 * @author Park Ki-Sung [jmonster(at)cyberlogitec.com]
 * @since  JDK5.0 or Higher Version
 */
public class DateTimeUtil {
    
    /**
     * 현재 시간을 int 형 데이터로 반환
     * @return 121212 int
     * @date   2007-09-11
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static int getTime(){
        String[] ids = TimeZone.getAvailableIDs(+9 * 60 * 60 * 1000);
        SimpleTimeZone pdt = new SimpleTimeZone(+9 * 60 * 60 * 1000, ids[0]);
        Calendar date = new GregorianCalendar(pdt);
        java.util.Date trialTime = new java.util.Date();
        date.setTime(trialTime);
    
        int timex = (date.get(Calendar.HOUR_OF_DAY)*10000) + (date.get(Calendar.MINUTE)*100) + date.get(Calendar.SECOND);
        return timex;
    } // end get Time
    
    /**
     * 현재시간을 문자열(6자리)로 반환한다.
     * @return 121212 String
     */
    public static String getTimeStr()
    {
        return CommonUtil.zeroFill(getTime(), 6);
    }
    
    /**
    * 현재 시간의 밀리세컨드를 long type으로 반환
    * @return 121212 123 long (맨뒤 3자리)
    * @date   2007-09-11
    * @author KiSung, Park koreadev(at)naver.com
    */
    public static long getMilliTime(){
        String[] ids = TimeZone.getAvailableIDs(+9 * 60 * 60 * 1000);
        SimpleTimeZone pdt = new SimpleTimeZone(+9 * 60 * 60 * 1000, ids[0]);
        Calendar date = new GregorianCalendar(pdt);
        java.util.Date trialTime = new java.util.Date();
        date.setTime(trialTime);
    
        int timexm = date.get(Calendar.MILLISECOND);
        return timexm;
    } // end get Time

    /**
     * 현재 MiliSecond minus 1 / 1000 초를 줄인 값을 반환
     * @return
     */
    public static long getMilliTimeMinus(){
        return getMilliTime() - 1;
    } // end get Time
    
    /**
     * 입력받은만큼 이전 Milisecond를 구한다. 
     * @param minusTime
     * @return
     */
    public static long getMilliTimeMinus(int minusTime){
        return getMilliTime() - minusTime;
    } // end get Time
    
    /**
     * 현재 시간 밀리세컨드까지 해서 String으로 돌려줌
     * @param void
     * @return String 092706012 (오전 9시, 27분, 6초, 12 Milisec)
     * @date    2007-09-11
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static String getSeqTime(String prefix){
        return prefix + CommonUtil.zeroFill(getTime(),6) + CommonUtil.zeroFill((int) getMilliTime(),3);
    } // end get Time
    
    /**
     * 현재날짜와시간(밀리세컨드포함)을 하나의 String으로 돌려받음
     * @param  void
     * @return String 20080923134220999 (2008년09월23일,13시42분20초 999Milisec)
     * @author JM,Son (since 20080924)
     */
    public static String getDayMilliSeqTime(){
        return getDayStr() + CommonUtil.zeroFill(getTime(),6) + CommonUtil.zeroFill((int) getMilliTime(),3);
    } 
    /**
     * 오늘 날자를 가져온다
     * @param   void
     * @return  20020101 int
     * @date    2007-09-11
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static int getDay(){
        return getDay(0);
    }
    
    /**
     * 현재일자를 문자열로 반환한다.
     * @return 20020101 String
     */
    public static String getDayStr()
    {
        return String.valueOf(getDay());
    }
    /**
     * 오늘 기준으로 차이나는 일자의 년도 반환
     * @param gap
     * @return year : yyyy
     */
    public static int getYear(int gap){
        return Integer.parseInt(String.valueOf(getDay(gap)).substring(0,4));
    }
    /**
     * 오늘 기준으로 차이나는 일자의 월 반환
     * @param gap
     * @return month : mm
     */
    public static int getMonth(int gap){
        return Integer.parseInt(String.valueOf(getDay(gap)).substring(4,6));
    }
    /**
     * 오늘 기준으로 차이나는 일 반환
     * @param gap
     * @return day : dd
     */
    public static int getSingleDay(int gap){
        return Integer.parseInt(String.valueOf(getDay(gap)).substring(6));
    }

    /**
    * 오늘 날자를 기준으로 Gap만큼 차이나는 날자를 가져온다
    * @param   gapDate int
    * @return  20020101 int
    * @date    2007-09-11
    * @author KiSung, Park koreadev(at)naver.com
    */
    public static int getDay(int gap){
        int day = 0;
        String[] ids = TimeZone.getAvailableIDs(+9 * 60 * 60 * 1000);
        SimpleTimeZone pdt = new SimpleTimeZone(+9 * 60 * 60 * 1000, ids[0]);

        Calendar date = new GregorianCalendar(pdt);
        java.util.Date trialTime = new java.util.Date();
        date.setTime(trialTime);
        date.set(Calendar.DATE, date.get(Calendar.DAY_OF_MONTH) + gap);

        day = (date.get(Calendar.YEAR) * 10000) + ((date.get(Calendar.MONTH)+1) *100) + date.get(Calendar.DAY_OF_MONTH) ;
        return day;
    } // end get Day

    /**
    * 입력 받은 날자 기준으로 그달의 마지막 날을 돌려줌
    * @param   int nowDate
    * @return  int 20020131
    * @date    2007-09-11
    * @author KiSung, Park koreadev(at)naver.com
    */
    public static int lastMonthDay(int nowDate){
        int allDate = nowDate;
        int yearx   = nowDate/10000;
        int monthx  = (nowDate - (yearx*10000))/100;

        if(monthx == 12){
            yearx += 1;
            monthx = 1;
            allDate = yearx*10000 + monthx*100 + 1;
        } else {
            monthx += 1;
            allDate = yearx*10000 + monthx*100 + 1;
        }

        int day = gapDay(allDate,-1);

        return day;
    } // end lastMonthDay

    /**
    * 오늘 날자 기준으로 그달의 마지막 날을 돌려줌
    * @param   void
    * @return  int 20020131
    * @date    2007-09-11
    * @author KiSung, Park koreadev(at)naver.com
    */
    public static int lastMonthDay(){
        return lastMonthDay(getDay());
    } // end lastMonthDay

    /**
    * 입력 받은 날자 기준, 입력받은 일수만큼의 일자 이(전/후)의 날자 계산
    * @param   int nowDate, int gap
    * @return  int 20020131
    * @date    2007-09-11
    * @author KiSung, Park koreadev(at)naver.com
    */
    public static int gapDay(int nowDate, int gap){
        int yearx   = nowDate/10000;
        int monthx  = (nowDate - (yearx*10000))/100;
        int dayx    = nowDate - (yearx*10000) - (monthx*100);

        int day     = 0;
        Calendar date = Calendar.getInstance();
        date.set(yearx, (monthx-1), dayx);
        date.set(date.DATE, date.get(Calendar.DAY_OF_MONTH) + gap);

        day = (date.get(Calendar.YEAR) * 10000) + ((date.get(Calendar.MONTH)+1) *100) + date.get(Calendar.DAY_OF_MONTH) ;
        return day;
    } // end gepDay
    /**
     * 시작일부터 종료일 까지의 차이일수를 반환
     * @param fromDate
     * @param toDate
     * @return int
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static int gapFromTo(int fromDate, int toDate){
        Calendar fDate = Calendar.getInstance();
        Calendar tDate = Calendar.getInstance();

        int fYear   = fromDate/10000;
        int fMonth  = (fromDate - (fYear*10000))/100;
        int fDay    = fromDate - (fYear*10000) - (fMonth*100);

        int tYear   = toDate/10000;
        int tMonth  = (toDate - (tYear*10000))/100;
        int tDay    = toDate - (tYear*10000) - (tMonth*100);

        fDate.set(fYear, (fMonth-1), fDay);
        tDate.set(tYear, (tMonth-1), tDay);

        long cal1sec = fDate.getTime().getTime();
        long cal2sec = tDate.getTime().getTime();
        long gap = cal2sec - cal1sec;
        int gapday = (int)(gap / 86400) / 1000;
        return gapday;
    } // end gapFromTo
    
    /**
     * 두 날짜간의 시간차이를 시분초의 형태의 String으로 반환<br>
     * @see #getDateTimeGapLong(String, String)
     * @param fromDateTime yyyymmddHHmiss
     * @param toDateTime yyyymmddHHmiss
     * @return gapDayTime - String hhmiss 차이나는 만큼의 시분초로 반환
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static String getDateTimeGap( String fromDateTime, String toDateTime ) {
        long   datedif;

        try{

              SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyyMMddHHmmss");

              if( fromDateTime.equals("") && toDateTime.equals("") ) {
                return "";
              }

              if( fromDateTime.trim().equals("") || fromDateTime.trim().equals("00") ) {
                  Calendar cal = Calendar.getInstance();
                  fromDateTime = fmt.format(cal.getTime()).trim();
              }

              datedif = ( ( fmt.parse(toDateTime).getTime() - fmt.parse(fromDateTime).getTime() ) / 1000 );
              
              datedif = ( datedif > 0 ) ? datedif : Math.abs(datedif);

              double ss   = datedif;
                     ss   = ( ss > 59 ) ? ( ss % 60 ) : ss;

              double mm   = Math.floor( ( datedif - ss ) / 60 );
                     mm   = ( mm > 59 ) ? ( mm % 60 ) : mm;

              double hh   = Math.floor( ( datedif - ( ( mm * 60 ) + ss ) ) / 60 / 60 );
//              double day  = ( hh > 23 ) ? Math.floor( hh / 24 ) : 0 ;
//                     hh   = ( hh > 23 ) ? ( hh % 24 ) : hh;
//
//              String date = ( day != 0) ? ""+((int)day)+"일 " : "";
              String hour = String.valueOf((int)hh);
              String min  = String.valueOf((int)mm);
              String sec  = String.valueOf((int)ss);

              if( hh < 10 ){ 
                  hour = "0"+hour; 
              }
              if( mm < 10 ){  
                  min = "0"+min;  
              }
              if( ss < 10 ){  
                  sec = "0"+sec;  
              }

              return hour+min+sec;

        }catch(Exception e){
            e.printStackTrace();
          return "";
        }
    }
    
    /**
     * 시작일시(날짜 8자리+시간 6자리)와 종료일시() 간의 시간차이를 반환한다.<br>
     * @see #getDateTimeGap(fromDateTime, toDateTime)
     * @param fromDateTime yyyymmddhhmiss
     * @param toDateTime yyyymmddhhmiss
     * @return gapDayTime - long hhmiss 차이나는 만큼의 시분초로 반환
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static long getDateTimeGapLong( String fromDateTime, String toDateTime ) {
        if ( Double.parseDouble(fromDateTime) > Double.parseDouble(toDateTime) ) {
            return Long.parseLong(getDateTimeGap(fromDateTime, toDateTime)) * -1;
        }
        else {
            return Long.parseLong(getDateTimeGap(fromDateTime, toDateTime));
        }
    }
    /**
     * 두 날짜 간의 시차를 구분자(delim)으로 구분하여 리턴하도록 한다.
     * @param fromDateTime yyyymmddhhmiss
     * @param toDateTime yyyymmddhhmiss
     * @param delim
     * @return
     */
    public static String getDateTimeGap ( String fromDateTime, String toDateTime, String delim ) {
        StringBuffer str = new StringBuffer();
        String gap = getDateTimeGap(fromDateTime, toDateTime);
        if ( Double.parseDouble(fromDateTime) > Double.parseDouble(toDateTime) ) {
            str.append("-");
        }
        str.append( gap.substring(0, gap.length()-4) );
        str.append( delim );
        str.append( gap.substring(gap.length()-4, gap.length()-2) );
        str.append( delim );
        str.append( gap.substring(gap.length()-2) );
        return str.toString();
    }
    /**
     * 입력받은 시간차이에 대하여 차이나는 시간 반큼을 분단위로 리턴함.<br>
     * format 입력 시, 월의 경우 대문자 MM을 입력
     * @param fromDateTime
     * @param toDateTime
     * @param format 입력받는 날짜의 포맷지정 (yyyyMMddHHmmss or yyyyMMddHHmm)
     * @return 분단위값 리턴
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static double getGapTimeReturnMinute( String fromDateTime, String toDateTime, String format ) {
        double   datedif;
        
        try{

              SimpleDateFormat fmt = new java.text.SimpleDateFormat(format);

              if( fromDateTime.equals("") && toDateTime.equals("") ) {
                  return 0;
              }

              if( fromDateTime.trim().equals("") || fromDateTime.trim().equals("00") ) {
                  Calendar cal = Calendar.getInstance();
                  fromDateTime = fmt.format(cal.getTime()).trim();
              }

              datedif = ( ( fmt.parse(toDateTime).getTime() - fmt.parse(fromDateTime).getTime() ) / 1000 );
              
              datedif = ( datedif > 0 ) ? datedif : Math.abs(datedif);

              double ss   = datedif;
                     ss   = ( ss > 59 ) ? ( ss % 60 ) : ss;

              double mm   = Math.floor( ( datedif - ss ) / 60 );

              String min  = String.valueOf((int)mm);
              String sec  = String.valueOf((int)ss);

              
              if( mm < 10 ){  
                  min = "0"+min;  
              }
              if( ss < 10 ){  
                  sec = "0"+sec;  
              }

              return Double.parseDouble(min) + Double.parseDouble(sec)/60;

        }catch(Exception e){
            return 0;
        }
    }
    
    /**
     * 입력받은 일자 기준으로 시간 차이만큼의 날짜를 계산하여 반환하도록 한다.
     * @param dayTime yyyymmddhh24miss
     * @param gapTime hh24miss
     * @param pm gapTime의 + / - 여부
     * @return
     */
    public static String gapDayTime( String dayTime, String gapTime, String pm ) {
        StringBuffer gapDayTime = new StringBuffer();
        
        int fhour = Integer.parseInt(dayTime.substring(8, 10));
        int fmin = Integer.parseInt(dayTime.substring(10, 12));
        int fsec = Integer.parseInt(dayTime.substring(12));
        int ghour = Integer.parseInt(gapTime.substring(0, gapTime.length()-4));
        int gmin = Integer.parseInt(gapTime.substring(gapTime.length()-4, gapTime.length()-2));
        int gsec = Integer.parseInt(gapTime.substring(gapTime.length()-2));
        int rday=0,rhour=0,rmin=0,rsec=0;
        if("-".equals(pm)){
            ghour*=-1;  gmin*=-1;   gsec*=-1;
        }
        rsec = fsec + gsec;
        if(rsec < 0){
            rsec+=60;
            fmin-=1;
        }else if(rsec > 59){
            rsec-=60;
            fmin+=1;
        }
        rmin = fmin + gmin;
        if(rmin < 0){
            rmin+=60;
            fhour-=1;
        }else if(rmin > 59){
            rmin-=60;
            fhour+=1;
        }
        rhour = fhour + ghour;
        if(rhour < 0){
            rday = rhour/24-1;
            rhour = 24-Math.abs(rhour)%24;
            if(rhour > 23){
                rday++;
                rhour = 0;
            }
            
        }else if(rhour > 23){
            rday = rhour/24;
            rhour = rhour%24;
        }
        gapDayTime.append(DateTimeUtil.gapDay(Integer.parseInt(dayTime.substring(0, 8)), (int)rday));
        if(rhour<10){
            gapDayTime.append("0");
        }
        gapDayTime.append(String.valueOf(rhour));
        if(rmin<10){
            gapDayTime.append("0");
        }
        gapDayTime.append(String.valueOf(rmin));
        if(rsec<10){
            gapDayTime.append("0");
        }
        gapDayTime.append(String.valueOf(rsec));
        return gapDayTime.toString();
    }
    
    /**
     * 입력박은 일시에서 gapTime 만큼의 차이가 나는 일시를 반환한다.
     * @param dayTime
     * @param gapTime
     * @return
     */
    public static String gapDayTime( String dayTime, double gapTime ) {
        DecimalFormat df = new DecimalFormat("#00");
        String pm = null;
        StringBuffer gapTimeStr = new StringBuffer();
        if ( gapTime >= 0 )
        {
            pm = "+";
        }
        else
        {
            pm = "-";
        }
        
        double ss = 0.0;
        double mm = 0.0;
        double hh = 0.0;
        ss   = Math.abs(gapTime);
        ss   = ( ss > 59 ) ? ( ss % 60 ) : ss;
        
        mm   = Math.floor( ( Math.abs(gapTime) - ss ) / 60 );
        mm   = ( mm > 59 ) ? ( mm % 60 ) : mm;
        hh   = Math.floor( ( Math.abs(gapTime) - ( ( mm * 60 ) + ss ) ) / 60 / 60 );
        gapTimeStr.append(df.format(hh));
        gapTimeStr.append(df.format(mm));
        gapTimeStr.append(df.format(ss));
        return gapDayTime( dayTime, gapTimeStr.toString(), pm );
    }
    
    /**
     * 입력받은 시간(HHMM)이 정규 작업시간인지 여부를 확인하여 boolean 값을 리턴
     * @param inTime hhmm String
     * @return boolean
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static boolean isWorkTime( String inTime ) {
        int hour = Integer.parseInt(inTime.substring(0, 2));
        int min = Integer.parseInt(inTime.substring(2));
        
        if ( ( hour >=0 && hour <= 24 ) && ( min >=0 && min <= 59 )) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * 입력받은 년도가 윤년인지의 여부를 판단한다.
     * @param year YYYY
     * @return
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static boolean isLeapYear(int year) { 
        return ((year%4==0)&&(year%100!=0)||(year%400==0)); 
    } 
    
    /**
     * 입력받은 일자가 속한 달의 날수를 가져오도록 한다.
     * @see #getDaysInMonth(int, int)
     * @param day
     * @return
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static int getDaysInMonth( int day ) {
        String yyyy = String.valueOf(day).substring(0, 4);
        String mm = String.valueOf(day).substring(4, 6);
        
        return getDaysInMonth( Integer.parseInt(yyyy), Integer.parseInt(mm) );
    }
    
    /**
     * 입력받은 년 / 월의 날수를 가져오도록 한다.
     * @param yyyy
     * @param mm
     * @return
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static int getDaysInMonth( int yyyy, int mm ) {
        switch ( mm ) {
            case 1 :
                return 31;
            case 2 :
                if ( isLeapYear(yyyy) ) {
                    return 29;
                }
                else {
                    return 28;
                }
            case 3 :
                return 31;
            case 4 :
                return 30;
            case 5 :
                return 31;
            case 6 :
                return 30;
            case 7 :
                return 31;
            case 8 :
                return 31;
            case 9 :
                return 30;
            case 10 :    
                return 31;
            case 11 :
                return 30;
            case 12 :
                return 31;
            default :
                return 0;
        }
    }
    
    /**
     * 입력받은 일자의 요일 정보를 int형으로 반환한다.<br>
     * 1 : SUNDAY<br>
     * 2 : MONDAY<br>
     * 3 : TUESDAY<br>
     * 4 : WEDNESDAY<br>
     * 5 : THURSDAY<br>
     * 6 : FRIDAY<br>
     * 7 : SATURDAY<br>
     * @param day
     * @return 요일에 대한 int 값
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static int getWeekDay( int day ) {
        Calendar today = Calendar.getInstance();
        today.add(today.DATE, day);
        int dow = today.get(Calendar.DAY_OF_WEEK);

        return dow;
    }
    
    /**
     * @return 현재요일에 대한 int 값
     */
    public static int getWeekDay() {
        Calendar today = Calendar.getInstance();
        today.add(today.DATE, getDay());
        int dow = today.get(Calendar.DAY_OF_WEEK);
        return dow;
    }
    
    /**
     * 입력받은 일자가 포함된 주의 마지막 일자를 구한다.
     * @param day
     * @return lastDay - yyyymmdd
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static int getEndOfWeekDay ( int day ) {
        int lastDay = 0;
        
        if ( getWeekDay( day ) == 7 ) {
            lastDay = day;
        }
        else {
            int gap = 7 - ( getWeekDay( day ) + 1 );
            lastDay = gapDay( day, gap );
        }
        return lastDay;
    }
    
    /**
     * 입력받은 일자가 포함된 주의 첫번째 날짜를 구한다.
     * @param day
     * @return firstDay - yyyymmdd
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static int getStartOfWeekDay ( int day ) {
        int firstDay = 0;
        
        if ( getWeekDay( day ) == 1 ) {
            firstDay = day;
        }
        else {
            int gap = 1 - ( getWeekDay( day ) + 1 );
            firstDay = gapDay( day, gap );
        }
        return firstDay;
    }
    
    /**
     * 입력받은 일자의 요일을 String 으로 반환한다.
     * @param day
     * @return
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static String getWeekTitle ( int day ) {
        switch ( getWeekDay(day) ) {
            case 1 :
                return "SUNDAY";
            case 2 :
                return "MONDAY";
            case 3 :
                return "TUESDAY";
            case 4 :
                return "WEDNESDAY";
            case 5 :
                return "THURSDAY";
            case 6 :
                return "FRIDAY";
            case 7 :
                return "SATURDAY";
            default :
                return "";
        }
    }
    
    /**
     * 선택된 일자에 대하여 입력받은 구분자로 보기 좋게 나눔
     * @param day
     * @param delim
     * @return
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static String getDayType(int day, String delim) {
        String DAY = day + "";

        if( day == 0 ){
            DAY = "";
        } else if(day>10000000){
            DAY = DAY.substring(0,4) + delim +DAY.substring(4,6) + delim + DAY.substring(6,8);
        } else if(day>100000){
            DAY = DAY.substring(0,4) + delim +DAY.substring(4,6);
        }
        return DAY;
    }
    
    /**
     * 선택된 일자에 대하여 입력받은 구분자로 보기 좋게 나눔
     * @param day
     * @param delim
     * @return
     * @author KiSung, Park koreadev(at)naver.com
     */
    public static String getDayTypeStr(String day, String delim) {
        StringBuffer DAY = new StringBuffer();
        if( day.length() == 8 ) {
            DAY.append(day.substring(0, 4));
            DAY.append(delim);
            DAY.append(day.substring(4, 6));
            DAY.append(delim);
            DAY.append(day.substring(6));
        } 
        else if ( day.length() == 6 ) {
            DAY.append(day.substring(0, 2));
            DAY.append(delim);
            DAY.append(day.substring(2, 4));
            DAY.append(delim);
            DAY.append(day.substring(4));
        }
        else {
            DAY.append("");
        }
        
        return DAY.toString();
    }
    
    /**
     * 선택된 일자에 대하여 입력받은 구분자로 보기 좋게 나눔
     * @param delim
     * @return
     */
    public static String getDayTypeStr(String delim) {
        String day = getDayStr();
        StringBuffer DAY = new StringBuffer();
        if( day.length() == 8 ) {
            DAY.append(day.substring(0, 4));
            DAY.append(delim);
            DAY.append(day.substring(4, 6));
            DAY.append(delim);
            DAY.append(day.substring(6));
        } 
        else if ( day.length() == 6 ) {
            DAY.append(day.substring(0, 2));
            DAY.append(delim);
            DAY.append(day.substring(2, 4));
            DAY.append(delim);
            DAY.append(day.substring(4));
        }
        else {
            DAY.append("");
        }
        
        return DAY.toString();
    }
    /**
     * 입력받은 시간을 구분자로 자른 후 반환
     * @param time
     * @param delim
     * @return
     */
    public static String getTimeTypeStr(String time, String delim) {
        StringBuffer TIME = new StringBuffer();
        if( time.length() == 6 ) {
            TIME.append(time.substring(0, 2));
            TIME.append(delim);
            TIME.append(time.substring(2, 4));
            TIME.append(delim);
            TIME.append(time.substring(4));
        }  
        else if ( time.length() == 5 ) {
            TIME.append(time.substring(0, 1));
            TIME.append(delim);
            TIME.append(time.substring(1, 3));
            TIME.append(delim);
            TIME.append(time.substring(3));
        } 
        else if ( time.length() == 4 ) {
            TIME.append(time.substring(0, 2));
            TIME.append(delim);
            TIME.append(time.substring(2, 4));
        } 
        else if ( time.length() == 9 ) {
            TIME.append(time.substring(0, 2));
            TIME.append(delim);
            TIME.append(time.substring(2, 4));
            TIME.append(delim);
            TIME.append(time.substring(4, 6));
            TIME.append(delim);
            TIME.append(time.substring(6));
        }
        else {
            TIME.append("");
        }
        
        return TIME.toString();
    }
    
    /**
     * get a date by string pattern
     * @param date a date
     * @param pattern a string pattern, ex) "yyyy-MM-dd HH:mm:ss"
     * @return string value
     */
    public static String getDateFormat(Date date, String pattern) {
            if (date == null) return "";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
    }
    
    
    /**
     * <pre>
     * 입력 받은 Date를 long type으로 반환
     * </pre>
     * @param trialTime
     * @return
     */
    public static long getDayTime( Date trialTime )
    {
        StringBuffer daytime = new StringBuffer();
        String[] ids = TimeZone.getAvailableIDs(+9 * 60 * 60 * 1000);
        SimpleTimeZone pdt = new SimpleTimeZone(+9 * 60 * 60 * 1000, ids[0]);
        Calendar date = new GregorianCalendar(pdt);
        date.setTime(trialTime);
        int timex = (date.get(Calendar.HOUR_OF_DAY)*10000) + (date.get(Calendar.MINUTE)*100) + date.get(Calendar.SECOND);
        
        Calendar date2 = new GregorianCalendar(pdt);
        date2.setTime(trialTime);
        date2.set(Calendar.DATE, date.get(Calendar.DAY_OF_MONTH));
        daytime.append((date2.get(Calendar.YEAR) * 10000) + ((date2.get(Calendar.MONTH)+1) *100) + date2.get(Calendar.DAY_OF_MONTH)) ;
        daytime.append(CommonUtil.zeroFill(timex, 6));
        return Long.parseLong(daytime.toString());
    } // end get Time
    
    public static String getAusTypeDayAndTime(String delim) {
        StringBuilder sb = new StringBuilder();
        String day = getDayStr();
        sb.append(day.substring(6));
        sb.append(delim);
        sb.append(day.substring(4, 6));
        sb.append(delim);
        sb.append(day.substring(0, 4));
        sb.append("_");
        sb.append(getTimeStr());
        return sb.toString();
    }
}
