<%@ include file="/WEB-INF/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<script>
function doRegister() {
	var params = "it_s_username, it_s_userid, it_s_userpwd, it_n_age, s_s_admin, s_s_boardadmin"
	var au = new AjaxUtil("user/register", params);
	au.send();
}
</script>

<body>

<table>
<tr><td>이름 </td><td><input type = "text" name = "username"/></td></tr>
<tr><td>아이디 </td><td><input type = "text" name = "userid"/></td></tr>
<tr><td>비번 </td><td><input type = "password" name = "userpwd"/></td></tr>
<tr><td>나이 </td><td><input type = "text" name = "age"/></td></tr>
<tr><td>관리자 권한 </td><td><select name = "admin" >
<option value = 1>Y</option>
<option value = 0>N</option>
</select></td></tr>
<tr><td>게시판 관리자 권한 </td><td>
<select name = "boardadmin">
<option value = 1>Y</option>
<option value = 0>N</option>
</select></td></tr>
</table>
<input type="button" value="완료" onclick="doRegister()">
</body>
</html>