package mx.ine.acuerdos.as.impl;

import java.util.List;

import mx.ine.acuerdos.as.ASModificarAcuerdosInterface;
import mx.ine.acuerdos.dao.DAOAcuerdosInterface;
import mx.ine.acuerdos.dao.DAOCTiposDocumentosInterface;
import mx.ine.acuerdos.dao.DAOPuntosInterface;
import mx.ine.acuerdos.dao.DAOResponsablesInterface;
import mx.ine.acuerdos.dao.DAOSeguimientosCGInterface;
import mx.ine.acuerdos.dao.DAOTipoSesionesInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
import mx.ine.acuerdos.dto.DTOTipoSesiones;
import mx.ine.acuerdos.util.Constantes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



/**
 * <code>ASRegistroAcuerdos.java</code>Descripcion de la clase
 *
 * @version 1.0
 * @since 12/10/2017 
 */


@Scope("prototype")
@Service("asModificarAcuerdos")
public class ASModificarAcuerdos implements ASModificarAcuerdosInterface {
	
	private static final Log log = LogFactory.getLog(ASModificarAcuerdos.class);

	
	
	@Autowired
	@Qualifier("daoTiposSesiones")
	private transient DAOTipoSesionesInterface daoModAcuerdos;

	@Autowired
	@Qualifier("daoAcuerdos")
	private transient DAOAcuerdosInterface daoModAcu;
	
	
	@Autowired 
	@Qualifier ("daoCTiposDocumentos")
	private transient DAOCTiposDocumentosInterface daoRegAcuerdosCTDocs;
	
	@Autowired
	@Qualifier("daoPuntos")
	private transient DAOPuntosInterface daoPuntos;
	
	@Autowired
	@Qualifier("daoResponsables")
	private transient DAOResponsablesInterface daoResponsables;
	
	@Autowired
	@Qualifier("daoSeguimientosCG")
	private transient DAOSeguimientosCGInterface daoSeguimientos;
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOTipoSesiones> recuperaTiposSesiones() throws Exception {
		
		return daoModAcuerdos.consultaTipoSesiones();
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public String recuperaTipoDocumentoS(Integer idDoc)  {
		return daoRegAcuerdosCTDocs.recuperaTipoDocumentoS(idDoc);
	}
	

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public String actualizarAcuerdo(DTOAcuerdos acuerdo)  {
	
		try{
		
			return(daoModAcu.guardaroActualizarAcuerdo(acuerdo));
		
		} catch (Exception e) {
		
			log.error( " ASModificarAcuerdos - actualizarAcuerdo() - Ocurrio un error: " + e.getMessage()  );
			return Constantes.NO_REGISTRO_RG;
		}
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo)   {
		try {
			return daoModAcu.obtenerAcuerdo(idAcuerdo);
		} catch (Exception e) {
			
			log.error( " ASModificarAcuerdos - obtenerAcuerdo() - Ocurrio un error: " + e.getMessage()  );
			
			return null;
		}
	}	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOPuntosAcuerdo> recuperaPuntosAcuerdos(String idNumAcuerdo) throws Exception {
		
		return daoPuntos.consultaPuntosAcuerdo(idNumAcuerdo);
				
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOResponsables> obtenerResponsable(Integer idArea) throws Exception{
		return daoResponsables.obtenerResponsable(idArea);
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOSeguimientosCG> obtenerResponsablesDelPunto(String idAcuerdo, Integer numeralia) throws Exception{
		return daoSeguimientos.obtenerResponsablesDelPunto(idAcuerdo,numeralia);
	}
	

		
	
	
}
