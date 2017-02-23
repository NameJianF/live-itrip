/**
 * Created by Feng on 2016/12/1.
 */

$(function () {
    $("#footer").load("/view/footer.html");
    initNavbar();

    loadAboutProducts();
});

function loadAboutProducts() {
    var productId = $("#productId").val();
    var productType = $("#productType").val();

    var jsondata = {
        'op': 'product.selectAbouts',
        'token': parent.token,
        'productId': productId,
        'productType': productType
    };

    execAjaxDataForView("/view/product.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                var jsonarray = eval(response.data);
                var content = getProductsHtml(jsonarray);
                $('#aboutProducts').html(content);
            }
        }, function () {
            // complete
        });
}

// 提交问题
function submitQuestion() {

}
