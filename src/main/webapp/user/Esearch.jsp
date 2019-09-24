<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script src="${path}/js/echarts.js"></script>
<script src="${path}/js/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        $.get("${path}/user/queryByMonth",function(data){
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '用户注册量趋势图',
                    show:true,
                    link:"${path}/main/main.jsp"
                },
                tooltip: {},
                legend: {
                    data:['男孩子','女孩子']
                },
                xAxis: {
                    data: data.month
                },
                yAxis: {},
                series: [{
                    name: '男孩子',
                    type: 'bar',
                    data: data.boys
                },{
                    name: '女孩子',
                    type: 'bar',
                    data: data.grils
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

            },"json");





    });



</script>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <div id="main" style="width: 600px;height: 400px;"></div>


</body>
</html>






















