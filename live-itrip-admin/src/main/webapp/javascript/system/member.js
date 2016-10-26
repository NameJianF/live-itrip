var tabUsers;

$(function () {
    tabUsers = $('#tableUsers').DataTable({
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true, //这个用来指明是通过服务端来取数据
        "bPaginate": true, // 分页按钮
        "bLengthChange": true, // 改变每页显示数据数量
        "iDisplayLength": 10,// 每页显示行数
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        "fnServerData": funSelectMembers, // 获取数据的处理函数
        "bFilter": false, // 隐藏筛选框
        "ordering": false,
        'bStateSave': true,
        "aoColumns": [
            {"mData": "id"},
            {"mData": "email"},
            {"mData": "userName"},
            {"mData": "mobile"},
            //{"mData": "departId"},
            {
                "mData": "departName",
                render: function (data, type, row) {
                    if (data == null) {
                        return "未知";
                    } else {
                        return data;
                    }
                }
            },
            //{"mData": "groupId"},
            {
                "mData": "groupName",
                render: function (data, type, row) {
                    if (data == null) {
                        return "未知";
                    } else {
                        return data;
                    }
                }
            },
            {
                "mData": "level",
                render: function (data, type, row) {
                    if (data == 0) {
                        return "员工";
                    } else if (data == 1) {
                        return "访客";
                    } else if (data == 2) {
                        return "VIP";
                    } else {
                        return "未知";
                    }
                }
            },
            {
                "mData": "status",
                render: function (data, type, row) {
                    if (data == 0) {
                        return "刚创建";
                    } else if (data == 1) {
                        return "正常使用";
                    } else if (data == 2) {
                        return "不可用";
                    } else {
                        return "未知";
                    }
                }
            },
            {
                "mData": "identity",
                render: function (data, type, row) {
                    if (data == 0) {
                        return "未认证";
                    } else if (data == 1) {
                        return "手机认证";
                    } else if (data == 2) {
                        return "邮箱认证";
                    } else if (data == 2) {
                        return "微信认证";
                    } else if (data == 2) {
                        return "企业认证";
                    } else {
                        return "未知";
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
                        return '<button type="button" class="btn btn-link btn-xs" onclick="funEditGetMemberInfo(' + row.id + ')">编辑</button>' +
                            '<button type="button" class="btn btn-link btn-xs" onclick="funDeleteMemberInfo(' + row.id + ')">删除</button>';
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


function funSelectMembers(sSource, aoData, fnCallback) {
    console.log("========== selectMembers ==========");
    sSource = "/sysCfg.action?flag=member";

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
 * @param memberId
 */
function funEditGetMemberInfo(memberId) {
    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'member.detail',
        'token': token,
        'memberId': memberId
    };

    parent.execAjaxData("/sysCfg.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#editMemberId').val(response.data.id);
                $('#editMemberEmail').val(response.data.email);
                $('#editMemberName').val(response.data.userName);
                $('#editMemberMobile').val(response.data.mobile);
                $('#editMemberDepart').val(response.data.departId);
                $('#editMemberGroup').val(response.data.groupId);
                $('#editMemberLevel').val(response.data.level);
                $('#editMemberStatus').val(response.data.status);
                $('#editMemberIdentity').val(response.data.identity);

                $('#formEditTitle').text("编辑用户");
                $('#formEditMember').modal('show');
            }
        }, function () {
            // complete
        });
}

// 部门选择
function departChangeEvent(event, flag) {
    //alert('You like ' + event.target.value + ' ice cream.');
    if (flag == 1) {
        $('#selectGroup').empty();
    } else {
        $('#editMemberGroup').empty();
    }

    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'group.selectGroupsByDepartId',
        'token': token,
        'departId': event.target.value
    };
    parent.execAjaxData("/sysCfg.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                if (flag == 1) {
                    var ops = "<option value=\"0\">全部</option>";
                    var jsonarray = eval(response.data);
                    for (var i = 0; i < jsonarray.length; i++) {
                        ops += '<option value="' + jsonarray[i].groupId + '">' + jsonarray[i].groupName + '</option>';
                    }
                    $('#selectGroup').append(ops);
                } else {
                    var ops = "";
                    var jsonarray = eval(response.data);
                    for (var i = 0; i < jsonarray.length; i++) {
                        ops += '<option value="' + jsonarray[i].groupId + '">' + jsonarray[i].groupName + '</option>';
                    }
                    $('#editMemberGroup').append(ops);
                }
            }
        }, function () {
            // complete
        });
}

function editSaveMemberInfo() {
    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'member.edit',
        'token': token,
        'id': $('#editMemberId').val(),
        'userName': $('#editMemberName').val(),
        'email': $('#editMemberEmail').val(),
        'mobile': $('#editMemberMobile').val(),
        'departId': $('#editMemberDepart').val(),
        'departName': $("#editMemberDepart").find("option:selected").text(),
        'groupId': $('#editMemberGroup').val(),
        'groupName': $('#editMemberGroup').find("option:selected").text(),
        'level': $('#editMemberLevel').val(),
        'status': $('#editMemberStatus').val(),
        'identity': $('#editMemberIdentity').val()
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
            $('#formEditMember').modal('hide');
        });
}

/**
 * 删除
 * @param memberId
 */
function funDeleteMemberInfo(memberId) {
    if (confirm("确定要删除数据吗?")) {
        console.log("delete member id:" + memberId);

        var token = $.cookie('userToken');
        var jsondata = {
            'op': 'member.delete',
            'token': token,
            'memberId': memberId
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
    tabUsers.ajax.reload();
}

/**
 * 新增
 */
function funClickAddRow() {
    console.log(" fnClickAddRow click ");
    // clear
    $('#formEditTitle').text("新增用户");

    $('#editMemberId').val(null)
    $('#editMemberEmail').val("");
    $('#editMemberName').val("");
    $('#editMemberMobile').val("");
    $('#editMemberDepart').val(0);
    $('#editMemberLevel').val(0);
    $('#editMemberStatus').val(0);
    $('#editMemberIdentity').val(0);

    $('#formEditMember').modal('show');
}
