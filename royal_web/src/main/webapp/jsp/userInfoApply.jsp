<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>黑马程序员论坛首页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-new.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user_info.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/hm-bbs.js"></script>
    <style type="text/css">
        .hm-header-b { border-bottom: 1px solid #d9d9d9; }
    </style>

</head>
<script>
    var count1;
    $(function () {
        $.ajax({
            type:"POST",
            // async: false,
            data:{userName:"${user.userName}"},
            url:"${pageContext.request.contextPath}/article/findCount.do",
            dataType:"json",
            success:function (data) {
                count1=data.msg;
                $("#btn4").text("当前发帖数："+data.msg)
            }
        })
    })
</script>
<body>


<!-- 头部 -->
<jsp:include page="common/header.jsp" />



<!--头部信息-->
<div class="hm-header">
    <div class="hm-inner clearfix">
        <div class="hm-header-t clearfix">
            <h1 class="logo l">
                <a href="javascript:;"><img src="../images/logo.png" height="64" width="168" alt=""/></a>
            </h1>
        </div>
        <div class="hm-header-b">
            <i class="hm-ico-home"></i>
            <a href="${pageContext.request.contextPath}/zone/findAll.do">首页</a><span>></span>申请高级用户
        </div>
    </div>
</div>



<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <div class="user-info clearfix">
            <div class="user-info-t" style="height:20px;"></div>

            <!--左侧用户名，头像-->
            <div class="user-info-l l">
                <div class="user-info-l-t">
                    <img src="${user.picUrl}"/>
                    <div class="username">${user.userName}</div>
                </div>
                <ul class="user-info-l-b">
                    <li><i class="info-icon"></i>我的资料</li>
                    <li><i class="safe-icon"></i>修改密码</li>

                    <li class="cur"><i class="info-icon"></i>申请高级用户</li>

                </ul>
            </div>


            <!--右侧用户信息-->
            <div class="user-info-r r">
                <ul class="clearfix hd">
                    <li><a href="${pageContext.request.contextPath}/jsp/userInfo.jsp">个人信息</a></li>
                    <li ><a href="${pageContext.request.contextPath}/jsp/userPwd.jsp">修改密码</a></li>
                    <c:if test="${user.role==1}">
                        <li class="cur"><a href="${pageContext.request.contextPath}/jsp/userInfoApply.jsp">申请高级用户</a></li>
                    </c:if>
                </ul>

                <ul>

                        <li class="clearfix" style="left: 100px;margin: 10px" >
                         <i class="red" style="font-size: larger">高级特权：</i>开辟新板块
                        </li>

                        <li class="clearfix" style="left: 100px;margin: 10px">
                            <i class="red" style="font-size: larger">申请条件：</i>发帖数≥5
                        </li>

                         <li class="clearfix" style="left: 100px;margin: 10px">
                            <i id="btn4" class="red" style="font-size: larger">当前发帖数：</i>
                        </li>
                   <li class="clearfix">
                       <div class="info-l"></div>
                       <div class="info-r">
                        <input type="button" id="btn2" class="btn" value="申请"/>
                       </div>
                  </li>
                    </ul>


            </div>


        </div>
    </div>
</div>


<!-- 底部 -->
<jsp:include page="common/footer.jsp"/>

<script>
        <%--当贴数大于5时，向后台发送请求--%>
        $(function () {





            $("#btn2").click(function () {
                if(count1>=5){
                    $.ajax({
                        type:"POST",
                        // async: false,
                        data:{userId:"${user.userId}"},
                        url:"${pageContext.request.contextPath}/userInfo/apply.do",
                        dataType:"json",
                        success:function (data) {
                            alert("申请成功，等待审核")
                        }
                    })
                }else{
                    alert(count1)
                }
            })
        })

        $("div1").css("background-color","red");

</script>

</body>
</html>