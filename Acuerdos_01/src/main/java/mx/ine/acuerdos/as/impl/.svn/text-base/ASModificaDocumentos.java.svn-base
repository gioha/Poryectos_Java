package mx.ine.acuerdos.as.impl;

import java.util.List;

import mx.ine.acuerdos.as.ASModificaDocumentosInterface;
import mx.ine.acuerdos.dao.DAOCAreasInterface;
import mx.ine.acuerdos.dao.DAOCTiposDocumentosInterface;
import mx.ine.acuerdos.dao.DAODocumentosInterface;
import mx.ine.acuerdos.dao.DAOPeriodosInterface;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCTipoDocumento;
import mx.ine.acuerdos.dto.DTODocumentosPeriodos;
import mx.ine.acuerdos.dto.DTODocumentosSivople;
import mx.ine.acuerdos.dto.helper.form.HLPFormDocumentos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("asModDocumentos")
@Scope("prototype")
public class ASModificaDocumentos implements ASModificaDocumentosInterface {
	
	@Autowired
	@Qualifier("daoCTiposDocumentos")
	private transient DAOCTiposDocumentosInterface daoCTiposDocumentos;
	
	@Autowired
	@Qualifier("daoCAreas")
	private transient DAOCAreasInterface daoCAreas;
	
	@Autowired
	@Qualifier("daoDocumentos")
	private transient DAODocumentosInterface daoDocumentos;
	
	@Autowired
	@Qualifier("daoPeriodos")
	private transient DAOPeriodosInterface daoPeriodos;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOCTipoDocumento> obtenTiposDocumentos() throws Exception {
		return daoCTiposDocumentos.consultaTiposDocumentos();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOCAreas> obtenElementosCatalogoAreas(int tipoArea) throws Exception {
		return daoCAreas.consultaCatalogoAreas(tipoArea);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void actulizaDocumento(HLPFormDocumentos formDocumento) throws Exception {
		daoDocumentos.persisteDocumento(formDocumento.getDocumento());
		daoPeriodos.eliminaPeriodos(formDocumento.getDocumento().getId().getIdNumDocumento());
		daoPeriodos.persistePeriodos(formDocumento.getPeriodos(), formDocumento.getDocumento());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTODocumentosSivople encuentraDocumentoPorID(String idDocumento) throws Exception {
		return daoDocumentos.buscarPorId(idDocumento);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTODocumentosPeriodos> encuentraPeriodosDocumento(String idDocumento) throws Exception {
		return daoPeriodos.buscarTodos(idDocumento);
	}

}
