package Nuvalence.Rectangle.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import Nuvalence.Rectangle.Beans.RectangleRequest;
import Nuvalence.Rectangle.Beans.RectangleResponse;
import Nuvalence.Rectangle.Controllers.RectangleController;
import Nuvalence.Rectangle.Beans.Coordinate;
import Nuvalence.Rectangle.Beans.Rectangle;


@Service
public class RectangleServices {
	
	
	final static Logger LOGGER = LoggerFactory.getLogger(RectangleController.class);


	
	/*********************************************************************
	 * 
	 * @param recReq
	 * @return
	 * @throws 
	 * @author nadim
	 * @apiNote This service function will determine all the attributes necessary for a 
	 * 			rectangle response.
	 */
	public RectangleResponse analyzeRectangle(RectangleRequest recReq) {
		LOGGER.info("RectangleServices::analyzeRectangle::Start");
		RectangleResponse recResponse = new RectangleResponse();
		Rectangle intersection = null;
		
		Rectangle recOne = recReq.getRectangles().get(0);
		Rectangle recTwo = recReq.getRectangles().get(1);		
		
		
		boolean containment = determineContainment(recOne, recTwo);
		boolean adjanceny = determineAdjacency(recOne, recTwo);
		if (!containment) {
			if (!adjanceny) {
				intersection = determineIntersection(recOne, recTwo);
			}
		}
		
		recResponse.setContainment(containment);
		recResponse.setAdjanceny(adjanceny);
		recResponse.setIntersections(intersection);
		
		LOGGER.info("RectangleServices::analyzeRectangle::End");
		return recResponse;
	}

	
	
	
	
	/*********************************************************************
	 * 
	 * @param recReq
	 * @return
	 * @throws 
	 * @author nadim
	 * @apiNote Determines Adjacency: This implementation has the ability to detect whether two rectangles are adjacent. 
	 * 			Adjacency is defined as the sharing of at least one side. Side sharing may be proper, sub-line or partial. 
	 * 			A sub-line share is a share where one side of rectangle A is a line that exists as a set of points wholly 
	 * 			contained on some other side of rectangle B, where partial is one where some line segment on a side of 
	 * 			rectangle A exists as a set of points on some side of Rectangle B. 
	 */
	private boolean determineAdjacency(Rectangle one, Rectangle two) {
		LOGGER.debug("RectangleServices::determineAdjacency::Start");

		//determine if rectangle one is Adjacency to rectangle two
		if (one.getTopLeft().getY()-1 == two.getBottomRight().getY() || //Rectangle is adjacent top
			one.getBottomRight().getY()+1 == two.getTopLeft().getY() || //Rectangle is adjacent bottom
			one.getTopLeft().getX()-1 == two.getBottomRight().getX() || //Rectangle is adjacent left
			one.getBottomRight().getX()+1 == two.getTopLeft().getX()) { //Rectangle is adjacent right
			LOGGER.debug("RectangleServices::determineAdjacency::End");
			return true;
		}
		
		LOGGER.debug("RectangleServices::determineAdjacency::End");
		return false;
	}

	
	/*********************************************************************
	 * 
	 * @param recReq
	 * @return
	 * @throws 
	 * @author nadim
	 * @apiNote Determines Containment: determines whether a rectangle is wholly contained within another rectangle. 
	 */
	private boolean determineContainment(Rectangle one, Rectangle two) {
		LOGGER.debug("RectangleServices::determineContainment::Start");

		//determine if rectangle one is inside rectangle two
		if (one.getTopLeft().getX() <= two.getTopLeft().getX() &&
			one.getTopLeft().getY() <= two.getTopLeft().getY() &&
			one.getBottomRight().getX() >= two.getBottomRight().getX() &&
			one.getBottomRight().getY() >= two.getBottomRight().getY()) {
			LOGGER.debug("RectangleServices::determineContainment::end");
			return true;
		}
		
		LOGGER.debug("RectangleServices::determineContainment::end");
		return false;
	}

	
	/*********************************************************************
	 * 
	 * @param recReq
	 * @return
	 * @throws 
	 * @author nadim
	 * @apiNote Determines Intersection: determines whether two rectangles have one or more intersecting lines and 
	 * 			produce a result identifying the points of intersection.
	 */
	private Rectangle determineIntersection(Rectangle one, Rectangle two) {
		LOGGER.debug("RectangleServices::determineIntersection::Start");

		Rectangle intersectingRectangle = new Rectangle(); 

		
		/*
			As two given points are diagonals of a rectangle. so, oneTopleftX < oneBottomRightX, 
			oneTopLeftY < oneBottomRightY. So, bottom-right and top-left points of intersection 
			rectangle can be found by using Min & Max.
			 
			In case of no intersection, intersectionTopLeftX and intersectionTopLeftY will always exceed 
			intersectionBottomRightX and intersectionBottomRightY.
			 
		*/

		//used to determine the points of intersection ( if any )
		int intersectionTopLeftX = Math.max(one.getTopLeft().getX(), two.getTopLeft().getX()); //== 2  --5
		int intersectionTopLeftY = Math.max(one.getTopLeft().getY(), two.getTopLeft().getY()); //== 6  --10
		int intersectionBottomRightX = Math.min(one.getBottomRight().getX(), two.getBottomRight().getX()); //== 2 --3
		int intersectionBottomRightY = Math.min(one.getBottomRight().getY(), two.getBottomRight().getY()); //== 7 --14
		
		
		
		//Determine if there is no intersection
		//If the Top Left X is greater than the Bottom Right X that means that there is no intersection
		//If the Top Left Y is greater than the bottom Right Y that means that there is no intersection 
		if ( intersectionTopLeftX > intersectionBottomRightX || intersectionTopLeftY > intersectionBottomRightY)
		{
			LOGGER.debug("RectangleServices::determineIntersection::End");
			return null;
		}
		
		Coordinate bottomRightCoordinate = new Coordinate();
		Coordinate TopLeftCoordinate = new Coordinate();
		bottomRightCoordinate.setX(intersectionBottomRightX);
		bottomRightCoordinate.setY(intersectionBottomRightY);
		TopLeftCoordinate.setX(intersectionTopLeftX);
		TopLeftCoordinate.setY(intersectionTopLeftY);
		
		intersectingRectangle.setBottomRight(bottomRightCoordinate);
		intersectingRectangle.setTopLeft(TopLeftCoordinate);
		
		LOGGER.debug("RectangleServices::determineIntersection::End");
		return intersectingRectangle;
	}

}
