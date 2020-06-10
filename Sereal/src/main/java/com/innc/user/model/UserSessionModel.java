/**
 * File Name : UserSessionModel.java
 * Copyright(c) 2016 - 2017 HNT & InnC. All rights reserved
 * 개발되는 소스는 해군사관학교 종합정보체계 시스템 구축을 위한 소스이며,
 * 작성되는 모든 파일은 해군사관학교 및 (주)사람과기술, (주)큐넥스에 소유권이 있습니다.
 */
package com.innc.user.model;

import java.util.List;

import org.springframework.context.annotation.Scope;

import com.innc.cmm.util.CommonUtil;
import com.innc.system.model.Menu;

/**
 * @since : 2016-10-06
 * @author : Administator
 * @version 1.0
 * @see
 * 
 *      <pre>
 * 개정이력
 * -----------------------------------------------------
 * 2016-10-06   Administator    TODO
 *      </pre>
 */
@Scope("Session")
public class UserSessionModel {
    private UserInfo userInfo;
    private List<Menu> menuList;

    private boolean isAuth;
    private String activePage;

    private String baseSuffix;

    private String todayStr;
    
    /**
     * activePage 기준 1Depth Menu
     */
    private String menuLv1;

    /**
     * activePage 기준 2Depth Menu
     */
    private String menuLv2;

    /**
     * activePage 기준 3Depth Menu
     */
    private String menuLv3;

    /**
     * activePage 기준 4Depth Menu
     */
    private String menuLv4;

    /**
     * 사용자가 조회중인 메뉴정보
     */
    private Menu activeMenu;

    /**
     * 사용자 즐겨찾기 목록을 json 형태로 관리
     */
    private String favoriteListJson;
    
    private String mainRDBMS;
    
    
    private boolean loginFlag = false;
    
    private boolean popupFlag = false;
    
    
    /**
     * @return the userInfo
     */
    public UserInfo getUserInfo() {
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        return userInfo;
    }

    /**
     * @param userInfo
     *            the userInfo to set
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * @return the menuList
     */
    public List<Menu> getMenuList() {
        return menuList;
    }

    /**
     * @param menuList
     *            the menuList to set
     */
    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    /**
     * @return the isAuth
     */
    public boolean isAuth() {
        return isAuth;
    }

    /**
     * @param isAuth
     *            the isAuth to set
     */
    public void setAuth(boolean isAuth) {
        this.isAuth = isAuth;
    }

    /**
     * @return the activePage
     */
    public String getActivePage() {
        return activePage;
    }

    /**
     * @param activePage
     *            the activePage to set
     */
    public void setActivePage(String activePage) {
        setMenuLv4("");
        setMenuLv3("");
        setMenuLv2("");
        if("/homepage/index".equals(activePage)) {
            setMenuLv1("/homepage/index");
        } else if ("/homepage/contents/member/MEM_001".equals(activePage)) {
            setMenuLv1("회원가입");
        }
        if (getMenuList() != null) {
            for (Menu menu : getMenuList()) {
                if (menu.isHasChilds()) {
                    // 2Depth Menu
                    for (Menu menu2 : menu.getChildMenuList()) {
                        if (menu2.isHasChilds()) {
                            for (Menu menu3 : menu2.getChildMenuList()) {
                                // 3Depth Menu
                                if (menu3.isHasChilds()) {
                                    for (Menu menu4 : menu3.getChildMenuList()) {
                                        if (!CommonUtil.isNullBlank(menu4.getRefPath()) && CommonUtil.fixNull(activePage).equalsIgnoreCase(menu4.getRefPath())) {
                                            setMenuLv4(menu4.getProgramName());
                                            setMenuLv3(menu3.getProgramName());
                                            setMenuLv2(menu2.getProgramName());
                                            setMenuLv1(menu.getProgramName());
                                            menu4.setMenuRole();
                                            setActiveMenu(menu4);
                                            break;
                                        }
                                    }
                                } else {
                                    if (!CommonUtil.isNullBlank(menu3.getRefPath()) && CommonUtil.fixNull(activePage).equalsIgnoreCase(menu3.getRefPath())) {
                                        setMenuLv3(menu3.getProgramName());
                                        setMenuLv2(menu2.getProgramName());
                                        setMenuLv1(menu.getProgramName());
                                        menu3.setMenuRole();
                                        setActiveMenu(menu3);
                                        break;
                                    }
                                }
                            }
                        } else {
                            if (!CommonUtil.isNullBlank(menu2.getRefPath()) && CommonUtil.fixNull(activePage).equalsIgnoreCase(menu2.getRefPath())) {
                                setMenuLv2(menu2.getProgramName());
                                setMenuLv1(menu.getProgramName());
                                menu2.setMenuRole();
                                setActiveMenu(menu2);
                                break;
                            }
                        }
                    }
                } else {
                    // 1Depth Menu
                    if (!CommonUtil.isNullBlank(menu.getRefPath()) && CommonUtil.fixNull(activePage).equalsIgnoreCase(menu.getRefPath())) {
                        setMenuLv4("");
                        setMenuLv3("");
                        setMenuLv2("");
                        setMenuLv1(menu.getProgramName());
                        menu.setMenuRole();
                        setActiveMenu(menu);
                        break;
                    }
                }
            }
        }
        this.activePage = activePage;
    }

