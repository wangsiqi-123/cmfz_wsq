<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
            $("#btntable").jqGrid({
                    url : "${path}/banner/queryByBanner",
                    editurl:"${path}/banner/edit",
                    datatype : "json",
                    rowNum : 2,
                    styleUI:"Bootstrap",
                    rowList : [ 1, 3, 5 ],
                    pager : '#bnpager',
                    viewrecords : true,//是否展示总条数
                    autowidth: true,
                    height: "auto",
                    colNames : [ 'id', '名称', '图片', '描述', '状态','上传时间'],
                    colModel : [
                        {name : 'id',width : 55},
                        {name : 'name',editable:true,width : 90},
                        {name : 'img_path',editable:true,width : 100,align:"center",edittype:"file",
                            formatter:function (cellValue) {
                                return "<img src='${path}/upload/photo/"+cellValue+"' style='width:50px;heigth:40px' >"
                            }
                        },
                        {name : 'description',editable:true,width : 80,align : "right"},
                        {name : 'status',width : 80,align : "center",
                            formatter:function (cellValue,option,row) {
                            if(cellValue==1){
                                return "<button class='btn btn-danger' onclick='updateStatus(\""+row.id+"\",\""+cellValue+"\")'>不展示</button>"
                            }else {
                                return "<button class='btn btn-success' onclick='updateStatus(\""+row.id+"\",\""+cellValue+"\")'>展示</button>"
                            }

                            }
                        },
                        {name : 'up_date',width : 80,align : "right"},
                    ],

                });
            $("#btntable").jqGrid('navGrid', '#bnpager', {
                edit : true,
                add : true,
                del : true,
                addtext:"添加",
                edittext:"修改",
                deltext:"删除"},
                {

                    closeAfterEdit: true,
                    beforeShowForm: function (obj) {
                        obj.find("#img_path").attr("disabled", true);

                        return "saaa";
                    }
                },//执行修改额外配置
                {
                    closeAfterAdd:true,
                    afterSubmit:function (data) {
                        $.ajaxFileUpload({
                            url:"${path}/banner/bannerUpload",
                            datatype:"json",
                            type:"post",
                            fileElementId:"img_path",
                            data:{id:data.responseText},
                            success:function () {
                                //执行刷新页面操作
                                $("#btntable").trigger("reloadGrid");
                            }
                        });
                        return "sss";
                    }
                },//执行添加操作的额外配置
                {}//执行删除操作的额外配置
                );

    });
    //修改状态
    function  updateStatus(id,status) {
        $.ajax({
            url:"${path}/banner/uploadStatus",
            type:"post",
            dataType:"json",
            data:{"id":id,"status":status},
            success:function () {
               // alert("修改成功")
                //刷新页面
                $("#btntable").trigger("reloadGrid");
            }
        })
        return "aaa";
    }
    
</script>
<!--初始化面板-->
<div class="panel panel-info">
    <div class="panel panel-heading">
       <h1>轮播图信息</h1>
    </div>
   <ul class="nav nav-tabs">
     <li class="active"><a href="#">轮播图信息</a> </li>
   </ul>
    <!--初始化表单-->
    <table id="btntable"/>
    <%--定义分页工具栏--%>
    <div id="bnpager"></div>


</div>

<body>





</body>
</html>