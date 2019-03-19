/*
package com.topspin.boot.exception;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.correios.api.commons.email.EmailService;

// Retirar essa anotação abaixo, essa anotação sera informada no projeto da API.
//@ControllerAdvice
public class CommonsExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(CommonsExceptionHandler.class);

	@Autowired
	private Environment env;
	@Autowired
	private ObjectMapper om;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exc, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String msg = env.getProperty("CMN-001");
		MessageResponse result = obtemMessageResponse(exc, request, status, msg);
		return handleExceptionInternal(exc, result, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException exc, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String msg = env.getProperty("CMN-005");
		MessageResponse response = obtemMessageResponse(exc, request, status, msg);
		return handleExceptionInternal(exc, response, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(	MissingPathVariableException exc, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String msg = env.getProperty("CMN-005");
		MessageResponse response = obtemMessageResponse(exc, request, status, msg);
		return handleExceptionInternal(exc, response, headers, status, request);		
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exc, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String msg = env.getProperty("CMN-006");
		MessageResponse response = obtemMessageResponse(exc, request, status, msg);
		return handleExceptionInternal(exc, response, headers, status, request);		
	}
	
	
	

	@ExceptionHandler({ java.util.concurrent.TimeoutException.class })
//	@ExceptionHandler({ java.util.concurrent.TimeoutException.class, com.netflix.hystrix.exception.HystrixRuntimeException.class })
	protected ResponseEntity<Object> handleTimeout(RuntimeException exc, WebRequest request) {
		String msg = env.getProperty("CMN-002");
		MessageResponse response = obtemMessageResponse(exc, request, HttpStatus.REQUEST_TIMEOUT, msg);
		return handleExceptionInternal(exc, response, new HttpHeaders(), HttpStatus.REQUEST_TIMEOUT, request);
	}   
	
	@ExceptionHandler(DataAccessException.class)
	protected ResponseEntity<Object> handleDataAccessException(DataAccessException e, WebRequest request){
		String msg = env.getProperty("CMN-004");
		MessageResponse response = obtemMessageResponse(e, request, HttpStatus.INTERNAL_SERVER_ERROR, msg);
		return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}    

    @ExceptionHandler({ ConstraintViolationException.class })
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exc, HttpHeaders headers, HttpStatus status, WebRequest request) {
      MessageResponse response = obtemMessageResponse(exc, request, status);
      return ResponseEntity.status(status).body(response);
    } 

    @ExceptionHandler({ ApiTecnicaRuntimeException.class })
    protected ResponseEntity<Object> handleApiTecnicaRuntimeException(ApiTecnicaRuntimeException exc, WebRequest request) {    
    	HttpStatus status = exc.getStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : exc.getStatus();	
    	MessageResponse response = obtemMessageResponse(exc, request, status);
		return handleExceptionInternal(exc, response, new HttpHeaders(), status, request);
    }

    @ExceptionHandler({ ApiNegocioRuntimeException.class })
    protected ResponseEntity<Object> handleApiNegocioRuntimeException(ApiNegocioRuntimeException exc, WebRequest request) {
    	HttpStatus status = exc.getStatus() == null ? HttpStatus.BAD_REQUEST : exc.getStatus();
    	MessageResponse response = obtemMessageResponse(exc, request, status);
		return handleExceptionInternal(exc, response, new HttpHeaders(), status, request);
    }
	@Override	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exc, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ObjectError> allErrors = exc.getBindingResult().getAllErrors();
		List<String> msgs = new ArrayList<String>(); 
		for (ObjectError objectError : allErrors) {
			msgs.add(objectError.getDefaultMessage());
		}
		MessageResponse response = obtemMessageResponse(exc, request, HttpStatus.BAD_REQUEST, msgs);
        return handleExceptionInternal(exc, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	} 
	
	// Exceção lançada na comuninação com outra API. Utilizando cliente rest do Spring
    @ExceptionHandler({ HttpClientErrorException.class })
	protected ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException exc, WebRequest request) {
    	HttpStatus status = null;
    	MessageResponse response = null;
		String json = exc.getResponseBodyAsString();
		if (json != null) {
			try {
				response = om.readValue(json, MessageResponse.class);
				status = exc.getStatusCode();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			status = exc.getStatusCode() != null ? exc.getStatusCode() : HttpStatus.INTERNAL_SERVER_ERROR; 
			response = obtemMessageResponse(exc, request, status);
		}
        return handleExceptionInternal(exc, response, new HttpHeaders(), status, request);
	} 
    

    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<Object> handleDefault(Exception exc, WebRequest request) {
		String msg = env.getProperty("CMN-999");
        MessageResponse response = obtemMessageResponse(exc, request, HttpStatus.INTERNAL_SERVER_ERROR);
        return handleExceptionInternal(exc, response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }  
    
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // private methods
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	protected MessageResponse obtemMessageResponse(Exception exc,  HttpStatus status) {
		return obtemMessageResponse(exc, null, status, (List<String>)null);
	}
	
	protected MessageResponse obtemMessageResponse(Exception exc, WebRequest request,  HttpStatus status) {
		return obtemMessageResponse(exc, request, status, (List<String>)null);
	}

	protected MessageResponse obtemMessageResponse(Exception exc, WebRequest request, HttpStatus status, String msg) {
		List<String> msgs = new ArrayList<String>();
		msgs.add(msg);
		return obtemMessageResponse(exc, request, status, msgs);
	}

	protected MessageResponse obtemMessageResponse(Exception exc, WebRequest request, HttpStatus status, List<String> msgs) {
		return obtemMessageResponse(exc, request, status, msgs, null);
	}
	
	protected MessageResponse obtemMessageResponse(Exception exc, WebRequest request, HttpStatus status, List<String> msgs, String rootCauseMessage) {
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
	}
	    
    
	
	public void enviaEmail(MessageResponse resp) {
		
	}

    

}
*/