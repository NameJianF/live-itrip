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
                </div>
            </div>
            <div class="ibox-content table-responsive">
                <table class="table table-hover table-bordered dataTables-example" id="tableServiceOrders"
                       style="font-size: 12px;">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>服务类型</th>
                        <th>联系人</th>
                        <th>手机号</th>
                        <th>微信</th>
                        <th>备注</th>
                        <th>处理</th>
                        <th>提交时间</th>
                        <th>处理时间</th>
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

<div class="modal fade modal-default" id="formEditOrder">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="formEditTitle">编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <input id="editOrderId" type="hidden">
                        <label for="editServiceType" class="col-sm-3 control-label">服务类型</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="editServiceType">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editLinkMan" class="col-sm-3 control-label">联系人</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" readonly id="editLinkMan">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editMobile" class="col-sm-3 control-label">手机号</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" readonly id="editMobile">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editWechat" class="col-sm-3 control-label">微信号</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" readonly id="editWechat">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editRemarks" class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-9">
                                <textarea type="text" rows="3" class="form-control" readonly
                                          id="editRemarks"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editSuccess" class="col-sm-3 control-label">状态</label>
                        <div class="col-sm-9">
                            <select class="form-control" id="editSuccess">
                                <option value="0">未处理</option>
                                <option value="1">未成单</option>
                                <option value="2">已成单</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                <button type="button" id="publicBtn" class="btn   btn-primary" onclick="editSaveOrderInfo()">确定
                </button>
            </div>
        </div>
    </div>
</div>

<script src="/javascript/view/tripService/sysTripService.js"></script>
