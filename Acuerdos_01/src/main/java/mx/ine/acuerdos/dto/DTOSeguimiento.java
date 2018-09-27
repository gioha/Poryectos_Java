package mx.ine.acuerdos.dto;
import java.io.Serializable;
/**
 * DTOCEstatusPuntos para llenar las tablas de la base de datos
 * @author Angel Omar Medel Hernandez
 * @since 23/10/2017
 * **/
import java.util.Date;

/**
 * @author INE
 *
 */
public class DTOSeguimiento implements Serializable {

	private static final long serialVersionUID = 783155421072334276L;
	private String anio;
	private String estatus;
	private String area;
	private String tematica;
	private String acuerdo;
	private String punto;
	private Date fecha;
	private String nombreAcuerdo;
	private String textoPunto;
	private String areaSiglas;
	private int idTipoDocumento;
	private String documentoTipo;
	private String movimientosHistorico;
	private String fechaEmision; //Se ocupa para  la fecha de la sesion
	private String tipoSesion;
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getTematica() {
		return tematica;
	}
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}
	public String getAcuerdo() {
		return acuerdo;
	}
	public void setAcuerdo(String acuerdo) {
		this.acuerdo = acuerdo;
	}
	public String getPunto() {
		return punto;
	}
	public void setPunto(String punto) {
		this.punto = punto;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombreAcuerdo() {
		return nombreAcuerdo;
	}
	public void setNombreAcuerdo(String nombreAcuerdo) {
		this.nombreAcuerdo = nombreAcuerdo;
	}
	public String getTextoPunto() {
		return textoPunto;
	}
	public void setTextoPunto(String textoPunto) {
		this.textoPunto = textoPunto;
	}
	public String getAreaSiglas() {
		return areaSiglas;
	}
	public void setAreaSiglas(String areaSiglas) {
		this.areaSiglas = areaSiglas;
	}
	public int getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(int idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public String getDocumentoTipo() {
		return documentoTipo;
	}
	public void setDocumentoTipo(String documentoTipo) {
		this.documentoTipo = documentoTipo;
	}
	public String getMovimientosHistorico() {
		return movimientosHistorico;
	}
	public void setMovimientosHistorico(String movimientosHistorico) {
		this.movimientosHistorico = movimientosHistorico;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getTipoSesion() {
		return tipoSesion;
	}
	public void setTipoSesion(String tipoSesion) {
		this.tipoSesion = tipoSesion;
	}
	
	

}
