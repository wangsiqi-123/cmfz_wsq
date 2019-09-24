<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
    $(function () {
        $("#abtable").jqGrid(
            {
                url : "${path}/album/selectByPage",
                editurl:"${path}/album/edit",
                datatype : "json",
                autowidth:true,
                height : "auto",
                styleUI:"Bootstrap",
                rowNum : 2,
                rowList : [ 1, 3, 5 ],
                pager : '#abpage',
                viewrecords : true,
                colNames : [ 'ID', '名称', '封面', '作者', '集数','评分','内容','播音员', '上传时间' ],
                colModel : [
                    {name : 'id',index : 'id',  width : 55},
                    {name : 'title',editable:true,width : 90},
                    {name : 'cover',editable:true,width : 100,edittype:"file",
                        formatter:function (cellValue) {
                            return "<img src='${path}/upload/photo/"+cellValue+"' style='width:50px;heigth:40px' >"
                        }
                    },
                    {name : 'author',editable:true,width : 80,align : "right"},
                    {name : 'counts',editable:true,width : 80,align : "right"},
                    {name : 'score',editable:true,width : 80,align : "right"},
                    {name : 'content',editable:true,width : 80,align : "right"},
                    {name : 'broadcast',editable:true,width : 80,align : "right"},
                    {name : 'crea_date',index : 'note',width : 150,sortable : false}
                ],

                subGrid : true,//是否开启子表格
                subGridRowExpanded : function(subgrid_id, row_id) {//subgrid_id 子表格id row_id 行id
                    //开启子表格
                    addSubGrid(subgrid_id,row_id);
                }

            });
        $("#abtable").jqGrid('navGrid', '#abpage', {
            add : true,
            edit : true,
            del : true,
            addtext:"添加",
            edittext:"修改",
            deltext:"删除"},
            {//执行修改的额外功能
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    obj.find("#cover").attr("disabled", true);
                    return "saaa";
                }
            },
            {//执行添加的额外功能
                closeAfterAdd:true,
                afterSubmit:function (data) {
                    $.ajaxFileUpload({
                        url:"${path}/album/albumUpload",
                        datatype:"json",
                        type:"post",
                        fileElementId:"cover",
                        data:{id:data.responseText},
                        success:function () {
                            //执行刷新页面操作
                            $("#abtable").trigger("reloadGrid");
                        }
                    });
                    return "sss";
                }
            },
            {} //执行删除的额外功能
            );
    });

    //子表格
    function addSubGrid(subgridId, rowId) {
        //tableid
        var subgridTableId=subgridId+"table";
        //工具栏id
        var pagerId=subgridId+"page";
        //在子表格中创建一个表单                                    创建一个工具栏
        $("#"+subgridId).html("<table id='"+subgridTableId+"' /><div id='"+pagerId+"' />")


        //初始子表格
        $("#" + subgridTableId).jqGrid(
            {
                url : "${path}/chapter/queryByPage?albumId="+rowId,
                editurl:"${path}/chapter/edit?albumId="+rowId,
                autowidth:true,
                height : "auto",
                styleUI:"Bootstrap",
                rowNum : 2,
                rowList : [ 1, 3, 5],
                datatype : "json",
                colNames : [ 'ID', '名字', '路径', '大小','时长','上传时间','操作' ],
                pager : "#"+pagerId,
                colModel : [
                    {name : "id",width : 80,key : true},
                    {name : "name",  width : 130,editable:true},
                    {name : "url",width : 70,align : "right",edittype:"file",editable:true},
                    {name : "size",width : 70,align : "right"},
                    {name : "duration",width : 70,align : "right"},
                    {name : "up_date",width : 70,align : "center"},
                    {name : "url",width : 70,align : "center",
                    formatter:function (value) {
                        return "<a href='#'> <span class='glyphicon glyphicon-play-circle ' onclick='play(\""+value+"\")'/></a>&emsp;&emsp; <a href='#'><span class='glyphicon glyphicon-download' onclick='downloads(\""+value+"\")'/></a>"
                    }
                    },
                ]

            });
        //增删改方法
        $("#" + subgridTableId).jqGrid('navGrid',
            "#" + pagerId, {
                edit : true,
                add : true,
                del : true,
                addtext:"添加",
                edittext:"修改",
                deltext:"删除"},
                {
                    closeAfterEdit:true,
                    beforeShowForm:function(obj) {
                        obj.find("#url").attr("disabled", true);
                        return "aaaa";
                    }

                },//修改的额外方法
                {
                    //执行添加的额外功能
                    closeAfterAdd:true,
                    afterSubmit:function (data) {
                        $.ajaxFileUpload({
                            url:"${path}/chapter/uploadChapter",
                            datatype:"json",
                            type:"post",
                            fileElementId:"url",
                            data:{id:data.responseText},
                            success:function () {
                                //执行刷新页面操作
                                $("#"+subgridTableId).trigger("reloadGrid");
                            }
                        });
                        return "sss";
                    }
                },//添加的额外方法
                {}
                );
    }
    function play(value) {
        /*展示音频标签*/
        $("#playAudioDiv").modal("show");
        /*传入音频路径*/
        $("#playAudioId").attr("src", "${path}/uoload/music/" + value);



    }
    function downloads(value) {


        location.href="${path}/chapter/downloadAudio?url="+value
    }


</script>



<!--初始化面板-->
<div class="panel panel-info">
    <div class="panel panel-heading">
       <h1>专辑信息</h1>
    </div>
   <ul class="nav nav-tabs">
     <li class="active"><a href="#">专辑信息</a> </li>
   </ul>
    <!--初始化表单-->
    <table id="abtable"/>
    <%--定义分页工具栏--%>
    <div id="abpage"></div>

    <%--在线播放音频--%>
    <div id="playAudioDiv" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <audio id="playAudioId" src="" controls></audio>
        </div>
    </div>
</div>

<body>





</body>
