package com.example.trade.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TradeMapper implements RowMapper<Trade> {

	@Override
	public Trade mapRow(ResultSet rs, int rowNum) throws SQLException {

		Trade trade = new Trade();
		trade.setTradeId(rs.getString("trade_id"));
		trade.setVersion(rs.getInt("version"));
		trade.setCounterPartyId(rs.getString("counter_partyid"));
		trade.setBookId(rs.getString("book_id"));
		trade.setCreatedDate(rs.getDate("created_date"));
		trade.setMaturityDate(rs.getDate("maturity_date"));
		trade.setExpired(rs.getString("expired"));

		return trade;
	}

}
