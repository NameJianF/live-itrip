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
                            <select class="form-control" id="selectDepart" onchange="departChangeEvent(event);">
                                <option value="0">全部</option>
                                <c:forEach items="${departList}" var="depart">
                                    <option value="${depart.id}">${depart.departName}</option>
                                </c:forEach>
                            </select>
                            <label class="control-label">分组:</label>
                            <select class="form-control" id="selectGroup">
                                <option value="0">全部</option>
                                <%--<c:forEach items="${groupList}" var="group">--%>
                                <%--<option value="${group.id}">${group.groupName}</option>--%>
                                <%--</c:forEach>--%>
                            </select>
                            <button type="button" onclick="funRefresh();" class="btn btn-primary ">查询</button>
                            <button type="button" onclick="funClickAddRow();" class="btn btn-primary ">
                                添加
                            </button>
                        </div>
                    </div>

                </div>
                <table class="table table-hover table-bordered dataTables-example" id="tableMember"
                       style="font-size: 14px;">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>模块名称</th>
                        <th>父模块</th>
                        <th>模块地址</th>
                        <th>排序</th>
                        <th>描述</th>
                        <th>是否删除</th>
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

    <div class="modal fade modal-default" id="formEditModule">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="formEditTitle">模块编辑</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <input id="editModuleId" type="hidden">
                            <label for="editModuleName" class="col-sm-3 control-label">模块名称</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editModuleName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editModuleParent" class="col-sm-3 control-label">父模块</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editModuleParent">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editModuleUrl" class="col-sm-3 control-label">模块地址</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editModuleUrl">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editModuleOrder" class="col-sm-3 control-label">排序</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editModuleOrder">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editModuleDiscription" class="col-sm-3 control-label">描述</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editModuleDiscription">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editModuleDelete" class="col-sm-3 control-label">是否删除</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="editModuleDelete">
                                    <option value="1">删除</option>
                                    <option value="0">未删除</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                    <button type="button" id="publicBtn" class="btn   btn-primary" onclick="editSaveModuleInfo()">确定
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>
</div>


<script src="/javascript/system/member.js"></script>
