
package mx.ine.acuerdos.mb;

import java.io.Serializable;
import java.util.ArrayList;

import mx.ine.acuerdos.bo.BOArchivosInterface;
import mx.ine.acuerdos.bsd.BSDConsultaConvocatoriaInterface;
import mx.ine.acuerdos.dto.DTOConvocatorias;
import mx.ine.acuerdos.dto.DTOTipoSesiones;
import mx.ine.acuerdos.dto.helper.HelperDTOMesesAnio;
import mx.ine.acuerdos.dto.helper.form.HelperConvocatoria;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MBConsultaConvocatoria extends MBGeneric implements Serializable {
	private static final long serialVersionUID = -752728846314329674L;

	@Autowired
	@Qualifier("bsdConsultaConvoc")
	private transient BSDConsultaConvocatoriaInterface bsdConsultaConvoc;

	@Autowired
	@Qualifier("boArchivosAcuerdos")
	private BOArchivosInterface boArchivos;

	private HelperConvocatoria helpConvocatoria;

	public void init() {
		helpConvocatoria = new HelperConvocatoria();
		helpConvocatoria.setListaConvocatorias( bsdConsultaConvoc.recuperarConvocatorias() );
		helpConvocatoria.setListaAnios( bsdConsultaConvoc.determinarListaAnios(helpConvocatoria.getListaConvocatorias()) );
		helpConvocatoria.setTiposDeSesiones( bsdConsultaConvoc.recuperarTiposDeSesiones() );
	}

	public HelperConvocatoria getHelpConvocatoria() {
		return helpConvocatoria;
	}

	public boolean esRolValido() {
		return bsdConsultaConvoc.validarRolUsuario(obtenUsuario().getRolUsuario());
	}

	public boolean esCaptura() {
		return bsdConsultaConvoc.validarRolCapConsultaUsuario(obtenUsuario().getRolUsuario());
	}

	public void recuperarConvocatorias(Integer tipoSesion) {
		helpConvocatoria.setConvocatoria(null);
		helpConvocatoria.setListaConvocFiltro( bsdConsultaConvoc.recuperarConvocatoriasPorTipo(helpConvocatoria, tipoSesion) );
	}

	public void seleccionarConvocatoria(SelectEvent event) {
		helpConvocatoria.setConvocatoria( (DTOConvocatorias) event.getObject() );
		helpConvocatoria.setIdComision( helpConvocatoria.getConvocatoria().getId().getIdComision() );
		helpConvocatoria.setComision(bsdConsultaConvoc.recuperarComision( helpConvocatoria.getIdComision() ));		
		helpConvocatoria.setComisionesUnidas( bsdConsultaConvoc.recuperarComisionesUnidas(helpConvocatoria.getIdComision()) );
		helpConvocatoria.setComisionesFinal( bsdConsultaConvoc.determinarComisionesFinal(helpConvocatoria) );
		helpConvocatoria.setIntegComision( bsdConsultaConvoc.determinarIntegComision(helpConvocatoria.getComisionesFinal()) );
		helpConvocatoria.setTipoIntegComision( bsdConsultaConvoc.recuperarTipoIntegComision() );
		helpConvocatoria.setResponsComision( bsdConsultaConvoc.recuperarResponsComision(helpConvocatoria.getIntegComision()) );
		helpConvocatoria.setArbolComisiones( bsdConsultaConvoc.construirArbolComisiones(helpConvocatoria) );
//		helpConvocatoria.setOrdenDelDia( bsdConsultaConvoc.recuperarOrdenDelDia( helpConvocatoria.getConvocatoria().getId()) );
	}

	public String getDescTipoDeSesion(Integer tipoSesion) {
		return bsdConsultaConvoc.recuperarDescTipoDeSesion(helpConvocatoria, tipoSesion);
	}

	public void determinarMesesAnio(Integer idAnio) {
		helpConvocatoria.setListaMesesAnio(new ArrayList<HelperDTOMesesAnio>());
		helpConvocatoria.setMes(null);
		helpConvocatoria.setTiposDeSesionesAux(new ArrayList<DTOTipoSesiones>());
		helpConvocatoria.setTipoSesion(null);
		helpConvocatoria.setListaConvocFiltro(new ArrayList<DTOConvocatorias>());
		helpConvocatoria.setConvocatoria(null);
		helpConvocatoria.setListaMesesAnio( bsdConsultaConvoc.determinarMesesAnio(helpConvocatoria, idAnio) );
	}

	public void determinarTiposSesion(Integer mes) {
		helpConvocatoria.setTiposDeSesionesAux(new ArrayList<DTOTipoSesiones>());
		helpConvocatoria.setTipoSesion(null);
		helpConvocatoria.setListaConvocFiltro(new ArrayList<DTOConvocatorias>());
		helpConvocatoria.setConvocatoria(null);
		helpConvocatoria.setTiposDeSesionesAux( bsdConsultaConvoc.determinarTiposSesion(helpConvocatoria, mes) );
	}

	public String recuperarNombreOrdenDia(String rutaArchivo) {
		return boArchivos.getNombreArchivo(rutaArchivo);
	}

	public String recuperarRutaVisualizacion(String rutaArchivo) {
		return boArchivos.getRutaPrevisualizacion(rutaArchivo);
	}

}
