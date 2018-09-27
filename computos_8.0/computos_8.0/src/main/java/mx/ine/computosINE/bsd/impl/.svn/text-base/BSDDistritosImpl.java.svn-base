package mx.ine.computosINE.bsd.impl;

import java.util.List;

import mx.ine.computosINE.as.ASDistritosInterface;
import mx.ine.computosINE.bsd.BSDDistritosInterface;
import mx.ine.computosINE.dto.DTODistrito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bsdDistritos")
@Scope("prototype")
public class BSDDistritosImpl implements BSDDistritosInterface{

	@Autowired
	@Qualifier("asDistritos")
	private ASDistritosInterface asDistritos;
	
	@Override
	public List<DTODistrito> cargaDistritos(Integer idEstado,
			Integer idMunicipioLocal) throws Exception {
		return asDistritos.consultaDistritos(idEstado, idMunicipioLocal);
	}

}
