package Nuvalence.Rectangle.Beans;



import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/******************
 * 
 * @author nadim
 * @apiNote Simple bean to hold the X and Y coordinates of a point of the rectangle
 *
 */



@Data
public class Coordinate {
	
	@JsonProperty("x")
	@NotBlank(message = "x is required")
	private int x;
	
	@JsonProperty("y")
	@NotBlank(message = "y is required")
	private int y;
}
