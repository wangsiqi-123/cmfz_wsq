<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
            $("#usertable").jqGrid({
                    url : "${path}/user/selectByPage",
                    editurl:"${path}/user/addUser",
                    datatype : "json",
                    rowNum : 2,
                    styleUI:"Bootstrap",
                    rowList : [ 1, 3, 5 ],
                    pager : '#userpager',
                    viewrecords : true,//是否展示总条数
                    autowidth: true,
                    height: "auto",
                    colNames : ['id','头像','姓名','法名','性别','密码','状态','手机号','注册时间'],
                    colModel : [
                        {name : 'id',width : 55,align:"center"},
                        {name : 'avater',width : 55,align:"center",
                            formatter:function (cellValue) {
                                return "<img src='${path}/upload/photo/"+cellValue+"' style='width:50px;heigth:40px' >"
                            }
                        },
                        {name : 'name',width : 55,align:"center",editable:true},
                        {name : 'law_name',width : 55,align:"center",editable:true},
                        {name : 'sex',width : 55,align:"center",editable:true},
                        {name : 'password',width : 55,align:"center",editable:true},
                        {name : 'status',width : 55,align:"center",
                            formatter:function (cellValue,option,row) {
                                if(cellValue==1){
                                    return "<button class='btn btn-danger' onclick='updateStatus(\""+row.id+"\",\""+cellValue+"\")'>不展示</button>"
                                }else {
                                    return "<button class='btn btn-success' onclick='updateStatus(\""+row.id+"\",\""+cellValue+"\")'>展示</button>"
                                }

                            }
                            },
                        {name : 'phone',width : 55,align:"center",editable:true},
                        {name : 'crea_date',width : 55,align:"center"},

                    ],

                });
            $("#usertable").jqGrid('navGrid', '#userpager', {
                edit : false,
                add : true,
                del : false,
                },
                {},//执行修改额外配置
                {
                    closeAfterAdd:true
                },//执行添加操作的额外配置
                {}//执行删除操作的额外配置
                );

    });

    //导出按钮
    function exp() {
        $.ajax({
            url:"${path}/user/exportUsers",
            type:"post",
            dataType:"json",
            success:function (date) {
                alert(date.message);
            }
        })
    }

    <%--//添加用户按钮--%>
    <%--function exp() {--%>
        <%--$.ajax({--%>
            <%--url:"${path}/user/addUser",--%>
            <%--type:"post",--%>
            <%--dataType:"json",--%>
            <%--success:function (date) {--%>
                <%--alert(date.message);--%>
            <%--}--%>
        <%--})--%>
    <%--}--%>


    //修改状态
    function  updateStatus(id,status) {
        $.ajax({
            url:"${path}/user/editStatus",
            type:"post",
            dataType:"json",
            data:{"id":id,"status":status},
            success:function () {
               // alert("修改成功")
                //刷新页面
                $("#usertable").trigger("reloadGrid");
            }
        })
        return "aaa";
    }
    
    //发送手机验证码

    function phoneSend() {

        var phoneNumber=$("#phone").val();
        alert(phoneNumber)
        $.ajax({
            url:"${path}/user/phoneMessage",
            type:"post",
            dataType:"json",
            data:{"phone":phoneNumber},
            success:function (date) {
                 alert(date)

            }
        })

    }
    
</script>
<!--初始化面板-->
<div class="panel panel-info">
    <div class="panel panel-heading">
       <h1>用户信息</h1>
    </div>
   <ul class="nav nav-tabs">
     <li class="active"><a href="#">用户信息</a> </li>
   </ul>
    <br/>
    <button onclick="exp()" class="btn btn-success">导出用户信息</button>
    <button class="btn btn-warning">导入用户</button>
    <button onclick="addUser()" class="btn btn-info">添加用户</button><br/><br/>
    <div align="right">
        <input type="text" id="phone"/>
        <button id="phoneId" class="btn btn-info" onclick="phoneSend()">发送验证码</button>
    </div>




    <!--初始化表单-->
    <table id="usertable"/>
    <%--定义分页工具栏--%>
    <div id="userpager"></div>


</div>

<body>





</body>
</html>