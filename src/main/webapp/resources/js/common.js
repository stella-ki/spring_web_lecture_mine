/**
 * 
 */

function pageMove(param){
	if(param){
		document.getElementById("urlStr").value = param;
		$("#urlForm").submit();
	}else{
		alert("url을 입력해주세요.");
	}
}

function urlSubmit(f){
	return true;
}

var AjaxUtil = function (url, params, type, dataType){
	if(!url){
		alert("url정보가 없습니다.");
		return null;
	}
	this.url = "/exam/" + url;
	var initData = {}
	initData["projectName"] = "exam";
	this.isVal = true;
	
	this.numCheck = function(value){
		var num = new Number(value);
		if(isNaN(num)){
			return false;
		}
		return true;
	}
	
	this.validation = function(objValidation,obj){
		if(objValidation=="n"){
			if(!this.numCheck(obj.val())){
				alert(obj.attr("name") + "값은 숫자만 입력하실 수 있습니다.");
				obj.focus();
				obj.val("");
				this.isVal = false;
				return false;
			}
		}
	}
	this.param = JSON.stringify(initData);
	if(params){
		var paramArr = params.split(",");

		var data = {};
		for(var i=0,max=paramArr.length;i<max;i++){
			var objType= paramArr[i].split("_")[0];
			var objValidation= paramArr[i].split("_")[1];
			var objName= paramArr[i].split("_")[2];
			
			var obj = $("input[name="+objName+"]");
			var value = "";
			if(obj.length==0){
				value = $("select[name=" + objName +"]").val();				
			}else{
				value = $("input[name=" + objName +"]").val();				
			}
			this.validation(objValidation,obj);
			data[objName] = value;
		}
		this.param = JSON.stringify(data);
	}
	this.type = type?type:"POST";
	this.dataType = dataType?dataType:"json";
	this.callbackSuccess = function(json){
    	var url = json.url;
    	var data = json.data;
    	var msg = json.msg;
    	if(msg){
    		alert(msg);
    	}
    	if(url){
        	pageMove(url);
    	}
	}
	
	this.setCallbackSuccess = function(callback){
		this.callbackSuccess = callback;
	}
	
	this.send = function(){
		if(!this.isVal) return;
		$.ajax({ 
	        type     : this.type
	    ,   url      : this.url
	    ,   dataType : this.dataType 
	    ,   beforeSend: function(xhr) {
	        xhr.setRequestHeader("Accept", "application/json");
	        xhr.setRequestHeader("Content-Type", "application/json");
	    }
	    ,   data     : this.param
	    ,   success : this.callbackSuccess
	    ,   error : function(xhr, status, e) {
		    	alert("에러 : "+e);
		},
		done : function(e) {
		}
		});
	}
}


function mdel(mid){
	if(confirm("삭제하시겠습니까?")){
		$("#mode").val("del");
		$("#userid").val(mid);
		var au = new AjaxUtil("/user/userlistaction","it_s_mode,it_s_userid");
		au.setCallbackSuccess(returnDel);
		au.send();
	}
}

function returnDel(list){
	var url = list.url;
	var data = list.data;
	var msg = list.msg;
	alert(msg);
	pageMove(url);
}
function change_board_admin(ids, vals){
	if(confirm("권한을 설정하시겠습니까?")){
		$("#userid").val(ids);
		$("#boardadmin").val(vals);
		var au = new AjaxUtil("/user/changeBoardAdmin","it_s_userid,it_s_boardadmin");
		au.setCallbackSuccess(returnSession);
		au.send();
	}
}
function returnSession(result){
	var url = result.url;
	alert("설정되었습니다.");
	pageMove(url);
}
