var tabModules;

$(function () {
    tabModules = $('#tableModules').DataTable({
        //"dom": '<"html5buttons"B>lTfgitp',
        //"buttons": [
        //    {extend: 'copy'},
        //    {extend: 'csv'},
        //    {extend: 'excel', title: 'ExampleFile'},
        //    {extend: 'pdf', title: 'ExampleFile'},
        //    {
        //        extend: 'print',
        //        customize: function (win) {
        //            $(win.document.body).addClass('white-bg');
        //            $(win.document.body).css('font-size', '10px');
        //            $(win.document.body).find('table')
        //                .addClass('compact')
        //                .css('font-size', 'inherit');
        //        }
        //    }
        //],
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true, //这个用来指明是通过服务端来取数据
        "bPaginate": true, // 分页按钮
        "bLengthChange": true, // 改变每页显示数据数量
        "iDisplayLength": 10,// 每页显示行数
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        "fnServerData": funSelectModules, // 获取数据的处理函数
        "bFilter": false, // 隐藏筛选框
        "ordering": false,
        'bStateSave': true,
        "aoColumns": [
            {"mData": "id"},
            {"mData": "moduleName"},
            {"mData": "parentId"},
            {"mData": "moduleUrl"},
            {"mData": "moduleOrder"},
            {
                "mData": "description",
                render: function (data, type, row) {
                    if (data == null) {
                        return "";
                    }
                    return data;
                }
            },
            {
                "mData": "isDelete",
                render: function (data, type, row) {
                    if (data == 1) {
                        return "删除";
                    } else {
                        return "正常";
                    }
                }
            },
            {
                "mData": "createTime",
                render: function (data, type, row) {
                    if (data == null) {
                        return "";
                    }
                    return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
                }
            },
            {
                "mData": "updateTime",
                render: function (data, type, row) {
                    if (data == null) {
                        return "";
                    }
                    return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
                }
            },
            {
                render: function (data, type, row) {
                    if (type === 'display') {
                        return '<button type="button" class="btn btn-link btn-xs" onclick="funEditGetModuleInfo(' + row.id + ')">编辑</button>' +
                            '<button type="button" class="btn btn-link btn-xs" onclick="funDeleteModuleInfo(' + row.id + ')">删除</button>';
                    }
                    return data;
                }
            }
        ],
        "language": {  //语言设置
            'sSearch': '筛选:',
            "sLengthMenu": "每页显示  _MENU_ 条记录",
            "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
            },
            "sZeroRecords": "抱歉， 没有数据",
            "sInfoEmpty": "没有数据"
        }

    });

});


function funSelectModules(sSource, aoData, fnCallback) {
    console.log("========== selectModules ==========");
    sSource = "/sysCfg.action?flag=module";

    // 添加查询条件
    //var queryContent = $("#queryContent").val();
    //var querySort = $("#querySort").val();
    //var queryStatus = $("#queryStatus").val();
    //aoData.push({name: "queryContent", value: queryContent});
    //aoData.push({name: "querySort", value: querySort});
    //aoData.push({name: "queryStatus", value: queryStatus});

    aoData.push({name: "token", value: parent.token});
    aoData = JSON.stringify(aoData);

    parent.execAjaxData(sSource, aoData, false
        , function (response) {
            // error
        }, function (response) {
            // success
            fnCallback(response);
        }, function () {
            // complete
        });
}

/**
 * 修改
 * @param moduleid
 */
function funEditGetModuleInfo(moduleid) {
    var jsondata = {
        'op': 'module.detail',
        'token': parent.token,
        'moduleid': moduleid
    };

    parent.execAjaxData("/sysCfg.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#editModuleId').val(response.data.id);
                $('#editModuleName').val(response.data.moduleName);
                $('#editModuleParent').val(response.data.parentId);
                $('#editModuleUrl').val(response.data.moduleUrl);
                $('#editModuleOrder').val(response.data.moduleOrder);
                $('#editModuleDiscription').val(response.data.description);
                $('#editModuleDelete').val(response.data.isDelete);

                $('#formEditTitle').text("编辑模块");
                $('#formEditModule').modal('show');
            }
        }, function () {
            // complete
        });
}


function editSaveModuleInfo() {
    var jsondata = {
        'op': 'module.edit',
        'token': parent.token,
        'id': $('#editModuleId').val(),
        'moduleName': $('#editModuleName').val(),
        'parentId': $('#editModuleParent').val(),
        'moduleUrl': $('#editModuleUrl').val(),
        'moduleOrder': $('#editModuleOrder').val(),
        'description': $('#editModuleDiscription').val(),
        'isDelete': $('#editModuleDelete').val()
    };

    parent.execAjaxData("/sysCfg.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
            parent.notifyDanger('保存失败', response);
        }, function (response) {
            // success
            if (response.code == 0) {
                parent.notifySuccess('保存成功', '');
                funRefresh();
            } else {
                parent.notifyDanger('保存失败', response.msg);
            }
        }, function () {
            // complete
            $('#formEditApikey').modal('hide');
        });
}

/**
 * 删除
 * @param moduleid
 */
function funDeleteModuleInfo(moduleid) {
    if (confirm("确定要删除数据吗?")) {
        console.log("delete module id:" + moduleid);

        var jsondata = {
            'op': 'module.delete',
            'token': parent.token,
            'moduleid': moduleid
        };

        parent.execAjaxData("/sysCfg.action", JSON.stringify(jsondata), true
            , function (response) {
                // error
            }, function (response) {
                // success
                if (response.code == 0) {
                    parent.notifySuccess('删除成功', '');
                    funRefresh();
                } else {
                    parent.notifyDanger('删除失败', response.msg);
                }
            }, function () {
                // complete
            });
    }
}

/**
 * 刷新
 */
function funRefresh() {
    tabModules.ajax.reload();//.fnDraw();
}

/**
 * 新增
 */
function funClickAddRow() {
    console.log(" fnClickAddRow click ");
    // clear
    $('#formEditTitle').text("新增模块");

    $('#editModuleId').val(null)
    $('#editModuleName').val("");
    $('#editModuleParent').val("");
    $('#editModuleUrl').val("");
    $('#editModuleOrder').val("");
    $('#editModuleDiscription').val("");
    $('#editModuleDelete').val(0);

    $('#formEditModule').modal('show');
}
