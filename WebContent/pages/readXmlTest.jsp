<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="Javascript1.2" src="../js/jquery-1.6.1.min.js"></script>
<title>Read XML File</title>
<script>
	function onClickXmlBtn(){
		postVal 	= "";
	    $.ajax({
	        type: "POST",
	        url: "http://localhost:8080/evoWeb/ReadXmlTestSvc?act=onload",
	        data: postVal,
	        beforeSend: "",
	        success: function(data){
		    	var xmlDoc 		= $.parseXML(data);
		        var $xml 		= $(xmlDoc);
		        $xml.find('Goods').each(function(){
                    var name = $(this).find("GoodsDescriptionText").text();
                    alert(name);
                });
	        }
	    });
	}

	function onClickJsonBtn(){
		var postVal 	= "";
		var jsonObj		= null;

		try{
		    $.ajax({
		        type: "POST",
		        url: "http://localhost:8080/evoWeb/ReadXmlTestSvc?act=json",
		        data: postVal,
		        beforeSend: "",
		        success: function(data){
			    	alert(data);
			    	jsonObj = JSON.parse(data);//Json string to json obj, It two ways choose $.parseJSON(data) and JSON.parse(data)
			    	alert(jsonObj.company);//get data in json node
			    	alert(JSON.stringify(jsonObj.messages));//Json obj to json String
	
			    	//Loop data in json array
			    	$.each(jsonObj.messages, function(idx, obj) {
			    		alert(obj.balance + " " + obj.num+ " " + obj.is_vip+ " " + obj.name);
			    	});
		        }
		    });
		}catch(e){
			alert(e);
		}
	}

	function onClickReadJsonBtn(){
		var postVal 	= "";
		var jsonObj 	= {  "company": "Summit computer"
							,"messages":[{"balance":1000.21,"num":100,"is_vip":true,"name":"foo"}
									    ,{"balance":1200.5,"num":99,"is_vip":true,"name":"Pound"}]};

		try{
			postVal = "act=readJson&strRequest=" + JSON.stringify(jsonObj);
		    $.ajax({
		        type: "POST",
		        url: "http://localhost:8080/evoWeb/ReadXmlTestSvc",
		        data: postVal,
		        beforeSend: "",
		        success: function(data){
			    	
		        }
		    });
		}catch(e){
			alert(e);
		}
	}
	
</script>
</head>
<body>
<input type="button" id="xmlBtn" name="xmlBtn" value="xmlBtn" onclick="onClickXmlBtn();" />
<input type="button" id="jsonBtn" name="jsonBtn" value="jsonBtn" onclick="onClickJsonBtn();" />
<input type="button" id="readJsonBtn" name="readJsonBtn" value="readJsonBtn" onclick="onClickReadJsonBtn();" />
</body>
</html>