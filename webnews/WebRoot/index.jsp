<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<jsp:include page="index-elements/index_top.jsp"></jsp:include>
<div id="container">
	<jsp:include page="index-elements/index_sidebar.jsp" />
	<div class="main">
		<div class="class_type">
			<img src="Images/class_type.gif" alt="新闻中心" />
		</div>
		<div class="content">

			<ul class="class_date">
				<!--遍历 显示topic list -->
				<li id='class_month'>
					<a href="${pageContext.request.contextPath}/NewsServlet?action=news&tid=ntid&ntid=-1"><b>全部</b></a>
					<c:forEach var="topic" items="${listTopic}">
						<a href="NewsServlet?action=news&tid=ntid&ntid=${topic.tid}"><b>${topic.tname}</b>
						</a>
					</c:forEach></li>
				<!--遍历 显示topic list -->
			</ul>
			<c:forEach var="news" items="${listNews}">
				<ul class="classlist">
					<!-- 遍历显示 news list  -->
					<li><a href="NewsServlet?action=detail&nid=${news.nid}">${news.ntitle}
					</a> <span>${news.ncreatedate}</span></li>
					<!-- 遍历显示 news list  -->
				</ul>
			</c:forEach>

			<ul>
				<!-- 分页显示 -->
				<p align="right">
					当前页数:[${pageNo}/${allPages}]&nbsp;&nbsp; <a
						href="${pageContext.request.contextPath}/NewsServlet?action=news&tid=ntid&pageNo=1">首页</a><a
						href="${pageContext.request.contextPath}/NewsServlet?action=news&tid=ntid&pageNo=${prev}">&nbsp;&nbsp;上一页</a><a
						href="${pageContext.request.contextPath}/NewsServlet?action=news&tid=ntid&pageNo=${next}">&nbsp;&nbsp;下一页</a>
					<a
						href="${pageContext.request.contextPath}/NewsServlet?action=news&tid=ntid&pageNo=${allPages}">&nbsp;&nbsp;末页</a>
				</p>

			</ul>
		</div>
		<jsp:include page="index-elements/index_rightbar.html" />
	</div>
</div>
<jsp:include page="index-elements/index_bottom.html" />
