package com.kartik.timetravel;

public class MadScientist {

    private final TimeMachine timeMachine;

    public MadScientist(TimeMachine timeMachine) {
        this.timeMachine = timeMachine;
    }

    public static void main(String[] args) {
        // make a MadScientist / TimeMachine and 3 TimeTraveler implementations
    	TimeMachine timeMachine = new TimeMachine();
    	MadScientist madScientist = new MadScientist(timeMachine);
    	
        // experiment on each TimeTraveler
        // a TimeTraveler should always start with 100 years of time travel strength
    	
        // one TimeTraveler implementation should linearly decay (i.e., one year of actual time travel reduces the
        // time traveler's ability by one year)
    	TimeTraveler linearDecayTraveler = new LinearDecayTimeTraveler("Christopher Nolan");
    	
        // one TimeTraveler implementation should decay double the travel value (i.e., one year of actual time travel reduces
        // the time traveler's ability by two years)
    	TimeTraveler doubleDecayTraveler = new DoubleDecayTimeTraveler("Hans Zimmer");
    	
        // one TimeTraveler implementation should have exponential decay with a decay constant inputted by the scientist (see http://en.wikipedia.org/wiki/Exponential_decay)
    	TimeTraveler exponentialDecayTraveler = new ExponentialDecayTimeTraveler("Steve Jablonsky", 2);
    	
        // continue to experiment until all the TimeTraveler's have exhausted their ability to travel
    
    		madScientist.experiment(linearDecayTraveler);
    	
    		madScientist.experiment(doubleDecayTraveler);
    	
    		madScientist.experiment(exponentialDecayTraveler);
    }

    
    public void experiment(final TimeTraveler timeTraveler) {
        // invoke the time-machine and print how much time has passed using a callback and adjust the time traveler's ability to travel
    	
    	timeMachine.travel(timeTraveler, new TimeTravelCallback(){ //This is the anonymous class
    		@Override
    		public void leaped(Time unit, int amount, boolean ahead){
    			
    			if (ahead == true){
    				System.out.println(timeTraveler.getName() +" has travelled "+ amount +" "+ unit + " in future. Time remaining is " + timeTraveler.getRemainingYearsOfTravel() + " years\n");
    			}
    			else{
    				System.out.println(timeTraveler.getName() +" has travelled "+ amount +" "+ unit + " in past. Time remaining is " + timeTraveler.getRemainingYearsOfTravel() + " years\n");
    			}
    			
                timeTraveler.adjust(unit,amount,ahead);
                
                if(timeTraveler.getRemainingYearsOfTravel() > 0d){
                    experiment(timeTraveler); // recursively calls the experiment() method to exhaust the traveler ability to travel
                }
                else{
                    System.out.print("Traveller " + timeTraveler.getName() + ", you have exhausted your time travel capacity.\n");
                }
            }
        });
    }
}
