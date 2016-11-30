/**
 * Created by Feng on 2016/7/19.
 */

var token;// = $.cookie('userToken');

/**
 * 执行 ajax 调用
 * @param url
 * @param jsondata
 * @param async
 * @param callback
 */
function execAjaxData(url, jsondata, async, error, success, complete) {
    console.log(url);
    console.log(jsondata);
    $.loading(true);
    $.ajax({
        cache: false,
        url: url,
        type: "POST",
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        data: jsondata,
        async: async,
        error: function (data) {
            console.log(data);
            error(data);
            // 隐藏loading
            $.loading(false);
        },
        success: function (data) {
            console.log(data);
            // 隐藏loading
            $.loading(false);
            success(data);
        },
        complete: function () {
            $.loading(false);
            complete();
        }
    });
}

toastr.options = {
    "closeButton": true,
    "debug": false,
    "progressBar": true,
    "preventDuplicates": false,
    "positionClass": "toast-top-center",
    "onclick": null,
    "showDuration": "400",
    "hideDuration": "1000",
    "timeOut": "2000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}

function notifySuccess(title, message) {
    toastr.success(message, title);
}
function notifyInfo(title, message) {
    toastr.info(message, title);
}
function notifyWarning(title, message) {
    toastr.warning(message, title);
}
function notifyDanger(title, message) {
    toastr.error(message, title);
}
