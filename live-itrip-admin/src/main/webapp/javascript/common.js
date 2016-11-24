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


/**
 * 执行 ajax 调用
 * @param url
 * @param jsondata
 * @param async
 * @param callback
 */
function execAjaxDataForView(url, jsondata, async, error, success, complete) {
    console.log(url);
    console.log(jsondata);
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
        },
        success: function (data) {
            console.log(data);
            success(data);
        },
        complete: function () {
            complete();
        }
    });
}



//parent.notifySuccess('删除成功', '');
//parent.notifySuccess('保存成功', '');
//parent.notifyInfo('刷新数据', '成功');
//parent.notifyWarning('刷新数据', '成功');
//parent.notifyDanger('删除失败', response.msg);
//parent.notifyDanger('保存失败', response.msg);

function notifySuccess(title, message) {
    notify(title, message, 'success', 'glyphicon glyphicon-star');
}
function notifyInfo(title, message) {
    notify(title, message, 'info', 'glyphicon glyphicon-star');
}
function notifyWarning(title, message) {
    notify(title, message, 'warning', 'glyphicon glyphicon-warning-sign');
}
function notifyDanger(title, message) {
    notify(title, message, 'danger', 'glyphicon glyphicon-warning-sign');
}

function notify(title, message, type, icon) {
    $.notify({
        // options
        icon: icon,
        title: title,
        message: message,
        //url: 'https://github.com/mouse0270/bootstrap-notify',
        //target: '_blank'
    }, {
        // settings
        element: 'body',
        position: null,
        type: type,
        allow_dismiss: true,
        newest_on_top: false,
        showProgressbar: false,
        placement: {
            from: "top",
            align: "center"
        },
        offset: 20,
        spacing: 10,
        z_index: 1031,
        delay: 3000,
        timer: 1000,
        url_target: '_blank',
        mouse_over: null,
        animate: {
            enter: 'animated fadeInDown',
            exit: 'animated fadeOutUp'
        },
        onShow: null,
        onShown: null,
        onClose: null,
        onClosed: null,
        icon_type: 'class',
        template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">' +
        '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>' +
        '<span data-notify="icon"></span> ' +
        '<span data-notify="title">{1}</span> ' +
        '<span data-notify="message">{2}</span>' +
        '<div class="progress" data-notify="progressbar">' +
        '<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
        '</div>' +
        '<a href="{3}" target="{4}" data-notify="url"></a>' +
        '</div>'
    });
}