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
                <table class="table table-hover table-bordered dataTables-example" id="tableFaq"
                       style="font-size: 12px;">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>产品</th>
                        <th>用户名称</th>
                        <th>手机号</th>
                        <th>微信号</th>
                        <th>问题</th>
                        <th>回答</th>
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

<div class="modal fade modal-default" id="formEditFaq">
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
                        <input id="editFaqId" type="hidden">
                        <label for="editFaqContent" class="col-sm-3 control-label">咨询内容</label>
                        <div class="col-sm-9">
                                <textarea type="text" readonly rows="3" class="form-control"
                                          id="editFaqContent"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="editFaqAnswer" class="col-sm-3 control-label">回复</label>
                        <div class="col-sm-9">
                            <textarea type="text" class="form-control" id="editFaqAnswer" rows="3"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                <button type="button" id="publicBtn" class="btn   btn-primary" onclick="editSaveFaqInfo()">确定
                </button>
            </div>
        </div>
    </div>
</div>

<script src="/javascript/view/faq/sysFaq.js"></script>
