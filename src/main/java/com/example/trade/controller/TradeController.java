package com.example.trade.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.trade.Entity.TradeDTO;
import com.example.trade.TradeValidation.TradeValidation;
import com.example.trade.exception.TradeException;

/**
 * @author USER
 *
 */
@RestController
public class TradeController {
	
	@Autowired
	private TradeValidation tradeValidation;

	@PostMapping(value="/insertTrade")
	public ResponseEntity<String> insertTrade(@RequestBody TradeDTO tradeDTO ) throws ParseException {
		try {
			tradeValidation.validateTradeAttributes(tradeDTO);
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (TradeException ve) {
			return new ResponseEntity<String>("FAILED", HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
