<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>黑马程序员论坛详情页</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common-new.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/search.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/detail.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/getArticle.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/hm-bbs.js"></script>
</head>
<body>


<!-- 头部 -->
<jsp:include page="common/header.jsp"/>



<div class="hm-header"></div>
<div class="hm-body hm-body-bgc">
    <div class="hm-inner">

        <!--帖子标题，点赞数，回复数，搜索-->
        <div class="hm-bbs-info">
            <div class="hm-bbs-icon l" style="width:130px;">
                <span><img src="../images/bbs-icon.png" height="80"/></span>
            </div>
            <div class="hm-bbs-info-in l" style="margin-left:30px;">
                <div class="t clearfix">
                    <h2 class="l">${article.title}</h2>
                    <div class="hm-detail-fun l">
					     <span class="icon-like">
					         <a href="#"><i></i>${article.upvoteCount}</a>
					     </span>
                        <span class="icon-talk">
						     <i></i>${article.replyCount}
						</span>
                    </div>
                </div>
            </div>
            <div class="search-box l">
                <form action="${pageContext.request.contextPath}/article/findLikeComment.do">
                    <input type="text" class="txt l" name="comment" placeholder="请输入关键字">
                    <input type="hidden" name="articleId" value="${article.articleId}">
                    <input type="submit" value="搜索" class="btn l"/>
                </form>
            </div>
        </div>


        <!--导航，回首页，帖子标题，排序-->
        <div class="detail-page-box clearfix">
            <a href="${pageContext.request.contextPath}/zone/findAll.do">
                <i class="hm-ico-home"></i>首页
            </a>
            <span>></span>
            <a href="#">${article.title}</a>
            <c:if test="${article.comments[0].commentTimeStr>article.comments[1].commentTimeStr}">
            <a id="asc" class="new-to-new r"  href="${pageContext.request.contextPath}/article/getArticle.do?articleId=${article.articleId}" style="font-size:12px;float: right;">
                <i></i>正序
            </a>
            </c:if>
            <c:if test="${article.comments[0].commentTimeStr<article.comments[1].commentTimeStr}">
            <a id="desc" class="new-to-old r" href="${pageContext.request.contextPath}/article/getArticleDesc.do?articleId=${article.articleId}" style="font-size:12px;float: right;">
                <i></i>倒序
            </a>
            </c:if>
        </div>


        <div class="detail-box">
            <ul class="detail-floors">

                <!--原帖楼-->
                <li class="floor clearfix">
                    <div class="floorer-info l">
                        <div class="floorer-photo"><img src=${article.userInfo.picUrl}></div>
                        <c:if test="${article.userInfo.role == 3}">
                            <div style="margin-left: 30px;color: red" class="floorer-name">${article.userInfo.userName}  <span style="color: crimson ;background: yellow ; margin-left: 15px">管理员</span> </div>
                        </c:if>
                        <c:if test="${article.userInfo.role == 2}">
                            <div style="margin-left: 60px;color: red" class="floorer-name">${article.userInfo.userName}</div>
                        </c:if>
                        <c:if test="${article.userInfo.role == 1}">
                            <div style="margin-left: 60px" class="floorer-name">${article.userInfo.userName} </div>
                        </c:if>
                    </div>
                    <div class="floor-con l">
                        <div class="floor-info clearfix">
                            <div class="floor-time l">${article.sendTimeStr}</div>
                            <div class="r">${article.browseCount}次查看</div>
                        </div>
                        <div class="floor-art-ans">
                            <div class="floor-art">
                                <p>${article.content}</p>
                            </div>
                            <div class="floor-ans"></div>
                        </div>
                        <%--<img src="../images/qw.png" id="myUpVote">--%>
                            <span class="icon-liked i" id="myUpVote"><a href="#"> <i></i>点赞</a></span>
                            <span class="icon-comment"><a href="#comment"> <i></i> 评论</a></span>
                    </div>
                </li>


                <!-- 评论部分,一楼及以后 -->
                <c:forEach items="${article.comments}" var="comment" varStatus="status">


                <li class="floor clearfix">
                    <div class="floorer-info l">
                        <div class="floorer-photo"><img src=${comment.userInfo.picUrl}></div>

                        <c:if test="${comment.userInfo.role == 3}">
                            <div style="margin-left: 30px;color: red" class="floorer-name">${comment.userInfo.userName}  <span style="color: crimson ;background: yellow ; margin-left: 15px">管理员</span> </div>
                        </c:if>
                        <c:if test="${comment.userInfo.role == 2}">
                            <div style="margin-left: 60px;color: red" class="floorer-name">${comment.userInfo.userName}</div>
                        </c:if>
                        <c:if test="${comment.userInfo.role == 1}">
                            <div style="margin-left: 60px" class="floorer-name">${comment.userInfo.userName} </div>
                        </c:if>

                    </div>
                    <div class="floor-con l">
                        <div class="floor-info clearfix">
                            <div class="floor-time l">回贴时间：${comment.commentTimeStr}</div>
                            <div class="r">${status.index+1}楼</div>
                        </div>
                        <div class="floor-art-ans">
                            <div class="floor-art">
                                <p>${comment.commentContent}</p>
                            </div>
                            <div class="floor-ans">
                                <ul>

                                    <c:forEach items="${comment.replyList}" var="reply">
                                        <!-- 回复部分,楼中楼 -->
                                        <li class="clearfix">
                                            <div class="floor-ans-pho l"><img src=${reply.userInfo.picUrl}></div>
                                            <div class="floor-ans-con l">
                                                <span class="name">${reply.replyUserName}</span>：${reply.replyContent}
                                                <span class="ans-time">${reply.replyTimeStr}</span>
                                            </div>
                                        </li>
                                    </c:forEach>

                                </ul>
                            </div>
                            <span class="icon-feedback">
                                <a href="javascript:;" onclick="showDialog(${status.index+1},${comment.commentId})"> <i></i> 回复</a>
                            </span>
                        </div>
                    </div>
                </li>
                </c:forEach>

            </ul>
        </div>

        <!--发表评论-->
        <div class="detail-to-comment">
            <c:if test="${empty user}">
                <div class="con"><a href="javascript:;" onclick="showLogin()"><h5>您没有登录论坛，请登录后再进行回复</h5></a></div>
            <!-- 未登录时候显示 <div class="con">您没有登录论坛，请登录后再进行回复</div>-->
            </c:if>
            <div class="tit"><a name="comment">发表评论</a></div>
            <c:if test="${not empty user}">
            <!-- 登录后显示评论输入框-->
            <form id="formAdd" action="#" method="post">
                <input type="hidden" name="commentUserName" value="${user.userName}">
                <input type="hidden" name="articleId" value="${article.articleId}">
                <div class="con con-loged">
                    <div class="con-t">
                        <textarea id="content" name="commentContent" placeholder="请在此输入您要回复的信息"></textarea>
                    </div>
                    <div class="con-b">
                        <input id="btnAdd" type="button" class="btn" value="发表评论"/>
                        <span class="num">不能超过5000字</span>
                    </div>
                </div>
            </form>
            </c:if>
        </div>
    </div>
