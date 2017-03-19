
$(document).ready(function() {
	$("#pays").css('visibility','visible');
	$("#langues").css('visibility','visible');
	$("#temps").css('visibility','visible');
	$("#genres").css('visibility','visible');
	
	$("#genreButton").click(function(){
     $.ajax({
        url : 'updateGenre',
        method : 'get',
        async : false,
        complete : function(data) {
        	console.log(data.responseText);        	
        	$("#pays").css('visibility','hidden');
        	$("#langues").css('visibility','hidden');
        	$("#temps").css('visibility','hidden');
        	$("#genres").css('visibility','visible');
        }
     });
	});
	
	$("#paysButton").click(function(){
	     $.ajax({
	        url : 'updatePays',
	        method : 'get',
	        async : false,
	        complete : function(data) {
	        	console.log(data.responseText);
	        	$("#genres").css('visibility','hidden');	        	
	        	$("#langues").css('visibility','hidden');
	        	$("#temps").css('visibility','hidden');
	        	$("#pays").css('visibility','visible');
	        }
	     });
	});

	
	$("#langueButton").click(function(){
	     $.ajax({
	        url : 'updateLangue',
	        method : 'get',
	        async : false,
	        complete : function(data) {
	        	console.log(data.responseText);
	        	$("#genres").css('visibility','hidden');
	        	$("#pays").css('visibility','hidden');
	        	$("#temps").css('visibility','hidden');
	        	$("#langues").css('visibility','visible');
	        }
	     });
		});
	
	$("#tempsButton").click(function(){
		$("#genres").css('visibility','hidden');
    	$("#pays").css('visibility','hidden');
    	$("#langues").css('visibility','hidden');
    	$("#temps").css('visibility','visible');
	     
	});

	
	$("#account-button").click(function(){
	     $.ajax({
	        url : 'gotoaccount',
	        method : 'get',
	        async : false,
	        complete : function(data) {
	        	console.log(data.responseText);
	        	window.location.assign("/_iTunesClone/account");
	        }
	     });
	});
	
	$("#cart-button").click(function(){
	     $.ajax({
	        url : 'gotocart',
	        method : 'get',
	        async : false,
	        complete : function(data) {
	        	console.log(data.responseText);
	        	window.location.assign("/_iTunesClone/cart");
	        }
	     });
	});
	
	$("#loan-button").click(function(){
	     $.ajax({
	        url : 'gotoloan',
	        method : 'get',
	        async : false,
	        complete : function(data) {
	        	console.log(data.responseText);
	        	window.location.assign("/_iTunesClone/emprunts");
	        }
	     });
	});
	
	$("#logout-button").click(function(){
	     $.ajax({
	        url : 'logout',
	        method : 'get',
	        async : false,
	        complete : function(data) {
	        	console.log(data.responseText);
	        	window.location.assign("/_iTunesClone/login");
	        }
	     });
	});
	
	$("#search-form").submit(function(e){
		var search = $("#search-bar-input").val(); 
		var genres = [];
		var langues = [];
		var pays = [];
		$('#criterias-checkboxes input[name="genre-checkbox"]:checked').each(function() {
			  genres.push($(this).val());
		});
		$('#criterias-checkboxes input[name="langue-checkbox"]:checked').each(function() {
			  langues.push($(this).val());
		});
		$('#criterias-checkboxes input[name="pays-checkbox"]:checked').each(function() {
			  pays.push($(this).val());
		});
		var min = 0;
		var max = 0;
		if ($('input[name="min"]').val()){
			min = $('input[name="min"]').val();
		}
		if ($('input[name="max"]').val()){
			max = $('input[name="max"]').val();
		}
			
		e.preventDefault();
		$.ajax({
	        url : 'search',
	        method : 'get',
	        async : false,
	        data : {search : search,
	        	genres : genres,
	        	langues : langues,
	        	pays : pays,
	        	min : min, 
	        	max : max},	    
	        contentType: "application/json",
	        dataType: "json",
	        complete : function(data) {
	        	console.log(data.responseText);
	        	$( "#result-search-div" ).empty();
	        	var JSON = jQuery.parseJSON(data.responseText);
	        	var result;
	        	for (var i = 0; i < JSON.length; i++) {
	        		if (JSON[i].hasOwnProperty('titre')){
	        			result = '<div class="movie col-sm-3" data-value="' + JSON[i].id + '"><div class="poster-div"> <img class="poster" src="' + JSON[i].poster + '" /> </div> <div class="title-div">' + JSON[i].titre + '</div></div>';
	        			$( "#result-search-div").append(result);
	        		}
	        		if (JSON[i].hasOwnProperty('nom')){
	        			result = '<div class="person col-sm-3" data-value="' + JSON[i].id + '"><div class="photo-div"> <img class="photo" src="' + JSON[i].photo + '" /> </div> <div class="nom-div">' + JSON[i].nom + '</div></div>';
	        			$( "#result-search-div").append(result);
	        		}
	        	}
	        
	        }

	     });
		
	});
	
	$('#result-search-div').on('click', '.movie', function(){
		var id = $(this).data('value');
		var type = 'movie';
		$.ajax({
	        url : 'gotodetails',
	        method : 'get',
	        async : false,
	        data : {id : id, type : type},	    
	        complete : function(data) {
	        	window.location.assign("/_iTunesClone/details");	   
	        }
		});	
	});
	
	$('#result-search-div').on('click', '.person', function(){
		var id = $(this).data('value');
		var type = 'person';
		$.ajax({
	        url : 'gotodetailspersonne',
	        method : 'get',
	        async : false,
	        data : {id : id, type : type},	    
	        complete : function(data) {
	        	window.location.assign("/_iTunesClone/details_personne");	   
	        }
		});	
	});
	
	
	$("#upload-form").submit(function(e){
		var formData = new FormData();
		formData.append('file', $('input[type=file]')[0].files[0]);
		console.log("form data " + formData);
		e.preventDefault();
		$.ajax({
	        url : 'uploadjson',
	        processData : false,
            contentType : false,
            type : 'POST',
	        async : false,
	        data : formData,	
	        dataType : 'json',
	        enctype: 'multipart/form-data',
	        complete : function(data) {
	        	console.log(data.responseText);
	        	$( "#result-search-div" ).empty();
	        	var JSON = jQuery.parseJSON(data.responseText);
	        	var result;
	        	for (var i = 0; i < JSON.length; i++) {
	        		if (JSON[i].hasOwnProperty('titre')){
	        			result = '<div class="movie col-sm-3" data-value="' + JSON[i].id + '"><div class="poster-div"> <img class="poster" src="' + JSON[i].poster + '" /> </div> <div class="title-div">' + JSON[i].titre + '</div></div>';
	        			$( "#result-search-div").append(result);
	        		}
	        		if (JSON[i].hasOwnProperty('nom')){
	        			result = '<div class="person col-sm-3" data-value="' + JSON[i].id + '"><div class="photo-div"> <img class="photo" src="' + JSON[i].photo + '" /> </div> <div class="nom-div">' + JSON[i].nom + '</div></div>';
	        			$( "#result-search-div").append(result);
	        		}
	        	}	        
	        }
	     });	
	});
});