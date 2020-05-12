<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="console_element/top.jsp" />
<script type="text/javascript">
	function check(){
		var ntitle = document.getElementById("ntitle");
		var nauthor = document.getElementById("nauthor");
		var nsummary = document.getElementById("nsummary");
		var ncontent = document.getElementById("ncontent");
		
		if(ntitle.value == ""){
			alert("标题不能为空！！");
			ntitle.focus();
			return false;
		}else if(nauthor.value == ""){
			alert("作者不能为空！！");
			nauthor.focus();
			return false;
		}else if(nsummary.value == ""){
			alert("摘要不能为空！！");
			nsummary.focus();
			return false;
		}else if(ncontent.value == ""){
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
    <h1 id="opt_type"> 添加新闻： </h1>
    <form action="${pageContext.request.contextPath}/AdminServlet?action=doAddNews" method="post" enctype="multipart/form-data" onsubmit="return check()">
      <p>
        <label>主题 </label>
        <select name="ntid">
	        <!-- 迭代输出主题 -->
	        	<c:forEach var="topic" items="${topicList}">
	        		<option value="${topic.tid}">${topic.tname}</option>
	        	</c:forEach>
	        <!-- 迭代输出主题 -->
        </select>
      </p>
      <p>
        <label> 标题 </label>
        <input id="ntitle" name="ntitle" type="text" class="opt_input" />
      </p>
      <p>
        <label> 作者 </label>
        <input id="nauthor" name="nauthor" type="text" class="opt_input" />
      </p>
      <p>
        <label> 摘要 </label>
        <textarea id="nsummary" name="nsummary" cols="40" rows="3"></textarea>
      </p>
      <p>
        <label> 内容 </label>
        <textarea id="ncontent" name="ncontent" cols="70" rows="10"></textarea>
      </p>
      <p>
        <label> 上传图片 </label>
        <input name="file" type="file" class="opt_input" />
      </p>
      <input type="submit" value="提交" class="opt_sub" />
      <input type="reset" value="重置" class="opt_sub" />
    </form>
  </div>
</div>

<jsp:include page="console_element/bottom.html" />
