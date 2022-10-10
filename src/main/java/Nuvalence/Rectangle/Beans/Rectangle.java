package Nuvalence.Rectangle.Beans;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/******************
 * 
 * @author nadim
 * @apiNote Object that related to two corners of a rectangle.
 *
 */



@Data
public class Rectangle {

	
	@JsonProperty("topLeft")
	private Coordinate topLeft;
	
	@JsonProperty("bottomRight")
	private Coordinate bottomRight;
	
}
