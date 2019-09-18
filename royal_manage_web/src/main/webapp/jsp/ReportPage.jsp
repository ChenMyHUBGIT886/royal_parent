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
                        <li><a href="#">用户帖管理</a></li>
                        <li class="active">审批举报</li>
                    </ol>
                </div>
                <div style="clear:both"></div>
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>帖子ID</th>
                        <th>举报内容</th>
                        <th>举报人</th>
                        <th>举报时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${pageInfo.list}" var="reports">
                            <tr>

                                <td width="10%" class="line-limit-length">${reports.articleId}</td>
                                <td width="35%" class="line-limit-length">${reports.reportContent}</td>
                                <td width="10%" class="line-limit-length">${reports.reportUserName}</td>
                                <td width="20%" class="line-limit-length">${reports.reportTime}</td>
                                <td width="20%">
                                    <a id="articleDet2ail" onclick="articleDetail(${reports.articleId})" class="btn btn-primary" data-toggle="modal" data-target="#myModal">相关帖子</a>
                                    <c:if test="${reports.reportStatus==0}">
                                        <a href="${pageContext.request.contextPath}/report/changeIsReport.do?articleId=${article.articleId}&pn=${articleMsgs.pageNum}&title=${articleSearch.title}&sendername=${articleSearch.sendername}" role="button" class="btn btn-danger" >屏蔽</a>
                                        <a href="${pageContext.request.contextPath}/report/changeStatus.do?reportId=${reports.reportId}" role="button" class="btn btn-info" >驳回</a>
                                    </c:if>
                                    <c:if test="${reports.reportStatus==1}">
                                        <a href="${pageContext.request.contextPath}/article/changeStatus.do?id=${article.articleId}&isTop=0" role="button" class="btn btn-info" >取消</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
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
                            <li><a href="#" onclick="searchArticle(1)">首页</a></li>
                            <!--上一页-->
                            <li>
                                <c:if test="${pageInfo.hasPreviousPage}">
                                        <a href="#" onclick="searchArticle('${pageInfo.pageNum-1}')" aria-label="Previous">
                                            <span aria-hidden="true">«</span>
                                        </a>
                                </c:if>
                            </li>

                            <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                                <c:if test="${page_num == pageInfo.pageNum}">
                                    <li class="active"><a href="#">${page_num}</a></li>
                                </c:if>
                                <c:if test="${page_num != pageInfo.pageNum}">
                                    <li><a href="#" onclick="searchArticle('${page_num}')">${page_num}</a></li>
                                </c:if>
                            </c:forEach>

                            <!--下一页-->
                            <li>
                                <c:if test="${pageInfo.hasNextPage}">
                                    <a href="#" onclick="searchArticle('${pageInfo.pageNum+1}')"
                                       aria-label="Next">
                                        <span aria-hidden="true">»</span>
                                    </a>
                                </c:if>
                            </li>
                            <li><a href="#" onclick="searchArticle('${pageInfo.pages}')">尾页</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div><!-- /.dept_info -->
        <!-- 尾部-->
        <%@ include file="commom/foot.jsp"%>

        <!-- 模态框（Modal） -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">帖子信息详情</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal article_detail_form">
                            <div class="form-group">
                                <label for="detail_title" class="col-sm-2 control-label">标题</label>
                                <div class="col-sm-8">
                                    <textarea class="form-control" rows="3" name="title" id="detail_title" disabled>${article.title}</textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="detail_content" class="col-sm-2 control-label">内容</label>
                                <div class="col-sm-8">
                                    <textarea class="form-control" rows="3" name="content" id="detail_content" disabled>${article.content}</textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
    </div><!-- /.hrms_dept_body -->

</div><!-- /.hrms_dept_container -->

<%--<%@ include file="ArticleAdd.jsp"%>--%>
<%@ include file="ArticleUpdate.jsp"%>
<script>
    function searchArticle(data) {
        location.href="${pageContext.request.contextPath}/report/findByPage.do?pageNum="+data+"&pageSize=5";
    }

    function articleDetail(articleId) {
        $.ajax({
            url:"${pageContext.request.contextPath}/article/findByIdManager.do",
            type:"GET",
            data:{"articleId":articleId},
            contentType:"application/json;charset=UTF-8",
            success:function (d) {
                alert(125)
                /*$("#detail_title").html(d.title);
                $("#detail_content").html(d.content);*/
            }
        })
    }
</script>
</body>
</html>
