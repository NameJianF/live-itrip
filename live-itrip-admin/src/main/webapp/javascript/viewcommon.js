/**
 * Created by Feng on 2016/7/19.
 */

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
