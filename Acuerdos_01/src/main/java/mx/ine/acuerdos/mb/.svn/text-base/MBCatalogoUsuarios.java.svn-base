package mx.ine.acuerdos.mb;

import java.io.Serializable;

import mx.ine.acuerdos.bsd.BSDCatalogoUsuariosInterface;
import mx.ine.acuerdos.dto.DTOResponsablesVista;
import mx.ine.acuerdos.dto.helper.form.HLPFormCatalogoUsuarios;
import mx.ine.acuerdos.util.Constantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

//@Controller("mbCatalogoUsuarios")
public class MBCatalogoUsuarios extends MBGeneric implements Serializable {

	private static final long serialVersionUID = -3138030948670901252L;
	private HLPFormCatalogoUsuarios formCatalogoUsuarios;
	private Integer areaUsuarioSesion;
	private String nombreAreaUsuarioSesion;
	private boolean esTitularAreaSinRegistro;
	
	@Autowired
	@Qualifier("bsdCatalogoUsuarios")
	private transient BSDCatalogoUsuariosInterface bsdCatalogoUsuarios;
	
	@Autowired
	@Qualifier("mbAdmin")
	private transient MBAdministradorSistema mbAdmin;
	
	public void init() {
		formCatalogoUsuarios = new HLPFormCatalogoUsuarios();
		formCatalogoUsuarios.setAreas(bsdCatalogoUsuarios.obtenListadoCatalogoAreas(1));
		formCatalogoUsuarios.setAreasResponsablesLigados(bsdCatalogoUsuarios.obtenListadoCatalogoAreasResponsablesLigados(1));
		esTitularAreaSinRegistro = titularAreaSinRegistro();
		if (esTitularAreaSinRegistro) {
			bsdCatalogoUsuarios.llenaFormDatosTitularArea(formCatalogoUsuarios, obtenUsuario().getUsuario());
		} else {
			if (esRolLimitado()) {
				DTOResponsablesVista responsableVista = bsdCatalogoUsuarios.buscaAreaUsuario(obtenUsuario().getUsuario());
				areaUsuarioSesion = responsableVista.getResponsable().getIdArea();
				nombreAreaUsuarioSesion = responsableVista.getArea().getDescripcion();
				formCatalogoUsuarios.setIdAreaBusqueda(areaUsuarioSesion);
			} else {
				areaUsuarioSesion = null;
				nombreAreaUsuarioSesion = null;
			}
		}
		
		mbAdmin.setImgsInfografias(bsdCatalogoUsuarios.recuperaImgsInfografias());
	}
	
	public void initConsulta() {
		formCatalogoUsuarios = new HLPFormCatalogoUsuarios();
		formCatalogoUsuarios.setAreasResponsablesLigados(bsdCatalogoUsuarios.obtenListadoCatalogoAreasResponsablesLigados(1));
		esTitularAreaSinRegistro = false;
		areaUsuarioSesion = null;
		nombreAreaUsuarioSesion = null;
		mbAdmin.setImgsInfografias(bsdCatalogoUsuarios.recuperaImgsInfografias());
	}
	
	public void buscaLdap() {
		formCatalogoUsuarios.setResponsables(bsdCatalogoUsuarios.obtenListadoResponsablesLdap(formCatalogoUsuarios));
		formCatalogoUsuarios.setResponsable(null);
	}
	
	public void verificaDatosConBD() {
		bsdCatalogoUsuarios.realizaVerificacion(formCatalogoUsuarios);
	}
	
