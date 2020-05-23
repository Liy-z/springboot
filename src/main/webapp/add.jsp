<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'insert.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	* {margin:0; padding:0;}
	div {
	margin:0 auto;
	width:420px;
	}
	table {
	width:420px;
	border-collapse: collapse;
	}
	.title {
	font-size:30px;
	height:50px;
	line-height:50px;
	text-align:center;
	}
	.center {
	text-align:center;
	}
	</style>
	<script type="text/javascript" src="<%=basePath%>/scripts/jquery-1.12.4.js"></script>
	<script type="text/javascript">
	var base = "<%=basePath%>";
	function runback(){
		location.href = "select.html";
	}
	//ajax请求，判断产品代码是否重复
	function judgeCode(){
		var info = $('#id').val();
		if(info.length > 0){
			$.ajax({
				url:"judge.jsp",
				data:{"code":$('#id').val()},
				dataType:"json",
				success:function(msg){
					//局部更新
					//$('#ajax_info').html(msg);
					var info = "<span style='color:"+msg.yanse+";'>"+msg.wenben+"</span>";
					$('#ajax_info').html(info);
				}
			});
			/* $.getJSON(base+"/judge.jsp",
				{"code":$('#id').val()},
				function(msg){
					//局部更新
					var info = "<span style='color:"+msg.yanse+";'>"+msg.wenben+"</span>";
					$('#ajax_info').html(info);
				}); */
			/* $('#ajax_info').load(base+"/judge.jsp","code="+$('#id').val(),function(){
				alert("aaaaa");
			}); */
		}
	}
	</script>
  </head>
  
  <body>
    <div>
	   <%--  <form action="<%=basePath%>/insert.jsp" method="post"> --%>
	    <form action="insertStaff.win" method="post">
	        <table border="0" cellpadding="0" cellspacing="0">
	            <tr class="title">
	                <td colspan="3">新增员工信息</td>
	            </tr>
	            <tr>
	                <td>姓名：</td>
	                <td><input type="text" name="sname" /></td>
	                <td><span id=""></span></td>
	            </tr>
	            <tr>
		           <td> 部门：</td>
			          <td> 
			          	<select name="department" id="department">
	                        <option value="">请选择</option>
	                         <c:forEach var="de" items="${list}">
		                        <option value="${de.id}">${de.department}</option>
		                     </c:forEach>
	                    </select>
	                </td>
	                <td></td>
	            </tr>
	            <tr>
	                <td>电话：</td>
	                <td><input type="text" name="phot"/></td>
	                <td></td>
	            </tr>
	            <tr>
	                <td>地址：</td>
	                <td><input type="text" name="address"/></td>
	            </tr>
	            <tr class="center">
	                <td colspan="3">
	                	<input type="submit" value="保存"/>&nbsp;
	                	<input type="button" value="返回" onclick="runback()"/></td>
	            </tr>
	        </table>
	    </form>
	</div>
  </body>
</html>
