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
<!DOCTYPE html>
<html>
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/plugins/dropzone/basic.css" rel="stylesheet">
    <link href="/css/plugins/dropzone/dropzone.css" rel="stylesheet">
    <link href="/css/plugins/dataTables/datatables.min.css" rel="stylesheet">

    <style>
        .dropzone {
            width: 500px;
            height: 230px;
            min-height: 0px !important;
        }
    </style>
</head>
<body>
<div class="wrapper wrapper-content animated fadeInRight ecommerce">
    <input id="productId" type="hidden"
    <c:if test="${!empty productId}">
           value="${productId}"
    </c:if>
    >
    <div class="row">
        <div class="col-lg-12">
            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#tab-1"><i
                            class="fa fa-desktop"></i>基本信息</a>
                    </li>
                    <li><a data-toggle="tab" href="#tab-2"><i
                            class="fa fa-desktop"></i>产品特色</a>
                    </li>
                    <li><a data-toggle="tab" href="#tab-3"><i
                            class="fa fa-desktop"></i>行程详情</a>
                    </li>

                    <li><a data-toggle="tab" href="#tab-4"><i
                            class="fa fa-desktop"></i>费用说明</a>
                    </li>
                    <li><a data-toggle="tab" href="#tab-5"><i
                            class="fa fa-desktop"></i>预定须知</a>
                    </li>
                    <li><a data-toggle="tab" href="#tab-6"><i
                            class="fa fa-desktop"></i>出游提醒</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">
                            <fieldset class="form-horizontal">
                                <div class="form-group">
                                    <label for="productTitle" class="col-sm-2 control-label">行程名称</label>
                                    <div class="col-sm-6">
                                        <input id="productTitle" type="text" class="form-control" placeholder="行程名称">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="productPrice" class="col-sm-2 control-label">价格</label>
                                    <div class="col-sm-6">
                                        <input id="productPrice" type="text" class="form-control" placeholder="价格"
                                               maxlength="8">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="priceFavoured" class="col-sm-2 control-label">折扣</label>
                                    <div class="col-sm-6">
                                        <input id="priceFavoured" type="text" class="form-control" value="100"
                                               maxlength="3">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="productType" class="col-sm-2 control-label">线路类型</label>
                                    <div class="col-sm-6">
                                        <select class="form-control m-b" id="productType">
                                            <c:forEach items="${listType}" var="item">
                                                <option value="${item.id}">${item.dictItemText}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="productDays" class="col-sm-2 control-label">行程天数</label>
                                    <div class="col-sm-6">
                                        <select class="form-control m-b" id="productDays">
                                            <c:forEach items="${listDays}" var="item">
                                                <option value="${item.id}">${item.dictItemText}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="productFromCity" class="col-sm-2 control-label">出发城市</label>
                                    <div class="col-sm-6">
                                        <select class="form-control m-b" id="productFromCity">
                                            <c:forEach items="${listCity}" var="item">
                                                <option value="${item.id}">${item.dictItemText}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="productTraffic" class="col-sm-2 control-label">往返交通</label>
                                    <div class="col-sm-6">
                                        <select class="form-control m-b" id="productTraffic">
                                            <c:forEach items="${listTraffic}" var="item">
                                                <option value="${item.id}">${item.dictItemText}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="productStartDate" class="col-sm-2 control-label">出发日期</label>
                                    <div class="col-sm-6">
                                        <div class="input-group date">
                                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                            <input id="productStartDate" type="text" class="form-control" value="">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-8">
                                    <button type="button" onclick="saveProductBaseInfo();"
                                            class="btn btn-primary pull-right m-t-n-xs" style="margin-left: 10px;">
                                        保存
                                    </button>
                                    <%--<button type="button" onclick="funRefresh();"--%>
                                    <%--class="btn btn-warning pull-right m-t-n-xs">清空--%>
                                    <%--</button>--%>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                    <div id="tab-2" class="tab-pane">
                        <div class="panel-body">
                            <fieldset class="form-horizontal">
                                <div class="form-group">
                                    <label for="productImgSamll" class="col-sm-2 control-label">小图</label>
                                    <input id="productImgSamllId" type="hidden">
                                    <div class="col-sm-8">
                                        <input id="productImgSamll" type="text" readonly class="form-control">
                                    </div>
                                    <button type="button" class="col-sm-2 btn btn-primary" style="max-width: 60px;"
                                            onclick="formUploadImageShow('small');">
                                        上传
                                    </button>
                                </div>
                                <div class="form-group">
                                    <label for="productImgBig" class="col-sm-2 control-label">大图</label>
                                    <input id="productImgBigId" type="hidden">
                                    <div class="col-sm-8">
                                        <input id="productImgBig" type="text" readonly class="form-control">
                                    </div>
                                    <button type="button" class="col-sm-2 btn btn-primary" style="max-width: 60px;"
                                            onclick="formUploadImageShow('big');">
                                        上传
                                    </button>
                                </div>
                                <div class="form-group">
                                    <label for="productDesr" class="col-sm-2 control-label">行程简介</label>
                                    <div class="col-sm-10">
                                        <input id="productDesr" type="text" maxlength="200" class="form-control"
                                               placeholder="100字内">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="productSpecialty" class="col-sm-2 control-label">产品特色</label>
                                    <div class="col-sm-10">
                                        <div class="summernote" id="productSpecialty">
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <button type="button" onclick="saveProductDescInfo();"
                                            class="btn btn-primary pull-right m-t-n-xs" style="margin-left: 10px;">
                                        保存
                                    </button>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                    <div id="tab-3" class="tab-pane">
                        <div class="panel-body">
                            <fieldset class="form-horizontal">
                                <div>
                                    <button type="button" onclick="addNewPlanDetail();"
                                            class="btn btn-primary pull-left m-t-n-xs" style="margin-bottom: 2px;">
                                        新增
                                    </button>
                                    <button type="button" onclick="funRefresh();"
                                            style="margin-bottom: 2px;margin-left: 5px;"
                                            class="btn btn-primary pull-left m-t-n-xs">刷新
                                    </button>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <div class="table-responsive">
                                            <table class="table table-hover table-bordered dataTables-example table-striped"
                                                   id="tablePlanDetails"
                                                   style="width: 100%;">
                                                <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>第*天</th>
                                                    <th>起点</th>
                                                    <th>交通</th>
                                                    <th>终点</th>

                                                    <th>早餐</th>
                                                    <th>午餐</th>
                                                    <th>晚餐</th>
                                                    <th>酒店</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                    <div id="tab-4" class="tab-pane">
                        <div class="panel-body">
                            <fieldset class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">费用说明</label>
                                    <div class="col-sm-6">
                                        <select id="selectCost" class="form-control m-b" onchange="costSelectChange();">
                                            <option></option>
                                            <c:forEach items="${listCosts}" var="item">
                                                <option value="${item.id}">${item.title}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="productcost" class="col-sm-2 control-label">编辑</label>
                                    <div class="col-sm-10">
                                        <div class="summernote" id="productCost">
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <button type="button" onclick="saveProductCoseInfo();"
                                            class="btn btn-primary pull-right m-t-n-xs" style="margin-left: 10px;">
                                        保存
                                    </button>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                    <div id="tab-5" class="tab-pane">
                        <div class="panel-body">
                            <fieldset class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">预定须知</label>
                                    <div class="col-sm-6">
                                        <select class="form-control m-b" id="selectReserves"
                                                onchange="reservesSelectChange();">
                                            <option></option>
                                            <c:forEach items="${listReserves}" var="item">
                                                <option value="${item.id}">${item.title}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="productreserve" class="col-sm-2 control-label">编辑</label>
                                    <div class="col-sm-10">
                                        <div class="summernote" id="productReserve">
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <button type="button" onclick="saveProductReservesInfo();"
                                            class="btn btn-primary pull-right m-t-n-xs" style="margin-left: 10px;">
                                        保存
                                    </button>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                    <div id="tab-6" class="tab-pane">
                        <div class="panel-body">
                            <fieldset class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">出游提醒</label>
                                    <div class="col-sm-6">
                                        <select class="form-control m-b" id="selectNotice"
                                                onchange="noticeSelectChange();">
                                            <option></option>
                                            <c:forEach items="${listNotices}" var="item">
                                                <option value="${item.id}">${item.title}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="productnotice" class="col-sm-2 control-label">编辑</label>
                                    <div class="col-sm-10">
                                        <div class="summernote" id="productNotice">
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <button type="button" onclick="saveProductNoticeInfo();"
                                            class="btn btn-primary pull-right m-t-n-xs" style="margin-left: 10px;">
                                        保存
                                    </button>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<%--图片上传--%>
