$(document).ready(
		function() {
			/**
			 * Función para aplicar la animación al pasar sobre los elementos
			 * del menú.
			 */
			$(".menu_div_no_seleccionable").hover(
					function() {

						$(this).css("background-color", "#D5007F");
						$(this).find(".menu_imagen_estatica").hide();
						$(this).find(".menu_imagen_hover").show().css(
								"transform", "translate(0px , 13px)");

					}, function() {

						$(this).css("background-color", "#999999");
						$(this).find(".menu_imagen_hover").hide();
						$(this).find(".menu_imagen_estatica").show();

					});

			/**
			 * Función para aplicar la animación al pasar sobre los elementos
			 * del menú.
			 */
			$(".menu_div_no_seleccionable").on(
					"click",
					function() {

						if ($('.menu_div_seleccionado').length > 0) {
							$('.menu_div_seleccionado').prev().show();
							$('.menu_div_seleccionado').hide();
							$('.menu_div_seleccionado').removeClass(
									"menu_div_seleccionado");
						}

						$(this).hide();
						var padre = $(this).parent();
						padre.find(".menu_div_seleccionable").addClass(
								"menu_div_seleccionado");

					});
			/**
			 * Función para aplicar el Tooltip sobre los elementos del menú.
			 */
			$('[data-toggle="tooltip"]').tooltip();
		});