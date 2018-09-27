
package mx.ine.acuerdos.mb;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import mx.ine.acuerdos.bsd.BSDConvocatoriaInterface;
import mx.ine.acuerdos.dto.DTOComisiones;
import mx.ine.acuerdos.dto.DTOOrdenesDelDia;
import mx.ine.acuerdos.dto.helper.form.HelperConvocatoria;
import mx.ine.acuerdos.util.Constantes;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MBConvocatoria extends MBGeneric implements Serializable {
	private static final long serialVersionUID = 4318956517123257487L;

	@Autowired
	@Qualifier("bsdConvocatoria")
	private transient BSDConvocatoriaInterface bsdConvocatoria;
	
	@Autowired
	@Qualifier("mbAdmin")
	private transient MBAdministradorSistema mbAdmin;

	private HelperConvocatoria helpConvocatoria;
//	private DTOComisiones comision;
//	private List<DTOComisiones> comisionesFinal;

	public void init() {
		helpConvocatoria = new HelperConvocatoria();
//		comision = new DTOComisiones();
//		comisionesFinal = new ArrayList<DTOComisiones>();

		if(obtenUsuario().getRolUsuario().equalsIgnoreCase(Constantes.CAPTURA_ADMIN_OC) ||
		   obtenUsuario().getRolUsuario().equalsIgnoreCase(Constantes.CAPTURA_SE_OC)) {
			helpConvocatoria.setIdComision(0);		// ID_COMISION = 0 para el consejo general
		} else {
			// Búsqueda del responsable con el fin de conocer ID_RESPONSABLE
			helpConvocatoria.setDtoResponsable( bsdConvocatoria.recuperarDtoResponsable(obtenUsuario().getUsuario()) );
			// Búsqueda del integrante con el fin de conocer ID_COMISION
			helpConvocatoria.setIdComision( (bsdConvocatoria.recuperarDtoIntegComision(
											 helpConvocatoria.getDtoResponsable().getIdResponsable())).
											 getId().getIdComision() );
		}

		helpConvocatoria.setComision(bsdConvocatoria.recuperarComision( helpConvocatoria.getIdComision() ));
		helpConvocatoria.setComisionesUnidas( bsdConvocatoria.recuperarComisionesUnidas(helpConvocatoria.getIdComision()) );
		helpConvocatoria.setComisionesFinal( bsdConvocatoria.determinarComisionesFinal(helpConvocatoria) );
		helpConvocatoria.setIntegComision( bsdConvocatoria.determinarIntegComision(helpConvocatoria.getComisionesFinal()) );

//		// Búsqueda de la comisión
//		comision = bsdConvocatoria.recuperarComision( helpConvocatoria.getIdComision() );
//		// La comisión es agregada al helper y al listado de comisiones final
//		helpConvocatoria.setComision(comision);
//		comisionesFinal.add(comision);
//		helpConvocatoria.setComisionesFinal(comisionesFinal);
//		// Búsqueda de los integrantes de la comisión y creación del árbol de comisiones
//		helpConvocatoria.setIntegComision( bsdConvocatoria.recuperarIntegComision(helpConvocatoria.getIdComision()) );

		helpConvocatoria.setTipoIntegComision( bsdConvocatoria.recuperarTipoIntegComision() );
		// Búsqueda de las áreas involucradas en la comisión por medio de los responsables
		helpConvocatoria.setResponsComision( bsdConvocatoria.recuperarResponsComision(helpConvocatoria.getIntegComision()) );
		helpConvocatoria.setArbolComisiones( bsdConvocatoria.construirArbolComisiones(helpConvocatoria) );
		// Búsqueda del catálogo de tipos de sesiones
		helpConvocatoria.setTiposDeSesiones( bsdConvocatoria.recuperarTiposDeSesiones() );
		// Comprobación y asignación de campos para usuarios ajenos a una comisión
		bsdConvocatoria.comprobarCamposSegunRol( helpConvocatoria, obtenUsuario().getRolUsuario() );
		mbAdmin.setImgsInfografias(bsdConvocatoria.recuperaImgsInfografias());
	}

	public void precargarPDF(FileUploadEvent event) {
		bsdConvocatoria.precargarOrdenDelDia(helpConvocatoria, event.getFile());
	}

	public void agregarDocumentosAdjuntos(ActionEvent actionEvent) {
		// Búsqueda del consecutivo para el número de sesión
		helpConvocatoria.setNumSesionSig( bsdConvocatoria.determinarNumSesionSig(helpConvocatoria) );
		helpConvocatoria.setNumSesion(helpConvocatoria.getNumSesionSig());

		bsdConvocatoria.guardarOrdenDelDia(helpConvocatoria);
		bsdConvocatoria.guardarDocumentacion(helpConvocatoria);
	}

	public void precargarZIP(FileUploadEvent event) {
		bsdConvocatoria.precargarDocumentacion(helpConvocatoria, event.getFile());
	}

	public HelperConvocatoria getHelpConvocatoria() {
		return helpConvocatoria;
	}

	public boolean esRolValido() {
		return bsdConvocatoria.validarRolUsuario(obtenUsuario().getRolUsuario());
	}

	public boolean esCaptura(){
		return bsdConvocatoria.validarRolCapConsultaUsuario(obtenUsuario().getRolUsuario());
	}
	
	public boolean esMiembroDeComision() {
		return bsdConvocatoria.validarMiembroComision(obtenUsuario().getRolUsuario(), obtenUsuario().getUsuario());
	}

	public boolean esRolComision() {
		return bsdConvocatoria.validarRolComision(obtenUsuario().getRolUsuario());
	}

	public void validarNumSesion() {
		bsdConvocatoria.validarNumSesion(helpConvocatoria);
	}

	public void activarDesactivarCheck(boolean comisionConjunta) {
		if(comisionConjunta) {
			helpConvocatoria.setListaComisiones( bsdConvocatoria.recuperarComisionesFiltradas(
												 helpConvocatoria.getIdComision()) );
		}
		bsdConvocatoria.actDesactComConjunta(helpConvocatoria, comisionConjunta);
	}

	public void seleccionarComision(SelectEvent event) {
		bsdConvocatoria.agregarComisionIntegrantes(helpConvocatoria, (DTOComisiones) event.getObject());
		helpConvocatoria.setArbolComisiones( bsdConvocatoria.construirArbolComisiones(helpConvocatoria));
	}

	public void noSeleccionarComision(UnselectEvent event) {
		bsdConvocatoria.eliminarComisionIntegrantes(helpConvocatoria, (DTOComisiones) event.getObject());
		helpConvocatoria.setArbolComisiones( bsdConvocatoria.construirArbolComisiones(helpConvocatoria));
	}

	public void seleccionarFila(SelectEvent event) {
		bsdConvocatoria.agregarFilaComision(helpConvocatoria, (DTOComisiones) event.getObject());
		helpConvocatoria.setArbolComisiones( bsdConvocatoria.construirArbolComisiones(helpConvocatoria));
	}

	public void agregarPuntoConvoc(HelperConvocatoria helpConvocatoria) {
		bsdConvocatoria.insertarPuntoConvoc(helpConvocatoria);
	}

	public void eliminarPuntoConvoc(DTOOrdenesDelDia ordenModificable) {
		bsdConvocatoria.eliminarPuntoConvoc(helpConvocatoria, ordenModificable);
	}

	public void moverPuntoConvoc(ReorderEvent event) {
		bsdConvocatoria.moverPuntoConvoc(helpConvocatoria, event);
	}

	public String agregarConvocatoria() {
		String msgAgregarConvoc = "";

		bsdConvocatoria.insertarConvocatoria(helpConvocatoria);
//		bsdConvocatoria.insertarOrdenDelDia(helpConvocatoria);
		bsdConvocatoria.enviarCorreoInformativo(helpConvocatoria);
		msgAgregarConvoc = "btnAgregarConvocatoria";

		if(helpConvocatoria.getExitoInsercion()) {
			agregaMensaje(TipoMensaje.INFO_MENSAJE, "La Convocatoria y el Orden del Día han sido guardados exitosamente");
		}

		return msgAgregarConvoc;
	}

	public void limpiarDialog() {
		helpConvocatoria.setDescPunto("");
	}

}
