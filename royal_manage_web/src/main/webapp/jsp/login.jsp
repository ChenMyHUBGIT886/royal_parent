<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="/bootstrap/bootstrap-theme.min.css">
    <script src="/bootstrap/jquery-1.11.0.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="/bootstrap/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4" style="margin: 380px 380px">
            <div class="login-panel panel panel-default"style="margin-top:-130px" >
                <div class="panel-heading">
                    <h3 class="panel-title" style="text-align: center;">王者荣耀论坛管理后台</h3>
                </div>
                <div class="panel-body">
                    <div id="errorMsg" class="alert alert-danger" ></div>
                    <form role="form" method="post" id="login_form">
                        <fieldset>
                            <div class="form-group">
                                <input id="name" class="form-control" placeholder="用户名" name="userName" autofocus>
                            </div>
                            <div class="form-group">
                                <input id="pass" class="form-control" placeholder="密码" name="userPass" type="password">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <!--<a href="javascript:void(0)" class="btn btn-lg btn-success btn-block" id='login_btn'>登录</a>-->
                            <input type="button" id="btn_sub" class="btn btn-lg btn-success btn-block" value="登录">
                        </fieldset>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>

</body>

<script>
    $("#btn_sub").click(function () {
        $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/user/login.do",
            // contentType: "application/json;charset=utf-8",
            data:$("#login_form").serialize(),
            dataType:"json",
            success:function (data) {
               if (data.msg == 0){
                   location.href = "${pageContext.request.contextPath}/jsp/main.jsp"
               }else if(data.msg == 1) {
                   alert("没有权限");
               }else if (data.msg == 2) {
                   alert("账号或密码错误");
               }
            }

        })

    })
</script>
</html>
