/**
 * <pre>
 * COPYRIGHT (C) 1999-2015 PoiznKorea CO., LTD. ALL RIGHTS RESERVED
 * Project         : common
 * File Name	   : com.poizn.common.entity / CommonCodeGroup.java
 * Create Date     : 2016. 4. 1.
 * Initial Creator : PoiznKorea
 * Change History
 * -------------------------------------------------------------------------------------
 * Date    : 2016. 4. 1.
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

/**
 * <p></p>
 * @author hojong.seo
 */
public class CommonCodeGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private long seq;
    private String grpCd;
    private String grpNm;
    private int sortNo;
    private int useSt;
    private String userId;
    private String userIp;

    private String mainRDBMS;
    
    /**
     * <p></p>
     * @return the seq
     */
    public long getSeq() {
        return seq;
    }
    /**
     * <p></p>
     * @param seq the seq to set
     */
    public void setSeq(long seq) {
        this.seq = seq;
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
     * <p></p>
     * @return the grpNm
     */
    public String getGrpNm() {
        return grpNm;
    }
    /**
     * <p></p>
     * @param grpNm the grpNm to set
     */
    public void setGrpNm(String grpNm) {
        this.grpNm = grpNm;
    }
    /**
     * <p></p>
     * @return the sortNo
     */
    public int getSortNo() {
        return sortNo;
    }
    /**
     * <p></p>
     * @param sortNo the sortNo to set
     */
    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }
    /**
     * <p></p>
     * @return the useSt
     */
    public int getUseSt() {
        return useSt;
    }
    /**
     * <p></p>
     * @param useSt the useSt to set
     */
    public void setUseSt(int useSt) {
        this.useSt = useSt;
    }
    /**
     * <p></p>
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * <p></p>
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * <p></p>
     * @return the userIp
     */
    public String getUserIp() {
        return userIp;
    }
    /**
     * <p></p>
     * @param userIp the userIp to set
     */
    public void setUserIp(String userIp) {
        this.userIp = userIp;
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