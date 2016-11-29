<%--
  Created by IntelliJ IDEA.
  User: Feng
  Date: 2016/11/23
  Time: 15:35
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
    <title>和旅行-专业日本地接,日本旅行</title>
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

    <!-- Mainly scripts -->
    <script src="/js/jquery-2.1.1.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

</head>
<body id="page-top" class="landing-page">
<div class="navbar-wrapper">
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="background: rgba(138, 109, 59, 0.70);">
        <div class="container">
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation1</span>
                    <span class="icon-bar">Toggle navigation2</span>
                    <span class="icon-bar">Toggle navigation3</span>
                    <span class="icon-bar">Toggle navigation4</span>
                </button>
                <a class="navbar-brand" href="/index.html">和旅行</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a class="page-scroll" href="/index.html">首页</a></li>
                    <li><a class="page-scroll" href="/view/customer.html">私人定制</a></li>
                    <li><a class="page-scroll" href="/view/theme.html">主题旅游</a></li>
                    <li><a class="page-scroll" href="/view/town.html">乡村民宿体验游</a></li>
                    <li><a class="page-scroll" href="/view/plan.html">参考行程</a></li>
                    <li><a class="page-scroll" href="/view/service.html">旅行服务</a></li>
                    <li><a class="page-scroll" href="/view/about.html">联系我们</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="townbg">
</div>

<section class="towncenter">
    <div class="row">
        <div class="col-md-9">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <input id="cityId" type="hidden" value="${id}">
                    ${cityName}
                </div>
                <div class="panel-body">
                    <div class="wrapper wrapper-content animated fadeinleft">
                        ${cityContent}
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    推荐线路
                </div>
                <div class="panel-body" style="padding: 5px">
                    <div class="wrapper wrapper-content animated fadeInRight">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="ibox">
                                    <div class="ibox-content product-box">
                                        <div class="product-imitation" style="padding: 0px">
                                            <a href="#">
                                                <img src="/images/web/theme/wq01.jpg" style="width: 280px;">
                                            </a>
                                        </div>
                                        <div class="product-desc">
                                            <span class="product-price">&yen; 10688</span>
                                            <a href="#" class="product-name"> 北海道-北海道-北海道-北海道</a>
                                            <div class="vote-info" style="margin-left: 0px;">
                                                <i class="fa fa-comments-o"></i> <a
                                                    href="#">Comments(32)</a>
                                                <i class="fa fa-user"></i>
                                                <a href="#">Andrew Williams</a>
                                            </div>
                                            <div class="m-t text-right">
                                                <a href="#"
                                                   class="btn btn-xs btn-outline btn-primary">详细 <i
                                                        class="fa fa-long-arrow-right"></i> </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="ibox">
                                    <div class="ibox-content product-box">
                                        <div class="product-imitation" style="padding: 0px">
                                            <a href="#">
                                                <img src="/images/web/theme/wq01.jpg" style="width: 280px;">
                                            </a>
                                        </div>
                                        <div class="product-desc">
                                            <span class="product-price">&yen; 10688</span>
                                            <a href="#" class="product-name"> 北海道-北海道-北海道-北海道</a>
                                            <div class="vote-info" style="margin-left: 0px;">
                                                <i class="fa fa-comments-o"></i> <a
                                                    href="#">Comments(32)</a>
                                                <i class="fa fa-user"></i>
                                                <a href="#">Andrew Williams</a>
                                            </div>
                                            <div class="m-t text-right">
                                                <a href="#"
                                                   class="btn btn-xs btn-outline btn-primary">详细 <i
                                                        class="fa fa-long-arrow-right"></i> </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!--footer-->
<section id="footer" class="gray-section contact">
</section>


<!-- Custom and plugin javascript -->
<script src="/js/inspinia.js"></script>
<script src="/js/plugins/pace/pace.min.js"></script>
<script src="/js/plugins/wow/wow.min.js"></script>

<%--itrip--%>
<script src="/javascript/viewcommon.js"></script>
<script src="/javascript/view/city/city.js"></script>


</body>
</html>

