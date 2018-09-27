	iniciarDragAndDrop();
	
	function iniciarDragAndDrop() {

		$( ".caja-drag-separador" ).draggable({
			revert: "invalid",
			snap: ".caja-drop-separador",
		    stack: ".caja-drag-separador",
		    
		});
	
	    $( ".caja-drop-separador" ).droppable({
	    	accept: function(draggable) {
	    		if( draggable.hasClass("caja-drag-separador") && $(this).has(".caja-drag-separador").length == 0) {
	    			return true;
	    		}
	            return false;
	    	},
	          activeClass: "drag-activo",
	          drop: function( event, ui ) {
	              console.log("drop");
	              var valor = ui.draggable.find(".oculto").first().val();
	              console.log(valor);
	              var coordenadas = $(this).find(".coor").first().val().split("-");
	              console.log(coordenadas[0]);
	              console.log(coordenadas[1]);
	              var edicion = $(this).parents( ".modoEdicion" ).length;
	              var params = [
	                      {name: 'valor', value:  valor},
		                  {name: 'coordenadas1', value: coordenadas[0]},
		                  {name: 'coordenadas2', value: coordenadas[1]}
		              ];
	              if(edicion > 0) {
	            	  console.log("edicion");
	            	  generarAcronimoEdicion(params);
	              } else {
	            	  console.log("normal");
		              generarAcronimo(params);      	            	  
	              }

	            }
	        });
		
		$( ".caja-drag-texto" ).draggable({
			revert: "invalid",
			snap: ".caja-drop-texto",
		    stack: ".caja-drag-texto",
		    
		});
	
	    $( ".caja-drop-texto" ).droppable({
	    	accept: function(draggable) {
	    		if( draggable.hasClass("caja-drag-texto") && $(this).has(".caja-drag-texto").length == 0) {
	    			return true;
	    		}
	            return false;
	    	},
	          activeClass: "drag-activo",
	          drop: function( event, ui ) {
	              console.log("drop");
	              var valor = ui.draggable.find(".oculto").first().val();
	              console.log(valor);
	              var coordenadas = $(this).find(".coor").first().val().split("-");
	              console.log(coordenadas[0]);
	              console.log(coordenadas[1]);
	              
	              var edicion = $(this).parents( ".modoEdicion" ).length;
	              var params = [
	                      {name: 'valor', value:  valor},
		                  {name: 'coordenadas1', value: coordenadas[0]},
		                  {name: 'coordenadas2', value: coordenadas[1]}
		              ];
	              if(edicion > 0) {
	            	  console.log("edicion");
	            	  generarAcronimoEdicion(params);
	              } else {
	            	  console.log("normal");
		              generarAcronimo(params);      	            	  
	              } 
	            }
	        });
		

	}
