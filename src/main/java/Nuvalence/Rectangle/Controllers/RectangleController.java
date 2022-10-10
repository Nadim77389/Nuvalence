package Nuvalence.Rectangle.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import Nuvalence.Rectangle.Beans.RectangleRequest;
import Nuvalence.Rectangle.Beans.RectangleResponse;
import Nuvalence.Rectangle.Services.RectangleServices;

import javax.validation.Valid;


@Controller
public class RectangleController {

	
	@Autowired
	RectangleServices rectangleService = new RectangleServices(); 
	
	final static Logger LOGGER = LoggerFactory.getLogger(RectangleController.class);
	
	/************************************************************
	 * 
	 * @param getRectangleAnalysis
	 * @return
	 * @throws 
	 * @author nadim
	 * @apiNote This POST function will accept RectangleRequest object and determine whether they intersect, have containment, and if the
	 * 			rectangles are adjacent to each other. 
	 */
	@PostMapping(path = "/Rectangle", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RectangleResponse> getRectangleAnalysis(@Valid @RequestBody(required = true) RectangleRequest recReq) {
		LOGGER.info("RectangleController::getRectangleAnalysis::Start");
		RectangleResponse recRes = new RectangleResponse();  
		
		recRes = rectangleService.analyzeRectangle(recReq);

		if (recRes != null) {
			LOGGER.info("RectangleController::getRectangleAnalysis::GoodEnd");
			return new ResponseEntity<>(recRes, HttpStatus.OK);
		}
		LOGGER.info("RectangleController::getRectangleAnalysis::BadEnd");
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	
}
