package com.kartik.timetravel;


/**
 * This class extends the SuperTimeTraveler Class and provide its specific implementation of the 
 * adjust() method.
 * @author KARTIK
 *
 */
public class LinearDecayTimeTraveler extends SuperTimeTraveler {

	public LinearDecayTimeTraveler(String name){
		super(name);
	}
	
	/**
	 * This method is used to calculate the amount to time traveled and the number of years
	 * left to travel. The amount of time traveled is converted to years depending on whether 
	 * the unit is "Hours" or "Days". If it is "Days" then it is divided by 365 and if it is 
	 * "Hours" then it is divided by 8760.
	 */
	@Override
	public void adjust(Time unit, int amount, boolean ahead) {

		double result;
		if(unit.equals("Days")){
			result = (double) amount / 365;
		}
		else{
			result = (double) amount / 8760;
		}

		/**
		 * if the result is greater than the yearsLeft then it can be handled in two ways.
		 * Either the yearsLeft can be made 0 which would suggest that henceforth there can be
		 * no more time travel OR
		 * we can throw an exception telling that there is not sufficient years
		 * left to travel the time as much specified by the amount
		 * In this case i am taking the first approach
		 */
		if (result > this.getRemainingYearsOfTravel()){
			this.setRemainingYearsOfTravel(0d);
		}
		else{
			this.setRemainingYearsOfTravel(this.getRemainingYearsOfTravel() - result);
		}
	}
}
