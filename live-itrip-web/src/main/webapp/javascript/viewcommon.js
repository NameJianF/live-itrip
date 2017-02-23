/**
 * Created by Feng on 2016/7/19.
 */

/**
 * 执行 ajax 调用
 * @param url
 * @param jsondata
 * @param async
 * @param callback
 */
function execAjaxDataForView(url, jsondata, async, error, success, complete) {
    console.log(url);
    console.log(jsondata);
    $.ajax({
        cache: false,
        url: url,
        type: "POST",
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        data: jsondata,
        async: async,
        error: function (data) {
            console.log(data);
            error(data);
        },
        success: function (data) {
            console.log(data);
            success(data);
        },
        complete: function () {
            complete();
        }
    });
}

function getProductsHtml(jsonarray) {
    var content = "";
    for (var i = 0; i < jsonarray.length; i++) {
        var item = jsonarray[i];
        content += '<div class="ibox">';
        content += '<div class="ibox-content product-box"> ';
        content += '<div class="product-imitation" style="padding: 0px">';
        content += '<a href="/view/product.action?pid=' + item.id + '">';
        content += '<img src="' + item.imgSmall + '" style="max-width:100%;height:auto;">';
        content += '</a>';
        content += '</div>';
        content += '<div class="product-desc">';
        content += '<span class="product-price">&yen; ' + item.price + '</span>';
        content += '<a href="/view/product.action?pid=' + item.id + '" class="product-name" style="text-align: center">' + item.title + '</a>';
        content += '<div class="vote-info" style="margin-left: 0px;"> <div class="row">';
        content += '<div class="col-md-6"';
        content += '<i class="fa fa-comments-o"></i> <a>浏览(' + item.clickCount + ')</a>';
        content += '</div>';
        content += '<div class="col-md-6">';
        content += '<i class="fa fa-user"></i>';
        content += '<a >参团人数(' + item.joinMans + ')</a>';
        content += '</div>';
        content += '</div>';
        content += '</div>';
        //content += '<div class="m-t text-right">';
        //content += ' <a href="/view/product.action?pid=' + item.id + '" class="btn btn-xs btn-outline btn-primary">详细<i class="fa fa-long-arrow-right"></i> </a>';
        //content += '</div>';
        content += '</div>';
        content += '</div>';
        content += '</div>';
        content += '</div>';
    }

    return content;
}

function initNavbar() {

    $('body').scrollspy({
        target: '.navbar-fixed-top',
        offset: 80
    });

    // Page scrolling feature
    $('a.page-scroll').bind('click', function (event) {
        var link = $(this);
        $('html, body').stop().animate({
            scrollTop: $(link.attr('href')).offset().top - 50
        }, 500);
        event.preventDefault();
        $("#navbar").collapse('hide');
    });

    var cbpAnimatedHeader = (function () {
        var docElem = document.documentElement,
            header = document.querySelector('.navbar-default'),
            didScroll = false,
            changeHeaderOn = 200;

        function init() {
            window.addEventListener('scroll', function (event) {
                if (!didScroll) {
                    didScroll = true;
                    setTimeout(scrollPage, 250);
                }
            }, false);
        }

        function scrollPage() {
            var sy = scrollY();
            if (sy >= changeHeaderOn) {
                $(header).addClass('navbar-scroll')
            }
            else {
                $(header).removeClass('navbar-scroll')
            }
            didScroll = false;
        }

        function scrollY() {
            return window.pageYOffset || docElem.scrollTop;
        }

        init();

    })();

    // Activate WOW.js plugin for animation on scrol
    new WOW().init();

}


