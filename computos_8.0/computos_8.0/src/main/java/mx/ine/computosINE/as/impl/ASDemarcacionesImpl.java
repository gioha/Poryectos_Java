package mx.ine.computosINE.as.impl;

import java.util.List;

import mx.ine.computosINE.as.ASDemarcacionesInterface;
import mx.ine.computosINE.dao.DAODemarcacionInterface;
import mx.ine.computosINE.dto.DTORegiduria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype")
@Service("asDemarcaciones")
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class ASDemarcacionesImpl implements ASDemarcacionesInterface{

	@Autowired
    @Qualifier("daoDemarcaciones")
    private DAODemarcacionInterface daoDemarcaciones;
	
	@Override
	@Transactional(readOnly = true, rollbackFor = { Exception.class })
	public List<DTORegiduria> consultaDemarcaciones(Integer idEstado,
			Integer idMunicipioLocal) throws Exception {
		return daoDemarcaciones.consultaDemarcaciones(idEstado, idMunicipioLocal);
	}

}
