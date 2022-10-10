package Nuvalence.Rectangle.Controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;


import Nuvalence.Rectangle.Beans.RectangleRequest;
import Nuvalence.Rectangle.Beans.RectangleResponse;
import Nuvalence.Rectangle.Services.RectangleServices;


@WebMvcTest(RectangleController.class)
@ExtendWith(MockitoExtension.class)
public class RectangleControllerTest {

	Gson g = new Gson();
	
	@Autowired
	private MockMvc mvc;
	
	
	@MockBean
	private RectangleServices rs;


	@Test
	public void getRectangleAnalysisTest () throws Exception {
		
		RectangleResponse recResponse = new RectangleResponse();
		RectangleRequest rr = new RectangleRequest();
		
		recResponse.setAdjanceny(true);
		recResponse.setContainment(false);
		recResponse.setIntersections(null);
		when(rs.analyzeRectangle(any(RectangleRequest.class))).thenReturn(recResponse);
		
		
		mvc.perform(MockMvcRequestBuilders
				.post("/Rectangle")
				.content(g.toJson(rr).toString())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());

	}
	
	
	@Test
	public void getRectangleAnalysisBadRequestTest () throws Exception {
		
		RectangleRequest rr = new RectangleRequest();
		
		when(rs.analyzeRectangle(any(RectangleRequest.class))).thenReturn(null);
		
		mvc.perform(MockMvcRequestBuilders
				.post("/Rectangle")
				.content(g.toJson(rr).toString())
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isBadRequest());

	}
	
}
