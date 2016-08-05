/**
 * Created by Feng on 2016/7/19.
 */


/**
 * 获取根路径
 * @returns {string}
 */
function getRootPath() {
    var strFullPath = window.document.location.href;
    var strPath = window.document.location.pathname;
    var pos = strFullPath.indexOf(strPath);
    var prePath = strFullPath.substring(0, pos);
    //var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    return prePath;
}

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
            error(data)
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

