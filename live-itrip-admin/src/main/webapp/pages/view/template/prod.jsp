<%--
  Created by IntelliJ IDEA.
  User: Feng
  Date: 2016/12/1
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${product.title}</title>
    <link rel="shortcut icon" href="/img/shortcut.png">
    <meta name="keywords" content="日本旅游,日本自由行,日本旅游价格,赴日旅游,行程报价,日本报价"/>
    <meta name="description" content="日本中和旅行是日本本地知名旅行服务商,提供华人到日本旅游包括日本跟团游、日本自由行、日本自驾游、机场接送等一站式日本旅游服务。"/>
    <meta name="author" content="和旅行 itrip.live">

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Animation CSS -->
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/style.css" rel="stylesheet">

    <link href="/css/view/town.css" rel="stylesheet">
    <link href="/css/view/footer.css" rel="stylesheet">

    <style>
        .nav-tabs > li > a {
            color: #1ab394;
            font-weight: 600;
            padding: 10px 20px 10px 25px;
        }

        .topbg {
            margin-top: 65px;
            margin-bottom: -19px;
            margin-left: -15px;
            height: 80px;
            text-align: center;
        }
    </style>
</head>
<body id="page-top" class="landing-page">

<div class="navbar-wrapper">
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header page-scroll">
                <a class="navbar-brand" href="/index.html">
                    <img src="/img/he.png" style="width: 30px;height: auto;margin: -5px;">
                </a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-left">
                    <li><a class="page-scroll" style="margin-left: 20px;" href="/index.html">首页</a></li>
                    <li><a class="page-scroll" href="/view/customer.html">私人定制</a></li>
                    <li><a class="page-scroll" href="/view/theme.html">主题旅游</a></li>
                    <li><a class="page-scroll" href="/view/town.html">乡村民宿体验游</a></li>
                    <li><a class="page-scroll" href="/view/plan.html">参考行程</a></li>
                    <li><a class="page-scroll" href="/view/service.html">旅行服务</a></li>
                    <li><a class="page-scroll" href="/view/about.html">联系我们</a></li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li><a class="page-scroll" style="padding-right: 5px;font-size: 14px;" onclick="login();">登录</a>
                    </li>
                    <li><a class="page-scroll" style="padding-right: 5px;font-size: 14px;" onclick="register();">注册</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="topbg">
            <img src="/images/web/theme/top.gif" style="width: 95%">
        </div>
    </div>
