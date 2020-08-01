package com.example.trade.TradeValidation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.trade.Entity.Trade;
import com.example.trade.Entity.TradeDTO;
import com.example.trade.Entity.TradeMapper;
import com.example.trade.exception.TradeException;

@Component
public class TradeValidation {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private String insertTradeQuery;
	@Autowired
	private String updateTradeQuery;
	@Autowired
	private String selectTradeQuery;

	public boolean validateTradeAttributes(TradeDTO tradeDto) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date parseDate = (Date) formatter.parse(tradeDto.getMaturityDate());

		if (parseDate.before(new Date())) {
			throw new TradeException("Maturity Date should be greater than today's date");
		}
		Trade trade = transformDto(tradeDto);
		trade.setMaturityDate(parseDate);
		List<Trade> retrieveTradeList = new ArrayList<Trade>();
		try {
			retrieveTradeList = jdbcTemplate.query(selectTradeQuery, new Object[] { tradeDto.getTradeId() },
					new TradeMapper());
		} catch (DataAccessException de) {
			throw new TradeException("Exception encountered while inserting to DB");
		}
		if (retrieveTradeList.size() != 0) {
			List<Trade> sameVerisionTrade = retrieveTradeList.stream()
					.filter(t -> t.getVersion() == tradeDto.getVersion()).collect(Collectors.toList());

			if (sameVerisionTrade.size() == 1) {
				updateTradeDetails(trade);
				return true;
			}
			Trade maxTradeObject = retrieveTradeList.stream().max(Comparator.comparingInt(Trade::getVersion)).get();

			if (maxTradeObject.getVersion() > tradeDto.getVersion()) {
				throw new TradeException("The version privided " + (tradeDto.getVersion())
						+ " is lower than the existing version " + maxTradeObject.getVersion());
			}

		}
		insertTradeDetails(trade);
		return true;

	}

	private void insertTradeDetails(Trade trade) {

		try {
			jdbcTemplate.update(insertTradeQuery, trade.getTradeId(), trade.getVersion(), trade.getCounterPartyId(),
					trade.getBookId(), trade.getMaturityDate(), trade.getCreatedDate(), trade.getExpired());
			
		} catch (DataAccessException de) {
			throw new TradeException("Exception encountered while inserting to DB");
		}
	}

	private void updateTradeDetails(Trade trade) {

		try {
			int count = jdbcTemplate.update(updateTradeQuery, trade.getCounterPartyId(), trade.getBookId(),
					trade.getMaturityDate(), trade.getCreatedDate(), trade.getExpired(), trade.getTradeId(), trade.getVersion());
			System.out.println("HI"+count);
		} catch (DataAccessException de) {
			throw new TradeException("Exception encountered while inserting to DB");
		}
	}

	private Trade transformDto(TradeDTO tradeDto) {
		Trade trade = new Trade();
		trade.setBookId(tradeDto.getBookId());
		trade.setCounterPartyId(tradeDto.getCounterPartyId());
		trade.setCreatedDate(new java.util.Date());
		// trade.setMaturityDate(tradeDto.getMaturityDate());
		trade.setTradeId(tradeDto.getTradeId());
		trade.setVersion(tradeDto.getVersion());
		trade.setExpired("N");
		return trade;

	}

}
