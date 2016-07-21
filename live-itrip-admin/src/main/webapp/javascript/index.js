/**
 * Created by Feng on 2016/7/19.
 */


function loadModules() {

    // from cookie
    var token = $.cookie('userToken');

    console.log("token:" + token)
    
    var jsondata = {
        'op': 'module.select',
        'token': token
    };

    execAjaxData("/user.action", JSON.stringify(jsondata), true,
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
                        strmenu += "<li>";
                        strmenu += "<a href=\"#\">";
                        strmenu += "<i class=\"fa fa fa-bar-chart-o\"></i>";
                        strmenu += "<span class=\"nav-label\">" + moduleName + "</span>";
                        strmenu += "<span class=\"fa arrow\"></span>";
                        strmenu += "</a>";
                        strmenu += "<ul class=\"nav nav-second-level\">";

                        for (var j = 0; j < jsonarray.length; j++) {
                            // 二级菜单
                            if (id == jsonarray[j].parentId) {
                                var moduleurl = jsonarray[j].moduleUrl;
                                if (moduleurl != null) {
                                    strmenu += "<li><a class=\"J_menuItem\" href=\"" + moduleurl + "\">" + jsonarray[j].moduleName + "</a></li>";
                                }
                            }
                        }
                        strmenu += "</ul>";
                        strmenu += "</li>";
                    }
                }
                $("#side-menu").append(strmenu);

            } else {
                alert(response.message)
            }
        },
        function () {
            // complete
        });
}