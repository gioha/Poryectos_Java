package mx.ine.acuerdos.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOCAreas;
import mx.ine.acuerdos.dto.DTOCClasificaciones;
import mx.ine.acuerdos.dto.DTOCTipoIntegComision;
import mx.ine.acuerdos.dto.DTOIntegrantesComision;
import mx.ine.acuerdos.dto.DTOOrdenesDelDia;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.DTOResponsables;
import mx.ine.acuerdos.dto.DTOTipoSesiones;
import mx.ine.acuerdos.dto.helper.form.HelperConvocatoria;

/**
 * @author INE
 *
 */
public class CorreoAcuerdos extends Notificador{

	private String txtAsunto;
	

	@Override
	protected void getAsuntoCorreo() {
	}

	@Override
	protected void getCuerpoCorreo() {
		
	}
	
	@Override
	protected void getCuerpoCorreoException() {


	}
	/**
	 * @return the txtAsunto
	 */
	public String getTxtAsunto() {
		return txtAsunto;
	}

	/**
	 * @param txtAsunto the txtAsunto to set
	 */
	public void setTxtAsunto(String txtAsunto) {
		this.txtAsunto = txtAsunto;
	}


	public void setCuerpoCorreo(String mensaje){
			
		this.cuerpo= new StringBuilder();
//		cuerpo.append(Utilidades.mensajeProperties("etiqueta_sanciones_correo_corresponda") + "\n");
//		cuerpo.append(mensaje);
//		cuerpo.append(Utilidades.mensajeProperties("etiqueta_sanciones_correo_saludo"));
		
		cuerpo.append("A quien corresponda" + "\n");
		cuerpo.append(mensaje);
		cuerpo.append("Saludos");
	}

