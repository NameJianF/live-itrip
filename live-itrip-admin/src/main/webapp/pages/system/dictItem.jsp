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
                    <button type="button" onclick="funRefresh();" class="btn btn-primary ">刷新</button>
                    <button type="button" onclick="funClickAddRow();" class="btn btn-primary ">
                        添加
                    </button>
                </div>
                <table class="table table-hover table-bordered dataTables-example" id="tabDictItem"
                       style="font-size: 14px;">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>字典子项名称</th>
                        <th>显示文本</th>
                        <th>所属字典</th>
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

    <div class="modal fade modal-default" id="formEditDictItem">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="formEditTitle">字典子项编辑</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <input id="editDictItemId" type="hidden">
                            <label for="editDictItemName" class="col-sm-3 control-label">字典子项名称</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editDictItemName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editDictItemText" class="col-sm-3 control-label">显示文本</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editDictItemText">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editDictName" class="col-sm-3 control-label">所属字典</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="editDictName">
                                    <c:forEach items="${dictList}" var="dict">
                                        <option value="${dict.id}">${dict.dictText}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editDictItemDelete" class="col-sm-3 control-label">是否删除</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="editDictItemDelete">
                                    <option value="1">删除</option>
                                    <option value="0">未删除</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                    <button type="button" id="publicBtn" class="btn   btn-primary" onclick="editSaveDictItemInfo()">确定
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>
</div>

<script src="/javascript/system/dictItem.js"></script>

