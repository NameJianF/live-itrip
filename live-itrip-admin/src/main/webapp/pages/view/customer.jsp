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
                <table class="table table-hover table-bordered dataTables-example" id="tableCustomers"
                       style="font-size: 12px;">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>称呼</th>
                        <th>联系方式</th>
                        <th>预计天数</th>
                        <th>出发日期</th>
                        <th>备注</th>
                        <th>状态</th>
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

<div class="modal fade modal-default" id="formEditCustomer">
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
                        <input id="editCustomerId" type="hidden">
                        <label for="editCustomerName" class="col-sm-3 control-label">称呼</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" readonly id="editCustomerName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editCustomerLink" class="col-sm-3 control-label">联系方式</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" readonly id="editCustomerLink">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editCustomerDays" class="col-sm-3 control-label">天数</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" readonly id="editCustomerDays">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editCustomerDate" class="col-sm-3 control-label">日期</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" readonly id="editCustomerDate">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editCustomerRemarks" class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" readonly id="editCustomerRemarks">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="editCustomerStatus" class="col-sm-3 control-label">状态</label>
                        <div class="col-sm-5">
                            <select class="form-control" id="editCustomerStatus">
                                <option value="0">初始</option>
                                <option value="1">未成单</option>
                                <option value="2">成单</option>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                <button type="button" id="publicBtn" class="btn   btn-primary" onclick="editSaveCustomerInfo()">确定
                </button>
            </div>
        </div>
    </div>
</div>

<script src="/javascript/view/customer/sysCustomer.js"></script>
