<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
<div class="hm-top-nav">
    <div class="hm-inner clearfix">
        <div class="hm-inner-l l"></div>
        <div class="hm-inner-r r">
            <div class="box">
                <c:if test="${not empty user}">
                    <a href="${pageContext.request.contextPath}/jsp/userInfo.jsp" id="login" class="to-login">${user.userName}  个人中心</a>
                </c:if>
                <c:if test="${empty user}">
                    <a href="javascript:;" id="login" class="to-login">游客登陆</a>
                </c:if>
                <c:if test="${not empty user}">
                    <a href="${pageContext.request.contextPath}/user/logout.do">注销</a>
                </c:if>
                <c:if test="${empty user}">
                    <a href="jsp/register.jsp">【新用户注册】</a>
                </c:if>
                <div id="dialogBg"></div>
                <div id="dialog" class="animated">
                    <img class="dialogIco" width="50" height="40" src="../images/ico.png"/>
                    <div class="dialogTop" style="height:25px;">
                        <a href="javascript:;" class="closeDialogBtn">关闭</a>
                    </div>
                    <form id="for" method="post">
                        <ul class="editInfos">
                            <li>用户名：<input type="text" id="userName" name="userName" class="ipt"/></li>
                            <li>密&nbsp;&nbsp;&nbsp;码：<input type="password" id="userPass" name="userPass" class="ipt"/></li>
                            <li><input id="btn"  type="button" value="登录" class="submitBtn"/></li>
                        </ul>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
  $(function () {
      //显示弹框
      $('.box #login').click(function () {
          var className = $(this).attr('class');
          $('#dialogBg').fadeIn(300);
          $('#dialog').removeAttr('class').addClass('animated ' + className + '').fadeIn();
          $('#userName').focus();
          $("#j_fixedBar").hide();
      });

      //关闭弹窗
      $('.closeDialogBtn').click(function () {
          $('#dialogBg').fadeOut(300, function () {
              $('#dialog').addClass('bounceOutUp').fadeOut();
              $("#j_fixedBar").show();
          });
      });

      $("#btn").click(function () {
          $.ajax({
              type:"POST",
              data:$("#for").serialize(),
              url:"${pageContext.request.contextPath}/user/login.do",
              dataType:"json",
              success:function (data) {
                  if (data.msg == 0){
                      alert("登陆成功");
                      location.reload();
                  }
                  if (data.msg == 1) {
                      alert("账号或密码错误");
                  }
              }
          })
      })

  });

</script>

</html>