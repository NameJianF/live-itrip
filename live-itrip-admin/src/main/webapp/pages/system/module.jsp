<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

<!-- DataTables -->
<link rel="stylesheet" href="/css/plugins/dataTables/dataTables.bootstrap.css">

<link href="/css/animate.min.css" rel="stylesheet">
<link href="/css/style.min862f.css?v=4.1.0" rel="stylesheet">

<script src="/javascript/common.js"></script>
<script src="/javascript/system/module.js"></script>

<div class="row">
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-content table-responsive">
                <div style="border-bottom:solid 1px lightgray; margin-bottom: 4px;">
                    <a onclick="funClickAddRow();" href="javascript:void(0);" class="btn btn-primary ">刷新</a>
                    <a onclick="funRefresh();" href="javascript:void(0);" class="btn btn-primary ">添加</a>
                </div>
                <table class="table table-hover table-bordered" id="tableModules" style="font-size: 14px;">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>模块名称</th>
                        <th>父模块</th>
                        <th>模块地址</th>
                        <th>是否可用</th>
                        <th>排序</th>
                        <th>描述</th>
                        <th>是否删除</th>
                        <th>创建时间</th>
                        <th>更新时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>

                </table>
            </div>
        </div>
    </div>
</div>
<script src="/js/jquery.min63b9.js?v=2.1.4"></script>
<script src="/js/bootstrap.min14ed.js?v=3.3.6"></script>
<script src="/js/jQueryCookie/jquery.cookie.js"></script>

<!-- DataTables -->
<script src="/js/plugins/datatables/jquery.dataTables.js"></script>
<script src="/js/plugins/datatables/dataTables.bootstrap.js"></script>

<script src="/js/content.mine209.js?v=1.0.0"></script>

<script>
    tabModules = $('#tableModules').dataTable({
        "bProcessing": true, // 是否显示取数据时的那个等待提示
        "bServerSide": true, //这个用来指明是通过服务端来取数据
        "bPaginate": true, // 分页按钮
        "bLengthChange": true, // 改变每页显示数据数量
        "iDisplayLength": 10,// 每页显示行数
        "bAutoWidth": true,//自动宽度
        "bInfo": true,//页脚信息
        "fnServerData": funSelectModules, // 获取数据的处理函数
        "bFilter": false, // 隐藏筛选框
        "ordering": false,
        "aoColumns": [
            {"mData": "id"},
            {"mData": "moduleName"},
            {"mData": "parentId"},
            {"mData": "moduleUrl"},
            {
                "mData": "available",
                render: function (data, type, row) {
                    if (data == 1) {
                        return "可用";
                    } else {
                        return "不可用";
                    }
                }
            },
            {"mData": "moduleOrder"},
            {
                "mData": "description",
                render: function (data, type, row) {
                    if (data == null) {
                        return "";
                    }
                    return data;
                }
            },
            {
                "mData": "isDelete",
                render: function (data, type, row) {
                    if (data == 1) {
                        return "删除";
                    } else {
                        return "正常";
                    }
                }
            },
            {
                "mData": "createTime",
                render: function (data, type, row) {
                    if (data == null) {
                        return "";
                    }
                    return data;
                }
            },
            {
                "mData": "updateTime",
                render: function (data, type, row) {
                    if (data == null) {
                        return "";
                    }
                    return data;
                }
            },
            {
                render: function (data, type, row) {
                    if (type === 'display') {
                        return '<button type="button" class="btn btn-link btn-xs" onclick="funEditModuleInfo(' + row.id + ')">编辑</button>' +
                                '<button type="button" class="btn btn-link btn-xs" onclick="funDeleteModuleInfo(' + row.id + ')">删除</button>';
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
</script>