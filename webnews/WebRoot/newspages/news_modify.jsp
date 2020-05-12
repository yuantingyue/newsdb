<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="console_element/top.jsp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js" ></script>
<script type="text/javascript">
	function check() {
		var ntitle = document.getElementById("ntitle");
		var nauthor = document.getElementById("nauthor");
		var nsummary = document.getElementById("nsummary");
		var ncontent = document.getElementById("ncontent");

		if (ntitle.value == "") {
			alert("标题不能为空！！");
			ntitle.focus();
			return false;
		} else if (nauthor.value == "") {
			alert("作者不能为空！！");
			nauthor.focus();
			return false;
		} else if (nsummary.value == "") {
			alert("摘要不能为空！！");
			nsummary.focus();
			return false;
		} else if (ncontent.value == "") {
			alert("内容不能为空！！");
			ncontent.focus();
			return false;
		}
		return true;
	}
</script>


<div id="main">
	<jsp:include page="console_element/left.jsp" />
	<div id="opt_area">
		<h1 id="opt_type">修改新闻：</h1>
		<form action="${pageContext.request.contextPath}/AdminServlet?action=doUpNews" method="post"
			enctype="multipart/form-data" onsubmit="return check()">
			<p>
				<label> 主题 </label> 
				<select name="ntid">
					 <!-- 迭代输出主题 -->
					 <c:forEach var="topic" items="${topicList}">
						<!-- 当前项判断 -->
							
					<option value="${topic.tid}" <c:if test="${requestScope.news.ntid==topic.tid}">selected</c:if>>${topic.tname}</option>
							
						<!-- 当前项判断 -->
						<!-- 其余option -->
						<!-- 其余option -->
					</c:forEach>
					 <!-- 迭代输出主题 -->
				</select> 
				<input type="hidden" name="nid" value="${news.nid}" />
			</p>
			<p>
				<label> 标题 </label> <input id="ntitle" name="ntitle" type="text"
					class="opt_input" value="${news.ntitle}" />
			</p>
			<p>
				<label> 作者 </label> <input id="nauthor" name="nauthor" type="text"
					class="opt_input" value="${news.nauthor}" />
			</p>
			<p>
				<label> 摘要 </label>
				<textarea id="nsummary" name="nsummary" cols="40" rows="3">${news.nsummary}</textarea>
			</p>
			<p>
				<label> 内容 </label>
				<textarea id="ncontent" name="ncontent" cols="70" rows="10">${news.ncontent}</textarea>
			</p>
			<p><input type="hidden" name="npicpath" value="${news.npicpath}" />
				<label> 上传图片 </label> <input name="file" type="file"
					class="opt_input" />
			</p>
			<input type="submit" value="提交" class="opt_sub" /> <input
				type="reset" value="重置" class="opt_sub" />
		</form>
		<h1 id="opt_type">修改新闻评论：</h1>
		<table width="80%" align="left" id="upComment_tb">
			
			<!-- 判断： 无评论 -->
				<c:if test="${fn:length(requestScope.commentList)==0}">
					<td colspan="6">暂无评论！</td>
					<tr><td colspan="6"><hr /></td></tr>
					</c:if>
			<!-- 判断： 无评论 -->
			
			<!-- 判断： 有评论 -->
				<!-- 循环输出评论 -->
					<c:forEach var="comments" items="${commentList}">
						<tr>
							<td>留言人：</td>
							<td id="cauthor">${comments.cauthor}</td>
							<td>IP：</td>
							<td id="cip">${comments.cip}</td>
							<td>留言时间：</td>
							<td id="cdate">${comments.cdate}</td>
							<td><a href="javascript:doDeleteComment(${comments.cid},${comments.cnid})"><b>删除</b></a></td>
						</tr>
						<tr>
							<td id="ccontent" colspan="6">${comments.ccontent}</td>
						</tr>
						<tr>
							<td colspan="6"><hr/></td>
						</tr>
					</c:forEach>
				<!-- 循环输出评论 -->
		   <!-- 判断： 有评论 -->
		</table>
	</div>
</div>
<script type="text/javascript">
		function doDeleteComment(cid,nid){
			var url="${pageContext.request.contextPath}/AdminServlet?action=doDeleteComment";
			var params={  //获取输入框的的数据
				comId:cid,
				newsId:nid
			};
			$.getJSON(url,params,function(result){
				$("#upComment_tb").empty();  //清空table的子元素和内容
				if(result.msg=="0"){
					alert("删除失败");
					return;
				}
				if(result.datas.length==0){
					var htmlString=$("<tr><td colspan='6'>暂无评论</td></tr><tr><td colspan='6'><hr/></td></tr>")
					$("#upComment_tb").append($(htmlString));
					return;
				}
				
				$.each(result.datas,function(i,comment){ 
					var rowHtml=$("<tr><td>留言人：</td><td id='cauthor'>"+comment.cauthor+"</td>"+
							"<td>IP：</td><td id='cip'>"+comment.cip+"</td><td>留言时间：</td><td id='cdate'>"+comment.cdate+"</td>"+
							"<td><a href='javascript:doDeleteComment("+comment.cid+","+comment.cnid+")'><b>删除</b></a></td></tr>"+
							"<tr><td id='ccontent' colspan='6'>"+comment.ccontent+"</td></tr><tr><td colspan='6'><hr/></td></tr>");
					
					$("#upComment_tb").append(rowHtml);
				});
			});
		}

</script>
<jsp:include page="console_element/bottom.html" />
