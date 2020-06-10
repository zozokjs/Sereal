
/** 화면 이동시 사용되는 request parameter 전용 map */
var pageMoveReqParam = null;

/**
 * Menu Click시 동작 제어
 */
function initMenuControl() {
    "use strict";

    var pageTitle = "DashBoard";
    var pageLink = $("a[data-role=PAGE]"); // dom ref
    var naviListItem = "";

    pageLink.on("click", function(e) {
       try {
            e.preventDefault();
            pageTitle = $(this).attr("data-title");
            $("#contentTitle").find("[data-role=titlePopover]").attr("data-content", $(this).attr("data-remark"));

            /** Navi String create start **/
            var pList = $(this).parents()
                              .map(function () {
                                  if (this.tagName.toLowerCase() != 'li') return;
                                  return "<li class='active'>" + $(this).attr("data-title") + "</li>";
                              });
            var cnt = pList.length;
            naviListItem = "<li><a href='/main'><i class='fa fa-dashboard'></i> Home</a></li>";

            var idx = 0;
            for (idx = cnt; idx > 0; idx --) {
                naviListItem += pList[idx-1];
            }

            pList = null;
            /** Navi String create end **/

            // submenu의 active일괄삭제
            $("#menuList").find("li:not(.treeview)").removeClass("active");

            // 선택한 메뉴를 강제로 active
            $(this).parent().addClass("active");

            //kendo.ui.progress($("section[id=contentArea]"), true);
            if (window.history && window.history.pushState) {
                // HTML5사양 pushState사용이 가능한 경우에만 동작
                var pushState = {};
                pushState["pageTitle"] = pageTitle;
                pushState["menuId"] = $(this).attr("id");
                history.pushState(pushState, null, $(this).attr("data-url"));
                changeContents(location.pathname);
                return false;
            } else {
                // HTML5사양 미 지원의 경우는 hash사용
                location.hash = $(this).attr("data-url");
                $(window).trigger("hashchange");
            }
        } catch (e) {
            alert(e);
        }
    });

    // localtion.hash를 이용하여 화면을 변경하는 경우
    $(window).on('hashchange', function() {
    	$(".modal-backdrop").hide();
        url = window.location.hash.substring(1);
        if(url == "/main" || url == ""){
            url = "/page/main/top";
        }
        changeContents(url);
    });

    // 브라우저 postback, next button대응
    $(window).on('popstate', function (e) {
    	$(".modal-backdrop").hide();
        if (window.history && window.history.pushState) {
            // HTML5 ver
            if (e.originalEvent && e.originalEvent.state) {
                var state = e.originalEvent.state;
                // page Title reset
                pageTitle = state.pageTitle;
                // submenu의 active일괄삭제
                $("#menuList").find("li:not(.treeview)").removeClass("active");
                // 선택한 메뉴를 강제로 active
                $(document).find("#sidebar").find("a[id=" + state.menuId + "]").parent().addClass("active");
                // 화면 description변경
                $("#contentTitle").find("[data-role=titlePopover]").attr("data-content", $(document).find("#sidebar").find("a[id=" + state.menuId + "]").attr("data-remark"));

                changeContents(location.pathname);
            } else {
                changeContents("/page/main/top");
                return;
            }
        }
    });

    // history.pushState를 이용하여 화면을 변경하는 경우
    function changeContents(url) {
        $.ajax({
            url: url,
            type: "GET",
            dataType: "html",
            data: pageMoveReqParam,
            headers: {
                Accept: "text/html;"
            },
            beforeSend: function() {
                return true;
            },
            success: function(data) {
                $("section[id=contentArea]").fadeOut("slow", function() {
                    $(this).html(data).fadeIn("fast", function() {
                        // 제어시 필요한 동작 기재
                    });
                });

                // change title
                $("section[id=contentTitle]").fadeOut("", function() {
                    $("span[id=titleArea]").text("");
                    $("#naviList").html("");
                    $("#contentTitle").find("[data-role=titlePopover]").attr("data-code", "");
                    $("#contentTitle").find("[data-role=titlePopover]").popover("hide");
                    $("#contentTitle").find("[data-role=titlePopover]").hide();
                    $(".main-body").find("[data-role=reoCodePopover]").popover("hide");

                    $("section[id=contentTitle]").fadeIn("fast", function() {
                        $("span[id=titleArea]").text(pageTitle);
                        $("#naviList").html(naviListItem);
                        $("#contentTitle").find("[data-role=titlePopover]").show();
                    });
                });
            },
            error: function (data, textStatus, jqXHR) {
                //console.log(data);
                //console.log(textStatus);
            },
            complete: function(data) {
                //kendo.ui.progress($("section[id=contentArea]"), false);
                pageMoveReqParam = null; // global parameter reset
            }
        });
    }
    pageLink = null; // memory leak block
}

/**
* 현재 화면(layout상 body 부분)을 다시 읽어오는 처리.
* body만 재 표시하기때문에 Left Menu, Header부분은 변경되지 않음
*/
function reloadContents() {
	// main 화면의 경우, dashboard content정보만을 추출해서 화면에 표시하도록 url변경
	var reqUrl = "";
    if (window.history && window.history.pushState) {
        // HTML5사양 pushState사용이 가능한 경우에만 동작

    	if (location.pathname === "/main") {
    		reqUrl = "/page/main/top";
    	} else {
    		reqUrl = location.pathname;
    	}
        //setContentsOnly(location.pathname);
    	setContentsOnly(reqUrl);
    } else {
        // HTML5사양 미 지원의 경우는 hash사용
        //setContentsOnly(window.location.hash.substring(1));
    	if (window.location.hash.substring(1) === "/main" || window.location.hash.substring(1) === "") {
    		reqUrl = "/page/main/top";
    	} else {
    		reqUrl = window.location.hash.substring(1);
    	}
        setContentsOnly(reqUrl);
    }
}

/**
* Contents 정보 재표시 (Title은 변경하지 않음)
*/
function setContentsOnly(url) {
    $.ajax({
        url: url,
        type: "GET",
        dataType: "html",
        headers: {
            Accept: "text/html;"
        },
        beforeSend: function() {
            return true;
        },
        success: function(data) {
            $("section[id=contentArea]").fadeOut("slow", function() {
                $(this).html(data).fadeIn("fast", function() {
                    // 제어시 필요한 동작 기재
                });
            });
        },
        complete: function(data) {
            //kendo.ui.progress($("section[id=contentArea]"), false);
        }
    });
}

/**
* Left Menu를 이용하지 않고 현재 Page내에서 다른 페이지로 이동
*/
function moveContentsPage(url, param) {
    var paramUrl = url;
    $.ajax({
        url: url,
        type: "GET",
        dataType: "html",
        data: param,
        headers: {
            Accept: "text/html;"
        },
        beforeSend: function() {
            return true;
        },
        success: function(data) {
        	if (window.history && window.history.pushState) {
                // HTML5사양 pushState사용이 가능한 경우에만 동작
                var pushState = {};
                pushState["pageTitle"] = param.pageTitle;
                pushState["menuId"] = $(this).attr("id");
                history.pushState(pushState, null, url);

                $("section[id=contentArea]").fadeOut("slow", function() {
                    $(this).html(data).fadeIn("fast", function() {
                        // 제어시 필요한 동작 기재
                        // menu active setting
                        selectNexMenu(paramUrl);
                    });
                });

                return false;
            } else {
                // HTML5사양 미 지원의 경우는 hash사용
                location.hash = $(this).attr("data-url");
                selectNexMenu(paramUrl);
                $(window).trigger("hashchange");
            }
        },
        error: function (data, textStatus, jqXHR) {
            //console.log(data);
            console.log(textStatus);
        },
        complete: function(data) {
            //kendo.ui.progress($("section[id=contentArea]"), false);
        }
    });
}

