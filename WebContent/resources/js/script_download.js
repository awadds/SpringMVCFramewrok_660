$(document).ready(function() {	
	console.log("running download script");
	
	$("#downloadButton").submit(function(e){
		
		console.log("button clicked");
		
        e.preventDefault();
        $.ajax({
        	url:'http://localhost:8080/_iTunesClone/download/json/personneJson.json',
        	method : 'get',
        	dataType: 'json',
        	async : false,
        	success: function (data, status) {
        		console.log(data);
        		
        		window.open(data.fileUrl);
        	},
        	error: function () {
                console.log("failed to load data");
            } 
        });
	});
	
});