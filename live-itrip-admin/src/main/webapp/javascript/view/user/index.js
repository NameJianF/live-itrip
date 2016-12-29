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

}