	public void guardaResponsable() {
		if (esRolLimitado() && !esTitularAreaSinRegistro && !areaUsuarioSesion.equals(formCatalogoUsuarios.getResponsable().getIdArea())) {
			agregaMensaje(TipoMensaje.ERROR_MENSAJE.getTipo(), "", "No puedes registrar un usuario perteneciente a otra área.");
		} else {
			if (bsdCatalogoUsuarios.guardaResponsable(formCatalogoUsuarios.getResponsable())) {
				if (esRolLimitado() && esTitularAreaSinRegistro) {
					areaUsuarioSesion = formCatalogoUsuarios.getResponsable().getIdArea();
				}
				
				formCatalogoUsuarios.limpiaFormulario();
				esTitularAreaSinRegistro = titularAreaSinRegistro();
				agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "", "Los datos se han guardado correctamente.");
			} else {
				agregaMensaje(TipoMensaje.ERROR_MENSAJE.getTipo(), "", "Ocurrió un error, revise los datos ingresados.");
			}
		}
	}
	
	public void buscaResponsable() {
		formCatalogoUsuarios.setResponsablesVista(bsdCatalogoUsuarios.obtenListadoResponsablesBd(formCatalogoUsuarios));
		formCatalogoUsuarios.setResponsableVista(null);
	}
	
	public void actualizaResponsable() {
		if (bsdCatalogoUsuarios.actualizaResponsable(formCatalogoUsuarios.getResponsableVista().getResponsable())) {
			formCatalogoUsuarios.limpiaFormulario();
			formCatalogoUsuarios.setIdAreaBusqueda(areaUsuarioSesion);
			formCatalogoUsuarios.setAreasResponsablesLigados(bsdCatalogoUsuarios.obtenListadoCatalogoAreasResponsablesLigados(1));
			agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "", "Los datos se han actualizado correctamente.");
		} else {
			agregaMensaje(TipoMensaje.ERROR_MENSAJE.getTipo(), "", "Ocurrió un error, revise los datos ingresados.");
		}
	}
	
	public void eliminaResponsable() {
		if (bsdCatalogoUsuarios.eliminaResponsable(formCatalogoUsuarios.getResponsableVista().getResponsable())) {
			formCatalogoUsuarios.limpiaFormulario();
			formCatalogoUsuarios.setResponsableVista(null);
			formCatalogoUsuarios.setIdAreaBusqueda(areaUsuarioSesion);	
			agregaMensaje(TipoMensaje.INFO_MENSAJE.getTipo(), "", "El reponsable se ha eliminado correctamente.");
		} else {
			agregaMensaje(TipoMensaje.ERROR_MENSAJE.getTipo(), "", "Ocurrió un error al eliminar al responsable.");
		}
	}
	
	public boolean permiteEliminarUsuario() {
		return !(esRolLimitado() &&
				obtenUsuario().getUsuario().equals(formCatalogoUsuarios.getResponsableVista().getResponsable().getCuentaLdap()));
	}
	
	public boolean esRolPermitido() {
		String rolUsuario = this.obtenUsuario().getRolUsuario();
		return (
			rolUsuario.equalsIgnoreCase( Constantes.CAPTURA_ADMIN_OC ) ||
			rolUsuario.equalsIgnoreCase( Constantes.CONSULTA_ADMIN_OC ) ||
			rolUsuario.equalsIgnoreCase( Constantes.CAPTURA_SE_OC ) ||
			rolUsuario.equalsIgnoreCase( Constantes.CONSULTA_SE_OC ) || 		
			rolUsuario.equalsIgnoreCase( Constantes.CAPTURA_TITULAR_AREA_OC ) || 		
			rolUsuario.equalsIgnoreCase( Constantes.CONSULTA_TITULAR_AREA_OC ) || 		
			rolUsuario.equalsIgnoreCase( Constantes.CAPTURA_AREA_OC ) || 		
			rolUsuario.equalsIgnoreCase( Constantes.CONSULTA_AREA_OC )
	   );
	}
	
	public boolean esRolSoloConsulta() {
		String rolUsuario = this.obtenUsuario().getRolUsuario();
		return !(
			rolUsuario.equalsIgnoreCase( Constantes.CAPTURA_ADMIN_OC ) ||
			rolUsuario.equalsIgnoreCase( Constantes.CAPTURA_SE_OC )
//			|| rolUsuario.equalsIgnoreCase( Constantes.CAPTURA_TITULAR_AREA_OC )
	   );
	}
	
	public boolean esRolLimitado() {
		String rolUsuario = this.obtenUsuario().getRolUsuario();
		return (
			rolUsuario.equalsIgnoreCase( Constantes.CAPTURA_AREA_OC ) ||
			rolUsuario.equalsIgnoreCase( Constantes.CONSULTA_AREA_OC ) ||
			rolUsuario.equalsIgnoreCase( Constantes.CONSULTA_TITULAR_AREA_OC ) ||
			rolUsuario.equalsIgnoreCase( Constantes.CAPTURA_TITULAR_AREA_OC )
		);
	}
	
	public boolean titularAreaSinRegistro() {
		return bsdCatalogoUsuarios.validaTitularArea(obtenUsuario());
	}

	public HLPFormCatalogoUsuarios getFormCatalogoUsuarios() {
		return formCatalogoUsuarios;
	}

	public void setFormCatalogoUsuarios(HLPFormCatalogoUsuarios formCatalogoUsuarios) {
		this.formCatalogoUsuarios = formCatalogoUsuarios;
	}

	public Integer getAreaUsuarioSesion() {
		return areaUsuarioSesion;
	}

	public void setAreaUsuarioSesion(Integer areaUsuarioSesion) {
		this.areaUsuarioSesion = areaUsuarioSesion;
	}

	public boolean isEsTitularAreaSinRegistro() {
		return esTitularAreaSinRegistro;
	}

	public void setEsTitularAreaSinRegistro(boolean esTitularAreaSinRegistro) {
		this.esTitularAreaSinRegistro = esTitularAreaSinRegistro;
	}

	public String getNombreAreaUsuarioSesion() {
		return nombreAreaUsuarioSesion;
	}

	public void setNombreAreaUsuarioSesion(String nombreAreaUsuarioSesion) {
		this.nombreAreaUsuarioSesion = nombreAreaUsuarioSesion;
	}

}