</div>
<div class="row" style="margin: 20px;">

    <div class="col-md-9" style="margin-top: 15px;">
        <!--摘要-->
        <div class="row">
            <div class="panel panel-primary">
                <input id="productId" type="hidden" value="${product.id}">
                <input id="productType" type="hidden" value="${product.type}">

                <div class="panel-heading" style="font-size: 20px;">
                    ${product.title}
                </div>
                <div class="panel-body" style="padding-left: 0px; padding-right: 0px;">
                    <div class="col-md-6">
                        <div>
                            <%--<a href="/view/product.action?pid=${product.id}">--%>
                            <img src="${product.imgMiddle}">
                            <%--</a>--%>
                        </div>
                        <div style="margin-top: 10px;">
                            ${product.description}
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div style="margin:0 auto; height: 60px; background-color: rgb(242, 77, 95);color: #ffffff;">
                            <div style="padding-top: 14px;padding-left: 20px;">参考价 <span
                                    style="font-size: 24px;margin-left: 30px;"> &yen;
                                ${product.price} </span></div>
                        </div>
                        <table class="table small m-b-xs">
                            <tbody>
                            <tr>
                                <td>
                                    线路类型： <strong>${product.type}</strong>
                                </td>
                                <td>
                                    出发城市： <strong>${product.fromCity}</strong>
                                </td>

                            </tr>
                            <tr>
                                <td>
                                    行程天数： <strong>${product.days} 天</strong>
                                </td>
                                <td>
                                    往返交通： <strong>${product.traffic}</strong>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="btn btn-outline btn-success">建议提前30天报名</label>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-outline btn-warning">起价说明</button>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                        <div class="m-t text-left">
                            <form class="form-horizontal">
                                <div class="form-group"><label class="col-sm-3 control-label">出发日期</label>
                                    <div class="col-sm-9"><select class="form-control m-b" name="account">
                                        <option value="1" selected>固定日期发团</option>
                                    </select>
                                    </div>
                                </div>
                                <div class="form-group"><label class="col-sm-3 control-label">出游人数</label>
                                    <div class="col-sm-9">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <label class="control-label">成人</label>
                                            </div>
                                            <div class="col-md-3" style="margin-left: -50px">
                                                <input type="text" placeholder="10" class="form-control">
                                            </div>
                                            <div class="col-md-3">
                                                <label class="control-label">儿童</label>
                                            </div>
                                            <div class="col-md-3" style="margin-left: -50px">
                                                <input type="text" placeholder="0" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="m-t text-right">
                            <label class="btn btn-outline btn-success" style="padding: 10px 20px;"><i
                                    class="fa fa-long-arrow-left"></i>加入收藏</label>
                            <label class="btn btn-outline btn-info" style="padding: 10px 20px;">立刻预定<i
                                    class="fa fa-long-arrow-right"></i></label>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--明细-->
        <div class="row">
            <div class="tabs-container">
                <ul class="nav nav-tabs" style="font-size: 16px;">
                    <!--产品特色-->
                    <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true"> <i
                            class="fa fa-laptop"></i>产品特色</a></li>

                    <!--行程详情-->
                    <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="false"><i
                            class="fa fa-desktop"></i>行程详情</a></li>

                    <!--费用说明-->
                    <li class=""><a data-toggle="tab" href="#tab-3" aria-expanded="false"><i
                            class="fa fa-database"></i>费用说明</a></li>
                    <!--预定须知-->
                    <li class=""><a data-toggle="tab" href="#tab-4" aria-expanded="false"><i
                            class="fa fa-database"></i>预定须知</a></li>
                    <!--出游提醒-->
                    <li class=""><a data-toggle="tab" href="#tab-5" aria-expanded="false"><i
                            class="fa fa-database"></i>出游提醒</a></li>
                    <!--在线咨询-->
                    <li class=""><a data-toggle="tab" href="#tab-6" aria-expanded="false"><i
                            class="fa fa-database"></i>在线咨询</a></li>
                </ul>
                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="panel-body">
                            ${product.specialty}
                        </div>
                    </div>
                    <div id="tab-2" class="tab-pane">
                        <div class="panel-body" style=" background-color: #f3f3f4;">
                            <div id="vertical-timeline" class="vertical-container light-timeline">
                                <c:forEach items="${planList}" var="item" varStatus="myIndex">
                                    <div class="vertical-timeline-block">
                                        <div class="vertical-timeline-icon navy-bg">
                                            <i class="fa fa-flag"></i>
                                        </div>
                                        <div class="vertical-timeline-content">
                                            <h2>${item.title}</h2>
                                            <table class="table small m-b-xs">
                                                <tbody>
                                                <tr style="height: 50px;">
                                                    <td style="line-height: 40px;">
                                                        <i class="fa fa-delicious" style="margin-right: 10px;"></i>
                                                        早餐: <span class="label label-primary"
                                                                  style="margin-right: 30px;">${item.breakfast}</span>
                                                        中餐: <span class="label label-primary"
                                                                  style="margin-right: 30px;">${item.lunch}</span>
                                                        晚餐: <span class="label label-primary"
                                                                  style="margin-right: 30px;">${item.dinner}</span>
                                                    </td>
                                                </tr>
                                                <tr style="height: 50px;">
                                                    <td style="line-height: 40px;">
                                                        <i class="fa fa-building" style="margin-right: 10px;"></i>
                                                        住宿: <span class="label label-primary">${item.hotel}</span>
                                                    </td>
                                                </tr>
                                                <tr style="height: 50px;">
                                                    <td style="line-height: 40px;">
                                                        <i class="fa fa-building" style="margin-right: 10px;"></i>
                                                        交通: <span class="label label-car">${item.traffic}</span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <p>${item.content}</p>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <a href="#" class="btn btn-xs btn-primary"> More
                                                info</a>
                                            <span class="vertical-date"><small>第 ${myIndex.count} 天
                                            </small> </span>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div id="tab-3" class="tab-pane">
                        <div class="panel-body">
                            ${product.cost}
                        </div>
                    </div>
                    <div id="tab-4" class="tab-pane">
                        <div class="panel-body">
                            ${product.reserve}
                        </div>
                    </div>
                    <div id="tab-5" class="tab-pane">
                        <div class="panel-body">
                            ${product.notice}
                        </div>
                    </div>
                    <div id="tab-6" class="tab-pane">
                        <div class="panel-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">联系方式</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control">
                                    </div>
                                </div>
                                <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">咨询内容</label>
                                    <div class="col-sm-10">
                                        <textarea type="text" aria-multiline="true" class="form-control"
                                                  style="height: 100px;"></textarea>
                                        <span class="help-block m-b-none">请详细描述咨询内容.</span>
                                    </div>
                                </div>
                                <div class="hr-line-dashed"></div>
                                <div class="form-group">
                                    <div class="col-sm-4 col-sm-offset-2">
                                        <button class="btn btn-primary" onclick="submitQuestion();">提交</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--侧边栏-->
    <div class="col-md-3  animated fadeInRight" style="margin-top: 15px;">
        <div class="panel panel-success">
            <div class="panel-heading">
                相关线路
            </div>
            <div class="panel-body" style="padding-top: 0px;">
                <div class="wrapper wrapper-content">
                    <div class="row" id="aboutProducts">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--footer-->
<section id="footer" class="gray-section contact">
</section>

<!-- Mainly scripts -->
<script src="/js/jquery-2.1.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="/js/inspinia.js"></script>
<script src="/js/plugins/pace/pace.min.js"></script>
<script src="/js/plugins/wow/wow.min.js"></script>

<%--itrip--%>
<script src="/javascript/viewcommon.js"></script>
<script src="/javascript/view/product/product.js"></script>

</body>
</html>
