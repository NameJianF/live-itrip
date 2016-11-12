/**
 * Created by Feng on 2016/11/12.
 */
var tabProducts;

$(function () {
    console.log("product page loading ...")
    tabProducts = $('#tableProducts').DataTable({
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true, //这个用来指明是通过服务端来取数据
        "bPaginate": true, // 分页按钮
        "bLengthChange": true, // 改变每页显示数据数量
        "iDisplayLength": 10,// 每页显示行数
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        "fnServerData": funSelectProducts, // 获取数据的处理函数
        "bFilter": false, // 隐藏筛选框
        "ordering": false,
        'bStateSave': true,
        "aoColumns": [
            {"mData": "id"},
            {"mData": "title"},
            {"mData": "price"},
            {"mData": "priceFavoured"},
            {"mData": "days"},
            {"mData": "type"},
            {"mData": "fromCity"},
            {"mData": "traffic"},
            {"mData": "startDay"},
            {"mData": "clickCount"},
            {"mData": "joinMans"},
            {"mData": "localHtml"},
            {
                "mData": "status",
                render: function (data, type, row) {
                    if (data == null) {
                        return "";
                    }
                    return data;
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
                        return '<button type="button" class="btn btn-link btn-xs" onclick="funEditGetProductInfo(' + row.id + ')">编辑</button>' +
                            '<button type="button" class="btn btn-link btn-xs" onclick="funDeleteProductInfo(' + row.id + ')">删除</button>' +
                            '<button type="button" class="btn btn-link btn-xs" onclick="funCreateHtmlFile(' + row.id + ')">生成HTML</button>';
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


function funSelectProducts(sSource, aoData, fnCallback) {
    console.log("========== select Products ==========");
    sSource = "/view/product.action?flag=list";

    // 添加查询条件
    //var queryContent = $("#queryContent").val();
    //var querySort = $("#querySort").val();
    //var queryStatus = $("#queryStatus").val();
    //aoData.push({name: "queryContent", value: queryContent});
    //aoData.push({name: "querySort", value: querySort});
    //aoData.push({name: "queryStatus", value: queryStatus});

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
 * @param productId
 */
function funEditGetProductInfo(productId) {
    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'product.eidt',
        'token': token,
        'Productid': Productid
    };

    parent.execAjaxData("/sysCfg.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#editProductId').val(response.data.id);
                $('#editProductName').val(response.data.ProductName);
                $('#editProductParent').val(response.data.parentId);
                $('#editProductUrl').val(response.data.ProductUrl);
                $('#editProductOrder').val(response.data.ProductOrder);
                $('#editProductDiscription').val(response.data.description);
                $('#editProductDelete').val(response.data.isDelete);

                $('#formEditTitle').text("编辑模块");
                $('#formEditProduct').modal('show');
            }
        }, function () {
            // complete
        });
}


function editSaveProductInfo() {
    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'Product.edit',
        'token': token,
        'id': $('#editProductId').val(),
        'ProductName': $('#editProductName').val(),
        'parentId': $('#editProductParent').val(),
        'ProductUrl': $('#editProductUrl').val(),
        'ProductOrder': $('#editProductOrder').val(),
        'description': $('#editProductDiscription').val(),
        'isDelete': $('#editProductDelete').val()
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
            $('#formEditProduct').modal('hide');
        });
}

/**
 * 删除
 * @param Productid
 */
function funDeleteProductInfo(Productid) {
    if (confirm("确定要删除数据吗?")) {
        console.log("delete Product id:" + Productid);

        var token = $.cookie('userToken');
        var jsondata = {
            'op': 'Product.delete',
            'token': token,
            'Productid': Productid
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
    tabProducts.ajax.reload();
}

/**
 * 新增
 */
function funClickAddRow() {
    console.log(" fnClickAddRow click ");
    // clear
    $('#formEditTitle').text("新增模块");

    $('#editProductId').val(null)
    $('#editProductName').val("");
    $('#editProductParent').val("");
    $('#editProductUrl').val("");
    $('#editProductOrder').val("");
    $('#editProductDiscription').val("");
    $('#editProductDelete').val(0);

    $('#formEditProduct').modal('show');
}

/**
 * 生成静态文件
 * @param productId
 */
function funCreateHtmlFile(productId) {

}
