<%--
  Created by IntelliJ IDEA.
  User: Feng
  Date: 2016/10/17
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="/pages/importcss.jsp"/>
<c:import url="/pages/importjs.jsp"/>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox-content table-responsive">
                <div style="border-bottom:solid 1px lightgray; margin-bottom: 4px;">
                    <div class="row">
                        <div class="col-sm-12 form-inline">
                            <label class="control-label">部门:</label>
                            <select class="form-control" id="selectDepart" onchange="departChangeEvent(event,1);"
                                    style="width: 120px;">
                                <option value="0">全部</option>
                                <c:forEach items="${departList}" var="depart">
                                    <option value="${depart.id}">${depart.departName}</option>
                                </c:forEach>
                            </select>
                            <label class="control-label">分组:</label>
                            <select class="form-control" id="selectGroup" style="width: 120px;">
                                <option value="0">全部</option>
                            </select>
                            <button type="button" onclick="funRefresh();" class="btn btn-primary ">查询</button>
                            <button type="button" onclick="funClickAddRow();" class="btn btn-primary ">
                                添加
                            </button>
                        </div>
                    </div>

                </div>
                <table class="table table-hover table-bordered dataTables-example" id="tableUsers"
                       style="font-size: 14px;">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Email</th>
                        <th>名称</th>
                        <th>手机</th>
                        <th>部门</th>
                        <th>分组</th>
                        <th>级别</th>
                        <th>状态</th>
                        <th>认证</th>
                        <th>创建时间</th>
                        <th>更新时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>

    </div>

    <div class="modal fade modal-default" id="formEditMember">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="formEditTitle">用户编辑</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <input id="editMemberId" type="hidden">
                            <label for="editMemberEmail" class="col-sm-3 control-label">Email</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editMemberEmail">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editMemberName" class="col-sm-3 control-label">用户名称</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editMemberName">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="editMemberMobile" class="col-sm-3 control-label">手机</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editMemberMobile">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editMemberDepart" class="col-sm-3 control-label">部门</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="editMemberDepart"
                                        onchange="departChangeEvent(event,2);">
                                    <option value="0">全部</option>
                                    <c:forEach items="${departList}" var="depart">
                                        <option value="${depart.id}">${depart.departName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editMemberGroup" class="col-sm-3 control-label">分组</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="editMemberGroup" style="width: 120px;">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editMemberLevel" class="col-sm-3 control-label">级别</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="editMemberLevel">
                                    <option value="0">员工</option>
                                    <option value="1">访客</option>
                                    <option value="2">VIP</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editMemberStatus" class="col-sm-3 control-label">状态</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="editMemberStatus">
                                    <option value="0">刚创建</option>
                                    <option value="1">正常使用</option>
                                    <option value="2">不可用</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editMemberIdentity" class="col-sm-3 control-label">认证</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="editMemberIdentity">
                                    <option value="0">未认证</option>
                                    <option value="1">手机认证</option>
                                    <option value="2">邮箱认证</option>
                                    <option value="3">微信认证</option>
                                    <option value="4">企业认证</option>
                                </select>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                    <button type="button" id="publicBtn" class="btn   btn-primary" onclick="editSaveMemberInfo()">确定
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>
</div>


<script src="/javascript/system/member.js"></script>
