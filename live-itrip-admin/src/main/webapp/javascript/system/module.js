var tabModules;


function selectModules(sSource, aoData, fnCallback) {
    console.log("========== selectModules ==========")
    sSource = "/sysCfg.action?op=module.select";

    // 添加查询条件
    //var queryContent = $("#queryContent").val();
    //var querySort = $("#querySort").val();
    //var queryStatus = $("#queryStatus").val();
    //aoData.push({name: "queryContent", value: queryContent});
    //aoData.push({name: "querySort", value: querySort});
    //aoData.push({name: "queryStatus", value: queryStatus});

    //var token = $.cookie('userToken');
    //aoData.push({name: "token", value: token});
    //aoData.push({name: "op", value: "module.select"});

    aoData = JSON.stringify(aoData);

    execAjaxData(sSource, aoData, true
        , function (response) {
            // error
        }, function (aaData) {
            // success
            fnCallback(aaData);
        }, function () {
            // complete
        });

    //$.ajax({
    //    url: sSource, //sAjaxSource
    //    data: aoData,
    //    type: 'POST',
    //    dataType: 'json',
    //    contentType: 'application/json;charset=UTF-8',
    //    async: false,
    //    success: function (aaData) {
    //        console.log("call data:" + aaData);
    //        fnCallback(aaData);
    //    },
    //    error: function (msg) {
    //        console.log(msg);
    //    }
    //});
}
