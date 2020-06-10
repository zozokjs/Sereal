/**
 * <pre>
 * COPYRIGHT (C) 1999-2015 PoiznKorea CO., LTD. ALL RIGHTS RESERVED
 * Project         : common
 * File Name	   : com.poizn.common.entity / CommonAuthGroup.java
 * Create Date     : 2016. 4. 13.
 * Initial Creator : PoiznKorea
 * Change History
 * -------------------------------------------------------------------------------------
 * Date    : 2016. 4. 13.
 * Author  : hojong.seo
 * Version : 1.0   First release.
 * -------------------------------------------------------------------------------------
 * Description
 *
 * -------------------------------------------------------------------------------------
 * </pre>
 */
package com.innc.system.model;

import com.innc.cmm.util.CommonUtil;

/**
 * <p>
 * </p>
 * 
 * @author hojong.seo
 */
public class CommonAuthGroup {
    // COMM_AUTH_GROUP
    private long seq;
    private String authGroupCd;
    private String authGroupNm;
    private String remark;

    // COMM_AUTH_GROUP_MEMBER
    private long authSeq;
    private long userSeq;
    private String memberId;
    private String memberName;

    // COMM_AUTH_GROUP_MENU
    private long menuSeq;
    private String menuType;
    private String menuName;
    private String menuPath;
    private String menuRole;
    private String menuUseSt;
    private String menuUseStNm;
    private String menuCode;
    private String parentCode;
    private String menuSort;
    private String menuFullPath;
    private String menuRoleNm;
    private String authType;
    
    // Common
    private String useSt;
    private String userId;
    private String wrkDivName; // 직구명
    private String jobDivName; // 직종명
    private String jobRnkName; // 직급명
    private String userIp; // 접속IP

    private String useStNm;
    private String updNm;
    private String updId;
    private String updDt;

    private String userNm;
    private String userStNm;

    private long seqno;
    
    private String menuAuthType;
    
    /**
     * <p>
     * </p>
     * 
     * @return the seq
     */
    public long getSeq() {
        return seq;
    }

