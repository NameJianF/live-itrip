var tabDictItem;

$(function () {
    tabDictItem = $('#tabDictItem').DataTable({
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true, //这个用来指明是通过服务端来取数据
        "bPaginate": true, // 分页按钮
        "bLengthChange": true, // 改变每页显示数据数量
        "iDisplayLength": 10,// 每页显示行数
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        "fnServerData": funSelectDictItems, // 获取数据的处理函数
        "bFilter": false, // 隐藏筛选框
        "ordering": false,
        'bStateSave': true,
        "aoColumns": [
            {"mData": "id"},
            {"mData": "dictItemName"},
            {"mData": "dictItemText"},
            {"mData": "dictName"},
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
                        return '<button type="button" class="btn btn-link btn-xs" onclick="funEditGetDcitItemInfo(' + row.id + ')">编辑</button>' +
                            '<button type="button" class="btn btn-link btn-xs" onclick="funDeleteDictItemInfo(' + row.id + ')">删除</button>';
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


function funSelectDictItems(sSource, aoData, fnCallback) {
    console.log("========== selectDictItems ==========");
    sSource = "/sysCfg.action?flag=dictItem";

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
 * @param dictid
 */
function funEditGetDcitItemInfo(dictItemId) {
    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'dictItem.detail',
        'token': token,
        'dictItemId': dictItemId
    };

    parent.execAjaxData("/sysCfg.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#editDictItemId').val(response.data.id);
                $('#editDictItemName').val(response.data.dictName);
                $('#editDictItemText').val(response.data.dictText);
                $('#editDictName').val(response.data.dictId);
                $('#editDictItemDelete').val(response.data.isDelete);

                $('#formEditTitle').text("编辑模块");
                $('#formEditDictItem').modal('show');
            }
        }, function () {
            // complete
        });
}

function editSaveDictItemInfo() {
    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'dictItem.edit',
        'token': token,
        'id': $('#editDictItemId').val(),
        'dictItemName': $('#editDictItemName').val(),
        'dictItemText': $('#editDictItemText').val(),
        'dictId': $('#editDictName').val(),
        'isDelete': $('#editDictItemDelete').val()
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
            $('#formEditDictItem').modal('hide');
        });
}

/**
 * 删除
 * @param dictid
 */
function funDeleteDictItemInfo(dictItemId) {
    if (confirm("确定要删除数据吗?")) {
        console.log("delete dict item id:" + dictItemId);

        var token = $.cookie('userToken');
        var jsondata = {
            'op': 'dict.delete',
            'token': token,
            'dictItemId': dictItemId
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
    tabDictItem.ajax.reload();
}

/**
 * 新增
 */
function funClickAddRow() {
    console.log(" fnClickAddRow click ");
    // clear
    $('#formEditTitle').text("新增字典子项");

    $('#editDictItemId').val(null)
    $('#editDictItemName').val("");
    $('#editDictItemText').val("");
    $('#editDictName').val(null);
    $('#editDictItemDelete').val(0);

    $('#formEditDictItem').modal('show');
}
