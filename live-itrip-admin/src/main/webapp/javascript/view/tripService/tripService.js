/**
 * Created by Feng on 2016/11/21.
 */

function submitDatas() {
    $("#btnSubmit").button('loading');
    var jsondata = {
        'op': 'tripService.add',
        'serviceType': $('#serviceType').find("option:selected").text(),
        'linkMan': $("#linkMan").val().trim(),
        'mobile': $("#mobile").val().trim(),
        'wechat': $("#wechat").val(),
        'remarks': $("#remarks").val()
    };

    execAjaxDataForView("/view/tripService.action", JSON.stringify(jsondata), false, function (response) {
    }, function (response) {
        if (response.code == 0) {
            alert('信息提交成功，客服将会联系您.');
            $('#serviceType').val(0);
            $("#linkMan").val('');
            $("#mobile").val('');
            $("#wechat").val('');
            $("#remarks").val('');
        }
    }, function () {
        $("#btnSubmit").button('reset');
    });
}