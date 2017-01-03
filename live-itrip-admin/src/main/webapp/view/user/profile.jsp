<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="row">
    <div class="col-lg-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>个人信息</h5>
            </div>
            <div class="ibox-content">
                <div class="form-horizontal">
                    <input id="userId" value="${user.id}" type="hidden">
                    <div class="form-group"><label class="col-sm-2 control-label"></label>
                        <div class="col-sm-6 profile-image">
                            <img src="/img/a4.jpg" class="img-circle circle-border m-b-md" alt="profile">
                        </div>
                    </div>
                    <div class="form-group"><label class="col-sm-2 control-label">账号</label>
                        <div class="col-sm-6"><input id="userName" type="text" value="${user.userName}"
                                                     class="form-control"></div>
                    </div>
                    <div class="form-group"><label class="col-lg-2 control-label">Email</label>
                        <div class="col-lg-10"><p class="form-control-static">${user.email}</p></div>
                    </div>
                    <div class="form-group"><label class="col-sm-2 control-label">姓名</label>
                        <div class="col-sm-6"><input id="realName" type="text" value="${userExpand.realName}"
                                                     class="form-control">
                        </div>
                    </div>
                    <div class="form-group"><label class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-4"><input type="password" value="password" readonly class="form-control"
                                                     name="password">
                        </div>
                        <button class="btn btn-primary" onclick="updatePassword();">修改</button>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别<br>
                        </label>
                        <div class="col-sm-6">
                            <label>
                                <input type="radio" value="1" id="optionsRadios1"
                                <c:if test="${userExpand.sex eq '1'}"> checked</c:if>
                                       name="optionsRadios"> 男
                            </label>
                            <label style="margin-left: 20px;">
                                <input type="radio" value="0" id="optionsRadios2" name="optionsRadios"
                                <c:if test="${userExpand.sex eq '0'}"> checked</c:if>>
                                女
                            </label>
                            <label style="margin-left: 20px;">
                                <input type="radio" value="-1"
                                <c:if test="${userExpand.sex eq '-1'}"> checked</c:if>
                                       id="optionsRadios3" name="optionsRadios">
                                保密
                            </label>
                        </div>
                    </div>
                    <div class="form-group"><label class="col-sm-2 control-label">手机</label>
                        <div class="col-sm-6"><input id="mobile" type="text" placeholder="手机" value="${user.mobile}"
                                                     class="form-control"></div>
                    </div>
                    <%--<div class="form-group"><label class="col-sm-2 control-label">地址</label>--%>
                    <%--<div class="col-sm-6"><input type="text" placeholder="地址" class="form-control"></div>--%>
                    <%--</div>--%>

                    <div class="hr-line-dashed"></div>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-2">
                            <button class="btn btn-primary" onclick="saveProfileDatas();">保存</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade modal-default" id="formEditPassword">
    <div class="modal-dialog" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="formEditTitle">修改密码</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label for="editOldPwd" class="col-sm-3 control-label">原始密码</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="editOldPwd">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editNewPwd" class="col-sm-3 control-label">新密码</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="editNewPwd">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editNewPwd2" class="col-sm-3 control-label">再次输入</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="editNewPwd2">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="editSaveNewPassword()">保存
                </button>
            </div>
        </div>
    </div>
</div>