$(document).ready(function() {
	$("#account-form").submit(function(e){
		var email = $("#email").val(); 
		var password = $("#password").val(); 			
		e.preventDefault();
		$.ajax({
	        url : 'login_submit',
	        method : 'get',
	        async : false,
	        data : {email : email,
	        	password : password},	    
	        contentType: "application/json",
	        dataType: "text",
	        error : function(data) {
	        	console.log(data.responseText);
	        	$( "#message").empty();
	        	$( "#message").append(data.responseText);	
	        	$( "#message").slideDown();	
	        },
	        success : function(data){
	        	window.location.assign("/_iTunesClone/home");
	        }	     
	     });
		
	});
});
