/**
 * @(#)BSDInterface.java 08/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.bsd.inter;

import java.util.List;

import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTODocumentoEntity;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOInstruccionesEntity;
import mx.ine.gestion.dto.helper.DTOTurnadoHelper;
import mx.ine.gestion.dto.helper.DTOTurnadoInstruHelper;
import mx.org.ine.servicios.dto.DTOBase;

/**
 * Interfaz que contiene la firma de los métodos que hacen las llamadas a los BSD´s que se utilizan en el módulo de Turnado
 * 
 * @author Pável Alexei Martínez Regalado
 * @since 08/01/2018
 * @update José Miguel Ortiz
 * @since 20/04/2018
 */
public interface BSDTurnadoInterface {
	
	/**
	 * Método que consulta en la bd las personas a las que puede turnar el usuario que está logueado al momento
	 * 
	 * @param personaActual: de tipo DTOEstructuraAreaEntity. Persona logueada al momento.
	 * 
	 * @return List<DTOEstructuraAreaEntity>: lista con las personas a las que se puede turnar.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/01/2018
	 */
	public List<DTOEstructuraAreasEntity> consultarPersonasTurnado(DTOEstructuraAreasEntity personaActual) throws Exception;

	/**
	 * Método que consulta en la bd las instrucciones de atención para el usuario logueado al momento
	 * 
	 * @param personaActual: de tipo DTOEstructuraAreaEntity. Persona logueada al momento.
	 * 
	 * @return List<DTOInstruccionesEntity>: lista con las instrucciones de atención del usuario.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/01/2018
	 */
	public List<DTOInstruccionesEntity> consultarInstruccionesAtencion(DTOEstructuraAreasEntity personaActual) throws Exception;
	
	/**
	 * Método que consulta en la bd las instrucciones informativas para el usuario logueado al momento
	 * 
	 * @param personaActual: de tipo DTOEstructuraAreaEntity. Persona logueada al momento.
	 * 
	 * @return List<DTOInstruccionesEntity>: lista con las instrucciones informativas del usuario.
	 * 
	 * @author Pável Alexei Martínez Regalado
	 * @since 09/01/2018
	 */
	public List<DTOInstruccionesEntity> consultarInstruccionesInformativas(DTOEstructuraAreasEntity personaActual) throws Exception;
	
//	public void turnarDesdeRecibidos(List<DTOTurnadoHelper> listaTurnado, DTOBandejaERecibidosEntity documentoBandejaRecibidos,
//			DTOEstructuraAreasEntity personaLogueada, String comentario) throws Exception;
	
	public void turnar(List<DTOTurnadoHelper> listaTurnado, DTOBase documentoBandeja, DTOEstructuraAreasEntity personaLogueada, String comentario) throws Exception;
	
	public void turnarHistorico(List<DTOTurnadoHelper> listaTurnado, DTOBase documentoBandeja, DTOEstructuraAreasEntity personaLogueada, String comentario, List<DTOTurnadoInstruHelper> instruccionesRealizadas) throws Exception;
	
	public List<DTOTurnadoInstruHelper> obtenerInstruccionesRealizadas(DTOEstructuraAreasEntity dtoEstructuraAreasEntity, DTODocumentoEntity documento) throws Exception;
	
	/**
	 * Método que obtiene el comentario que fue guardado cuando s e envio a Turnar un documento.
	 * 
	 * @param documento
	 * @param personaTurno
	 * @return
	 *
	 * @author Homero Fidel Villanueva
	 * @since 07/03/2018
	 */
	public String obtenerComentarioTurnado(DTODocumentoEntity documento, DTOEstructuraAreasEntity personaTurno);

	/**
	 * Método encargado de enviar el o los correos de notificación correspondientes al turnado de un documento.
	 * @author José Miguel Ortiz
	 * @param DTOEstructuraAreasEntity usuarioLogueado, List<DTOTurnadoHelper> listaTurnado, DTODocumentoEntity documentoTurnado, List<DTOInstruccionesEntity> listaAtencion, List<DTOInstruccionesEntity> listaInformativas
	 * @return void
	 * @since 20/04/2018
	 */
	public void enviarCorreoNotificacion(DTOUsuarioLogin usuarioLogueado, List<DTOTurnadoHelper> listaTurnado, DTODocumentoEntity documentoTurnado, List<DTOInstruccionesEntity> listaAtencion, List<DTOInstruccionesEntity> listaInformativas);

}
