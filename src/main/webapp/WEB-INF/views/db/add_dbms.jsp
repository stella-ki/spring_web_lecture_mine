<%@ include file="/WEB-INF/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Home</title>
</head>
<script>
//만약 이렇게 function안에 없다면 이 jsp가 시작되자 마자 해당 소스가 시작되는데 it_s_url와 같은 정보가 없기 때문에 에러가 난다...
function doadd() {
	var params = "it_s_url, it_s_port, it_s_dbms, it_s_dbname, it_s_id, it_s_pwd"
	var au = new AjaxUtil("db/add", params);
	au.send();
}
</script>
<body>
<table border="1">
	<tr>
		<td colspan = "2">신규 데이터 베이스 입력</td>
	</tr>
	<tr>
		<td>url</td><td><input type = "text" name = "url" id = "url"/></td>
	</tr>	
	<tr>
		<td>port</td><td><input type = "text" name = "port" id = "port"/></td>
	</tr>	
	<tr>
		<td>dbms</td><td><input type = "text" name = "dbms" id = "dbms"/></td>
	</tr>
	<tr>
		<td>dbname</td><td><input type = "text" name = "dbname" id = "dbname"/></td>
	</tr>
	<tr>
		<td>id</td><td><input type = "text" name = "id" id = "id"/></td>
	</tr>	
	<tr>
		<td>pwd</td><td><input type = "text" name = "pwd" id = "pwd"/></td>
	</tr>
	<tr>
		<td colspan = "2"><input type="button" value = "신규디비생셩" onclick = "doadd()"></td>
	</tr>

</table>
</body>
</html>
