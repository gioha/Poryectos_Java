package mx.ine.computosINE.dto.form;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.ine.common.fechas.impl.ValidacionFechas;
import mx.ine.computosINE.dto.DTOActaTipoCandidatura;
import mx.ine.computosINE.dto.helper.HLPConsejero;
import mx.ine.computosINE.dto.helper.HLPRepresentante;
import mx.ine.computosINE.util.Utilidades;
import mx.org.ine.servicios.dto.DTOBase;

/**
 * 
 * Clase Form que mapea los campos requeridos para la generación de las
 * distintas Actas.
 * 
 * @author Jean Pierre Pacheco Avila
 * @version 1.0
 * @since 19/04/2017
 * @copyright INE
 *
 */
public class FormGeneracionActas implements Serializable {

	/*
	 * Serial
	 */
	private static final long serialVersionUID = 214706118774950481L;

	private Date fechaHora;

	private String condicionesComputo;

	private String ubicacionComputo;

	private Integer boletasSobrantes;

	private Integer votantesLista;

	private Integer repVotantesNoLista;

	private Integer totalValidosNulos;

	private String coincideSuma;

	private String coincideResultado;

	private String textoActa;

	private List<HLPConsejero> consejeros;

	private List<HLPRepresentante> representantes;

	public FormGeneracionActas() {
		fechaHora = null;
	}

	public void setCampos(DTOBase acta) {

		if (acta instanceof DTOActaTipoCandidatura) {
			setFechaHora(((DTOActaTipoCandidatura) acta).getFechaHoraActa());
			setUbicacionComputo(((DTOActaTipoCandidatura) acta)
					.getUbicacionComputo());

		}
	}

	/**
	 * Obtiene el objeto Calendar equivalente al Date
	 * 
	 * @return
	 */
	public Calendar getCalendar() {
		return ValidacionFechas.dateToCalendar(fechaHora);
	}

	/**
	 * Obtiene las hora de la fecha.
	 * 
	 * @return
	 */
	public String getHoras() {
		Calendar c = getCalendar();
		Integer hora = c.get(Calendar.HOUR);
		if (0 <= hora && hora <= 9) {
			return "0" + hora;
		}
		return hora + "";
	}

	/**
	 * OBtiene los minutos
	 * 
	 * @return
	 */
	public String getMinutos() {
		Calendar c = getCalendar();
		Integer min = c.get(Calendar.MINUTE);
		if (0 <= min && min <= 9) {
			return "0" + min;
		}
		return min + "";
	}

	/**
	 * Nos dice si hora es AM, si no, es PM.
	 * 
	 * @return
	 */
	public boolean hourIsAM() {
		Calendar c = getCalendar();
		Integer time = c.get(Calendar.AM_PM);
		return time.equals(Calendar.AM);
	}

	/**
	 * Obtiene la el dia de la fecha por ejemplo: martes 27 lunes 2 domingo 8
	 * etc.
	 * 
	 * @return
	 */
	public String getDay() {
		Calendar c = getCalendar();
		String dia = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG,
				Utilidades.MEX);
		Integer diaN = c.get(Calendar.DAY_OF_MONTH);
		return dia + " " + diaN;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getCondicionesComputo() {
		return condicionesComputo;
	}

	public void setCondicionesComputo(String condicionesComputo) {
		this.condicionesComputo = condicionesComputo;
	}

	public String getUbicacionComputo() {
		return ubicacionComputo;
	}

	public void setUbicacionComputo(String ubicacionComputo) {
		this.ubicacionComputo = ubicacionComputo;
	}

	public Integer getBoletasSobrantes() {
		return boletasSobrantes;
	}

	public void setBoletasSobrantes(Integer boletasSobrantes) {
		this.boletasSobrantes = boletasSobrantes;
	}

	public Integer getVotantesLista() {
		return votantesLista;
	}

	public void setVotantesLista(Integer votantesLista) {
		this.votantesLista = votantesLista;
	}

	public Integer getRepVotantesNoLista() {
		return repVotantesNoLista;
	}

	public void setRepVotantesNoLista(Integer repVotantesNoLista) {
		this.repVotantesNoLista = repVotantesNoLista;
	}

	public Integer getTotalValidosNulos() {
		return totalValidosNulos;
	}

	public void setTotalValidosNulos(Integer totalValidosNulos) {
		this.totalValidosNulos = totalValidosNulos;
	}

	public String getCoincideSuma() {
		return coincideSuma;
	}

	public void setCoincideSuma(String coincideSuma) {
		this.coincideSuma = coincideSuma;
	}

	public String getCoincideResultado() {
		return coincideResultado;
	}

	public void setCoincideResultado(String coincideResultado) {
		this.coincideResultado = coincideResultado;
	}

	public List<HLPConsejero> getConsejeros() {
		return consejeros;
	}

	public void setConsejeros(List<HLPConsejero> consejeros) {
		this.consejeros = consejeros;
	}

	public List<HLPRepresentante> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(List<HLPRepresentante> representantes) {
		this.representantes = representantes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTextoActa() {
		return textoActa;
	}

	public void setTextoActa(String textoActa) {
		this.textoActa = textoActa;
	}

}
