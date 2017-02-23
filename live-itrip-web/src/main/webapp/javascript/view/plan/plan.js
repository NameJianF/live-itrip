/**
 * Created by Feng on 2016/11/3.
 */

$(function () {
    $("#footer").load("/view/footer.html");
    $("#divCarousel").removeClass('landing-page');

    initNavbar();

    initHoverItems();

    //基本例子
    initSearchBox();
});

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


// 初始化查询控件
function initSearchBox() {
    var options = {
        //查询事件
        "search": function (paramList) {
            console.log('查询参数:' + JSON.stringify(paramList));
        },
        //默认展开条件数
        "expandRow": 2,
        //查询条件
        "searchBoxs": [
            {
                "id": "Createor_Basic",
                "title": "相关城市",
                "data": [
                    {"value": "0", "text": "东京"},
                    {"value": "1", "text": "京都"},
                    {"value": "2", "text": "大阪"},
                    {"value": "3", "text": "北海道"}
                ]
            },
            {
                "id": "Createor_Basic",
                "title": "行程天数",
                "data": [
                    {"value": "3", "text": "3天"},
                    {"value": "4", "text": "4天"},
                    {"value": "5", "text": "5天"},
                    {"value": "6", "text": "6天"},
                    {"value": "7", "text": "7天"},
                    {"value": "8", "text": "8天"},
                    {"value": "9", "text": "9天"}
                ]
            },
            {
                "id": "Createor_Basic",
                "title": "价格区间",
                "data": [
                    {"value": "0", "text": "1000-5000元"},
                    {"value": "1", "text": "5001-8000元"},
                    {"value": "2", "text": "8001-10000元"},
                    {"value": "3", "text": "10000+元"}
                ]
            }
        ]
    };
    $("#basic_searchbox").fiterMore(options);
}

