package mx.ine.computosINE.bsd.impl;

import java.util.List;

import mx.ine.computosINE.as.ASDemarcacionesInterface;
import mx.ine.computosINE.bsd.BSDDemarcacionesInterface;
import mx.ine.computosINE.dto.DTORegiduria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bsdDemarcaciones")
@Scope("prototype")
public class BSDDemarcacionesImpl implements BSDDemarcacionesInterface{

	@Autowired
	@Qualifier("asDemarcaciones")
	private ASDemarcacionesInterface asDemarcaciones;
	
	@Override
	public List<DTORegiduria> cargaDemarcaciones(Integer idEstado,
			Integer idMunicipioLocal) throws Exception {
		return asDemarcaciones.consultaDemarcaciones(idEstado, idMunicipioLocal);
	}

}
