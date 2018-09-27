package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.ine.acuerdos.as.ASCatalogoUsuariosInterface;
import mx.ine.acuerdos.bsd.BSDCatalogoUsuariosInterface;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOResponsablesVista;
import mx.ine.acuerdos.dto.DTOUsuarioLogin;
import mx.ine.acuerdos.dto.helper.form.HLPFormCatalogoUsuarios;
import mx.ine.acuerdos.util.Constantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bsdCatalogoUsuarios")
@Scope("prototype")
public class BSDCatalogoUsuarios implements BSDCatalogoUsuariosInterface, Serializable {

	private static final long serialVersionUID = -8063888049192081366L;
	
	@Autowired
	@Qualifier("asCatalogoUsuarios")
	private transient ASCatalogoUsuariosInterface asCatalogoUsuarios;

	@Override
	public List<DTOCAreas> obtenListadoCatalogoAreas(int tipoArea) {
		try {
			return asCatalogoUsuarios.obtenElementosCatalogoAreas(tipoArea);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<DTOCAreas> obtenListadoCatalogoAreasResponsablesLigados(int tipoArea) {
		try {
			return asCatalogoUsuarios.obtenElementosCatalogoAreasResponsablesLigados(tipoArea);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<DTOResponsables> obtenListadoResponsablesLdap(HLPFormCatalogoUsuarios formCatalogoUsuarios) {
		try {
			return asCatalogoUsuarios.obtenListadoUsuariosLdap(formCatalogoUsuarios);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<DTOResponsablesVista> obtenListadoResponsablesBd(HLPFormCatalogoUsuarios formCatalogoUsuarios) {
		try {
			return asCatalogoUsuarios.obtenResponsables(formCatalogoUsuarios);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean guardaResponsable(DTOResponsables responsable) {
		try {
			asCatalogoUsuarios.guardaResponsable(responsable);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void realizaVerificacion(HLPFormCatalogoUsuarios formCatalogo) {
		try {
			//Comprobamos si el responsable seleccionado ya existe.
			DTOResponsables responsableEnBase = asCatalogoUsuarios.buscaUsuarioPorCuentaLdap(
					formCatalogo.getResponsable().getCuentaLdap()
			);
			
			if (responsableEnBase != null && responsableEnBase.getEstatus() == 1) {
				//Si existe se refleja en el formulario para dar conocimiento al usuario.
				formCatalogo.setExisteResponsableBD(true);
			} else {
				//Si no existe se procede a vincular el área que trae de LDAP con el catálogo.
				formCatalogo.setExisteResponsableBD(false);
				String idArea = asCatalogoUsuarios.obtenCoincidenciaArea(formCatalogo.getResponsable().getNomNivelArea());
				if (idArea != null && !idArea.isEmpty()) {
					formCatalogo.getResponsable().setIdArea(Integer.parseInt(idArea));
					formCatalogo.getResponsable().setTipoArea(1);
					formCatalogo.setCambiaArea(false);
				} else {
					formCatalogo.setCambiaArea(true);
					formCatalogo.getResponsable().setTipoArea(1);
				}
			}
			if (responsableEnBase != null && responsableEnBase.getEstatus() == 0) {
				formCatalogo.getResponsable().setIdResponsable(responsableEnBase.getIdResponsable());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean actualizaResponsable(DTOResponsables responsable) {
		try {
			asCatalogoUsuarios.actualizaResponsable(responsable);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public DTOResponsablesVista buscaAreaUsuario(String usuarioLdap) {
		try {
			DTOResponsables responsable = asCatalogoUsuarios.buscaUsuarioPorCuentaLdap(usuarioLdap);
			return new DTOResponsablesVista(
					responsable,
					asCatalogoUsuarios.buscaAreaPorId(responsable.getIdArea())
				);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean validaTitularArea(DTOUsuarioLogin usuario) {
		try {
			if (usuario.getRolUsuario().equalsIgnoreCase( Constantes.CAPTURA_TITULAR_AREA_OC )) {
				DTOResponsables responsable = asCatalogoUsuarios.buscaUsuarioPorCuentaLdap(usuario.getUsuario());
				if (responsable == null || responsable.getEstatus() == 0) {
					return true;
				}
			} 
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public void llenaFormDatosTitularArea(HLPFormCatalogoUsuarios formCatalogoUsuarios, String usuarioLdap) {
		formCatalogoUsuarios.setUsuarioBusqueda(usuarioLdap);
		try {
			List<DTOResponsables> responsables = asCatalogoUsuarios.obtenListadoUsuariosLdap(formCatalogoUsuarios);
			if (responsables != null && responsables.size() == 1) {
				formCatalogoUsuarios.setResponsable(responsables.get(0));
				realizaVerificacion(formCatalogoUsuarios);
				formCatalogoUsuarios.getResponsable().setTipoResponsable(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean eliminaResponsable(DTOResponsables responsable) {
		try {
			responsable.setEstatus(0);
			asCatalogoUsuarios.cambiaEstatusResponsable(responsable);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<String> recuperaImgsInfografias() {
		try{
			List<String> imgInf = new ArrayList<String>();
			imgInf.add("catalogos/Infografia_CatUsuarios-05-05.png");
			return  (List<String>) imgInf;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
