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
            <div class="ibox-content table-responsive">
                <table class="table table-hover table-bordered dataTables-example" id="tableApikey"
                       style="font-size: 12px;">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>ApiKey</th>
                        <th>ClientName</th>
                        <th>SecretKey</th>
                        <th>Source</th>
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


<div class="modal fade modal-default" id="formEditApikey">
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
                        <input id="editApikeyId" type="hidden">
                        <label for="editApikey" class="col-sm-3 control-label">ApiKey</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="editApikey">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editClientName" class="col-sm-3 control-label">ClientName</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="editClientName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editSecretKey" class="col-sm-3 control-label">SecretKey</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="editSecretKey">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editSource" class="col-sm-3 control-label">Source</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="editSource">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editDiscription" class="col-sm-3 control-label">描述</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="editDiscription">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editDelete" class="col-sm-3 control-label">是否删除</label>
                        <div class="col-sm-5">
                            <select class="form-control" id="editDelete">
                                <option value="1">删除</option>
                                <option value="0">未删除</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                <button type="button" id="publicBtn" class="btn   btn-primary" onclick="editSaveApikeyInfo()">确定
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<script src="/javascript/system/apikey.js"></script>