	/**
	 * MÃ©todo que contruye el cuerpo del correo
	 * 
	 * @author Monserratt LÃ³pez
	 * @since: 10/17
	 * @param String numeroResolucion
	 * 		  String sujetoObligado
	 * 		  Integer motivoCorrreo 1: Registro de sanciÃ³n, 2:ActualizaciÃ³n de sanciÃ³n
	 * **/
	public CorreoAcuerdos(DTOPuntosAcuerdo punto,  Integer motivoCorrreo, List<DTOCClasificaciones> clasificaciones,List<DTOResponsables> responsables, DTOAcuerdos acuerdo, List<DTOCAreas> seleccionados, boolean responsabilidaConjunta) {
		super();
		this.asunto=new StringBuilder();
		this.cuerpo = new StringBuilder();		
		
		if(motivoCorrreo.equals(1)){	//asignacion de un area a un punto
				String notifi =  punto.getNotificacion()==1? "S&iacute;" : "No";		
				String respConjunta = responsabilidaConjunta ? "S&iacute;" : "No";
				String clasific = "";
				for (DTOCClasificaciones clasificacion : clasificaciones) {
					if(clasificacion.getIdClasificacion().equals(punto.getIdClasificacion())){
						clasific = clasificacion.getDescripcion();
					}
				}				 	
				        String plantilla = "<html><head><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body><div style ='height:100px; background-color:#AB2962;'><img src=\'cid:image\' style= 'margin-top: 10px; margin-left:60px;'></div><br/>"
						+ "<div style ='margin-left: 80px;'><h4>Estimado usuario:</h4><div style ='height:7px; width:180px; background-color:#AB2962; margin-top: -20px;'></div>"
						+ "<br/><div>Te informamos que la Direcci&oacute;n del Secretariado ha asignado un Punto de Acuerdo/Resoluci&oacute;n para tu seguimiento. A continuaci&oacute;n, te presentamos el resumen de la informaci&oacute;n:"
						+ "</div></div><br/><br/><br/><div style='margin: 0 auto; width:70%; border: 2px solid #AAAAAA; border-radius: 15px;'>"
						+ "<div style='margin: 10 auto; margin-top:10px; margin-botton:10px; margin-left: 10px; margin-right:10px; line-height: 20px;'><div style='float:left; font-weight: bold;'>"
						+ "Nombre del Acuerdo o Resoluci&oacute;n: &nbsp;</div>"
						+ "<div style='float:left;'>"+ punto.getId().getIdNumAcuerdo() +"</div><br/><div style='float:left; font-weight: bold;'>Identificador del punto: &nbsp;</div>"
						+ "<div style='float:left;'>"+ replaceAcentos( punto.getNumeraliaTexto() ) +"</div><br/><div style='float:left; font-weight: bold;'>Texto del punto: &nbsp;</div><div style='float:left;'>"
						+ replaceAcentos( limpiarTextoxPunto(punto.getTextoPunto()) )
						+ "</div><br/><div style='float:left; font-weight: bold;'>Clasificaci&oacute;n: &nbsp;</div><div style='float:left;'>"+replaceAcentos( clasific )+"</div>"
						+ "<br/><div style='float:left; font-weight: bold;'>Involucra una notificaci&oacute;n: &nbsp;"
						+ "</div><div style='float:left;'>"+ notifi +"</div><br/><div style='float:left; font-weight: bold;'>Responsabilidad conjunta: &nbsp;</div><div style='float:left;'>"+respConjunta+"</div><br/>"
						+ "<div style='font-weight: bold;'>Responsables asignados: </div><br/>"
						+ "<div style ='margin-left: 80px;'>";
				
							for(DTOCAreas area: seleccionados){
								plantilla +=replaceAcentos( area.getDescripcion() )+"<br/>";
							}
						
							plantilla += "</div></div></div><br/><br/><div style ='margin-left: 80px;'>"
									  + "Ingresa a la Bandeja de Seguimiento del Sistema de Acuerdos para dar continuidad a las actividades del Punto.</div><br/><br/>"
									  + "<div style='text-align: center;'>Atentamente<br/>Direcci&oacute;n del Secretariado</div><br/><br/><div style='text-align: center;  color: #D5007F;'>"
									  + "<h2>Contigo, M&eacute;xico es m&aacute;s. S&uacute;mate.</h2></div><br/><div style='text-align: center;'>"
									  + "Para mayor informaci&oacute;n ingresa al Sistema de Acuerdos o dir&iacute;gete a la Direcci&oacute;n del Secretariado.</div></body></html>";
													
										asunto.append("Sistema de Acuerdos: Punto de Acuerdo/Resolución asignado.");		
										cuerpo.append(plantilla);
									
		}
		if(motivoCorrreo.equals(2)){//eliminacion de un area del punto
			String plantilla ="<html><head><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body><div style ='height:100px; background-color:#AB2962;'>"
					+ "<img src=\'cid:image\' style= 'margin-top: 10px; margin-left:60px;'></div><br/><div style ='margin-left: 80px;'>"
					+ "<h4>Estimado usuario:</h4><div style ='height:7px; width:180px; background-color:#AB2962; margin-top: -20px;'> </div>"
					+ "<br/></br></br></br><div>Te informamos que has sido eliminado del seguimiento del punto que ten&iacute;as asignado como responsable.</br></br></br></div></div></br></br></br>"
					+ "</div></div><br/><br/><br/><div style='margin: 0 auto; width:70%; border: 2px solid #AAAAAA; border-radius: 15px;'>"
					+ "<div style='margin: 10 auto; margin-top:10px; margin-botton:10px; margin-left: 10px; margin-right:10px; line-height: 20px;'><div style='float:left; font-weight: bold;'>"
					+ "Nombre del Acuerdo o Resoluci&oacute;n: &nbsp;</div>"
					+ "<div style='float:left;'>"+ punto.getId().getIdNumAcuerdo() +"</div><br/><div style='float:left; font-weight: bold;'>Identificador del punto: &nbsp;</div>"
					+ "<div style='float:left;'>"+ replaceAcentos( punto.getNumeraliaTexto() )+"</div><br/><div style='float:left; font-weight: bold;'>Texto del punto: &nbsp;</div><div style='float:left;'>"
					+ replaceAcentos( limpiarTextoxPunto(punto.getTextoPunto()))
					+ "</div><br/>"
					+ "<br/>"
					+ "</div></div><br/><br/>"
					+ "</br></br><div style='text-align: center;'>Atentamente</br>Direcci&oacute;n del Secretariado</div></br></br>"
					+ "<div style='text-align: center;  color: pink;'><h2>Contigo, M&eacute;xico es m&aacute;s. S&uacute;mate.</h2></div></br>"
					+ "<div style='text-align: center;'>Para mayor informaci&oacute;n ingresa al Sistema de Acuerdos o dir&iacute;gete a la Direcci&oacute;n del Secretariado."
					+ "</div></body></html>";
			
			asunto.append("Sistema de Acuerdos: Desasignación de un punto.");		
			cuerpo.append(plantilla);
		}
		if(motivoCorrreo.equals(3)){//modificacion de un punto
			String notifi =  punto.getNotificacion()==1? "S&iacute;" : "No";		
			String respConjunta = responsabilidaConjunta ? "S&iacute;" : "No";
			String clasific = "";
			for (DTOCClasificaciones clasificacion : clasificaciones) {
				if(clasificacion.getIdClasificacion().equals(punto.getIdClasificacion())){
					clasific = clasificacion.getDescripcion();
				}
			} 	
			        String plantilla = "<html><head><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body><div style ='height:100px; background-color:#AB2962;'><img src=\'cid:image\' style= 'margin-top: 10px; margin-left:60px;'></div><br/>"
					+ "<div style ='margin-left: 80px;'><h4>Estimado usuario:</h4><div style ='height:7px; width:180px; background-color:#AB2962; margin-top: -20px;'></div>"
					+ "<br/><div>Te informamos que se ha modificado un Punto de Acuerdo/Resoluci&oacute;n al que te encuentras asociado. A continuaci&oacute;n, te presentamos el resumen de la informaci&oacute;n:"
					+ "</div></div><br/><br/><br/><div style='margin: 0 auto; width:70%; border: 2px solid #AAAAAA; border-radius: 15px;'>"
					+ "<div style='margin: 10 auto; margin-top:10px; margin-botton:10px; margin-left: 10px; margin-right:10px; line-height: 20px;'><div style='float:left; font-weight: bold;'>"
					+ "Nombre del Acuerdo o Resoluci&oacute;n: &nbsp;</div>"
					+ "<div style='float:left;'>"+ punto.getId().getIdNumAcuerdo() +"</div><br/><div style='float:left; font-weight: bold;'>Identificador del punto: &nbsp;</div>"
					+ "<div style='float:left;'>"+  replaceAcentos( punto.getNumeraliaTexto() ) +"</div><br/><div style='float:left; font-weight: bold;'>Texto del punto: &nbsp;</div><div style='float:left;'>"
					+ replaceAcentos( limpiarTextoxPunto(punto.getTextoPunto()) )
					+ "</div><br/><div style='float:left; font-weight: bold;'>Clasificaci&oacute;n: &nbsp;</div><div style='float:left;'>"+replaceAcentos( clasific ) +"</div>"
					+ "<br/><div style='float:left; font-weight: bold;'>Involucra una notificaci&oacute;n: &nbsp;"
					+ "</div><div style='float:left;'>"+ notifi +"</div><br/><div style='float:left; font-weight: bold;'>Responsabilidad conjunta: &nbsp;</div><div style='float:left;'>"+respConjunta+"</div><br/>"
					+ "<div style='font-weight: bold;'>Responsables asignados: </div><br/>"
					+ "<div style ='margin-left: 80px;'>";
			
						for(DTOCAreas area: seleccionados){
							plantilla +=replaceAcentos( area.getDescripcion() )+"<br/>";
						}
					
						plantilla += "</div></div></div><br/><br/><div style ='margin-left: 80px;'>"
								  + "Para conocer los detalles, ingresa a la Bandeja de Seguimiento y consulta el Punto de Acuerdo/Resoluci&oacute;n.</div><br/><br/>"
								  + "<div style='text-align: center;'>Atentamente<br/>Direcci&oacute;n del Secretariado</div><br/><br/><div style='text-align: center;  color: #D5007F;'>"
								  + "<h2>Contigo, M&eacute;xico es m&aacute;s. S&uacute;mate.</h2></div><br/><div style='text-align: center;'>"
								  + "Para mayor informaci&oacute;n ingresa al Sistema de Acuerdos o dir&iacute;gete a la Direcci&oacute;n del Secretariado.</div></body></html>";
									
			asunto.append("Sistema de Acuerdos: Punto de Acuerdo/Resolución modificado.");		
			cuerpo.append(plantilla);
		}
		
	}
	
