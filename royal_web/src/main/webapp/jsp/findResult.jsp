<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>王者荣耀论坛</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common-new.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index-new.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/hm-bbs.js"></script>
</head>

<body>

<!-- 头部 -->
<jsp:include page="common/header.jsp"/>


<!-- 主体部分 -->
<div class="hm-header"></div>
<div class="hm-body hm-body-bgc">
    <div class="hm-inner">
        <div class="hm-banner"></div>


        <!--头部，帖子统计，搜索-->
        <div class="hm-bbs-info">
            <div class="hm-bbs-icon l" style="width:130px;">
                <span><img src="../images/bbs-icon.png" height="80"/></span>
            </div>
            <div class="hm-bbs-info-in l" style="margin-left:30px;">
                <div class="t clearfix"><h2 class="l">王者荣耀</h2></div>
                <p>

                </p>
            </div>

        </div>


        <!-- 导航 -->
        <ul class="hm-bbs-nav border-lrb clearfix">
            <a href="${pageContext.request.contextPath}/zone/findAll.do">
                <i class="hm-ico-home"></i>首页
                <span style="float: right ; margin-right:410px; font-size: 30px ">搜索结果</span>
            </a>

        </ul>

        <!-- 主体部分 -->
        <div class="hm-bbs-main border-lrb clearfix">
            <!-- 左侧列表 -->
            <div class="">
                <div style="float: right; margin-right: 350px">
                <ul>
                    <%--                    帖子显示列表--%>
                    <c:forEach items="${titleList}" var="article">
                        <c:if test="${article.isTop==1}">
                            <li class="clearfix ding">
                                <div class="hm-index-title">
                                    <a href="${pageContext.request.contextPath}/article/getArticle.do?articleId=${article.articleId}">${article.title}</a>
                                </div>
                                <div class="hm-index-con">${article.content}</div>
                                <div class="hm-index-info l">
                                    <span class="article-username">${article.senderName}</span>
                                    <span class="post-time">${article.sendTimeStr}</span>
                                </div>
                                <div class="hm-index-fun r">
                                    <span class="icon-like"><i></i>1</span>
                                    <span class="icon-talk"><i></i>0</span>
                                </div>
                            </li>
                        </c:if>
                        <c:if test="${article.isTop!=1}">
                            <li>
                                <div class="hm-index-title">
                                    <a href="getArticle.do">${article.title}</a>
                                </div>
                                <div class="hm-index-con">${article.content}</div>
                                <div class="hm-index-info l">
                                    <span class="article-username">${article.senderName}</span>
                                    <span class="post-time">${article.sendTimeStr}</span>
                                </div>
                                <div class="hm-index-fun r">
                                    <span class="icon-like"><i></i>1</span>
                                    <span class="icon-talk"><i></i>0</span>
                                </div>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
                </div>
            </div>


        </div>
    </div>
</div>


<!-- 底部 -->
<jsp:include page="common/footer.jsp"/>


<!-- 右边发帖，回顶部 -->
<div class="fixedBar" id="j_fixedBar">
    <a id="newTopicBtn" href="javascript:;" class="newTopic"><span></span>发帖</a>
    <a href="#" class="goTop"><i></i><span>返回<br/>顶部</span></a>
</div>

</body>


<script>

</script>


</html>