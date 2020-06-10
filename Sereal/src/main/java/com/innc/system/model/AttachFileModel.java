/**
 * <pre>
 * COPYRIGHT (C) 1999-2015 PoiznKorea CO., LTD. ALL RIGHTS RESERVED
 * File Name	   : AttachFileVO.java
 * Create Date     : 2015. 3. 17.
 * Initial Creator : KISUNG, PARK
 * Change History
 * -------------------------------------------------------------------------------------
 * Date    : 2015. 3. 17.
 * Author  : KISUNG, PARK
 * Version : 1.0   First release.
 * -------------------------------------------------------------------------------------
 *
 * Description
 * -------------------------------------------------------------------------------------
 * 
 * </pre>
 */

package com.innc.system.model;

public class AttachFileModel {
    private int fileIdx;
    private String filePath;
    private String fileRealPath;
    private String fileNm;
    private String fileRealNm;
    private int sortOrd;
    private String reltTgt;
    private int reltTgtKeyIdx;
    private String reltTgtKeyNo;
    private int subReltTgtKeyIdx;
    private String subReltTgtKeyNo;
    private String delFlag;
    private String insUid;
    private String updUid;
    private String insDate;
    private String updDate;

    private String name;
    private String extension;
    private long size;

    /**
     * <pre>
     * to get the fileIdx
     * </pre>
     *
     * @return the fileIdx
     */
    public int getFileIdx() {
        return fileIdx;
    }

    /**
     * <pre>
     * the fileIdx to set
     * </pre>
     *
     * @param fileIdx
     */
    public void setFileIdx(int fileIdx) {
        this.fileIdx = fileIdx;
    }

    /**
     * <pre>
     * to get the filePath
     * </pre>
     *
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * <pre>
     * the filePath to set
     * </pre>
     *
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * <pre>
     * to get the fileRealPath
     * </pre>
     *
     * @return the fileRealPath
     */
    public String getFileRealPath() {
        return fileRealPath;
    }

    /**
     * <pre>
     * the fileRealPath to set
     * </pre>
     *
     * @param fileRealPath
     */
    public void setFileRealPath(String fileRealPath) {
        this.fileRealPath = fileRealPath;
    }

    /**
     * <pre>
     * to get the fileNm
     * </pre>
     *
     * @return the fileNm
     */
    public String getFileNm() {
        return fileNm;
    }

