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
                <div method="get" class="form-horizontal">
                    <div class="form-group"><label class="col-sm-2 control-label"></label>
                        <div class="col-sm-6 profile-image">
                            <img src="/img/a4.jpg" class="img-circle circle-border m-b-md" alt="profile">
                        </div>
                    </div>
                    <div class="form-group"><label class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-6"><input type="text" value="${user.userName}" class="form-control"></div>
                    </div>
                    <div class="form-group"><label class="col-lg-2 control-label">Email</label>
                        <div class="col-lg-10"><p class="form-control-static">${user.email}</p></div>
                    </div>
                    <div class="form-group"><label class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-6"><input type="password" value="password" readonly class="form-control"
                                                     name="password">
                        </div>
                        <button class="btn btn-primary">修改</button>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别<br>
                        </label>
                        <div class="col-sm-6">
                            <label> <input type="radio" value="option1" id="optionsRadios1"
                                           name="optionsRadios"> 男 </label>
                            <label style="margin-left: 20px;"> <input type="radio" value="option2"
                                                                      id="optionsRadios2" name="optionsRadios">
                                女 </label>
                            <label style="margin-left: 20px;"> <input type="radio" value="option2" checked=""
                                                                      id="optionsRadios3" name="optionsRadios">
                                保密 </label>
                        </div>
                    </div>
                    <div class="form-group"><label class="col-sm-2 control-label">手机</label>
                        <div class="col-sm-6"><input type="text" placeholder="手机" value="${user.mobile}"
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