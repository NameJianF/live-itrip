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
                <table class="table table-hover table-bordered dataTables-example" id="tableProducts"
                       style="font-size: 14px;">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>标题</th>
                        <th>价格</th>
                        <th>折扣</th>
                        <th>天数</th>
                        <th>类型</th>
                        <th>出发城市</th>
                        <th>交通</th>
                        <th>出发日期</th>
                        <th>浏览次数</th>
                        <th>累计人数</th>
                        <th>HTML地址</th>
                        <th>状态</th>
                        <th>创建时间</th>
                        <th>修改时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>

    <div class="modal fade modal-default" id="formEditProduct">
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
                            <input id="editProductId" type="hidden">
                            <label for="editProductName" class="col-sm-3 control-label">模块名称</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editProductName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editProductParent" class="col-sm-3 control-label">父模块</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editProductParent">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editProductUrl" class="col-sm-3 control-label">模块地址</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editProductUrl">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editProductOrder" class="col-sm-3 control-label">排序</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editProductOrder">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editProductDiscription" class="col-sm-3 control-label">描述</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control" id="editProductDiscription">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="editProductDelete" class="col-sm-3 control-label">是否删除</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="editProductDelete">
                                    <option value="1">删除</option>
                                    <option value="0">未删除</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                    <button type="button" id="publicBtn" class="btn   btn-primary" onclick="editSaveProductInfo()">确定
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>
</div>

<script src="/javascript/view/product/sysProduct.js"></script>
