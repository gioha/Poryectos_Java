$(function() {
//	$('.tabs-catalogo a').click(function() {
//		$(this).parent().addClass("activo").siblings().removeClass("activo");
//	});
	$('#form-busqueda').on('click', '.ui-datatable-selectable', function() {
		$(this).children().first().find("input").prop('checked', true);
		$('html, body').animate({
	        scrollTop: $('#fin-pagina').offset().top
	    }, 1500);
	});
	$('.ui-datatable-selectable.ui-state-highlight').children().first().find("input").prop('checked', true);
	$('#panel-datos input, #panel-datos select').change(function() {
		var _contenedor = $(this).parents('.col-xs-12');
		_contenedor.find('.ui-message-error').hide();
		_contenedor.find('.ui-state-error').removeClass('ui-state-error');
	});
});
function muestraCargando() {
	$('#contenedor-tabla-responsables').addClass('esconde');
	$('#cargando').removeClass('esconde');
}
function escondeCargando() {
	$('#contenedor-tabla-responsables').removeClass('esconde');
	$('#cargando').addClass('esconde');
	$('html, body').animate({
        scrollTop: $('#contenedor-tabla-responsables').offset().top	
    }, 1500);
}
function validaComposBusqueda() {
	var pasaValidacion = false;
	$('#form-busqueda').find('input[type=text]').each(function() {
		if ($(this).val().trim().length >= 3) {
			pasaValidacion = true;
		}
	});
	$('#form-busqueda').find('select').each(function() {
		if ($(this).val().trim().length > 0) {
			pasaValidacion = true;
		}
	});
	if (pasaValidacion) {
		muestraCargando();
	} else {
		$('.label-busqueda').addClass('label-warning shake-it');
		setTimeout(function() {
			$('.label-busqueda').removeClass('shake-it');
		}, 3000);
	}
	return pasaValidacion;
}