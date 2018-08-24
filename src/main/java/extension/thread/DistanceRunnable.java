
package extension.thread;

import java.util.Hashtable;

import disambiguation.sina.org.InstanceRetrieval;
import disambiguation.sina.org.StateInfo;;

public class DistanceRunnable implements Runnable{

	private InstanceRetrieval er =new InstanceRetrieval();
	private StateInfo nextR;
	private StateInfo firstR;
	private Hashtable<StateInfo, Double> distance_row;
	
	public DistanceRunnable(StateInfo firstR, StateInfo nextR, Hashtable<StateInfo, Double> distance_row) {
		this.firstR = firstR;
		this.nextR = nextR;
		this.distance_row = distance_row;
	}
	
	public void run() {
		int distance=er.getDistance(firstR, nextR);
		distance_row.put(nextR,  (double) distance);
		System.out.println( nextR.getUri() + "   Distance  " + distance);
	}
	
}
