package mx.ine.gestion.seguridad;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

import mx.org.ine.servicios.utils.ApplicationContextUtils;

import org.jboss.logging.Logger;

import seguridata.segurisign.service.SeguriSignService;

@WebServiceClient(name="SeguriSignService", targetNamespace="http://service.segurisign.seguridata/")
public class SeguriSignService_Service
  extends Service
{
  private static final Logger log = Logger.getLogger(SeguriSignService_Service.class);
  private static final URL SEGURISIGNSERVICE_WSDL_LOCATION;
  private static final WebServiceException SEGURISIGNSERVICE_EXCEPTION;
  private static final QName SEGURISIGNSERVICE_QNAME = new QName("http://service.segurisign.seguridata/", "SeguriSignService");
  private static String ruta = (String) ApplicationContextUtils.getApplicationContext().getBean("seguriSign");
  //private static String ruta = "http://10.0.28.156:8080/WS_SeguriSign/SeguriSign?wsdl";
  
  static
  { 
    URL url = null;
    WebServiceException e = null;
    try
    {
      url = new URL(ruta);
    }
    catch (MalformedURLException ex)
    {
      e = new WebServiceException(ex);
    }catch (Throwable t){
    	log.error("SeguriSignServiceImpl:: ",t);
    }
    SEGURISIGNSERVICE_WSDL_LOCATION = url;
    SEGURISIGNSERVICE_EXCEPTION = e;
  }
  
  public SeguriSignService_Service()
  {
    super(__getWsdlLocation(), SEGURISIGNSERVICE_QNAME);
  }
  
  public SeguriSignService_Service(WebServiceFeature... features)
  {
    super(__getWsdlLocation(), SEGURISIGNSERVICE_QNAME, features);
  }
  
  public SeguriSignService_Service(URL wsdlLocation)
  {
    super(wsdlLocation, SEGURISIGNSERVICE_QNAME);
  }
  
  public SeguriSignService_Service(URL wsdlLocation, WebServiceFeature... features)
  {
    super(wsdlLocation, SEGURISIGNSERVICE_QNAME, features);
  }
  
  public SeguriSignService_Service(URL wsdlLocation, QName serviceName)
  {
    super(wsdlLocation, serviceName);
  }
  
  public SeguriSignService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features)
  {
    super(wsdlLocation, serviceName, features);
  }
  
  @WebEndpoint(name="SeguriSignServicePort")
  public SeguriSignService getSeguriSignServicePort()
  {
    return (SeguriSignService)super.getPort(new QName("http://service.segurisign.seguridata/", "SeguriSignServicePort"), SeguriSignService.class);
  }
  
  @WebEndpoint(name="SeguriSignServicePort")
  public SeguriSignService getSeguriSignServicePort(WebServiceFeature... features)
  {
    return (SeguriSignService)super.getPort(new QName("http://service.segurisign.seguridata/", "SeguriSignServicePort"), SeguriSignService.class, features);
  }
  
  private static URL __getWsdlLocation()
  {
    if (SEGURISIGNSERVICE_EXCEPTION != null) {
      throw SEGURISIGNSERVICE_EXCEPTION;
    }
    return SEGURISIGNSERVICE_WSDL_LOCATION;
  }
  
  /**
	 * @return the ruta
	 */
	public static String getRuta() {
		return ruta;
	}

	/**
	 * @param ruta the ruta to set
	 */
	public static void setRuta(String ruta) {
		SeguriSignService_Service.ruta = ruta;
	}
}
