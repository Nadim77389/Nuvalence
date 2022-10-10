package Nuvalence.Rectangle.Beans;


import lombok.Data;

/******************
 * 
 * @author nadim
 * @apiNote This response will be returned to the calling application. This response will hold information
 * 			related to intersections of rectangles, whether a rectangle is contained in another, or whether
 * 			a rectangle is adjacent to another rectangle. 
 *
 */



@Data
public class RectangleResponse {
	
	private Rectangle intersections;	
	private boolean containment;
	private boolean adjanceny;

}
