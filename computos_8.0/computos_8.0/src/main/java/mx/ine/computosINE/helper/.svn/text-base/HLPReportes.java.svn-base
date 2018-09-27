package mx.ine.computosINE.helper;

import mx.ine.common.util.Constantes;
import mx.ine.common.ws.api.client.ClientWS;
import mx.ine.common.ws.api.exception.ClienteWebServiceException;
import mx.ine.common.ws.api.request.JsonRequestBody;
import mx.ine.common.ws.api.request.Request;
import mx.ine.common.ws.api.request.RequestBody;
import mx.ine.common.ws.api.response.JsonResponseBody;
import mx.ine.common.ws.api.response.Response;
import mx.ine.common.ws.api.response.ResponseBody;
import mx.ine.computosINE.dto.reportes.DTOCandidatosIndependientesWS;
import mx.ine.computosINE.dto.reportes.DTOParametrosEntradaWS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("hlpReportes")
@Scope("prototype")
public class HLPReportes extends ClientWS {

	@Autowired
	@Qualifier("urlRestWs")
	protected String urlRestWs;

	public DTOCandidatosIndependientesWS consultaNombreCandidatos(
			DTOParametrosEntradaWS parametros)
			throws ClienteWebServiceException {
		
		//URL DEL SERVICIO
		String urlWS = urlRestWs + "ComputosINEReportes/servicios/reportes/consultaCandidatosIndependientes";		
		//String urlWS = "http://localhost:8080/ComputosINEReportes/servicios/reportes/consultaCandidatosIndependientes";


		// Se define el cuerpo de la peticion de Webservice
		RequestBody requestBody = new JsonRequestBody.RequestBodyBuilder(
				jsonParser).body(parametros).build();
		ResponseBody.Builder<?> responseBuilder = new JsonResponseBody.ResponseBodyBuilder(
				jsonParser);

		// Se define la peticion de Webservice
		Request.Builder requestBuilder = new Request.Builder().url(urlWS)
				.responseBodyBuilder(responseBuilder).post(requestBody);
		Request request = requestBuilder.build();

		// Se ejecuta webservice
		Response response = webserviceClient.newCall(request).execute();

		// Se procesa respuesta del webservice
		if (response.isSuccessful()) {
            JsonResponseBody responseBody = (JsonResponseBody) response.body();
            if (responseBody.body() != null) {
            	DTOCandidatosIndependientesWS c = 
            			responseBody.objectBody(DTOCandidatosIndependientesWS.class);
                return c;
            } else {
                throw new ClienteWebServiceException(Constantes.MSG_SIN_RESPUESTA_WS);
            }
        } else {
            throw new ClienteWebServiceException(Constantes.MSG_SIN_RESPUESTA_WS);
        }
	}
}
