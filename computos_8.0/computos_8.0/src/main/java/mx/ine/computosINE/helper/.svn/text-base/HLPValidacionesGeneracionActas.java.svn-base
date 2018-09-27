package mx.ine.computosINE.helper;

import java.io.Serializable;
import java.util.List;

import mx.ine.computosINE.bsd.BSDDistribucionVotosInterface;
import mx.ine.computosINE.bsd.BSDGeneracionActasInterface;
import mx.ine.computosINE.dto.DTOActaTipoCandidaturaPK;
import mx.ine.computosINE.dto.DTODistribucionCandParcial;
import mx.ine.computosINE.dto.DTODistribucionCandParcialPK;
import mx.ine.computosINE.dto.DTODistribucionCandidatos;
import mx.ine.computosINE.dto.DTODistribucionCandidatosPK;
import mx.ine.computosINE.dto.DTODistribucionPartidosCI;
import mx.ine.computosINE.dto.DTODistribucionPartidosCIPK;
import mx.ine.computosINE.dto.DTOUsuarioLogin;
import mx.ine.computosINE.dto.helper.HLPValidacionesDatosActa;
import mx.ine.computosINE.util.Constantes;
import mx.ine.computosINE.util.Utilidades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * Clase Helper para realizar las diferentes validaciones
 * según el tipo de acta que se vaya a generar.
 * @author Alejandra Gómez Ruiz
 *
 */
@Component("hplValidacionesGeneraActa")
@Scope ("prototype")
public class HLPValidacionesGeneracionActas implements Serializable{

	/**
	 * Identificador serializable de la clase
	 */
	private static final long serialVersionUID = -3117722692928722289L;
	
	
	
	/*
	 * BSD de distribución de votos
	 */
	@Autowired
	@Qualifier("bsdDistribucionVotos")
	public transient BSDDistribucionVotosInterface bsd;
	
	
	/*
	 * helper de validaciones para la generación de las actas
	 */
	@Autowired
	@Qualifier("hplObjectValidaciones")
	public transient HLPValidacionesDatosActa hlp;
	
	

	
	/**
	 * Realiza las validaciones de reglas de negocio segun el tipo de candidatura
	 * y el tipo de usuario que este ingresando
	 * @param idTipoCandidatura - el tipo de candidatura seleccionado
	 * @param usuario - el usuario que ingreso
	 * @return boolean
	 * @throws Exception
	 */
	
