package mx.ine.acuerdos.dto.helper;

import java.io.Serializable;

public class HelperReporteSeguimiento implements Serializable {
	private static final long serialVersionUID = -4675733855672268616L;
	private String header;
	private String property;
	
	public HelperReporteSeguimiento(String header, String property) {
		this.header=header;
		this.property=property;		
	}
	
	public String getHeader() {
		return header;
	}
	
	public String getProperty() {
		return property;
	}

}
