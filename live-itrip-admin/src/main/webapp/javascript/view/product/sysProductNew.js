/**
 * Created by Feng on 2016/11/12.
 */

$(function () {
    $('#productSpecialty').summernote();
    $('#productCost').summernote();
    $('#productReserve').summernote();
    $('#productNotice').summernote();

    $('#productStartDate').datepicker({
        language: "zh-CN",
        autoclose: true,
        clearBtn: false,
        todayBtn: true,
        format: "yyyy-mm-dd"
    });

    Dropzone.options.myAwesomeDropzone = {
        autoProcessQueue: false,
        uploadMultiple: true,
        parallelUploads: 100,
        maxFiles: 100,

        // Dropzone settings
        init: function () {
            var myDropzone = this;

            this.element.querySelector("button[type=submit]").addEventListener("click", function (e) {
                e.preventDefault();
                e.stopPropagation();
                myDropzone.processQueue();
            });
            this.on("sendingmultiple", function () {
            });
            this.on("successmultiple", function (files, response) {
            });
            this.on("errormultiple", function (files, response) {
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
    var productDesr = $('#productDesr').val();
    var productSpecialty = $('#productSpecialty').code();

    var token = $.cookie('userToken');
    var jsondata = {
        'op': 'product.edit',
        'token': token,
        'id': productId,
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


function formUploadImageShow() {
    $('#formUploadImage').modal('show');
}