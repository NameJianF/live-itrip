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
                </div>
                <table class="table table-hover table-bordered dataTables-example table-striped" id="tableProducts"
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
</div>

<script src="/javascript/view/product/sysProduct.js"></script>
