package com.example.desafio.model;

public class Erro {
	
	private Integer status_code = 400;
	private String Message; 
	
	public Integer getStatus_code() {
		return status_code;
	}
	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
}
