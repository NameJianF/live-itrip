/**
 * Created by Feng on 2016/12/29.
 */

$(function () {
    loadPageFromUrl('/view/home.action');
});


function menuItemClick(pageUrl) {
    if (pageUrl == undefined) {
        return false;
    }

    loadPageFromUrl(pageUrl);
}

function loadPageFromUrl(url) {
    // 查询数据
    $.ajax({
        cache: false,
        url: url,
        type: "GET",
        dataType: "html",
        async: true,
        error: function (data) {
            console.log(data);
        },
        success: function (data) {
            $("#divContent").html(data);
        }, complete: function () {
        }
    });

}

function logout() {
    var jsondata = {
        'userName': ''
    };
    execAjaxDataForView("/view/logout.action", JSON.stringify(jsondata), false, function (response) {
    }, function (response) {
        if (response.code == 0) {
            window.location.href = '/login.html';
        }
    }, function () {
    });
}

function saveProfileDatas() {
    var sex = $("input[name=optionsRadios]:checked").val();
    var jsondata = {
        'id': $("#userId").val(),
        'userName': $("#userName").val(),
        'realName': $("#realName").val(),
        'sex': sex,
        'mobile': $("#mobile").val()
    };

    execAjaxDataForView("/view/userEdit.action", JSON.stringify(jsondata), false, function (response) {
    }, function (response) {
        if (response.code == 0) {
            alert("数据保存成功。")
        }
    }, function () {

    });
}

function updatePassword() {
    $('#formEditPassword').modal('show');
}
function editSaveNewPassword() {

    if ($("#editNewPwd").val() != $("#editNewPwd2").val()) {
        alert("新密码两次输入不一致。");
        return;
    }

    var jsondata = {
        'uid': $("#userId").val(),
        'originPwd': $("#editOldPwd").val(),
        'password': $("#editNewPwd").val()
    };

    execAjaxDataForView("/view/password.action", JSON.stringify(jsondata), false, function (response) {
    }, function (response) {
        if (response.code == 0) {
            alert("数据保存成功。");
            $('#formEditPassword').modal('hide');
        } else {
            alert(response.msg);
        }
    }, function () {

    });
}