	/**
	 * MÃ©todo que contruye el cuerpo del correo
	 * 
	 * @author Monserratt LÃ³pez
	 * @since: 10/17
	 * @param String numeroResolucion
	 * 		  String sujetoObligado
	 * 		  Integer motivoCorrreo 1: Registro de sanciÃ³n, 2:ActualizaciÃ³n de sanciÃ³n
	 * **/
	public CorreoAcuerdos(DTOPuntosAcuerdo punto, DTOAcuerdos acuerdo, Integer motivoCorreo, List<DTOTipoSesiones> tipoSesiones){
		super();
		this.asunto=new StringBuilder();
		this.cuerpo = new StringBuilder();	
			if(motivoCorreo.equals(1)){//correo cuando un punto fue eliminado
				
		        String plantilla = "<html><head><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body><div style ='height:100px; background-color:#AB2962;'><img src=\'cid:image\' style= 'margin-top: 10px; margin-left:60px;'></div><br/>"
				+ "<div style ='margin-left: 80px;'><h4>Estimado usuario:</h4><div style ='height:7px; width:180px; background-color:#AB2962; margin-top: -20px;'></div>"
				+ "<br/><div>Te informamos que se ha eliminado un Punto de Acuerdo/Resoluci&oacute;n al que te encuentras asociado."
				+ "</div></div><br/><br/><br/>"
				+ "<div style='margin: 0 auto; width:70%; border: 2px solid #AAAAAA; border-radius: 15px;'>"
				+ "<div style='margin: 10 auto; margin-top:10px; margin-botton:10px; margin-left: 10px; margin-right:10px; line-height: 20px;'>"
				+ "<div style='float:left; font-weight: bold;'>"
				+ "Nombre del Acuerdo o Resoluci&oacute;n: &nbsp;</div>"
				+ "<div style='float:left;'>"+ punto.getId().getIdNumAcuerdo() +"</div><br/><div style='float:left; font-weight: bold;'>Identificador del punto: &nbsp;</div>"
				+ "<div style='float:left;'>"+  replaceAcentos( punto.getNumeraliaTexto() ) +"</div><br/><div style='float:left; font-weight: bold;'>Texto del punto: &nbsp;</div>"
				+ "<div style='float:left;'>"+ replaceAcentos( limpiarTextoxPunto(punto.getTextoPunto()) )+ "</div><br/><br/>"			
				+"</div></div><br/><br/><br/><br/>"
			    + "<div style='text-align: center;'>Atentamente<br/>Direcci&oacute;n del Secretariado</div><br/><br/><div style='text-align: center;  color: #D5007F;'>"
			    + "<h2>Contigo, M&eacute;xico es m&aacute;s. S&uacute;mate.</h2></div><br/><div style='text-align: center;'>"
			    + "Para mayor informaci&oacute;n ingresa al Sistema de Acuerdos o dir&iacute;gete a la Direcci&oacute;n del Secretariado.</div></body></html>";
							
					asunto.append("Sistema de Acuerdos: Punto de Acuerdo/Resolución eliminado.");		
					cuerpo.append(plantilla);				
			}
			
			if(motivoCorreo.equals(2)){//correo cuando un acuerdo fue modificado
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String tipoDocumento, engrose = "";
				String sesion ="";
				for (DTOTipoSesiones tipo : tipoSesiones) {
					if(tipo.getIdTipoSesion().equals(acuerdo.getIdTipoSesion())){
						sesion = tipo.getDescripcion();
						break;
					}
				}
					if(acuerdo.getIdTipoDocumento().equals(1)){
						tipoDocumento = "Acuerdo";	
					}else{
						tipoDocumento = "Resolución";
					}
					if(acuerdo.getEngrose().equals(1)){
						engrose = "S&iacute;";
					}else{
						engrose = "No";
					}
				
					
		        String plantilla = "<html><head><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body><div style ='height:100px; background-color:#AB2962;'><img src=\'cid:image\' style= 'margin-top: 10px; margin-left:60px;'></div><br/>"
				+ "<div style ='margin-left: 80px;'><h4>Estimado usuario:</h4><div style ='height:7px; width:180px; background-color:#AB2962; margin-top: -20px;'></div>"
				+ "<br/><div>Te informamos que se ha modificado un Acuerdo/Resoluci&oacute;n al que te encuentras asociado. A continuaci&oacute;n, te presentamos el resumen de la informaci&oacute;n:"
				+ "</div></div><br/><br/><br/>"
				+ "<div style='margin: 0 auto; width:70%; border: 2px solid #AAAAAA; border-radius: 15px;'>"
				+ "<div style='margin: 10 auto; margin-top:10px; margin-botton:10px; margin-left: 10px; margin-right:10px; line-height: 20px;'>"
				+ "<div style='float:left; font-weight: bold;'>"
				+ "Tipo de Documento: &nbsp;</div>"
				+ "<div style='float:left;'>"+ replaceAcentos( tipoDocumento )+"</div><br/>"
				+ "<div style='float:left; font-weight: bold;'>"
				+ "N&uacute;mero: &nbsp;</div>"
				+ "<div style='float:left;'>"+ acuerdo.getIdNumAcuerdo() +"</div><br/>"
				+ "<div style='float:left; font-weight: bold;'>Nombre: &nbsp;</div>"
				+ "<div style='float:left;'>"+ acuerdo.getNombre() +"</div><br/>"
				+ "<div style='float:left; font-weight: bold;'>Fecha de Sesi&oacute;n: &nbsp;</div>"
				+ "<div style='float:left;'>"+ dateFormat.format(acuerdo.getFechaSesion())+ "</div><br/>"
				+ "<div style='float:left; font-weight: bold;'>Tipo de Sesi&oacute;n: &nbsp;</div>"
				+ "<div style='float:left;'>"+ sesion + "</div><br/>"	
				+ "<div style='float:left; font-weight: bold;'>Liga del Documento: &nbsp;</div>"
				+ "<div style='float:left;'>"+ acuerdo.getLigaAcuerdo()+ "</div><br/>"	
				+ "<div style='float:left; font-weight: bold;'>Cuenta con engrose: &nbsp;</div>"
				+ "<div style='float:left;'>"+ engrose + "</div><br/><br/>"				
				+"</div></div><br/><br/><br/><br/>"
			    + "<div style='text-align: center;'>Atentamente<br/>Direcci&oacute;n del Secretariado</div><br/><br/><div style='text-align: center;  color: #D5007F;'>"
			    + "<h2>Contigo, M&eacute;xico es m&aacute;s. S&uacute;mate.</h2></div><br/><div style='text-align: center;'>"
			    + "Para mayor informaci&oacute;n ingresa al Sistema de Acuerdos o dir&iacute;gete a la Direcci&oacute;n del Secretariado.</div></body></html>";
							
					asunto.append("Sistema de Acuerdos: Acuerdo/Resolución modificado.");		
					cuerpo.append(plantilla);				
			}
			if(motivoCorreo.equals(3)){//correo cuando un Acuerdo fue eliminado
				
		        String plantilla = "<html><head><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body><div style ='height:100px; background-color:#AB2962;'><img src=\'cid:image\' style= 'margin-top: 10px; margin-left:60px;'></div><br/>"
				+ "<div style ='margin-left: 80px;'><h4>Estimado usuario:</h4><div style ='height:7px; width:180px; background-color:#AB2962; margin-top: -20px;'></div>"
				+ "<br/><div>Te informamos que se han eliminado los puntos y el Acuerdo/Resoluci&oacute;n a los que te encontrabas asociados."
				+ "</div></div><br/><br/><br/>"
				+ "<div style='margin: 0 auto; width:70%; border: 2px solid #AAAAAA; border-radius: 15px;'>"
				+ "<div style='margin: 10 auto; margin-top:10px; margin-botton:10px; margin-left: 10px; margin-right:10px; line-height: 20px;'>"
				+ "<div style='float:left; font-weight: bold;'>"
				+ "Nombre del Acuerdo o Resoluci&oacute;n: &nbsp;</div>"
				+ "<div style='float:left;'>"+ punto.getId().getIdNumAcuerdo() +"</div><br/><div style='float:left; font-weight: bold;'>Identificador del punto: &nbsp;</div>"
				+ "<div style='float:left;'>"+  replaceAcentos( punto.getNumeraliaTexto() ) +"</div><br/><div style='float:left; font-weight: bold;'>Texto del punto: &nbsp;</div>"
				+ "<div style='float:left;'>"+ replaceAcentos( limpiarTextoxPunto(punto.getTextoPunto()) )+ "</div><br/><br/>"			
				+"</div></div><br/><br/><br/><br/>"
			    + "<div style='text-align: center;'>Atentamente<br/>Direcci&oacute;n del Secretariado</div><br/><br/><div style='text-align: center;  color: #D5007F;'>"
			    + "<h2>Contigo, M&eacute;xico es m&aacute;s. S&uacute;mate.</h2></div><br/><div style='text-align: center;'>"
			    + "Para mayor informaci&oacute;n ingresa al Sistema de Acuerdos o dir&iacute;gete a la Direcci&oacute;n del Secretariado.</div></body></html>";
							
					asunto.append("Sistema de Acuerdos: Puntos y Acuerdo/Resolución eliminados.");		
					cuerpo.append(plantilla);				
			}
		
	}
	
