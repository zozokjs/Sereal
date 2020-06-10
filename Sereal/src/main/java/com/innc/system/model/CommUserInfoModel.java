/**
 * File Name : CommUserInfoModel.java
 * Copyright(c) 2016 InnC Co.,Ltd. All rights reserved
 */
package com.innc.system.model;

import java.io.Serializable;

import com.innc.cmm.util.CommonUtil;

/**
 * @since : 2016-10-19
 * @author : Kisung, Park
 * @version 1.0
 * @see
 * 
 *      <pre>
 * 개정이력
 * -----------------------------------------------------
 * 2016-10-19   Kisung, Park    TODO
 *      </pre>
 */
public class CommUserInfoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private long seqno;
    private String userId;
    private String userPw;
    private String userNm;
    private String userStCd;
    private String useSt;
    private String regId;
    private String regDt;
    private String regIp;
    private String updId;
    private String updDt;
    private String updIp;
    private String juminNo;
    
    private String mainRDBMS;
    
    private String publicKeyModulus;
    private String publicKeyExponent;
    
    private String currentMenu;
    
    /**
     * <pre>
     * 사용자 구분명
     * select cd_nm from comm_cd_info where cd_val = #{userStCd} and grp_seq = (select seq from comm_grp_cd_info.grp_cd = '000001')
     * </pre>
     */
    private String userStNm;

    /**
     * <pre>
     * 사용구분
     * select cd_nm from comm_cd_info where cd_val = #{userStCd} and grp_seq = (select seq from comm_grp_cd_info.grp_cd = '000002')
     * </pre>
     */
    private String useStNm;

    // 검색조건 parameter 변수
    private String searchUserStCd;
    private String searchUseSt;
    private String searchUserId;
    private String searchUserNm;
    
    private String prevpasswd;
    private String passreset;

    /**
     * 최종수정자
     */
    private String updNm;
    
    /**
     * 이전 사용자 id
     */
    private String prevuserid;
    
    /**
     * @return seqno double
     */
    public long getSeqno() {
        return seqno;
    }

    /**
     * @return userId String
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @return userPw String
     */
    public String getUserPw() {
        return userPw;
    }

    /**
     * @return userNm String
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * @return userStCd String
     */
    public String getUserStCd() {
        return userStCd;
    }

    /**
     * @return useSt double
     */
    public String getUseSt() {
        return useSt;
    }

    /**
     * @return regId String
     */
    public String getRegId() {
        return regId;
    }

    /**
     * @return regDt String
     */
    public String getRegDt() {
        return regDt;
    }

    /**
     * @return regIp String
     */
    public String getRegIp() {
        return regIp;
    }

    /**
     * @return updId String
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * @return updDt String
     */
    public String getUpdDt() {
        return updDt;
    }

    /**
     * @return updIp String
     */
    public String getUpdIp() {
        return updIp;
    }

    /**
     * @param seqno
     */
    public void setSeqno(long seqno) {
        this.seqno = seqno;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @param userPw
     */
    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    /**
     * @param userNm
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * @param userStCd
     */
    public void setUserStCd(String userStCd) {
        this.userStCd = userStCd;
    }

    /**
     * @param useSt
     */
    public void setUseSt(String useSt) {
        this.useSt = useSt;
    }

    /**
     * @param regId
     */
    public void setRegId(String regId) {
        this.regId = regId;
    }

    /**
     * @param regDt
     */
    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    /**
     * @param regIp
     */
    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    /**
     * @param updId
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    /**
     * @param updDt
     */
    public void setUpdDt(String updDt) {
        this.updDt = updDt;
    }

    /**
     * @param updIp
     */
    public void setUpdIp(String updIp) {
        this.updIp = updIp;
    }

    /**
     * @return the userStNm
     */
    public String getUserStNm() {
        return userStNm;
    }

    /**
     * @param userStNm
     *            the userStNm to set
     */
    public void setUserStNm(String userStNm) {
        this.userStNm = userStNm;
    }

    /**
     * @return the useStNm
     */
    public String getUseStNm() {
        return useStNm;
    }

    /**
     * @param useStNm
     *            the useStNm to set
     */
    public void setUseStNm(String useStNm) {
        this.useStNm = useStNm;
    }

    /**
     * @return the searchUserStCd
     */
    public String getSearchUserStCd() {
        return searchUserStCd;
    }

    /**
     * @param searchUserStCd the searchUserStCd to set
     */
    public void setSearchUserStCd(String searchUserStCd) {
        this.searchUserStCd = searchUserStCd;
    }

    /**
     * @return the searchUseSt
     */
    public String getSearchUseSt() {
        return searchUseSt;
    }

    /**
     * @param searchUseSt the searchUseSt to set
     */
    public void setSearchUseSt(String searchUseSt) {
        this.searchUseSt = searchUseSt;
    }

    /**
     * @return the searchUserId
     */
    public String getSearchUserId() {
        return searchUserId;
    }

    /**
     * @param searchUserId the searchUserId to set
     */
    public void setSearchUserId(String searchUserId) {
        this.searchUserId = searchUserId;
    }

    /**
     * @return the searchUserNm
     */
    public String getSearchUserNm() {
        return searchUserNm;
    }

    /**
     * @param searchUserNm the searchUserNm to set
     */
    public void setSearchUserNm(String searchUserNm) {
        this.searchUserNm = searchUserNm;
    }

    /**
     * @return the prevpasswd
     */
    public String getPrevpasswd() {
        return prevpasswd;
    }

    /**
     * @param prevpasswd the prevpasswd to set
     */
    public void setPrevpasswd(String prevpasswd) {
        this.prevpasswd = prevpasswd;
    }

    /**
     * @return the passreset
     */
    public String getPassreset() {
        return passreset;
    }

    /**
     * @param passreset the passreset to set
     */
    public void setPassreset(String passreset) {
        this.passreset = passreset;
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

    /**
     * @return the prevuserid
     */
    public String getPrevuserid() {
        return prevuserid;
    }

    /**
     * @param prevuserid the prevuserid to set
     */
    public void setPrevuserid(String prevuserid) {
        this.prevuserid = prevuserid;
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
     * @return the publicKeyModulus
     */
    public String getPublicKeyModulus() {
        return publicKeyModulus;
    }

    /**
     * @param publicKeyModulus the publicKeyModulus to set
     */
    public void setPublicKeyModulus(String publicKeyModulus) {
        this.publicKeyModulus = publicKeyModulus;
    }

    /**
     * @return the publicKeyExponent
     */
    public String getPublicKeyExponent() {
        return publicKeyExponent;
    }

    /**
     * @param publicKeyExponent the publicKeyExponent to set
     */
    public void setPublicKeyExponent(String publicKeyExponent) {
        this.publicKeyExponent = publicKeyExponent;
    }

    public String getJuminNo() {
        return juminNo;
    }

    public void setJuminNo(String juminNo) {
        this.juminNo = juminNo;
    }

    /**
     * 검색조건에 대한 변환처리
     */
    public void convertSearchParameter() {
        if (!CommonUtil.isNullBlank(getSearchUserNm())) {
            setUserNm("%" + getSearchUserNm() +"%");
        }
        if (!CommonUtil.isNullBlank(getSearchUserId())) {
            setUserId("%" + getSearchUserId() +"%");
        }
        if (!CommonUtil.isNullBlank(getSearchUserStCd())) {
            setUserStCd(getSearchUserStCd());
        }
        if (!CommonUtil.isNullBlank(getSearchUseSt())) {
            setUseSt(getSearchUseSt());
        }
    }

    /**
     * @return the currentMenu
     */
    public String getCurrentMenu() {
        return currentMenu;
    }

    /**
     * @param currentMenu the currentMenu to set
     */
    public void setCurrentMenu(String currentMenu) {
        this.currentMenu = currentMenu;
    }
}