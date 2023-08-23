function doAjax(url, para) {
	var reResp = null;
	$.ajax({
		url:  url,
		type: "POST", 
		dataType: "json", 
		data: JSON.stringify(para),
		async: false,
		contentType: "application/json",
		success: function (resp) {
			reResp = resp;
		},
		error: function (resp, status, err) {
			console.error("system error");
			console.error(err);
			alert('처리 중 오류가 발생하였습니다. \n관리자에게 문의하세요');
			reResp = "error";
		}
	});
	return reResp;
};
function doGetAjax(url) {
	var reResp = null;
	$.ajax({
		url:  url,
		type: "GET",
		dataType: "json",
		async: false,
		contentType: "application/json",
		success: function (resp) {
			reResp = resp;
		},
		error: function (resp, status, err) {
			console.error("system error");
			console.error(err);
		}
	});
	return reResp;
};

function getCode(url, para, add) {
	var codes = doAjax("/code" + url, para);
	var reCodes = [];
	
	if(add == 'no'){
		reCodes = codes;
	} else if(add == 'choice'){
		reCodes = [{cd : '', nm : '선택'}].concat(codes);
	} else {
		reCodes = [{cd : '', nm : '전체'}].concat(codes);
	}
	
	return reCodes;
}

function getList(url, para) {
	return doAjax( url + "/list.tf", para);
}

function getInfo(url, para) {
	return doAjax( url + "/info.tf", para);
}

function goSave(url, para) {
	return doAjax( url + "/save.tf", para);
}


function movePage(url, data) {
	var $form = $("<form>").attr("action", url).attr("method", "POST").appendTo("body");
	
	if(data != null ){
		data.forEach(function(val){
			$("<input>").attr({type:"hidden", name : val.nm, value : val.val}).appendTo($form);
		});
	}
	$form.submit().remove();
};

function moveList(url, data) {
	movePage(url, data);
}

function moveView(url, data) {
	movePage(url+"/view", data);
}