    /**
     * <pre>
     * the fileNm to set
     * </pre>
     *
     * @param fileNm
     */
    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }

    /**
     * <pre>
     * to get the fileRealNm
     * </pre>
     *
     * @return the fileRealNm
     */
    public String getFileRealNm() {
        return fileRealNm;
    }

    /**
     * <pre>
     * the fileRealNm to set
     * </pre>
     *
     * @param fileRealNm
     */
    public void setFileRealNm(String fileRealNm) {
        this.fileRealNm = fileRealNm;
    }

    /**
     * <pre>
     * to get the sortOrd
     * </pre>
     *
     * @return the sortOrd
     */
    public int getSortOrd() {
        return sortOrd;
    }

    /**
     * <pre>
     * the sortOrd to set
     * </pre>
     *
     * @param sortOrd
     */
    public void setSortOrd(int sortOrd) {
        this.sortOrd = sortOrd;
    }

    /**
     * <pre>
     * to get the reltTgt
     * </pre>
     *
     * @return the reltTgt
     */
    public String getReltTgt() {
        return reltTgt;
    }

    /**
     * <pre>
     * the reltTgt to set
     * </pre>
     *
     * @param reltTgt
     */
    public void setReltTgt(String reltTgt) {
        this.reltTgt = reltTgt;
    }

    /**
     * <pre>
     * to get the reltTgtKeyIdx
     * </pre>
     *
     * @return the reltTgtKeyIdx
     */
    public int getReltTgtKeyIdx() {
        return reltTgtKeyIdx;
    }

    /**
     * <pre>
     * the reltTgtKeyIdx to set
     * </pre>
     *
     * @param reltTgtKeyIdx
     */
    public void setReltTgtKeyIdx(int reltTgtKeyIdx) {
        this.reltTgtKeyIdx = reltTgtKeyIdx;
    }

    /**
     * <pre>
     * to get the reltTgtKeyNo
     * </pre>
     *
     * @return the reltTgtKeyNo
     */
    public String getReltTgtKeyNo() {
        return reltTgtKeyNo;
    }

    /**
     * <pre>
     * the reltTgtKeyNo to set
     * </pre>
     *
     * @param reltTgtKeyNo
     */
    public void setReltTgtKeyNo(String reltTgtKeyNo) {
        this.reltTgtKeyNo = reltTgtKeyNo;
    }

    /**
     * <pre>
     * to get the delFlag
     * </pre>
     *
     * @return the delFlag
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * <pre>
     * the delFlag to set
     * </pre>
     *
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * <pre>
     * to get the insUid
     * </pre>
     *
     * @return the insUid
     */
    public String getInsUid() {
        return insUid;
    }

    /**
     * <pre>
     * the insUid to set
     * </pre>
     *
     * @param insUid
     */
    public void setInsUid(String insUid) {
        this.insUid = insUid;
    }

    /**
     * <pre>
     * to get the updUid
     * </pre>
     *
     * @return the updUid
     */
    public String getUpdUid() {
        return updUid;
    }

    /**
     * <pre>
     * the updUid to set
     * </pre>
     *
     * @param updUid
     */
    public void setUpdUid(String updUid) {
        this.updUid = updUid;
    }

    /**
     * <pre>
     * to get the insDate
     * </pre>
     *
     * @return the insDate
     */
    public String getInsDate() {
        return insDate;
    }

    /**
     * <pre>
     * the insDate to set
     * </pre>
     *
     * @param insDate
     */
    public void setInsDate(String insDate) {
        this.insDate = insDate;
    }

    /**
     * <pre>
     * to get the updDate
     * </pre>
     *
     * @return the updDate
     */
    public String getUpdDate() {
        return updDate;
    }

    /**
     * <pre>
     * the updDate to set
     * </pre>
     *
     * @param updDate
     */
    public void setUpdDate(String updDate) {
        this.updDate = updDate;
    }

    /**
     * <pre>
     * to get the name
     * </pre>
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * <pre>
     * the name to set
     * </pre>
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <pre>
     * to get the extension
     * </pre>
     *
     * @return the extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * <pre>
     * the extension to set
     * </pre>
     *
     * @param extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * <pre>
     * to get the size
     * </pre>
     *
     * @return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * <pre>
     * the size to set
     * </pre>
     *
     * @param size
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * <pre>
     * to get the subReltTgtKeyIdx
     * </pre>
     *
     * @return the subReltTgtKeyIdx
     */
    public int getSubReltTgtKeyIdx() {
        return subReltTgtKeyIdx;
    }

    /**
     * <pre>
     * the subReltTgtKeyIdx to set
     * </pre>
     *
     * @param subReltTgtKeyIdx
     */
    public void setSubReltTgtKeyIdx(int subReltTgtKeyIdx) {
        this.subReltTgtKeyIdx = subReltTgtKeyIdx;
    }

    /**
     * <pre>
     * to get the subReltTgtKeyNo
     * </pre>
     *
     * @return the subReltTgtKeyNo
     */
    public String getSubReltTgtKeyNo() {
        return subReltTgtKeyNo;
    }

    /**
     * <pre>
     * the subReltTgtKeyNo to set
     * </pre>
     *
     * @param subReltTgtKeyNo
     */
    public void setSubReltTgtKeyNo(String subReltTgtKeyNo) {
        this.subReltTgtKeyNo = subReltTgtKeyNo;
    }
}
