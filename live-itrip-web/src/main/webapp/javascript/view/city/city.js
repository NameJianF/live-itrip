/**
 * Created by Feng on 2016/11/23.
 */

$(function () {
    $("#footer").load("/view/footer.html");

    loadHotProducts();
});


function loadHotProducts() {
    console.log('loading products ...');
    var cityId = $("#cityId").val();
    var cityName = $("#cityName").val();

    var jsondata = {
        'op': 'product.selectByCity',
        'token': parent.token,
        'cityId': cityId,
        'cityName': cityName
    };

    execAjaxDataForView("/view/product.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                var jsonarray = eval(response.data);
                var content = getProductsHtml(jsonarray);
                $('#hotProducts').html(content);
            }
        }, function () {
            // complete
        });
}