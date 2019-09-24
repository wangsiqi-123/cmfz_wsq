<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script charset="utf-8" src="${path}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.ready(function(K) {
        window.editor = K.create('#editor_id',{
            uploadJson:"${path}/edit/uploadEditor",//制定上传图片的路径
            filePostName:"photo",//设置上传图片的名称
            allowFileManager:true,//是否展示浏览器远程图片按钮
            fileManagerJson:"${path}/editor/queryPhotos",//制定浏览远程图片的路径

        });
    });
</script>



<div align="center">
<textarea id="editor_id" name="content" style="width:700px;height:300px;">
&lt;strong&gt;HTML内容&lt;/strong&gt;
</textarea>
</div>