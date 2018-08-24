package disambiguation.sina.org;

/**
 * @author Saedeeh Shekarpour
 */

/*
 * This class inheriting from Hidden Markov Model populates the state space of the model.
 * For each valid segment as an observation, it retrieves all the mapped resources (instance, class and properties). 
 * Furthermore, it calculates the distance between states
 */


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import extension.thread.DistanceRunnable;
import extension.thread.MultiRunnerDam;


import net.didion.jwnl.JWNLException;

public class StateSpace extends HiddenMarkovModel {

	// ############################################################
	// ############# This method generates stateSpace along with
	// the starting and end point of keywords of observable segments
	// ############################################################

	public void buildStateSpace(List keywordList) {

		System.out.println(" ...state space is being build...  ");

		for (int i = 0; i < observationList.size(); i++) {

			List Segment = observationList.get(i);
			List<StateInfo> r = observedMap.get(Segment);
			int Start, End;

			Start = keywordList.indexOf(Segment.get(0));
			End = keywordList.indexOf(Segment.get(Segment.size() - 1));

			// for example for gettysburge battle instead of
			// " battle of gettysburge"
			if (Start > End) {
				int temp = Start;
				Start = End;
				End = temp;
			}

			List borderPoints = new ArrayList();
			borderPoints.add(Start);
			borderPoints.add(End);
			System.out.println(" segment " + Segment);
			for (int j = 0; j < r.size(); j++) {
				System.out.println(" resource " + r.get(j).getUri() + "Start= "
						+ Start + "End" + End);
				stateBorders.put(r.get(j), borderPoints);
				stateSpace.add(r.get(j));
			}
		}
	}

	
	// ############################################################
	// ############# This method computes distance matrix
	// ############################################################
	public void buildDistanceMatrix() {
		List<StateInfo> nextStates = new ArrayList<StateInfo>();
		for (int i = 0; i < stateSpace.size(); i++) {
			int End = (Integer) stateBorders.get(stateSpace.get(i)).get(1);
			for (int j = 0; j < stateSpace.size(); j++) {
				int NStart = (Integer) stateBorders.get(stateSpace.get(j)).get(
						0);
				if (NStart == End + 1) {
					nextStates.add(stateSpace.get(j));
				}
			}

			System.out.println(" neighbor entities to "
					+ stateSpace.get(i).getUri());
			Hashtable<StateInfo, Double> distance_row = new Hashtable<StateInfo, Double>();
			List<Runnable> queryRunnables = new ArrayList<Runnable>();
			for (int j = 0; j < nextStates.size(); j++) {
				StateInfo nextR = nextStates.get(j);
				Runnable distanceQuery = new DistanceRunnable(
						stateSpace.get(i), nextR, distance_row);
				queryRunnables.add(distanceQuery);
			}

			Runnable[] runnables = new Runnable[queryRunnables.size()];
			MultiRunnerDam runnerDam = new MultiRunnerDam(
					queryRunnables.toArray(runnables));
			runnerDam.run();

			distanceMatrix.put(stateSpace.get(i), distance_row);
			nextStates.clear();
		}

		System.out.println("distance matrix");
		System.out.println(distanceMatrix);
	}

	private void ResourceRetrivalforPattern(String pattern,
			ArrayList<Object> keywordQuery, int start, int end) {
		ArrayList<Object> segment = new ArrayList<Object>();
		for (int j = start; j < end; j++) {
			segment.add(keywordQuery.get(j));
		}
		System.out.println(" Segment  " + segment + "  ");
	}

}
