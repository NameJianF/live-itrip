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
function funEditGetModuleInfo(moduleid) {
    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'module.detail',
        'token': token,
        'moduleid': moduleid
    };

    execAjaxData("/sysCfg.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (datas) {
            // success
            if (datas.code == 0) {
                $('#editModuleName').val(datas.data.moduleName);
                $('#editModuleParent').val(datas.data.parentId);
                $('#editModuleUrl').val(datas.data.moduleUrl);
                $('#editModuleAvailable').val(datas.data.available);
                $('#editModuleOrder').val(datas.data.moduleOrder);
                $('#editModuleDiscription').val(datas.data.description);
                $('#editModuleDelete').val(datas.data.isDelete);

                $('#formEditTitle').text("编辑模块");
                $('#formEditModule').modal('show');
            }
        }, function () {
            // complete
        });
}


function editSaveModuleInfo(isNew) {
    if (isNew) {
        // 新增
    } else {
        // 编辑
    }
}

/**
 * 删除
 * @param moduleid
 */
function funDeleteModuleInfo(moduleid) {
    if (confirm("确定要删除数据吗?")) {
        console.log("delete module id:" + moduleid);
    }
}

/**
 * 刷新
 */
function funRefresh() {
    tabModules.fnDraw();
}

/**
 * 新增
 */
function funClickAddRow() {
    console.log(" fnClickAddRow click ");
    // clear
    $('#formEditTitle').text("新增模块");

    $('#editModuleName').val("");
    $('#editModuleParent').val("");
    $('#editModuleUrl').val("");
    $('#editModuleAvailable').val(0);
    $('#editModuleOrder').val("");
    $('#editModuleDiscription').val("");
    $('#editModuleDelete').val(0);

    $('#formEditModule').modal('show');
}
