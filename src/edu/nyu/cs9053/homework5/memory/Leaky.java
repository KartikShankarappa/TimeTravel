package edu.nyu.cs9053.homework5.memory;

import java.util.ArrayList;
import java.util.List;

public class Leaky {
	
	public class LeakyInner {
		
		private String someString = new String();
		
		public LeakyInner(String someString) {
			this.someString = someString;
		}
		
		public String getValueOfString() {
			return this.someString;
		}
	}
	
	private final int[] someNumber;
	
	public Leaky() {
		someNumber = new int[999999];
		for (int i = 0; i < someNumber.length ; i++) {
			someNumber[i] = i;
		}
	}
	
	public LeakyInner generateNewLeakyInner(String someString) {
		LeakyInner obj = new LeakyInner(someString);
		return obj;
	}

	public double getFreeMemory() {
		long memory = Runtime.getRuntime().freeMemory();
		return Math.round(memory / 1024d / 1024d);
//		System.out.println("Available free memory: " + memory);
	}
	

    public static void main(String[] args) {
        // user inner classes to cause the JVM to run out of memory
    	List<Leaky.LeakyInner> innerLeakyList = new ArrayList<Leaky.LeakyInner>();
    	for(int i = 0; i < 999999; i++) {

    		Leaky leakyObjects = new Leaky();
    		System.out.printf("Memory available before the inner object is created : %f\n", leakyObjects.getFreeMemory());
    		Leaky.LeakyInner innerLeakyObjects = leakyObjects.generateNewLeakyInner(String.valueOf(i));
    		innerLeakyList.add(innerLeakyObjects);
    		System.out.printf("Memory available after the inner object is created : %f\n", leakyObjects.getFreeMemory());
    	}
    }

}
