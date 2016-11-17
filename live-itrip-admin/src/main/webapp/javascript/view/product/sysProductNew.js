/**
 * Created by Feng on 2016/11/12.
 */

var myDropzone;
var imgFlag = 'small';
var tabPlanDetails;

$(function () {
    initDataTable();

    $('#productSpecialty').summernote();
    $('#productCost').summernote();
    $('#productReserve').summernote();
    $('#productNotice').summernote();
    $('#planContent').summernote();

    $('#productStartDate').datepicker({
        language: "zh-CN",
        autoclose: true,
        clearBtn: false,
        todayBtn: true,
        format: "yyyy-mm-dd"
    });

    Dropzone.options.myAwesomeDropzone = {
        url: "/file/upload.action?flag=0",
        autoProcessQueue: false,
        uploadMultiple: true,
        parallelUploads: 100,
        maxFiles: 1,

        // Dropzone settings
        init: function () {
            myDropzone = this;

            $('#btnUploadFile').click(function (e) {
                e.preventDefault();
                e.stopPropagation();
                myDropzone.processQueue();
            });
            this.on('sendingmultiple', function (file, xhr, formData) {
                formData.append('productId', $('#productId').val());
                formData.append('imgFlag', imgFlag);
            });
            this.on("sendingmultiple", function () {
            });
            this.on("successmultiple", function (files, message) {
                //console.log('successmultiple' + message);
                var obj = jQuery.parseJSON(message)
                if (imgFlag == 'small') {
                    $('#productImgSamll').val(obj.data.fileUrl);
                    $('#productImgSamllId').val(obj.data.fileId);
                } else if (imgFlag == 'big') {
                    $('#productImgBig').val(obj.data.fileUrl);
                    $('#productImgBigId').val(obj.data.fileId);
                }
            });
            this.on("errormultiple", function (files, message) {
                alert(message);
            });

            this.on("addedfile", function (file) {

                // Create the remove button
                var removeButton = Dropzone.createElement("<button>删除</button>");

                // Capture the Dropzone instance as closure.
                var _this = this;

                // Listen to the click event
                removeButton.addEventListener("click", function (e) {
                    // Make sure the button click doesn't submit the form:
                    e.preventDefault();
                    e.stopPropagation();
                    // Remove the file preview.
                    _this.removeFile(file);
                    // If you want to the delete the file on the server as well,
                    // you can do the AJAX request here.
                });

                // Add the button to the file preview element.
                file.previewElement.appendChild(removeButton);
            });
        }
    };
});

function initDataTable() {
    tabPlanDetails = $('#tablePlanDetails').DataTable({
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true, //这个用来指明是通过服务端来取数据
        "bPaginate": true, // 分页按钮
        "bLengthChange": true, // 改变每页显示数据数量
        "iDisplayLength": 10,// 每页显示行数
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        "fnServerData": funSelectPlanDetails, // 获取数据的处理函数
        "bFilter": false, // 隐藏筛选框
        "ordering": false,
        'bStateSave': true,
        "aoColumns": [
            {"mData": "id"},
            {"mData": "title"},
            {"mData": "stationFrom"},
            {"mData": "traffic"},
            {"mData": "stationTo"},

            {"mData": "breakfast"},
            {"mData": "lunch"},
            {"mData": "dinner"},
            {"mData": "hotel"},
            {
                render: function (data, type, row) {
                    if (type === 'display') {
                        return '<button type="button" class="btn btn-link btn-xs" onclick="editPlanDetail(' + row.id + ')">编辑</button>' +
                            '<button type="button" class="btn btn-link btn-xs" onclick="deletePlanInfo(' + row.id + ')">删除</button>';
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
}


function costSelectChange() {
    //console.log('costSelectChange:' + $('#selectCost').val());
    var infoId = $('#selectCost').val();
    if (infoId != '') {
        var token = $.cookie('userToken');
        var jsondata = {
            'op': 'staticInfo.detail',
            'token': token,
            'infoId': infoId
        };

        parent.execAjaxData("/view/staticInfo.action", JSON.stringify(jsondata), true
            , function (response) {
                // error
            }, function (response) {
                // success
                if (response.code == 0) {
                    $('#productCost').code(response.data.content);
                }
            }, function () {
                // complete
            });
    }
}

function reservesSelectChange() {
    var infoId = $('#selectReserves').val();
    if (infoId != '') {
        var token = $.cookie('userToken');
        var jsondata = {
            'op': 'staticInfo.detail',
            'token': token,
            'infoId': infoId
        };

        parent.execAjaxData("/view/staticInfo.action", JSON.stringify(jsondata), true
            , function (response) {
                // error
            }, function (response) {
                // success
                if (response.code == 0) {
                    $('#productReserve').code(response.data.content);
                }
            }, function () {
                // complete
            });
    }
}

function noticeSelectChange() {
    var infoId = $('#selectNotice').val();
    if (infoId != '') {
        var token = $.cookie('userToken');
        var jsondata = {
            'op': 'staticInfo.detail',
            'token': token,
            'infoId': infoId
        };

        parent.execAjaxData("/view/staticInfo.action", JSON.stringify(jsondata), true
            , function (response) {
                // error
            }, function (response) {
                // success
                if (response.code == 0) {
                    $('#productNotice').code(response.data.content);
                }
            }, function () {
                // complete
            });
    }
}

// 保存基本信息
function saveProductBaseInfo() {
    var productId = $('#productId').val();
    var productTitle = $('#productTitle').val();
    var productPrice = $('#productPrice').val();
    var priceFavoured = $('#priceFavoured').val();
    var productType = $('#productType').find("option:selected").text();
    var productDays = $('#productDays').find("option:selected").text();
    var productFromCity = $('#productFromCity').find("option:selected").text();
    var productTraffic = $('#productTraffic').find("option:selected").text();
    var productStartDate = $('#productStartDate').val();

    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'product.edit',
        'token': token,
        'id': productId,
        'title': productTitle,
        'price': productPrice,
        'priceFavoured': priceFavoured,
        'type': productType,
        'days': productDays.replace('天', ''),
        'fromCity': productFromCity,
        'traffic': productTraffic,
        'startDay': productStartDate
    };

    parent.execAjaxData("/view/product.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#productId').val(response.data.id);
                alert('保存成功');
            }
        }, function () {
            // complete
        });
}

// 保存产品特色信息
function saveProductDescInfo() {
    var productId = $('#productId').val();
    var productImgSamll = $('#productImgSamll').val();
    var productImgSamllId = $('#productImgSamllId').val();
    var productImgBig = $('#productImgBig').val();
    var productImgBigId = $('#productImgBigId').val();

    var productDesr = $('#productDesr').val();
    var productSpecialty = $('#productSpecialty').code();

    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'product.edit',
        'token': token,
        'id': productId,
        'imgSmall': productImgSamll,
        'productImgSamllId': productImgSamllId,
        'imgBig': productImgBig,
        'productImgBigId': productImgBigId,
        'description': productDesr,
        'specialty': productSpecialty
    };

    parent.execAjaxData("/view/product.action", JSON.stringify(jsondata), false
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                alert('保存成功');
            }
        }, function () {
            // complete
        });
}


