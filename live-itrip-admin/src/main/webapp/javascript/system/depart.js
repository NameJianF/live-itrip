var tabDepart;

$(function () {
    tabDepart = $('#tabDepart').DataTable({
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true, //这个用来指明是通过服务端来取数据
        "bPaginate": true, // 分页按钮
        "bLengthChange": true, // 改变每页显示数据数量
        "iDisplayLength": 10,// 每页显示行数
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        "fnServerData": funSelectDeparts, // 获取数据的处理函数
        "bFilter": false, // 隐藏筛选框
        "ordering": false,
        'bStateSave': true,
        "aoColumns": [
            {"mData": "id"},
            {"mData": "departName"},
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
                        return '<button type="button" class="btn btn-link btn-xs" onclick="funEditGetDepartInfo(' + row.id + ')">编辑</button>' +
                            '<button type="button" class="btn btn-link btn-xs" onclick="funDeleteDepartInfo(' + row.id + ')">删除</button>';
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


function funSelectDeparts(sSource, aoData, fnCallback) {
    console.log("========== selectDeparts ==========");
    sSource = "/sysCfg.action?flag=depart";

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
 * @param departId
 */
function funEditGetDepartInfo(departId) {
    var jsondata = {
        'op': 'depart.detail',
        'token': parent.token,
        'departId': departId
    };

    parent.execAjaxData("/sysCfg.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#editDepartId').val(response.data.id);
                $('#editDepartName').val(response.data.departName);

                $('#formEditTitle').text("编辑部门");
                $('#formEditDepart').modal('show');
            }
        }, function () {
            // complete
        });
}

function editSaveDepartInfo() {
    var jsondata = {
        'op': 'depart.edit',
        'token': parent.token,
        'id': $('#editDepartId').val(),
        'departName': $('#editDepartName').val()
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
                parent.notifyDanger('保存失败', '');
            }
        }, function () {
            // complete
            $('#formEditDepart').modal('hide');
        });
}

/**
 * 删除
 * @param departId
 */
function funDeleteDepartInfo(departId) {
    if (confirm("确定要删除数据吗?")) {
        console.log("delete depart id:" + departId);

        var jsondata = {
            'op': 'depart.delete',
            'token': parent.token,
            'departId': departId
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
    tabDepart.ajax.reload();//.fnDraw();
}

/**
 * 新增
 */
function funClickAddRow() {
    console.log(" fnClickAddRow click ");
    // clear
    $('#formEditTitle').text("新增部门");

    $('#editDepartId').val(null)
    $('#editDepartName').val("");

    $('#formEditDepart').modal('show');
}

