<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>itrip.live - 登录</title>
    <meta name="keywords" content="和旅行 日本地接 日本旅游">
    <meta name="description" content="">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/itrip/login.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

    <script src="/js/jquery-2.1.1.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <script>
        if (window.top !== window.self) {
            window.top.location = window.location
        }
    </script>


    <script src="/javascript/login.js"></script>
    <script src="/javascript/common.js"></script>
</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1>和旅行-iTrip.live</h1>
                </div>
                <div class="m-b"></div>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 日本地接</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 日本旅游</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 北海道</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 东京</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 大阪</li>
                </ul>
                <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
            </div>
        </div>
        <div class="col-sm-5">
            <h4 class="no-margins">Login</h4>
            <input id="userName" type="text" class="form-control uname" placeholder="邮箱" value="fjf789@126.com"/>
            <input id="pwd" type="password" class="form-control pword m-b" placeholder="密码" value="123456"/>
            <a href="#">忘记密码了？</a>
            <button id="btnLogin" data-loading-text="Loading..." class="btn btn-success btn-block" onclick="doLogin()">
                登录
            </button>
        </div>
    </div>
    <div class="signup-footer" style="margin-top: 150px;">
        <div class="pull-left">
            &copy; 2016 All Rights Reserved.
        </div>
    </div>
</div>
</body>
</html>
