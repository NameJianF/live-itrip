var tabModules;

function funSelectModules(sSource, aoData, fnCallback) {
    console.log("========== selectModules ==========");
    sSource = "/sysCfg.action?flag=1";

    // 添加查询条件
    //var queryContent = $("#queryContent").val();
    //var querySort = $("#querySort").val();
    //var queryStatus = $("#queryStatus").val();
    //aoData.push({name: "queryContent", value: queryContent});
    //aoData.push({name: "querySort", value: querySort});
    //aoData.push({name: "queryStatus", value: queryStatus});

    var token = $.cookie('userToken');
    aoData.push({name: "token", value: token});
    aoData = JSON.stringify(aoData);

    execAjaxData(sSource, aoData, false
        , function (response) {
            // error
        }, function (aaData) {
            // success
            fnCallback(aaData);
        }, function () {
            // complete
        });
}

/**
 * 修改
 * @param moduleid
 */
function funEditModuleInfo(moduleid) {
    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'module.detail',
        'token': token,
        'moduleid': moduleid
    };

    execAjaxData("/sysCfg.action", jsondata, true
        , function (response) {
            // error
        }, function (aaData) {
            // success
        }, function () {
            // complete
        });
}

/**
 * 删除
 * @param moduleid
 */
function funDeleteModuleInfo(moduleid) {

}

/**
 * 刷新
 */
function funRefresh() {

}

/**
 * 新增
 */
function funClickAddRow() {
    console.log(" fnClickAddRow click ");
}
