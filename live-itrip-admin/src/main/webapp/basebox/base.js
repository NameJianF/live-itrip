
$(function() {

        $(".baseBox").on('click', '.bclose', function() {
                $(this).removeClass("bclose").addClass("bopen");
                $(".baseBox").find('.baseOn').stop().animate({height: '0'}, 450).addClass("overOn");
        });
        $(".baseBox").on('click', '.bopen', function() {
                $(this).removeClass("bopen").addClass("bclose");
                $(".baseBox").find('.baseOn').stop().animate({height: '273px'}, 450).removeClass("overOn");
        });

        var ScrollTop = $(window).scrollTop();
        if (ScrollTop > 100) {
                $(".J_ScrollTop").show();
        } else {
                $(".J_ScrollTop").hide();
        }
        $(window).scroll(function() {
                var ScrollTop = $(window).scrollTop();
                if (ScrollTop > 100) {
                        $(".J_ScrollTop").show();
                } else {
                        $(".J_ScrollTop").hide();
                }
        });
        //返回顶部
        $(".J_ScrollTop").click(function() {
                $('body,html').animate({scrollTop: 0}, 800);
        });
        $(".J_ScrollTop").hover(function() {
                $(this).addClass("on");
        }, function() {
                $(this).removeClass("on");
        });

        //辅助功能栏弹窗
        $('.baseOn li').hover(function() {
                $(this).addClass("on");
                $(this).children(".bchild").show();
        }, function() {
                $(this).removeClass("on");
                $(this).children(".bchild").hide();
        });

});