<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>王者荣耀论坛注册页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-new.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
</head>
<body>


<!-- 头部 -->
<jsp:include page="common/header.jsp"/>


<div class="hm-header">
    <div class="hm-inner clearfix">
        <div class="hm-header-t clearfix">
            <h1 class="logo l">
                <a href="javascript:;"><img src="images/logo.png" height="64" width="168" alt=""/></a>
            </h1>
            <div class="search-box l">
                <form action="javascript:;">
                    <input type="text" class="txt l" placeholder="请输入关键字">
                    <input type="button" value="搜索" class="btn l"/>
                </form>
            </div>
        </div>
        <div class="hm-header-b">
            <i class="hm-ico-home"></i>
            <a href="index.do">首页</a><span>></span>注册页面
        </div>
    </div>
</div>


<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <div class="reg-box">
            <h2>用户注册<span>（红色型号代表必填）</span></h2>
            <div class="reg-info">
                <form action="${pageContext.request.contentType}/register/register.do" method="post" id="registerFrom">
                    <ul>
                        <li>
                            <div class="reg-l">
                                <span class="red">*</span> 用户名：
                            </div>
                            <div class="reg-c">
                                <input type="text" id="username" name="username" class="txt" />
                            </div>
                            <span class="tips">用户名必须是由英文、数字、下划线组成</span>
                        </li>
                        <li>
                            <div class="reg-l">
                                <span class="red">*</span> 密&nbsp;&nbsp;&nbsp;码：
                            </div>
                            <div class="reg-c">
                                <input type="password" id="userpass" name="userpass" class="txt" />
                            </div>
                            <span class="tips">密码长度必须6~10位的英文或数字</span>
                        </li>
                        <li class="no-tips">
                            <div class="reg-l">&nbsp;&nbsp;邮&nbsp;&nbsp;&nbsp;箱：</div>
                            <div class="reg-c">
                                <input type="text" id="email" name="email" class="txt" />
                            </div>
                        </li>
                        <li>
                            <div class="reg-l"></div>
                            <div class="reg-c">
                                <input type="submit" class="submit-btn" value="注册"/><br/>
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


<%--表单校验--%>
<script>
    <%--//检验用户名--%>

    function checkUserName() {
        var username = $("#username").val();
        var reg_username = /^\w{3,20}$/;
        // alert(username);
        var flag = reg_username.test(username);
        if (flag) {
            if (username) {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contentType}/register/query.do",
                    // contentType: "application/json;charset=utf-8",
                    data: $("#registerFrom").serialize(),
                    dataType: "json",
                    success: function (data) {
                        if (data!=null){
                            alert("用户名已经被注册")
                        }

                    }
                })
            }
        } else {
            alert("请输入用户名");
            return flag;
        }

    }
    function checkUserPass() {
        var userpass = $("#userpass").val();
        var reg_userpass = /^\w{3,20}$/;
        var flag1 = reg_userpass.test(userpass);
        if (flag1) {
            if (userpass) {
            }
        } else {
            alert("请输入密码");
            return flag1;
        }

    }

    $(function () {
        $("#registerFrom").submit(function () {
            return checkUserName() ;
        });
        $("#registerFrom").submit(function () {
            return checkUserPass()  ;
        });

        // $("#username").blur(function () {
        //   return checkUserName();
        // })
        $("#username").blur(checkUserName);
        $("#userpass").blur(checkUserName);
        // $("#userpass").blur(function () {
        //   return checkUserPass();
        // })
    })


</script>

</body>
</html>