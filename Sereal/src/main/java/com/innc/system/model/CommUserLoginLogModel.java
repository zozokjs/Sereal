/**
  * File Name : CommUserLoginLogModel.java
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
public class CommUserLoginLogModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String logId;
    private String accessDate;
    private String userId;
    private String userNm;
    private String userAgent;
    private String accessIp;
    private String authFlag;
    private String userSessionId;
    private String logOutFlag;
    private String hostInfo;
    
    private String mainRDBMS;

    /**
     * 사용자 MAC ADDRESS 정보
     */
    private String userMacAddress;
    
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
     * @return authFlag String 
     */
    public String getAuthFlag() {
        return authFlag;
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
     * 
     * @param authFlag  
     */
    public void setAuthFlag(String authFlag) {
        this.authFlag = authFlag;
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
     * @return the userSessionId
     */
    public String getUserSessionId() {
        return userSessionId;
    }

    /**
     * @param userSessionId the userSessionId to set
     */
    public void setUserSessionId(String userSessionId) {
        this.userSessionId = userSessionId;
    }

    /**
     * @return the logOutFlag
     */
    public String getLogOutFlag() {
        return logOutFlag;
    }

    /**
     * @param logOutFlag the logOutFlag to set
     */
    public void setLogOutFlag(String logOutFlag) {
        this.logOutFlag = logOutFlag;
    }

    /**
     * @return the userMacAddress
     */
    public String getUserMacAddress() {
        return userMacAddress;
    }

    /**
     * @param userMacAddress the userMacAddress to set
     */
    public void setUserMacAddress(String userMacAddress) {
        this.userMacAddress = userMacAddress;
    }

    /**
     * @return the hostInfo
     */
    public String getHostInfo() {
        return hostInfo;
    }

    /**
     * @param hostInfo the hostInfo to set
     */
    public void setHostInfo(String hostInfo) {
        this.hostInfo = hostInfo;
    }
}