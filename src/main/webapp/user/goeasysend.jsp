<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<head>
<script type="text/javascript" src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script src="${path}/js/echarts.js"></script>
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
    <script type="text/javascript">

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));


            var goEasy = new GoEasy({
                appkey: "BC-2e692eeae92a4b00bcb1912852ceb68e"
            });
            goEasy.subscribe({
                channel:"myChannel",
                onMessage:function(message){

                    var data=message.content;
                    //将json串转化为json、对象
                    var d=JSON.parse(data);
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
                            data: d.month
                        },
                        yAxis: {},
                        series: [{
                            name: '男孩子',
                            type: 'bar',
                            data: d.boys
                        },{
                            name: '女孩子',
                            type: 'bar',
                            data: d.grils
                        }]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);




                }
            });










    </script>

</head>
<body>

<div id="main" style="width: 600px;height: 400px;"></div>


</body>



























