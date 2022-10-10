package Nuvalence.Rectangle.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Nuvalence.Rectangle.Beans.RectangleRequest;
import Nuvalence.Rectangle.Beans.RectangleResponse;


public class RectangleServiceTest {

	ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	ObjectMapper mapper = new ObjectMapper();
	
	
	@Autowired
	private RectangleServices rs = new RectangleServices();
	
	
	@BeforeEach
	private void init() {
	}
	
	
	@Test
	public void analyzeRectangleContainmentTest() throws JsonParseException, JsonMappingException, IOException {
		
		File file = new File(classLoader.getResource("stubs/Containment.json").getFile());
		Object fileObject = mapper.readValue(file, RectangleRequest.class);
		RectangleRequest request = (RectangleRequest) fileObject;
		
		
		RectangleResponse rr = rs.analyzeRectangle(request);

		assertEquals(rr.isContainment(), true);
		assertEquals(rr.isAdjanceny(), false);
		assertEquals(rr.getIntersections(), null);
		
	}
	
	@Test
	public void analyzeRectangleAdjacencyTest() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(classLoader.getResource("stubs/Adjacent.json").getFile());
		Object fileObject = mapper.readValue(file, RectangleRequest.class);
		RectangleRequest request = (RectangleRequest) fileObject;
		
		
		RectangleResponse rr = rs.analyzeRectangle(request);

		assertEquals(rr.isContainment(), false);
		assertEquals(rr.isAdjanceny(), true);
		assertEquals(rr.getIntersections(), null);
			
	}
	
	
	
	@Test
	public void analyzeRectangleIntersectionTest() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(classLoader.getResource("stubs/Intersecting.json").getFile());
		Object fileObject = mapper.readValue(file, RectangleRequest.class);
		RectangleRequest request = (RectangleRequest) fileObject;
		
		
		RectangleResponse rr = rs.analyzeRectangle(request);

		assertEquals(rr.isContainment(), false);
		assertEquals(rr.isAdjanceny(), false);
		assertNotNull(rr.getIntersections());
	}

	
	
	@Test
	public void analyzeRectangleIntersectionCornerTest() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(classLoader.getResource("stubs/IntersectingCorner.json").getFile());
		Object fileObject = mapper.readValue(file, RectangleRequest.class);
		RectangleRequest request = (RectangleRequest) fileObject;
		
		
		RectangleResponse rr = rs.analyzeRectangle(request);

		assertEquals(rr.isContainment(), false);
		assertEquals(rr.isAdjanceny(), false);
		assertNotNull(rr.getIntersections());
	}
	
	
	@Test
	public void analyzeRectangeNoAssociationTest() throws JsonParseException, JsonMappingException, IOException {
		File file = new File(classLoader.getResource("stubs/NoAssociation.json").getFile());
		Object fileObject = mapper.readValue(file, RectangleRequest.class);
		RectangleRequest request = (RectangleRequest) fileObject;
		
		
		RectangleResponse rr = rs.analyzeRectangle(request);

		assertEquals(rr.isContainment(), false);
		assertEquals(rr.isAdjanceny(), false);
		assertEquals(rr.getIntersections(), null);
	}
}
