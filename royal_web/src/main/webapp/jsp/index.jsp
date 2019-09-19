<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
                    <span>今日帖子<strong>${count}</strong></span>
                    <span>全部帖子<strong>${sumCount}</strong></span>
                </p>
            </div>
            <div class="search-box l">
                <form action="${pageContext.request.contextPath}/zone/findLikeTitle.do">
                    <input type="text" name="title" class="txt l" placeholder="请输入关键字">
                    <input type="submit" value="搜索" class="btn l"/>
                </form>
            </div>
        </div>


        <!-- 导航 -->
        <ul  class="hm-bbs-nav border-lrb clearfix">
            <c:forEach items="${zoneList}" var="zone" varStatus="status">

                <c:if test="${zone.zoneId==articleList[0].zoneId}">
                    <li class="current">
                        <a  href="${pageContext.request.contextPath}/article/findByZoneId.do?zoneId=${zone.zoneId}"><em></em>${zone.zoneName}</a>
                        <input id="zoneID" type="hidden" value="${zone.zoneId}">
                    </li>
                </c:if>

                <c:if test="${zone.zoneId!=articleList[0].zoneId}">
                    <li>
                        <a  href="${pageContext.request.contextPath}/article/findByZoneId.do?zoneId=${zone.zoneId}"><em></em>${zone.zoneName}</a>
                    </li>
                </c:if>

            </c:forEach>
        </ul>

        <!-- 主体部分 -->
        <div class="hm-bbs-main border-lrb clearfix">
            <!-- 左侧列表 -->
            <div class="list-view l">
                <ul>
<%--                    帖子显示列表--%>
                    <c:forEach items="${articleList}" var="article">
                        <c:if test="${article.isTop==1}">
                             <li class="clearfix ding">
                                <div class="hm-index-title">
                                    <i class="set-to-top">顶</i> <a href="${pageContext.request.contextPath}/article/getArticle.do?articleId=${article.articleId}">${article.title}</a>
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

                    </c:forEach>
                </ul>
            </div>


            <!-- 右侧侧边栏,在线用户 -->
            <div class="aside l">
                <div class="aside-box">
                    <h3 class="t">
                        <a href="javascript:;">在线用户 *${userStatusList.size()}*</a>
                    </h3>
                    <ul class="b clearfix">
                        <c:forEach items="${userStatusList}" var="userStatus">
                        <li>
                            <div><img src="${userStatus.picUrl}" height="55"/></div>
                            <p>${userStatus.userName}</p>
                        </li>
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
    <c:if test="${not empty user}">
    <a id="newTopicBtn" href="javascript:;" onclick="showLoginZ()" class="newTopic"><span></span>发帖</a>
    </c:if>
    <a href="#" class="goTop"><i></i><span>返回<br/>顶部</span></a>
</div>

<!-- 发帖弹出框 -->
<form id="formZ" action="${pageContext.request.contextPath}/article/save.do" method="post" >
    <input type="hidden" name="senderName" value="${user.userName}">
    <div class="pop-box ft-box">
        <div class="mask"></div>
        <div class="win">
            <div class="win_hd">
                <h4 class="l">主题帖</h4><span class="close r">&times;</span>
            </div>
            <div class="win_bd">
                <div class="win_bd_t">
                    <input type="text" id="title" name="title" placeholder="帖子标题"/>
                </div>
                <div class="win_bd_b">
                    <textarea id="content" name="content" placeholder="正文"></textarea>
                </div>
            </div>
            <div class="win_ft">
                <div class="win_ft_in">
                    <input type="submit" class="btn" value="发表"/>
                </div>
            </div>
        </div>
    </div>
</form>

</body>
<script>

function showLoginZ() {
    var zoneID =  $("#zoneID").val()
    $("#formZ").append("<input type='hidden' name='zoneId' value="+zoneID+">")
}

</script>

</html>