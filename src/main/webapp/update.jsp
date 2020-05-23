<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	    <form action="updateStaff.win" method="post">
	        <table border="0" cellpadding="0" cellspacing="0">
	            <tr class="title">
	                <td colspan="3">修改员工</td>
	            </tr>
	            <tr>
	                <td>姓名：</td>
	                <td>
		                <input type="hidden" name="id" id="id" />
		                <input type="text" id="sname" name="sname" />
	                </td>
	                <td><span id="ajax_info"></span></td>
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
	                <td><input type="text" id="phot" name="phot"/></td>
	                <td></td>
	            </tr>
	             <tr>
	                <td>地址：</td>
	                <td><input type="text" id="address" name="address"/>（yyyy-MMM-dd）</td>
	                <td></td>
	            </tr>
	            <tr class="center">
	                <td colspan="3">
	                	<input type="submit" value="保存"/>&nbsp;
	                	<input type="button" value="返回" onclick="runback()"/></td>
	            </tr>
	        </table>
	    </form>
	</div>
<script type="text/javascript" src="/scripts/jquery-1.12.4.js"></script>
	<script type="text/javascript">
	function runback(){
		location.href = "selectStaff.win";
	}
	$(function(){
		$("#id").val("${sff.id}");
		$("#sname").val("${sff.sname}");
		$("#department").val("${sff.department}");
		$("#phot").val("${sff.phot}");
		$("#address").val("${sff.address}");
	});
	
	</script>
</body>
</html>