/**
* 특정 메뉴를 수동으로 Active상태로 변경하는 함수
*/
function selectNexMenu(url) {
    var menuLinkObj = $(document).find("section[id=sidebar]").find("a[data-url=NCS_EVA_E01_00]");
    if (menuLinkObj != null) {
        // 이동할 화면이 메뉴상에 존재하는 경우
        // 기존 선택메뉴의 active style 삭제
        $("#menuList").find("li:not(.treeview)").removeClass("active");
        // 선택한 메뉴를 강제로 active
        menuLinkObj.parent().addClass("active");
    }
}

/**
 * Add Grid Header Tooltip
 * @param gridId
 */
function setGridTooltip(gridId) {
    var grid = $("#" + gridId).data("kendoGrid");
    grid.thead.kendoTooltip({
        filter: "th",
        content: function (e) {
            var target = e.target; // element for which the tooltip is shown
            var targetTxt = $(target).text();
            target = null;
            return targetTxt;
        }
    });
}

/**
 * Add Grid(TreeList) Header Tooltip
 * @param gridId
 */
function setTreeListTooltip(gridId) {
    var grid = $("#" + gridId).data("kendoTreeList");
    grid.thead.kendoTooltip({
        filter: "th",
        content: function (e) {
            var target = e.target; // element for which the tooltip is shown
            return $(target).text();
        }
    });
}

/**
 * search editable-cell class & tab control
 * @param e
 */
function onGridKeydown(e) {
    if (e.keyCode === kendo.keys.TAB) {
        var grid = $(this).closest("[data-role=grid]").data("kendoGrid");
        var current = grid.current();
        if (!current.hasClass("editable-cell")) {
            var nextCell;
            if (e.shiftKey) {
                nextCell = current.prevAll(".editable-cell");
                if (!nextCell[0]) {
                    //search the next row
                    var prevRow = current.parent().prev();
                    var nextCell = prevRow.children(".editable-cell:last");
                    grid.select(prevRow);
                } else {
                    grid.select(current.parent());
                }
            } else {
                nextCell = current.nextAll(".editable-cell");
                if (!nextCell[0]) {
                    //search the next row
                    var nextRow = current.parent().next();
                    var nextCell = nextRow.children(".editable-cell:first");
                    grid.select(nextRow);
                } else {
                    grid.select(current.parent());
                }
            }
            grid.current(nextCell);
            grid.editCell(nextCell[0]);
        }
    }
}

/**
 * DatePicker Initialize
 * @param id
 * @param type :: ym, ymd
 * @param readonlyFlag :: true / false
 */
function setDatePickerStart(id, type, readonlyFlag) {
    readonlyFlag = (readonlyFlag == null) ? true : readonlyFlag;

    if (type == "ym") {
        $("#" + id).datepicker({
            format:  "yyyy/mm",
            //dateFormat : "yy/mm",
            minViewMode: "months",
            todayHighlight: true
        }).on('changeDate',function(e){
            $(this).datepicker('hide');
        });
    } else {
        // base = ymd
        $("#" + id).datepicker({
            autoclose:true,
            format: 'yyyy/mm/dd',
            todayHighlight: true,
            todayBtn: "linked"
        });
    }
    $("#" + id).datepicker('setDate', 'today'); // set today
    $("#" + id).attr('readonly', readonlyFlag); // readonly
}

/**
 * Dropdown Initialize
 * @param name : drowdownlist component Id
 * @param optionlabel : All / Select / ---- ... etc
 * @param onChangeDropdownEvent : call back event
 * @param commonCodeGroup : data select key
 * @param searchOptions : set [search condition], [cascade Id]
 */
function initDropdown(name, optionlabel, onChangeDropdownEvent, commonCodeGroup, searchOptions) {
    var dataBoundHandler = null;
    if (searchOptions && searchOptions.dataBoundHandler) {
        dataBoundHandler = searchOptions.dataBoundHandler;
    } else {
        dataBoundHandler = function () {
            try {
                this.select(0);
            } catch (e) {}
        }
    }

    if (commonCodeGroup != null) {
        var remoteUrl = commonCodeGroup;

        $("#" + name).kendoDropDownList({
        	height:400,
            optionLabel: optionlabel,
            dataTextField: "cdNm",
            dataValueField: "cdVal",
            autoBind: false,
            cascadeFrom: (!searchOptions || !searchOptions.cascadeId) ? null : searchOptions.cascadeId,
            change: onChangeDropdownEvent,

            
            dataBound: dataBoundHandler,
            template: (!searchOptions || !searchOptions.template) ? null : searchOptions.template,
            filter: (!searchOptions || !searchOptions.filter) ? null : searchOptions.filter
        });

        // data search
        var successFunc = function(response) {
        	if (response != null) {
        		$("#" + name).data("kendoDropDownList").setDataSource(response);
                $("#" + name).data("kendoDropDownList").dataSource.read();

                if(typeof(window["dropdownCallback"]) != "undefined"){
                	window["dropdownCallback"](name);
                }
        	}
        	if(name == "searchTerm"){
        		$("#" + name).data("kendoDropDownList").select(response.length);
        	}

        }
        var params = {};
        if (searchOptions && searchOptions.paramMap) params = searchOptions.paramMap();
        dataSource_transport_json("listSearch", remoteUrl, params, successFunc);

        /*
        if (searchOptions == null || searchOptions.cascadeId == null) {
            $("#" + name).data("kendoDropDownList").dataSource.read();
        }
        */

    } else {
        //blank data
        $("#" + name).kendoDropDownList({
        	height:400,
            optionLabel: optionlabel,
            dataTextField: "cdNm",
            dataValueField: "cdVal",
            autoBind: false,
            template: (!searchOptions || !searchOptions.template) ? null : searchOptions.template,
            filter: (!searchOptions || !searchOptions.filter) ? null : searchOptions.filter,
            dataBound: dataBoundHandler,
            change: onChangeDropdownEvent
        });
    }
}

/**
 * Dropdown Initialize : 업무 코드 조회 시
 * @param name : drowdownlist component Id
 * @param optionlabel : All / Select / ---- ... etc
 * @param onChangeDropdownEvent : call back event
 * @param commonCodeGroup : data select key
 * @param searchOptions : set [search condition], [cascade Id]
 */
