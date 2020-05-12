<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="index-elements/index_top.jsp" />
<link href="CSS/read.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>

<div id="container">
	<jsp:include page="index-elements/index_sidebar.jsp" />
	<div class="main">
		<div class="class_type">
			<img src="Images/class_type.gif" alt="新闻中心" />
		</div>
		<div class="content">
			<ul class="classlist">
				<table width="80%" align="center">
					<tr width="100%">
						<td colspan="2" align="center">${newsDetail.ntitle}</td>
					</tr>
					<tr>
						<td colspan="2"><hr /></td>
					</tr>
					<tr>
						<td align="center">作者:${newsDetail.nauthor}
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							类型： <a
							href="${pageContext.request.contextPath}/NewsServlet?action=news&tid=ntid&ntid=${newsDetail.ntid}">${newsDetail.tname}<!-- tname --></a>
						</td>
						<td align="left">发布时间:${newsDetail.ncreatedate} <!-- ncreatedate -->
						</td>
					</tr>
					<tr>
						<td align="right"><strong>摘要：${newsDetail.nsummary}<!-- nsummary --></strong>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center"></td>
					</tr>
					<tr>
						<td colspan="2">${newsDetail.ncontent}<!-- ncontent -->
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<hr />
						</td>
					</tr>
				</table>
			</ul>
			<ul class="classlist">
				<table width="80%" align="center" id="commentTable">
					<!--  暂无评论！ -->
					<c:if test="${fn:length(commentsList)==0}">
					<td colspan="6">暂无评论！</td>
					<tr><td colspan="6"><hr /></td></tr>
					</c:if>
					
					<!-- 有评论 -->
					<!-- 遍历评论列表 -->
					<c:forEach var="comments" items="${commentsList}">
						<tr>
							<td>留言人：</td>
							<td>${comments.cauthor}</td>
							<td>IP：</td>
							<td>${comments.cip}</td>
							<td>留言时间：</td>
							<td>${comments.cdate}</td>
						</tr>
						<tr>
							<td colspan="6">${comments.ccontent}</td>
						</tr>
						<tr>
							<td colspan="6"><hr/></td>
						</tr>
					</c:forEach>
					<!-- 遍历评论列表 -->
					<!-- 有评论 -->
				</table>
			</ul>
			<ul class="classlist">
				<form>
					<input type="hidden" id="ctid" value="${newsDetail.nid}" />
					<table width="80%" align="center">
						<tr><td>评 论</td></tr>
						<tr>
							<td>用户名：</td>
							<td><input id="cauthor"  value="${sessionScope.loginOk.uname}" readonly="readonly" />
						    IP： <input id="cip"  value="Ip地址" readonly="readonly" /></td>
						</tr>
						<tr>
							<td colspan="2"><textarea id="ccontent"  cols="70" rows="10" value=""></textarea></td>
						</tr>
						<td><input id="doCommentAJAX" value="发  表" type="button" /></td>
					</table>
				</form>
			</ul>
		</div>
	</div>
</div>
<script>
	$("#doCommentAJAX").click(function(){
		
		$.ajax({
			url:"${pageContext.request.contextPath}/NewsServlet?action=doCommentAJAX",
			type:"post",
			
			data:{
				ctid:$("#ctid").val(),
				cauthor:$("#cauthor").val(),
				cip:$("#cip").val(),
				ccontent:$("#ccontent").val()
			},
			dataType:"json",
			success:function(result){
				if(result.msg=='0'){
					alert("评论失败！");
					return;
				}
				
				$("#commentTable").empty();
				$.each(result.datas,function(i,comment){
					var rowHtml = $("<tr><td>留言人：</td><td>"+comment.cauthor+"</td><td>IP：</td><td>"+comment.cip+"</td>"+
							"<td>留言时间：</td><td>"+comment.cdate+"</td></tr><tr><td colspan='6'>"+comment.ccontent+"</td></tr><tr><td colspan='6'><hr/></td></tr>");
					$("#commentTable").append($(rowHtml));
					
					$("#ccontent").val("");
				});
			},
			error:function(req){ //XmlHttpRequest
				if(req.status!=200)
					alert("http status:"+req.status);
			}
		});
	});
</script>
<%
	request.removeAttribute("news_view");
	request.removeAttribute("comments_view");
%>
<jsp:include page="index-elements/index_bottom.html" />