</div>



<!-- 底部 -->
<jsp:include page="common/footer.jsp"/>



<!-- 回复弹出框 -->
<form id="formReply" action="" method="post">
    <div class="pop-box ft-box">
        <div class="mask"></div>
        <div class="win">
            <div class="win_hd">
                <h4 class="l">回复<span id="floorSpan"></span>楼</h4>
                <span class="close r">&times;</span>
            </div>
            <div class="win_bd">
                <div class="win_bd_b">
                    <textarea id="replyContent" name="replyContent" placeholder="回复内容限于40字以内"></textarea>
                </div>
            </div>
            <div class="win_ft">
                <div class="win_ft_in">
                    <input id="btnReply" type="button" class="btn" value="回复"/>
                    <input type="hidden" id="commentId" name="commentId"/>
                    <input type="hidden" value="${user.userName}" name="replyUserName"/>
                </div>
            </div>
        </div>
    </div>
</form>

<%--右侧--%>
<div class="fixedBar" id="j_fixedBar">
    <a href="#comment" class="newTopic"><span></span>回复</a>
    <a href="#" class="goTop"><i></i><span>返回<br/>顶部</span></a>
</div>


</body>

<script type="text/javascript">
//弹出回复框
function showDialog(num, commentId) {
	var loginUser = "${user}";
	if(!loginUser){
		alert("请登录");
        var className = $(this).attr('class');
        $('#dialogBg').fadeIn(300);
        $('#dialog').removeAttr('class').addClass('animated ' + className + '').fadeIn();
        $('#userName').focus();
        $("#j_fixedBar").hide();
        return;
	}
	$("#commentId").val(commentId);
    $('.pop-box').css('display', 'block');
    $("#floorSpan").html(num);
}


function showLogin() {
    var loginUser = "${user}";
    if(!loginUser){
        var className = $(this).attr('class');
        $('#dialogBg').fadeIn(300);
        $('#dialog').removeAttr('class').addClass('animated ' + className + '').fadeIn();
        $('#userName').focus();
        $("#j_fixedBar").hide();
        return;
    }
}
</script>

