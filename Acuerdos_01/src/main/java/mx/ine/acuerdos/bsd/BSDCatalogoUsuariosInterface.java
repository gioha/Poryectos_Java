package mx.ine.acuerdos.bsd;

import java.util.List;

import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOResponsablesVista;
import mx.ine.acuerdos.dto.DTOUsuarioLogin;
import mx.ine.acuerdos.dto.helper.form.HLPFormCatalogoUsuarios;

public interface BSDCatalogoUsuariosInterface {

	public List<DTOCAreas> obtenListadoCatalogoAreas(int tipoArea);
	
	public List<DTOResponsables> obtenListadoResponsablesLdap(HLPFormCatalogoUsuarios formCatalogoUsuarios);
	
	public List<DTOResponsablesVista> obtenListadoResponsablesBd(HLPFormCatalogoUsuarios formCatalogoUsuarios);
	
	public boolean guardaResponsable(DTOResponsables responsable);

	public void realizaVerificacion(HLPFormCatalogoUsuarios formCatalogo);

	public boolean actualizaResponsable(DTOResponsables responsable);

	public DTOResponsablesVista buscaAreaUsuario(String usuarioLdap);

	public boolean validaTitularArea(DTOUsuarioLogin usuario);

	public void llenaFormDatosTitularArea(HLPFormCatalogoUsuarios formCatalogoUsuarios, String usuarioLdap);

	public boolean eliminaResponsable(DTOResponsables responsable);

	public List<String> recuperaImgsInfografias();

	public List<DTOCAreas> obtenListadoCatalogoAreasResponsablesLigados(int tipoArea);
}
