/**
 * @(#)MBHistorialTurnado.java 04/05/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.mb;

import java.io.Serializable;

import mx.ine.gestion.bsd.inter.BSDBandejaEntradaInterface;
import mx.ine.gestion.bsd.inter.BSDHistorialInterface;
import mx.ine.gestion.dto.db.DTODocumentoEntity;

import org.jboss.logging.Logger;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Homero Fidel Villanueva
 * @since 04/05/2018
 *
 */
public class MBHistorialTurnado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4822645236339830062L;
	
	@Autowired
	@Qualifier("bsdHistorial")
	private transient BSDHistorialInterface bsdHistorialInterface;
	
	@Autowired
	@Qualifier("bsdBandejaEntrada")
	private transient BSDBandejaEntradaInterface bsdBandejaEntradaInterface;
	
	/**
	 * Objeto para el servicio de bitácora de mensajes de la aplicación.
	 */
	private static final Logger log = Logger.getLogger(MBBandejaBorradores.class);
	
	private Integer idDocumento;
	
	/**
	 * 
	 */
	private TreeNode listaHistoricoTurnado;
	
	public void iniciar(){
		idDocumento  = 0;
	}
	
	/**
	 * 
	 * @param documento
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/05/2018
	 */
	public void consultarHistorialTurnado(DTODocumentoEntity documento){
		
		if(documento != null && !idDocumento.equals(documento.getIdDocumento())){
			
			try {
				listaHistoricoTurnado = bsdBandejaEntradaInterface.obtenrHistoricoTurnado(documento);
				idDocumento = documento.getIdDocumento();
			} catch (Exception e) {
				log.error("<=================== Clase: "+this.getClass().getName()+" , Método: seleccionarFilaAtencion()");
				log.error("<=================== ocurrio un error al tratar de obtener la lista del historico de turnado del documento: " + documento);
				log.error("<=================== USUARIO LOGUEADO: " + SecurityContextHolder.getContext().getAuthentication().getName());
				log.error("", e);
			}
		}
	}

	/**
	 * @return valor de tipo TreeNode que esta contenido en la variable listaHistoricoTurnado
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/05/2018
	 */
	public TreeNode getListaHistoricoTurnado() {
		return listaHistoricoTurnado;
	}

	/**
	 * @param listaHistoricoTurnado : valor que se ingresa a la variable de tipo TreeNode
	 *
	 * @author Homero Fidel Villanueva
	 * @since 04/05/2018
	 */
	public void setListaHistoricoTurnado(TreeNode listaHistoricoTurnado) {
		this.listaHistoricoTurnado = listaHistoricoTurnado;
	}

}
