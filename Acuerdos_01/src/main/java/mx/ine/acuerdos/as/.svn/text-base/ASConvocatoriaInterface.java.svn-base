package mx.ine.acuerdos.as;

import java.util.Date;
import java.util.List;

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

/**
 * Interfaz que sirve que comunica a las capas BSD y AS de las vistas de Captura y
 * Consulta de la Convocatoria, contiene la firma de todos los métodos disponibles
 * 
 * @author Miguel Ortiz
 * @since 05/12/2017
 */
public interface ASConvocatoriaInterface {

	public DTOResponsables recuperarDtoResponsable(String nomUsuario) throws Exception;
	public DTOIntegrantesComision recuperarDtoIntegComision(Integer idResponsable) throws Exception;
	public DTOComisiones recuperarComision(Integer idComision) throws Exception;
	public List<DTOIntegrantesComision> recuperarIntegComision(Integer idComision) throws Exception;
	public List<DTOCTipoIntegComision> recuperarTipoIntegComision() throws Exception;
	public DTOResponsables recuperarResponsComision(Integer idResponsable) throws Exception;
	public List<DTOTipoSesiones> recuperarTiposDeSesiones() throws Exception;
	public List<DTOConvocatorias> recuperarConvocatoriasPorAnio(Integer anio) throws Exception;
	public List<DTOComisiones> recuperarComisionesFiltradas(Integer idComision) throws Exception;
	public void insertarConvocatoria(DTOConvocatorias convocatoria) throws Exception;
	public void insertarPuntoOrdenDia(DTOOrdenesDelDia ordenDelDia) throws Exception;
	public DTOConformComisiones recuperarConformComisionActual(Integer idComision) throws Exception;
	
	public List<DTOComisionesUnidas> recuperarComisionesUnidas(Integer idComisionPreside) throws Exception;
	public void insertarAnexoOrdenDia(DTOAnexosOrdenesDelDia anexoOrdenDia) throws Exception;

	public List<DTOConvocatorias> recuperarConvocatoriasFechaTipo(Integer idAnio, Integer tipoSesion) throws Exception;
	public List<DTOConvocatorias> recuperarConvocatoriasFormatAnio(Date inicioAnio, Date finAnio) throws Exception;
	public List<DTOOrdenesDelDia> recuperarOrdenDelDia(DTOConvocatoriasPK dtoConvoctariaPK) throws Exception;
	public List<DTOConvocatorias> recuperarConvocatorias() throws Exception;

}
