$(function () {
    initControls();
});

//初始化控件
function initControls() {
    $('#planDate').datepicker({
        language: "zh-CN",
        autoclose: true,
        clearBtn: false,
        todayBtn: true,
        format: "yyyy-mm-dd"
    });
}

function submitDatas() {
    $("#btnSubmit").button('loading');
    var jsondata = {
        'cusName': $("#cusName").val().trim(),
        'links': $("#links").val().trim(),
        'days': $("#days").val().trim(),
        'planDate': $("#planDate").val()
    };

    execAjaxDataForView("/view/customerAsk.action", JSON.stringify(jsondata), false, function (response) {
    }, function (response) {
        if (response.code == 0) {
            alert('信息提交成功，客服将会联系您.');
        }
    }, function () {
        $("#btnSubmit").button('reset');
    });
}