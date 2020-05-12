<%@ page language="java" import="java.util.*,java.sql.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<jsp:include page="console_element/top.jsp" />

<script language="javascript">
	function clickdel() {
		return confirm("删除后，主题下的文章也会删除，确认删除吗？");
	}
</script>


<div id="main">
	<jsp:include page="console_element/left.jsp" />
	<div id="opt_area">
		<ul class="classlist">
			<!-- 遍历  topic list -->
			<c:forEach var="topic" items="${requestScope.topicList}">
			<li>${topic.tname} &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;
				<a href="${pageContext.request.contextPath}/AdminServlet?action=toModify&tid=${topic.tid}&tname=${topic.tname}">修改</a>&#160;&#160;&#160;&#160;
				<a href="${pageContext.request.contextPath}/AdminServlet?action=doDeleteTopic&tid=${topic.tid}" onclick='return clickdel()'>删除</a>
			</li>
			</c:forEach>
			
			<!-- 遍历  topic list -->
			
			<!-- 显示分页 -->
			<p align="right"> 当前页数:[1/1]&nbsp;&nbsp; 
				<a href="${pageContext.request.contextPath}/AdminServlet?action=toUpTopic&pageNo=1">首页</a>
				<a href="${pageContext.request.contextPath}/AdminServlet?action=toUpTopic&pageNo=1">&nbsp;&nbsp;上一页</a>
				<a href="${pageContext.request.contextPath}/AdminServlet?action=toUpTopic&pageNo=1">&nbsp;&nbsp;下一页</a>
				<a href="${pageContext.request.contextPath}/AdminServlet?action=toUpTopic&pageNo=1">&nbsp;&nbsp;末页</a>
			</p>
		</ul>
	</div>
</div>


<jsp:include page="console_element/bottom.html" />
