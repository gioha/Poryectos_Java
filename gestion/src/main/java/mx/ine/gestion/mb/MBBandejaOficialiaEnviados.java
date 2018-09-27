/**
 * @(#)MBBandejaEnviados.java 02/11/2017
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import mx.ine.gestion.bo.inter.BOArchivoInteface;
import mx.ine.gestion.bsd.inter.BSDBandejaEnviadoInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaOficialiaInterface;
import mx.ine.gestion.bsd.inter.BSDBandejaBorradoresInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOBandejaEntradasOficialiaEntity;
import mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.util.Utilidades;

import org.jboss.logging.Logger;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Homero Fidel Villanueva
 *
 */
public class MBBandejaOficialiaEnviados implements Serializable {

	/**
	 * Objeto para la serialización de esta clase.
	 */
	private static final long serialVersionUID = -5396668565294332622L;
	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger log = Logger
			.getLogger(MBBandejaBorradores.class);

	private List<DTOEnviadosDocumentosEntity> listaEnviados;
	
	private List<DTOBandejaEntradasOficialiaEntity> listaBandejaOficialia;
	
	private DTOBandejaEntradasOficialiaEntity dtoBandejaOficialiaSeleccionados;

	private DTOEnviadosDocumentosEntity enviadoSeleccionado;

	private String columnaOrdenamiento;

	private boolean esOrdenamientoAscendente;

	private String rutaDocumento;

	private DTOEstructuraAreasEntity usuario;
	
	
	// ------------------------ MENSAJES ------------------------ //
	/**
	 * Atributo que guarda el mensaje que se mostrará cuando no se tenga ningún
	 * documento Enviado
	 */
	private String mensajeSinEnviados;

	/**
	 * Atributo que guarda el mensaje que se mostrará cuando se quiera eliminar
	 * un documento enviado
	 */
	private String mensajeConfimacionEliminarEnviado;

	/**
	 * Atributo que guarda el mensaje que se mostrará cuando el navegador del
	 * usuarion no sea capaz de mostrar el PDF.
	 */
	private String mensajeErrorNavegadorPDF;

	/**
	 * Atributo que guarda el mensaje para indicarle al usuario de donde puede
	 * desacargar el pdf que no se pudo visualizar.
	 */
	private String mensajeSolucionNavegadorPDF;
	
	/**
	 * Atributo que guarda el mensaje que aparecerá en la cabecera del dialog para eliminar un documento borrador
	 */
	private String mensajeTituloEliminarBorrador;
	
	/**
	 * Atributo que guarda el mensaje que se mostrará en el botón de Aceptar
	 */
	private String mensajeAceptar;
	
	/**
	 * Atributo que guarda el mensaje que se mostrará en el botón de Cancelar
	 */
	private String mensajeCancelar;
	

	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("bsdBorradorDocumentos")
	private transient BSDBandejaBorradoresInterface bsdEjercicioInterface;

	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("boArchivo")
	private transient BOArchivoInteface boArchivoInteface;
	
	@Autowired
	@Qualifier("bsdBandejaOficialia")
	private transient BSDBandejaOficialiaInterface bsdBandejaOficialiaInterface;
	
	/**
	 * Interface que sirve para realizar la comunicación con las capas
	 * inferiores.
	 */
	@Autowired
	@Qualifier("bsdBandejaEnviados")
	private transient BSDBandejaEnviadoInterface bsdBandejaEnviadoInterface; 


	// ------------------------ Métodos ------------------------ //
	public void iniciar() {
		
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());
		
		log.info(this.getClass().getName()
				+ " Inicia con éxito el método iniciar() ");
		setUsuario(SecurityContextHolder.getContext().getAuthentication()
				.getName());
		iniciarMensajes();
		Integer idArea = bsdBandejaOficialiaInterface.consultarAreasOficialias(usuarioLogueado.getIdOficialia()).get(0).getIdArea();
		this.listaBandejaOficialia = bsdBandejaOficialiaInterface.consultarBandeja(usuarioLogueado.getIdOficialia(),idArea);
		
		//listaEnviados = bsdEjercicioInterface.consultarEnviados("fecha", false,
				//usuario);
		if (listaBandejaOficialia != null && listaBandejaOficialia.size() > 0) {
			seleccionarEnviado(0);
			setRutaDocumento(dtoBandejaOficialiaSeleccionados);
		}
		log.info(this.getClass().getName()
				+ " Termina con éxito el método iniciar() ");
		