    /**
     * <p>
     * </p>
     * 
     * @param seq
     *            the seq to set
     */
    public void setSeq(long seq) {
        this.seq = seq;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the authGroupCd
     */
    public String getAuthGroupCd() {
        return authGroupCd;
    }

    /**
     * <p>
     * </p>
     * 
     * @param authGroupCd
     *            the authGroupCd to set
     */
    public void setAuthGroupCd(String authGroupCd) {
        this.authGroupCd = authGroupCd;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the authGroupNm
     */
    public String getAuthGroupNm() {
        return authGroupNm;
    }

    /**
     * <p>
     * </p>
     * 
     * @param authGroupNm
     *            the authGroupNm to set
     */
    public void setAuthGroupNm(String authGroupNm) {
        this.authGroupNm = authGroupNm;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <p>
     * </p>
     * 
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the authSeq
     */
    public long getAuthSeq() {
        return authSeq;
    }

    /**
     * <p>
     * </p>
     * 
     * @param authSeq
     *            the authSeq to set
     */
    public void setAuthSeq(long authSeq) {
        this.authSeq = authSeq;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the userSeq
     */
    public long getUserSeq() {
        return userSeq;
    }

    /**
     * <p>
     * </p>
     * 
     * @param userSeq
     *            the userSeq to set
     */
    public void setUserSeq(long userSeq) {
        this.userSeq = userSeq;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the menuSeq
     */
    public long getMenuSeq() {
        return menuSeq;
    }

    /**
     * <p>
     * </p>
     * 
     * @param menuSeq
     *            the menuSeq to set
     */
    public void setMenuSeq(long menuSeq) {
        this.menuSeq = menuSeq;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the useSt
     */
    public String getUseSt() {
        return useSt;
    }

    /**
     * <p>
     * </p>
     * 
     * @param useSt
     *            the useSt to set
     */
    public void setUseSt(String useSt) {
        this.useSt = useSt;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * <p>
     * </p>
     * 
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * <p>
     * </p>
     * 
     * @param memberId
     *            the memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the memberName
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * <p>
     * </p>
     * 
     * @param memberName
     *            the memberName to set
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the menuType
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * <p>
     * </p>
     * 
     * @param menuType
     *            the menuType to set
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the menuName
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * <p>
     * </p>
     * 
     * @param menuName
     *            the menuName to set
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the menuPath
     */
    public String getMenuPath() {
        return menuPath;
    }

    /**
     * <p>
     * </p>
     * 
     * @param menuPath
     *            the menuPath to set
     */
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the wrkDivName
     */
    public String getWrkDivName() {
        return wrkDivName;
    }

    /**
     * <p>
     * </p>
     * 
     * @param wrkDivName
     *            the wrkDivName to set
     */
    public void setWrkDivName(String wrkDivName) {
        this.wrkDivName = wrkDivName;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the jobDivName
     */
    public String getJobDivName() {
        return jobDivName;
    }

    /**
     * <p>
     * </p>
     * 
     * @param jobDivName
     *            the jobDivName to set
     */
    public void setJobDivName(String jobDivName) {
        this.jobDivName = jobDivName;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the jobRnkName
     */
    public String getJobRnkName() {
        return jobRnkName;
    }

    /**
     * <p>
     * </p>
     * 
     * @param jobRnkName
     *            the jobRnkName to set
     */
    public void setJobRnkName(String jobRnkName) {
        this.jobRnkName = jobRnkName;
    }

    /**
     * @return the userIp
     */
    public String getUserIp() {
        return userIp;
    }

    /**
     * @param userIp
     *            the userIp to set
     */
    public void setUserIp(String userIp) {
        this.userIp = userIp;
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
     * @return the updNm
     */
    public String getUpdNm() {
        return updNm;
    }

    /**
     * @param updNm
     *            the updNm to set
     */
    public void setUpdNm(String updNm) {
        this.updNm = updNm;
    }

    /**
     * @return the updId
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * @param updId
     *            the updId to set
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    /**
     * @return the updDt
     */
    public String getUpdDt() {
        return updDt;
    }

    /**
     * @param updDt
     *            the updDt to set
     */
    public void setUpdDt(String updDt) {
        this.updDt = updDt;
    }

    /**
     * @return the userNm
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * @param userNm
     *            the userNm to set
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
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
     * @return the seqno
     */
    public long getSeqno() {
        return seqno;
    }

    /**
     * @param seqno the seqno to set
     */
    public void setSeqno(long seqno) {
        this.seqno = seqno;
    }

    /**
     * @return the menuRole
     */
    public String getMenuRole() {
        return menuRole;
    }

    /**
     * @param menuRole the menuRole to set
     */
    public void setMenuRole(String menuRole) {
        this.menuRole = menuRole;
    }

    /**
     * @return the menuUseSt
     */
    public String getMenuUseSt() {
        return menuUseSt;
    }

    /**
     * @param menuUseSt the menuUseSt to set
     */
    public void setMenuUseSt(String menuUseSt) {
        this.menuUseSt = menuUseSt;
    }

    /**
     * @return the menuUseStNm
     */
    public String getMenuUseStNm() {
        return menuUseStNm;
    }

    /**
     * @param menuUseStNm the menuUseStNm to set
     */
    public void setMenuUseStNm(String menuUseStNm) {
        this.menuUseStNm = menuUseStNm;
    }

    /**
     * @return the menuCode
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * @param menuCode the menuCode to set
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * @return the parentCode
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * @param parentCode the parentCode to set
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * @return the menuSort
     */
    public String getMenuSort() {
        return menuSort;
    }

    /**
     * @param menuSort the menuSort to set
     */
    public void setMenuSort(String menuSort) {
        this.menuSort = menuSort;
    }

    /**
     * @return the menuFullPath
     */
    public String getMenuFullPath() {
        return menuFullPath;
    }

    /**
     * @param menuFullPath the menuFullPath to set
     */
    public void setMenuFullPath(String menuFullPath) {
        this.menuFullPath = menuFullPath;
    }

    /**
     * @return the authType
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * @param authType the authType to set
     */
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    /**
     * @return the menuRoleNm
     */
    public String getMenuRoleNm() {
        StringBuilder sb = new StringBuilder();
        if (!CommonUtil.isNullBlank(getMenuRole())) {
            int length = getMenuRole().length();
            String s = getMenuRole();
            for (int i = 0; i < length; i++) {
                if (i == 0) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        sb.append("저장");
                    }
                } else if (i == 1) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        if (!"".equalsIgnoreCase(sb.toString())) {
                            sb.append("/");
                        }
                        sb.append("삭제");
                    }
                } else if (i == 2) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        if (!"".equalsIgnoreCase(sb.toString())) {
                            sb.append("/");
                        }
                        sb.append("조회");
                    }
                } else if (i == 3) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        if (!"".equalsIgnoreCase(sb.toString())) {
                            sb.append("/");
                        }
                        sb.append("프린트");
                    }
                } else if (i == 4) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        if (!"".equalsIgnoreCase(sb.toString())) {
                            sb.append("/");
                        }
                        sb.append("엑셀다운로드");
                    }
                } else if (i == 5) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        if (!"".equalsIgnoreCase(sb.toString())) {
                            sb.append("/");
                        }
                        sb.append("엑셀업로드");
                    }
                } else if (i == 6) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        if (!"".equalsIgnoreCase(sb.toString())) {
                            sb.append("/");
                        }
                        sb.append("파일다운로드");
                    }
                } else if (i == 7) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        if (!"".equalsIgnoreCase(sb.toString())) {
                            sb.append("/");
                        }
                        sb.append("파일업로드");
                    }
                } else if (i == 8) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        if (!"".equalsIgnoreCase(sb.toString())) {
                            sb.append("/");
                        }
                        sb.append("일괄처리");
                    }
                }
            }
        }
        menuRoleNm = sb.toString();
        return menuRoleNm;
    }

    /**
     * @param menuRoleNm the menuRoleNm to set
     */
    public void setMenuRoleNm(String menuRoleNm) {
        this.menuRoleNm = menuRoleNm;
    }

    /**
     * @return the menuAuthType
     */
    public String getMenuAuthType() {
        return menuAuthType;
    }

    /**
     * @param menuAuthType the menuAuthType to set
     */
    public void setMenuAuthType(String menuAuthType) {
        this.menuAuthType = menuAuthType;
    }
}