package com.kartik.timetravel;
/**
 * This class implements the TimeTravel interface.
 * The getname() and the getRemainingYearsOfTravel() methods are implemented here.
 * Since the adjust() method is impemented in some other class hence this class is made abstract
 * @author KARTIK
 *
 */
public abstract class SuperTimeTraveler implements TimeTraveler {
	
	private final String name;
	protected double yearsLeft;
	
	protected SuperTimeTraveler(String name){
		this.name =  name;
		this.yearsLeft = 100;
		
		if(this.name == null){
			throw new IllegalArgumentException("No one to time travel");
		}
	}
	
	/**
	 * A getter method to obtain the name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * A method that returns the number of years remaining to travel
	 */
	public Double getRemainingYearsOfTravel(){
			return this.yearsLeft;
	}
	
	/**
	 * A setter method to set the value of yearsLeft
	 * @param yearsLeft the value to which yearsLeft is to be set.
	 */
	public void setRemainingYearsOfTravel(double yearsLeft){
		this.yearsLeft = yearsLeft;
	}
}
