package mx.ine.computosINE.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import mx.ine.computosINE.dao.DAOCEstatusInterface;
import mx.ine.computosINE.dto.DTOCEstatus;

/**
 * <code>DAOCEstatusInterface.java</code> 
 *
 * @author Giovanni Hernández Alonso
 * @since 24/04/20167
 * @copyright Direccion de sistemas - INE
 */

@Repository("daoCEstatus")
@Scope("prototype")
public class DAOCEstatusImpl extends DAOGeneric< DTOCEstatus, Integer> implements DAOCEstatusInterface, Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 2766757168835942119L;
	
	/**
     * Elemento para generar log
     */
    private static final Log LOGGER = LogFactory.getLog(DAOCEstatusImpl.class);

	@Override
	@SuppressWarnings("unchecked")
	public List<DTOCEstatus> estatusComputos() throws Exception {
		
        LOGGER.info("se ejecuta el método DAOCEstatusImpl.estatusComputos()");
        
        Criteria criteria = getSession().createCriteria(DTOCEstatus.class);
        return (List<DTOCEstatus>) criteria.list();
		
	}



}
