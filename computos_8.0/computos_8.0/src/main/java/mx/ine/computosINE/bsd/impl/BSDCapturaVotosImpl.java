package mx.ine.computosINE.bsd.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.computosINE.as.ASCapturaVotosInterface;
import mx.ine.computosINE.bsd.BSDCapturaVotosInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOAsociacion;
import mx.ine.computosINE.dto.DTODistribucionCandParcial;
import mx.ine.computosINE.dto.DTODistribucionCandParcialPK;
import mx.ine.computosINE.dto.DTOSubcoalicion;

/**
 * <code>BSDCapturaVotosImpl.java</code>Descripcion de la clase
 *
 * @author Giovanni Hernández Alonso
 * @version 1.0
 * @since 08/05/2017 
 */
@Component("bsdCapturaVotos")
@Scope("prototype")
public class BSDCapturaVotosImpl implements BSDCapturaVotosInterface {

    @Autowired
    @Qualifier("asCapturaVotos")
    private ASCapturaVotosInterface asCapturaVotos;
	
	@Override
	public List<DTOActaCasillaVotos> getActasEntidad(DTOActaCasillaVotosPK id) throws Exception
	{
		return asCapturaVotos.getActasEntidad(id);
	}
    
    @Override
	public List<DTOActaCasillaVotos> getActasMunicipioEnSecciones(DTOActaCasillaVotosPK id) throws Exception {
		return asCapturaVotos.getActasMunicipioEnSecciones(id);
	}
    
    @Override
	public List<DTOActaCasillaVotos> getActasMunicipioEnSeccionesParaDistribucion(DTOActaCasillaVotosPK id) throws Exception {
		return asCapturaVotos.getActasMunicipioEnSeccionesParaDistribucion(id);
	}

	@Override
	public List<DTOActaCasillaVotos> getActasMunicipioEnDistritosSecciones(DTOActaCasillaVotosPK id) throws Exception {
		return asCapturaVotos.getActasMunicipioEnDistritosSecciones(id);
	}
	
	@Override
	public Integer getActasParcialesCapturadasEnDistrito(DTODistribucionCandParcialPK id) throws Exception {
		return asCapturaVotos.getActasParcialesCapturadasEnDistrito(id);
	}
	
	@Override
	public List<DTOActaCasillaVotos> getActasCapturadasxEntidadCandidatura(DTOActaCasillaVotosPK id) throws Exception {
		return asCapturaVotos.getActasCapturadasxEntidadCandidatura(id);
	}
	
	@Override
	public List<DTOActaCasillaVotos> getActasCapturadasEnDistrito(DTOActaCasillaVotosPK id) throws Exception {
		return asCapturaVotos.getActasCapturadasEnDistrito(id);
	}
	
	@Override
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegidurias(DTOActaCasillaVotosPK id) throws Exception {
		return asCapturaVotos.getActasMunicipioEnRegidurias(id);
	}
	
	@Override
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegiduriasParaDistribucion(DTOActaCasillaVotosPK id) throws Exception {
		return asCapturaVotos.getActasMunicipioEnRegiduriasParaDistribucion(id);
	}

	@Override
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegiduriasSecciones(DTOActaCasillaVotosPK id) throws Exception {
		return asCapturaVotos.getActasMunicipioEnRegiduriasSecciones(id);
	}

	@Override
	public List<DTOSubcoalicion> recuperaSubcoaliciones(List<DTOAsociacion> coaliciones) throws Exception{
		return asCapturaVotos.recuperaSubcoaliciones(coaliciones);
	}
	
	@Override
	public void guardaVotosDeActa(List<DTOActaCasillaVotos> votosActa) throws Exception{
		asCapturaVotos.guardaVotosDeActa(votosActa);
	}
	
	@Override
	public List<DTOActaCasillaVotos> getvotosAsociacionesActa(DTOActaCasillaVotosPK id) throws Exception{
		return asCapturaVotos.getvotosAsociacionesActa(id);
	}
	
	@Override
	public List<DTOActaCasillaVotos> consultaActa (DTOActaCasillaVotosPK id)	throws Exception{
		return asCapturaVotos.consultaActa (id);
	}
	
	@Override
	public void eliminarActa(List<DTOActaCasillaVotos> acta) throws Exception{
		asCapturaVotos.eliminarActa(acta);
	}
}