		//this.nombreArea = this.listaBandejaOficialia.get(0).getDtoDocumento().getArea().getDescripcion();
	}

	public void iniciarMensajes() {

		mensajeSinEnviados = Utilidades
				.mensajeProperties("mensaje_tabla_enviados_vacia");

		mensajeConfimacionEliminarEnviado = Utilidades
				.mensajeProperties("mensaje_confirmacion_eliminar_enviado");
		mensajeErrorNavegadorPDF = Utilidades
				.mensajeProperties("mensaje_error_navegador_pdf");

		mensajeSolucionNavegadorPDF = Utilidades
				.mensajeProperties("mensaje_solucion_navegador_pdf");
		
		mensajeTituloEliminarBorrador = Utilidades
				.mensajeProperties("mensaje_titulo_dialog_eliminar_enviado");
		
		mensajeAceptar = Utilidades
				.mensajeProperties("etiqueta_boton_aceptar");
		
		mensajeCancelar = Utilidades
				.mensajeProperties("etiqueta_boton_cancelar");

	}

	public void ordenarDocumentosPorNombre() {
//		columnaOrdenamiento = "nombre";
//		esOrdenamientoAscendente = !esOrdenamientoAscendente;
//		listaEnviados = bsdBandejaEnviadoInterface.consultarEnviados(columnaOrdenamiento, esOrdenamientoAscendente, usuario);
	}

	public void ordenarDocumentosPorFecha() {
//		columnaOrdenamiento = "fecha";
//		esOrdenamientoAscendente = !esOrdenamientoAscendente;
//		listaEnviados = bsdBandejaEnviadoInterface.consultarEnviados(columnaOrdenamiento, esOrdenamientoAscendente, usuario);
	}

	public void seleccionarEnviado(DTOBandejaEntradasOficialiaEntity dtoEnviado) {
		dtoBandejaOficialiaSeleccionados = dtoEnviado;
		setRutaDocumento(dtoBandejaOficialiaSeleccionados);
	}

	public void seleccionarEnviado(SelectEvent event) {

		seleccionarEnviado(((DTOBandejaEntradasOficialiaEntity) event.getObject()));
	}

	public void seleccionarEnviado(int i) {
		if (listaEnviados != null && listaEnviados.size() > i) {
			dtoBandejaOficialiaSeleccionados = listaBandejaOficialia.get(i);
			seleccionarEnviado(dtoBandejaOficialiaSeleccionados);
		}
	}

	// ------------------------ GETTERS & SETTERS ------------------------ //

	/**
	 * @return valor de tipo List<DTOEnviadosDocumentosEntity> que esta
	 *         contenido en la variable listaEnviados
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public List<DTOEnviadosDocumentosEntity> getListaEnviados() {
		return listaEnviados;
	}

	/**
	 * @param listaEnviados
	 *            : valor que se ingresa a la variable de tipo
	 *            List<DTOEnviadosDocumentosEntity>
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public void setListaEnviados(List<DTOEnviadosDocumentosEntity> listaEnviados) {
		this.listaEnviados = listaEnviados;
	}

	/**
	 * @return valor de tipo DTOEnviadosDocumentosEntity que esta contenido en
	 *         la variable enviadoSeleccionado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public DTOEnviadosDocumentosEntity getEnviadoSeleccionado() {
		return enviadoSeleccionado;
	}

	/**
	 * @param enviadoSeleccionado
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOEnviadosDocumentosEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public void setEnviadoSeleccionado(
			DTOEnviadosDocumentosEntity enviadoSeleccionado) {
		this.enviadoSeleccionado = enviadoSeleccionado;
	}

	/**
	 * @return valor de tipo boolean que esta contenido en la variable
	 *         esOrdenamientoAscendente
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public boolean isEsOrdenamientoAscendente() {
		return esOrdenamientoAscendente;
	}

	/**
	 * @param esOrdenamientoAscendente
	 *            : valor que se ingresa a la variable de tipo boolean
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public void setEsOrdenamientoAscendente(boolean esOrdenamientoAscendente) {
		this.esOrdenamientoAscendente = esOrdenamientoAscendente;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         columnaOrdenamiento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public String getColumnaOrdenamiento() {
		return columnaOrdenamiento;
	}

	/**
	 * @param columnaOrdenamiento
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 02/11/2017
	 */
	public void setColumnaOrdenamiento(String columnaOrdenamiento) {
		this.columnaOrdenamiento = columnaOrdenamiento;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         rutaDocumento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/11/2017
	 */
	public String getRutaDocumento() {
		return rutaDocumento;
	}

	/**
	 * @param rutaDocumento
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 03/11/2017
	 */
	public void setRutaDocumento(String rutaDocumento) {
		this.rutaDocumento = rutaDocumento;
	}

	public void setRutaDocumento(
			DTOBandejaEntradasOficialiaEntity dtoEnviadosDocumentosEntity) {
		if (dtoEnviadosDocumentosEntity != null) {
			this.rutaDocumento = boArchivoInteface.obtenerRutaGlusterPdfNube()
					+ File.separator
					+ dtoEnviadosDocumentosEntity.getDtoDocumento()
							.getNoDocumento() + ".pdf";
		} else {
			this.rutaDocumento = "";
		}
	}

	/**
	 * @return valor de tipo DTOEstructuraAreasEntity que esta contenido en la
	 *         variable usuario
	 *
	 * @author Homero Fidel Villanueva
	 * @since 10/11/2017
	 */
	public DTOEstructuraAreasEntity getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            : valor que se ingresa a la variable de tipo
	 *            DTOEstructuraAreasEntity
	 *
	 * @author Homero Fidel Villanueva
	 * @since 10/11/2017
	 */
	public void setUsuario(DTOEstructuraAreasEntity usuario) {
		this.usuario = usuario;
	}

	public void setUsuario(String usuario) {
		
		//this.usuario = bsdEjercicioInterface.consultarPersonaXCuenta(usuario);
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeSinEnviados
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeSinEnviados() {
		return mensajeSinEnviados;
	}

	/**
	 * @param mensajeSinEnviados
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeSinEnviados(String mensajeSinEnviados) {
		this.mensajeSinEnviados = mensajeSinEnviados;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeConfimacionEliminarEnviado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeConfimacionEliminarEnviado() {
		return mensajeConfimacionEliminarEnviado;
	}

	/**
	 * @param mensajeConfimacionEliminarEnviado
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeConfimacionEliminarEnviado(
			String mensajeConfimacionEliminarEnviado) {
		this.mensajeConfimacionEliminarEnviado = mensajeConfimacionEliminarEnviado;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeErrorNavegadorPDF
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeErrorNavegadorPDF() {
		return mensajeErrorNavegadorPDF;
	}

	/**
	 * @param mensajeErrorNavegadorPDF
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeErrorNavegadorPDF(String mensajeErrorNavegadorPDF) {
		this.mensajeErrorNavegadorPDF = mensajeErrorNavegadorPDF;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable
	 *         mensajeSolucionNavegadorPDF
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeSolucionNavegadorPDF() {
		return mensajeSolucionNavegadorPDF;
	}

	/**
	 * @param mensajeSolucionNavegadorPDF
	 *            : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeSolucionNavegadorPDF(
			String mensajeSolucionNavegadorPDF) {
		this.mensajeSolucionNavegadorPDF = mensajeSolucionNavegadorPDF;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable mensajeTituloEliminarBorrador
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeTituloEliminarBorrador() {
		return mensajeTituloEliminarBorrador;
	}

	/**
	 * @param mensajeTituloEliminarBorrador : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeTituloEliminarBorrador(
			String mensajeTituloEliminarBorrador) {
		this.mensajeTituloEliminarBorrador = mensajeTituloEliminarBorrador;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable mensajeAceptar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeAceptar() {
		return mensajeAceptar;
	}

	/**
	 * @param mensajeAceptar : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeAceptar(String mensajeAceptar) {
		this.mensajeAceptar = mensajeAceptar;
	}

	/**
	 * @return valor de tipo String que esta contenido en la variable mensajeCancelar
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public String getMensajeCancelar() {
		return mensajeCancelar;
	}

	/**
	 * @param mensajeCancelar : valor que se ingresa a la variable de tipo String
	 *
	 * @author Homero Fidel Villanueva
	 * @since 11/11/2017
	 */
	public void setMensajeCancelar(String mensajeCancelar) {
		this.mensajeCancelar = mensajeCancelar;
	}

	/**
	 * @return valor de tipo List<DTOBandejaEntradasOficialiaEntity> que esta contenido en la variable listaBandejaOficialia
	 * 
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public List<DTOBandejaEntradasOficialiaEntity> getListaBandejaOficialia() {
		return listaBandejaOficialia;
	}

	/**
	 * @param listaBandejaOficialia : valor que se ingresa a la variable de tipo List<DTOBandejaEntradasOficialiaEntity>
	 *
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public void setListaBandejaOficialia(
			List<DTOBandejaEntradasOficialiaEntity> listaBandejaOficialia) {
		this.listaBandejaOficialia = listaBandejaOficialia;
	}

	/**
	 * @return valor de tipo DTOBandejaEntradasOficialiaEntity que esta contenido en la variable dtoBandejaOficialiaSeleccionados
	 * 
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public DTOBandejaEntradasOficialiaEntity getDtoBandejaOficialiaSeleccionados() {
		return dtoBandejaOficialiaSeleccionados;
	}

	/**
	 * @param dtoBandejaOficialiaSeleccionados : valor que se ingresa a la variable de tipo DTOBandejaEntradasOficialiaEntity
	 *
	 * @author David Rodríguez Corral
	 * @since 21/11/2017
	 */
	public void setDtoBandejaOficialiaSeleccionados(
			DTOBandejaEntradasOficialiaEntity dtoBandejaOficialiaSeleccionados) {
		this.dtoBandejaOficialiaSeleccionados = dtoBandejaOficialiaSeleccionados;
	}

}
