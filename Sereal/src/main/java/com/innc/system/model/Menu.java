package com.innc.system.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.innc.cmm.util.CommonUtil;
import com.innc.user.model.UserInfo;

/**
 * <p>
 * Menu정보를 설정하기위한 Bean Class
 * </p>
 * 
 * @author hojong.seo
 */
public class Menu extends UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    // 처리유형 (insert, update)
    private String procType;

    @NotEmpty
    private String code;
    private String parentCode;

    @NotEmpty
    private String type;
    @NotEmpty
    private String programName;
    private String refPath;
    private String leftMenuIcon;
    private String pageMenuIcon;
    private int sortNo;
    private String order;
    private String remark;
    private String useYn;
    private String useSt;
    private int cnt;
    private boolean hasChilds;
    private List<Menu> childMenuList;

    private String mainRDBMS;

    private String systemMode;
    private String schType;
    private String userStCd;
    private String userId;

    /**
     * 사용자 그룹의 메뉴 권한
     */
    private String menuRole;

    private boolean save;
    private boolean delete;
    private boolean search;
    private boolean excelDown;
    private boolean excelUp;
    private boolean print;
    private boolean batch;
    private boolean fileDown;
    private boolean fileUp;
    
    
    private String convertRemark;
    private String viewYn;
    /**
     * <p>
     * </p>
     * 
     * @return the procType
     */
    public String getProcType() {
        return procType;
    }

    /**
     * <p>
     * </p>
     * 
     * @param procType
     *            the procType to set
     */
    public void setProcType(String procType) {
        this.procType = procType;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the parentCode
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * @param parentCode
     *            the parentCode to set
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the programName
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * @param programName
     *            the programName to set
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     * @return the refPath
     */
    public String getRefPath() {
        return refPath;
    }

    /**
     * @param refPath
     *            the refPath to set
     */
    public void setRefPath(String refPath) {
        this.refPath = refPath;
    }

    /**
     * @return the leftMenuIcon
     */
    public String getLeftMenuIcon() {
        return leftMenuIcon;
    }

    /**
     * @param leftMenuIcon
     *            the leftMenuIcon to set
     */
    public void setLeftMenuIcon(String leftMenuIcon) {
        this.leftMenuIcon = leftMenuIcon;
    }

    /**
     * @return the pageMenuIcon
     */
    public String getPageMenuIcon() {
        return pageMenuIcon;
    }

    /**
     * @param pageMenuIcon
     *            the pageMenuIcon to set
     */
    public void setPageMenuIcon(String pageMenuIcon) {
        this.pageMenuIcon = pageMenuIcon;
    }

    /**
     * @return the sortNo
     */
    public int getSortNo() {
        return sortNo;
    }

    /**
     * @param sortNo
     *            the sortNo to set
     */
    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the cnt
     */
    public int getCnt() {
        return cnt;
    }

    /**
     * @param cnt
     *            the cnt to set
     */
    public void setCnt(int cnt) {
        this.cnt = cnt;
        if (cnt > 0)
            setHasChilds(true);
    }

    /**
     * @return the hasChilds
     */
    public boolean isHasChilds() {
        return hasChilds;
    }

    /**
     * @param hasChilds
     *            the hasChilds to set
     */
    public void setHasChilds(boolean hasChilds) {
        this.hasChilds = hasChilds;
    }

    /**
     * @return the childMenuList
     */
    public List<Menu> getChildMenuList() {
        return childMenuList;
    }

    /**
     * @param childMenuList
     *            the childMenuList to set
     */
    public void setChildMenuList(List<Menu> childMenuList) {
        this.childMenuList = childMenuList;
    }

    public String hasChildsValue() {
        return String.valueOf(isHasChilds());
    }

    /**
     * <p>
     * </p>
     * 
     * @return the useYn
     */
    public String getUseYn() {
        return useYn;
    }

    /**
     * <p>
     * </p>
     * 
     * @param useYn
     *            the useYn to set
     */
    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    /**
     * <p>
     * </p>
     * 
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * <p>
     * </p>
     * 
     * @param order
     *            the order to set
     */
    public void setOrder(String order) {
        this.order = order;
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
     * @return the mainRDBMS
     */
    public String getMainRDBMS() {
        return mainRDBMS;
    }

    /**
     * @param mainRDBMS
     *            the mainRDBMS to set
     */
    public void setMainRDBMS(String mainRDBMS) {
        this.mainRDBMS = mainRDBMS;
    }

    /**
     * @return the systemMode
     */
    public String getSystemMode() {
        return systemMode;
    }

    /**
     * @param systemMode
     *            the systemMode to set
     */
    public void setSystemMode(String systemMode) {
        this.systemMode = systemMode;
    }

    /**
     * @return the schType
     */
    public String getSchType() {
        return schType;
    }

    /**
     * @param schType
     *            the schType to set
     */
    public void setSchType(String schType) {
        this.schType = schType;
    }

    /**
     * @return the userStCd
     */
    public String getUserStCd() {
        return userStCd;
    }

    /**
     * @param userStCd
     *            the userStCd to set
     */
    public void setUserStCd(String userStCd) {
        this.userStCd = userStCd;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the menuRole
     */
    public String getMenuRole() {
        return menuRole;
    }

    /**
     * @param menuRole
     *            the menuRole to set
     */
    public void setMenuRole(String menuRole) {
        this.menuRole = menuRole;
    }

    /**
     * @return the save
     */
    public boolean isSave() {
        return save;
    }

    /**
     * @param save the save to set
     */
    public void setSave(boolean save) {
        this.save = save;
    }

    /**
     * @return the delete
     */
    public boolean isDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    /**
     * @return the search
     */
    public boolean isSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(boolean search) {
        this.search = search;
    }

    /**
     * @return the excelDown
     */
    public boolean isExcelDown() {
        return excelDown;
    }

    /**
     * @param excelDown the excelDown to set
     */
    public void setExcelDown(boolean excelDown) {
        this.excelDown = excelDown;
    }

    /**
     * @return the excelUp
     */
    public boolean isExcelUp() {
        return excelUp;
    }

    /**
     * @param excelUp the excelUp to set
     */
    public void setExcelUp(boolean excelUp) {
        this.excelUp = excelUp;
    }

    /**
     * @return the print
     */
    public boolean isPrint() {
        return print;
    }

    /**
     * @param print the print to set
     */
    public void setPrint(boolean print) {
        this.print = print;
    }

    /**
     * @return the batch
     */
    public boolean isBatch() {
        return batch;
    }

    /**
     * @param batch the batch to set
     */
    public void setBatch(boolean batch) {
        this.batch = batch;
    }

    /**
     * @return the fileDown
     */
    public boolean isFileDown() {
        return fileDown;
    }

    /**
     * @param fileDown the fileDown to set
     */
    public void setFileDown(boolean fileDown) {
        this.fileDown = fileDown;
    }

    /**
     * @return the fileUp
     */
    public boolean isFileUp() {
        return fileUp;
    }

    /**
     * @param fileUp the fileUp to set
     */
    public void setFileUp(boolean fileUp) {
        this.fileUp = fileUp;
    }

    /**
     * @return the convertRemark
     */
    public String getConvertRemark() {
        if (CommonUtil.isNullBlank(convertRemark)) {
            if (!CommonUtil.isNullBlank(getRemark())) {
                String returnStr = "\\\\" + "n";
                convertRemark = getRemark().replaceAll("\\n", returnStr);
            }
        }
        return convertRemark;
    }

    /**
     * @param convertRemark the convertRemark to set
     */
    public void setConvertRemark(String convertRemark) {
        this.convertRemark = convertRemark;
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

    /**
     * @return the menuRoleNm
     */
    public void setMenuRole() {
        if (!CommonUtil.isNullBlank(getMenuRole())) {
            int length = getMenuRole().length();
            String s = getMenuRole();
            for (int i = 0; i < length; i++) {
                if (i == 0) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        setSave(true);
                    }
                } else if (i == 1) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        setDelete(true);
                    }
                } else if (i == 2) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        setSearch(true);
                    }
                } else if (i == 3) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        setPrint(true);
                    }
                } else if (i == 4) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        setExcelDown(true);
                    }
                } else if (i == 5) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        setExcelUp(true);
                    }
                } else if (i == 6) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        setFileDown(true);
                    }
                } else if (i == 7) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        setFileUp(true);
                    }
                } else if (i == 8) {
                    if("Y".equalsIgnoreCase(s.substring(i, i+1))) {
                        setBatch(true);
                    }
                }
            }
        }
    }
}