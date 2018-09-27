/*******************************************************************************
 * Función para agregar las animaciones a Jquery
 ******************************************************************************/
$.fn
		.extend({
			animateCss : function(animationName) {
				var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
				this.addClass('animated ' + animationName).one(animationEnd,
						function() {
							$(this).removeClass('animated ' + animationName);
						});
				return this;
			}
		});
