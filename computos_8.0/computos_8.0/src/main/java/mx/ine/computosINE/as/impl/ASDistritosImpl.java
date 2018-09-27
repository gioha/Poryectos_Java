package mx.ine.computosINE.as.impl;

import java.util.List;

import mx.ine.computosINE.as.ASDistritosInterface;
import mx.ine.computosINE.dao.DAODistritoInterface;
import mx.ine.computosINE.dto.DTODistrito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype")
@Service("asDistritos")
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class ASDistritosImpl implements ASDistritosInterface{

	@Autowired
    @Qualifier("daoDistritos")
    private DAODistritoInterface daoDistritos;
	
	@Override
	@Transactional(readOnly = true, rollbackFor = { Exception.class })
	public List<DTODistrito> consultaDistritos(Integer idEstado,
			Integer idMunicipioLocal) throws Exception {
		return daoDistritos.consultaDistritos(idEstado, idMunicipioLocal);
	}

}
