/**
 * Created by Feng on 2016/12/7.
 */


$(function () {
    $("#footer").load("/view/footer.html");


});


function register() {
    $("#btnRegister").button('正在提交...');
    var jsondata = {
        'userName': $("#userName").val(),
        'email': $("#email").val(),
        'password': $("#password").val()
    };

    execAjaxDataForView("/view/register.action", JSON.stringify(jsondata), false, function (response) {
    }, function (response) {
        if (response.code == 0) {
            window.location.href = '/view/index.action';
        } else {
            alert(response.msg);
        }
    }, function () {
        $("#btnRegister").button('reset');
    });
}
