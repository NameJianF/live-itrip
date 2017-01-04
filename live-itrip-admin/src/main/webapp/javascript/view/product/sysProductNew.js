/**
 * Created by Feng on 2016/11/12.
 */

var imgFlag = 'small';
var tabPlanDetails;

$(function () {
    $('#productSpecialty').summernote({
        //height: 400,
        onImageUpload: function (files, editor, welEditable) {
            uploadImageFile(files[0], editor, welEditable);
        }
    });
    $('#productCost').summernote();
    $('#productReserve').summernote();
    $('#productNotice').summernote();
    $('#planContent').summernote({
        //height: 400,
        onImageUpload: function (files, editor, welEditable) {
            uploadImageFile(files[0], editor, welEditable);
        }
    });

    $('#productStartDate').datepicker({
        language: "zh-CN",
        autoclose: true,
        clearBtn: false,
        todayBtn: true,
        format: "yyyy-mm-dd"
    });

    initDataTable();

    // load data
    loadDatas();
});

function uploadImageFile(file, editor, welEditable) {
    var imageData = new FormData();
    imageData.append("file", file);
    imageData.append('productId', $('#productId').val());
    imageData.append('imgFlag', 'details');

    $.ajax('/file/upload.action?flag=0', {
        method: "POST",
        data: imageData,
        cache: false,
        processData: false,
        contentType: false,
        success: function (message) {
            var obj = jQuery.parseJSON(message);
            console.log(obj.data.fileUrl);
            editor.insertImage(welEditable, obj.data.fileUrl);
        },
        error: function () {
            console.log('Upload error');
        }
    });
}

function loadDatas() {
    var productId = $('#productId').val();
    if (productId != undefined && productId > 0) {
        var jsondata = {
            'op': 'product.detail',
            'token': parent.token,
            'productId': productId
        };

        parent.execAjaxData("/system/view/product.action", JSON.stringify(jsondata), true
            , function (response) {
                // error
            }, function (response) {
                // success
                if (response.code == 0) {
                    // base info
                    $('#productTitle').val(response.data.title);
                    $('#productPrice').val(response.data.price);
                    $('#priceFavoured').val(response.data.priceFavoured);
                    $('#productType option').filter(function () {
                        return $(this).text() == response.data.type;
                    }).attr("selected", true);
                    $('#productDays option').filter(function () {
                        return $(this).text() == response.data.days + '天';
                    }).attr("selected", true);
                    $('#productFromCity option').filter(function () {
                        return $(this).text() == response.data.fromCity;
                    }).attr("selected", true);
                    $('#productTraffic option').filter(function () {
                        return $(this).text() == response.data.traffic;
                    }).attr("selected", true);
                    $('#productStartDate').val(response.data.startDay);
                    $('#productClickCount').val(response.data.clickCount);
                    $('#priceJoinMans').val(response.data.joinMans);

                    // 产品特色
                    $('#productImgSamll').val(response.data.imgSmall);
                    $('#productImgMidd').val(response.data.imgMiddle);
                    $('#productImgBig').val(response.data.imgBig);
                    $('#productDesr').val(response.data.description);
                    $('#productSpecialty').code(response.data.specialty);

                    // 费用
                    $('#productCost').code(response.data.cost);

                    // 预定须知
                    $('#productReserve').code(response.data.reserve);

                    // 出游提醒
                    $('#productNotice').code(response.data.notice);
                }
            }, function () {
                // complete
            });
    }
}

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
        var jsondata = {
            'op': 'staticInfo.detail',
            'token': parent.token,
            'infoId': infoId
        };

        parent.execAjaxData("/system/view/staticInfo.action", JSON.stringify(jsondata), true
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
        var jsondata = {
            'op': 'staticInfo.detail',
            'token': parent.token,
            'infoId': infoId
        };

        parent.execAjaxData("/system/view/staticInfo.action", JSON.stringify(jsondata), true
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
        var jsondata = {
            'op': 'staticInfo.detail',
            'token': parent.token,
            'infoId': infoId
        };

        parent.execAjaxData("/system/view/staticInfo.action", JSON.stringify(jsondata), true
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
    var productClickCount = $('#productClickCount').val();
    var priceJoinMans = $('#priceJoinMans').val();

    var jsondata = {
        'op': 'product.edit',
        'token': parent.token,
        'id': productId,
        'title': productTitle,
        'price': productPrice,
        'priceFavoured': priceFavoured,
        'type': productType,
        'days': productDays.replace('天', ''),
        'fromCity': productFromCity,
        'traffic': productTraffic,
        'startDay': productStartDate,
        'clickCount': productClickCount,
        'joinMans': priceJoinMans
    };

    parent.execAjaxData("/system/view/product.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#productId').val(response.data.id);
                parent.notifySuccess('保存成功', '');
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
    var productImgMidd = $('#productImgMidd').val();
    var productImgMiddId = $('#productImgMiddId').val();
    var productImgBig = $('#productImgBig').val();
    var productImgBigId = $('#productImgBigId').val();

    var productDesr = $('#productDesr').val();
    var productSpecialty = $('#productSpecialty').code();

    var jsondata = {
        'op': 'product.edit',
        'token': parent.token,
        'id': productId,
        'imgSmall': productImgSamll,
        'productImgSamllId': productImgSamllId,
        'imgMiddle': productImgMidd,
        'productImgMiddId': productImgMiddId,
        'imgBig': productImgBig,
        'productImgBigId': productImgBigId,
        'description': productDesr,
        'specialty': productSpecialty
    };

    parent.execAjaxData("/system/view/product.action", JSON.stringify(jsondata), false
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                parent.notifySuccess('保存成功', '');
            }
        }, function () {
            // complete
        });
}


// 保存费用信息
function saveProductCoseInfo() {
    var productId = $('#productId').val();
    var productCost = $('#productCost').code();

    var jsondata = {
        'op': 'product.edit',
        'token': parent.token,
        'id': productId,
        'cost': productCost
    };

    parent.execAjaxData("/system/view/product.action", JSON.stringify(jsondata), false
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                parent.notifySuccess('保存成功', '');
            }
        }, function () {
            // complete
        });
}

