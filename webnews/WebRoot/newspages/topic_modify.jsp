<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="console_element/top.jsp" />

<script type="text/javascript">
	function check(){
		var tname = document.getElementById("tname");

		if(tname.value == ""){
			alert("请输入主题名称！！");
			tname.focus();
			return false;
		}		
		return true;
	}
</script>


<div id="main">
  <jsp:include page="console_element/left.jsp" />
  <div id="opt_area">
    <h1 id="opt_type"> 修改主题： </h1>
    <form action="${pageContext.request.contextPath}/AdminServlet?action=doUpTopic" method="post" onsubmit="return check()">
      <p>
        <label> 主题名称 </label>
        <input name="topic" type="hidden" value="${requestScope.tid}">
        <input id="tname"  name="topic" type="text" class="opt_input" value="${requestScope.tname}"/>
      </p>
      <input type="submit" value="提交" class="opt_sub" />
      <input type="reset" value="重置" class="opt_sub" />
    </form>
  </div>
</div>
<jsp:include page="console_element/bottom.html" />
