<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div class="row" id="aisPopupModal">
<%-- Body Area Start --%>
    <div class="modal-header">
        <span><label></label></span>
        <button type="button" class="close" data-dismiss="modal" id="close">
            <i class="fa fa-remove"></i>
        </button>
    </div>
    
    <div class="modal-body" style="padding-top:10px">
        <!-- Detail Area -->
        <div class="box box-solid" >
            <!-- 검색조건 Header & Button -->
            <div class="box-header with-border">
                <i class="fa fa-columns"></i>
                <h3 class="box-title"><spring:message code="user.info.management" text=""/></h3>
                <div class="pull-right">
                    <button class="btn bg-navy btn-flat" id="new"><i class="fa fa-fw fa-sticky-note" aria-hidden="true"></i> <spring:message code="button.clear" text="" /></button>
                    <button class="btn bg-navy btn-flat" id="save"><i class="fa fa-fw fa-save" aria-hidden="true"></i> <spring:message code="button.save" text="" /></button>
                </div>
            </div>
            <!-- 상세 표시 영역 -->
            <div class="box-body" style="height:150px" id="inputFieldArea">
                <input type="hidden" id="prevPasswd" value="${userSession.userInfo.userPasswd}" data-role="inputField" />
                <table border="0" cellpadding="0" cellspacing="0" class="table-grid  table-detail">
                    <colgroup>
                        <col style="width:20%">
                        <col style="width:80%">
                    </colgroup>
                    <tr>
                        <th>* 신규비밀번호</th>
                        <td>
                            <div class="input-group" style="width:100%">
                                <input type="password" name="userPw" id="userPw" class="form-control" data-role="inputField" style="width:70%" data-type="required" placeholder="신규비밀번호"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>* 비밀번호확인</th>
                        <td>
                            <div class="input-group" style="width:100%">
                                <input type="password" name="confirmUserPw" id="confirmUserPw" class="form-control" data-role="inputField" style="width:70%" data-type="required" placeholder="비밀번호확인"/>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <!-- End Detail Area -->
    </div>      
<%-- Body Area End --%>
</div>
<%-- end row block --%>

<script type="text/javascript" >
    $(document).ready(function() {
    	$(document).find(".btn").on("click", function(){
            var btnId = $(this).attr("id");

            if (btnId == "new") {
                codeInsertModeAfterAction();
            } else if (btnId == "save") {
                saveUser();
            } 
        });
    });
    
    /**
     * 사용자 정보 저장
     */
    function saveUser() {
        var requiredCheck = false;
        $("[data-type=required]").each(function (i,e){
            var val1 = $(this).val().replace(/^\s+/, "").replace(/\s+$/, "");
            if(val1 == ""){
                $(this).css("border","2px solid red");
                requiredCheck = true;
            }else{
                $(this).css("border","");
            }
        });
        
        if(requiredCheck){
            commonNoti("Warning", "<spring:message code='common.code.msg.0000006' />", "warning");
            return;
        }

        if ($("#userPw").val() != $("#confirmUserPw").val()) {
            commonNoti("Warning", "입력된 비밀번호가 상이합니다. 비밀번호를 다시 입력하세요. ", "warning");
            $("#userPw").val("");
            $("#confirmUserPw").val("");
            return;
        }
        
        if (!confirm("<spring:message code='common.code.msg.0000002' />")) return;

        var params = getInputDataMap($("#inputFieldArea"), "[data-role=inputField]");
        
        var successFunc = function(data) {
            if (data != null && data.rtCount == 1) {
                commonNoti("Success", "<spring:message code='common.code.msg.0000004' />", "success");
                codeInsertModeAfterAction();
                //$("#aisPopupModal").modal('hide');
            } else if(data != null && data.resultCount == -1){
                commonNoti("Error", "<spring:message code='common.code.msg.0000008' />", "error");
            } else {
                commonNoti("Error", "<spring:message code='common.code.msg.0000005' />", "error");
            }
        };

        var failFunc = function (data, status, xhr) {
            toastr.error(status, {timeOut:3000});
        }
        dataSource_transport("saveUserInfo", "/usrMgt/SAVE_USER_PASSWORD", params, successFunc, failFunc);
    }
    /**
     * input field initialize
     */
    function codeInsertModeAfterAction() {
    	$("#userPw").val("");
        $("#confirmUserPw").val("");
    }
</script>  
