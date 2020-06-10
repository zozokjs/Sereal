/**
  * File Name : TRegSiteInfoModel.java
  * 
  * Copyright(c) 2016 InnC Co.,Ltd. All rights reserved
  * 
  */
package com.innc.system.model;

import java.io.Serializable;

/**
  * @since : 2017-03-11
  * @author : KISUNG, PARK
  * @version 1.0
  * @see
  * <pre>
  * 개정이력
  * -----------------------------------------------------
  * 2017-03-11   KISUNG, PARK    TODO
  * </pre>
  */
public class TRegSiteInfoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String mainRDBMS;
    private String userStCd;

    private int regSeq;
    private String siteUrl;
    private String siteHomePath;
    private String siteNm;
    private String siteAdmId;
    private String remark;
    private String delFlag;
    private String useSt;
    private String regId;
    private String regDt;
    private String regIp;
    private String updId;
    private String updDt;
    private String updIp;


    private String siteAdmNm;
    private String regNm;
    private String updNm;
    
    /**
     * Main RDBMS  
     * @return mainRDBMS
     */
    public String getMainRDBMS() {
        return mainRDBMS;
    }

    /**
     * userStCd  
     * @return userStCd
     */
    public String getUserStCd() {
        return userStCd;
    }

    /**
     * 등록일련번호
     * @return regSeq int 
     */
    public int getRegSeq() {
        return regSeq;
    }

    /**
     * 사이트 URL
     * @return siteUrl String 
     */
    public String getSiteUrl() {
        return siteUrl;
    }

    /**
     * 사이트 최초 접속 URL(INDEX)
     * @return siteHomePath String 
     */
    public String getSiteHomePath() {
        return siteHomePath;
    }

    /**
     * 사이트명
     * @return siteNm String 
     */
    public String getSiteNm() {
        return siteNm;
    }

    /**
     * 사이트 관리자
     * @return siteAdmId String 
     */
    public String getSiteAdmId() {
        return siteAdmId;
    }

    /**
     * 설명
     * @return remark String 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 삭제여부
     * @return delFlag String 
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 사용자 사용여부
     * @return useSt String 
     */
    public String getUseSt() {
        return useSt;
    }

    /**
     * 등록자 ID
     * @return regId String 
     */
    public String getRegId() {
        return regId;
    }

    /**
     * 등록일자
     * @return regDt String 
     */
    public String getRegDt() {
        return regDt;
    }

    /**
     * 등록자 접속 IP
     * @return regIp String 
     */
    public String getRegIp() {
        return regIp;
    }

    /**
     * 수정자 ID
     * @return updId String 
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * 수정일자
     * @return updDt String 
     */
    public String getUpdDt() {
        return updDt;
    }

    /**
     * 수정자 접속 IP
     * @return updIp String 
     */
    public String getUpdIp() {
        return updIp;
    }

    /**
     * mainRDBMS the mainRDBMS to set
     * @param mainRDBMS 
     */
    public void setMainRDBMS(String mainRDBMS) {
        this.mainRDBMS = mainRDBMS;
    }

    /**
     * userStCd the userStCd to set
     * @param userStCd 
     */
    public void setUserStCd(String userStCd) {
        this.userStCd = userStCd;
    }

    /**
     * 등록일련번호
     * @param regSeq  
     */
    public void setRegSeq(int regSeq) {
        this.regSeq = regSeq;
    }

    /**
     * 사이트 URL
     * @param siteUrl  
     */
    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    /**
     * 사이트 최초 접속 URL(INDEX)
     * @param siteHomePath  
     */
    public void setSiteHomePath(String siteHomePath) {
        this.siteHomePath = siteHomePath;
    }

    /**
     * 사이트명
     * @param siteNm  
     */
    public void setSiteNm(String siteNm) {
        this.siteNm = siteNm;
    }

    /**
     * 사이트 관리자
     * @param siteAdmId  
     */
    public void setSiteAdmId(String siteAdmId) {
        this.siteAdmId = siteAdmId;
    }

    /**
     * 설명
     * @param remark  
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 삭제여부
     * @param delFlag  
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 사용자 사용여부
     * @param useSt  
     */
    public void setUseSt(String useSt) {
        this.useSt = useSt;
    }

    /**
     * 등록자 ID
     * @param regId  
     */
    public void setRegId(String regId) {
        this.regId = regId;
    }

    /**
     * 등록일자
     * @param regDt  
     */
    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    /**
     * 등록자 접속 IP
     * @param regIp  
     */
    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    /**
     * 수정자 ID
     * @param updId  
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    /**
     * 수정일자
     * @param updDt  
     */
    public void setUpdDt(String updDt) {
        this.updDt = updDt;
    }

    /**
     * 수정자 접속 IP
     * @param updIp  
     */
    public void setUpdIp(String updIp) {
        this.updIp = updIp;
    }

    /**
     * @return the siteAdmNm
     */
    public String getSiteAdmNm() {
        return siteAdmNm;
    }

    /**
     * @param siteAdmNm the siteAdmNm to set
     */
    public void setSiteAdmNm(String siteAdmNm) {
        this.siteAdmNm = siteAdmNm;
    }

    /**
     * @return the regNm
     */
    public String getRegNm() {
        return regNm;
    }

    /**
     * @param regNm the regNm to set
     */
    public void setRegNm(String regNm) {
        this.regNm = regNm;
    }

    /**
     * @return the updNm
     */
    public String getUpdNm() {
        return updNm;
    }

    /**
     * @param updNm the updNm to set
     */
    public void setUpdNm(String updNm) {
        this.updNm = updNm;
    }
}