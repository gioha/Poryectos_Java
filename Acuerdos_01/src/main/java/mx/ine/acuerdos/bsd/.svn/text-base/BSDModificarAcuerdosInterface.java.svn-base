package mx.ine.acuerdos.bsd;

import java.io.Serializable;
import java.util.List;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOTipoSesiones;
import mx.ine.acuerdos.dto.helper.form.HLPFormModificarAcuerdos;
import mx.ine.acuerdos.dto.helper.form.HLPFormRegistroAcuerdos;


public interface BSDModificarAcuerdosInterface  extends Serializable {

	
//	/**
//     * Muestra u oculta panel de Engose
//     * @param form: elementos de la pantalla registro acuerdos
//     * @return regresa true: motrar o false: ocultar
//   */
//	public void mostrarOcultar(HLPFormModificarAcuerdos form);
	
	
	public void evaluaRequeridos();
	
	public List<DTOTipoSesiones> recuperaTiposSesiones();

	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo);

	public String actualizarAcuerdo(DTOAcuerdos dtoAcuerdo);

	public String extraerNomArchivo(String str);

	public List<String> recuperaImgsInfografias();

	public void hayEngrose(HLPFormModificarAcuerdos form);

	public void seleccionarConEngrose(HLPFormModificarAcuerdos form);

	public void mostrarTipoDocumento(HLPFormModificarAcuerdos form);

}
