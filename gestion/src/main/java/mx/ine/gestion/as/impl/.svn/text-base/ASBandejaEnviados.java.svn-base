/**
 * @(#)ASBandejaEnviados.java 14/02/2018
 *
 * Copyright (C) 2017 Instituto Nacional Electoral (INE).
 *
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.List;

import mx.ine.gestion.as.inter.ASBandejaEnviadosInterface;
import mx.ine.gestion.dao.inter.DAOEnviadosDocumentosInterface;
import mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity;
import mx.ine.gestion.dto.helper.DTOEnviadosDocumentosHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Homero Fidel Villanueva
 *
 */
@Component("asBandejaEnviados")
@Scope("prototype")
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class ASBandejaEnviados implements ASBandejaEnviadosInterface{

	/**
	 * Interface que sirve para realizar diferentes operaciones con la tabla
	 * "FIRMA_DOCUMENTOS"
	 */
	@Autowired
	@Qualifier("daoEnviadosDocumentos")
	private DAOEnviadosDocumentosInterface daoEnviadosDocumentosInterface;
	
	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEnviadosInterface#consultarEnviados(mx.ine.gestion.dto.helper.DTOEnviadosDocumentosHelper)
	 */
	@Override
	public List<DTOEnviadosDocumentosEntity> consultarEnviados(DTOEnviadosDocumentosHelper helperEnviados) throws Exception{
		helperEnviados.setEstatusDocumento('A');
		helperEnviados.setEstatusEnviado('A');
		return daoEnviadosDocumentosInterface.consultarEnviados(helperEnviados);
	}
	
	/* (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASBandejaEnviadosInterface#eliminarEnviados(mx.ine.gestion.dto.db.DTOEnviadosDocumentosEntity)
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = { Exception.class })
	public void eliminarEnviados(DTOEnviadosDocumentosEntity enviado) throws Exception{
		enviado.setEstatus('E');
		daoEnviadosDocumentosInterface.modificar(enviado);
	}
}