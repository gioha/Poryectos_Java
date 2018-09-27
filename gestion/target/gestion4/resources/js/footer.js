$(document).ready(
		function() {

			/**
			 * Función para esconder la sección Gris del footer.
			 */
			$("#footer_flecha").on(
					"click",
					function() {
						if ($("#footer_flecha.ocultar_gris").length > 0) {
							$("#footer_contenedor_gris").addClass(
									"footer_contenedor_gris_oculto");
							$("#footer_flecha").removeClass("ocultar_gris");
						} else {
							$("#footer_contenedor_gris").removeClass(
									"footer_contenedor_gris_oculto");
							$("#footer_flecha").addClass("ocultar_gris");
						}

					});

		});