// 保存费用信息
function saveProductCoseInfo() {
    var productId = $('#productId').val();
    var productCost = $('#productCost').code();

    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'product.edit',
        'token': token,
        'id': productId,
        'cost': productCost
    };

    parent.execAjaxData("/view/product.action", JSON.stringify(jsondata), false
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                alert('保存成功');
            }
        }, function () {
            // complete
        });
}

// 保存预定须知信息
function saveProductReservesInfo() {
    var productId = $('#productId').val();
    var productReserve = $('#productReserve').code();

    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'product.edit',
        'token': token,
        'id': productId,
        'reserve': productReserve
    };

    parent.execAjaxData("/view/product.action", JSON.stringify(jsondata), false
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                alert('保存成功');
            }
        }, function () {
            // complete
        });
}

// 保存出游提醒信息
function saveProductNoticeInfo() {
    var productId = $('#productId').val();
    var productNotice = $('#productNotice').code();

    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'product.edit',
        'token': token,
        'id': productId,
        'notice': productNotice
    };

    parent.execAjaxData("/view/product.action", JSON.stringify(jsondata), false
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                alert('保存成功');
            }
        }, function () {
            // complete
        });
}


function formUploadImageShow(flag) {
    imgFlag = flag;
    myDropzone.removeAllFiles(true);
    $('#formUploadImage').modal('show');
}


/**
 * 刷新
 */
function funRefresh() {
    tabPlanDetails.ajax.reload();
}

/**
 * 查询行程详情列表
 */
function funSelectPlanDetails() {
    sSource = "/view/planDetail.action?flag=list";

    // 添加查询条件
    var productId = $("#productId").val();
    aoData.push({name: "productId", value: productId});

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
 * 新增行程详情
 */
function addNewPlanDetail() {
    $('#formPlanDetailTitle').text("新增详情");

    $('#planId').val(null);
    $('#planTitle').val('');
    $('#planBreakfast').val('');
    $('#planLunch').val('');
    $('#planDinner').val('');
    $('#planTraffic').val('');
    $('#planStationFrom').val('');
    $('#planStationTo').val('');
    $('#planHotel').val('');
    $('#planContent').code('');

    $('#formPlanDetail').modal('show');
}

/**
 * 编辑行程详情
 */
function editPlanDetail(planId) {
    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'planDetail.detail',
        'token': token,
        'planId': planId
    };

    parent.execAjaxData("/view/planDetail.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#planTitle').val(response.data.title);
                $('#planBreakfast').val(response.data.breakfast);
                $('#planLunch').val(response.data.lunch);
                $('#planDinner').val(response.data.dinner);
                $('#planTraffic').val(response.data.traffic);
                $('#planStationFrom').val(response.data.stationFrom);
                $('#planStationTo').val(response.data.stationTo);
                $('#planHotel').val(response.data.hotel);
                $('#planContent').code(response.data.content);

                $('#formPlanDetailTitle').text("编辑详情");
                $('#formPlanDetail').modal('show');
            }
        }, function () {
            // complete
        });


}

/**
 * 保存详情
 */
function editSavePlanInfo() {

    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'planDetail.edit',
        'token': token,
        'id': $('#planId').val(),
        'title': $('#planTitle').val(),
        'stationFrom': $('#planStationFrom').val(),
        'traffic': $('#planTraffic').val(),
        'stationTo': $('#planStationTo').val(),
        'breakfast': $('#planBreakfast').val(),
        'lunch': $('#planLunch').val(),
        'dinner': $('#planDinner').val(),
        'hotel': $('#planHotel').val(),
        'content': $('#planContent').code()
    };

    parent.execAjaxData("/view/planDetail.action", JSON.stringify(jsondata), true
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
            $('#formPlanDetail').modal('hide');
        });
}

/**
 * 删除详情
 * @param planId
 */
function deletePlanInfo(planId) {
    if (confirm("确定要删除数据吗?")) {
        console.log("delete module id:" + planId);

        var token = $.cookie('userToken');
        var jsondata = {
            'op': 'planDetail.delete',
            'token': token,
            'planId': planId
        };

        parent.execAjaxData("/view/planDetail.action", JSON.stringify(jsondata), true
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
