/**
 * Created by Feng on 2016/11/12.
 */

$(function () {
    $('#productSpecialty').summernote();
    $('#productCost').summernote();
    $('#productReserve').summernote();
    $('#productNotice').summernote();

    $('#productStartDate').datepicker({
        language: "zh-CN",
        autoclose: true,
        clearBtn: false,
        todayBtn: true,
        format: "yyyy-mm-dd"
    });
});
