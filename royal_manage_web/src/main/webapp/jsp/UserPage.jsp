<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>帖信息管理页面</title>

</head>
<style type="text/css">
    html,body{
        overflow:auto;
        height:100%;
    }

    .line-limit-length {
        max-width: 220px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }


</style>
<script>

</script>
<body>
<div class="hrms_dept_container">
    <!-- 导航栏-->
    <%@ include file="commom/head.jsp"%>


    <!-- 中间部分（左侧栏+表格内容） -->
    <div class="hrms_dept_body">
        <!-- 左侧栏 -->
        <%@ include file="commom/leftsidebar.jsp"%>

        <!-- 表格内容 -->
        <div class="dept_info col-sm-10">
            <div class="panel panel-success">
                <!-- 路径导航 -->
                <div >
                    <ol class="breadcrumb">
                        <li><a href="#">用户管理</a></li>
                        <li class="active">用户信息</li>
                    </ol>
                </div>
                <hr>
                <!-- Table -->
                <div>
                    <div style="float: left">
                        <form method="post" action="${pageContext.request.contextPath}/user/userSearchForm.do" id="userSearchForm">
                            <table>
                                <tr>
                                    <th>
                                        <label for="title" class="control-label">用户名:</label>
                                    </th>
                                    <th>
                                        <input type="text" id="title" class="form-control"
                                               name="userName" value="${condition.userName}">
                                        <%--<input type="hidden" id="pageNum" name="pn" value="">--%>
                                    </th>
                                    <th>
                                        <label for="article_sendername" class="control-label">用户组:</label>
                                    </th>

                                    <th>
                                        <select class="form-control" id="changePageSize" name="role" >
                                            <%--<option value="" style="display: none"></option>--%>
                                            <option value="0">---请选择用户组---</option>
                                            <option  value="1" <c:if test="${condition.role ==1}">selected</c:if> >普通用户</option>
                                            <option value="2" <c:if test="${condition.role ==2}">selected</c:if>>高级用户</option>
                                            <option value="3" <c:if test="${condition.role ==3}">selected</c:if>>超级管理员</option>
                                        </select>
                                        <%--<input type="text" id="article_sendername" class="form-control"--%>
                                               <%--name="role" value="">--%>
                                    </th>
                                    <th colspan="2">
                                        <input type="submit" value="查询" class="form-control btn-primary">
                                            <%--<button type="submit" class="btn btn-default">查询</button>--%>
                                    </th>
                                </tr>
                            </table>

                        </form>
                    </div>
                </div>
                <div style="clear:both"></div>
                <hr>
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>用户名</th>
                        <th>用户组</th>
                        <th>邮箱</th>
                        <th>是否禁言</th>
                        <th>最近登录时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="user">

                            <tr>
                                <td width="15%" class="line-limit-length">${user.userName}
                                </td>
                                <td width="15%" class="line-limit-length">
                                    ${user.roleStr}
                                </td>
                                <td width="15%" class="line-limit-length">${user.email}</td>
                                <td width="15%" class="line-limit-length">
                                    ${user.talkStatusStr}
                                </td>
                                <td width="15%">
                                    ${user.lastLoginTime}
                                </td>
                                <td width="15%">
                                    <%--<c:if test="${user.role<3}">--%>
                                        <%--<a href="/user/changeRole.do?id=${user.userId}&roles=${user.role}&page=${pageInfo.pageNum}&userName=${condition.userName}&role=${condition.role}" role="button" class="btn btn-primary" >升级</a>--%>
                                    <%--</c:if>--%>

                                    <c:if test="${user.isupdating==1 && user.role<3}">
                                        <a href="/user/changeRole.do?id=${user.userId}&roles=${user.role}&page=${pageInfo.pageNum}&userName=${condition.userName}&role=${condition.role}" role="button" class="btn btn-primary" >升级</a>
                                    </c:if>


                                    <c:if test="${user.role==3}">
                                        <a href="/user/downgradeRole.do?id=${user.userId}&roles=${user.role}&page=${pageInfo.pageNum}&userName=${condition.userName}&role=${condition.role}" role="button" class="btn btn-primary" >降级</a>
                                    </c:if>

                                    <c:if test="${user.role==2}">
                                        <a href="/user/downgradeRole.do?id=${user.userId}&roles=${user.role}&page=${pageInfo.pageNum}&userName=${condition.userName}&role=${condition.role}" role="button" class="btn btn-primary" >降级</a>
                                    </c:if>

                                        <%--驳回--%>
                                    <c:if test="${user.isupdating==1}">
                                        <a href="/user/isupdating.do?id=${user.userId}&isupdating=0&page=${pageInfo.pageNum}&userName=${condition.userName}&role=${condition.role}" role="button" class="btn btn-primary" >驳回</a>
                                    </c:if>


                                    <c:if test="${user.talkStatus==0}">
                                    <a href="/user/changeTalkStatus.do?id=${user.userId}&talkStatus=1&page=${pageInfo.pageNum}&userName=${condition.userName}&role=${condition.role}" role="button" class="btn btn-danger">禁言</a>
                                    </c:if>
                                    <c:if test="${user.talkStatus==1}">
                                        <a href="/user/changeTalkStatus.do?id=${user.userId}&talkStatus=0&page=${pageInfo.pageNum}&userName=${condition.userName}&role=${condition.role}" role="button" class="btn btn-info">恢复</a>
                                    </c:if>


                                    <c:if test="${article.istop==0}">
                                        <a href="/article/changeStatus.do?id=${article.articleid}&pn=${articleMsgs.pageNum}&title=${articleSearch.title}&sendername=${articleSearch.sendername}" role="button" class="btn btn-danger" >置顶</a>
                                    </c:if>
                                    <c:if test="${article.istop==1}">
                                        <a href="/article/changeStatus.do?id=${article.articleid}&pn=${articleMsgs.pageNum}&title=${articleSearch.title}&sendername=${articleSearch.sendername}" role="button" class="btn btn-info" >取消</a>
                                    </c:if>
                                </td>
                            </tr>
                    </c:forEach>
                        <%--</c:forEach>--%>
                    </tbody>
                </table>


            </div><!-- /.panel panel-success -->
            <!--显示分页信息-->
            <div class="row">
                <!--文字信息-->
                <div class="col-md-6">
                    当前第 ${pageInfo.pageNum} 页.总共 ${pageInfo.pages} 页.一共 ${pageInfo.total} 条记录
                </div>

                <!--点击分页-->
                <div class="col-md-6">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <!--首页-->
                            <li><a href="${pageContext.request.contextPath}/user/userSearchForm.do?page=1&size=${pageInfo.pageSize}&userName=${condition.userName}&role=${condition.role}" onclick="searchArticle(1)">首页</a></li>
                            <!--上一页-->
                            <%--<li><a href="${pageContext.request.contextPath}/user/findByPage.do?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}" >上一页</a></li>--%>
                            <li>
                                <c:if test="${pageInfo.hasPreviousPage}">
                                        <a href="${pageContext.request.contextPath}/user/userSearchForm.do?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}&userName=${condition.userName}&role=${condition.role}" onclick="searchArticle('${pageInfo.pageNum-1}')" aria-label="Previous">
                                            <span aria-hidden="true">«</span>
                                        </a>
                                </c:if>
                            </li>

                            <%--<c:forEach items="${pageInfo.navigatepageNums}" var="page_num">--%>
                                <%--<c:if test="${page_num == pageInfo.pageNum}">--%>
                                    <%--<li class="active"><a href="#">${page_num}</a></li>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${page_num != pageInfo.pageNum}">--%>
                                    <%--<li><a href="#" onclick="searchArticle('${page_num}')">${page_num}</a></li>--%>
                                <%--</c:if>--%>
                            <%--</c:forEach>--%>
                            <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                                <li><a href="${pageContext.request.contextPath}/user/userSearchForm.do?page=${pageNum}&size=${pageInfo.pageSize}&userName=${condition.userName}&role=${condition.role}">${pageNum}</a></li>


                            </c:forEach>

                            <!--下一页-->
                            <li>
                                <c:if test="${pageInfo.hasNextPage}">
                                    <a href="${pageContext.request.contextPath}/user/userSearchForm.do?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}&userName=${condition.userName}&role=${condition.role}" onclick="searchArticle('${pageInfo.pageNum+1}')"
                                       aria-label="Next">
                                        <span aria-hidden="true">»</span>
                                    </a>
                                </c:if>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/user/userSearchForm.do?page=${pageInfo.pages}&size=${pageInfo.pageSize}&userName=${condition.userName}&role=${condition.role}" onclick="searchArticle('${pageInfo.pages}')">尾页</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div><!-- /.dept_info -->
        <!-- 尾部-->
        <%@ include file="commom/foot.jsp"%>
    </div><!-- /.hrms_dept_body -->

</div><!-- /.hrms_dept_container -->

<%--<%@ include file="ArticleAdd.jsp"%>--%>
<%@ include file="../jsp/ArticleUpdate.jsp"%>
</body>
</html>
