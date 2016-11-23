/**
 * Created by Feng on 2016/11/12.
 */
var tabCustomer;

$(function () {
    console.log("customer order page loading ...")
    tabCustomer = $('#tableCustomers').DataTable({
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true, //这个用来指明是通过服务端来取数据
        "bPaginate": true, // 分页按钮
        "bLengthChange": true, // 改变每页显示数据数量
        "iDisplayLength": 10,// 每页显示行数
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        "fnServerData": funSelectCustomers, // 获取数据的处理函数
        "bFilter": false, // 隐藏筛选框
        "ordering": false,
        'bStateSave': true,
        "aoColumns": [
            {"mData": "id"},
            {"mData": "cusName"},
            {"mData": "links"},
            {"mData": "days"},
            {"mData": "planDate"},
            {
                "mData": "remarks",
                render: function (data, type, row) {
                    if (data == null) {
                        return "";
                    }
                    return data;
                }
            },
            {
                "mData": "status",
                render: function (data, type, row) {
                    if (data == 0) {
                        return "初始";
                    } else if (data == 1) {
                        return "未成单";
                    } else if (data == 2) {
                        return "成单";
                    }
                    return "";
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
                        return '<button type="button" class="btn btn-link btn-xs" onclick="funEditGetCustomerInfo(' + row.id + ')">编辑</button>' +
                            '<button type="button" class="btn btn-link btn-xs" onclick="funDeleteCustomerInfo(' + row.id + ')">删除</button>'
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


function funSelectCustomers(sSource, aoData, fnCallback) {
    console.log("========== select customer ==========");
    sSource = "/system/view/customer.action?flag=list";

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
 * @param orderId
 */
function funEditGetCustomerInfo(orderId) {
    var jsondata = {
        'op': 'customer.detail',
        'token': parent.token,
        'orderId': orderId
    };

    parent.execAjaxData("/system/view/customer.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#editCustomerId').val(response.data.id);
                $('#editCustomerName').val(response.data.cusName);
                $('#editCustomerLink').val(response.data.links);
                $('#editCustomerDays').val(response.data.days);
                $('#editCustomerDate').val(response.data.planDate);
                $('#editCustomerRemarks').val(response.data.remarks);
                $('#editCustomerStatus').val(response.data.status);

                $('#formEditTitle').text("信息编辑");
                $('#formEditCustomer').modal('show');
            }
        }, function () {
            // complete
        });
}


function editSaveCustomerInfo() {
    var jsondata = {
        'op': 'customer.edit',
        'token': parent.token,
        'id': $('#editCustomerId').val(),
        'status': $('#editCustomerStatus').val()
    };

    parent.execAjaxData("/system/view/customer.action", JSON.stringify(jsondata), true
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
            $('#formEditOrder').modal('hide');
        });
}


/**
 * 删除
 * @param orderId
 */
function funDeleteCustomerInfo(orderId) {
    if (confirm("确定要删除数据吗?")) {
        console.log("delete Product id:" + orderId);

        var jsondata = {
            'op': 'customer.delete',
            'token': parent.token,
            'orderId': orderId
        };

        parent.execAjaxData("/system/view/customer.action", JSON.stringify(jsondata), true
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
    tabCustomer.ajax.reload();
}


