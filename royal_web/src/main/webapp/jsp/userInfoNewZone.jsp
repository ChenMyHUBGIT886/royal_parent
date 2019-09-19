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
            <a href="${pageContext.request.contextPath}/zone/findAll.do">首页</a><span>></span>开辟新板块
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
                    <li ><i class="info-icon"></i>我的资料</li>
                    <li><i class="safe-icon"></i>修改密码</li>
                    <li class="cur"><i class="info-icon"></i>开辟新板块</li>
                </ul>
            </div>


            <!--右侧用户信息-->
            <div class="user-info-r r">
                <ul class="clearfix hd">
                    <li><a href="${pageContext.request.contextPath}/jsp/userInfo.jsp">个人信息</a></li>
                    <li ><a href="${pageContext.request.contextPath}/jsp/userPwd.jsp">修改密码</a></li>
                    <c:if test="${user.role==3}">
                        <li  class="cur"><a href="${pageContext.request.contextPath}/jsp/userInfoNewZone.jsp">开辟新板块</a></li>
                    </c:if>
                </ul>


                <form action="#" method="post" >
                    <input type="hidden" name="userName" id="username" value="${user.userName}">
                    <ul class="bd">
                        <li class="clearfix">
                            <div class="info-l">板块名称：</div>
                            <div class="info-r"><input type="text" id="zoneName" name="zoneName" class="txt" /></div>
                        </li>
                        <li class="clearfix">
                            <div class="info-l">申请理由：</div>
                            <div class="info-r">
                                <textarea   id="reason" name="reason" class="txt"  placeholder="请在此输入申请理由"></textarea>
                            </div>
                        </li>
                        <li class="clearfix">
                            <div class="info-l"></div>
                            <div class="info-r">
                                <input type="button" id="btn1" class="btn" value="申请"/>
                                <%--此处为button,不能是submit--%>
                            </div>
                        </li>
                    </ul>
                </form>


            </div>


        </div>
    </div>
</div>


<!-- 底部 -->
<jsp:include page="common/footer.jsp"/>

<script>
    $(function () {
        $("#btn1").click(function () {
            $.ajax({
                type:"POST",
                data:JSON.stringify({'userName':$("#username").val(),'zoneName':$("#zoneName").val(),'reason':$("#reason").val()}),
                contentType:"application/json;charset=utf-8",
                url:"${pageContext.request.contextPath}/zoneApply/save.do",
                dataType:"json",
                success:function (data) {
                     alert("申请成功，等待审核")
                }
            })
        })
    })
</script>

</body>
</html>