<%--
  Created by IntelliJ IDEA.
  User: Feng
  Date: 2016/12/6
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<link rel="stylesheet" href="/css/plugins/cropper/cropper.min.css">
<link rel="stylesheet" href="/css/plugins/cropper/main.css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<%--</head>--%>

<%--<body style="width: 800px;height: 600px;">--%>
<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
your browser</a> to improve your experience.</p>
<![endif]-->


<div class="row">
    <div class="col-md-8">
        <!-- <h3 class="page-header">Demo:</h3> -->
        <div class="img-container">
            <img id="image" alt="原图">
        </div>
    </div>
    <div class="col-md-3">
        <!-- <h3 class="page-header">Preview:</h3> -->
        <div class="docs-preview clearfix">
            <div class="img-preview preview-lg"></div>
        </div>

        <!-- <h3 class="page-header">Data:</h3> -->
        <div class="docs-data">

            <div class="input-group input-group-sm">
                <label class="input-group-addon" for="dataWidth">Width</label>
                <input type="text" class="form-control" id="dataWidth" placeholder="width">
                <span class="input-group-addon">px</span>
            </div>
            <div class="input-group input-group-sm">
                <label class="input-group-addon" for="dataHeight">Height</label>
                <input type="text" class="form-control" id="dataHeight" placeholder="height">
                <span class="input-group-addon">px</span>
            </div>
            <div class="input-group input-group-sm">
                <label class="input-group-addon" for="dataRotate">Rotate</label>
                <input type="text" class="form-control" id="dataRotate" placeholder="rotate">
                <span class="input-group-addon">deg</span>
            </div>

        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-8 docs-buttons">
        <!-- <h3 class="page-header">Toolbar:</h3> -->
        <div class="btn-group">
            <button type="button" class="btn btn-primary" data-method="setDragMode" data-option="move" title="Move">
                <span class="docs-tooltip" data-toggle="tooltip"
                      title="拖拽模式">
              <span class="fa fa-arrows"></span>
            </span>
            </button>
            <%--<button type="button" class="btn btn-primary" data-method="setDragMode" data-option="crop" title="Crop">--%>
            <%--<span class="docs-tooltip" data-toggle="tooltip"--%>
            <%--title="$().cropper(&quot;setDragMode&quot;, &quot;crop&quot;)">--%>
            <%--<span class="fa fa-crop"></span>--%>
            <%--</span>--%>
            <%--</button>--%>
        </div>

        <div class="btn-group">
            <button type="button" class="btn btn-primary" data-method="zoom" data-option="0.1" title="放大">
            <span class="docs-tooltip" data-toggle="tooltip" title="放大">
              <span class="fa fa-search-plus"></span>
            </span>
            </button>
            <button type="button" class="btn btn-primary" data-method="zoom" data-option="-0.1" title="缩小">
            <span class="docs-tooltip" data-toggle="tooltip" title="缩小">
              <span class="fa fa-search-minus"></span>
            </span>
            </button>
        </div>

        <div class="btn-group">
            <button type="button" class="btn btn-primary" data-method="move" data-option="-10"
                    data-second-option="0" title="Move Left">
                <span class="docs-tooltip" data-toggle="tooltip" title="">
                  <span class="fa fa-arrow-left"></span>
                </span>
            </button>
            <button type="button" class="btn btn-primary" data-method="move" data-option="10" data-second-option="0"
                    title="Move Right">
                <span class="docs-tooltip" data-toggle="tooltip" title="">
                  <span class="fa fa-arrow-right"></span>
                </span>
            </button>
            <button type="button" class="btn btn-primary" data-method="move" data-option="0"
                    data-second-option="-10" title="Move Up">
                <span class="docs-tooltip" data-toggle="tooltip" title="">
                  <span class="fa fa-arrow-up"></span>
                </span>
            </button>
            <button type="button" class="btn btn-primary" data-method="move" data-option="0" data-second-option="10"
                    title="Move Down">
                <span class="docs-tooltip" data-toggle="tooltip" title="">
                  <span class="fa fa-arrow-down"></span>
                </span>
            </button>
        </div>

        <div class="btn-group">
            <button type="button" class="btn btn-primary" data-method="rotate" data-option="-45"
                    title="Rotate Left">
                <span class="docs-tooltip" data-toggle="tooltip" title="">
                  <span class="fa fa-rotate-left"></span>
                </span>
            </button>
            <button type="button" class="btn btn-primary" data-method="rotate" data-option="45"
                    title="Rotate Right">
                <span class="docs-tooltip" data-toggle="tooltip" title="">
                  <span class="fa fa-rotate-right"></span>
                </span>
            </button>
        </div>

        <div class="btn-group">
            <button type="button" class="btn btn-primary" data-method="scaleX" data-option="-1"
                    title="Flip Horizontal">
                <span class="docs-tooltip" data-toggle="tooltip" title="">
                  <span class="fa fa-arrows-h"></span>
                </span>
            </button>
            <button type="button" class="btn btn-primary" data-method="scaleY" data-option="-1"
                    title="Flip Vertical">
                <span class="docs-tooltip" data-toggle="tooltip" title="">
                  <span class="fa fa-arrows-v"></span>
                </span>
            </button>
        </div>

        <%--<div class="btn-group">--%>
        <%--<button type="button" class="btn btn-primary" data-method="crop" title="Crop">--%>
        <%--<span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;crop&quot;)">--%>
        <%--<span class="fa fa-check"></span>--%>
        <%--</span>--%>
        <%--</button>--%>
        <%--<button type="button" class="btn btn-primary" data-method="clear" title="Clear">--%>
        <%--<span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;clear&quot;)">--%>
        <%--<span class="fa fa-remove"></span>--%>
        <%--</span>--%>
        <%--</button>--%>
        <%--</div>--%>

        <div class="btn-group">
            <button type="button" class="btn btn-primary" data-method="disable" title="Disable">
                <span class="docs-tooltip" data-toggle="tooltip" title="">
                  <span class="fa fa-lock"></span>
                </span>
            </button>
            <button type="button" class="btn btn-primary" data-method="enable" title="Enable">
                <span class="docs-tooltip" data-toggle="tooltip" title="">
                  <span class="fa fa-unlock"></span>
                </span>
            </button>
        </div>

        <div class="btn-group">
            <button type="button" class="btn btn-primary" data-method="reset" title="Reset">
                <span class="docs-tooltip" data-toggle="tooltip" title="">
                  <span class="fa fa-refresh"></span>
                </span>
            </button>
            <label class="btn btn-primary btn-upload" for="inputImage" title="Upload image file">
                <input type="file" class="sr-only" id="inputImage" name="file" accept="image/*">
                <span class="docs-tooltip" data-toggle="tooltip" title="选择图片">
                  <span class="fa fa-upload"></span>
                </span>
            </label>
            <%--<button type="button" class="btn btn-primary" data-method="destroy" title="Destroy">--%>
            <%--<span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;destroy&quot;)">--%>
            <%--<span class="fa fa-power-off"></span>--%>
            <%--</span>--%>
            <%--</button>--%>
        </div>

        <%--<div class="btn-group btn-group-crop">--%>
        <%--<button type="button" class="btn btn-primary" data-method="getCroppedCanvas">--%>
        <%--<span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getCroppedCanvas&quot;)">--%>
        <%--Get Cropped Canvas--%>
        <%--</span>--%>
        <%--</button>--%>
        <%--<button type="button" class="btn btn-primary" data-method="getCroppedCanvas"--%>
        <%--data-option="{ &quot;width&quot;: 160, &quot;height&quot;: 90 }">--%>
        <%--<span class="docs-tooltip" data-toggle="tooltip"--%>
        <%--title="$().cropper(&quot;getCroppedCanvas&quot;, { width: 160, height: 90 })">--%>
        <%--160&times;90--%>
        <%--</span>--%>
        <%--</button>--%>
        <%--<button type="button" class="btn btn-primary" data-method="getCroppedCanvas"--%>
        <%--data-option="{ &quot;width&quot;: 320, &quot;height&quot;: 180 }">--%>
        <%--<span class="docs-tooltip" data-toggle="tooltip"--%>
        <%--title="$().cropper(&quot;getCroppedCanvas&quot;, { width: 320, height: 180 })">--%>
        <%--320&times;180--%>
        <%--</span>--%>
        <%--</button>--%>
        <%--</div>--%>

        <!-- Show the cropped image in modal -->
        <div class="modal fade docs-cropped" id="getCroppedCanvasModal" aria-hidden="true"
             aria-labelledby="getCroppedCanvasTitle" role="dialog" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="getCroppedCanvasTitle">Cropped</h4>
                    </div>
                    <div class="modal-body"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <a class="btn btn-primary" id="download" href="javascript:void(0);" download="cropped.jpg">Download</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.modal -->

        <%--<button type="button" class="btn btn-primary" data-method="moveTo" data-option="0">--%>
        <%--<span class="docs-tooltip" data-toggle="tooltip" title="cropper.moveTo(0)">--%>
        <%--0,0--%>
        <%--</span>--%>
        <%--</button>--%>
        <%--<button type="button" class="btn btn-primary" data-method="zoomTo" data-option="1">--%>
        <%--<span class="docs-tooltip" data-toggle="tooltip" title="cropper.zoomTo(1)">--%>
        <%--100%--%>
        <%--</span>--%>
        <%--</button>--%>
        <%--<button type="button" class="btn btn-primary" data-method="rotateTo" data-option="180">--%>
        <%--<span class="docs-tooltip" data-toggle="tooltip" title="cropper.rotateTo(180)">--%>
        <%--180°--%>
        <%--</span>--%>
        <%--</button>--%>

    </div>
    <!-- /.docs-buttons -->

    <div class="col-md-4 docs-toggles">
        <!-- <h3 class="page-header">Toggles:</h3> -->
        <div class="btn-group btn-group-justified" data-toggle="buttons">
            <label class="btn btn-primary active">
                <input type="radio" class="sr-only" id="aspectRatio0" name="aspectRatio" value="1.7777777777777777">
            <span class="docs-tooltip" data-toggle="tooltip" title="aspectRatio: 16 / 9">
              16:9
            </span>
            </label>
            <label class="btn btn-primary">
                <input type="radio" class="sr-only" id="aspectRatio1" name="aspectRatio" value="1.3333333333333333">
            <span class="docs-tooltip" data-toggle="tooltip" title="aspectRatio: 4 / 3">
              4:3
            </span>
            </label>
            <label class="btn btn-primary">
                <input type="radio" class="sr-only" id="aspectRatio2" name="aspectRatio" value="1">
            <span class="docs-tooltip" data-toggle="tooltip" title="aspectRatio: 1 / 1">
              1:1
            </span>
            </label>
            <label class="btn btn-primary">
                <input type="radio" class="sr-only" id="aspectRatio3" name="aspectRatio" value="0.6666666666666666">
            <span class="docs-tooltip" data-toggle="tooltip" title="aspectRatio: 2 / 3">
              2:3
            </span>
            </label>
            <label class="btn btn-primary">
                <input type="radio" class="sr-only" id="aspectRatio4" name="aspectRatio" value="NaN">
            <span class="docs-tooltip" data-toggle="tooltip" title="aspectRatio: NaN">
              Free
            </span>
            </label>
        </div>

    </div>
    <!-- /.docs-toggles -->
</div>

<script src="/js/plugins/cropper/cropper.min.js"></script>
<script src="/js/plugins/cropper/main.js"></script>