	public CorreoAcuerdos(HelperConvocatoria helpConvocatoria) {
		super();

		this.asunto = new StringBuilder();
		this.cuerpo = new StringBuilder();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat hourFormat = new SimpleDateFormat("HH:mm");
		String tipoInteg = "";
		String tipoSesion = "";
		String caracter = "";
		String lugar = "";
		String plantilla = "";
		String ordenDelDia = "";

		for(DTOIntegrantesComision integComision : helpConvocatoria.getIntegComision()) {
			for(DTOCTipoIntegComision tipoDeInteg : helpConvocatoria.getTipoIntegComision()) {
				if(integComision.getIdTipoIntegrante().equals(tipoDeInteg.getIdTipoIntegrante())) {
					tipoInteg = replaceAcentos(tipoDeInteg.getDescripcion());
					break;
				}
			}
			break;
		}

		for(DTOTipoSesiones tipoDeSesion : helpConvocatoria.getTiposDeSesiones()) {
			if(helpConvocatoria.getTipoSesion().equals(tipoDeSesion.getIdTipoSesion())) {
				tipoSesion = replaceAcentos(tipoDeSesion.getDescripcion());
				break;
			}
		}

		if(helpConvocatoria.getCaracter() != null) {
			caracter = replaceAcentos(helpConvocatoria.getCaracter());
		}

		if(helpConvocatoria.getLugarConvoc().equals("Otro")) {
			lugar = replaceAcentos(helpConvocatoria.getDescLugar());
		} else {
			lugar = replaceAcentos(helpConvocatoria.getLugarConvoc());
		}

//		for(DTOOrdenesDelDia puntoOrden : helpConvocatoria.getOrdenDelDia()) {
//			ordenDelDia = ordenDelDia + "<tr><td style='font-weight: bold; width: 10%; border-right: 3px solid gray;'>"
//									  + puntoOrden.getId().getNumPunto() + "</td><td>"
//									  + replaceAcentos(puntoOrden.getDescripcionPunto()) + "</td></tr>"
//									  ;
//		}

		plantilla = "<html><head><meta http-equiv='content-type' content='text/html; charset=UTF-8'></head><body><div style='height: 125px; background-color: #ab2962;'>"
				  + "<img src=\'cid:image\' style= 'width: 125px; margin-top: 15px; margin-left:70px;'></div><br />"
				  + "<div style='text-align: justify; padding: 0px 80px 0px 80px;'><h4 style='font-size: 19px; margin-bottom: 5px;'>Estimado usuario:</h4>"
				  + "<div style='height: 7px; width: 200px; background-color: #ab2962;'></div><br /><div style='font-size: 17px; margin-top: 10px;'>"
				  + "Te informamos que has sido <span style='font-weight: bold; color: #ab2962;'>convocado a la sesi&oacute;n</span> del "
				  + replaceAcentos(helpConvocatoria.getComision().getNomComision())
//				  + helpConvocatoria.getDtoResponsable().getNombre() + " " + helpConvocatoria.getDtoResponsable().getApellidos()
//				  + ", "
//				  + tipoInteg
				  + " cuyos detalles se muestran a continuaci&oacute;n:"
				  + "</div></div><br /><br /><div style='text-align: justify; margin: 0 auto; font-size: 17px; width: 75%; padding: 10px 15px 10px 15px;"
				  + "line-height: 25px; border: 3px solid gray; border-radius: 10px;'>"
//				  + "<div><span style='font-weight: bold;'>N&uacute;mero de sesi&oacute;n:&nbsp;&nbsp;</span><span>"
//				  + helpConvocatoria.getNumSesion()
//				  + "</span></div>"
				  + "<div><span style='font-weight: bold;'>Tipo de sesi&oacute;n:&nbsp;&nbsp;</span><span>"
				  + tipoSesion
				  + "</span></div>"
//				  + "<div><span style='font-weight: bold;'>Car&aacute;cter:&nbsp;&nbsp;</span><span>"
//				  + caracter
//				  + "</span></div>"
				  + "<div><span style='font-weight: bold;'>Fecha de sesi&oacute;n:&nbsp;&nbsp;</span><span>"
				  + dateFormat.format(helpConvocatoria.getFechaSesion())
				  + "</span></div><div><span style='font-weight: bold;'>Hora:&nbsp;&nbsp;</span><span>"
				  + hourFormat.format(helpConvocatoria.getHoraSesion()) + " hrs."
				  + "</span></div><div><span style='font-weight: bold;'>Lugar:&nbsp;&nbsp;</span><span>"
				  + lugar
				  + "</span></div><div><span style='font-weight: bold;'>Asunto:&nbsp;&nbsp;</span><span>"
				  + replaceAcentos(helpConvocatoria.getAsuntoRelevante())
				  + "</span></div>"
				  + "<div style='font-size: 15px; text-align: center;'>"
				  + "* Consulta los detalles del Orden del D&iacute;a en el archivo adjunto a este correo *"
				  + "</div></div><br /><br />"
				  + "<div style='text-align: justify; font-size: 17px; padding: 0px 80px 0px 80px;'>"
				  + "Los anexos de los puntos del Orden del D&iacute;a los podr&aacute;s encontrar en el apartado de Convocatoria."
				  + "</div><br /><br />"
//				  + "<div style='text-align: justify; font-size: 17px; padding: 0px 80px 0px 80px;'>"
//				  + "A continuaci&oacute;n los Puntos de la Orden Del D&iacute;a:</div><br /><br /><div style='margin: 0 auto; width: 70%;'>"
//				  + "<table style='text-align: center; font-size: 17px; width: 100%; line-height: 25px; border: 3px solid gray; border-collapse: collapse;'>"
//				  + "<tr><th style='font-size: 20px; border: 3px solid gray;' colspan='2'>Puntos</th></tr>"
//				  + ordenDelDia
//				  + "</table></div><br /><br />"
				  + "<div style='text-align: justify; font-size: 19px; color: #ab2962; padding: 0px 80px 0px 80px;'>"
				  + "Contamos con tu participaci&oacute;n.</div><br /><br /><br /><div style='text-align: center; font-size: 19px; line-height: 25px;'>Atentamente<br />"
				  + replaceAcentos(helpConvocatoria.getComision().getNomComision())
				  + "</div><br /><div style='text-align: center; color: #d5007f;'><h2>Contigo, M&eacute;xico es m&aacute;s. S&uacute;mate.</h2></div><br />"
				  + "<div style='text-align: center; font-size: 19px; margin-bottom: 20px;'>"
				  + "Para mayor informaci&oacute;n ingresa al Sistema de Acuerdos o dir&iacute;gete a la Direcci&oacute;n del Secretariado."
				  + "</div></body></html>"
				  ;

		asunto.append("Sistema de Acuerdos: Convocatoria de Sesión del " + helpConvocatoria.getComision().getNomComision() + ".");
		cuerpo.append(plantilla);
	}

	public String limpiarTextoxPunto(String txtPunto) {
		int pos = txtPunto.indexOf(".-") + 2;
		txtPunto = txtPunto.substring(pos);
		return txtPunto;
	}
	
	public String replaceAcentos(String cadena){
		cadena = cadena.replace("á", "&aacute;");
		cadena = cadena.replace("Á", "&Aacute;");
		
		cadena = cadena.replace("é", "&eacute;");
		cadena = cadena.replace("É", "&Eacute;");	
		
		cadena = cadena.replace("í", "&iacute;");
		cadena = cadena.replace("Í", "&Iacute;");
		
		cadena = cadena.replace("ó", "&oacute;");
		cadena = cadena.replace("Ó", "&Oacute;");
		
		cadena = cadena.replace("ú", "&uacute;");
		cadena = cadena.replace("Ú", "&Uacute;");
		
		cadena = cadena.replace("ñ", "&ntilde;");
		cadena = cadena.replace("Ñ", "&Ntilde;");
				
		
		cadena = cadena.replace("¡", "&iexcl;");
		
		
		cadena = cadena.replace("¿", "&iquest;");
		
	
		
		return cadena;		
	}
	
}
