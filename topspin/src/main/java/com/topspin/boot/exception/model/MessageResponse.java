package com.topspin.boot.exception.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MessageResponse {
	
	private List<String> msgs;
	private String descricao;
	private Date date;
    protected String causa;
    protected String path;
    @JsonIgnore
    private String user;    
    protected String stackTrace;
    @JsonIgnore
    private HttpStatus status;
    
    
    public String getCausa() {
        return causa;
    }
    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getStackTrace() {
        return stackTrace;
    }
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void addMensagem(String mensagem) {
		if (this.msgs == null) {
			this.msgs = new ArrayList<String>();
		}
		this.msgs.add(mensagem);
	}

	public List<String> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<String> msgs) {
		this.msgs = msgs;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "MessageResponse [msgs=" + msgs + ", date=" + date + ", descricao=" + descricao + ", causa=" + causa
				+ ", path=" + path + ", user=" + user + ", stackTrace=" + stackTrace + ", status=" + status + "]";
	}


}