	public HLPValidacionesDatosActa validaReglasByActa(DTOUsuarioLogin usuario, DTOActaTipoCandidaturaPK pkActa) throws Exception {
	 
		/*Definición de los dto´s de llave primaria para consultar los diferentes tipos de distribución*/
		DTODistribucionCandParcialPK pkParcial = new DTODistribucionCandParcialPK();
		DTODistribucionCandidatosPK pkFinal = new DTODistribucionCandidatosPK();
		DTODistribucionPartidosCIPK pkPPCI =  new DTODistribucionPartidosCIPK();
		
		/* Definición  de los objetos definidos en el helper que retorna el metodo Validar Reglas by Acta*/
		List<DTODistribucionCandParcial>   listDistParcial       = null;
		List<DTODistribucionCandidatos>    listDistFinal         = null;
		List<DTODistribucionPartidosCI>    listPPCI              = null;
		boolean captura100 = false;
		
		
		/*Se setea el DTO de DTODistribucionCandParcialPK, tomando los datos de DTOActaTipoCandidaturaPK*/
		pkParcial.setIdProcesoElectoral(pkActa.getIdProcesoElectoral());
		pkParcial.setIdDetalleProceso(pkActa.getIdDetalleProceso());
		pkParcial.setIdEstado(pkActa.getIdEstado());
		pkParcial.setIdDistrito(pkActa.getIdDistrito());
		pkParcial.setIdMunicipio(pkActa.getIdMunicipio());
		pkParcial.setIdRegiduria(pkActa.getIdRegiduria());
		pkParcial.setIdComunidad(pkActa.getIdComunidad());
		pkParcial.setIdTipoCandidatura(pkActa.getIdTipoCandidatura());
		
		/*Se setea el DTO de DTODistribucionCandidatosPK, tomando los datos de DTOActaTipoCandidaturaPK*/
		pkFinal.setIdProcesoElectoral(pkActa.getIdProcesoElectoral());
		pkFinal.setIdDetalleProceso(pkActa.getIdDetalleProceso());
		pkFinal.setIdEstado(pkActa.getIdEstado());
		pkFinal.setIdDistrito(pkActa.getIdDistrito());
		pkFinal.setIdMunicipio(pkActa.getIdMunicipio());
		pkFinal.setIdRegiduria(pkActa.getIdRegiduria());
		pkFinal.setIdComunidad(pkActa.getIdComunidad());
		pkFinal.setIdTipoCandidatura(pkActa.getIdTipoCandidatura());
		
		/*Se setea el DTO de DTODistribucionPartidosCIPK, tomando los datos de DTOActaTipoCandidaturaPK*/
		pkPPCI.setIdProcesoElectoral(pkActa.getIdProcesoElectoral());
		pkPPCI.setIdDetalleProceso(pkActa.getIdDetalleProceso());
		pkPPCI.setIdEstado(pkActa.getIdEstado());
		pkPPCI.setIdDistrito(pkActa.getIdDistrito());
		pkPPCI.setIdMunicipio(pkActa.getIdMunicipio());
		pkPPCI.setIdRegiduria(pkActa.getIdRegiduria());
		pkPPCI.setIdComunidad(pkActa.getIdComunidad());
		pkPPCI.setIdTipoCandidatura(pkActa.getIdTipoCandidatura());
		
		
		switch(usuario.getRolUsuario()){
		case Constantes.CAPTURA_MUNICIPAL:
			/*Regiduria Mayoria Relativa == 15, Regiduria RP == 16, Ayuntamiento == 9 */
			switch(pkActa.getIdTipoCandidatura()){
			    case 15:
			    case 16:
			    case 9:
			    	/*regla de negocio: Verificar que la distribución ya este hecha*/
			    	listDistFinal = bsd.consultarDistribucionFinal(pkFinal);
					listPPCI = bsd.consultarDistribucionPartidosCI(pkFinal);
					listDistParcial = null;
					captura100 = false;
					hlp.setListDistribucionFinal(listDistFinal);
					hlp.setListDistribucionParcial(listDistParcial);
					hlp.setListDistribucionPPCI(listPPCI);
					hlp.setCaptura100de100(captura100);
			    break;
			    /*Diputados MR == 7 Gobernador == 6*/
			    case 7:
			    case 6:
			    	/*regla de negocio: Verificar que la captura al 100 por ciento ya este completa*/
			    	listDistFinal =  null;
					listPPCI = null;
					listDistParcial = null;
					captura100 = true;
					hlp.setListDistribucionFinal(listDistFinal);
					hlp.setListDistribucionParcial(listDistParcial);
					hlp.setListDistribucionPPCI(listPPCI);
					hlp.setCaptura100de100(captura100);
			    	break;
			
			}
			
		case Constantes.CAPTURA_OPLE:
			/*Diputados MR == 7 Diputador RP == 8 Gobernador == 6*/
				switch(pkActa.getIdTipoCandidatura()){
					case 8:
					case 7:
					case 6:
						/*regla de negocio: Verificar que la distribución parcial y distribución final ya este hecha*/
						listDistFinal = bsd.consultarDistribucionFinal(pkFinal);
						listPPCI = bsd.consultarDistribucionPartidosCI(pkFinal);
						listDistParcial = bsd.consultarDistribucionParcial(pkFinal);
						captura100 = false;
						hlp.setListDistribucionFinal(listDistFinal);
						hlp.setListDistribucionParcial(listDistParcial);
						hlp.setListDistribucionPPCI(listPPCI);
						hlp.setCaptura100de100(captura100);
						break;
						/*Regiduria Mayoria Relativa == 15 Regiduria RP == 16 Ayuntamiento == 9 */
					case 15:
				    case 16:
				    case 9:
				    	/*regla de negocio: Verificar que la distribución final a nivel de capturista municipal ya este hecha*/
				    	listDistFinal = bsd.consultarDistribucionFinal(pkFinal);
						listPPCI = bsd.consultarDistribucionPartidosCI(pkFinal);
						listDistParcial = null;
						captura100 = false;
						hlp.setListDistribucionFinal(listDistFinal);
						hlp.setListDistribucionParcial(listDistParcial);
						hlp.setListDistribucionPPCI(listPPCI);
						hlp.setCaptura100de100(captura100);
				    
				    break;
				}
			
			break;
		
		}
	
		return hlp;
	}
	

}
