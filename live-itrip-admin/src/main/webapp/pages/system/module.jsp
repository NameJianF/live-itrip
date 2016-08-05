<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

<!-- DataTables -->
<link rel="stylesheet" href="/js/plugins/datatables/dataTables.bootstrap.css">

<link href="/css/animate.min.css" rel="stylesheet">
<link href="/css/style.min862f.css?v=4.1.0" rel="stylesheet">

<script src="/javascript/common.js"></script>
<script src="/javascript/system/module.js"></script>

<div class="col-sm-12 gray-bg">
    <div class="ibox float-e-margins" style="margin-top: 5px;margin-bottom: -5px;">
        <div class="ibox-content" style="border:solid 1px #add9c0;">
            <div>
                <a onclick="fnClickAddRow();" href="javascript:void(0);" class="btn btn-primary ">添加行</a>
            </div>
            <table class="table table-striped table-bordered table-hover" id="tableModules">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>所属部门</th>
                    <th>创建时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>

            </table>
        </div>
    </div>
</div>

<script src="/js/jquery.min63b9.js?v=2.1.4"></script>
<script src="/js/bootstrap.min14ed.js?v=3.3.6"></script>
<script src="/js/content.mine209.js?v=1.0.0"></script>

<!-- DataTables -->
<script src="/js/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/js/plugins/datatables/dataTables.bootstrap.min.js"></script>


<script>
    tabModules = $('#tableModules').DataTable({
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true, //这个用来指明是通过服务端来取数据
        "bPaginate": true, // 分页按钮
        "bLengthChange": true, // 改变每页显示数据数量
        "iDisplayLength": 10,// 每页显示行数
        "bAutoWidth": true,//自动宽度
        "bInfo": true,//页脚信息
        "fnServerData": selectModules, // 获取数据的处理函数
        "bFilter": false, // 隐藏筛选框
        "ordering": false,
        "aoColumns": [
//            {
//                render: function (data, type, row) {
//                    if (type === 'display') {
//                        return '<input type="checkbox">';
//                    }
//                    return data;
//                }
//            },
            {
                "mData": "id"
            },
            {"mData": "email"},
            {"mData": "nickName"},
            {
                "mData": "mobile",
                render: function (data, type, row) {
                    if (null == data || "" == data) {
                        return "未设置";
                    } else {
                        return data;
                    }
                }
            },
            {"mData": "depart"},
            {"mData": "createTime"},
            {
                "mData": "status",
                render: function (data, type, row) {
                    if (data == 1) {
                        return "生效";
                    } else {
                        return "未生效";
                    }
                    return "未知";
                }
            },
            {
                render: function (data, type, row) {
                    if (type === 'display') {
                        return '<button type="button" class="btn btn-link btn-xs" onclick="showModalUserForm(' + row.id + ')">编辑</button>';
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
    /********************************/
    //add tooltip for small view action buttons in dropdown menu
    $('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});

    //tooltip placement on right or left
    function tooltip_placement(context, source) {
        var $source = $(source);
        var $parent = $source.closest('table')
        var off1 = $parent.offset();
        var w1 = $parent.width();

        var off2 = $source.offset();
        //var w2 = $source.width();

        if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
        return 'left';
    }

    $('.mailbox-messages input[type="checkbox"]').iCheck({
        checkboxClass: 'icheckbox_flat-blue',
        radioClass: 'iradio_flat-blue'
    });

    //Enable check and uncheck all functionality
    $(".checkbox-toggle").click(function () {
        var clicks = $(this).data('clicks');
        if (clicks) {
            //Uncheck all checkboxes
            $(".mailbox-messages input[type='checkbox']").iCheck("uncheck");
            $(".fa", this).removeClass("fa-check-square-o").addClass('fa-square-o');
        } else {
            //Check all checkboxes
            $(".mailbox-messages input[type='checkbox']").iCheck("check");
            $(".fa", this).removeClass("fa-square-o").addClass('fa-check-square-o');
        }
        $(this).data("clicks", !clicks);
    });
</script>