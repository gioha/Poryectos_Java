/**
 * 
 */
package mx.ine.computosINE.bo.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import mx.ine.computosINE.bo.BOCalculosComputosInterface;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <code>BOCalculosComputosImpl.java</code>Descripcion de la clase
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 06/05/2017 13:27:00
 */
@Component("boCalculos")
@Scope ("prototype")
public class BOCalculosComputosImpl implements BOCalculosComputosInterface, Serializable {

	private static final long serialVersionUID = -5011569114532311796L;
	private static final Log log = LogFactory.getLog(BOCalculosComputosImpl.class);

	@Override
	public Object[] getCalculosActas(BigDecimal capturadas,	BigDecimal universoTotCasillas) throws Exception {
		// TODO Auto-generated method stub

		log.info("BOCalculosComputosImpl.getCalculosActas");
		// TODO Objeto que almacena los calculos del acta
		Object [] calculos = new Object[3];

		// TODO Avance global de actas capturadas
		BigDecimal avanGlobalActasCapt = new BigDecimal(0);
		// TODO Actas capturadas
		BigDecimal actasCapturadas = new BigDecimal(0);
		// TODO Actas pendientes por capturar
		BigDecimal actasPendXcapturadas = universoTotCasillas;

		if ( capturadas.compareTo(BigDecimal.ZERO) != 0 ) {
			if ( universoTotCasillas.intValue() >=  capturadas.intValue() ) {
				avanGlobalActasCapt  = capturadas.multiply(new BigDecimal(100)).divide(universoTotCasillas, RoundingMode.HALF_DOWN);
				actasCapturadas      = capturadas;
				actasPendXcapturadas = universoTotCasillas.subtract(actasCapturadas);
			}
		}
		calculos[0] = avanGlobalActasCapt;
		calculos[1] = actasCapturadas;
		calculos[2] = actasPendXcapturadas;
		return calculos;
	}

	
	
}
