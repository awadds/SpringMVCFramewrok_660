$(document).ready(function() {
	$(".remove").click(function(){
		alert( "Handler for 'remove' called." );
		var id = $(this).parent().data('value');
		
		$.ajax({
		    type:"get",		    
		    async: false,
		    contentType: "application/json",
		    dataType: "json",
		    data: {id : id},
		    url: "removefilm",
		    complete: function(data){
		    	alert(data.responseText);
		    	$(".movie").each(function() {
		    		  if ($(this).data('value') == id){
		    			  $(this).remove();
		    		  }
		    	});
			}
		});
	});
	$("#confirm-button").click(function(){		
		alert( "Handler for 'confirm' called." );

		$.ajax({
		    type:"get",		    
		    async: false,
		    contentType: "application/json",
		    dataType: "json",
		    url: "confirm",
		    complete: function(data){
		    	alert(data.responseText);
		    	$("#cart-films-div").empty();	    
			}
		});
	});
	
});