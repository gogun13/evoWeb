<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="Javascript1.2" src="../js/jquery-1.6.1.min.js"></script>
<script language="Javascript1.2" src="../js/jquery.AjaxFileUpload.js"></script>
<title>File Upload</title>
<script>

	$( document ).ready(function() {
		
		 $('input[type="file"]').ajaxfileupload({
			   'params': {'ac':'sss'},
		       'action': "http://localhost:8080/evoWeb/FileUploadSvc?act=readExcelData",           
		   'onComplete': function(response) {
		         $('#upload').hide();
		         alert("response :: " + response);
		         document.getElementById("frm").reset();
		         
		       },
		       'onStart': function() {
		         $('#upload').show(); 
		       },
		       'submit_button' :  null//$('#btnSubmit')
		  });
		
	});

	function save(){
		postVal 	= "";
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/evoWeb/FileUploadSvc?act=writeFile",
            data: postVal,
            beforeSend: "",
            success: function(data){
                if(data.indexOf('OK') > -1){
                    alert("SUCCESS");
                }else{
                	alert("ERROR");
                }
            }
        });
	}
	
</script>
</head>
<body>
	<form id="frm">
		<div id="div_upload">
			<input type="file" name="datafile" id="datafile" />
			<input type="button" name="btnSave" id="btnSave" value="save" onclick="save();" />
			<div id="upload" style="display: none;">Uploading..</div>
		</div>
	</form>
</body>
</html>