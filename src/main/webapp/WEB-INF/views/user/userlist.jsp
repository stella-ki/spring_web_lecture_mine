<%@ include file="/WEB-INF/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" cellspacing="0" cellpadding="0" id="tablelist">
</table>
<script>
$(document).ready(function(){
	var au = new AjaxUtil("/user/userlistaction","it_s_mode");
	au.setCallbackSuccess(returnList);
	au.send();
})

function returnList(list){
	var url = list.url;
	var data = list.data;
   	$("#tablelist").html("<tr><th>아이디</th><th>게시판권한</th><th>삭제</th></tr>");
   	for(i=0; i<data.length;i++){
   		var str;
   		if(data[i].board_admin == "1"){
   			str="<option value = 1 selected=\"selected\">Y</option><option value = 0>N</option>";
   		}else{
   			str="<option value = 1 >Y</option><option value = 0 selected=\"selected\">N</option>";
   		}
   		$("#tablelist").append(
   				"<tr>" 
   				+ "<td>"+data[i].user_id+"</td>" 
   				+ "<td><select id='std_"+data[i].user_id+"' onchange='change_board_admin(\""+data[i].user_id+"\", this.value)'>"+str+"</select></td>" 
   				+ "<td><input type='button' value='삭제' onclick='mdel(\""+data[i].user_id+"\");' ></td>" 
   				+ "</tr>");
   	}
}
</script>
<input type="hidden" name="mode" id="mode" value="list"/>
<input type="hidden" name="userid" id="userid" value=""/>
<input type="hidden" name="boardadmin" id="boardadmin" value=""/>
</body>
</html>