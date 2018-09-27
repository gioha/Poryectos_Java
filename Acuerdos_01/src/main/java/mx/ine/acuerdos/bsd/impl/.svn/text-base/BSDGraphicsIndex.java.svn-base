package mx.ine.acuerdos.bsd.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.ine.acuerdos.as.ASBandejaCompromisosInterface;
import mx.ine.acuerdos.as.ASGraphicsIndexInterface;
import mx.ine.acuerdos.bsd.BSDGraphicsIndexInterface;
import mx.ine.acuerdos.dto.DTOAcuerdos;
import mx.ine.acuerdos.dto.DTOPuntosAcuerdo;
import mx.ine.acuerdos.dto.helper.form.HelperFormGraphicsIndex;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("bsdGraphicsIndex")
@Scope("prototype")
public class BSDGraphicsIndex implements BSDGraphicsIndexInterface, Serializable{
	private HelperFormGraphicsIndex form;
	private static final long serialVersionUID = -1105526273813915871L;
	private static Logger log = Logger.getLogger(BSDGraphicsIndex.class);
	
	
	@Autowired
	@Qualifier("asGraphicsIndex")
	public ASGraphicsIndexInterface asGraphicsIndex;
	
	@Autowired
	@Qualifier("asBandejaCompromisos")
	private transient ASBandejaCompromisosInterface asBandejaCompromisos;
	
	public HelperFormGraphicsIndex getAlgo() {
		HelperFormGraphicsIndex helper = new HelperFormGraphicsIndex();
		return helper;
		}
	
	/*@Override
	public HashMap<String, Integer> acuerdos(int tipoDocumento) throws Exception {
		List<DTOAcuerdos> acuerdos= asGraphicsIndex.AcuerdosAnio(tipoDocumento);
		List<DTOPuntosAcuerdo> puntosAcuerdo;
		int pendiente =0;
		int parcialmente = 0;
		int cumplido =0;
		for(DTOAcuerdos acuerdo: acuerdos) {
			puntosAcuerdo = new ArrayList<DTOPuntosAcuerdo>();
			puntosAcuerdo = asGraphicsIndex.consultaPuntosAcuerdo(acuerdo.getIdNumAcuerdo());
			log.info("Acuerdo " + acuerdo.getIdNumAcuerdo());
			for(DTOPuntosAcuerdo pa: puntosAcuerdo) {
				log.info("Puntos Acuerdo Estatus Global " + pa.getEstatusGlobal());
				if(pa.getEstatusGlobal().equals("Pendiente")) {
					pendiente++;
				}else if(pa.getEstatusGlobal().equals("Cumplido Parcialmente")) {
					parcialmente++;
				}else if(pa.getEstatusGlobal().equals("Cumplido")) {
					cumplido++;
				}
				
			}
			
		}
		
		log.info("Pendiente" + pendiente);
		log.info("Parcialmente" + parcialmente);
		log.info("cumplido" + cumplido);
		HashMap<String, Integer> params = new HashMap<String, Integer>();
		params.put("pendiente", pendiente);
		params.put("parcialmente", parcialmente);
		params.put("cumplido", cumplido);
		return params;
	}*/
	
	
	@Override
	public HashMap<String, Integer> acuerdosPuntosEstatus(int tipoDocumento)throws Exception {
		
		int pendiente =0;
		int parcialmente = 0;
		int cumplido =0;
		
		
		//Recuperamos el total de acuerdos o resoluciones
		List<DTOAcuerdos> documentos = asBandejaCompromisos.recuperaAcuerdos( new Integer(tipoDocumento), new Integer(1) );		// ROQUE TEMPORAL (CORREGIR)
		
		
		if (  documentos!= null &&  documentos.size() > 0 ) {
			
			for (DTOAcuerdos doc : documentos) {
				
				List<DTOPuntosAcuerdo>  puntos = new ArrayList<DTOPuntosAcuerdo>();
				puntos = asBandejaCompromisos.recuperaPuntosAcuerdos( doc.getIdNumAcuerdo() );
				
				if ( puntos!= null && puntos.size() > 0 ) {
					
					for (DTOPuntosAcuerdo p : puntos) {
						
						if( p.getEstatusGlobal().equalsIgnoreCase("Pendiente") )
							pendiente ++;
						
						else  if ( p.getEstatusGlobal().equalsIgnoreCase("Cumplido Parcialmente") )
							parcialmente ++;
						
						else if ( p.getEstatusGlobal().equalsIgnoreCase("Cumplido") )
							cumplido ++;
					}
					
					
				}
				
			}
			
		}
		

		
		
		
		
		
		
		
		
//		int pendiente =0;
//		int parcialmente = 0;
//		int cumplido =0;
//		List<DTOGraphicAcuerdos> acuerdos = new ArrayList<DTOGraphicAcuerdos>();
//		acuerdos =  asGraphicsIndex.acuerdosPuntosEstatus(tipoDocumento);
//		HashSet<String> listaAcuerdos = new HashSet<String>();
//		
//		for(DTOGraphicAcuerdos aux: acuerdos) {
//			listaAcuerdos.add(aux.getIdNumAcuerdo());
//		}
//		
//		int pendienteInterior=0;
//		int cumplidoInterior =0;
//		int numAcuerdosInterior=0;		
//		for(String aux: listaAcuerdos) {
//				for(DTOGraphicAcuerdos  acuerdo: acuerdos) {
//					if(aux.equals(acuerdo.getIdNumAcuerdo())) {
//						if(acuerdo.getIdEstatusPunto()==1) {
//							pendienteInterior++;
//						}else if(acuerdo.getIdEstatusPunto()==7) {
//							cumplidoInterior++;
//						}
//						numAcuerdosInterior++;
//					}
//				}			
//			if(numAcuerdosInterior==pendienteInterior) {
//				pendiente++;
//			}else if(numAcuerdosInterior==cumplidoInterior) {
//				cumplido++;
//			}else {
//				parcialmente++;
//			}		
//			pendienteInterior=0;
//			cumplidoInterior=0;
//			numAcuerdosInterior=0;
//		}
		
		HashMap<String, Integer> params = new HashMap<String, Integer>();
		params.put("pendiente", pendiente);
		params.put("parcialmente", parcialmente);
		params.put("cumplido", cumplido);
		return params;
	}

}
