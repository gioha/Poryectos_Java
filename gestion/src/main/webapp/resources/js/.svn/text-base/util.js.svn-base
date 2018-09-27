$(document).ready(function(){
	util.focusOnLogin();
});


var util = (function(){
	
	var _priv = {
			//private attributes
			scrollPos : 0
			//private methods
	};
	
	return {
		//public attributes
		
		//public methods
		init : function(args){//construct
			
		},
		
		saveScroll : function(){
			_priv.scrollPos = $(window).scrollTop();
		},
		
		restartScroll : function(){
			window.scrollTo(0, _priv.scrollPos);
		},
		
		//Métodos específicos del módulo de registrar periodo
		registrarPeriodo : {
			//atributos específicos del módulo de registrar periodo
			primerFechaDisponibleParaFinPeriodo : null,
			primerFechaDisponibleParaInicioPre : null,
			primerFechaDisponibleParaFinPre : null,
			primerFechaDisponibleParaInicioInter : null, 
			primerFechaDisponibleParaFinInter : null, 
			primerFechaDisponibleParaInicioCamp : null,
			ultimaFechaDisponible : null,
			
			//si esta definido el día del inicio del periodo ya podemos saber 
			//los días que deshabilitaremos para el fin del periodo
			//si no esta definido el día del inicio del periodo no deshabilitamos nada
			deshabilitaDiasFinPeriodo : function(date){
				if(util.registrarPeriodo.primerFechaDisponibleParaFinPeriodo != null){
					if(date.getTime() < new Date(util.registrarPeriodo.primerFechaDisponibleParaFinPeriodo).getTime()){
						return [false];
					}
				}
				return [true];
			},
			deshabilitaDiasFinPrecampania : function(date){
				if(util.registrarPeriodo.primerFechaDisponibleParaFinPre != null){
					if(util.registrarPeriodo.ultimaFechaDisponible != null){
						if(date.getTime() < new Date(util.registrarPeriodo.primerFechaDisponibleParaFinPre).getTime() || date.getTime() > new Date(util.registrarPeriodo.ultimaFechaDisponible)){
							return [false];
						}
					}else if(date.getTime() < new Date(util.registrarPeriodo.primerFechaDisponibleParaFinPre).getTime()){
						return [false];
					}
				}
				return [true];
			},
			deshabilitaDiasInicioCampania: function(date){
				if(util.registrarPeriodo.primerFechaDisponibleParaInicioCamp != null){
					if(util.registrarPeriodo.ultimaFechaDisponible != null){
						if(date.getTime() < new Date(util.registrarPeriodo.primerFechaDisponibleParaInicioCamp).getTime() || date.getTime() > new Date(util.registrarPeriodo.ultimaFechaDisponible)){
							return [false];	
						}
					}else if(date.getTime() < new Date(util.registrarPeriodo.primerFechaDisponibleParaInicioCamp).getTime()){
						return [false];
					}
				}
				return [true];
			},
			deshabilitaDiasInicioPeriodo: function(date){
				if(date.getTime() < (new Date().getTime())-86400000){
					return [false];
				}
				return [true];
			},
			deshabilitaDiasInicioPrecampania : function(date){
				if(util.registrarPeriodo.primerFechaDisponibleParaInicioPre != null){
					if(util.registrarPeriodo.ultimaFechaDisponible != null){
						if(date.getTime() < new Date(util.registrarPeriodo.primerFechaDisponibleParaInicioPre).getTime() || date.getTime() > new Date(util.registrarPeriodo.ultimaFechaDisponible)){
							return [false];
						}
					}else if(date.getTime() < new Date(util.registrarPeriodo.primerFechaDisponibleParaInicioPre).getTime()){
						return [false];
					}
				}
				return [true];
			},
			deshabilitaDiasInicioIntercampania : function(date){
				if(util.registrarPeriodo.primerFechaDisponibleParaInicioInter != null){
					if(util.registrarPeriodo.ultimaFechaDisponible != null){
						if(date.getTime() < new Date(util.registrarPeriodo.primerFechaDisponibleParaInicioInter).getTime() || date.getTime() > new Date(util.registrarPeriodo.ultimaFechaDisponible)){
							return [false];
						}
					}else if(date.getTime() < new Date(util.registrarPeriodo.primerFechaDisponibleParaInicioInter).getTime()){
						return [false];
					}
				}
				return [true];
			},
			deshabilitaDiasFinIntercampania : function(date){
				if(util.registrarPeriodo.primerFechaDisponibleParaFinInter != null){
					if(util.registrarPeriodo.ultimaFechaDisponible != null){
						if(date.getTime() < new Date(util.registrarPeriodo.primerFechaDisponibleParaFinInter).getTime() || date.getTime() > new Date(util.registrarPeriodo.ultimaFechaDisponible)){
							return [false];
						}
					}else if(date.getTime() < new Date(util.registrarPeriodo.primerFechaDisponibleParaFinInter).getTime()){
						return [false];
					}
				}
				return [true];
			}
		},

		focusOnLogin : function(){
			var j_username = $("#j_username");
			if(j_username.length > 0){
				j_username.focus();
			}
		},
		
		reloj : { 
			formato : null,
			dateFormat : null, 
			currentTimes: {
				"serverClock" : null,
				"dbClock" : null
			}, 
			components : {
				"serverClock" : null,
				"dbClock" : null
			},
			format : null, 
			intervals : {
				"serverClock" : null,
				"dbClock" : null
			},
			init: function(current, component, dateFormat, instance, format){
		        this.format = format || "HH:MM:ss dd/mm/yyyy";
		        this.dateFormat = dateFormat;
		        this.currentTimes[instance] = new Date(current);
		        this.components[instance] = component;
		        this.start(instance);
			},
		    refresh: function(current, instance, format) {
		        clearInterval(this.intervals[instance]);
		        this.init(format, current)
		    },
			start: function(instance) {
		        var that = this;
		        this.intervals[instance] = setInterval(function(){
		            that.updateOutput(instance)
		        }, 1000)
		    },
		    stop: function(instance) {
		        clearInterval(intervals[instance])
		    },
		    updateOutput: function(instance) {
		    	console.log("upateingoutput");
		        this.currentTimes[instance].setTime(this.currentTimes[instance].getTime() + 1000);
		        $(this.components[instance]).text(this.dateFormat(this.currentTimes[instance], this.format))
		    },
		},
		/*
		 * Método utilizado apra prevenir que se pueda regresar en el historial de páginas al presionar el back button del navegador
		 * agregamos un evento que agrega en el hsitorial de navegacion la propia pagina en la que se encuentra, lo que no deja hacer back
		 * */
		system : {
			preventBackButton : function(){
				history.pushState(null, null, document.URL);
				window.addEventListener('popstate', function () {
				    history.pushState(null, null, document.URL);
				});
			}
		}
	}
})();


function flecha() {
        jQuery(window).scroll(function() {
            if(jQuery(this).scrollTop() > 151) {
                jQuery('#toTop').fadeIn();
            } else {
                jQuery('#toTop').fadeOut();
            }
        });
        jQuery('body').on('click', "#toTop,.to-top", function() {
	            jQuery('body,html').animate({scrollTop:150},100);
	    });
		jQuery('body').on('click', "#toBottom,.to-bottom", function() {
				jQuery("html, body").animate({ scrollTop: $(document).height() }, 100);
	    });
		jQuery('body').on('click', ".go-to-timeline", function(){
			jQuery("html, body").animate({ scrollTop: $(".timeline").offset().top }, 100);
		});
}
flecha();