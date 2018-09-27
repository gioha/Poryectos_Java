//<!--On/Wcapi-->

		var privateKeyBuffer = new ArrayBuffer(0); // ArrayBuffer with loaded private key
		var certificateBuffer = new ArrayBuffer(0); // ArrayBuffer with loaded certificate
		
		/**
		* Función para procesar la llave al ser seleccionado en pantalla.
		*/
		function handleParsingPrivKeyFile(evt)
        {
            var temp_reader = new FileReader();

            var current_files = evt.target.files;

	            temp_reader.onload =
	            function(event)
	            {
	                privateKeyBuffer = event.target.result;
	               
	            };
            
            temp_reader.readAsArrayBuffer(current_files[0]);
        }
		
		/**
		* Función para procesar el certificado al ser seleccionado en pantalla.
		*/
		function handleParsingCertFile(evt)
        {
            var temp_reader = new FileReader();

            var current_files = evt.target.files;
            
	            temp_reader.onload =
	            function(event)
	            {
	                certificateBuffer = event.target.result;
	      			
	            };

            temp_reader.readAsArrayBuffer(current_files[0]);
        }
		
		function validarCamposNecesariosFirma(idFormulario, banderaCertificado){
			
			var certificado = document.getElementById(idFormulario + ':certificadoFile');
			var certificadoHidden = document.getElementById(idFormulario + ':certificadoHidden');
			var llavePrivada = document.getElementById(idFormulario + ':llaveFile');
			var contrasenia = document.getElementById(idFormulario + ':contraseniaText');
		
			if(!isWCAPISupported())
				return;
			
			
			//Validamos las variables de la firma
			
			var mensajeErrorFaltanCampos = '';
	
			if (certificateBuffer.byteLength == 0) {
				mensajeErrorFaltanCampos += "Certificado ";
			}
			
			if (privateKeyBuffer.byteLength == 0) {
				mensajeErrorFaltanCampos += "Llave privada ";
			}

			if (contrasenia == null || contrasenia.value == '') {
				mensajeErrorFaltanCampos += "Contrase\u00f1a ";
			}

			if (mensajeErrorFaltanCampos != '') {
				
				var mensajeBase = "Favor de ingresar los datos: ";
				
				PF('msgWarning').renderMessage({"summary": mensajeBase + mensajeErrorFaltanCampos,
												"detail":"",
												"severity":"warn"});
		
				return false;
			}
			
			//Validamos extensiones del certificado y llave primaria
			var mensajeErrorValoresIncorrectos = '';

			//Checar con Roberto
			/*if (certificado != null && certificado.value != '' && certificado.value.substring(certificado.value.lastIndexOf(".")) != ".cer") {
				mensajeErrorValoresIncorrectos += " - Certificado </br>";
			}*/
			
			if (banderaCertificado == 1) {
				mensajeErrorValoresIncorrectos += " - Certificado </br>";
			}
			
			if (llavePrivada != null && llavePrivada.textContent != '' && llavePrivada.textContent.substring(llavePrivada.textContent.lastIndexOf(".")) != ".key") {
				mensajeErrorValoresIncorrectos += " - Llave privada </br>";
			
			}

			if (mensajeErrorValoresIncorrectos != '') {
				
				var mensajeBase = "Los siguientes datos son inv\u00E1lidos y se necesita seleccionar los correctos: </br>";
				
				PF('msgWarning').renderMessage({"summary": mensajeBase + mensajeErrorValoresIncorrectos,
												"detail":"",
												"severity":"warn"});
				
				return false;
			}
			
			certificadoHidden=certificado.value;
			
			return true;
		}
	
		function firmarArchivos(idFormulario, banderaCertificado, algoritmo) {
			
		
			//Obtenemos la información de los campos
			var contrasenia = document.getElementById(idFormulario + ":contraseniaText").value;
			var numeroArchivos = document.getElementById(idFormulario + ":numeroArchivosAFirmarHidden").value;
			var arregloOTs = [];

			//Generamos un arreglo por el número de archivos que se van a firmar
			for (var indice = 0; indice < numeroArchivos; indice++) {
				arregloOTs[indice] = indice;
			}

			if(!isWCAPISupported())
				return;
			
			if(!validarCamposNecesariosFirma(idFormulario, banderaCertificado)) {
		
				//PF('bloquearContenido').unblock();
				return false;
			} 
			
			var hashAlgorithm;
			var hashOption = algoritmo;//document.getElementById("hashAlg").value; //Probando con el agoritmo SHA1
			switch(hashOption)
			{
				case "SHA1":
					hashAlgorithm = 2;
					break;
				case "SHA-256":
					hashAlgorithm = 3;
					break;
				case "SHA-384":
					hashAlgorithm = 4;
					break;
				case "SHA-512":
					hashAlgorithm = 5;
					break;
				default:;
			}
			
				var cipheredKey;
				var privateKeyBufferString = arrayBufferToString(privateKeyBuffer);
				var pKey = privateKeyBufferString.replace(/(-----(BEGIN|END) PRIVATE KEY-----|\r\n)/g, '');
				
				if(pKey.charAt(0) === "M") {
					cipheredKey = window.atob(pKey);
				}
				else {
					cipheredKey = privateKeyBufferString;
				}
				
				var certX509;
				var certificateBufferString = arrayBufferToString(certificateBuffer);
				var pCert = certificateBufferString.replace(/(-----(BEGIN|END) CERTIFICATE-----|\r\n)/g, '');
				
				if(pCert.charAt(0) === "M") {
					certX509 = window.atob(pCert);
				}
				else {
					certX509 = certificateBufferString;
				}
			
				try {
					//Getting password and data to sign
					var password = document.getElementById(idFormulario + ":contraseniaText").value;
					
					//Aquí va el for
					$(arregloOTs).each(function(indiceEach) {
					
					var hashToSign = document.getElementById(idFormulario + ":hashCertificadoHidden_" + indiceEach).value;					
					
					// Signing hash
					if (window.Promise) {
						
						var signPromise = pkcs7FromHash(password, cipheredKey, certX509, hashAlgorithm, hashToSign);
					
						signPromise.then(function(Signature) {
							//document.getElementById("signature").innerHTML = btoa(arrayBufferToString(Signature), false, true);
							document.getElementById(idFormulario + ":pkcs7Hidden_" + indiceEach).value = btoa(arrayBufferToString(Signature), false, true);
							//document.getElementById("inpPKCS7").innerHTML = btoa(arrayBufferToString(Signature), false, true);  //--Royer
							
							//Aquí se envía el pkcs7 al servidor para iniciar el proceso de actualización y generación de id de secuencia y pdf firmado
							if(indiceEach == (numeroArchivos-1)){
								iniciarActualizacion();
							}
							
						}, function(error) {
							//document.getElementById(idFormulario + ":pkcs7Hidden").value = "";
							document.getElementById(idFormulario + ":pkcs7Hidden_" + indiceEach).value = "";
							//document.getElementById("signature").innerHTML = "";
							//alert("[SgDataCrypto][pkcs7FromHash] " + error.message + hashToSign);
							
							PF('msgWarning').renderMessage({"summary": "Contrase\u00f1a de la llave privada incorrecta. Favor de verificar.",
								"detail":"",
								"severity":"warn"});
							
						});
						
					} else {
						
						PF('msgWarning').renderMessage({"summary": "Tú navegador no soporta la firma electrónica, favor de cambiar a Google chrome",
							"detail":"",
							"severity":"warn"});
						//alert("Your current browser does not support Promises! This page will not work.");
					}
					});
					//iniciarActualizacion();
					} catch(err) {
						PF('msgWarning').renderMessage({"summary": "Ocurrió un problema al firmar, por favor intentelo de nuevo",
							"detail":"",
							"severity":"warn"});
					//alert("SgDataCrypto Error:\n " + err.message + "\n" + err.stack);
				}
		} //End of signData 
		
		
//    <!--Off/Wcapi-->//<!--On/Wcapi-->