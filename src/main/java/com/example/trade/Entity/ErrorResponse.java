package com.example.trade.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class ErrorResponse {

	private String status;
	private String errorMsg;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
}
