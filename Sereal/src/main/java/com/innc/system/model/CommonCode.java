/**
 * <pre>
 * COPYRIGHT (C) 1999-2015 PoiznKorea CO., LTD. ALL RIGHTS RESERVED
 * Project         : common
 * File Name	   : com.poizn.common.entity / CommonCode.java
 * Create Date     : 2016. 3. 30.
 * Initial Creator : PoiznKorea
 * Change History
 * -------------------------------------------------------------------------------------
 * Date    : 2016. 3. 30.
 * Author  : hojong.seo
 * Version : 1.0   First release.
 * -------------------------------------------------------------------------------------
 * Description
 *
 * -------------------------------------------------------------------------------------
 * </pre>
 */
package com.innc.system.model;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.innc.cmm.util.CommonUtil;

/**
 * <p>공통코드 처리용 Entity Class</p>
 * @author hojong.seo
 */
public class CommonCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Logger logger = LoggerFactory.getLogger(CommonCode.class);

    private long seq;
    private long grpSeq;
    private String grpCd;
    private String cdVal;
    private String cdNm;
    private String upperCd;
    private String upperNm;
    private String remark;

    private String codeGroupKey;

    private String autoCompResultCode;
    private String autoCompResultName;

    private String grpNm;
    private String userId;
    private String userIp;
    private int sortNo;
    private int useSt;
    
    private String mainRDBMS;
    
    /**
     * <p></p>
     * @return the seqno
     */
    public long getSeq() {
        return seq;
    }
    /**
     * <p></p>
     * @param seqno the seqno to set
     */
    public void setSeq(long seq) {
        this.seq = seq;
    }
    /**
     * <p></p>
     * @return the cdVal
     */
    public String getCdVal() {
        return cdVal;
    }
    /**
     * <p></p>
     * @param cdVal the cdVal to set
     */
    public void setCdVal(String cdVal) {
        this.cdVal = cdVal;
    }
    /**
     * <p></p>
     * @return the cdNm
     */
    public String getCdNm() {
        return cdNm;
    }
    /**
     * <p></p>
     * @param cdNm the cdNm to set
     */
    public void setCdNm(String cdNm) {
        this.cdNm = cdNm;
    }
    /**
     * <p></p>
     * @return the upperCd
     */
    public String getUpperCd() {
        return CommonUtil.isNullBlank(upperCd) ? null : upperCd;
    }
    /**
     * <p></p>
     * @param upperCd the upperCd to set
     */
    public void setUpperCd(String upperCd) {
        this.upperCd = upperCd;
    }
    /**
     * <p></p>
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }
    /**
     * <p></p>
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * <p></p>
     * @return the upperNm
     */
    public String getUpperNm() {
        return upperNm;
    }
    /**
     * <p></p>
     * @param upperNm the upperNm to set
     */
    public void setUpperNm(String upperNm) {
        this.upperNm = upperNm;
    }
    /**
     * <p></p>
     * @return the autoCompResultCode
     */
    public String getAutoCompResultCode() {
        return autoCompResultCode;
    }
    /**
     * <p></p>
     * @param autoCompResultCode the autoCompResultCode to set
     */
    public void setAutoCompResultCode(String autoCompResultCode) {
        this.autoCompResultCode = autoCompResultCode;
    }
    /**
     * <p></p>
     * @return the autoCompResultName
     */
    public String getAutoCompResultName() {
        return autoCompResultName;
    }
    /**
     * <p></p>
     * @param autoCompResultName the autoCompResultName to set
     */
    public void setAutoCompResultName(String autoCompResultName) {
        this.autoCompResultName = autoCompResultName;
    }
    /**
     * <p></p>
     * @return the grpSeq
     */
    public long getGrpSeq() {
        return grpSeq;
    }
    /**
     * <p></p>
     * @param grpSeq the grpSeq to set
     */
    public void setGrpSeq(long grpSeq) {
        this.grpSeq = grpSeq;
    }
    /**
     * <p></p>
     * @return the codeGroupKey
     */
    public String getCodeGroupKey() {
        //return codeGroupKey;
        return getGrpCd() + "(" + getGrpNm() + ")";
    }
    /**
     * <p></p>
     * @param codeGroupKey the codeGroupKey to set
     */
    public void setCodeGroupKey(String codeGroupKey) {
        this.codeGroupKey = codeGroupKey;
    }
    /**
     * <p></p>
     * @return the grpCd
     */
    public String getGrpCd() {
        return grpCd;
    }
    /**
     * <p></p>
     * @param grpCd the grpCd to set
     */
    public void setGrpCd(String grpCd) {
        this.grpCd = grpCd;
    }
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return the userIp
     */
    public String getUserIp() {
        return userIp;
    }
    /**
     * @param userIp the userIp to set
     */
    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }
    /**
     * @return the grpNm
     */
    public String getGrpNm() {
        return grpNm;
    }
    /**
     * @param grpNm the grpNm to set
     */
    public void setGrpNm(String grpNm) {
        this.grpNm = grpNm;
    }
    /**
     * @return the sortNo
     */
    public int getSortNo() {
        return sortNo;
    }
    /**
     * @param sortNo the sortNo to set
     */
    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }
    /**
     * @return the useSt
     */
    public int getUseSt() {
        return useSt;
    }
    /**
     * @param useSt the useSt to set
     */
    public void setUseSt(int useSt) {
        this.useSt = useSt;
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
}