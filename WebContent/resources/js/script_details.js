$(document).ready(function() {	
	
	var date_diff_indays = function(date1, date2) {
		return Math.floor((Date.UTC(date2.getFullYear(), date2.getMonth(), date2.getDate()) - Date.UTC(date1.getFullYear(), date1.getMonth(), date1.getDate()) ) /(1000 * 60 * 60 * 24));
	}
	
	var correctDates = function(diff) {
		$("#message-cout").empty();
		 if (diff >= 0){
	        	cout = coutBase + diff * coutJour;
	        	$("#message-cout").append("Cout : " + cout);
	        	return true;
	        } else {
	        	$("#message-cout").append("Dates incorrectes");
	        	return false;
	        }		 
	}
	
	var diff;
	var coutBase = 5;
	var coutJour = 3;
	var cout;
	var today = moment().format('YYYY-MM-DD');
	$("#date-debut").val(today);
	$("#date-debut").attr({
	       "min" : today      
	    });
	
	$("#date-fin").val(today);
	$("#date-fin").attr({
	       "min" : $("#date-debut").attr('min')   
	    });
	
	$('#date-debut').change(function(){
		var test = $("#date-fin").val();
		if ($("#date-fin").val() != ""){
	        alert(this.value);         //Date in full format alert(new Date(this.value));
	        var debut = new Date(this.value);
	        var fin = new Date($("#date-fin").val());
	        diff = date_diff_indays(debut, fin);
	        correctDates(diff);
		}
    });
	
	$('#date-fin').change(function(){
		if ($("#date-debut").val() != ""){
	        alert(this.value);         //Date in full format alert(new Date(this.value));
	        var debut = new Date($("#date-debut").val());
	        var fin = new Date(this.value);
	        diff = date_diff_indays(debut, fin);
	        correctDates(diff);
		}
    });
	
	$("#buy-button").click(function(){
		var debut = $("#date-debut").val();
		var fin = $("#date-fin").val();
		var debutDate = new Date(debut);
        var finDate = new Date(fin);
        diff = date_diff_indays(debutDate, finDate);        
		if (correctDates(diff)){			
		     $.ajax({
		        url : 'addtocart',
		        method : 'get',
		        contentType: "application/json",
			    dataType: "json",
		        data : { debut : debut, fin : fin, cout : cout},
		        async : false,
		        complete : function(data) {
		        	console.log(data.responseText);
		        	$( "#message").empty();
		        	$( "#message").append(data.responseText);	        	        	
		        }
		     });
		}
	});
});