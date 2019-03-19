package com.topspin.boot.exception;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TopspinExceptionHandler extends ResponseEntityExceptionHandler {
	
	//private static final Logger log = LoggerFactory.getLogger(TopspinExceptionHandler.class);
	
	@ExceptionHandler({ ApiTecnicaRuntimeException.class })
    protected ResponseEntity<Object> handleApiTecnicaRuntimeException(ApiTecnicaRuntimeException exc, WebRequest request) {    
    	HttpStatus status = exc.getStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : exc.getStatus();	
    	MessageResponse response = obtemMessageResponse(exc, request, status, (List<String>)null, null);
		return handleExceptionInternal(exc, response, new HttpHeaders(), status, request);
    }

    @ExceptionHandler({ ApiNegocioRuntimeException.class })
    protected ResponseEntity<Object> handleApiNegocioRuntimeException(ApiNegocioRuntimeException exc, WebRequest request) {
    	HttpStatus status = exc.getStatus() == null ? HttpStatus.BAD_REQUEST : exc.getStatus();
    	MessageResponse response = obtemMessageResponse(exc, request, status, (List<String>)null, null);
		return handleExceptionInternal(exc, response, new HttpHeaders(), status, request);
    }
    
    
    //Métodos privados de apoio
    protected MessageResponse obtemMessageResponse(Exception exc, WebRequest request, HttpStatus status, List<String> msgs, String causa) {
		
    	MessageResponse messageResponse = new MessageResponse();
    	messageResponse.setMsgs(msgs);
    	messageResponse.setDate(new Date());
    	messageResponse.setCausa(causa);
    	if (causa == null) {
			messageResponse.setCausa(ExceptionUtils.getRootCauseMessage(exc)); 
		}
    	if (request != null) {    		
    		messageResponse.setPath(request.getDescription(false));
    	}
    	messageResponse.setStackTrace(ExceptionUtils.getStackTrace(exc));
    	messageResponse.setUser(null);
    	messageResponse.setStatus(status);
    	return messageResponse;
    	
    	/*
    	MessageResponse result = new MessageResponse();  	
		String user = null;
		String path = null;
		if (request != null) {
			Principal userPrincipal = request.getUserPrincipal();	//TODO usar credencial
			if (userPrincipal != null) {
				user = userPrincipal.getName();
			}
			path = request.getDescription(false);
		}
				
		if (rootCauseMessage == null) {
			rootCauseMessage = ExceptionUtils.getRootCauseMessage(exc); 
		}
		if (msgs == null || msgs.size() == 0) {
			String msg = null;
			if (rootCauseMessage != null) {
				int indexOf = rootCauseMessage.indexOf(":");
			    int length = rootCauseMessage.length();
				if (indexOf > 0 && length > 2) {
					msg = rootCauseMessage.substring(indexOf + 2, length);
			    }
			}	
			if (msg == null || msg.trim().length() == 0) {
				msg = rootCauseMessage;
			}
			result.addMensagem(msg);
		} else {
			result.setMsgs(msgs);			
		}
		   
		result.setCausa(rootCauseMessage);	
		String stack = ExceptionUtils.getStackTrace(exc); // NÃO FAZER PARA PRODUCÇÃO
		result.setStackTrace(stack);

		result.setStatus(status);
		result.setUser(user);
		result.setPath(path);
		result.setDate(LocalDateTime.now());

		
		if (status.value() >= 500) {
			log.error(stack);
			//emailService.enviaEmail(result);
		} else if (status.value() >= 400) {
			log.error(result.getMsgs().toString());
		}
		return result;
		*/
	}

}