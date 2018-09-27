package mx.ine.acuerdos.as.impl;

import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.as.ASConvocatoriaInterface;
import mx.ine.acuerdos.dao.DAOAnexosOrdenesDelDiaInterface;
import mx.ine.acuerdos.dao.DAOCTipoIntegComisionInterface;
import mx.ine.acuerdos.dao.DAOComisionesInterface;
import mx.ine.acuerdos.dao.DAOComisionesUnidasInterface;
import mx.ine.acuerdos.dao.DAOConformComisionesInterface;
import mx.ine.acuerdos.dao.DAOConvocatoriasInterface;
import mx.ine.acuerdos.dao.DAOIntegComisionInterface;
import mx.ine.acuerdos.dao.DAOOrdenesDelDiaInterface;
import mx.ine.acuerdos.dao.DAOResponsablesInterface;
import mx.ine.acuerdos.dao.DAOTipoSesionesInterface;
import mx.ine.acuerdos.dto.DTOAnexosOrdenesDelDia;
import mx.ine.acuerdos.dto.DTOCTipoIntegComision;
import mx.ine.acuerdos.dto.DTOComisiones;
import mx.ine.acuerdos.dto.DTOComisionesUnidas;
import mx.ine.acuerdos.dto.DTOConformComisiones;
import mx.ine.acuerdos.dto.DTOConvocatorias;
import mx.ine.acuerdos.dto.DTOConvocatoriasPK;
import mx.ine.acuerdos.dto.DTOIntegrantesComision;
import mx.ine.acuerdos.dto.DTOOrdenesDelDia;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOTipoSesiones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype")
@Service("asConvocatoria")
public class ASConvocatoria implements ASConvocatoriaInterface {

	@Autowired
	@Qualifier("daoResponsables")
	private transient DAOResponsablesInterface daoResponsable;

	@Autowired
	@Qualifier("daoIntegComision")
	private transient DAOIntegComisionInterface daoIntegComision;

	@Autowired
	@Qualifier("daoComisiones")
	private transient DAOComisionesInterface daoComisiones;

	@Autowired
	@Qualifier("daoConformComisiones")
	private transient DAOConformComisionesInterface daoConformComisiones;

	@Autowired
	@Qualifier("daoComisionesUnidas")
	private transient DAOComisionesUnidasInterface daoComisionUnida;

	@Autowired
	@Qualifier("daoCTipoIntegComision")
	private transient DAOCTipoIntegComisionInterface daoTipoIntegComision;

	@Autowired
	@Qualifier("daoTiposSesiones")
	private transient DAOTipoSesionesInterface daoTiposDeSesiones;

	@Autowired
	@Qualifier("daoConvocatorias")
	private transient DAOConvocatoriasInterface daoConvocatoria;

	@Autowired
	@Qualifier("daoOrdenesDelDia")
	private transient DAOOrdenesDelDiaInterface daoOrdenDelDia;

	@Autowired
	@Qualifier("daoAnexosOrdenesDelDia")
	private transient DAOAnexosOrdenesDelDiaInterface daoAnexosOrdenDelDia;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOResponsables recuperarDtoResponsable(String nomUsuario) throws Exception {
		return daoResponsable.obtenerResponsPorUsuario(nomUsuario);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOIntegrantesComision recuperarDtoIntegComision(Integer idResponsable) throws Exception {
		return daoIntegComision.obtenerIntegComision(idResponsable);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOComisiones recuperarComision(Integer idComision) throws Exception {
		return daoComisiones.obtenerComision(idComision);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOIntegrantesComision> recuperarIntegComision(Integer idComision) throws Exception {
		return daoIntegComision.obtenerIntegsComision(idComision);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOCTipoIntegComision> recuperarTipoIntegComision() throws Exception {
		return daoTipoIntegComision.obtenerTipoIntegComision();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOResponsables recuperarResponsComision(Integer idResponsable) throws Exception {
		return daoResponsable.obtenerResponsablePorID(idResponsable);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOTipoSesiones> recuperarTiposDeSesiones() throws Exception {
		return daoTiposDeSesiones.consultaTipoSesiones();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOConvocatorias> recuperarConvocatoriasPorAnio(Integer anio) throws Exception {
		return daoConvocatoria.obtenerConvocatoriasPorAnio(anio);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOComisiones> recuperarComisionesFiltradas(Integer idComision) throws Exception {
		return daoComisiones.obtenerComisionesConFiltro(idComision);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void insertarConvocatoria(DTOConvocatorias convocatoria) throws Exception {
		daoConvocatoria.insertarConvocatoria(convocatoria);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void insertarPuntoOrdenDia(DTOOrdenesDelDia ordenDelDia) throws Exception {
		daoOrdenDelDia.insertarPuntoOrdenDia(ordenDelDia);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public DTOConformComisiones recuperarConformComisionActual(Integer idComision) throws Exception {
		return daoConformComisiones.obtenerConformComisionActual(idComision);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOComisionesUnidas> recuperarComisionesUnidas(Integer idComisionPreside) throws Exception {
		return daoComisionUnida.obtenerComisionesUnidas(idComisionPreside);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
	public void insertarAnexoOrdenDia(DTOAnexosOrdenesDelDia anexoOrdenDia) throws Exception {
		daoAnexosOrdenDelDia.insertarAnexoOrdenDia(anexoOrdenDia);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOConvocatorias> recuperarConvocatoriasFechaTipo(Integer idAnio, Integer tipoSesion) throws Exception {
		return daoConvocatoria.obtenerConvocatoriasFechaTipo(idAnio, tipoSesion);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOConvocatorias> recuperarConvocatoriasFormatAnio(Date inicioAnio, Date finAnio) throws Exception {
		return daoConvocatoria.obtenerConvocatoriasFormatAnio(inicioAnio, finAnio);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOOrdenesDelDia> recuperarOrdenDelDia(DTOConvocatoriasPK dtoConvoctariaPK) throws Exception {
		return daoOrdenDelDia.obtenerOrdenDelDia(dtoConvoctariaPK);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public List<DTOConvocatorias> recuperarConvocatorias() throws Exception {
		return daoConvocatoria.obtenerConvocatorias();
	}

}