//예를 들어 아래와 같은 함수가 호출 되었다면
//("LICENSE_CD_TP", "<spring:message code='common.select' text=''/>", onChangeDropdownType, "/common/code/searchDropdownBizCode/1001"
/*
 * name = LICENSE_CD_TP
 * optionLabe = 선택
 * onChangeDropdownEvent = onChangeDropdownType
 * commonCodeGroup = /common/code/searchDropdownBizCode/1001
 * searchOptions = 전달안됨
 * */
//아래처럼 전달됐을 경우
//		 initBizDropdown("LICENSE_CDz", "test", onChangeDa, "/common/code/searchDropdownBizCode/1001");
function initBizDropdown(name, optionlabel, onChangeDropdownEvent, commonCodeGroup, searchOptions) {
	
	//searchOption 검사
    var dataBoundHandler = null;
    if (searchOptions && searchOptions.dataBoundHandler) {
        dataBoundHandler = searchOptions.dataBoundHandler;
    } else {
        dataBoundHandler = function () {
            try {
                this.select(0); //undifiend
            } catch (e) {}
        }
    }

    if (commonCodeGroup != null) {
        var remoteUrl = commonCodeGroup;
        
        //select 태그 내부의 option 태그에서 value 속성으로 CD를 표시하고, 텍스트 속성으로 CD_NM을 표시함
        //ex) <select><option value="CD속성">CD_NM속성</option></select>
        //select 태그의 chage 이벤트로 파라미터로 전달 받은 onChangeDropDownEvent를 지정함
        $("#" + name).kendoDropDownList({
        	height:400,
            optionLabel: optionlabel,
            dataTextField: "CD_NM",
            dataValueField: "CD",
            autoBind: false,
            cascadeFrom: (!searchOptions || !searchOptions.cascadeId) ? null : searchOptions.cascadeId,
            change: onChangeDropdownEvent,

            
            dataBound: dataBoundHandler,
            template: (!searchOptions || !searchOptions.template) ? null : searchOptions.template,
            filter: (!searchOptions || !searchOptions.filter) ? null : searchOptions.filter
        });

        // data search
        var successFunc = function(response) {
        	if (response != null) {
        		$("#" + name).data("kendoDropDownList").setDataSource(response);
                $("#" + name).data("kendoDropDownList").dataSource.read();

                if(typeof(window["dropdownCallback"]) != "undefined"){
                	window["dropdownCallback"](name);
                }
        	}
        	if(name == "searchTerm"){
        		$("#" + name).data("kendoDropDownList").select(response.length);
        	}

        }
        var params = {};
        if (searchOptions && searchOptions.paramMap) params = searchOptions.paramMap();
        
        //세번째 옵션은 전달되지 않음.
        dataSource_transport_json("listSearch", remoteUrl, params, successFunc);

        /*
        if (searchOptions == null || searchOptions.cascadeId == null) {
            $("#" + name).data("kendoDropDownList").dataSource.read();
        }
        */

    } else {
        //blank data
        $("#" + name).kendoDropDownList({
        	height:400,
            optionLabel: optionlabel,
            dataTextField: "CD_NM",
            dataValueField: "CD",
            autoBind: false,
            template: (!searchOptions || !searchOptions.template) ? null : searchOptions.template,
            filter: (!searchOptions || !searchOptions.filter) ? null : searchOptions.filter,
            dataBound: dataBoundHandler,
            change: onChangeDropdownEvent
        });
    }
}
/**
 * Dropdown Initialize
 * @param name : drowdownlist component Id
 * @param optionlabel : All / Select / ---- ... etc
 * @param onChangeDropdownEvent : call back event
 * @param commonCodeGroup : data select key
 * @param searchOptions : set [search condition], [cascade Id]
 */
function initDropdownOracle(name, optionlabel, onChangeDropdownEvent, commonCodeGroup, searchOptions) {
    var dataBoundHandler = null;
    if (searchOptions && searchOptions.dataBoundHandler) {
        dataBoundHandler = searchOptions.dataBoundHandler;
    } else {
        dataBoundHandler = function () {
            try {
                this.select(0);
            } catch (e) {}
        }
    }

    if (commonCodeGroup != null) {
        var remoteUrl = commonCodeGroup;

        $("#" + name).kendoDropDownList({
        	height:400,
            optionLabel: optionlabel,
            dataTextField: "CDNM",
            dataValueField: "CDVAL",
            autoBind: false,
            cascadeFrom: (!searchOptions || !searchOptions.cascadeId) ? null : searchOptions.cascadeId,
            change: onChangeDropdownEvent,

            dataBound: dataBoundHandler,
            template: (!searchOptions || !searchOptions.template) ? null : searchOptions.template,
            filter: (!searchOptions || !searchOptions.filter) ? null : searchOptions.filter
        });

        // data search
        var successFunc = function(response) {
        	$("#" + name).data("kendoDropDownList").setDataSource(response);
            $("#" + name).data("kendoDropDownList").dataSource.read();
        	if(name == "searchTerm"){
        		$("#" + name).data("kendoDropDownList").select(response.length);
        	}

        }
        var params = {};
        if (searchOptions && searchOptions.paramMap) params = searchOptions.paramMap();
        dataSource_transport_json("listSearch", remoteUrl, params, successFunc);
    } else {
        $("#" + name).kendoDropDownList({
        	height:400,
            optionLabel: optionlabel,
            dataTextField: "CDNM",
            dataValueField: "CDVAL",
            autoBind: false,
            template: (!searchOptions || !searchOptions.template) ? null : searchOptions.template,
            filter: (!searchOptions || !searchOptions.filter) ? null : searchOptions.filter,
            dataBound: dataBoundHandler,
            change: onChangeDropdownEvent
        });
    }
}

/**
 * Number Format change ( add comma)
 * @param x
 * @returns
 */
function numberWithCommas(x) {
    if (x == null || x == "") {
        return "";
    }

    var parts = x.toString().split(".");
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return parts.join(".");
}

/**
* 특정 node (tag)아래에 존재하는 selector에 해당하는 모든 태그의 입력값을 조회, map형식으로 반환하는 함수
* child node의 type이 select인 경우는 kendoDropDownList형식으로 변환하에 값을 취득. radio button의 경우는 선택된 radio의 value가 설정됨
*
* @param targetNodeObj : 검색대상이 되는 최상위 node. jquery object를 설정해야 함. (ex 화면 전체 대상이면 $(document) 등등. )
* @param selector : 검색조건. jquery에 사용되는 일반적인 selector를 지정함. (ex class a 조회시는  ".a", id조회시는 "#a" 등등)
* @return 입력값이 [name, value] 형식으로 저장된 map
*/
function getInputDataMap(targetNodeObj, selector) {
    var inputMap = {};
    targetNodeObj.find(selector).map(function (value) {
        if (this.nodeName && this.nodeName.toLowerCase() == "select") {
            inputMap[$(this).attr("name")] = $("#" + this.id).data("kendoDropDownList").value();
        } else if (this.nodeName && this.nodeName.toLowerCase() == "textarea") {
            inputMap[$(this).attr("name")] = $(this).val();
        } else {
            if ($(this).attr("type") == "radio") {
                inputMap[$(this).attr("name")] = $("input[name=" + $(this).attr("name") + "]:checked").val();
            } else {
                inputMap[$(this).attr("name")] = $(this).val();
            }
        }
    });

    return inputMap;
}