// 保存预定须知信息
function saveProductReservesInfo() {
    var productId = $('#productId').val();
    var productReserve = $('#productReserve').code();

    var jsondata = {
        'op': 'product.edit',
        'token': parent.token,
        'id': productId,
        'reserve': productReserve
    };

    parent.execAjaxData("/system/view/product.action", JSON.stringify(jsondata), false
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                parent.notifySuccess('保存成功', '');
            }
        }, function () {
            // complete
        });
}

// 保存出游提醒信息
function saveProductNoticeInfo() {
    var productId = $('#productId').val();
    var productNotice = $('#productNotice').code();

    var jsondata = {
        'op': 'product.edit',
        'token': parent.token,
        'id': productId,
        'notice': productNotice
    };

    parent.execAjaxData("/system/view/product.action", JSON.stringify(jsondata), false
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                parent.notifySuccess('保存成功', '');
            }
        }, function () {
            // complete
        });
}


function formUploadImageShow(flag) {
    imgFlag = flag;
    $('#divCropper').load("/pages/view/cropper/cropper.jsp");
    $('#formUploadImage').modal('show');
}

function uploadImage() {
    imageCropper.cropper('getCroppedCanvas').toBlob(function (blob) {
        var formData = new FormData();

        formData.append('croppedImage', blob);
        formData.append('productId', $('#productId').val());
        formData.append('imgFlag', imgFlag);

        $.ajax('/file/upload.action?flag=0', {
            method: "POST",
            data: formData,
            cache: false,
            processData: false,
            contentType: false,
            success: function (message) {
                //console.log('Upload success');
                var obj = jQuery.parseJSON(message)
                if (imgFlag == 'small') {
                    $('#productImgSamll').val(obj.data.fileUrl);
                    $('#productImgSamllId').val(obj.data.fileId);
                } else if (imgFlag == 'middle') {
                    $('#productImgMidd').val(obj.data.fileUrl);
                    $('#productImgMiddId').val(obj.data.fileId);
                } else if (imgFlag == 'big') {
                    $('#productImgBig').val(obj.data.fileUrl);
                    $('#productImgBigId').val(obj.data.fileId);
                }
                console.log('Upload success');
                $('#formUploadImage').modal('hide');
            },
            error: function () {
                console.log('Upload error');
            }
        });
    });
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
function funSelectPlanDetails(sSource, aoData, fnCallback) {
    sSource = "/system/view/planDetail.action?flag=list";

    // 添加查询条件
    var productId = $("#productId").val();
    aoData.push({name: "productId", value: productId});

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
    var jsondata = {
        'op': 'planDetail.detail',
        'token': parent.token,
        'planId': planId
    };

    parent.execAjaxData("/system/view/planDetail.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#planId').val(response.data.id);
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

    var jsondata = {
        'op': 'planDetail.edit',
        'token': parent.token,
        'id': $('#planId').val(),
        'title': $('#planTitle').val(),
        'productId': $('#productId').val(),
        'stationFrom': $('#planStationFrom').val(),
        'traffic': $('#planTraffic').val(),
        'stationTo': $('#planStationTo').val(),
        'breakfast': $('#planBreakfast').val(),
        'lunch': $('#planLunch').val(),
        'dinner': $('#planDinner').val(),
        'hotel': $('#planHotel').val(),
        'content': $('#planContent').code()
    };

    parent.execAjaxData("/system/view/planDetail.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
            parent.notifyDanger('保存失败', response);
        }, function (response) {
            // success
            if (response.code == 0) {
                parent.notifySuccess('保存成功', '');
                funRefresh();
            } else {
                parent.notifyDanger('保存失败', response.msg);
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

        var jsondata = {
            'op': 'planDetail.delete',
            'token': parent.token,
            'planId': planId
        };

        parent.execAjaxData("/system/view/planDetail.action", JSON.stringify(jsondata), true
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

//预览
function showPreview() {
    // set value
    var productType = $('#productType').find("option:selected").text();
    var preFromCity = $('#productFromCity').find("option:selected").text();
    var days = $('#productDays').find("option:selected").text();
    var trafic = $('#productTraffic').find("option:selected").text();
    $('#preTitle').html($('#productTitle').val());
    $("#preImgMidd").attr("src", $('#productImgMidd').val());
    $('#preDesr').html($('#productDesr').val());
    $('#prePrice').html('&yen;' + $('#productPrice').val());
    $('#preType').html(productType);
    $('#preFromCity').html(preFromCity);
    $('#preDays').html(days);
    $('#preTrafic').html(trafic);
    $('#preSpecialty').html($('#productSpecialty').code());
    $('#preCost').html($('#productCost').code());
    $('#preReserve').html($('#productReserve').code());
    $('#preNotice').html($('#productNotice').code());

    // plan
    var jsondata = {
        'op': 'planDetail.preList',
        'token': parent.token,
        'productId': $('#productId').val()
    };

    parent.execAjaxData("/system/view/planDetail.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                var content = "";
                var jsonarray = eval(response.data);
                for (var i = 0; i < jsonarray.length; i++) {
                    var item = jsonarray[i];
                    content += '<div class="vertical-timeline-block">';
                    content += '<div class="vertical-timeline-icon navy-bg">';
                    content += ' <i class="fa fa-flag"></i>';
                    content += '</div>';
                    content += '<div class="vertical-timeline-content">';
                    content += '<h2>' + item.title + '</h2>';
                    content += '<table class="table small m-b-xs">';
                    content += '<tbody>';
                    content += '<tr style="height: 50px;">';
                    content += '<td style="line-height: 40px;">';
                    content += '<i class="fa fa-delicious" style="margin-right: 10px;"></i>';
                    content += '早餐: <span class="label label-primary"';
                    content += 'style="margin-right: 30px;">' + item.breakfast + '</span>';
                    content += '中餐: <span class="label label-primary"';
                    content += 'style="margin-right: 30px;">' + item.lunch + '</span>';
                    content += '晚餐: <span class="label label-primary"';
                    content += 'style="margin-right: 30px;">' + item.dinner + '</span>';
                    content += '</td>';
                    content += '</tr>';
                    content += '<tr style="height: 50px;">';
                    content += '<td style="line-height: 40px;">';
                    content += ' <i class="fa fa-building" style="margin-right: 10px;"></i>';
                    content += '住宿: <span class="label label-primary">' + item.hotel + '</span>';
                    content += '</td>';
                    content += '</tr>';
                    content += '<tr style="height: 50px;">';
                    content += '<td style="line-height: 40px;">';
                    content += ' <i class="fa fa-building" style="margin-right: 10px;"></i>';
                    content += '交通: <span class="label label-car">' + item.traffic + '</span>';
                    content += '</td>';
                    content += '</tr>';
                    content += '<tr>';
                    content += '<td>';
                    content += '<p>' + item.content;
                    content += '</p>';

                    content += '</td>';
                    content += '</tr>';
                    content += '</tbody>';
                    content += '</table>';

                    content += '<a href="#" class="btn btn-xs btn-primary"> More';
                    content += 'info</a>';
                    content += '<span class="vertical-date"><small>第 ' + (i + 1) + ' 天';
                    content += '</small> </span>';
                    content += '</div>';
                    content += '</div>';
                }
                $('#vertical-timeline').html(content);
            }
        }, function () {
            // complete
        });


    $('#formPreview').modal('show');
}