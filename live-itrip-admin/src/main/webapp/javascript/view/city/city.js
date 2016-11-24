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

    var jsondata = {
        'op': 'product.selectByCity',
        'token': parent.token,
        'cityId': cityId
    };

    parent.execAjaxDataForView("/view/product.action", JSON.stringify(jsondata), true
        , function (response) {
            // error
        }, function (response) {
            // success
            if (response.code == 0) {
                $('#editCityInfoId').val(response.data.id);
                $('#editCityInfoName').val(response.data.cityName);
                $('#editCityInfoArea').val(response.data.cityArea);
                $('#editCityInfoTitle').val(response.data.cityTitle);
                $('#editCityInfoContent').code(response.data.cityContent);

                $('#formEditTitle').text("信息编辑");
                $('#formEditCityInfo').modal('show');
            }
        }, function () {
            // complete
        });
}