/**
 * Created by Feng on 2016/12/7.
 */


$(function () {
    initNavbar();
});


function login() {
    $("#btnLogin").button('正在登录...');
    var jsondata = {
        'userName': $("#userName").val(),
        'pwd': $("#pwd").val()
    };

    execAjaxDataForView("/view/login.action", JSON.stringify(jsondata), false, function (response) {
    }, function (response) {
        if (response.code == 0) {
            window.location.href = '/view/userHome.action';
        }
    }, function () {
        $("#btnLogin").button('reset');
    });
}
