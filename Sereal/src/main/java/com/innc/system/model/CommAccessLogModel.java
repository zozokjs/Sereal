/**
  * File Name : CommAccessLogModel.java
  * 
  * Copyright(c) 2016 InnC Co.,Ltd. All rights reserved
  * 
  */
package com.innc.system.model;

import java.io.Serializable;

/**
  * @since : 2016-10-27
  * @author : KISUNG, PARK
  * @version 1.0
  * @see
  * <pre>
  * 개정이력
  * -----------------------------------------------------
  * 2016-10-27   KISUNG, PARK    TODO
  * </pre>
  */
public class CommAccessLogModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String logId;
    private String accessDate;
    private String userId;
    private String userNm;
    private String accessPath;
    private String paramValue;
    private String userAgent;
    private String accessIp;

    private String mainRDBMS;
    private String programName;
    
    /**
     * 
     * @return logId String 
     */
    public String getLogId() {
        return logId;
    }

    /**
     * 
     * @return accessDate String 
     */
    public String getAccessDate() {
        return accessDate;
    }

    /**
     * 
     * @return userId String 
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 
     * @return userNm String 
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * 
     * @return accessPath String 
     */
    public String getAccessPath() {
        return accessPath;
    }

    /**
     * 
     * @return userAgent String 
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * 
     * @return accessIp String 
     */
    public String getAccessIp() {
        return accessIp;
    }

    /**
     * 
     * @param logId  
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }

    /**
     * 
     * @param accessDate  
     */
    public void setAccessDate(String accessDate) {
        this.accessDate = accessDate;
    }

    /**
     * 
     * @param userId  
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 
     * @param userNm  
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * 
     * @param accessPath  
     */
    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    /**
     * 
     * @param userAgent  
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * 
     * @param accessIp  
     */
    public void setAccessIp(String accessIp) {
        this.accessIp = accessIp;
    }

    /**
     * @return the mainRDBMS
     */
    public String getMainRDBMS() {
        return mainRDBMS;
    }

    /**
     * @param mainRDBMS the mainRDBMS to set
     */
    public void setMainRDBMS(String mainRDBMS) {
        this.mainRDBMS = mainRDBMS;
    }

    /**
     * @return the programName
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * @param programName the programName to set
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     * @return the paramValue
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * @param paramValue the paramValue to set
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}