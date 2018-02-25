/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          (p1)
// FILE:             (Score.java)
//
// TEAM:    (Team 03 - Numb Biscuits)
// Authors: (Nafis Faisal Arrafi, Christian Farris, Kevin Fischer, Matthew 
//  		Matthew Stout, Chen Yang, and Ruimin Zhang)
//
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This class is used to construct Score objects. It allows a access to 
 * the name, points, max possible points, category, and percent of possible
 * points.
 *
 */
public class Score {

	private String name;
	private double points;
	private double maxP;
	
	/**
	 * This is the Score constructor. It creates score objects. 
	 * It assigns the parameters to instance variables.
	 *
	 * PRECONDITIONS: name can't be null, points must be greater
	 * than 0 and less than max possible points,  and max possible
	 * points must be greater than 0. 
	 * 
	 * POSTCONDITIONS: the parameters have been assigned to
	 * instance variables.
	 *
	 * @param (name) (Name of the score)
	 * @param (points) (Points of this Score)
	 * @param (maxP) (Max possible points to Score)
	 * @throws IllegalArgumentException.
	 */
	public Score(String name, double points, double maxP) throws IllegalArgumentException{
		if(name==null) throw new IllegalArgumentException();
		if(points<0 || points>maxP) throw new IllegalArgumentException();
		if(maxP<0) throw new IllegalArgumentException();
		
		this.name = name;
		this.points = points;
		this.maxP = maxP;
	}
	
	/**
	 * (Accessor method for name variable.)
	 *
	 * PRECONDITIONS: (Name variable must be assigned.)
	 * 
	 * POSTCONDITIONS: ()
	 *
	 * @return (String variable the holds name of Score object.)
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * (Accessor Method for points.)
	 *
	 * PRECONDITIONS: (Points variable must be assigned.)
	 * 
	 * POSTCONDITIONS: ()
	 *
	 * @return (Double variable that holds points of Score object.)
	 */
	public double getPoints(){
		return this.points;
	}
	
	/**
	 * (Accessor Method for max possible points.)
	 *
	 * PRECONDITIONS: (maxP variable must be assigned.)
	 * 
	 * POSTCONDITIONS: ()
	 *
	 * @return (Double variable that holds max possible points 
	 * of Score object.)
	 */
	public double getMaxPossible(){
		return this.maxP;
	}
	
	/**
	 * (Method that returns the first character of the string
	 * variable name in the form of a string.)
	 *
	 * PRECONDITIONS: (Name variable must be assigned.)
	 * 
	 * POSTCONDITIONS: ()
	 *
	 * @return (String variable that holds first character of the
	 * String variable name of Score object.)
	 */
	public String getCategory(){
		return Character.toString(this.name.charAt(0));
	}
	
	/**
	 * (This method divides points by maxP then multiplies it by 100
	 * in order to return the percentage of possible points.)
	 *
	 * PRECONDITIONS: (Points and maxP variables must be assigned.)
	 * 
	 * POSTCONDITIONS: ()
	 *
	 * @return (The percentage of points possible)
	 */
	public double getPercent(){
		return (points/maxP)*100;
	}
}
