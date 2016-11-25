<%--
  Created by IntelliJ IDEA.
  User: Feng
  Date: 2016/10/18
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>

<!-- Mainly scripts -->
<script src="/js/jquery-2.1.1.js"></script>
<script src="/js/bootstrap.min.js"></script>

<!-- Custom and plugin javascript -->
<%--<script src="/js/plugins/pace/pace.min.js"></script>--%>


<!-- jQuery UI -->
<script src="/js/plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- Toastr -->
<script src="/js/plugins/toastr/toastr.min.js"></script>

<script src="/js/itrip/jquery.cookie.js"></script>

<script src="/js/plugins/dataTables/datatables.min.js"></script>

<script>

    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1,
            //月份
            "d+": this.getDate(),
            //日
            "h+": this.getHours(),
            //小时
            "m+": this.getMinutes(),
            //分
            "s+": this.getSeconds(),
            //秒
            "q+": Math.floor((this.getMonth() + 3) / 3),
            //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    }
</script>