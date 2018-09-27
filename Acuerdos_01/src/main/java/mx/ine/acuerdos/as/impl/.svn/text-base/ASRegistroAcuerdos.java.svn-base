package mx.ine.acuerdos.as.impl;

import java.util.List;

import mx.ine.acuerdos.as.ASRegistroAcuerdosInterface;
import mx.ine.acuerdos.dao.DAOAcuerdosInterface;
import mx.ine.acuerdos.dao.DAOCTiposDocumentosInterface;
import mx.ine.acuerdos.dao.DAOTipoSesionesInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
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
 * @author Giovanni Hernandez Alonso
 * @version 1.0
 * @since 12/10/2017 
 */


@Scope("prototype")
@Service("asRegistroAcuerdos")
public class ASRegistroAcuerdos implements ASRegistroAcuerdosInterface {

	private static final Log log = LogFactory.getLog(ASRegistroAcuerdos.class);
	
	
	@Autowired
	@Qualifier("daoTiposSesiones")
	private transient DAOTipoSesionesInterface daoRegAcuerdos;

	@Autowired
	@Qualifier("daoAcuerdos")
	private transient DAOAcuerdosInterface daoRegAcu;
	

	@Autowired 
	@Qualifier ("daoCTiposDocumentos")
	private transient DAOCTiposDocumentosInterface daoRegAcuerdosCTDocs;
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOTipoSesiones> recuperaTiposSesiones() throws Exception {
		
		return daoRegAcuerdos.consultaTipoSesiones();
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOCTipoDocumento> recuperaTiposDocumentos()  {
		try{
		return daoRegAcuerdosCTDocs.consultaTiposDocumentos();
		} catch (Exception e) {
			log.error( " ASRegistroAcuerdos - recuperaTiposDocumentos() - Ocurrio un error: " + e.getMessage()  );
			return null;
		}
	}
	
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor={Exception.class})
	public String guardaAcuerdo(DTOAcuerdos dtoAcuerdos) 
	
	{
	
		try {
		
			return daoRegAcu.registrarAcuerdo(dtoAcuerdos);
	
		} catch (Exception e) {
			log.error( " ASRegistroAcuerdos - guardaAcuerdo() - Ocurrio un error: " + e.getMessage()  );
			return Constantes.NO_REGISTRO_RG;
			
		}
		
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo){
		try {
			return daoRegAcu.obtenerAcuerdo(idAcuerdo);
		} catch (Exception e) {
			log.error( " ASRegistroAcuerdos - obtenerAcuerdo() - Ocurrio un error: " + e.getMessage()  );
			return null;
		}
	}	
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void eliminarSeguimiento(String idAcuerdo){
		try {
			daoRegAcu.eliminarSeguimiento(idAcuerdo);
		} catch (Exception e) {
			log.error( " ASRegistroAcuerdos - eliminarSeguimiento() - Ocurrio un error: " + e.getMessage()  );
		}
	}	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void eliminarPuntosAc(String idAcuerdo){
		try {
			daoRegAcu.eliminarPuntosAc(idAcuerdo);
		} catch (Exception e) {
			log.error( " ASRegistroAcuerdos - eliminarPuntosAc() - Ocurrio un error: " + e.getMessage()  );
		}
	}	
	
	
	
	
	
	
	
}
