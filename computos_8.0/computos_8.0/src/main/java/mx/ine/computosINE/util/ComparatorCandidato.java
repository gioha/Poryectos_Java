package mx.ine.computosINE.util;

import java.util.Comparator;

import mx.ine.computosINE.dto.DTOCandidato;

/**
 * <code>ComparatorCandidato.java</code>Descripcion de la clase
 *
 * @author Clemencia Cuellar
 * @version 1.0
 * @since 29/04/2017 12:27:00
 */
public class ComparatorCandidato implements Comparator<DTOCandidato> {

	@Override
	public int compare(DTOCandidato c1, DTOCandidato c2) {

		int val = c1.getTipoAsociacionAux().compareTo(c2.getTipoAsociacionAux());

		if ( val < 0 ) {
			return -1;
		}

		if ( val == 0 ) {
			return c1.getOrdenAux().compareTo(c2.getOrdenAux());
		}

		if ( val > 0 ){
			return 1;
		}
		return 0;
	}
}
