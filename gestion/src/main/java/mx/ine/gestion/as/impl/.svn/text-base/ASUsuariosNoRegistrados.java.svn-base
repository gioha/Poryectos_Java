/**
 * @(#)ASUsuariosNoRegistrados.java 16/01/2018
 *
 * Copyright (C) 2018 Instituto Nacional Electoral (INE).
 * Todos los derechos reservados.
 */
package mx.ine.gestion.as.impl;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.Attributes;

import mx.ine.gestion.as.inter.ASUsuariosNoRegistradosInterface;
import mx.ine.gestion.dao.inter.DAOCAreasInterface;
import mx.ine.gestion.dao.inter.DAOCorresponsalInterface;
import mx.ine.gestion.dao.inter.DAOEstructuraAreaInterface;
import mx.ine.gestion.dao.inter.DAOJerarquiaPersonasInterface;
import mx.ine.gestion.dto.DTOUsuarioLogin;
import mx.ine.gestion.dto.db.DTOEstructuraAreasEntity;
import mx.ine.gestion.dto.db.DTOJerarquiaPersonasEntity;
import mx.ine.gestion.dto.db.catalogos.DTOCAreaEntity;
import mx.ine.gestion.dto.helper.DTOInformacionUsNoRegHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase que contiene las llamadas a los DAO´s que se utilizan en la vista para usuarios no registrados
 * 
 * @author Roberto Shirásago Domínguez
 * @since 16/01/2018
 */
@Scope("prototype")
@Component("asUsuariosNoRegistrados")
@Transactional(readOnly=true, rollbackFor={Exception.class})
public class ASUsuariosNoRegistrados implements ASUsuariosNoRegistradosInterface {

	@Autowired
	@Qualifier("daoCAreas")
	private DAOCAreasInterface daocAreasInterface;

	@Autowired
	@Qualifier("daoEstructuraArea")
	private DAOEstructuraAreaInterface daoEstructuraAreaInterface;

	@Autowired
	@Qualifier("daoJerarquiaPersonas")
	private DAOJerarquiaPersonasInterface daoJerarquiaPersonasInterface;

	@Autowired
	@Qualifier("daoCorresponsal")
	private DAOCorresponsalInterface daoCorresponsalInterface;

	@Autowired
	@Qualifier("ldapTemplate")
	private LdapTemplate ldapTemplate;

	/*
	 * (El comentario se encuentra en la interfaz dónde esta declarado el método)
	 * @see mx.ine.gestion.as.inter.ASUsuariosNoRegistradosInterface#buscarInformacionUsuarioNoRegistrado(mx.ine.gestion.dto.helper.DTOInformacionUsNoRegHelper)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void buscarInformacionUsuarioNoRegistrado(DTOInformacionUsNoRegHelper info) {
				
		//1.- Obtenemos el objeto que contiene la información del usuario en sesión
		DTOUsuarioLogin usuarioLogueado = ((DTOUsuarioLogin) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

		//2.- Buscamos la área donde debería estar registrado
		DTOCAreaEntity area = this.daocAreasInterface.buscarAreasPorNombre(usuarioLogueado.getNombreAreaLDAP().trim());

		//3.- Validamos si encontro alguna similitud
		if (area == null) {
			
			info.setTienePosibleArea(false);
			
		} 
		//3.- Validamos si no encontro resultados
		else {

			info.setTienePosibleArea(true);

			//3.1.- Vamos a buscar en LDAP a todos los administradores de área de su sistema para despues buscar el de su área y mandarlo con el
			
			List<String> listaCuentasAdmonAreaLDAP = ldapTemplate.search("ou=Grupos", "cn=GESTION4.ADMIN_AREA.OC", new AttributesMapper() {
				public Object mapFromAttributes(Attributes attrs) throws NamingException, javax.naming.NamingException {
					
					return attrs.get("memberUid");
			}
			});
			
			String cadenaCuentas = listaCuentasAdmonAreaLDAP.toString();
			String[] arreglo = cadenaCuentas.split(":");
			String[] arregloBueno = arreglo.length > 1 ? arreglo[1].split(",") : new String[0];
			List<String> listaCuentasLDAP = new ArrayList<String>();
			
			for (int i = 0; i < arregloBueno.length; i++) {
				listaCuentasLDAP.add(arregloBueno[i].trim());
			}
			
			//3.2.- Con las cuentas encontradas y el área buscamos a la información de los administradores de su área
			List<DTOEstructuraAreasEntity> administradoresArea = 
					this.daoEstructuraAreaInterface.consultarPersonasXCuentasXArea(listaCuentasLDAP, area.getTipoArea(), area.getIdArea());
			
			//3.3.- Validamos si encontro administradores de su área, si los encontro guardamos la información
			if (!administradoresArea.isEmpty()) {
				
				info.setTieneAdministradoresArea(true);
				info.setListaAdministradoresArea(administradoresArea);
				
			} 
			//3.4.- Si no encontro administrador, buscamos a su titular
			else {
				
				info.setTieneAdministradoresArea(false);
				
				//3.4.1.- Buscamos al titular y lo guardamos
				DTOJerarquiaPersonasEntity registroTitular = this.daoJerarquiaPersonasInterface.consultarTitular(area.getTipoArea(), area.getIdArea());
				info.setTitular(registroTitular.getDtoEstructuraAreas());
				
				//3.4.2.- Buscamos a sus corresponsables y los guardamos
				List<DTOEstructuraAreasEntity> corresponsables = this.daoCorresponsalInterface.buscarCorresponsales(registroTitular.getIdPersona());
				info.setListaCorresponsablesTitular(corresponsables);
			}
		}
	}
}
