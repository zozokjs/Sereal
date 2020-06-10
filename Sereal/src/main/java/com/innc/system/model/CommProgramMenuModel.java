/**
  * File Name : CommProgramMenuModel.java
  * 
  * Copyright(c) 2016 InnC Co.,Ltd. All rights reserved
  * 
  */
package com.innc.system.model;

import java.io.Serializable;

/**
  * @since : 2016-10-28
  * @author : 
  * @version 1.0
  * @see
  * <pre>
  * 개정이력
  * -----------------------------------------------------
  * 2016-10-28       TODO
  * </pre>
  */
public class CommProgramMenuModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private long seq;
    private String code;
    private String parentCode;
    private String type;
    private String programName;
    private String refPath;
    private String leftMenuIcon;
    private String pageMenuIcon;
    private long sortNo;
    private String remark;
    private long useSt;
    private String regId;
    private String regDt;
    private String regIp;
    private String updId;
    private String updDt;
    private String updIp;
    private String viewYn;

    /**
     * 
     * @return seq long 
     */
    public long getSeq() {
        return seq;
    }

    /**
     * 
     * @return code String 
     */
    public String getCode() {
        return code;
    }

    /**
     * 
     * @return parentCode String 
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 
     * @return type String 
     */
    public String getType() {
        return type;
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
     * @return leftMenuIcon String 
     */
    public String getLeftMenuIcon() {
        return leftMenuIcon;
    }

    /**
     * 
     * @return pageMenuIcon String 
     */
    public String getPageMenuIcon() {
        return pageMenuIcon;
    }

    /**
     * 
     * @return sortNo long 
     */
    public long getSortNo() {
        return sortNo;
    }

    /**
     * 
     * @return remark String 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @return useSt long 
     */
    public long getUseSt() {
        return useSt;
    }

    /**
     * 
     * @return regId String 
     */
    public String getRegId() {
        return regId;
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
     * @return updId String 
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * 
     * @return updDt String 
     */
    public String getUpdDt() {
        return updDt;
    }

    /**
     * 
     * @return updIp String 
     */
    public String getUpdIp() {
        return updIp;
    }

    /**
     * 
     * @param seq  
     */
    public void setSeq(long seq) {
        this.seq = seq;
    }

    /**
     * 
     * @param code  
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 
     * @param parentCode  
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * 
     * @param type  
     */
    public void setType(String type) {
        this.type = type;
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
     * @param leftMenuIcon  
     */
    public void setLeftMenuIcon(String leftMenuIcon) {
        this.leftMenuIcon = leftMenuIcon;
    }

    /**
     * 
     * @param pageMenuIcon  
     */
    public void setPageMenuIcon(String pageMenuIcon) {
        this.pageMenuIcon = pageMenuIcon;
    }

    /**
     * 
     * @param sortNo  
     */
    public void setSortNo(long sortNo) {
        this.sortNo = sortNo;
    }

    /**
     * 
     * @param remark  
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 
     * @param useSt  
     */
    public void setUseSt(long useSt) {
        this.useSt = useSt;
    }

    /**
     * 
     * @param regId  
     */
    public void setRegId(String regId) {
        this.regId = regId;
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
     * 
     * @param updId  
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    /**
     * 
     * @param updDt  
     */
    public void setUpdDt(String updDt) {
        this.updDt = updDt;
    }

    /**
     * 
     * @param updIp  
     */
    public void setUpdIp(String updIp) {
        this.updIp = updIp;
    }

    /**
     * @return the viewYn
     */
    public String getViewYn() {
        return viewYn;
    }

    /**
     * @param viewYn the viewYn to set
     */
    public void setViewYn(String viewYn) {
        this.viewYn = viewYn;
    }
}