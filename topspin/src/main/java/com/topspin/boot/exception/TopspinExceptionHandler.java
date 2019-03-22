package com.topspin.boot.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.topspin.boot.exception.model.ErrorResponse;
import com.topspin.boot.exception.model.ErrorResponseObject;
import com.topspin.boot.exception.model.MessageResponse;

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
    	MessageResponse response = obtemMessageResponse(exc, request, status, exc.getListaDeMensagens(), null); 
		return handleExceptionInternal(exc, response, new HttpHeaders(), status, request);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, 
                                                                  HttpStatus status, WebRequest request) {
        //return super.handleMethodArgumentNotValid(ex, headers, status, request);
    	List<ErrorResponseObject> errors = getErrors(ex);
    	ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
    	return new ResponseEntity<>(errorResponse, status);
    }
    
    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<Object> handleDefault(Exception exc, WebRequest request) {
    	MessageResponse response = obtemMessageResponse(exc, request, HttpStatus.INTERNAL_SERVER_ERROR, (List<String>)null, null);
        return handleExceptionInternal(exc, response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
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
    	
	}
    
    private List<ErrorResponseObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
        		.map(error -> new ErrorResponseObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }
    
    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<ErrorResponseObject> errors) {
        return new ErrorResponse("Requisição possui campos inválidos", status.value(),
                status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
    }

}