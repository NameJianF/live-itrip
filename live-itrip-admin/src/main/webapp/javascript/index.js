/**
 * Created by Feng on 2016/7/19.
 */

// 加载菜单
function loadModules() {
    console.log("load menu ........");
    // from cookie
    var token = $.cookie('userToken');

    var jsondata = {
        'op': 'module.select',
        'token': token
    };

    execAjaxData("/userInfo.action", JSON.stringify(jsondata), false,
        function (response) {
            // error
        },
        function (response) {
            // sucess
            if (response.code == 0) {
                var strmenu = "";
                var jsonarray = eval(response.data);
                for (var i = 0; i < jsonarray.length; i++) {
                    var moduleName = jsonarray[i].moduleName;
                    var parentid = jsonarray[i].parentId;
                    var id = jsonarray[i].id;

                    // 一级菜单
                    if (parentid == 0) {
                        strmenu += '<li class>';
                        strmenu += '<a data-id="' + id + '"><i class="' + jsonarray[i].moduleIcon + '"></i><span class="nav-label">' + moduleName + '</span>' +
                            '<span class="fa arrow"></span>' +
                            '</a>';

                        if (jsonarray.length > 0) {
                            strmenu += '<ul class=\"nav nav-second-level collapse\" style="height: 0px;" ">';
                            for (var j = 0; j < jsonarray.length; j++) {
                                // 二级菜单
                                if (id == jsonarray[j].parentId) {
                                    var moduleurl = jsonarray[j].moduleUrl;
                                    if (moduleurl != null) {
                                        strmenu += '<li>';
                                        strmenu += '<a class="menuItem" data-id="' + jsonarray[j].id + '" href="' + moduleurl + '" data-index="' + jsonarray[j].moduleOrder + '">' +
                                            '<i class="' + jsonarray[j].moduleIcon + '"></i>' + jsonarray[j].moduleName + '</a>';
                                        strmenu += '</li>';
                                    }
                                }
                            }
                            strmenu += '</ul>';
                        }
                        strmenu += '</li>';
                    }
                }
                $('#side-menu').append(strmenu);
            } else {
                alert(response.message)
            }
        },
        function () {
            // complete
        });
}