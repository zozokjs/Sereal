package com.innc.user.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.innc.cmm.util.CommonUtil;

public class UserInfo {

	private int seqNo;

	@NotEmpty
	@Size(min=5, max=30)
	private String userId;

	private String userNm;

	private String userStCd;

	private String useSt;

	private String userIp;

	private String userType; //(S: 학생, P:교수, A:admin)

	private String userPasswd;
	
	private String juminNo;
	
	private Integer userIdx;
	
	public Integer getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(Integer userIdx) {
		this.userIdx = userIdx;
	}
	/**
	 * @return the seqNo
	 */
	public int getSeqNo() {
		return seqNo;
	}
	/**
	 * @param seqNo the seqNo to set
	 */
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return CommonUtil.fixNull(userId);
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	/**
	 * @return the userStCd
	 */
	public String getUserStCd() {
		return userStCd;
	}
	/**
	 * @param userStCd the userStCd to set
	 */
	public void setUserStCd(String userStCd) {
		this.userStCd = userStCd;
	}
	/**
	 * @return the useSt
	 */
	public String getUseSt() {
		return useSt;
	}
	/**
	 * @param useSt the useSt to set
	 */
	public void setUseSt(String useSt) {
		this.useSt = useSt;
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
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	/**
     * @return the userPasswd
     */
    public String getUserPasswd() {
        return userPasswd;
    }
    /**
     * @param userPasswd the userPasswd to set
     */
    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }
    
    public String getJuminNo() {
        return juminNo;
    }
    public void setJuminNo(String juminNo) {
        this.juminNo = juminNo;
    }
    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("UserInfo[ seqNo=%s, userId=%s, userNm=%s, userStCd=%s, useSt=%s, userIp=%s ,userType=%s]",
		        seqNo, userId, userNm, userStCd, useSt, userIp,userType);
	}
}