/**
* 특정 node (tag)아래에 존재하는 selector에 해당하는 모든 태그의 입력값을 설정하는 함수
* select 의 경우는 index 0번째를 강제로 설정함.
* radio button의 경우는 첫번째  라디오버튼을 강제로 설정함.
*
* @param targetNodeObj : 검색대상이 되는 최상위 node. jquery object를 설정해야 함. (ex 화면 전체 대상이면 $(document) 등등. )
* @param selector : 검색조건. jquery에 사용되는 일반적인 selector를 지정함. (ex class a 조회시는  ".a", id조회시는 "#a" 등등)
* @param setValue : 설정할 내용
*/
function setInputDataMap(targetNodeObj, selector, setValue) {
    targetNodeObj.find(selector).map(function (value) {
        if (this.nodeName && this.nodeName.toLowerCase() == "select") {
            $(this).data("kendoDropDownList").select(0); // selected first
        } else if (this.nodeName && this.nodeName.toLowerCase() == "textarea") {
            $(this).val(setValue);
        } else if (this.nodeName && this.nodeName.toLowerCase() == "span") {
            $(this).html(setValue);
        } else {
            if ($(this).attr("type") == "radio") {
                $("input[name=" + $(this).attr("name") + "]:eq(0)").prop("checked", true);
            } else {
                $(this).val(setValue);
            }
        }
    });
}

/**
* 특정 node (tag)아래에 존재하는 selector에 해당하는 모든 태그를 조회하여, setValueMap parameter값과 비교, name과 key가 일치하는 항목에 대해서 값을 설정하는 함수
* select 의 경우는 kendoDropDownList Object를 취득하여 value를 설정함
* radio button의 경우는 value가 setValueMap의 값과 일치하는 항목을 check함
*
* @param targetNodeObj : 검색대상이 되는 최상위 node. jquery object를 설정해야 함. (ex 화면 전체 대상이면 $(document) 등등. )
* @param selector : 검색조건. jquery에 사용되는 일반적인 selector를 지정함. (ex class a 조회시는  ".a", id조회시는 "#a" 등등)
* @param setValueMap : 설정할 내용이 등록되어있는 map object {key, value} 구조여야 함
*/
function setInputFieldValue(targetNodeObj, selector, setValueMap) {
    targetNodeObj.find(selector).map(function (value) {
        if (this.nodeName && this.nodeName.toLowerCase() == "select") {
            $(this).data("kendoDropDownList").value(setValueMap == null ? "" : setValueMap[$(this).attr("name")]);
        } else if (this.nodeName && this.nodeName.toLowerCase() == "textarea") {
            $(this).val(setValueMap == null ? "" : setValueMap[$(this).attr("name")]);
        } else if (this.nodeName && this.nodeName.toLowerCase() == "span") {
            $(this).html(setValueMap == null ? "" : setValueMap[$(this).attr("name")]);
        } else {
            if ($(this).attr("type") == "radio") {
            	targetNodeObj.find("input[name=" + $(this).attr("name") + "]").val([setValueMap == null ? "" : setValueMap[$(this).attr("name")]]);
            } else {
                $(this).val(setValueMap == null ? "" : setValueMap[$(this).attr("name")]);
            }
        }
    });
}

/**
* 문자열을 실행함수로 변환하는 처리
*/
function onChangeStrToFunc(obj){
    // editor string to function
    if(obj.editor != null && obj.editor != "undefined" && obj.editor != ""){
    	obj.editor = eval(obj.editor);
    }
    // template (blank to null)
    if (obj.template != null && obj.template != "undefined" && obj.template == "") {
        obj.template = null;
    }
    return obj;
}

/**
 * Grid Data Initialize (Async Transfer)
 * @param gridDivId : grid div tag Id
 * @param gridId : search grid property key
 * @param gridInitOptions : initialize options {height:500, editable:true/false, toolbarId:"sampleToolbar", change:function, dblClick:function }
 */
//그리드 생성기 
//createGrid("mainGrid", "규정등록현황", gridProperty);가 왔을 때
//gridProperty에는  height와 editable과 excelName등이 있음
function createGrid(gridDivId, gridId, gridInitOptions) {
    var paramMap = {};
    paramMap.gridId = gridId;
    
    //세번째는 SystemCodeController.java에 있다. 
    search_grid_template(gridDivId, "/common/grid/initGridData", paramMap, gridInitOptions);
}

/**
 * @param dst_id : grid div tag Id
 * @param dst_url :
 * @param dst_params
 * @param gridInitOptions
 *          - height        :   number
 *          - editable      :   boolean
 *          - filterable    :   true / false / {mode: "row"}
 *          - selectable    :   true / false / multiple
 *          - change        :   call back function or null
 *          - dblClick      :   call back function or null
 *          - toolbarId     :   toolbar Id or null
 *          - saveEvent     :   call back function or null
 *          - gridCols      :   call back function or null (save grid column info)
 *          - gridType      :   init grid type ('grid' or 'treelist'). default = 'grid'
 *          - dataSourceObj :   dataSource options
 *              .requestUrl         :   request url
 *              .requestParam       :   return of javascript map function
 *              .resultDataName     :   response data name
 *              .resultCountName    :   response count name
 */
function search_grid_template (dst_id, dst_url, dst_params, gridInitOptions) {
    $.ajax({
        type : 'POST',
        url : dst_url,
        dataType : 'json',
        data : dst_params
    }).done(function(data) {
    	
    	// Ajax 성공 시, 그 결과가 gridList라는 key에 값을 담아서 온다.
    	//gridList에는 어떤 경우 다음과 같은 파일이 List 형식으로 변형되어 저장된다.
    	//grid.board.content.json 
    	//grid.board.content.json 
    	
        // grid column set
        if (gridInitOptions.gridCols) {
            gridInitOptions.gridCols(data.gridList);
            console.log("gridCols = "+gridInitOptions.gridCols);
            console.log("gridCols = "+gridInitOptions.gridCols(data.gridList));
        }

        if (gridInitOptions.gridType && gridInitOptions.gridType == "treelist") {
            kendoTreeListInit(dst_id,
                    data.gridList,
                    gridInitOptions.height,
                    gridInitOptions.editable,
                    gridInitOptions.change,
                    gridInitOptions.dblClick,
                    gridInitOptions.toolbarId,
                    gridInitOptions.saveEvent,
                    gridInitOptions.excelName,
                    gridInitOptions.editEvent,
                    gridInitOptions.naviEvent,
                    gridInitOptions.filterable);

            if (gridInitOptions.callGridInit) {
                gridInitOptions.callGridInit();
            }
        } else {
            // kendo grid
            kendoGridInit(dst_id,
                    data.gridList,
                    gridInitOptions.height,
                    gridInitOptions.editable,
                    gridInitOptions.change,
                    gridInitOptions.dblClick,
                    gridInitOptions.toolbarId,
                    gridInitOptions.saveEvent,
                    gridInitOptions.excelName,
                    gridInitOptions.editEvent,
                    gridInitOptions.naviEvent,
                    gridInitOptions.filterable,
                    gridInitOptions.selectable,
                    gridInitOptions.pageable,
                    gridInitOptions.noRecordObj,
                    gridInitOptions.dataSourceObj);

            if (gridInitOptions.callGridInit) {
                gridInitOptions.callGridInit();
            }
        }
    }).fail(function(data, textStatus, jqXHR) {
        try {
            alert(textStatus);
        } catch (e) {
            // function call fail skip
            alert(e);
        }
    });
}

