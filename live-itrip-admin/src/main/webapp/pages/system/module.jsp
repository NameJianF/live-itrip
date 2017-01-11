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
            <div class="ibox-title">
                <div class="ibox-tools">
                    <div class="row">
                        <div class="col-sm-8 form-inline" style="text-align: left;">
                            <label class="control-label">模块名称:</label>
                            <input type="text" class="form-control" id="moduleName">
                            <button type="button" onclick="funRefresh();" class="btn btn-primary ">查询</button>
                        </div>
                        <div class="col-sm-4">
                            <div class="btn-group">
                                <a onclick="funRefresh();" class="btn btn-primary ">
                                    <span class="fa fa-refresh"></span> 刷新
                                </a>
                            </div>
                            <div class="btn-group">
                                <a class="btn btn-primary dropdown-text" onclick="funClickAddRow()">
                                    <i class="fa fa-plus"></i> 新增
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ibox-content table-responsive">
                <table class="table table-hover table-bordered dataTables-example" id="tableModules"
                       style="font-size: 12px;">
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

<script src="/javascript/system/module.js"></script>
