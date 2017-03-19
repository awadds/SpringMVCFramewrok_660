$(document).ready(function() {
	
	$("#general-form").on('submit', (function(){
		alert( "Handler for .submit() called." );
		var c = {};
		c['nomFamille'] = $('#nom').val();
		c['prenom'] = $('#prenom').val();
		c['courriel'] = $('#telephone').val();
		c['tel'] = $('#telephone').val();
		c['anniversaire'] = $('#anniversaire').val();
		c['adresse'] = $('#adresse').val();
		c['ville'] = $('#ville').val();
		c['province'] = $('#province').val();
		c['codePostal'] = $('#codepostal').val();
		c['forfait'] = $('#forfait').val();
		c['id'] = -1;
		
		$.ajax({
		    type:"get",		    
		    async: false,
		    contentType: "application/json",
		    dataType: "json",
		    data: {nom : c['nomFamille'],
		    	prenom : c['prenom'],
		    	courriel :c['courriel'],
		    	tel : c['tel'],
		    	anniversaire : c['anniversaire'],
		    	adresse : c['adresse'],
		    	ville: c['ville'],
		    	province : c['province'],
		    	codepostal: c['codePostal'],
		    	forfait: c['forfait']
		    },
		    url: "saveGeneral",
		    success: function(){
		       alert("success");
		    }
		});
	}));
	
	$("#carte-form").on('submit', (function(){
		alert( "Handler for .submit() called." );
		var c = {};
		c['id'] = $(this).data('value');
		c['numero'] = $('#numero').val();
		c['expmois'] = $('#expmois').val();
		c['expannee'] = $('#expannee').val();
		c['type'] = $('#type').val();
		
		$.ajax({
		    type:"get",		    
		    async: false,
		    contentType: "application/json",
		    dataType: "json",
		    data: {id : c['id'],
		    	numero : c['numero'],		    
		    	expmois : c['expmois'],
		    	expannee :c['expannee'],
		    	type : c['type']		   
		    },
		    url: "saveCarte",
		    success: function(){
		       alert("success");
		    }
		});
	}));
	
	
});