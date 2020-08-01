package com.example.trade;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.trade.Entity.TradeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TradeApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureMockMvc
public class TradeControllerTest {
	
	@Autowired
	public MockMvc mvc;
	@Autowired
	   WebApplicationContext webApplicationContext;
	
	@Before
	   public void setUp() {
	      mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	   }
	   protected String mapToJson(Object obj) throws JsonProcessingException {
		      ObjectMapper objectMapper = new ObjectMapper();
		      return objectMapper.writeValueAsString(obj);
		   }
	   @Test
	   public void testController() throws JsonProcessingException, Exception {
		   TradeDTO dto = new TradeDTO();
		   dto.setBookId("B2");
		   dto.setCounterPartyId("CP-3");
		   dto.setTradeId("T3");
		   dto.setVersion(4);
		   dto.setMaturityDate("2021-04-04");
		   dto.setExpired(Boolean.FALSE);
		   MvcResult mvcResult = (MvcResult) mvc.perform(MockMvcRequestBuilders.post("/insertTrade")
				      .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(dto))).andReturn();
		   int actualStatus = mvcResult.getResponse().getStatus();
		   assertEquals(HttpStatus.OK.value(), actualStatus);
	   }
}
