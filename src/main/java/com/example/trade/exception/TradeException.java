package com.example.trade.exception;

public class TradeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1179315447578601586L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TradeException(String message) {
		super();
		this.message = message;
	}
	
	
}