/**
 * kendoGridInit
 * : Initialize Kendo Grid
 *
 * @param   id              : grid id
 * @param   cos             : grid columns
 * @param   height          : grid height
 * @param   editable        : grid editing true/false
 * @param   clkEvent        : grid row selection event
 * @param   dblEvent        : grid row double click event
 * @param   toolbarId       : grid toolbar Id
 * @param   saveEvent       : grid save event
 * @param   excelName       : export excel name
 * @param   editEvent       : grid edit event
 * @param   naviEvent       : grid Navigation Event
 * @param   filterable      : grid filter (true / false / {mode: row}
 * @param   selectable      : grid select flag (true / false / multiple)
 * @param   pageable        : grid pagable flag (true / false)
 * @param   noRecordObj     : no records avaliable flag (true / false / template : [string] )
 * @param   dataSourceObj   : grid datasource object
 *
 * ex) var grid = kendoGridInit('grid', cols, 400, false, onChange, onDblClick, datasource);
 */
function kendoGridInit(id, cols, height, editable, clkEvent, dblEvent, toolbarId, saveEvent, excelName, editEvent, naviEvent, filterable, selectable, pageable, noRecordObj, dataSourceObj){

    var exportExcelName = "Kendo UI Grid Export.xlsx";
    if (excelName != null && excelName != "") exportExcelName = excelName + ".xlsx";

    for(var i=0 ; i<cols.length ; i++){
        cols[i] = onChangeStrToFunc(cols[i]);
    }
    var divId = "#" + id;
    var dataSource = new kendo.data.DataSource({
        schema: {
            model: {
              fields: cols
           }
        }
    });

    // dataSource가 지정된 경우는 해당 dataSource사용
    // 지정이 없는경우는 default생성값을 사용
    if (dataSourceObj != null) {
        dataSource = getGridDataSource(id, dataSourceObj.requestUrl, dataSourceObj.requestParam, dataSourceObj.resultDataName, dataSourceObj.resultCountName, cols);
    }

    $(divId).kendoGrid({
        height:height,
        dataSource: dataSource,
        excel: {
            allPages: true,
            fileName: exportExcelName,
            filterable: true
        },
        autoBind : false,
        scrollable: {
            virtual: true
        },
        sortable: true,
        resizable: true,
        reorderable: true,
        selectable: (selectable == null) ? true : selectable,
        pageable: (pageable == null) ? false : pageable,
        filterable: filterable,
        change: clkEvent,
        dataBound: dblEvent,
        groupable: false,
        columns: cols,
        editable: editable,
        save: saveEvent,
        navigatable: true,
        navigate: naviEvent,
        toolbar: toolbarId == null ? null : kendo.template($("#" + toolbarId).html()),
        edit: editEvent,
        noRecords: noRecordObj
    }).find("table").on("keyup", onGridKeydown);

    var grid = $(divId).data("kendoGrid");
    if (grid != null && grid.thead != null) {
	    grid.thead.kendoTooltip({
	        filter: "th",
	        content: function (e) {
	            var target = e.target; // element for which the tooltip is shown
	            return $(target).text().replace("Filter", "");
	        }
	    });
    }

    if (toolbarId != null) {
        $("#" + toolbarId + "_columnMenuButton").kendoColumnMenu({
            filterable: false,
            sortable: false,
            dataSource: grid.dataSource,
            columns: true,
            owner: grid
        });
    }

    if(typeof(window["kendoGridLoadEvent"]) != "undefined"){
    	window["kendoGridLoadEvent"]({gridId:divId});
    }

    return grid;
};

/**
 * kendoTreeListInit
 * : Initialize Kendo TreeList
 *
 * @param   id              : grid id
 * @param   cos             : grid columns
 * @param   height          : grid height
 * @param   editable        : grid editing true/false
 * @param   clkEvent        : grid row selection event
 * @param   dblEvent        : grid row double click event
 * @param   toolbarId       : grid toolbar Id
 * @param   saveEvent       : grid save event
 * @param   excelName       : export excel name
 * @param   editEvent       : grid edit event
 * @param   naviEvent       : grid Navigation Event
 * @param   filterable      : grid filter (true / false / {mode: row}
 *
 * ex) var grid = kendoTreeListInit('grid', cols, 400, false, onChange, onDblClick);
 */
function kendoTreeListInit(id, cols, height, editable, clkEvent, dblEvent, toolbarId, saveEvent, excelName, editEvent, naviEvent, filterable){

    var exportExcelName = "Kendo UI Grid Export.xlsx";
    if (excelName != null && excelName != "") exportExcelName = excelName + ".xlsx";

    for(var i=0 ; i<cols.length ; i++){
        cols[i] = onChangeStrToFunc(cols[i]);
    }
    var divId = "#" + id;
    var dataSource = new kendo.data.TreeListDataSource({
        schema: {
            model: {
              fields: cols
           }
        }
    });

    $(divId).kendoTreeList({
        height:height,
        dataSource: dataSource,
        excel: {
            allPages: true,
            fileName: exportExcelName,
            filterable: true
        },
        autoBind : false,
        scrollable: {
            virtual: true
        },
        sortable: true,
        resizable: true,
        reorderable: true,
        selectable: true,
        filterable: filterable,
        change: clkEvent,
        dataBound: dblEvent,
        groupable: false,
        columns: cols,
        editable: editable,
        save: saveEvent,
        navigatable: true,
        navigate: naviEvent,
        toolbar: toolbarId == null ? null : kendo.template($("#" + toolbarId).html()),
        edit: editEvent
    }).find("table").on("keyup", onGridKeydown);

    var grid = $(divId).data("kendoTreeList");
    grid.thead.kendoTooltip({
        filter: "th",
        content: function (e) {
            var target = e.target; // element for which the tooltip is shown
            return $(target).text();
        }
    });

    if (toolbarId != null) {
        $("#" + toolbarId + "_columnMenuButton").kendoColumnMenu({
            filterable: false,
            sortable: false,
            dataSource: grid.dataSource,
            columns: true,
            owner: grid
        });
    }
    return grid;
};

/**
 * Grid Clear
 *
 * @param gridId
 * @param columnFields
 */
function gridClear(gridId, columnFields) {
    var dataSource = new kendo.data.DataSource({
        data : null,
        total : 0,
        schema: {
            model: {
                fields: columnFields
            }
        }
    });
    var grid = $("#" + gridId).data("kendoGrid");
    grid.setDataSource(dataSource);
    grid.dataSource.fetch(function(e) {});
}

/**
 * set Auto Completed
 * @param objId : auto complete target object(tag) Id
 * @param targetId : process initialize id
 * @param options:
 *          - ignoreCase = ignoreCase Set (true / false). default = true
 *          - filter = filter type Set (startWith, contains)
 *          - dataTextField = view list name
 *          - minLength = minimum input length (search start length)
 *          - changeEvent = change event handler
 *          - selectEvent = select event handler
 *          - boundEvent = data Bound event handler
 */
