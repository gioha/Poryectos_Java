package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.ine.acuerdos.as.ASModificarPuntosInterface;
import mx.ine.acuerdos.as.ASSeguimientoPuntosInterface;
import mx.ine.acuerdos.bsd.BSDModificarPuntosInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOSeguimientosCG;
import mx.ine.acuerdos.dto.helper.form.HLPFormPuntos;
import mx.ine.acuerdos.util.CorreoAcuerdos;
import mx.ine.acuerdos.util.Utilidades;

@Component("bsdModPuntos")
@Scope("prototype")
public class BSDModificarPuntos implements BSDModificarPuntosInterface,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6532442466097046543L;

	private static final Log log = LogFactory.getLog(BSDModificarPuntos.class);
	@Autowired
	@Qualifier("asModPuntos")
	private transient ASModificarPuntosInterface asModPuntos;
	
	@Autowired
	@Qualifier("asSegPuntos")
	private transient ASSeguimientoPuntosInterface asSegPuntos;
	
	
	private DTOSeguimientosCG seguimiento;
	private List<DTOSeguimientosCG> seguimientoAux;
	private List<DTOSeguimientosCG> responsablesOld;
	private List<DTOResponsables> responsablesArea;

	@Override
	public List<DTOCClasificaciones> recuperaClasificaciones() {
		try {
			return asModPuntos.recuperaClasificaciones();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDModificarPuntos.recuperaClasificaciones :"
					+ e.getMessage());
			return null;
		}

	}

	@Override
	public String obtenerNumeraliCardinal(Integer numActual, String idAcuerdo,
			Integer numeralia) {

		DTOPuntosAcuerdo puntoTemp = obtenerPunto(idAcuerdo, numeralia)
				.getPunto();
		Integer numTemp = new Integer(0);
		Utilidades util = new Utilidades();

		if (numeralia.equals(0)) {
			return "Esta numeralia es invalida";
		}

		if (puntoTemp != null) {
			numTemp = puntoTemp.getId().getNumeralia();
			if (numActual.equals(numeralia)) {
				return util.ordinalesToCardinales(numeralia);

			}

			if (numeralia.equals(numTemp) && puntoTemp.getActivo().equals(1)) {
				return "Esta numeralia ya existe";
			}
		} else {

			return util.ordinalesToCardinales(numeralia);
		}

		return util.ordinalesToCardinales(numeralia);
	}

	@Override
	public boolean actualizarPunto(HLPFormPuntos form, List<DTOResponsables> responsables,List<DTOCClasificaciones> clasificaciones, DTOAcuerdos acuerdo, List<DTOCAreas> seleccionados,List<DTOCAreas> areas) {
		
		form.setPkPunto(form.getPkPunto());
		form.getPunto().setId(form.getPkPunto());
		form.getPunto().setTextoPunto(
				form.getNumOrdinal() + ".- " + form.getPunto().getTextoPunto());
		form.getPunto().setActivo(1);
		DTOPuntosAcuerdo punto = form.getPunto();
		
		try {
			asModPuntos.actualizarPunto(punto);
			responsablesOld = asModPuntos.obtenerResponsablesDelPunto(form.getPkPunto().getIdNumAcuerdo(), form.getPkPunto().getNumeralia());
			//si la lista de responsables anterior no contiene a los nuevos estos se agragan al seguimiento
			for (DTOCAreas selec : seleccionados) {
				
				boolean existe = false;
				Integer areaNotificada = 0;
							for (DTOSeguimientosCG old : responsablesOld) {				
								if(!areaNotificada.equals(old.getId().getIdArea())){
									areaNotificada = old.getId().getIdArea();
										if (selec.getId().getIdArea().equals( old.getId().getIdArea() )){						
											existe = true;
											
											responsablesArea= asModPuntos.obtenerResponsable(selec.getId().getIdArea());
											old.setResponsabilidadConjunta(0);
											asModPuntos.guardaroActualizarSeguimiento(old);
											
											for(DTOResponsables responsable: responsablesArea){//se envia un correo a los responsables del area notificandoles que se ha modificado el punto
												
												if(responsable.getIdArea().equals(selec.getId().getIdArea())){
													if(responsable.getTipoResponsable().equals(1)){
														List<String> correos = new ArrayList<String>();
														correos.add(responsable.getCorreo());
														/*correos.add("jorge.luna@ine.mx");
														correos.add("lizbeth.vargasl@ine.mx");
														correos.add("xochiquetzal.hernand@ine.mx");*/
														
														CorreoAcuerdos correo =  new CorreoAcuerdos(punto,3,clasificaciones,responsables,acuerdo,seleccionados,false);
														correo.setCorreos(correos);
														correo.enviaNotification();
													}
												}
											}					
										}
								 }
							 }	
				
				if( !existe ){//se agregan a seguimiento y se les notifica por correo
					seguimiento = new DTOSeguimientosCG();
					seguimiento.getId().setIdNumAcuerdos(punto.getId().getIdNumAcuerdo());
					seguimiento.getId().setNumeralia(punto.getId().getNumeralia());
					seguimiento.getId().setIdArea(selec.getId().getIdArea());
					seguimiento.getId().setTipoArea(selec.getId().getTipoArea());
					seguimiento.getId().setIdEstatusPunto(1);
					seguimiento.setTipoMovimiento(3);
					seguimiento.setDescripcion("En espera de un movimiento");							
					seguimiento.setResponsabilidadConjunta(new Integer(0));
					
					asModPuntos.guardaroActualizarSeguimiento(seguimiento);						
					
					responsablesArea= asModPuntos.obtenerResponsable(selec.getId().getIdArea());
					
					for(DTOResponsables responsable: responsablesArea){//se envia un correo a los responsables del area notificandoles que se han agregado
						if(responsable.getIdArea().equals(selec.getId().getIdArea())){
							if(responsable.getTipoResponsable().equals(1)){
								List<String> correos = new ArrayList<String>();
								correos.add(responsable.getCorreo());
								/*correos.add("jorge.luna@ine.mx");
								correos.add("lizbeth.vargasl@ine.mx");
								correos.add("xochiquetzal.hernand@ine.mx");*/
								
								CorreoAcuerdos correo =  new CorreoAcuerdos(punto,1,clasificaciones,responsables,acuerdo,seleccionados,false);
								correo.setCorreos(correos);
								correo.enviaNotification();
							}
						}
					}
				}
			}	
			
			Integer areaNotificadaOld = 0;
			//proceso para eliminar los responsables que fueron eliminados de la lista original
			for (DTOSeguimientosCG  old : responsablesOld) {
				
				boolean existe = false;
				
				for (DTOCAreas selec : seleccionados) {				
					
					if (old.getId().getIdArea().equals( selec.getId().getIdArea() )){						
						existe = true;
						break;
					}
				}
				
				if( !existe ){//si no existe se eliminan las areas de segumiento 
					seguimientoAux = asModPuntos.obtenerSeguimiento(punto.getId().getIdNumAcuerdo(), punto.getId().getNumeralia(), old.getId().getIdArea());					
					asModPuntos.eliminarSeguimiento(seguimientoAux.get(0));	
					//System.out.println("eliminado"+old.getId().getIdArea());
					
					responsablesArea= asModPuntos.obtenerResponsable(old.getId().getIdArea());
					if(!areaNotificadaOld.equals(old.getId().getIdArea())){
						areaNotificadaOld = old.getId().getIdArea();
								for(DTOResponsables responsable: responsablesArea){//se envia un correo a los responsables del area notificandoles que se han eliminado
									if(responsable.getIdArea().equals(old.getId().getIdArea())){
										if(responsable.getTipoResponsable().equals(1)){
											List<String> correos = new ArrayList<String>();
											correos.add(responsable.getCorreo());
											/*correos.add("jorge.luna@ine.mx");
											correos.add("lizbeth.vargasl@ine.mx");
											correos.add("xochiquetzal.hernand@ine.mx");*/
											
											CorreoAcuerdos correo =  new CorreoAcuerdos(punto,2,clasificaciones,responsables,acuerdo,seleccionados,false);
											correo.setCorreos(correos);
											correo.enviaNotification();
										}
									}
								}
					}			
				}
				
				
				
			}	
			
			
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			log.error(" BSDModificarPuntos.actualizarPunto :" + e.getMessage());
			return false;
		}

	}
	
	@Override
	public boolean actualizarPunto(HLPFormPuntos form, List<DTOResponsables> responsables,List<DTOCClasificaciones> clasificaciones, DTOAcuerdos acuerdo, List<DTOCAreas> seleccionados,List<DTOCAreas> areas,List<String> respConjunta,List<String> respSuelta) {
		form.setPkPunto(form.getPkPunto());
		form.getPunto().setId(form.getPkPunto());
		form.getPunto().setTextoPunto(
				form.getNumOrdinal() + ".- " + form.getPunto().getTextoPunto());
		form.getPunto().setActivo(1);
		DTOPuntosAcuerdo punto = form.getPunto();
		
		try {
			asModPuntos.actualizarPunto(punto);
			responsablesOld = asModPuntos.obtenerResponsablesDelPunto(form.getPkPunto().getIdNumAcuerdo(), form.getPkPunto().getNumeralia());
			//si la lista de responsables anterior no contiene a los nuevos estos se agragan al seguimiento
			for (DTOCAreas selec : seleccionados) {
				
				boolean existe = false;
				
				for (DTOSeguimientosCG old : responsablesOld) {				
					
					if (selec.getId().getIdArea().equals( old.getId().getIdArea() )){						
						existe = true;

						responsablesArea= asModPuntos.obtenerResponsable(selec.getId().getIdArea());
						
						for(DTOResponsables responsable: responsablesArea){//se envia un correo a los responsables del area notificandoles que se ha modificado el punto
							if(responsable.getIdArea().equals(selec.getId().getIdArea())){
								if(responsable.getTipoResponsable().equals(1)){
									List<String> correos = new ArrayList<String>();
									correos.add(responsable.getCorreo());
									/*correos.add("jorge.luna@ine.mx");
									correos.add("lizbeth.vargasl@ine.mx");
									correos.add("xochiquetzal.hernand@ine.mx");*/
									
									CorreoAcuerdos correo =  new CorreoAcuerdos(punto,3,clasificaciones,responsables,acuerdo,seleccionados,false);
									correo.setCorreos(correos);
									correo.enviaNotification();
								}
							}
						}
					}
				}
				
				if( !existe ){//si no existe en los asignados anteriores
					seguimiento = new DTOSeguimientosCG();
					seguimiento.getId().setIdNumAcuerdos(punto.getId().getIdNumAcuerdo());
					seguimiento.getId().setNumeralia(punto.getId().getNumeralia());
					seguimiento.getId().setIdArea(selec.getId().getIdArea());
					seguimiento.getId().setTipoArea(selec.getId().getTipoArea());
					seguimiento.getId().setIdEstatusPunto(1);
					seguimiento.setTipoMovimiento(3);
					seguimiento.setDescripcion("En espera de un movimiento");							

					String areAbuscar= "";
						for(DTOCAreas area: seleccionados){
							if(area.getId().getIdArea().equals(selec.getId().getIdArea())){//<------
								areAbuscar = area.getDescripcion();							
								break;
							}
						}
					if(respConjunta.contains(areAbuscar)){	
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
					asModPuntos.guardaroActualizarSeguimiento(seguimiento);	
					
					responsablesArea= asModPuntos.obtenerResponsable(selec.getId().getIdArea());
										
										for(DTOResponsables responsable: responsablesArea){//se envia un correo a los responsables del area notificandoles que se han asignado
											if(responsable.getIdArea().equals(selec.getId().getIdArea())){
												if(responsable.getTipoResponsable().equals(1)){
													List<String> correos = new ArrayList<String>();
													correos.add(responsable.getCorreo());
													/*correos.add("jorge.luna@ine.mx");
													correos.add("lizbeth.vargasl@ine.mx");
													correos.add("xochiquetzal.hernand@ine.mx");*/
													
													CorreoAcuerdos correo =  new CorreoAcuerdos(punto,1,clasificaciones,responsables,acuerdo,seleccionados,false);
													correo.setCorreos(correos);
													correo.enviaNotification();
												}
											}
										}
					
				}else{	//si ya existe en los asignados anteriores									

					String areAbuscar= "";
						for(DTOCAreas area: seleccionados){
							if(area.getId().getIdArea().equals(selec.getId().getIdArea())){//<------
								areAbuscar = area.getDescripcion();
								
								break;
							}
						}
					if(respConjunta.contains(areAbuscar)){	
							//encontrar la posicion del area en la responsabilidad conjunta para saber si es suelta o en grupo
							int posicionDelArea= -1;
							int contador = 0;
							for (String resp : respConjunta) {
								contador++;
								if(areAbuscar.equals(resp)){
									posicionDelArea = contador;
									;
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
								
								//seguimiento.setResponsabilidadConjunta(grupo);//asignamos el grupo
								
								asModPuntos.modificaSeguimientoAreaGrupo(punto.getId().getIdNumAcuerdo(), punto.getId().getNumeralia(), selec.getId().getIdArea(), grupo);
							}
												
					}else{
						//seguimiento.setResponsabilidadConjunta(new Integer(0));
						asModPuntos.modificaSeguimientoAreaGrupo(punto.getId().getIdNumAcuerdo(), punto.getId().getNumeralia(), selec.getId().getIdArea(), 0);
					}					
//					asModPuntos.guardaroActualizarSeguimiento(seguimiento);	
				}
			}	
			
			//proceso para eliminar los responsables que fueron eliminados de la lista original
			for (DTOSeguimientosCG  old : responsablesOld) {
				
				boolean existe = false;
				
				for (DTOCAreas selec : seleccionados) {				
					
					if (old.getId().getIdArea().equals( selec.getId().getIdArea() )){						
						existe = true;
						break;
					}
				}
				
				if( !existe ){
					seguimientoAux = asModPuntos.obtenerSeguimiento(punto.getId().getIdNumAcuerdo(), punto.getId().getNumeralia(), old.getId().getIdArea());					
					asModPuntos.eliminarSeguimiento(seguimientoAux.get(0));	
					
					responsablesArea= asModPuntos.obtenerResponsable(old.getId().getIdArea());
										
										for(DTOResponsables responsable: responsablesArea){//se envia un correo a los responsables del area notificandoles que se han eliminado
											if(responsable.getIdArea().equals(old.getId().getIdArea())){
												if(responsable.getTipoResponsable().equals(1)){
													List<String> correos = new ArrayList<String>();
													correos.add(responsable.getCorreo());
													/*correos.add("jorge.luna@ine.mx");
													correos.add("lizbeth.vargasl@ine.mx");
													correos.add("xochiquetzal.hernand@ine.mx");*/
													
													CorreoAcuerdos correo =  new CorreoAcuerdos(punto,2,clasificaciones,responsables,acuerdo,seleccionados,false);
													correo.setCorreos(correos);
													correo.enviaNotification();
												}
											}
										}
				}
			}	
			
			
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			log.error(" BSDModificarPuntos.actualizarPunto :" + e.getMessage());
			return false;
		}

	}
	
	@Override
	public HLPFormPuntos obtenerPunto(String idAcuerdo, Integer numeralia) {

		HLPFormPuntos formPunto = new HLPFormPuntos();
		Date fechaSesion = obtenerFechaSesion(idAcuerdo);
		try {
			DTOPuntosAcuerdo punto = asModPuntos.obtenerPunto(idAcuerdo,
					numeralia);
			if (punto.getIdClasificacion() == 1
					|| punto.getIdClasificacion() == 2) {
				if (punto.getFechaNotificacion() != null) {
					formPunto.setEsPPN(true);
					if (fechaSesion.compareTo(punto.getFechaNotificacion()) == 0) {
						formPunto.setEsFechSesion(true);
					} else {
						formPunto.setEsFechSesion(false);
					}

				} else {
					formPunto.setEsPPN(false);

				}

			}
			formPunto.setFechaSesion(fechaSesion);
			punto.setTextoPunto(limpiarTextoxPunto(punto.getTextoPunto()));
			formPunto.setPunto(punto);
			return formPunto;
		} catch (Exception e) {

			e.printStackTrace();
			log.error(" BSDModificarPuntos.obtenerPunto :" + e.getMessage());

			return null;
		}

	}
	
	@Override
	public List<DTOSeguimientosCG> obtenerResponsablesDelPunto(String idAcuerdo, Integer numeralia) {
		try {
			List<DTOSeguimientosCG> responsables = asModPuntos.obtenerResponsablesDelPunto(idAcuerdo, numeralia);			
			List<DTOSeguimientosCG> respAux2 = new ArrayList<DTOSeguimientosCG>();
			
			int count = 1;
			for (DTOSeguimientosCG r : responsables) {
				
				if ( count == 1 ){
					respAux2.add(r);
				}
				else{
					
					boolean existe = false;
					for (DTOSeguimientosCG rAux : respAux2) {
						if ( 
								rAux.getId().getIdArea().equals( r.getId().getIdArea() ) && 
								rAux.getId().getTipoArea().equals( r.getId().getTipoArea() )
							)
						{
							existe = true;
							break;
						}
					}
					if(!existe){
						respAux2.add(r);
					}
					
					
				}
				count ++;
			}
			
			
			if ( respAux2.size() > 0 )
				responsables = respAux2;
			
			
			return responsables;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDModificarPuntos.obtenerTodasLasAreas :" + e.getMessage());
			return null;
		}
	}
	@Override
	public List<DTOSeguimientosCG> obtenerTodoSeguimiento(String idAcuerdo, Integer numeralia) {
		try {		
			return asModPuntos.obtenerResponsablesDelPunto(idAcuerdo, numeralia);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDModificarPuntos.obtenerTodoSeguimiento :" + e.getMessage());
			return null;
		}
	}
	
	@Override
	public List<DTOCAreas> obtenerTodasLasAreas() {
		try {
			List<DTOCAreas> todasAreas = asModPuntos.obtenerTodasLasAreas();
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
			log.error(" BSDModificarPuntos.obtenerTodasLasAreas :" + e.getMessage());
			return null;
		}
	}
	
	@Override
	public DTOCAreas obtenerArea(Integer idArea) {
		try {
			return asModPuntos.obtenerArea(idArea);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDModificarPuntos.obtenerArea :" + e.getMessage());
			return null;
		}
	}

	@Override
	public String limpiarTextoxPunto(String txtPunto) {
		int pos = txtPunto.indexOf(".-") + 3;
		txtPunto = txtPunto.substring(pos);
		return txtPunto;
	}

	@Override
	public Date obtenerFechaSesion(String idAcuerdo) {
		return asModPuntos.obtenerFechaSesion(idAcuerdo);
	}
	
	@Override
	public List<DTOResponsables> obtenerResponsablesArea(List<DTOCAreas> areas){
		List<DTOResponsables> responsables = new ArrayList<DTOResponsables>();
		List<DTOResponsables> responAux = new ArrayList<DTOResponsables>();
		try {
			for (DTOCAreas area : areas) {
				responAux = asModPuntos.obtenerResponsable(area.getId().getIdArea());
					for (DTOResponsables responsable : responAux) {
						responsables.add(responsable);
					}
			}	
			return responsables;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("BSDModificarPuntos.obtenerResponsablesArea :" + e.getMessage());
			return null;
		}
	}
	
	@Override
	public String extraerNomArchivo(String str){
		
		str = str.replace("\\", ";");
		str = str.replace("/", ",");
        String[] cadenas = str.split(";");
        //System.out.println(cadenas[cadenas.length-1]);
		return (cadenas[cadenas.length-1]);
		
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
		return  (List<String>) imgInf;
	}
	
	@Override
	public boolean obtenerSiPPN (HLPFormPuntos form){
		try {
			List<DTOCClasificaciones> clasificaciones = asModPuntos.obtenerSiPPN();
			for (DTOCClasificaciones clas : clasificaciones) {
				if(form.getPunto().getIdClasificacion().equals(clas.getIdClasificacion()))
					return true;
			}					
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDModificarPuntos.obtenerSiPPN :" + e.getMessage());			
		}
		return false;
	}
	
	@Override
	public DTOAcuerdos obtenerAcuerdo(String idAcuerdo) {
		try {
			return asModPuntos.obtenerAcuerdo(idAcuerdo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDModificarPuntos.recuperaClasificaciones :"
					+ e.getMessage());
			return null;
		}

	}
	@Override
	public void eliminarSeguimiento(List<DTOSeguimientosCG> seguimientos) {
		try {
			for (DTOSeguimientosCG seguimiento : seguimientos) {
				
				List<DTOSeguimientosCG> seguiArea = asSegPuntos.recuperarSeguimientoArea(seguimiento.getId().getIdNumAcuerdos(), seguimiento.getId().getNumeralia(), seguimiento.getId().getIdArea());
				for (DTOSeguimientosCG segArea : seguiArea) {
					asSegPuntos.eliminarSegPunto(segArea);
				}
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(" BSDModificarPuntos.eliminarSeguimiento :"
					+ e.getMessage());			
		}

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
