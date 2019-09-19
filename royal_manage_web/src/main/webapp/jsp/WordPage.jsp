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
                        <li class="active">敏感词汇管理</li>
                    </ol>
                </div>
                <div style="clear:both"></div>

                <button class="btn btn-primary" data-toggle="modal" data-target="#myModal">新增加敏感词</button>

                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>敏感词</th>
                        <th>是否启用</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${pageInfo.list}" var="word" varStatus="vs">
                            <tr>
                                <td width="15%">${vs.index+1}</td>
                                <td width="30%" class="line-limit-length">${word.word}</td>
                                <td width="5%" class="line-limit-length">${word.statusStr}</td>
                                <td width="15%">
                                    <c:if test="${word.status==0}">
                                        <a href="${pageContext.request.contextPath}/word/changeStatus.do?wordId=${word.wordId}&status=1&pageNum=${pageInfo.pageNum}" role="button" class="btn btn-primary" >启用</a>
                                    </c:if>
                                    <c:if test="${word.status==1}">
                                        <a href="${pageContext.request.contextPath}/word/changeStatus.do?wordId=${word.wordId}&status=0&pageNum=${pageInfo.pageNum}" role="button" class="btn btn-danger" >停用</a>
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

            <!-- 模态框（Modal） -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="myModalLabel">
                                新增加敏感词
                            </h4>
                        </div>
                        <div class="modal-body">
                            <textarea class="form-control" rows="1" name="title" id="detail_title" ></textarea>
                            <span id="span1"></span>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                            </button>
                            <button id="wordBtn" type="button" class="btn btn-primary">
                                保存
                            </button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal -->
            </div>
        </div><!-- /.dept_info -->
        <!-- 尾部-->
        <%@ include file="commom/foot.jsp"%>
    </div><!-- /.hrms_dept_body -->

</div><!-- /.hrms_dept_container -->

<%--<%@ include file="ArticleAdd.jsp"%>--%>
<%@ include file="ArticleUpdate.jsp"%>
<script>
    function searchArticle(data) {
        location.href="${pageContext.request.contextPath}/word/findByPage.do?pageNum="+data+"&pageSize=5";
    }

    $("#wordBtn").click(function () {
        var word = $("#detail_title").val();
        $.ajax({
            url:"${pageContext.request.contextPath}/word/addWord.do?word="+word,
            type:"GET",
            data:{},
            contentType:"application/json;charset=UTF-8",
            success:function (d) {
                if ($("#detail_title").val()=="") {
                    $("#span1").css("color","red").html("请输入敏感词");
                }else {
                    location.reload();
                }
            }
        })
    })
</script>
</body>
</html>
