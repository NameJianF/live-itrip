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
            //{"mData": "priceFavoured"},
            {"mData": "days"},
            {"mData": "type"},
            {"mData": "fromCity"},
            {"mData": "traffic"},
            //{"mData": "startDay"},
            {"mData": "clickCount"},
            {"mData": "joinMans"},
            {
                "mData": "localHtml",
                render: function (data, type, row) {
                    if (data == null) {
                        return "无";
                    }
                    return data;
                }
            },
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
                render: function (data, type, row) {
                    if (type === 'display') {
                        return '<button class="btn btn-link btn-xs" onclick="funEditGetProductInfo(' + row.id + ')">编辑</button>' +
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
    sSource = "/system/view/product.action?flag=list";

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
 * @param productId
 */
function funEditGetProductInfo(dataId) {
    var dataUrl = "/system/view/editProduct.action?id=" + dataId;
    var menuName = "Product:" + dataId;
    parent.$.itriptab.addTabFromOut(dataId, dataUrl, menuName);
}


/**
 * 删除
 * @param productId
 */
function funDeleteProductInfo(productId) {
    if (confirm("确定要删除数据吗?")) {
        console.log("delete Product id:" + productId);

        var jsondata = {
            'op': 'product.delete',
            'token': parent.token,
            'productId': productId
        };

        parent.execAjaxData("/system/view/product.action", JSON.stringify(jsondata), true
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
    tabProducts.ajax.reload();
}

/**
 * 生成静态文件
 * @param productId
 */
function funCreateHtmlFile(productId) {

}
