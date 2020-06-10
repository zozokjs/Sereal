/**
  * File Name : CommUserFavoriteModel.java
  * 
  * Copyright(c) 2016 InnC Co.,Ltd. All rights reserved
  * 
  */
package com.innc.system.model;

import java.io.Serializable;

import com.innc.cmm.util.CommonUtil;

/**
  * @since : 2016-11-09
  * @author : KISUNG, Park
  * @version 1.0
  * @see
  * <pre>
  * 개정이력
  * -----------------------------------------------------
  * 2016-11-09   KISUNG, Park    TODO
  * </pre>
  */
public class CommUserFavoriteModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private long favSeq;
    private String userId;
    private String programName;
    private String refPath;
    private String regDt;
    private String regIp;

    private String mainRDBMS;

    private String cdNm;
    private String cdVal;
    
    /**
     * 
     * @return favSeq long 
     */
    public long getFavSeq() {
        return favSeq;
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
     * @return programName String 
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * 
     * @return refPath String 
     */
    public String getRefPath() {
        return refPath;
    }

    /**
     * 
     * @return regDt String 
     */
    public String getRegDt() {
        return regDt;
    }

    /**
     * 
     * @return regIp String 
     */
    public String getRegIp() {
        return regIp;
    }

    /**
     * 
     * @param favSeq  
     */
    public void setFavSeq(long favSeq) {
        this.favSeq = favSeq;
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
     * @param programName  
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     * 
     * @param refPath  
     */
    public void setRefPath(String refPath) {
        this.refPath = refPath;
    }

    /**
     * 
     * @param regDt  
     */
    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    /**
     * 
     * @param regIp  
     */
    public void setRegIp(String regIp) {
        this.regIp = regIp;
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
     * @return the cdNm
     */
    public String getCdNm() {
        if (CommonUtil.isNullBlank(cdNm)) {
            cdNm = this.programName;
        }
        return cdNm;
    }

    /**
     * @param cdNm the cdNm to set
     */
    public void setCdNm(String cdNm) {
        this.cdNm = cdNm;
    }

    /**
     * @return the cdVal
     */
    public String getCdVal() {
        if (CommonUtil.isNullBlank(cdVal)) {
            cdVal = this.refPath;
        }
        return cdVal;
    }

    /**
     * @param cdVal the cdVal to set
     */
    public void setCdVal(String cdVal) {
        this.cdVal = cdVal;
    }
}