function setAutoComplete(objId, targetId, options) {
    $("#"+objId).kendoAutoComplete({
        animation: {
            open: {
                effects: "zoom:in",
                duration: 300
            }
        },
        ignoreCase: options.ignoreCase,
        filter: options.filter,
        dataTextField: options.dataTextField,
        minLength: options.minLength,
        change: options.changeEvent,
        select: options.selectEvent,
        dataBound: options.boundEvent,
        dataSource: {
            schema: {
                total: "total",
                data: "data"
            },
            serverFiltering: true,
            transport: {
                read: {
                    type: "POST",
                    contentType: "application/json",
                    url: "/comm/autoComplete/read/" + targetId
                },
                parameterMap: function parameterMap(inputMap, type) {
                    var params = {};
                    params["fieldValue"] = inputMap.filter.filters[0]["value"];
                    params["operator"] = inputMap.filter.filters[0]["operator"];
                    params["fieldName"] = objId;
                    params["ignoreCase"] = inputMap.filter.filters[0]["ignoreCase"];
                    return JSON.stringify(params);
                }
            }
        },
        filtering: function(e) {
            if (!e.filter.value) {
                e.preventDefault();
            }
        }
    });
}

/**
* kendo window create
* @param targetNodeId
* @param options : kendo window options
*         .actions : array. ("Close" / "Refresh" / "Pin" / "Minimize" / "Maximize")
*         .draggable : dragg true / false
*         .modal : modal true / false
*         .width : width
*         .height : height
*         .title : window title
*         .content : remote page url
*         .activate : activate event handler function
*         .close : close event handler function
*/
function createKendoWindow(targetNodeId, options) {
    // Search modal start
    var kendoWindowObj =
    $("#" + targetNodeId).kendoWindow({
        actions: options.actions,
        draggable: options.draggable == null ? true : options.draggable,
        modal: options.modal == null ? false : options.modal,
        width: options.width,
        height: options.height,
        title: options.title,
        content: options.content,
        activate: options.activate,
        close: options.close
    }).data("kendoWindow");

    return kendoWindowObj;
}

/**
* kendo grid select check
* @return true - grid selected. / false - grid not selected
*/
function isGridSelected(gridId) {
    var grid = $("#" + gridId).data("kendoGrid");
    var selectedItem = grid.dataItem(grid.select());

    return (selectedItem != null);
}

/**
 * getGridDataSource
 * : getting Datasoruce for Kendo Grid
 *
 * @gridId  gridId          : kendo grid Id
 * @param   url             : url
 * @param   params          : data parameter get function
 *             .getParamMap (return javascript map function)
 * @param   gridDataNm      : Returning Grid Data Object Name
 * @param   gridTotalCntNm  : Returning Grid Data Total Rows Count
 * @param   columnlist      : Grid column list
 *
 * ex) var dataSource = getGridDataSource('gridId', '/resBody/AA000001', params, 'gridData', 'TOT_CNT', columnlist);
 */
function getGridDataSource(gridId, url, params, gridDataNm, gridTotalCntNm, columnlist){
    var dataSource = new kendo.data.DataSource({
        autoSync: false,
        transport: {
            read: {
                type : "POST",
                url : url,
                contentType: 'application/json; charset=utf-8',
                dataType : "json"
            },
            parameterMap: function(options, operation) {
                if (operation === "read") {
                    var requestMap = {};
                    if (params != null) {
                        requestMap = params();
                    }
                    return JSON.stringify(requestMap);
                }
            }
            , error: function(e) {
                alert(e);
            }
        },
        schema: {
            data: gridDataNm,
            total:gridTotalCntNm,
            model: {
                fields: columnlist
            }
        }
    });
    return dataSource;
};
/**
 * kendoGrid 데이터 CSV파일로 변환해 다운
 * @param gridId (String)					: kendoGrid Id
 * @param fileName (String)				: csv파일 다운로드시 파일명
 * @param carmelType (Boolean)		: 헤더표시용 true = 카멜타입,  false =  대소문자   기본은 false (대소문자)
 * @param ignore (Array)					: 변환작업중 dataSource에서 빠질 변수명
 *
 * ex) toCSV("masterGrid","CSV변환파일",false,["action","rowNo"]);
 */
function toCSV(gridId, fileName, carmelType, ignore) {
	var data = $('#'+gridId).data('kendoGrid').dataSource.data();
    var csv = '';
    if (!ignore) {
        ignore = [];
    }

    ignore = $.merge(ignore, ["_events", "idField", "_defaultId", "constructor", "init", "get",
        "_set", "wrap", "bind", "one", "first", "trigger",
        "unbind", "uid", "dirty", "id", "parent" ,"_handlers","length"]);

    if (data.length > 0) {
        for (var col in data[0]) {
            if (!data[0].hasOwnProperty(col) || $.inArray(col,ignore) >= 0 ) {
                continue;
            }

            if (carmelType) {
                col = col.split('_').join(' ').replace(/([A-Z])/g, ' $1');
            }

            col = col.replace(/"/g, '""');
            csv += '"' + col + '"';
            if (col != data[0].length - 1) {
                csv += ',';
            }
        }
        csv += '\n';
    }

    var len = data.length;
    for (var i = 0 ; i < len ; i++) {
        for (var col in data[i]) {
            if (!data[i].hasOwnProperty(col) || $.inArray(col,ignore) >= 0 ) {
                continue;
            }

            var value = data[i][col];
            if (value === null) {
                value = '';
            } else if (value instanceof Date) {
                value = moment(value).format('YYYY/MM/DD');
            } else {
                value = '' + value;
            }

            value = value.replace(/"/g, '""');
            csv += '"' + value + '"';
            if (col != data[i].length - 1) {
                csv += ',';
            }
        }
        csv += '\n';
    }

    var link = document.getElementById('downloadLink');
    link.setAttribute('href', 'data:text/csv;charset=utf-8,%EF%BB%BF' + encodeURI(csv));
    link.setAttribute('download', fileName);
    link.click();
};

/**
 * serializeObject
 * desc   : form의 데이터를 json 형태로 변환해 준다.
 * return : 성공시에는 객체(JSON)을 리턴한다. 실패시에는 null을 리턴한다.
 */
$.fn.serializeObject = function()
{
   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
       } else {
           o[this.name] = this.value || '';
       }
   });
   return o;
};

/**
 * titles : Message Title
 * messages : Display Message
 * type : Window Type
 *     - error : Error Message Display Window
 *     - success : Success Message Display Window
 *     - info : Information Message Display Window
 *     - warning : Warning Message Display Window
 */
function commonNoti(titles, messages, type) {
	notification.show({title: titles, message: messages}, type);
};

/**
 * Notification Information Load (Async)
 * @param dst_id
 * @param dst_url
 * @param dst_params
 */
function loadNotificationData (dst_id, dst_url, dst_params) {
    $.ajax({
        type : 'POST',
        url : dst_url,
        dataType : 'json',
        data : dst_params
    }).done(function(data) {
        noti_callback_success(dst_id, data);
    }).fail(function(data, textStatus, jqXHR) {
        noti_callback_error(dst_id, data);
    });
};