<div class="modal fade modal-default" id="formUploadImage">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="formEditTitle">图片上传</h4>
            </div>
            <div class="modal-body">
                <form id="my-awesome-dropzone" class="dropzone">
                    <div class="dropzone-previews"></div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" id="btnUploadFile" style="margin-right: 10px;">上传文件</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<%--行程详情--%>
<div class="modal fade modal-default ecommerce" id="formPlanDetail">
    <div class="modal-dialog" style="width: 900px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="formPlanDetailTitle">行程详情</h4>
            </div>
            <div class="modal-body panel-body">
                <form class="form-horizontal">
                    <%--id--%>
                    <input id="planId" type="hidden">
                    <div class="form-group">
                        <label for="planTitle" class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="planTitle">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="planStationFrom" class="col-sm-2 control-label">起点</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="planStationFrom">
                        </div>
                        <label for="planTraffic" class="col-sm-2 control-label">交通</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="planTraffic">
                        </div>
                        <label for="planStationTo" class="col-sm-2 control-label">终点</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="planStationTo">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="planBreakfast" class="col-sm-2 control-label">早餐</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="planBreakfast">
                        </div>
                        <label for="planLunch" class="col-sm-2 control-label">午餐</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="planLunch">
                        </div>
                        <label for="planDinner" class="col-sm-2 control-label">晚餐</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="planDinner">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="planHotel" class="col-sm-2 control-label">酒店</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="planHotel">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="planContent" class="col-sm-2 control-label">景点介绍</label>
                        <div class="col-sm-10">
                            <div class="summernote" id="planContent">
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                <button type="button" id="publicBtn" class="btn   btn-primary" onclick="editSavePlanInfo()">确定
                </button>
            </div>
        </div>
    </div>
</div>


<!-- Mainly scripts -->
<script src="/js/jquery-2.1.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="/js/itrip/jquery.cookie.js"></script>

<!-- Custom and plugin javascript -->
<script src="/js/inspinia.js"></script>
<script src="/js/plugins/pace/pace.min.js"></script>

<!-- SUMMERNOTE -->
<script src="/js/plugins/summernote/summernote.min.js"></script>

<!-- Data picker -->
<script src="/js/plugins/datapicker/bootstrap-datepicker.js"></script>

<!-- DROPZONE -->
<script src="/js/plugins/dropzone/dropzone.js"></script>

<script src="/js/plugins/dataTables/datatables.min.js"></script>

<script src="/javascript/view/product/sysProductNew.js"></script>

</body>

</html>