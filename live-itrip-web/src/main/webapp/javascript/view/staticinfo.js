var tabStaticInfo;

$(function () {
    tabStaticInfo = $('#tableStaticInfos').DataTable({
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true, //这个用来指明是通过服务端来取数据
        "bPaginate": true, // 分页按钮
        "bLengthChange": true, // 改变每页显示数据数量
        "iDisplayLength": 10,// 每页显示行数
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        "fnServerData": funSelectStaticInfos, // 获取数据的处理函数
        "bFilter": false, // 隐藏筛选框
        "ordering": false,
        'bStateSave': true,
        "aoColumns": [
            {"mData": "id"},
            {"mData": "type"},
            {"mData": "title"},
            {"mData": "content"},
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
                        return '<button type="button" class="btn btn-link btn-xs" onclick="funEditGetStaticInfo(' + row.id + ')">编辑</button>' +
                            '<button type="button" class="btn btn-link btn-xs" onclick="funDeleteStaticInfo(' + row.id + ')">删除</button>';
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

    $('#editStaticInfoContent').summernote();
});


function funSelectStaticInfos(sSource, aoData, fnCallback) {
    console.log("========== selectStaticInfos ==========");
    sSource = "/system/view/staticInfo.action?flag=list";

    // 添加查询条件
    var queryContent = $("#queryContent").val();
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
 * @param infoId
 */
function funEditGetStaticInfo(infoId) {
    var jsondata = {
        'op': 'staticInfo.detail',
        'token': parent.token,
        'infoId': infoId
    };

    parent.execAjaxData("/system/view/staticInfo.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#editStaticInfoId').val(response.data.id);
                $('#editStaticInfoType').val(response.data.type);
                $('#editStaticInfoTitle').val(response.data.title);
                $('#editStaticInfoContent').code(response.data.content);

                $('#formEditTitle').text("信息编辑");
                $('#formEditStaticInfo').modal('show');
            }
        }, function () {
            // complete
        });
}


function editSaveStaticInfo() {
    var markupStr = $('#editStaticInfoContent').code();
    var jsondata = {
        'op': 'staticInfo.edit',
        'token': parent.token,
        'id': $('#editStaticInfoId').val(),
        'type': $('#editStaticInfoType').val(),
        'title': $('#editStaticInfoTitle').val(),
        'content': markupStr
    };

    parent.execAjaxData("/system/view/staticInfo.action", JSON.stringify(jsondata), true
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
            $('#formEditStaticInfo').modal('hide');
        });
}

/**
 * 删除
 * @param infoId
 */
function funDeleteStaticInfo(infoId) {
    if (confirm("确定要删除数据吗?")) {
        console.log("delete staticInfo id:" + infoId);
        var jsondata = {
            'op': 'staticInfo.delete',
            'token': parent.token,
            'infoId': infoId
        };

        parent.execAjaxData("/system/view/staticInfo.action", JSON.stringify(jsondata), true
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
    tabStaticInfo.ajax.reload();
}

/**
 * 新增
 */
function funClickAddRow() {
    console.log(" fnClickAddRow click ");
    // clear
    $('#formEditTitle').text("新增信息");

    $('#editStaticInfoId').val(null);
    $('#editStaticInfoType').val(null);
    $('#editStaticInfoTitle').val("");
    $('#editStaticInfoContent').code('');

    $('#formEditStaticInfo').modal('show');
}
