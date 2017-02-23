var tabCityInfo;

$(function () {
    tabCityInfo = $('#tableCitys').DataTable({
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true, //这个用来指明是通过服务端来取数据
        "bPaginate": true, // 分页按钮
        "bLengthChange": true, // 改变每页显示数据数量
        "iDisplayLength": 10,// 每页显示行数
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        "fnServerData": funSelectCityInfos, // 获取数据的处理函数
        "bFilter": false, // 隐藏筛选框
        "ordering": false,
        'bStateSave': true,
        "aoColumns": [
            {"mData": "id"},
            {"mData": "cityName"},
            {"mData": "cityArea"},
            {"mData": "cityTitle"},
            //{"mData": "cityContent"},
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
                        return '<button type="button" class="btn btn-link btn-xs" onclick="funEditGetCityInfo(' + row.id + ')">编辑</button>' +
                            '<button type="button" class="btn btn-link btn-xs" onclick="funDeleteCityInfo(' + row.id + ')">删除</button>';
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

    $('#editCityInfoContent').summernote({
        //height: 200,
        onImageUpload: function (files, editor, welEditable) {
            for (var i = files.length - 1; i >= 0; i--) {
                sendFile(files[i], editor, welEditable);
            }
        }
    });
});

//图片上传
function sendFile(file, editor, welEditable) {
    var formData = new FormData();
    formData.append("file", file);
    formData.append('cityId', '100');
    //formData.append('imgFlag', imgFlag);

    $.ajax({
        data: formData,
        type: "POST",
        url: "/file/upload.action?flag=1",
        cache: false,
        contentType: false,
        processData: false,
        success: function (response) {
            var obj = jQuery.parseJSON(response)
            editor.insertImage(welEditable, obj.data.fileUrl);
        }
    });
}


function funSelectCityInfos(sSource, aoData, fnCallback) {
    console.log("========== selectCityInfos ==========");
    sSource = "/system/view/citys.action?flag=list";

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
 * @param cityId
 */
function funEditGetCityInfo(cityId) {
    var jsondata = {
        'op': 'citys.detail',
        'token': parent.token,
        'cityId': cityId
    };

    parent.execAjaxData("/system/view/citys.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#editCityInfoId').val(response.data.id);
                $('#editCityInfoName').val(response.data.cityName);
                $('#editCityInfoArea').val(response.data.cityArea);
                $('#editCityInfoTitle').val(response.data.cityTitle);
                $('#editCityInfoContent').code(response.data.cityContent);

                $('#formEditTitle').text("信息编辑");
                $('#formEditCityInfo').modal('show');
            }
        }, function () {
            // complete
        });
}


function editSaveCityInfo() {
    var markupStr = $('#editCityInfoContent').code();
    var jsondata = {
        'op': 'citys.edit',
        'token': parent.token,
        'id': $('#editCityInfoId').val(),
        'cityName': $('#editCityInfoName').val(),
        'cityArea': $('#editCityInfoArea').val(),
        'cityTitle': $('#editCityInfoTitle').val(),
        'cityContent': markupStr
    };

    parent.execAjaxData("/system/view/citys.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
            parent.notifyDanger('保存失败', response.msg);
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
            $('#formEditCityInfo').modal('hide');
        });
}

/**
 * 删除
 * @param cityId
 */
function funDeleteCityInfo(cityId) {
    if (confirm("确定要删除数据吗?")) {
        console.log("delete CityInfo id:" + cityId);

        var jsondata = {
            'op': 'citys.delete',
            'token': parent.token,
            'cityId': cityId
        };

        parent.execAjaxData("/system/view/citys.action", JSON.stringify(jsondata), true
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
    tabCityInfo.ajax.reload();
}

/**
 * 新增
 */
function funClickAddRow() {
    console.log(" fnClickAddRow click ");
    // clear
    $('#formEditTitle').text("新增信息");

    $('#editCityInfoId').val(null);
    $('#editCityInfoName').val(null);
    $('#editCityInfoArea').val("");
    $('#editCityInfoTitle').val("");
    $('#editCityInfoContent').code('');

    $('#formEditCityInfo').modal('show');
}
