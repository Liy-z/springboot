<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cc" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	* {margin:0; padding:0;}
	div {
	margin:0 auto;
	width:600px;
	}
	#tip {
	text-align:center;
	height:50px;
	line-height:50px;
	}
	table {
	margin:0 auto;
	width:600px;
	border-collapse: collapse;
	}
	table td {
	text-align:center;
	}
	.title {
	font-weight:bolder;
	background-color:#426ab3;
	}
	table:first-of-type {
	height:80px;
	line-height:80px;
	}
	table:first-of-type td {
	font-size:18px;
	font-weight:bolder;
	}
	</style>
	<script type="text/javascript" src="<%=basePath%>/scripts/jquery-1.12.4.js"></script>
	<script type="text/javascript">
	
		function Solr(){
			location.href = "selectSolr.win";
		}
		function Redis(){
			location.href = "selectStaff.win";
		}
			
			function querenDelete(sid){
				var isremove = confirm("确认删除嘛？");
				if(isremove == true){
					//触发ajax请求，实现局部刷新
					$.ajax({
						url		:	"removeStaff.win",
						type	:	"post",
						data	:	{"sid":sid},
						dataType:	"json",
						error	:	function () {
							alert("删除失败！");
						},
						success	:	function(msg){
							//参数msg自动存储了“响应数据”，json数组，用于局部刷新
							alert(msg.bool);
							location.href = "selectStaff.win";
						} 
					});
				}
			}
			
		</script>
  </head>
  
  <body>
    <table>
        <tr>
	        <td >
		        <input type="button" value="Solr" onclick="Solr()" />&nbsp;
		        <input type="button" value="Redis" onclick="Redis()" />
	        </td>
            <td>
                <form action="selectSolr.win" method="POST">
                	<input type="text" name="tname" value = "" />
                	<%-- <select name="xuanze" id="xuanze">
                        <option value="">请选择</option>
                        <option value="1">书名</option>
                        <option value="2">作者</option>
                        <option value="3">出版社</option>
                    </select>
                    &nbsp;<input type="text" name="tname" value = "${sessionScope.condition_bname }" />
                    <input type="hidden" name="currPageNo" value="${pager.currPageNo}"/>
	        		<input type="hidden" name="pageSize" value="${pager.pageSize}"/> --%>
                    &nbsp;<input type="submit" value="提交"/>
                </form>
            </td>
             <td><a href="selectDepartment.win">新增员工信息</a></td>
        </tr>
    </table>
    <table border="1" cellpadding="0" cellspacing="0">
	    <tr class="title">
	        <td>姓名</td>
	        <td>部门</td>
	        <td>电话</td>
	        <td>地址</td>
	        <td>操作</td>
	    </tr>
	      <c:if test="${empty list}">
			<tr>
				<td colspan="5"><h2>未查到符合您的查询条件的理财产品！</h2></td>
			</tr>
		</c:if>
		<c:if test="${not empty list}">
	    <c:forEach var="fp" items="${list}">
			<tr>
	        <td>${fp.sname }</td>
	        <td>${fp.department }</td>
	        <td>${fp.phot }</td>
	        <td>${fp.address }</td>
	        <td><a href="javascript:void(0)" onclick="querenDelete(${fp.id},this)">删除</a>
	        	<a href="selectStaffKey.win?pk=${fp.id}">修改</a></td>
	    </tr>
	    </c:forEach>
	    </c:if>
	</table>
	<div id="tip">${sessionScope.tip }</div>
  </body>
</html>
