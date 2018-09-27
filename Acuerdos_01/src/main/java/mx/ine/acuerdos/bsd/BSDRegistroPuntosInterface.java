package mx.ine.acuerdos.bsd;

import java.util.Date;
import java.util.List;

import org.primefaces.model.TreeNode;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdoPK;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.helper.form.HLPFormPuntos;

public interface BSDRegistroPuntosInterface {
	/**
     * metodo para guardar punto sin respuesta conjunta
     * @author Sampier Cuevas
     * @param HLPFormPuntos form, List<DTOResponsables> responsables,List<DTOCClasificaciones> clasificaciones, DTOAcuerdos acuerdo, List<DTOCAreas> seleccionados
     * @return boolean
     * @since 13/10/2017*/
	public boolean guardarPunto(HLPFormPuntos form, List<DTOResponsables> responsables,List<DTOCClasificaciones> clasificaciones, DTOAcuerdos acuerdo, List<DTOCAreas> seleccionados);
	/**
     * metodo para guardar punto con respuesta conjunta
     * @author Sampier Cuevas
     * @param HLPFormPuntos form, List<DTOResponsables> responsables,List<DTOCClasificaciones> clasificaciones, DTOAcuerdos acuerdo, List<DTOCAreas> seleccionados, List<String> respConjunta,List<String> respSuelta
     * @return boolean
     * @since 13/10/2017*/
	public boolean guardarPunto(HLPFormPuntos form, List<DTOResponsables> responsables,List<DTOCClasificaciones> clasificaciones, DTOAcuerdos acuerdo, List<DTOCAreas> seleccionados, List<String> respConjunta,List<String> respSuelta);
	/**
     * metodo recuperar las clasificaciones de la Bd que pueden ser asignada al punto
     * @author Sampier Cuevas
     * @param 
     * @return List<DTOCClasificaciones>
     * @since 13/10/2017*/
	public List<DTOCClasificaciones> recuperaClasificaciones();
	/**
     * metodo para convertir a cardinal
     * @author Sampier Cuevas
     * @param DTOPuntosAcuerdoPK pkPunto
     * @return String
     * @since 13/10/2017*/
	public String obtenerNumeraliCardinal(DTOPuntosAcuerdoPK pkPunto);
	/**
     * se obtine un punto a partir de idAcuerdo y numeralia
     * @author Sampier Cuevas
     * @param String idAcuerdo, Integer numeralia
     * @return DTOPuntosAcuerdo
     * @since 13/10/2017*/
	public DTOPuntosAcuerdo obtenerPunto(String idAcuerdo, Integer numeralia);
	/**
     * valida que los datos ingresados en el resgistro sean correctos
     * @author Sampier Cuevas
     * @param SDTOPuntosAcuerdo punto
     * @return boolean
     * @since 13/10/2017*/
	public boolean datosValidos(DTOPuntosAcuerdo punto);
	/**
     * obtiene la fecha de sesion del id de acuerdo proporcionado
     * @author Sampier Cuevas
     * @param String idAcuerdo
     * @return Date
     * @since 13/10/2017*/
	public Date obtenerFechaSesion(String idAcuerdo);
	/**
     * obtiene todas las areas existentes en la BD
     * @author Sampier Cuevas
     * @param 
     * @return List<DTOCAreas>
     * @since 13/10/2017*/
	public List<DTOCAreas> obtenerTodasLasAreas();
	/**
     * obtiene todos los responsables de las areas proporcionadas
     * @author Sampier Cuevas
     * @param List<DTOCAreas> areas
     * @return List<DTOResponsables>
     * @since 13/10/2017*/
	public List<DTOResponsables> obtenerResponsablesArea(List<DTOCAreas> areas);
	/**
     * obtiene las infografias pertenecientes al registro del punto
     * @author Sampier Cuevas
     * @return List<String>
     * @since 13/10/2017*/
	public List<String> recuperaImgsInfografias();
	/**
     * retorna true si es PPN
     * @author Sampier Cuevas
     * @param HLPFormPuntos form
     * @return boolean
     * @since 13/10/2017*/
	public boolean obtenerSiPPN (HLPFormPuntos form);
	/**
     * retorna el objeto completo del idAcuerdo
     * @author Sampier Cuevas
     * @param String idAcuerdo
     * @return DTOAcuerdos
     * @since 13/10/2017*/
	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo);

	public TreeNode construirArbolRespons(List<DTOCAreas> listaAreas, List<DTOResponsables> listaResponsables);

}
