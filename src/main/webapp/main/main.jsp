<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>

    <!--顶部导航-->
    <nav class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#"><strong>持明法州管理系统</strong></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">


            <ul class="nav navbar-nav navbar-right">
                <li>
                <a href="#">欢迎
                    <span style="color: #2aabd2">${admin.username}</span>
                </a>
            </li>
                <li>
                    <a href="${path}/admin/logout">退出登录
                        <span class=" glyphicon glyphicon-log-out " aria-hidden="true"></span></a>

                </li>

            </ul>
        </div>

    </nav>

    <!--栅格系统-->
        <!--左边手风琴部分-->
        <!--巨幕开始-->
        <!--右边轮播图部分-->
        <!--页脚-->
    <!--栅格系统-->

    <div class="container-fluid">

            <div class="col-md-2 column">
                <div class="panel-group" id="panel-69841">
                    <div class="panel panel-default panel-success text-center">
                        <div class="panel-heading">
                            <a class="panel-title " data-toggle="collapse" data-parent="#panel-69841" href="#panel-element-328181">用户管理</a>
                        </div>
                        <div id="panel-element-328181" class="panel-collapse collapse">
                            <div class="panel-body">
                                <span class="btn btn-warning"><a href="javascript:$('#mainId').load('${path}/user/user.jsp')">用户信息</a> </span><br/><br/>
                                <span class="btn btn-warning"><a href="javascript:$('#mainId').load('${path}/user/goeasysend.jsp')">用户统计</a> </span><br/><br/>
                                <span class="btn btn-warning"><a href="javascript:$('#mainId').load('${path}/user/chinaMap.jsp')">用户分布</a> </span><br/>
                            </div>

                        </div>
                    </div><br/>
                    <div class="panel panel-default panel-danger text-center">
                        <div class="panel-heading">
                            <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-69841" href="#panel-element-821084">轮播图管理</a>
                        </div>
                        <div id="panel-element-821084" class="panel-collapse collapse">
                            <div class="panel-body">
                                <button class="btn btn-warning"><a href="javascript:$('#mainId').load('${path}/banner/banner.jsp')">轮播图信息</a> </button><br/>
                            </div>
                        </div>
                    </div><br/>
                    <div class="panel panel-default panel-warning text-center">
                        <div class="panel-heading">
                            <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-69841" href="#panel-element-821085">专辑管理</a>
                        </div>
                        <div id="panel-element-821085" class="panel-collapse collapse">
                            <div class="panel-body">
                                <button class="btn btn-warning">
                                    <a href="javascript:$('#mainId').load('${path}/album/album.jsp')">专辑信息</a>
                                </button><br/>
                            </div>
                        </div>
                    </div><br/>
                    <div class="panel panel-default panel-info text-center">
                        <div class="panel-heading">
                            <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-69841" href="#panel-element-821086">文章管理</a>
                        </div>
                        <div id="panel-element-821086" class="panel-collapse collapse">
                            <div class="panel-body">
                                <button class="btn btn-warning">
                                    <a href="javascript:$('#mainId').load('${path}/article/article.jsp')">文章管理</a>
                                </button><br/>
                            </div>
                        </div>
                    </div><br/>
                    <div class="panel panel-default panel-primary text-center">
                        <div class="panel-heading">
                            <a class="panel-title collapsed" data-toggle="collapse" data-parent="#panel-69841" href="#panel-element-821087">上师管理</a>
                        </div>
                        <div id="panel-element-821087" class="panel-collapse collapse">
                            <div class="panel-body">
                                Anim pariatur cliche...
                            </div>
                        </div>
                    </div><br/>
                    <div class="panel panel-default panel-success text-center">
                        <div class="panel-heading">
                            <a class="panel-title " data-toggle="collapse" data-parent="#panel-69841" href="#panel-element-3281810">管理员管理</a>
                        </div>
                        <div id="panel-element-3281810" class="panel-collapse collapse">
                            <div class="panel-body">
                                <span class="btn btn-warning"><a href="">用户信息</a> </span><br/><br/>
                                <span class="btn btn-warning"><a href="">用户统计</a> </span><br/><br/>
                                <span class="btn btn-warning"><a href="">用户分布</a> </span><br/>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-10 column" id="mainId">

                <div class="jumbotron well">
                    <p>
                       <h2><strong>欢迎访问持明法州后台管理系统</strong></h2>
                    </p>
                </div>
                <div class="carousel slide" id="carousel-82418">
                    <ol class="carousel-indicators">
                        <li class="active" data-slide-to="0" data-target="#carousel-82418">
                        </li>
                        <li data-slide-to="1" data-target="#carousel-82418">
                        </li>
                        <li data-slide-to="2" data-target="#carousel-82418">
                        </li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="item active">
                            <img alt="" src="${path}/bootstrap/img/shouye.jpg" />
                            <div class="carousel-caption">


                            </div>
                        </div>
                        <div class="item">
                            <img alt="" src="${path}/bootstrap/img/1.jpg" />
                            <div class="carousel-caption">

                            </div>
                        </div>
                        <div class="item">
                            <img alt="" src="${path}/bootstrap/img/2.png" />
                            <div class="carousel-caption">


                            </div>
                        </div>
                    </div> <a class="left carousel-control" href="#carousel-82418" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-82418" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
                </div>
                <div class="panel-footer text-center">www.爱喝汽水的王机智.com</div>
            </div>

    </div>

</body>
</html>
