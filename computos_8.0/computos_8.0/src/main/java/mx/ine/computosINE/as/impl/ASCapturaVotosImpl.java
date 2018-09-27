package mx.ine.computosINE.as.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.ine.computosINE.as.ASCapturaVotosInterface;
import mx.ine.computosINE.dao.DAOActaCasillaVotosInterface;
import mx.ine.computosINE.dao.DAODistribucionVotosInterface;
import mx.ine.computosINE.dto.DTOActaCasillaVotos;
import mx.ine.computosINE.dto.DTOActaCasillaVotosPK;
import mx.ine.computosINE.dto.DTOAsociacion;
import mx.ine.computosINE.dto.DTODistribucionCandParcial;
import mx.ine.computosINE.dto.DTODistribucionCandParcialPK;
import mx.ine.computosINE.dto.DTOSubcoalicion;

/**
 * <code>ASCapturaVotosImpl.java</code>Descripcion de la clase
 *
 * @author Giovanni Hernandez Alonso
 * @version 1.0
 * @since 08/05/2017 
 */

@Scope("prototype")
@Service("asCapturaVotos")
public class ASCapturaVotosImpl implements ASCapturaVotosInterface{

	@Autowired
	@Qualifier("daoActaCasillaVotos")
	private transient DAOActaCasillaVotosInterface daoActaCasillaVotos;
	
	@Autowired
	@Qualifier("daoDistribucionVotos")
	private transient DAODistribucionVotosInterface daoDistribucionVotos;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> getActasEntidad(DTOActaCasillaVotosPK id) throws Exception
	{
		return daoActaCasillaVotos.getActasEntidad(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> getActasMunicipioEnSecciones(DTOActaCasillaVotosPK id) throws Exception {
	
		return daoActaCasillaVotos.getActasMunicipioEnSecciones(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> getActasMunicipioEnSeccionesParaDistribucion(DTOActaCasillaVotosPK id) throws Exception {
	
		return daoActaCasillaVotos.getActasMunicipioEnSeccionesParaDistribucion(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> getActasMunicipioEnDistritosSecciones(DTOActaCasillaVotosPK id) throws Exception {
		
		return daoActaCasillaVotos.getActasMunicipioEnDistritosSecciones(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public Integer getActasParcialesCapturadasEnDistrito(DTODistribucionCandParcialPK id) throws Exception {
		
		return daoActaCasillaVotos.getActasParcialesCapturadasEnDistrito(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> getActasCapturadasxEntidadCandidatura(DTOActaCasillaVotosPK id) throws Exception {
		
		return daoActaCasillaVotos.getActasCapturadasxEntidadCandidatura(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> getActasCapturadasEnDistrito(DTOActaCasillaVotosPK id) throws Exception {
		
		return daoActaCasillaVotos.getActasCapturadasEnDistrito(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegidurias(DTOActaCasillaVotosPK id) throws Exception {
		
		return daoActaCasillaVotos.getActasMunicipioEnRegidurias(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegiduriasParaDistribucion(DTOActaCasillaVotosPK id) throws Exception {
		
		return daoActaCasillaVotos.getActasMunicipioEnRegiduriasParaDistribucion(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> getActasMunicipioEnRegiduriasSecciones(DTOActaCasillaVotosPK id) throws Exception {
		
		return daoActaCasillaVotos.getActasMunicipioEnRegiduriasSecciones(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOSubcoalicion> recuperaSubcoaliciones(List<DTOAsociacion> coaliciones) throws Exception{
		
		return daoDistribucionVotos.recuperaSubcoaliciones(coaliciones);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void guardaVotosDeActa(List<DTOActaCasillaVotos> votosActa) throws Exception{
		daoActaCasillaVotos.saveOrUpdate(votosActa);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public List<DTOActaCasillaVotos> getvotosAsociacionesActa(DTOActaCasillaVotosPK id) throws Exception{
		return daoActaCasillaVotos.getvotosAsociacionesActa(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, rollbackFor={Exception.class})
	public List<DTOActaCasillaVotos> consultaActa (DTOActaCasillaVotosPK id)	throws Exception{
		return daoActaCasillaVotos.consultaActa (id);
	}
	
	@Override
	@Transactional(readOnly = false, rollbackFor = {Exception.class})
	public void eliminarActa(List<DTOActaCasillaVotos> acta) throws Exception{
		daoActaCasillaVotos.eliminar( acta );
	}
}
