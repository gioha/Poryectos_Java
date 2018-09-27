package mx.ine.acuerdos.as;

import java.util.List;

import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOResponsablesVista;
import mx.ine.acuerdos.dto.helper.form.HLPFormCatalogoUsuarios;

public interface ASCatalogoUsuariosInterface {

	public List<DTOCAreas> obtenElementosCatalogoAreas(int tipoArea) throws Exception;
	
	public List<DTOResponsables> obtenListadoUsuariosLdap(HLPFormCatalogoUsuarios formCatalogoUsuarios) throws Exception;
	
	public void guardaResponsable(DTOResponsables responsable) throws Exception;

	public String obtenCoincidenciaArea(String nomNivelArea) throws Exception;

//	public boolean existeResponsable(DTOResponsables responsable) throws Exception;

	public List<DTOResponsablesVista> obtenResponsables(HLPFormCatalogoUsuarios formCatalogoUsuarios) throws Exception;

	public void actualizaResponsable(DTOResponsables responsable) throws Exception;

	public DTOResponsables buscaUsuarioPorCuentaLdap(String usuario) throws Exception;

	public void cambiaEstatusResponsable(DTOResponsables responsable) throws Exception;

	public DTOCAreas buscaAreaPorId(Integer idArea) throws Exception;

	public List<DTOCAreas> obtenElementosCatalogoAreasResponsablesLigados(int tipoArea) throws Exception;
	
}