<script>

    //1：判断是否有用户登录
    $(function () {
        //  a.没有用户登录的时候，点击爱心按钮就提示先登录
        if (${empty sessionScope.user}) {
            $("#myUpVote").click(function () {
                alert("请先登录~")
            })
        } else {
            // alert("有用户登录")
            <%--alert("${upvote.upvoteArticleId}")--%>
            //b.有用户登录的时候，判断upvote对象是否有值
            //a:没有值的时候，爱心显示空心，点击爱心发送ajax请求到后台保存upvote对象到upvote表中
            //最后刷新页面
            if (${empty upvote.upvoteUserName}) {
                $("#myUpVote").click(function () {
                    // alert("无值的时候")
                    $.ajax({
                        url: "${pageContext.request.contextPath}/upVote/saveByBean.do",
                        contentType:"application/json;charset=UTF-8",
                        data: '{"upvoteUserName": "${user.userName}", "upvoteArticleId": ${comment.articleId}, "isUpvote": 1}',
                        dataType: "json",
                        type: "post",
                        success: function (d) {
                            // alert("ajax成功")
                            $("#myUpVote").attr("class","icon-liked")
                            location.reload()
                        }
                    })
                })
            }
            //当有值的时候
            else if (${not empty upvote.upvoteUserName}) {
                // alert("有值的时候")
                <%--alert("当前值为：${upvote.isUpvote}")--%>
                //a:判断isupvote是否为1，为1的时候说明已点赞，爱心显示实心，再次点赞爱心便变回空心，发送ajax到后台
                //         根据登录用户和帖子编号更新upvote表中isupvote的值，最后刷新页面
                if (${upvote.isUpvote == 1}) {
                    // alert("isupvote为1的时候")
                    $("#myUpVote").attr("class","icon-liked")
                    $("#myUpVote").click(function() {
                        // alert("按钮被点击了")
                        $.ajax({
                            url: "${pageContext.request.contextPath}/upVote/changeIsUpvote.do",
                            contentType:"application/json;charset=UTF-8",
                            <%--data: '{"upvoteUserName": "${userbaojia.userName}", "upvoteArticleId": ${comment.articleId}, "isUpvote": 0}',--%>
                            data:'{"upvoteUserName":"${upvote.upvoteUserName}","upvoteArticleId":${upvote.upvoteArticleId},"isUpvote": 0}',
                            dataType:"json",
                            type:"post",
                            success:function (d) {
                                alert(d.isUpvote)
                                $("#myUpVote").attr("class","icon-like")
                                location.reload()
                            }
                        })
                    })
                }
                // b:为0的时候说明未点赞，爱心显示空心，点击点赞按钮，爱心变成实心，发送ajax到后台
                //    更新upvote表中的isupvote的值，最后刷新页面
                else if(${upvote.isUpvote == 0}) {
                    // alert("isupvote为0的时候")
                    $("#myUpVote").click(function() {
                        // alert("按钮被点击了")
                        $.ajax({
                            url: "${pageContext.request.contextPath}/upVote/changeIsUpvote.do",
                            contentType:"application/json;charset=UTF-8",
                            <%--data: '{"upvoteUserName": "${userbaojia.userName}", "upvoteArticleId": ${comment.articleId}, "isUpvote": 0}',--%>
                            data:'{"upvoteUserName":"${upvote.upvoteUserName}","upvoteArticleId":${upvote.upvoteArticleId},"isUpvote": 1}',
                            dataType:"json",
                            type:"post",
                            success:function (d) {
                                // alert(d.isUpvote)
                                $("#myUpVote").attr("class","icon-liked")
                                location.reload()
                            }
                        })
                    })
                }
            }
        }
    })

    $("#btnAdd").click(function () {
        $.ajax({
            type: "POST",
            url:"${pageContext.request.contextPath}/comment/addComment.do",
            data:$("#formAdd").serialize(),
            // contentType:"application/html;charset=utf-8",
            dataType:"json",
            success:function (data) {
                if (data.msg == 1){
                    alert("发表成功经验+3 告辞 ✌")
                    location.reload();
                }else if (data.msg == 0) {
                    alert("您已被禁言，请联系管理员")
                }
            }

        })
    })

    $("#btnReply").click(function () {
        $.ajax({
            type: "POST",
            url:"${pageContext.request.contextPath}/reply/addReply.do",
            data:$("#formReply").serialize(),
            // contentType:"application/html;charset=utf-8",
            dataType:"json",
            success:function (data) {
                if (data.msg == 1){
                    alert("回复成功经验+3 告辞 ✌")
                    location.reload();
                }else if (data.msg == 0) {
                    alert("内容不能为空")
                } else if (data.msg == 3) {
                    alert("您已被禁言，请联系管理员")
                }
            }

        })
    })

</script>
</html>