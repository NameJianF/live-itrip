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

<div class="wrapper wrapper-content animated fadeInRight gray-bg">


    <div class="row m-b-lg m-t-lg">
        <div class="col-md-6">

            <div class="profile-image">
                <img src="/img/a4.jpg" class="img-circle circle-border m-b-md" alt="profile">
            </div>
            <div class="profile-info">
                <div class="">
                    <div>
                        <h2 class="no-margins">
                            ${user.userName}
                        </h2>
                        <h4>${user.email}</h4>
                        <small>
                            There are many variations of passages of Lorem Ipsum available, but the majority
                            have suffered alteration in some form Ipsum available.
                        </small>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <table class="table small m-b-xs">
                <tbody>
                <tr>
                    <td>
                        <strong>142</strong> Projects
                    </td>
                    <td>
                        <strong>22</strong> Followers
                    </td>

                </tr>
                <tr>
                    <td>
                        <strong>61</strong> Comments
                    </td>
                    <td>
                        <strong>54</strong> Articles
                    </td>
                </tr>
                <tr>
                    <td>
                        <strong>154</strong> Tags
                    </td>
                    <td>
                        <strong>32</strong> Friends
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">

        <div class="col-lg-12">

            <div class="ibox">
                <div class="ibox-content">
                    <h3>About Alex Smith</h3>

                    <p class="small">
                        There are many variations of passages of Lorem Ipsum available, but the majority have
                        suffered alteration in some form, by injected humour, or randomised words which don't.
                        <br>
                        <br>
                        If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't
                        anything embarrassing
                    </p>

                    <p class="small font-bold">
                        <span><i class="fa fa-circle text-navy"></i> Online status</span>
                    </p>

                </div>
            </div>

        </div>

        <div class="col-lg-12 m-b-lg">
            <div id="vertical-timeline" class="vertical-container light-timeline no-margins">
                <div class="vertical-timeline-block">
                    <div class="vertical-timeline-icon navy-bg">
                        <i class="fa fa-briefcase"></i>
                    </div>

                    <div class="vertical-timeline-content">
                        <h2>Meeting</h2>
                        <p>Conference on the sales results for the previous year. Monica please examine sales trends in
                            marketing and products. Below please find the current status of the sale.
                        </p>
                        <a href="profile_2.html#" class="btn btn-sm btn-primary"> More info</a>
                                    <span class="vertical-date">
                                        Today <br>
                                        <small>Dec 24</small>
                                    </span>
                    </div>
                </div>

                <div class="vertical-timeline-block">
                    <div class="vertical-timeline-icon blue-bg">
                        <i class="fa fa-file-text"></i>
                    </div>

                    <div class="vertical-timeline-content">
                        <h2>Send documents to Mike</h2>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has
                            been the industry's standard dummy text ever since.</p>
                        <a href="profile_2.html#" class="btn btn-sm btn-success"> Download document </a>
                                    <span class="vertical-date">
                                        Today <br>
                                        <small>Dec 24</small>
                                    </span>
                    </div>
                </div>

                <div class="vertical-timeline-block">
                    <div class="vertical-timeline-icon lazur-bg">
                        <i class="fa fa-coffee"></i>
                    </div>

                    <div class="vertical-timeline-content">
                        <h2>Coffee Break</h2>
                        <p>Go to shop and find some products. Lorem Ipsum is simply dummy text of the printing and
                            typesetting industry. Lorem Ipsum has been the industry's. </p>
                        <a href="profile_2.html#" class="btn btn-sm btn-info">Read more</a>
                        <span class="vertical-date"> Yesterday <br><small>Dec 23</small></span>
                    </div>
                </div>

                <div class="vertical-timeline-block">
                    <div class="vertical-timeline-icon yellow-bg">
                        <i class="fa fa-phone"></i>
                    </div>

                    <div class="vertical-timeline-content">
                        <h2>Phone with Jeronimo</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto, optio, dolorum provident
                            rerum aut hic quasi placeat iure tempora laudantium ipsa ad debitis unde? Iste voluptatibus
                            minus veritatis qui ut.</p>
                        <span class="vertical-date">Yesterday <br><small>Dec 23</small></span>
                    </div>
                </div>

                <div class="vertical-timeline-block">
                    <div class="vertical-timeline-icon navy-bg">
                        <i class="fa fa-comments"></i>
                    </div>

                    <div class="vertical-timeline-content">
                        <h2>Chat with Monica and Sandra</h2>
                        <p>Web sites still in their infancy. Various versions have evolved over the years, sometimes by
                            accident, sometimes on purpose (injected humour and the like). </p>
                        <span class="vertical-date">Yesterday <br><small>Dec 23</small></span>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>

<div class="modal fade modal-default" id="formEditDepart">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="formEditTitle">部门编辑</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <input id="editDepartId" type="hidden">
                        <label for="editDepartName" class="col-sm-3 control-label">部门名称</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="editDepartName">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn   btn-primary" data-dismiss="modal">取消</button>
                <button type="button" id="publicBtn" class="btn   btn-primary" onclick="editSaveDepartInfo()">确定
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<!-- Sparkline -->
<script src="js/plugins/sparkline/jquery.sparkline.min.js"></script>


<script src="/javascript/system/profile.js"></script>

