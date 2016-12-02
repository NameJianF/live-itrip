/**
 * Created by Feng on 2016/11/29.
 */


$(function () {
    //$("#divNavbar").load("/view/navbar.html");
    $("#footer").load("/view/footer.html");
    initNavbar();
    loadDatas();

    initHoverItems();
});


function loadDatas() {
    // 加载 温泉产品
    loadProducts($('#productList1'), 1);
    // 加载 滑雪之行
    loadProducts($('#productList2'), 2);
    // 加载 海岛旅游
    loadProducts($('#productList3'), 3);
    // 加载 快乐家族
    loadProducts($('#productList4'), 4);
    // 加载 见学体验
    loadProducts($('#productList5'), 5);
    // 加载 健康检查
    loadProducts($('#productList6'), 6);
}

function loadProducts(ele, flag) {
    var jsondata = {
        'op': 'product.selectProductList',
        'flag': flag
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

function initHoverItems() {
    //移动像素的图像
    var move = -15;
    //缩放比例，1.2 =120％
    var zoom = 1.01;
    //在对这些缩略图的鼠标滑过事件
    $('.itemHover').hover(function () {
        //根据缩放百分比设置宽度和高度
        var width = $('.itemHover').width() * zoom;
        var height = $('.itemHover').height() * zoom;
        //移动和缩放图像
        $(this).find('img').stop(false, true).animate({
            'width': width,
            'height': height,
            'top': move,
            'left': move
        }, {duration: 200});
    }, function () {
        //复位图像
        $(this).find('img').stop(false, true).animate({
            'width': $('.itemHover').width(),
            'height': $('.itemHover').height(),
            'top': '0',
            'left': '0'
        }, {duration: 100});
    });
}
