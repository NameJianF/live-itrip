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
<link href="/css/plugins/summernote/summernote.css" rel="stylesheet">
<link href="/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">

<c:import url="/pages/importjs.jsp"/>

<div class="wrapper wrapper-content animated fadeInRight ecommerce">
    <div class="row">
        <div class="col-lg-12">
            <div class="ibox-title">
                <div class="ibox-tools">
                    <div class="btn-group">
                        <input id="queryContent" type="text" class="form-control" placeholder="关键字"
                               style="width: 200px;">
                    </div>
                    <div class="btn-group" style="margin-left: -4px;">
                        <a id="btnSearch" onclick="funRefresh();" class="btn btn-primary"><span
                                class="fa fa-search"></span> 查询
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
                <table class="table table-hover table-bordered dataTables-example table-responsive"
                       id="tableStaticInfos"
                       style="font-size: 12px;">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>类型</th>
                        <th>标题</th>
                        <th>内容</th>
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

<div class="modal fade modal-default" id="formEditStaticInfo">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="formEditTitle">信息编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <input id="editStaticInfoId" type="hidden">
                        <label for="editStaticInfoType" class="col-sm-2 control-label">类型</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editStaticInfoType">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editStaticInfoTitle" class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editStaticInfoTitle">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="editStaticInfoContent" class="col-sm-2 control-label">内容</label>
                        <div class="col-sm-10">
                            <div class="summernote" id="editStaticInfoContent">

                            </div>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                <button type="button" id="publicBtn" class="btn   btn-primary" onclick="editSaveStaticInfo()">确定
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!-- SUMMERNOTE -->
<script src="/js/plugins/summernote/summernote.min.js"></script>
<script src="/javascript/view/staticinfo.js"></script>
