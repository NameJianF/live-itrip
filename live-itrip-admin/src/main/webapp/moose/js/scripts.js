
/*-----------------------------------------------------------------------------------*/
/*	ISOTOPE PORTFOLIO
 /*-----------------------------------------------------------------------------------*/
$(document).ready(function () {
    var $container = $('.items');
    $container.imagesLoaded(function () {
        $container.isotope({
            itemSelector: '.item',
            layoutMode: 'fitRows'
        });
    });

    $('.portfolio .filter a').click(function () {

        $('.portfolio .filter a').removeClass('active');
        $(this).addClass('active');

        var selector = $(this).attr('data-filter');
        $container.isotope({
            filter: selector
        });

        return false;
    });
});
/*-----------------------------------------------------------------------------------*/
/*	ISOTOPE FULLSCREEN PORTFOLIO
 /*-----------------------------------------------------------------------------------*/

var isotopeBreakpoints = [{
    min_width: 1680,
    columns: 6
}, // Desktop
    {
        min_width: 1140,
        max_width: 1680,
        columns: 5
    }, // iPad Landscape
    {
        min_width: 1024,
        max_width: 1440,
        columns: 4
    }, // iPad Portrait
    {
        min_width: 768,
        max_width: 1024,
        columns: 3
    }, // iPhone Landscape
    {
        max_width: 768,
        columns: 1
    } // iPhone Portrait

];

$(document).ready(function () {
    var $container = $('.items.fullscreen');

    $container.imagesLoaded(function () {
        $container.isotope({
            itemSelector: '.item',
            layoutMode: 'fitRows'
        });
    });

    // hook to window resize to resize the portfolio items for fluidity / responsiveness
    $(window).smartresize(function () {
        var windowWidth = $(window).width();
        var windowHeight = $(window).height();

        for (var i = 0; i < isotopeBreakpoints.length; i++) {
            if (windowWidth >= isotopeBreakpoints[i].min_width || !isotopeBreakpoints[i].min_width) {
                if (windowWidth < isotopeBreakpoints[i].max_width || !isotopeBreakpoints[i].max_width) {
                    $container.find('.item').each(function () {
                        $(this).width(Math.floor($container.width() / isotopeBreakpoints[i].columns));
                    });

                    break;
                }
            }
        }
    });

    $(window).trigger('smartresize');


});
/*-----------------------------------------------------------------------------------*/
/*	ISOTOPE BLOG
 /*-----------------------------------------------------------------------------------*/
$(document).ready(function () {
    var $container = $('.grid-blog');
    $container.imagesLoaded(function () {
        $container.isotope({
            itemSelector: '.post'
        });
    });

    $(window).on('resize', function () {
        $('.grid-blog').isotope('reLayout')
    });
});
/*-----------------------------------------------------------------------------------*/
/*	IMAGE HOVER
 /*-----------------------------------------------------------------------------------*/
$(document).ready(function () {
    $('.icon-overlay a').prepend('<span class="icn-more"></span>');
});
/*-----------------------------------------------------------------------------------*/
/*	HOME SLIDER
 /*-----------------------------------------------------------------------------------*/
$(document).ready(function () {
    var revapi;
    jQuery(document).ready(function () {

        revapi = jQuery('.fullwidthbanner').revolution({
            delay: 9000,
            startwidth: 1170,
            startheight: 600,
            hideThumbs: 200,
            touchenabled: "off",
            fullWidth: "on"
        });

    });
});
/*-----------------------------------------------------------------------------------*/
/*	PRETTIFY
 /*-----------------------------------------------------------------------------------*/
jQuery(document).ready(function () {
    window.prettyPrint && prettyPrint()
});
/*-----------------------------------------------------------------------------------*/
/*	DATA REL
 /*-----------------------------------------------------------------------------------*/
$('a[data-rel]').each(function () {
    $(this).attr('rel', $(this).data('rel'));
});

$(document).ready(function () {
    $('.comment-form input[title], .comment-form textarea').each(function () {
        if ($(this).val() === '') {
            $(this).val($(this).attr('title'));
        }

        $(this).focus(function () {
            if ($(this).val() == $(this).attr('title')) {
                $(this).val('').addClass('focused');
            }
        });
        $(this).blur(function () {
            if ($(this).val() === '') {
                $(this).val($(this).attr('title')).removeClass('focused');
            }
        });
    });
});
/*-----------------------------------------------------------------------------------*/
/*	PARALLAX MOBILE
 /*-----------------------------------------------------------------------------------*/
$(document).ready(function () {
    if (navigator.userAgent.match(/Android/i) ||
        navigator.userAgent.match(/webOS/i) ||
        navigator.userAgent.match(/iPhone/i) ||
        navigator.userAgent.match(/iPad/i) ||
        navigator.userAgent.match(/iPod/i) ||
        navigator.userAgent.match(/BlackBerry/i)) {
        $('.parallax').addClass('mobile');
    }
});
/*-----------------------------------------------------------------------------------*/
/*	TOOLTIP
 /*-----------------------------------------------------------------------------------*/
$(document).ready(function () {
    if ($("[rel=tooltip]").length) {
        $("[rel=tooltip]").tooltip();
    }
});