    /**
     * @return the baseSuffix
     */
    public String getBaseSuffix() {
        return baseSuffix;
    }

    /**
     * @param baseSuffix
     *            the baseSuffix to set
     */
    public void setBaseSuffix(String baseSuffix) {
        this.baseSuffix = baseSuffix;
    }

    /**
     * @return the menuLv1
     */
    public String getMenuLv1() {
        return CommonUtil.fixNull(menuLv1);
    }

    /**
     * @param menuLv1
     *            the menuLv1 to set
     */
    public void setMenuLv1(String menuLv1) {
        this.menuLv1 = menuLv1;
    }

    /**
     * @return the menuLv2
     */
    public String getMenuLv2() {
        return CommonUtil.fixNull(menuLv2);
    }

    /**
     * @param menuLv2
     *            the menuLv2 to set
     */
    public void setMenuLv2(String menuLv2) {
        this.menuLv2 = menuLv2;
    }

    /**
     * @return the menuLv3
     */
    public String getMenuLv3() {
        return CommonUtil.fixNull(menuLv3);
    }

    /**
     * @param menuLv3
     *            the menuLv3 to set
     */
    public void setMenuLv3(String menuLv3) {
        this.menuLv3 = menuLv3;
    }

    /**
     * @return the menuLv4
     */
    public String getMenuLv4() {
        return CommonUtil.fixNull(menuLv4);
    }

    /**
     * @param menuLv4
     *            the menuLv4 to set
     */
    public void setMenuLv4(String menuLv4) {
        this.menuLv4 = menuLv4;
    }

    /**
     * @return the activeMenu
     */
    public Menu getActiveMenu() {
        return activeMenu;
    }

    /**
     * @param activeMenu
     *            the activeMenu to set
     */
    public void setActiveMenu(Menu activeMenu) {
        this.activeMenu = activeMenu;
    }

    /**
     * @return the favoriteListJson
     */
    public String getFavoriteListJson() {
        return favoriteListJson;
    }

    /**
     * @param favoriteListJson the favoriteListJson to set
     */
    public void setFavoriteListJson(String favoriteListJson) {
        this.favoriteListJson = favoriteListJson;
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
     * @return the todayStr
     */
    public String getTodayStr() {
        return todayStr;
    }

    /**
     * @param todayStr the todayStr to set
     */
    public void setTodayStr(String todayStr) {
        this.todayStr = todayStr;
    }

    /**
     * @return the loginFlag
     */
    public boolean isLoginFlag() {
        return loginFlag;
    }

    /**
     * @param loginFlag the loginFlag to set
     */
    public void setLoginFlag(boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    /**
     * @return the popupFlag
     */
    public boolean isPopupFlag() {
        return popupFlag;
    }

    /**
     * @param popupFlag the popupFlag to set
     */
    public void setPopupFlag(boolean popupFlag) {
        this.popupFlag = popupFlag;
    }
}
