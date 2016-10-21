var tableApikey;

$(function () {
    tableApikey = $('#tableModules').DataTable({
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true, //这个用来指明是通过服务端来取数据
        "bPaginate": true, // 分页按钮
        "bLengthChange": true, // 改变每页显示数据数量
        "iDisplayLength": 10,// 每页显示行数
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        "fnServerData": funSelectApikeys, // 获取数据的处理函数
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
                        return '<button type="button" class="btn btn-link btn-xs" onclick="funEditGetApikeyInfo(' + row.id + ')">编辑</button>' +
                            '<button type="button" class="btn btn-link btn-xs" onclick="funDeleteApikeyInfo(' + row.id + ')">删除</button>';
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


function funSelectApikeys(sSource, aoData, fnCallback) {
    console.log("========== select apikeys ==========");
    sSource = "/sysCfg.action?flag=apikey";

    var token = $.cookie('userToken');
    aoData.push({name: "token", value: token});
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
 * @param apikeyId
 */
function funEditGetApikeyInfo(apikeyId) {
    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'apikey.detail',
        'token': token,
        'apikeyId': apikeyId
    };

    parent.execAjaxData("/sysCfg.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#editApikeyId').val(response.data.id);
                $('#editApikey').val(response.data.apiKey);
                $('#editClientName').val(response.data.clientName);
                $('#editSecretKey').val(response.data.secretKey);
                $('#editSource').val(response.data.source);
                $('#editDiscription').val(response.data.description);
                $('#editDelete').val(response.data.isDelete);

                $('#formEditTitle').text("编辑ApiKey");
                $('#formEditApikey').modal('show');
            }
        }, function () {
            // complete
        });
}


function editSaveApikeyInfo() {
    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'apikey.edit',
        'token': token,
        'id': $('#editApikeyId').val(),
        'apiKey': $('#editApikey').val(),
        'clientName': $('#editClientName').val(),
        'secretKey': $('#editSecretKey').val(),
        'source': $('#editSource').val(),
        'description': $('#editDiscription').val(),
        'isDelete': $('#editDelete').val()
    };

    parent.execAjaxData("/sysCfg.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
            alert("保存失败。");
        }, function (response) {
            // success
            if (response.code == 0) {
                alert("保存成功。");
                funRefresh();
            } else {
                alert("保存失败。");
            }
        }, function () {
            // complete
            $('#formEditApikey').modal('hide');
        });
}

/**
 * 删除
 * @param apikeyId
 */
function funDeleteApikeyInfo(apikeyId) {
    if (confirm("确定要删除数据吗?")) {
        console.log("delete apikey id:" + apikeyId);

        var token = $.cookie('userToken');
        var jsondata = {
            'op': 'apikey.delete',
            'token': token,
            'apikeyId': apikeyId
        };

        parent.execAjaxData("/sysCfg.action", JSON.stringify(jsondata), true
            , function (response) {
                // error
            }, function (response) {
                // success
                if (response.code == 0) {
                    alert("删除成功。")
                    funRefresh();
                } else {
                    alert("删除失败：" + response.msg);
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
    tableApikey.ajax.reload();
}

/**
 * 新增
 */
function funClickAddRow() {
    console.log(" fnClickAddRow click ");
    // clear
    $('#formEditTitle').text("新增ApiKey");

    $('#editApikeyId').val(null)
    $('#editApikey').val("");
    $('#editClientName').val("");
    $('#editSecretKey').val("");
    $('#editSource').val("");
    $('#editDiscription').val("");
    $('#editDelete').val(0);

    $('#formEditApikey').modal('show');
}
