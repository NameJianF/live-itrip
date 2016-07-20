/**
 * Created by 建锋 on 2016/7/7.
 */


/**
 * 登录
 */
function doLogin() {
    $("#btnLogin").button('loading');
    var jsondata = {
        'op': 'user.login',
        'userName': $("#userName").val(),
        'pwd': $("#pwd").val()
    };

    execAjaxData("/user.action", JSON.stringify(jsondata), false, function (response) {
    }, function (response) {
        if (response.code == 0) {
            window.location.href = 'index.htm';
        }
    }, function () {
        $("#btnLogin").button('reset');
    });
}