/**
웹접근성 table관련 script추가
.table_caption클래스 지정시, 관련 caption자동 입력
*/

$(document).ready(function(){
    $(".table_caption").each(function(){
        if (!$(this).find("caption").length){
            $(this).prepend("<caption class=\"caption\"></caption>");
        } // caption 태그가 존재 하지 않을 때 caption 태그 생성
        var txtSum = []; // 배열 생성
        $(this).find("th").each(function(){
            txt_th = $(this).text();
            txtSum.push(txt_th); // 얻은 텍스트값을 배열에 추가
        });
        var txtDiv = txtSum.join(', '); // 배열 값의 구분 값 설정 후 문자열로 변환
        var tit_tab = " " + $(".tab_menu a.on").text(); // 탭메뉴가 존재할 때 탭메뉴 텍스트값 추가
        var tit = $("h3.table_caption_title").text(); // 제목 텍스트값 추가
        $(this).find(".caption").text(tit + "의 " + txtDiv + " " + tit_tab + " 정보입력"); // caption 태그에 텍스트 값 대입
    });
});

/**
 * kendo editor 정보
 * image browser 사용시 사용자별 별도의 이미지 업로드 폴더 관리 기능이 필요한 경우 호출 시 이미 업로드 폴더를 지정할 수 있도록 함
 * 관리자 기능에서만 가능함
 * @param id
 * @param option
 * @param savedir
 * @returns
 */
function initKendoEditor(id, option, savedir) {
	if (option == "ALL_TOOL") {
		$("#" + id).kendoEditor({
			resizable: {
		        content: true
			},
			tools: ["bold","italic","underline","strikethrough","justifyLeft","justifyCenter","justifyRight","justifyFull","insertUnorderedList","insertOrderedList","indent","outdent","createLink","unlink","insertImage","createTable","addRowAbove","addRowBelow","addColumnLeft","addColumnRight","deleteRow","deleteColumn","viewHtml","cleanFormatting","foreColor","backColor","formatting","fontName","fontSize"]
		});
	} else if (option == "ADMIN_TOOL") {
		if (savedir == null || savedir == "") {
			$("#" + id).kendoEditor({
				resizable: {
			        content: true
				},
				tools: ["fontName","fontSize","bold","italic","underline","justifyLeft","justifyCenter","justifyRight","justifyFull","insertUnorderedList","insertOrderedList","indent","outdent","createLink","unlink","insertImage","createTable","addRowAbove","addRowBelow","addColumnLeft","addColumnRight","deleteRow","deleteColumn","viewHtml"],
				imageBrowser: {
		               messages: {
		                dropFilesHere: "Drop files here"
		               },
		               transport: {
		                    read: "/imageBrowser/read",
		                    destroy: {
		                        url: "/imageBrowser/destroy",
		                        type: "POST"
		                    },
		                    create: {
		                        url: "/imageBrowser/create",
		                        type: "POST"
		                    },
		                    thumbnailUrl: "/imageBrowser/thumbnail",
		                    uploadUrl: "/imageBrowser/upload",
		                    imageUrl: function (e) {
		                        return "/resources/userupload/images/" + e;
		                    }
		               }
				}
			});
		} else {
			$("#" + id).kendoEditor({
				resizable: {
			        content: true
				},
				tools: ["fontName","fontSize","bold","italic","underline","justifyLeft","justifyCenter","justifyRight","justifyFull","insertUnorderedList","insertOrderedList","indent","outdent","createLink","unlink","insertImage","createTable","addRowAbove","addRowBelow","addColumnLeft","addColumnRight","deleteRow","deleteColumn","viewHtml"],
				imageBrowser: {
		               messages: {
		                dropFilesHere: "Drop files here"
		               },
		               transport: {
		                    read: "/imageBrowser/read",
		                    destroy: {
		                        url: "/imageBrowser/destroy",
		                        type: "POST"
		                    },
		                    create: {
		                        url: "/imageBrowser/create",
		                        type: "POST"
		                    },
		                    thumbnailUrl: "/imageBrowser/thumbnail",
		                    uploadUrl: "/imageBrowser/upload",
		                    imageUrl: function (e) {
		                        return "/resources/" + e;
		                    }
		               },
		               path: savedir
				}
			});
		}
	} else {
		$("#" + id).kendoEditor({ resizable: {
            content: true,
            toolbar: true
        }});
	}
}

/**
 * 입력된 값의 최소값 확인. 최소값 이하인 경우 최소값으로 변경
 * @param id
 * @param minVal
 * @returns
 */
function checkMinValue(id, minVal) {
	var inVal = $("#" + id).val();
	if (inVal != "") {
		if (parseFloat(inVal) < minVal) {
			$("#" + id).val(minVal);
		}
	}
}

/**
 * Popup 화면을 Modal로 호출하는 경우
 * @param obj
 * @returns
 */
function callReplyDeletePopup(obj){
	var url = "";
	var popupType = "modal";
	var dulYn = true;
	var controllerId = "";
	
	if(obj.popId == "REPLY_DELETE") {
		console.log("READ....");
		popupType = "popup";
		url = "/popup/deleteReply";				
	}


	var modalId = "modalDiv";
	var modalHtml = "<div class='modal fade' id='" + modalId + "' tabindex='-1'>";
		modalHtml += "<div class='modal-dialog' style='width:50%'>";
		modalHtml += "<div class='modal-content'>";
		modalHtml += "</div></div></div>";

	$("body").append(modalHtml);
	$("body").append("<input type='hidden' id='popParmaID'>");
	$("body").append("<input type='hidden' id='popParmaName'>");
	$("body").append("<input type='hidden' id='popParmaeEtc'>");
	$("#"+obj.btnId).attr("callBackFunc",obj.callbackFunc);

	$("#popParmaeEtc").val(obj.eventEtc);

	$("#"+obj.btnId).on("click",function(){
		popupEvent(popupType,modalId, url, 200, 200);
		callbackBtnId = $(this).attr("id");
		callBackFunc = $(this).attr("callBackFunc");
	});
}


function callPasswordChangePopup(obj){
	var url = "";
	var popupType = "modal";
	var dulYn = true;
	var controllerId = "";

	url = "/popup/changePassword";		//교수
	controllerId = "/popup/changePassword";


	var modalId = "modalDiv";
	var modalHtml = "<div class='modal fade' id='" + modalId + "' tabindex='-1'>";
		modalHtml += "<div class='modal-dialog' style='width:50%'>";
		modalHtml += "<div class='modal-content'>";
		modalHtml += "</div></div></div>";

	$("body").append(modalHtml);
	$("body").append("<input type='hidden' id='popParmaID'>");
	$("body").append("<input type='hidden' id='popParmaName'>");
	$("body").append("<input type='hidden' id='popParmaeEtc'>");
	$("#"+obj.btnId).attr("callBackFunc",obj.callbackFunc);

	$("#popParmaeEtc").val(obj.eventEtc);

	$("#"+obj.btnId).on("click",function(){
		popupEvent(popupType,modalId, url, 200, 200);
		callbackBtnId = $(this).attr("id");
		callBackFunc = $(this).attr("callBackFunc");
	});
}


