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
            <div class="ibox-content table-responsive">
                <div style="border-bottom:solid 1px lightgray; margin-bottom: 4px;">
                    <div class="row">
                        <div class="col-sm-12 form-inline">
                            <label class="control-label" style="margin-right: 10px;">标题</label>
                            <input type="text" class="form-control" id="queryContent" placeholder="关键字">
                            <button type="button" onclick="funRefresh();" class="btn btn-primary ">查询</button>
                            <button type="button" onclick="funClickAddRow();" class="btn btn-primary ">
                                添加
                            </button>
                        </div>
                    </div>

                </div>
                <table class="table table-hover table-bordered dataTables-example table-responsive"
                       id="tableCitys"
                       style="font-size: 14px;">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>城市名称</th>
                        <th>区域</th>
                        <th>简介</th>
                        <%--<th>内容介绍</th>--%>
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

<div class="modal fade modal-default" id="formEditCityInfo">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="formEditTitle">信息编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <input id="editCityInfoId" type="hidden">
                        <label for="editCityInfoName" class="col-sm-2 control-label">景点名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editCityInfoName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editCityInfoArea" class="col-sm-2 control-label">区域</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editCityInfoArea">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editCityInfoTitle" class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="editCityInfoTitle">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editCityInfoContent" class="col-sm-2 control-label">内容</label>
                        <div class="col-sm-10">
                            <div class="summernote" id="editCityInfoContent">

                            </div>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                <button type="button" id="publicBtn" class="btn   btn-primary" onclick="editSaveCityInfo()">确定
                </button>
            </div>
        </div>
    </div>
</div>

<!-- SUMMERNOTE -->
<script src="/js/plugins/summernote/summernote.min.js"></script>
<script src="/javascript/view/city/sysCity.js"></script>
