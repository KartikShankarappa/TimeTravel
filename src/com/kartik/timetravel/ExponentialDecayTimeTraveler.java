package com.kartik.timetravel;

/**
 * This class extends the SuperTimeTraveler Class and provide its specific implementation of the 
 * adjust() method.
 * @author KARTIK
 *
 */
public class ExponentialDecayTimeTraveler extends SuperTimeTraveler {

	private final int decayConstant;
	
	public ExponentialDecayTimeTraveler(String name, int decayConstant){
		super(name);
		this.decayConstant = decayConstant;
		
		if(decayConstant < 0){
			throw new IllegalArgumentException("Illegal decay constant specified");
		}
	}
	
	
	/**
	 * A getter method to obtain the decay constant value
	 * @return
	 */
	public int getDecayConstant(){
		return decayConstant;
	}
	
	
	/**
	 * This method is used to calculate the amount to time traveled and the number of years
	 * left to travel. The amount of time traveled is converted to years depending on whether 
	 * the unit is "Days" or "Hours". If it is "Days" then it is divided by 365 and if it is 
	 * "Hours" then it is divided by 8760. The formula used is :
	 *  N(t) = N(0) * exp(-λ * t)  
	 *  where N(t) is the quantity at time t,
	 *  N(0) is the quantity at time 0.
	 */
	@Override
	public void adjust(Time unit, int amount, boolean ahead) {

		double result;
		if(unit.equals("Days")){
			result = 100 * Math.exp(-getDecayConstant() * (double) amount / 365);
		}
		else{
			result = 100 * Math.exp(-getDecayConstant() * (double) amount / 8760);
		}
			
		/**
		 * if the result is greater than the yearsLeft then it can be handled in two ways.			 
		 * Either the yearsLeft can be made 0 which would suggest that henceforth there can be
		 * no more time travel OR
		 * we can throw an exception telling that there is not sufficient years
		 * left to travel time as much specified by the amount
		 * In this case i am taking the first approach
		 */
		if (result > getRemainingYearsOfTravel()){
			this.setRemainingYearsOfTravel(0d);
		}
		else{
			this.setRemainingYearsOfTravel(this.getRemainingYearsOfTravel() - result);
		}
	}
}
