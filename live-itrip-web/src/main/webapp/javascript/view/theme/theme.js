/**
 * Created by Feng on 2016/11/29.
 */


$(function () {
    //$("#divNavbar").load("/view/navbar.html");
    $("#footer").load("/view/footer.html");
    initNavbar();



    loadDatas();

    initSearchBox();
});


function loadDatas() {
    // 加载全部产品
    loadProducts($('#productList'), "");
}

function loadProducts(ele, params) {
    var jsondata = {
        'op': 'product.selectProductList',
        'params': params
    };

    execAjaxDataForView("/view/product.action", JSON.stringify(jsondata), false
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                var content = "";
                var jsonarray = eval(response.data);
                for (var i = 0; i < jsonarray.length; i++) {
                    var item = jsonarray[i];
                    content += '<div class="col-md-3">';
                    content += '<div class="ibox">';
                    content += '<div class="ibox-content product-box">';
                    content += '<div class="product-imitation" style="padding: 1px">';
                    content += ' <a href="/view/product.action?pid=' + item.id + '">';
                    content += '<img style="max-width:100%;height:auto;" src="' + item.imgSmall + '">';
                    content += '</a>';
                    content += '</div>';
                    content += ' <div class="product-desc" style="text-align: center;padding: 10px;">';
                    content += '<span class="product-price">';
                    content += '&yen;' + item.price;
                    content += '</span>';
                    content += '<a href="/view/product.action?pid=' + item.id + '" class="product-name">' + item.title + '</a>';
                    content += '</div>';
                    content += '</div>';
                    content += '</div>';
                    content += '</div>';
                }
                console.log(content);
                ele.html(content);
            }
        }, function () {
            // complete
        });
}


// 初始化查询控件
function initSearchBox() {
    var options = {
        //查询事件
        "search": function (paramList) {
            console.log('查询参数:' + JSON.stringify(paramList));
            loadProducts($('#productList'), JSON.stringify(paramList));
        },
        //默认展开条件数
        "expandRow": 2,
        //查询条件
        "searchBoxs": [
            {
                "id": "theme",
                "title": "主题",
                "isMultiple": false,
                "data": [
                    {"value": "温泉旅游", "text": "温泉旅游"},
                    {"value": "滑雪之行", "text": "滑雪之行"},
                    {"value": "海岛旅游", "text": "海岛旅游"},
                    {"value": "快乐家族", "text": "快乐家族"},
                    {"value": "见学体验", "text": "见学体验"},
                    {"value": "健康检查", "text": "健康检查"}
                ]
            }
            ,
            {
                "id": "citys",
                "title": "城市",
                "data": [
                    {"value": "东京", "text": "东京"},
                    {"value": "大阪", "text": "大阪"},
                    {"value": "京都", "text": "京都"},
                    {"value": "北海道", "text": "北海道"}
                ]
            }
        ]
    };
    $("#searchbox").fiterMore(options);
}