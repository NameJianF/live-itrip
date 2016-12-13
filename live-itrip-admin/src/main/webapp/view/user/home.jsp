<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>和旅行</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">

</head>

<body>

<div id="wrapper">

    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element" style="text-align: center;">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="/index.html">
                            <img alt="image" src="/img/he.png" style="width: 60px;height: auto"/>
                        </a>
                    </div>
                </li>

                <li class="active">
                    <a href="/view/profile.action"><i class="fa fa-diamond"></i> <span class="nav-label">Profile</span></a>
                </li>

                <li>
                    <a href="metrics.html"><i class="fa fa-pie-chart"></i> <span class="nav-label">Metrics</span> </a>
                </li>
                <li>
                    <a href="widgets.html"><i class="fa fa-flask"></i> <span class="nav-label">Widgets</span></a>
                </li>
                <li>
                    <a href="grid_options.html"><i class="fa fa-laptop"></i> <span class="nav-label">Grid options</span></a>
                </li>
                <%--<li class="special_link">--%>
                <%--<a href="package.html"><i class="fa fa-database"></i> <span class="nav-label">Package</span></a>--%>
                <%--</li>--%>
            </ul>
        </div>
    </nav>

    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0;background-color: white;">
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <span class="m-r-sm text-muted welcome-message">Welcome to iTrip.</span>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="profile_2.html#">
                            <i class="fa fa-envelope"></i> <span class="label label-warning">16</span>
                        </a>
                        <ul class="dropdown-menu dropdown-messages">
                            <li>
                                <div class="dropdown-messages-box">
                                    <a href="home.jsp" class="pull-left">
                                        <img alt="image" class="img-circle" src="/img/a7.jpg">
                                    </a>
                                    <div class="media-body">
                                        <small class="pull-right">46h ago</small>
                                        <strong>Mike Loreipsum</strong> started following <strong>Monica Smith</strong>.
                                        <br>
                                        <small class="text-muted">3 days ago at 7:58 pm - 10.06.2014</small>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="dropdown-messages-box">
                                    <a href="home.jsp" class="pull-left">
                                        <img alt="image" class="img-circle" src="/img/a4.jpg">
                                    </a>
                                    <div class="media-body ">
                                        <small class="pull-right text-navy">5h ago</small>
                                        <strong>Chris Johnatan Overtunk</strong> started following <strong>Monica
                                        Smith</strong>. <br>
                                        <small class="text-muted">Yesterday 1:21 pm - 11.06.2014</small>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="dropdown-messages-box">
                                    <a href="home.jsp" class="pull-left">
                                        <img alt="image" class="img-circle" src="/img/profile.jpg">
                                    </a>
                                    <div class="media-body ">
                                        <small class="pull-right">23h ago</small>
                                        <strong>Monica Smith</strong> love <strong>Kim Smith</strong>. <br>
                                        <small class="text-muted">2 days ago at 2:30 am - 11.06.2014</small>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="text-center link-block">
                                    <a href="mailbox.html">
                                        <i class="fa fa-envelope"></i> <strong>Read All Messages</strong>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="profile_2.html#">
                            <i class="fa fa-bell"></i> <span class="label label-primary">8</span>
                        </a>
                        <ul class="dropdown-menu dropdown-alerts">
                            <li>
                                <a href="mailbox.html">
                                    <div>
                                        <i class="fa fa-envelope fa-fw"></i> You have 16 messages
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="home.jsp">
                                    <div>
                                        <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                        <span class="pull-right text-muted small">12 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="grid_options.html">
                                    <div>
                                        <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                        <span class="pull-right text-muted small">4 minutes ago</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="text-center link-block">
                                    <a href="notifications.html">
                                        <strong>See All Alerts</strong>
                                        <i class="fa fa-angle-right"></i>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </li>


                    <li>
                        <a href="login.html">
                            <i class="fa fa-sign-out"></i> Log out
                        </a>
                    </li>
                </ul>
            </nav>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row m-b-lg m-t-lg">
                <div class="col-md-6">

                    <div class="profile-image">
                        <img src="/img/a4.jpg" class="img-circle circle-border m-b-md" alt="profile">
                    </div>
                    <div class="profile-info">
                        <div class="">
                            <div>
                                <h2 class="no-margins">
                                    Alex Smith
                                </h2>
                                <h4>Founder of Groupeq</h4>
                                <small>
                                    There are many variations of passages of Lorem Ipsum available, but the majority
                                    have suffered alteration in some form Ipsum available.
                                </small>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <table class="table small m-b-xs">
                        <tbody>
                        <tr>
                            <td>
                                <strong>142</strong> Projects
                            </td>
                            <td>
                                <strong>22</strong> Followers
                            </td>

                        </tr>
                        <tr>
                            <td>
                                <strong>61</strong> Comments
                            </td>
                            <td>
                                <strong>54</strong> Articles
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>154</strong> Tags
                            </td>
                            <td>
                                <strong>32</strong> Friends
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-3">
                    <small>Sales in last 24h</small>
                    <h2 class="no-margins">206 480</h2>
                    <div id="sparkline1"></div>
                </div>


            </div>
            <div class="row">

                <div class="col-lg-3">

                    <div class="ibox">
                        <div class="ibox-content">
                            <h3>About Alex Smith</h3>

                            <p class="small">
                                There are many variations of passages of Lorem Ipsum available, but the majority have
                                suffered alteration in some form, by injected humour, or randomised words which don't.
                                <br/>
                                <br/>
                                If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't
                                anything embarrassing
                            </p>

                            <p class="small font-bold">
                                <span><i class="fa fa-circle text-navy"></i> Online status</span>
                            </p>

                        </div>
                    </div>

                    <div class="ibox">
                        <div class="ibox-content">
                            <h3>Followers and friends</h3>
                            <p class="small">
                                If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't
                                anything embarrassing
                            </p>
                            <div class="user-friends">
                                <a href="profile_2.html"><img alt="image" class="img-circle" src="/img/a3.jpg"></a>
                                <a href="profile_2.html"><img alt="image" class="img-circle" src="/img/a1.jpg"></a>
                                <a href="profile_2.html"><img alt="image" class="img-circle" src="/img/a2.jpg"></a>
                                <a href="profile_2.html"><img alt="image" class="img-circle" src="/img/a4.jpg"></a>
                                <a href="profile_2.html"><img alt="image" class="img-circle" src="/img/a5.jpg"></a>
                                <a href="profile_2.html"><img alt="image" class="img-circle" src="/img/a6.jpg"></a>
                                <a href="profile_2.html"><img alt="image" class="img-circle" src="/img/a7.jpg"></a>
                                <a href="profile_2.html"><img alt="image" class="img-circle" src="/img/a8.jpg"></a>
                                <a href="profile_2.html"><img alt="image" class="img-circle" src="/img/a2.jpg"></a>
                                <a href="profile_2.html"><img alt="image" class="img-circle" src="/img/a1.jpg"></a>
                            </div>
                        </div>
                    </div>

                    <div class="ibox">
                        <div class="ibox-content">
                            <h3>Personal friends</h3>
                            <ul class="list-unstyled file-list">
                                <li><a href="profile_2.html"><i class="fa fa-file"></i> Project_document.docx</a></li>
                                <li><a href="profile_2.html"><i class="fa fa-file-picture-o"></i>
                                    Logo_zender_company.jpg</a></li>
                                <li><a href="profile_2.html"><i class="fa fa-stack-exchange"></i>
                                    Email_from_Alex.mln</a></li>
                                <li><a href="profile_2.html"><i class="fa fa-file"></i> Contract_20_11_2014.docx</a>
                                </li>
                                <li><a href="profile_2.html"><i class="fa fa-file-powerpoint-o"></i>
                                    Presentation.pptx</a></li>
                                <li><a href="profile_2.html"><i class="fa fa-file"></i> 10_08_2015.docx</a></li>
                            </ul>
                        </div>
                    </div>

                    <div class="ibox">
                        <div class="ibox-content">
                            <h3>Private message</h3>

                            <p class="small">
                                Send private message to Alex Smith
                            </p>

                            <div class="form-group">
                                <label>Subject</label>
                                <input type="email" class="form-control" placeholder="Message subject">
                            </div>
                            <div class="form-group">
                                <label>Message</label>
                                <textarea class="form-control" placeholder="Your message" rows="3"></textarea>
                            </div>
                            <button class="btn btn-primary btn-block">Send</button>

                        </div>
                    </div>

                </div>

                <div class="col-lg-5">

                    <div class="social-feed-box">

                        <div class="pull-right social-action dropdown">
                            <button data-toggle="dropdown" class="dropdown-toggle btn-white">
                                <i class="fa fa-angle-down"></i>
                            </button>
                            <ul class="dropdown-menu m-t-xs">
                                <li><a href="profile_2.html#">Config</a></li>
                            </ul>
                        </div>
                        <div class="social-avatar">
                            <a href="profile_2.html" class="pull-left">
                                <img alt="image" src="/img/a1.jpg">
                            </a>
                            <div class="media-body">
                                <a href="profile_2.html#">
                                    Andrew Williams
                                </a>
                                <small class="text-muted">Today 4:21 pm - 12.06.2014</small>
                            </div>
                        </div>
                        <div class="social-body">
                            <p>
                                Many desktop publishing packages and web page editors now use Lorem Ipsum as their
                                default model text, and a search for 'lorem ipsum' will uncover many web sites still
                                in their infancy. Packages and web page editors now use Lorem Ipsum as their
                                default model text.
                            </p>

                            <div class="btn-group">
                                <button class="btn btn-white btn-xs"><i class="fa fa-thumbs-up"></i> Like this!</button>
                                <button class="btn btn-white btn-xs"><i class="fa fa-comments"></i> Comment</button>
                                <button class="btn btn-white btn-xs"><i class="fa fa-share"></i> Share</button>
                            </div>
                        </div>
                        <div class="social-footer">
                            <div class="social-comment">
                                <a href="profile_2.html" class="pull-left">
                                    <img alt="image" src="/img/a1.jpg">
                                </a>
                                <div class="media-body">
                                    <a href="profile_2.html#">
                                        Andrew Williams
                                    </a>
                                    Internet tend to repeat predefined chunks as necessary, making this the first true
                                    generator on the Internet. It uses a dictionary of over 200 Latin words.
                                    <br/>
                                    <a href="profile_2.html#" class="small"><i class="fa fa-thumbs-up"></i> 26 Like
                                        this!</a> -
                                    <small class="text-muted">12.06.2014</small>
                                </div>
                            </div>

                            <div class="social-comment">
                                <a href="profile_2.html" class="pull-left">
                                    <img alt="image" src="/img/a2.jpg">
                                </a>
                                <div class="media-body">
                                    <a href="profile_2.html#">
                                        Andrew Williams
                                    </a>
                                    Making this the first true generator on the Internet. It uses a dictionary of.
                                    <br/>
                                    <a href="profile_2.html#" class="small"><i class="fa fa-thumbs-up"></i> 11 Like
                                        this!</a> -
                                    <small class="text-muted">10.07.2014</small>
                                </div>
                            </div>

                            <div class="social-comment">
                                <a href="profile_2.html" class="pull-left">
                                    <img alt="image" src="/img/a3.jpg">
                                </a>
                                <div class="media-body">
                                    <textarea class="form-control" placeholder="Write comment..."></textarea>
                                </div>
                            </div>

                        </div>

                    </div>

                    <div class="social-feed-box">

                        <div class="pull-right social-action dropdown">
                            <button data-toggle="dropdown" class="dropdown-toggle btn-white">
                                <i class="fa fa-angle-down"></i>
                            </button>
                            <ul class="dropdown-menu m-t-xs">
                                <li><a href="profile_2.html#">Config</a></li>
                            </ul>
                        </div>
                        <div class="social-avatar">
                            <a href="profile_2.html" class="pull-left">
                                <img alt="image" src="/img/a6.jpg">
                            </a>
                            <div class="media-body">
                                <a href="profile_2.html#">
                                    Andrew Williams
                                </a>
                                <small class="text-muted">Today 4:21 pm - 12.06.2014</small>
                            </div>
                        </div>
                        <div class="social-body">
                            <p>
                                Many desktop publishing packages and web page editors now use Lorem Ipsum as their
                                default model text, and a search for 'lorem ipsum' will uncover many web sites still
                                in their infancy. Packages and web page editors now use Lorem Ipsum as their
                                default model text.
                            </p>
                            <p>
                                Lorem Ipsum as their
                                default model text, and a search for 'lorem ipsum' will uncover many web sites still
                                in their infancy. Packages and web page editors now use Lorem Ipsum as their
                                default model text.
                            </p>
                            <img src="/img/gallery/3.jpg" class="img-responsive">
                            <div class="btn-group">
                                <button class="btn btn-white btn-xs"><i class="fa fa-thumbs-up"></i> Like this!</button>
                                <button class="btn btn-white btn-xs"><i class="fa fa-comments"></i> Comment</button>
                                <button class="btn btn-white btn-xs"><i class="fa fa-share"></i> Share</button>
                            </div>
                        </div>
                        <div class="social-footer">
                            <div class="social-comment">
                                <a href="profile_2.html" class="pull-left">
                                    <img alt="image" src="/img/a1.jpg">
                                </a>
                                <div class="media-body">
                                    <a href="profile_2.html#">
                                        Andrew Williams
                                    </a>
                                    Internet tend to repeat predefined chunks as necessary, making this the first true
                                    generator on the Internet. It uses a dictionary of over 200 Latin words.
                                    <br/>
                                    <a href="profile_2.html#" class="small"><i class="fa fa-thumbs-up"></i> 26 Like
                                        this!</a> -
                                    <small class="text-muted">12.06.2014</small>
                                </div>
                            </div>

                            <div class="social-comment">
                                <a href="profile_2.html" class="pull-left">
                                    <img alt="image" src="/img/a2.jpg">
                                </a>
                                <div class="media-body">
                                    <a href="profile_2.html#">
                                        Andrew Williams
                                    </a>
                                    Making this the first true generator on the Internet. It uses a dictionary of.
                                    <br/>
                                    <a href="profile_2.html#" class="small"><i class="fa fa-thumbs-up"></i> 11 Like
                                        this!</a> -
                                    <small class="text-muted">10.07.2014</small>
                                </div>
                            </div>

                            <div class="social-comment">
                                <a href="profile_2.html" class="pull-left">
                                    <img alt="image" src="/img/a8.jpg">
                                </a>
                                <div class="media-body">
                                    <a href="profile_2.html#">
                                        Andrew Williams
                                    </a>
                                    Making this the first true generator on the Internet. It uses a dictionary of.
                                    <br/>
                                    <a href="profile_2.html#" class="small"><i class="fa fa-thumbs-up"></i> 11 Like
                                        this!</a> -
                                    <small class="text-muted">10.07.2014</small>
                                </div>
                            </div>

                            <div class="social-comment">
                                <a href="profile_2.html" class="pull-left">
                                    <img alt="image" src="/img/a3.jpg">
                                </a>
                                <div class="media-body">
                                    <textarea class="form-control" placeholder="Write comment..."></textarea>
                                </div>
                            </div>

                        </div>

                    </div>


                </div>
                <div class="col-lg-4 m-b-lg">
                    <div id="vertical-timeline" class="vertical-container light-timeline no-margins">
                        <div class="vertical-timeline-block">
                            <div class="vertical-timeline-icon navy-bg">
                                <i class="fa fa-briefcase"></i>
                            </div>

                            <div class="vertical-timeline-content">
                                <h2>Meeting</h2>
                                <p>Conference on the sales results for the previous year. Monica please examine sales
                                    trends in marketing and products. Below please find the current status of the sale.
                                </p>
                                <a href="profile_2.html#" class="btn btn-sm btn-primary"> More info</a>
                                    <span class="vertical-date">
                                        Today <br>
                                        <small>Dec 24</small>
                                    </span>
                            </div>
                        </div>

                        <div class="vertical-timeline-block">
                            <div class="vertical-timeline-icon blue-bg">
                                <i class="fa fa-file-text"></i>
                            </div>

                            <div class="vertical-timeline-content">
                                <h2>Send documents to Mike</h2>
                                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem
                                    Ipsum has been the industry's standard dummy text ever since.</p>
                                <a href="profile_2.html#" class="btn btn-sm btn-success"> Download document </a>
                                    <span class="vertical-date">
                                        Today <br>
                                        <small>Dec 24</small>
                                    </span>
                            </div>
                        </div>

                        <div class="vertical-timeline-block">
                            <div class="vertical-timeline-icon lazur-bg">
                                <i class="fa fa-coffee"></i>
                            </div>

                            <div class="vertical-timeline-content">
                                <h2>Coffee Break</h2>
                                <p>Go to shop and find some products. Lorem Ipsum is simply dummy text of the printing
                                    and typesetting industry. Lorem Ipsum has been the industry's. </p>
                                <a href="profile_2.html#" class="btn btn-sm btn-info">Read more</a>
                                <span class="vertical-date"> Yesterday <br><small>Dec 23</small></span>
                            </div>
                        </div>

                        <div class="vertical-timeline-block">
                            <div class="vertical-timeline-icon yellow-bg">
                                <i class="fa fa-phone"></i>
                            </div>

                            <div class="vertical-timeline-content">
                                <h2>Phone with Jeronimo</h2>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iusto, optio, dolorum
                                    provident rerum aut hic quasi placeat iure tempora laudantium ipsa ad debitis unde?
                                    Iste voluptatibus minus veritatis qui ut.</p>
                                <span class="vertical-date">Yesterday <br><small>Dec 23</small></span>
                            </div>
                        </div>

                        <div class="vertical-timeline-block">
                            <div class="vertical-timeline-icon navy-bg">
                                <i class="fa fa-comments"></i>
                            </div>

                            <div class="vertical-timeline-content">
                                <h2>Chat with Monica and Sandra</h2>
                                <p>Web sites still in their infancy. Various versions have evolved over the years,
                                    sometimes by accident, sometimes on purpose (injected humour and the like). </p>
                                <span class="vertical-date">Yesterday <br><small>Dec 23</small></span>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </div>

        <div class="footer">
            <div class="pull-right">
                <a href="http://www.itrip.live">
                    www.itrip.live</a>
            </div>
            <div>
                <strong>Copyright</strong> 和旅行 &copy; 2015
            </div>
        </div>
    </div>
</div>


<!-- Mainly scripts -->
<script src="/js/jquery-2.1.1.js"></script>
<script src="/js/bootstrap.js"></script>
<script src="/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="/js/inspinia.js"></script>
<script src="/js/plugins/pace/pace.min.js"></script>

<!-- Sparkline -->
<script src="/js/plugins/sparkline/jquery.sparkline.min.js"></script>

<script>
    $(document).ready(function () {


        $("#sparkline1").sparkline([34, 43, 43, 35, 44, 32, 44, 48], {
            type: 'line',
            width: '100%',
            height: '50',
            lineColor: '#1ab394',
            fillColor: "transparent"
        });


    });
</script>

</body>

</html>
