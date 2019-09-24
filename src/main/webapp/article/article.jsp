<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.create('#editor_id',{
            uploadJson:"${path}/editor/uploadEditor",//制定上传图片的路径
            filePostName:"photo",//设置上传图片的名称
            allowFileManager:true,//是否展示浏览器远程图片按钮
            fileManagerJson:"${path}/editor/queryPhotos",//制定浏览远程图片的路径
            afterBlur:function () {//失去焦点之后执行的内容
                this.sync();//犟kindetior中的内容同步到表单

        }

        });

</script>



<script>
    $(function () {
            $("#Articletable").jqGrid({
                    url : "${path}/article/selectByPage",
                    editurl:"${path}/article/articleEdit",
                    datatype : "json",
                    rowNum : 2,
                    styleUI:"Bootstrap",
                    rowList : [ 2, 3, 5 ],
                    pager : '#Articlepager',
                    viewrecords : true,//是否展示总条数
                    autowidth: true,
                    height: "auto",
                    colNames : ['ID','标题','作者','内容','上传时间','状态','上师id'],
                    colModel : [
                        {name : 'id',width : 55,align:"center"},
                        {name : 'title',width : 55,align:"center"},
                        {name : 'author',width : 55,align:"center"},
                        {name : 'content',width : 55,align:"center"},
                        {name : 'crea_date',width : 55,align:"center"},
                        {name : 'status',width : 55,align:"center",
                            formatter:function (cellValue,option,row) {
                                if(cellValue==1){
                                    return "<button class='btn btn-danger' onclick='updateStatus(\""+row.id+"\",\""+cellValue+"\")'>不展示</button>"
                                }else {
                                    return "<button class='btn btn-success' onclick='updateStatus(\""+row.id+"\",\""+cellValue+"\")'>展示</button>"
                                }

                            }},
                        {name : 'guru_id',width : 55,align:"center"}

                    ]

                });
            $("#Articletable").jqGrid('navGrid', '#Articlepager', {
                edit : false,
                add : false,
                del : true,
                deltext:"删除"
                },
                {},//执行修改额外配置
                {},//执行添加操作的额外配置
                {}//执行删除操作的额外配置
                );
            //展示文章信息
            $("#btn1").click(function () {
               var rowId= $("#Articletable").jqGrid("getGridParam","selrow");//最后选择的行id
                if(rowId!=null){
                    //根据行id获取行数据
                    var row= $("#Articletable").jqGrid("getRowData",rowId);
                    //给input框设置数据
                     $("#title").val(row.title);
                    $("#author").val(row.author);
                    KindEditor.html("#editor_id",row.content);

                    //展示模态框
                    $("#MyModels").modal("show");
                    //设置按钮
                    $("#MyFoot").html("<button class='btn btn-primary' onclick='updateArticle(\""+rowId+"\")'>确认修改</button>" +
                        "<button  class='btn btn-default' data-dismiss='modal'>关闭</button>")

                }else{
                    alert("请选择一行");
                }

            })
    //    添加文章信息
        $("#btn2").click(function () {
            //清空表单
            $("#Myform")[0].reset();
            //清空kindeditor
            KindEditor.html("#editor_id","");
            //展示模态框
            $("#MyModels").modal("show");
            $("#MyFoot").html("<button class='btn btn-primary' onclick='addArticle()'>添加</button><button  class='btn btn-default' data-dismiss='modal'>关闭</button>")
        });

        //    删除文章信息
        $("#btn3").click(function () {
            var rowId= $("#Articletable").jqGrid("getGridParam","selrow");//最后选择的行id
            if(rowId!=null){
                $.ajax({
                    url:"${path}/article/delete?id="+rowId,
                    type:"get",
                    dataType:"json",
                    success:function () {
                        // alert("修改成功")
                        //刷新页面
                        $("#Articletable").trigger("reloadGrid");
                    }
                })


            }else{
                alert("请选择一行");
            }


            });



    });
    //修改状态
    function  updateStatus(id,status) {
        $.ajax({
            url:"${path}/article/editStatus",
            type:"post",
            dataType:"json",
            data:{"id":id,"status":status},
            success:function () {
               // alert("修改成功")
                //刷新页面
                $("#Articletable").trigger("reloadGrid");
            }
        })
        return "aaa";
    }
    
    
    //点击添加按钮添加文章
    function addArticle() {
        $.ajax({
            url:"${path}/article/add",
            type:"post",
            dataType:"json",
            data:$("#Myform").serialize(),
            success:function () {
                //关闭模态框
                $("#MyModels").modal("hide");
                //刷新页面
                $("#Articletable").trigger("reloadGrid");
            }
        })
    }

    //点击提交按钮修改文章信息
    function updateArticle(rowId) {
        $.ajax({
            url:"${path}/article/update?id="+rowId,
            type:"post",
            dataType:"json",
            data:$("#Myform").serialize(),
            success:function () {
                //关闭模态框
                $("#MyModels").modal("hide");
                //刷新页面
                $("#Articletable").trigger("reloadGrid");
            }
        })
    }




</script>
<!--初始化面板-->
<br class="panel panel-info">
    <div class="panel panel-heading">
       <h1>文章管理</h1>
    </div>
   <ul class="nav nav-tabs">
     <li class="active"><a href="#">文章信息</a> </li>
   </ul>

    <div class="panel panel-body">
    <button id="btn1" class="btn btn-success">文章信息</button>&nbsp;
    <button id="btn2" class="btn btn-warning">添加文章</button>&nbsp;
    <button id="btn3" class="btn btn-info">删除文章</button>
    </div>




    <%--<form class="navbar-form navbar-right">--%>
        <%--<div class="form-group">--%>
            <%--<input type="text" class="form-control" placeholder="请输入搜索内容">--%>
        <%--</div>--%>
        <%--<button type="submit" class="btn btn-default">点击搜索</button>--%>
    <%--</form></br></br>--%>



    <!--初始化表单-->
    <table id="Articletable"/>
    <%--定义分页工具栏--%>
    <div id="Articlepager"></div>

<div id="MyModels" class="modal fade"  role="dialog" >
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:730px">
            <%--模态框标题--%>
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">展示文章信息</h4>
            </div>
                <%--模态框内容--%>
            <div class="modal-body" >
                <%--放一个表单--%>
                <form id="Myform">
                    <div class="input-group"   >
                        <span  class="input-group-addon" id="basic-addon1">名字</span>
                        <input id="title" name="title" type="text" class="form-control"  aria-describedby="basic-addon1">
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon2">作者</span>
                        <input id="author" name="author" type="text" class="form-control"  aria-describedby="basic-addon1">
                    </div><br>
                    <%--输入框--%>
                    <div   align="center" class="input-group">
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;"></textarea>
                    </div>

                </form>

            </div>
            <div id="MyFoot" class="modal-footer">
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                <%--<button type="button" class="btn btn-primary">提交</button>--%>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->





<body>





</body>
</html>























