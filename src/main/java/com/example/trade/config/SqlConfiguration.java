package com.example.trade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlConfiguration {

	@Bean
	public String insertTradeQuery() {
		String insertQuery = "Insert into tradeservice.trade (trade_id,version,counter_partyid,book_id,maturity_date,created_date,expired) values (?,?,?,?,?,?,?)";
		return insertQuery;
	}

	@Bean
	public String updateTradeQuery() {
		String updateQuery = "Update tradeservice.trade set counter_partyid = ?,book_id = ?,maturity_date = ?,created_date = ?,expired = ? where trade_id = ? and version = ?";
		return updateQuery;
	}

	@Bean
	public String selectTradeQuery() {
		String sqlQuery = "select * from tradeservice.trade where trade_Id=?";
		return sqlQuery;
	}

}
