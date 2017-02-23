var tabDict;

$(function () {
    tabDict = $('#tabDict').DataTable({
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true, //这个用来指明是通过服务端来取数据
        "bPaginate": true, // 分页按钮
        "bLengthChange": true, // 改变每页显示数据数量
        "iDisplayLength": 10,// 每页显示行数
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        "fnServerData": funSelectDicts, // 获取数据的处理函数
        "bFilter": false, // 隐藏筛选框
        "ordering": false,
        'bStateSave': true,
        "aoColumns": [
            {"mData": "id"},
            {"mData": "dictName"},
            {"mData": "dictText"},
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
                        return '<button type="button" class="btn btn-link btn-xs" onclick="funEditGetDictInfo(' + row.id + ')">编辑</button>' +
                            '<button type="button" class="btn btn-link btn-xs" onclick="funDeleteDictInfo(' + row.id + ')">删除</button>';
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


function funSelectDicts(sSource, aoData, fnCallback) {
    console.log("========== selectDicts ==========");
    sSource = "/sysCfg.action?flag=dict";
    var queryContent = $("#dictText").val();
    aoData.push({name: "queryContent", value: queryContent});
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
 * @param dictid
 */
function funEditGetDictInfo(dictid) {
    var jsondata = {
        'op': 'dict.detail',
        'token': parent.token,
        'dictid': dictid
    };

    parent.execAjaxData("/sysCfg.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#editDictId').val(response.data.id);
                $('#editDictName').val(response.data.dictName);
                $('#editDictText').val(response.data.dictText);
                $('#editDictDelete').val(response.data.isDelete);

                $('#formEditTitle').text("编辑字典");
                $('#formEditDict').modal('show');
            }
        }, function () {
            // complete
        });
}

function editSaveDictInfo() {
    var jsondata = {
        'op': 'dict.edit',
        'token': parent.token,
        'id': $('#editDictId').val(),
        'dictName': $('#editDictName').val(),
        'dictText': $('#editDictText').val(),
        'isDelete': $('#editDictDelete').val()
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
            $('#formEditDict').modal('hide');
        });
}

/**
 * 删除
 * @param dictid
 */
function funDeleteDictInfo(dictid) {
    if (confirm("确定要删除数据吗?")) {
        console.log("delete dict id:" + dictid);

        var jsondata = {
            'op': 'dict.delete',
            'token': parent.token,
            'dictid': dictid
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
    tabDict.ajax.reload();//.fnDraw();
}

/**
 * 新增
 */
function funClickAddRow() {
    console.log(" fnClickAddRow click ");
    // clear
    $('#formEditTitle').text("新增字典");

    $('#editDictId').val(null)
    $('#editDictName').val("");
    $('#editDictText').val("");
    $('#editDictDelete').val(0);

    $('#formEditDict').modal('show');
}

