package Nuvalence.Rectangle.Beans;

import java.util.LinkedList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


/******************
 * 
 * @author nadim
 * @apiNote This request will come into the Rectangle API and pass in a list of coordinates for each rectangle.
 * 			These coordinates will feature two left and two right points.
 *
 */


@Data
public class RectangleRequest {

	@JsonProperty("rectangles")
	List<Rectangle> rectangles = new LinkedList<Rectangle>();

}