function popupEvent(popupType,modalId, url, width, height){
	if(popupType == "modal"){
		$("#"+modalId).removeData("bs.modal");
		$("#"+modalId).modal({
	        remote: url,
	        keyboard: false,
	        backdrop: "static"
	    });
	} else {
		window.open(url,"pop","width="+width+",height="+height+", scrollbars=yes, resizable=yes");
	}
}

function setInputFieldValueExceptionKendo(targetNodeObj, selector, setValueMap) {
    targetNodeObj.find(selector).map(function (value) {
        if (this.nodeName && this.nodeName.toLowerCase() == "select") {
            $(this).val(setValueMap[$(this).attr("name")]);
            if($(this).css("display") == 'none'){
            	$(this).data("kendoDropDownList").value(setValueMap == null ? "" : setValueMap[$(this).attr("name")]);
            }
        } else if (this.nodeName && this.nodeName.toLowerCase() == "textarea") {
            $(this).val(setValueMap[$(this).attr("name")]);
        } else if (this.nodeName && this.nodeName.toLowerCase() == "span") {
            $(this).html(setValueMap[$(this).attr("name")]);
        } else {
            if ($(this).attr("type") == "radio") {
            	$("input:radio[name='" + $(this).attr("name") + "'][value='" + setValueMap[$(this).attr("name")] + "']").prop("checked",true);
            } else if($(this).attr("type") == "checkbox"){
            	$(this).val() == setValueMap[$(this).attr("name")] ? $(this).prop("checked",true) : $(this).prop("checked",false)
            } else {
                $(this).val(setValueMap[$(this).attr("name")]);
            }
        }
    });
}


function setInputDataMapExceptionKendo(targetNodeObj, selector, setValue) {
    targetNodeObj.find(selector).map(function (value) {
        if (this.nodeName && this.nodeName.toLowerCase() == "select") {
            $(this).val(0); // selected first
        } else if (this.nodeName && this.nodeName.toLowerCase() == "textarea") {
            $(this).val(setValue);
        } else if (this.nodeName && this.nodeName.toLowerCase() == "span") {
            $(this).html(setValue);
        } else {
            if ($(this).attr("type") == "radio") {
                $("input[name=" + $(this).attr("name") + "]:eq(0)").prop("checked", true);
            } else {
                $(this).val(setValue);
            }
        }
    });
}

/**
 * 숫자타입에서 사용. 금액에 천단위 콤마 적용
 */
Number.prototype.format = function(){
	if(this==0) return 0;

	var reg = /(^[+-]?\d+)(\d{3})/;
	var n = (this + '');

	while (reg.test(n)) n = n.replace(reg, '$1' + ',' + '$2');

	return n;
}
/**
 * 문자열에서 적용
 */
String.prototype.format = function(){
	var num = parseFloat(this);
	if( isNaN(num) ) return "0";

	return num.format();
}

/**
 * 입력 받은 항목의 금액(숫자)를 천단위 콤마를 추가하도록 한다.
 * @returns
 */
function setMoneyFormat() {
	jQuery('.format-money').text(function() {
		jQuery(this).text(
			jQuery(this).text().format()
		);
	});
}

/**
 * 주소팝업조
 * @returns
 */
//회원가입 등 주소조회에 쓰임
function callAddressPopup(obj){
	var url = "";
	var popupType = "modal";
	var dulYn = true;
	var controllerId = "";
	if(obj.popId == "JUSO") {
		popupType = "popup";
		url = "/popup/jusoPopup";				//주소
	}

	var modalId = "modalDiv";""
	var modalHtml = "<div class='modal fade' id='" + modalId + "' tabindex='-1'>";
		modalHtml += "<div class='modal-dialog' style='width:50%'>";
		modalHtml += "<div class='modal-content'>";
		modalHtml += "</div></div></div>";
	
	//body에 modal창 생성
	$("body").append(modalHtml);
	$("body").append("<input type='hidden' id='popParmaID'>");
	$("body").append("<input type='hidden' id='popParmaName'>");
	$("body").append("<input type='hidden' id='popParmaeEtc'>");
	
	//obj에서 callbackFunc를 가져와서 세팅함
	$("#"+obj.btnId).attr("callBackFunc",obj.callbackFunc);
	
	// 팝업별 기타 처리 코드
	$("#popParmaeEtc").val(obj.eventEtc);

	//obj의 eventCode가 공백이 아니면서 obj의 eventCode 타입이 undefinde가 아니라면
	if(obj.eventCode != "" && typeof(obj.eventCode)!="undefined")
	{
		console.log("obj.eventCode가 null 아님 ");
		console.log("obj.eventCode 타입이 undefined가 아님");		
		$("#"+obj.eventCode).on("keydown",function(key){
			$("#popParmaID").val($(this).val());
    		callbackBtnId = $("#"+obj.btnId).attr("id");
    		callBackFunc = $("#"+obj.btnId).attr("callBackFunc");
	        if(key.keyCode == 13){
	        	popopAutoSearch(obj.popId, controllerId, "CODE", $(this).val(), popupType, modalId, url, obj.eventCode, obj.evnetName);
	        }
		});
	}

	if(obj.evnetName != "" && typeof(obj.evnetName)!="undefined")
	{
		console.log("obj.eventCode가 공백 아님 ");
		console.log("obj.eventCode 타입이 undefined가 아님");				
		$("#"+obj.evnetName).on("keydown",function(key){
			$("#popParmaName").val($(this).val());
    		callbackBtnId = $("#"+obj.btnId).attr("id");
    		callBackFunc = $("#"+obj.btnId).attr("callBackFunc");
	        if(key.keyCode == 13){
	        	popopAutoSearch(obj.popId, controllerId, "NAME", $(this).val(), popupType, modalId, url, obj.eventCode, obj.evnetName);
	        }
		});
	}

	// 결과값 자동셋팅
	$("#"+obj.btnId).attr("autoSet","N");
	$("#"+obj.btnId).attr("popId",obj.popId);

	if(typeof(obj.autoSet)!="undefined" && obj.autoSet == "Y"){
		console.log("obj.autoSet 타입이 undefined 아님 ");
		console.log("obj.autoSet이 Y다 ");

		$("#"+obj.btnId).attr("autoSet","Y");
	}

	$("#"+obj.btnId).on("click",function(){
		console.log("innc.common.js에서 "+obj.btnId+"가 클릭 되었습니다 ★");
		
		
		if(obj.eventCode != "" && typeof(obj.eventCode)!="undefined")
		{
			console.log("Error 1");
			$("#popParmaID").val($("#"+obj.eventCode).val()); 
		}
		if(obj.evnetName != "" && typeof(obj.evnetName)!="undefined"){ 
			console.log("Error 2");
			$("#popParmaName").val($("#"+obj.evnetName).val());
		}
		popupEvent(popupType,modalId, url, 570, 420);
		callbackBtnId = $(this).attr("id");
		callBackFunc = $(this).attr("callBackFunc");
		console.log("Error 3");
	});
}
