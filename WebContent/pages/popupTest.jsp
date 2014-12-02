<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="Javascript1.2" src="../js/jquery-1.6.1.min.js"></script>
<title>Popup test</title>
<style type="text/css">

	.hideDiv{
		display: none;
	}
	
	.showDiv{
		display	: block;
		width	: 200px;
		position: absolute;
	    left: 0px;
	    top: 25px;
	    z-index: 99;
	    background-color: white;
	    border:1px solid #999999;  
	    -moz-box-shadow: 5px 5px 5px #ccc;
		-webkit-box-shadow: 5px 5px 5px #ccc;
		box-shadow: 5px 5px 5px #ccc;
		float:left;
		text-align: left;
	}
	
	.trFind:HOVER {
		background-color: #ADC3E7;
		cursor: inherit;
	}
	
	.trFindR {
		background-color: white;
	}
	
}
	
</style>
<script>

	function lp_onloadPage(){
		lo_find = null;

		try{
			lo_find 			= document.getElementById("find");
			lp_findData(lo_find);
		}catch(e){
			alert(e.message);
		}
	}

	function lp_findData(ao_obj){
		var lo_findPopup 			= null;
		var postVal 				= "";
		var lo_dataResult			= null;
		var lo_dataList				= null;
		var la_data					= null;
		var lv_code					= "";
		var lv_description  		= "";
		var lv_tableOpenTags		= '<table id="findPopupTab" border="0" cellpadding="1" cellspacing="0" width="200px;">';
		var lv_tableCloseTags		= '</table>';
		var lv_tr					= "";

		try{
			lo_findPopup 			= document.getElementById("findPopup");

			if(ao_obj.value!=""){
				$.ajax({
		            type: "POST",
		            url: "http://localhost:8080/evoWeb/PopupSvc?act=findData",
		            data: postVal,
		            beforeSend: "",
		            success: function(data){
		                if(gp_checkResultCode(data)){
		                	lo_findPopup.className 	= "showDiv";

		                	lo_dataResult 	= gp_dataResult(data);
		                	lo_dataList		= lo_dataResult.getElementsByTagName("DataList")[0];
		                	la_data			= lo_dataList.childNodes;

		                	if(la_data){
			                	for(var i=0;i<la_data.length;i++){
			                		lv_code 			= la_data[i].getElementsByTagName("Code")[0].text;
			                		lv_description 		= la_data[i].getElementsByTagName("Description")[0].text;

			                		lv_tr				= lv_tr + '<tr class="trFind" onClick="lp_setType(this);">'
					                								+ '<td>' 
					                									+ lv_description
					                									+ '<input type="hidden" name="hidType" value="' + lv_code + ' - ' + lv_description + '" />' 
					                								+ '</td>'
					                							+ '</tr>';
			                	}

			                	lo_findPopup.innerHTML = lv_tableOpenTags + lv_tr + lv_tableCloseTags;
			                	
		                	}
		                	
		                }else{
		                	alert("ERROR");
		                }
		            }
		        });
				
			}else{
				lo_findPopup.className 	= "hideDiv";
			}
		}catch(e){
			alert(e.message);
		}
	}

	function lp_setType(ao_obj){

		var la_hidType 	= null;
		var lv_index	= null;
		var lo_find		= null;
		
		try{
			la_hidType 	= document.getElementsByName("hidType");
			lv_index	= gp_rowTableIndex(ao_obj);
			lo_find		= document.getElementById("find");

			lo_find.value = la_hidType[lv_index].value;
			lp_hideFindPopup();

			
		}catch(e){
			alert(e.message);
		}
	}

	function gp_rowTableIndex(ao_obj){
	    var lv_index            = 0;
	    var lv_tagName          = "";
	    var lo_obj              = ao_obj;
	    
	    lv_tagName  = lo_obj.tagName.toUpperCase();
	    while (lv_tagName != "TR") {
	        lo_obj      = lo_obj.parentNode;
	        lv_tagName  = lo_obj.tagName.toUpperCase();
	    }
	    
	    lv_index = lo_obj.rowIndex;
	    
	    return lv_index;
	}

	function gp_xmlParser(av_xmlStr){

		var parser	= null;
		var xmlDoc 	= null;
		
		try{
			if (window.DOMParser){
				parser = new DOMParser();
				xmlDoc = parser.parseFromString(av_xmlStr,"text/xml");
			}else{ // Internet Explorer 
				xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
				xmlDoc.async=false;
				xmlDoc.loadXML(av_xmlStr); 
			}
		}catch(e){
			alert(e.message);
		}

		return xmlDoc;
	}

	function gp_checkResultCode(av_xmlStr) {

		var lo_objXml 			= null;
		var lo_header			= null;
		var lo_body				= null;
		var lo_resultCode		= null;
		var lo_errMsg			= null;
		
		try{
			lo_objXml = gp_xmlParser(av_xmlStr);
			lo_header = lo_objXml.getElementsByTagName("Header")[0];
			if (lo_header) {
				lo_resultCode = lo_header.getElementsByTagName("ResultCode")[0];
				if(lo_resultCode.text=="OK"){
					return true;
				}else{
					lo_body 		= lo_objXml.getElementsByTagName("Body")[0];
					lo_errMsg 		= lo_body.getElementsByTagName("ErrMsg")[0];
					return false;
				}
			}

			
		}catch(e){
			alert(e.message);
		}
	}

	function gp_dataResult(av_xmlStr) {

		var lo_objXml 			= null;
		var lo_body				= null;
		
		try{
			lo_objXml 		= gp_xmlParser(av_xmlStr);
			lo_body 		= lo_objXml.getElementsByTagName("Body")[0];

			return lo_body;
		}catch(e){
			alert(e.message);
		}
	}

	function lp_hideFindPopup(){
		lo_findPopup = null;

		try{
			lo_findPopup 			= document.getElementById("findPopup");
			lo_findPopup.className 	= "hideDiv";
		}catch(e){
			alert(e.message);
		}
	}

</script>
</head>
<body onload="lp_onloadPage();">
	<div style="position : relative;">
		<input type="text" name="find" id="find" value="" style="width:200px;" onkeyup="lp_findData(this);" />
		<div id="findPopup"></div>
	</div>
	<div>
		<input type="text" name="name" id="name" value="xxxxx" style="width:200px;" />
	</div>
</body>
</html>