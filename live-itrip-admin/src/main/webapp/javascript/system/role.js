var tabRole;

$(function () {
    tabRole = $('#tabRole').DataTable({
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true, //这个用来指明是通过服务端来取数据
        "bPaginate": true, // 分页按钮
        "bLengthChange": true, // 改变每页显示数据数量
        "iDisplayLength": 10,// 每页显示行数
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        "fnServerData": funSelectRoles, // 获取数据的处理函数
        "bFilter": false, // 隐藏筛选框
        "ordering": false,
        'bStateSave': true,
        "aoColumns": [
            {"mData": "id"},
            {"mData": "roleName"},
            {"mData": "roleText"},
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
                        return '<button type="button" class="btn btn-link btn-xs" onclick="funEditGetRoleInfo(' + row.id + ')">编辑</button>' +
                            '<button type="button" class="btn btn-link btn-xs" onclick="funDeleteRoleInfo(' + row.id + ')">删除</button>';
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


function funSelectRoles(sSource, aoData, fnCallback) {
    console.log("========== selectRoles ==========");
    sSource = "/sysCfg.action?flag=role";

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
 * @param roleId
 */
function funEditGetRoleInfo(roleId) {
    var jsondata = {
        'op': 'role.detail',
        'token': parent.token,
        'roleId': roleId
    };

    parent.execAjaxData("/sysCfg.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#editRoleId').val(response.data.id);
                $('#editRoleName').val(response.data.roleName);
                $('#editRoleText').val(response.data.roleText);

                $('#formEditTitle').text("编辑角色");
                $('#formEditRole').modal('show');
            }
        }, function () {
            // complete
        });
}

function editSaveRoleInfo() {
    var jsondata = {
        'op': 'role.edit',
        'token': parent.token,
        'id': $('#editRoleId').val(),
        'roleName': $('#editRoleName').val(),
        'roleText': $('#editRoleText').val()
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
 * @param roleId
 */
function funDeleteRoleInfo(roleId) {
    if (confirm("确定要删除数据吗?")) {
        console.log("delete role id:" + roleId);
        var jsondata = {
            'op': 'role.delete',
            'token': parent.token,
            'roleId': roleId
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
    tabRole.ajax.reload();
}

/**
 * 新增
 */
function funClickAddRow() {
    console.log(" fnClickAddRow click ");
    // clear
    $('#formEditTitle').text("新增角色");

    $('#editRoleId').val(null)
    $('#editRoleName').val("");
    $('#editRoleText').val("");

    $('#formEditRole').modal('show');
}

