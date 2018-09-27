package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.ine.acuerdos.as.ASRegistroPuntosInterface;
import mx.ine.acuerdos.bsd.BSDRegistroPuntosInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdoPK;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
import mx.ine.acuerdos.dto.helper.form.HLPFormPuntos;
import mx.ine.acuerdos.util.CorreoAcuerdos;
import mx.ine.acuerdos.util.Utilidades;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bsdRegPuntos")
@Scope("prototype")
public class BSDRegistroPuntos implements BSDRegistroPuntosInterface,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6228145362714403522L;
	/**
	 * 
	 */
	private boolean isDatosValidos;
	private DTOSeguimientosCG seguimiento;
	private static final Log log = LogFactory.getLog(BSDRegistroPuntos.class);
	@Autowired
	@Qualifier("asRegPuntos")
	private transient ASRegistroPuntosInterface asRegPuntos;

	@Override
	public boolean guardarPunto(HLPFormPuntos form, List<DTOResponsables> responsables,List<DTOCClasificaciones> clasificaciones, DTOAcuerdos acuerdo, List<DTOCAreas> seleccionados) {
		DTOPuntosAcuerdo punto = form.getPunto();
		DTOPuntosAcuerdoPK pk = form.getPkPunto();
		try {
			punto.setNumeraliaTexto(obtenerNumeraliCardinal(pk));
			if (datosValidos(punto)) {
				
				punto.setTextoPunto(punto.getNumeraliaTexto() + ".- "
						+ punto.getTextoPunto());
				punto.setId(pk);
				punto.setActivo(1);
				asRegPuntos.guardarPunto(punto);
				Integer areaInsertada = new Integer(0);
				for (DTOResponsables responsable : responsables) {
					
					//se valida si el area ya fue insertada ya no se vuelve a insertar aun que tenga mas de un responsable
					if(responsable.getIdArea() != areaInsertada){
					areaInsertada = responsable.getIdArea();
					seguimiento = new DTOSeguimientosCG();
					seguimiento.getId().setIdNumAcuerdos(punto.getId().getIdNumAcuerdo());
					seguimiento.getId().setNumeralia(punto.getId().getNumeralia());
					seguimiento.getId().setIdArea(responsable.getIdArea());
					seguimiento.getId().setTipoArea(responsable.getTipoArea());
					seguimiento.getId().setIdEstatusPunto(1);
					seguimiento.setTipoMovimiento(9);
					seguimiento.setDescripcion("En espera de un movimiento");
					asRegPuntos.guardaroActualizarSeguimiento(seguimiento);
					}
					if(responsable.getTipoResponsable().equals(1)){
						List<String> correos = new ArrayList<String>();
						correos.add(responsable.getCorreo());
						/*correos.add("jorge.luna@ine.mx");
						correos.add("lizbeth.vargasl@ine.mx");
						correos.add("xochiquetzal.hernand@ine.mx");*/
						//Envia correo de notificacion cuando un area es asignada a un punto
						CorreoAcuerdos correo =  new CorreoAcuerdos(punto,1,clasificaciones,responsables,acuerdo,seleccionados,false);
						correo.setCorreos(correos);
						correo.enviaNotification();
					}
				}			
				
				return true;
			} else {
				log.error(" BSDRegistroPuntos.guardarPunto : Datos incorresctos");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDRegistroPuntos.guardarPunto :" + e.getMessage());
			return false;
		}
	}
	
	@Override
	public boolean guardarPunto(HLPFormPuntos form, List<DTOResponsables> responsables,List<DTOCClasificaciones> clasificaciones, DTOAcuerdos acuerdo, List<DTOCAreas> seleccionados,List<String> respConjunta,List<String> respSuelta) {
		
		DTOPuntosAcuerdo punto = form.getPunto();
		DTOPuntosAcuerdoPK pk = form.getPkPunto();
		try {
			punto.setNumeraliaTexto(obtenerNumeraliCardinal(pk));
			if (datosValidos(punto)) {
				
				punto.setTextoPunto(punto.getNumeraliaTexto() + ".- "
						+ punto.getTextoPunto());
				punto.setId(pk);
				punto.setActivo(1);
				asRegPuntos.guardarPunto(punto);
				Integer areaInsertada = new Integer(0);
				boolean responsabilidaConjunta = false;
				
				for (DTOResponsables responsable : responsables) {
					responsabilidaConjunta = false;				
								
					//se valida si el area ya fue insertada ya no se vuelve a insertar aun que tenga mas de un responsable
					if(responsable.getIdArea() != areaInsertada){
					areaInsertada = responsable.getIdArea();
					seguimiento = new DTOSeguimientosCG();
					seguimiento.getId().setIdNumAcuerdos(punto.getId().getIdNumAcuerdo());
					seguimiento.getId().setNumeralia(punto.getId().getNumeralia());
					seguimiento.getId().setIdArea(responsable.getIdArea());
					seguimiento.getId().setTipoArea(responsable.getTipoArea());
					seguimiento.getId().setIdEstatusPunto(1);
					seguimiento.setTipoMovimiento(9);
					seguimiento.setDescripcion("En espera de un movimiento");
					
					
						String areAbuscar= "";
							for(DTOCAreas area: seleccionados){
								if(area.getId().getIdArea().equals(responsable.getIdArea())){
									areAbuscar = area.getDescripcion();
									
									break;
								}
							}
						if(respConjunta.contains(areAbuscar)){	
							responsabilidaConjunta = true;
								//encontrar la posicion del area en la responsabilidad conjunta para saber si es suelta o en grupo
								int posicionDelArea= -1;
								int contador = 0;
								for (String resp : respConjunta) {
									contador++;
									if(areAbuscar.equals(resp)){
										posicionDelArea = contador;
										
									}
								}
								int posicionDelPadre = -1;
								//en contrar el padre para saber el tipo de responsabilidad
								for(int i = posicionDelArea-1; i >= 0; i--){
									if(respConjunta.get(i).contains("Responsabilidad")){
										posicionDelPadre = i;
										
										break;
									}
								}
								//evaluamos la responsabilidad
								if(respConjunta.get(posicionDelPadre).contains("Conjunta")){//si es conjunta tenemos que saber el numero de grupo
									
									String padre = respConjunta.get(posicionDelPadre);//obtenemos la cadena del padre
									int posicionPunto = padre.indexOf(".");//buscamos la posicion del punto p.e Responsabilidad Conjunta num.1
									
									int grupo = Integer.parseInt(padre.substring(posicionPunto+1, padre.length()).trim());//se convierte a entero el del . hacia adelante
								
									seguimiento.setResponsabilidadConjunta(grupo);//asignamos el grupo
								}
								/* anterir logica donde los conjuntos y los sueltos vienen en el mismo List
								else{
									seguimiento.setResponsabilidadConjunta(new Integer(0));
								}*/						
						}else{
							seguimiento.setResponsabilidadConjunta(new Integer(0));
						}						
					
					asRegPuntos.guardaroActualizarSeguimiento(seguimiento);
					}
					if(responsable.getTipoResponsable().equals(1)){
						List<String> correos = new ArrayList<String>();
						correos.add(responsable.getCorreo());
						/*correos.add("jorge.luna@ine.mx");
						correos.add("lizbeth.vargasl@ine.mx");
						correos.add("xochiquetzal.hernand@ine.mx");*/
						//Envia correo de notificacion cuando un area es asignada a un punto
						CorreoAcuerdos correo =  new CorreoAcuerdos(punto,1,clasificaciones,responsables,acuerdo,seleccionados,true);
						correo.setCorreos(correos);
						correo.enviaNotification();
					}
					
				}			
				
				return true;
			} else {
				log.error(" BSDRegistroPuntos.guardarPunto : Datos incorresctos");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDRegistroPuntos.guardarPunto :" + e.getMessage());
			return false;
		}
	}

	@Override
	public List<DTOCClasificaciones> recuperaClasificaciones() {
		try {
			return asRegPuntos.recuperaClasificaciones();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDRegistroPuntos.recuperaClasificaciones :"
					+ e.getMessage());
			return null;
		}
	}

	@Override
	public String obtenerNumeraliCardinal(DTOPuntosAcuerdoPK idPunto) {
		String idAcuerdo = idPunto.getIdNumAcuerdo();
		Integer numeralia = idPunto.getNumeralia();
		// Se obtiene el punto del acuerdo
		DTOPuntosAcuerdo puntoTemp = obtenerPunto(idAcuerdo, numeralia);
		Integer numTemp = new Integer(0);
		Utilidades util = new Utilidades();
		// Se valida que la numeralia no sea vacia
		if (numeralia.equals(0)) {
			this.isDatosValidos = false;
			return "¡Numeralia inválida!";
		}

		if (puntoTemp != null) {
			numTemp = puntoTemp.getId().getNumeralia();

			if (numeralia.equals(numTemp) && puntoTemp.getActivo().equals(1)) {
				this.isDatosValidos = false;
				return "El número ingresado, ya se encuentra registrado";
			}
		} else {
			this.isDatosValidos = true;
			return util.ordinalesToCardinales(numeralia);
		}
		this.isDatosValidos = true;
		return util.ordinalesToCardinales(numeralia);
	}

	@Override
	public DTOPuntosAcuerdo obtenerPunto(String idAcuerdo, Integer numeralia) {

		try {
			DTOPuntosAcuerdo puntoemp = asRegPuntos.obtenerPunto(idAcuerdo,
					numeralia);
			return puntoemp;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDRegistroPuntos.obtenerPunto :" + e.getMessage());
			return null;
		}

	}

	@Override
	public boolean datosValidos(DTOPuntosAcuerdo punto) {

		if (punto != null) {
			if (punto.getTextoPunto() != null
					&& "".equals(punto.getTextoPunto().trim())) {
				return false;
			}			
			return this.isDatosValidos;

		} else {

			return false;
		}
	}

	@Override
	public Date obtenerFechaSesion(String idAcuerdo) {
		return asRegPuntos.obtenerFechaSesion(idAcuerdo);
	}
	
	@Override
	public List<DTOCAreas> obtenerTodasLasAreas() {
		try {
			List<DTOCAreas> todasAreas = asRegPuntos.obtenerTodasLasAreas();
			List<DTOResponsables> todosResponsables =  obtenerResponsablesArea(todasAreas);
			List<DTOCAreas> eliminar = new ArrayList<DTOCAreas>(); 
			for (DTOCAreas area : todasAreas) {//recorremos todas las areas para saber si tienen responsable
				boolean tieneResp =false;
				for (DTOResponsables responsable : todosResponsables) {
					if(area.getId().getIdArea().equals(responsable.getIdArea())){
						tieneResp =true;
						break;
					}					
				}
				if(tieneResp == false){//si no tienen responsable se elimina
					eliminar.add(area);
				}
			}
			
			for (DTOCAreas elim : eliminar) {
				todasAreas.remove(elim);
			}
			
			return todasAreas;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDRegistroPuntos.obtenerTodasLasAreas :" + e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<DTOResponsables> obtenerResponsablesArea(List<DTOCAreas> areas){
		List<DTOResponsables> responsables = new ArrayList<DTOResponsables>();
		List<DTOResponsables> responAux = new ArrayList<DTOResponsables>();
		try {
			for (DTOCAreas area : areas) {
				responAux = asRegPuntos.obtenerResponsable(area.getId().getIdArea());
					for (DTOResponsables responsable : responAux) {
						responsables.add(responsable);
					}
			}	
			return responsables;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDRegistroPuntos.obtenerResponsablesArea :" + e.getMessage());
			return null;
		}
	}
	
	/**
     * Estaclecer lista de nombres de imagenes de Infografías
     * @param DTOAcuerdos, HLPFormRegistroAcuerdos
     * @return String
   */
	@Override
	public List<String> recuperaImgsInfografias(){
		List<String> imgInf = new ArrayList<String>();
		imgInf.add("registroPunto/Infografia_Reg_Pu_01.png");
		imgInf.add("registroPunto/Infografia_Reg_Pu_02.png");
		imgInf.add("registroPunto/Infografia_Reg_Pu_03.png");
		return  (List<String>) imgInf;
		
	}

	@Override
	public boolean obtenerSiPPN (HLPFormPuntos form){
		try {
			List<DTOCClasificaciones> clasificaciones = asRegPuntos.obtenerSiPPN();
			for (DTOCClasificaciones clas : clasificaciones) {
				if(form.getPunto().getIdClasificacion().equals(clas.getIdClasificacion()))
					return true;
			}					
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDRegistroPuntos.obtenerSiPPN :" + e.getMessage());			
		}
		return false;
	}
	
	@Override
	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo){
		try {			
			return asRegPuntos.obtenerAcuerdo(idAcuerdo);			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDRegistroPuntos.obtenerAcuerdo :" + e.getMessage());			
		}
		return null;		
	}

	@Override
	public TreeNode construirArbolRespons(List<DTOCAreas> listaAreas, List<DTOResponsables> listaResponsables) {
		TreeNode raizArbol = new DefaultTreeNode("Raiz del Arbol", null);
	
		for(DTOCAreas area : listaAreas) {
			TreeNode nodoArea = new DefaultTreeNode(area.getDescripcion(), raizArbol);
			for(DTOResponsables responsable : listaResponsables) {
				if(area.getId().getIdArea().equals(responsable.getIdArea()) &&
				   responsable.getEstatus().equals(1) &&			// Se selecciona sólo a aquellos responsables activos
				   responsable.getTipoResponsable().equals(1)) {	// Se selecciona sólo a aquellos responsables que incolucren notificación
					nodoArea.getChildren().add(new DefaultTreeNode(responsable.getNombre() + " " + responsable.getApellidos()));
				}
			}
			nodoArea.setExpanded(false);
		}
	
		return raizArbol;
	}

}
