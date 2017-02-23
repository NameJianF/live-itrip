/**
 * Created by Feng on 2016/11/3.
 */

$(function () {
    $("#footer").load("/view/footer.html");

    initNavbar();
    //基本例子
    initSearchBox();
});

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
                "id": "Status_Basic",
                "title": "产品类型",
                "isMultiple": true,
                "data": [
                    {"value": "0", "text": "跟团游"},
                    {"value": "1", "text": "乡村民宿体验游"},
                    {"value": "2", "text": "自由行"},
                    {"value": "3", "text": "房车之旅"},
                    {"value": "4", "text": "团队游"}
                ]
            },
            {
                "id": "Createor_Basic",
                "title": "景点组合",
                "data": [
                    {"value": "0", "text": "大阪＋东京"},
                    {"value": "1", "text": "大阪＋京都"},
                    {"value": "2", "text": "高山温泉线"},
                    {"value": "3", "text": "东京＋富士山＋京都＋大阪"}
                ]
            },
            {
                "id": "Createor_Basic",
                "title": "行程天数",
                "data": [
                    {"value": "1", "text": "1天"},
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
                    {"value": "0", "text": "0-500元"},
                    {"value": "1", "text": "500-1000元"},
                    {"value": "2", "text": "1000-2000元"},
                    {"value": "3", "text": "2000-3000元"},
                    {"value": "4", "text": "3000-4000元"},
                    {"value": "5", "text": "4000-5000元"},
                    {"value": "6", "text": "5000-10000元"},
                    {"value": "7", "text": "10000+元"}
                ]
            }
        ]
    };
    $("#basic_searchbox").fiterMore(options);
}