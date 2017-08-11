package disambiguation;

/**
 * @author Saedeeh Shekarpour
 */

import java.util.*;


public class HiddenMarkovModel {

	protected static Hashtable<ArrayList, List<StateInfo>> observedMap = new Hashtable<ArrayList, List<StateInfo>>();
	protected static List<ArrayList> observationList = new ArrayList();
	protected static Hashtable<StateInfo, List<Integer>> stateBorders = new Hashtable<StateInfo, List<Integer>>();
	protected static List<StateInfo> stateSpace = new ArrayList();
	protected static Hashtable<StateInfo, Hashtable<StateInfo, Double>> distanceMatrix = new Hashtable<StateInfo, Hashtable<StateInfo, Double>>();
	protected static Hashtable<StateInfo, Hashtable<StateInfo, Double>> transMatrix = new Hashtable<StateInfo, Hashtable<StateInfo, Double>>();
	protected static Hashtable<StateInfo, Hashtable<Integer, Double>> emissionMatrix = new Hashtable<StateInfo, Hashtable<Integer, Double>>();
	protected static Hashtable<StateInfo, Double> startProbabilityMatrix = new Hashtable<StateInfo, Double>();
	protected static List<xResourceInfo> bestPath = new ArrayList();
	protected static Hashtable<StateInfo, List<Object[]>> traceTree = new Hashtable<StateInfo, List<Object[]>>();
	protected static double beta = 0;
	protected static LinkedList<Object[]> listSortedPaths = new LinkedList<Object[]>();

	public void initialize() {
		stateBorders.clear();
		stateSpace.clear();
		observedMap.clear();
		observationList.clear();
		distanceMatrix.clear();
		transMatrix.clear();
		emissionMatrix.clear();
		startProbabilityMatrix.clear();
		bestPath.clear();
		traceTree.clear();
		listSortedPaths.clear();
	}

	public void runModel(String inputQuery) {

		System.out.println(" input query is: " + inputQuery);
		PreProcessingOfTerms ppt = PreProcessingOfTerms.getInstance();
		StringSimilarityComparator ssc = new StringSimilarityComparator();
		Observation observationObject = new Observation();
		StateSpace stateSpaceObject = new StateSpace();
		ViterbiAlgorithm vb = new ViterbiAlgorithm();

		ArrayList Segment = new ArrayList<String>();

		ArrayList keywordQuery = new ArrayList();
		keywordQuery = ppt.removeStopWordswithlem(inputQuery);

		System.out.println(" input query after lemmatization is");
		for (int j = 0; j < keywordQuery.size(); j++) {
			System.out.println("keyword :   " + keywordQuery.get(j));
		}

		observationObject.buildModerateObservationState(keywordQuery);
		stateSpaceObject.buildStateSpace(keywordQuery);
		stateSpaceObject.buildDistanceMatrix();

		this.applyingHITS();
		singleEntity_HubAuthority(keywordQuery);
		buildTransitionMatrixbyWeightedHITS();

		buildEmission_Matrix(keywordQuery);
		buildStart_Probability_BasedonHITS();

		System.out.println(" Emission_Matrix " + emissionMatrix);
		System.out.println(" Start_Probability_Matrix "
				+ startProbabilityMatrix);
		
		vb.startMarkovModel(keywordQuery);

		

	}
	
	
	
	

	// ########################################################################################################################
	// ######### building transion matrix: calculating the transition
	// probability using distance between states
	// ########################################################################################################################

	private void buildTransitionMatrix() {
		int DisSize = distanceMatrix.size();
		List rowElement_list = new ArrayList();
		List Sorting_list = new ArrayList();
		List rowRank_list = new ArrayList();

		Set<Double> set = new HashSet<Double>();

		for (StateInfo s : stateSpace) {

			System.out.println(" State: " + s);
			Hashtable<StateInfo, Double> row_Distance = distanceMatrix.get(s);
			for (StateInfo c : stateSpace) {
				if (row_Distance.containsKey(c)) {
					Double d = row_Distance.get(c);
					if (d != Constants.MaximumDistance) {
						rowElement_list.add(d);
						set.add(d);
					}
				}

			}

			Sorting_list.addAll(set);
			Collections.sort(Sorting_list);

			// finding the rank of each element in a row

			for (int h = 0; h < rowElement_list.size(); h++) {
				int index = Sorting_list.indexOf(rowElement_list.get(h));
				int rank = index + 1;
				rowRank_list.add(h, rank);
			}

			Double Sigma = 0.0;
			for (int j = 0; j < rowRank_list.size(); j++) {
				Sigma = Sigma
						+ (1 / (Double.parseDouble(Integer
								.toString((Integer) rowRank_list.get(j)))));
			}

			Hashtable<StateInfo, Double> tran_row = new Hashtable<StateInfo, Double>();
			for (StateInfo c : stateSpace) {
				if (row_Distance.containsKey(c)) {
					Double distance = row_Distance.get(c);
					if (distance != Constants.MaximumDistance) {
						int index = rowElement_list.indexOf(distance);
						int rank = (Integer) rowRank_list.get(index);
						Double tran_prob = 1 / (rank * Sigma);
						tran_row.put(c, tran_prob);
					}

					else {
						tran_row.put(c, (double) 0);
					}

				}
			}
			if (!tran_row.isEmpty()) {
				transMatrix.put(s, tran_row);
			}

			rowElement_list.clear();
			Sorting_list.clear();
			rowRank_list.clear();
			set.clear();
		}

	}

	// ################################################################################
	// ######### this method calculates the hub and authority of each state
	// ################################################################################

	private void applyingHITS() {
		int k = 5; // k is the number of iterations
		double closeness;
		for (int step = 1; step <= k; step++) {
			System.out.println(" step = " + step);
			double norm = 0;
			for (StateInfo p : stateSpace) // update all authority values
											// first
			{
				p.setauthorithy(0);

				for (StateInfo s : stateSpace) // p.incomingNeighbors is the
												// set of pages that link to
												// p
				{
					if (distanceMatrix.containsKey(s)) {
						Hashtable<StateInfo, Double> DistanceRow = distanceMatrix
								.get(s);

						if (DistanceRow != null) {
							if (DistanceRow.get(p) != null) {

								if (DistanceRow.get(p) != Constants.MaximumDistance) {
									closeness = 6 - DistanceRow.get(p);
									p.setauthorithy(p.getauthorithy()
											+ s.gethub() * closeness);
								}

							}

						}
					}
				}

				norm += Math.pow(p.getauthorithy(), 2); // calculate the sum of
														// the squared auth
														// values to normalise

			}

			norm = Math.sqrt(norm);
			for (StateInfo p : stateSpace) // update the auth scores
			{

				p.setauthorithy(p.getauthorithy() / norm);

			}

			norm = 0;

			for (StateInfo p : stateSpace) // update all hub values first
			{

				p.sethub(0);
				if (distanceMatrix.containsKey(p)) {

					Hashtable<StateInfo, Double> DistanceRow = distanceMatrix
							.get(p);

					for (StateInfo s : stateSpace) // p.outgoingNeighbors is
													// the set of pages that
													// p links to
					{
						if (DistanceRow != null) {
							if (DistanceRow.containsKey(s)) {

								if (DistanceRow.get(s) != Constants.MaximumDistance) {

									closeness = 6 - DistanceRow.get(s);
									p.sethub(p.gethub() + s.getauthorithy()
											* closeness);
								}

							}
						}
					}
				}

				norm += Math.pow(p.gethub(), 2); // calculate the sum of the
													// squared auth values to
													// normalise

			}

			norm = Math.sqrt(norm);

			for (StateInfo p : stateSpace) // update the auth scores
			{
				p.sethub(p.gethub() / norm);

			}

			for (StateInfo p : stateSpace) // update the auth scores
			{
				if (java.lang.Double.isNaN(p.gethub())) {
					p.sethub(0);
				}
				if (java.lang.Double.isNaN(p.getauthorithy())) {
					p.setauthorithy(0);
				}

				System.out.println(" state == " + p.getLabel() + " hub = "
						+ p.gethub() + " authorithy = " + p.getauthorithy());

			}

		}

	}

	// ########################################################################################################################
	// ######### building transion matrix: calculating the transition
	// probability using distance between states
	// ########################################################################################################################

	private void buildTransitionMatrixbyWeightedHITS() {
		int DisSize = distanceMatrix.size();
		Set<Float> set = new HashSet<Float>();

		for (StateInfo s : stateSpace) {

			System.out.println(" State: " + s.getLabel());
			if (distanceMatrix.containsKey(s)) {
				Hashtable<StateInfo, Double> row_Distance = distanceMatrix
						.get(s);
				double leftProbability = s.gethub(); // this is the probability
														// of not going to
														// Unknown Entity

				double sigma = 0;
				for (StateInfo c : stateSpace) {
					if (row_Distance.containsKey(c)) {

						if (row_Distance.get(c) != Constants.MaximumDistance) {
							sigma += c.getauthorithy();
						}
					}

				}

				Hashtable<StateInfo, Double> tran_row = new Hashtable<StateInfo, Double>();
				for (StateInfo c : stateSpace) {
					if (row_Distance.containsKey(c)) {
						Double distance = row_Distance.get(c);
						if (distance != Constants.MaximumDistance) {
							double tran_prob = ((c.getauthorithy()) / sigma)
									* leftProbability;
							tran_row.put(c, tran_prob);
						}

						else {
							tran_row.put(c, (double) 0);
						}

					}
				}

				if (!tran_row.isEmpty()) {
					transMatrix.put(s, tran_row);
				}

			}
		}
	}

	// ########################################################################################################################
	// ######### This method calculates the emission probability
	// ########################################################################################################################

	private void buildEmission_Matrix(ArrayList keywordQuery) {

		for (int j = 0; j < stateSpace.size(); j++) {
			Hashtable<Integer, Double> rowEmission = new Hashtable<Integer, Double>();
			StateInfo Entity = stateSpace.get(j);

			for (int i = 0; i < keywordQuery.size(); i++) {

				double Similarity = 0;

				int Start = (stateBorders.get(Entity)).get(0);
				int End = (stateBorders.get(Entity)).get(1);
				if (Start <= i && i <= End) {
					Similarity = Entity.getStringSimilarityScore();
				}
				rowEmission.put(i, Similarity);

			}

			emissionMatrix.put(Entity, rowEmission);

		}
	}

	// ########################################################################################################################
	// ######### This method calculates the initial probabilities for the
	// starting point states
	// ########################################################################################################################

	private void buildStart_Probability_Matrix() {

		double count = 0;

		// the following lines count the number of entities that starts with the
		// first keywords
		for (int j = 0; j < stateSpace.size(); j++) {

			StateInfo Entity = stateSpace.get(j);
			int Start = (stateBorders.get(Entity)).get(0);
			if (Start == 0) {
				count = count + 1;
			}
		}

		System.out.println(" count " + count);

		// the following lines put the probability of the

		for (int j = 0; j < stateSpace.size(); j++) {

			StateInfo Entity = stateSpace.get(j);
			int Start = (stateBorders.get(Entity)).get(0);
			double prob = 0;
			if (Start == 0) {
				prob = 1 / count;

			}

			startProbabilityMatrix.put(Entity, prob);

		}

	}

	// ########################################################################################################################
	// ######### This method calculates the initial probabilities for the
	// starting point states by taking hub and authority of states into account
	// ########################################################################################################################

	private void buildStart_Probability_BasedonHITS() {

		double count = 0;
		double Sigma = 0;

		// the following lines count the number of entities that starts with the
		// first keywords
		for (int j = 0; j < stateSpace.size(); j++) {

			StateInfo Entity = stateSpace.get(j);
			int Start = (stateBorders.get(Entity)).get(0);
			if (Start == 0) {
				count++;
				Sigma = Sigma + stateSpace.get(j).getauthorithy()
						+ stateSpace.get(j).gethub();
			}
		}

		// the following lines calculates probability

		for (int j = 0; j < stateSpace.size(); j++) {
			StateInfo Entity = stateSpace.get(j);
			int Start = (stateBorders.get(Entity)).get(0);
			double prob = 0;
			if (Start == 0) {

				// prob = 1/count;
				prob = ((Entity.getauthorithy() + stateSpace.get(j).gethub()) / Sigma)
						* (1 - beta);

			}

			startProbabilityMatrix.put(Entity, prob);
		}

	}

	// ########################################################################################################################
	// ######### This methods assigns hub and authority when the input query is
	// mapped only to a single entity
	// ########################################################################################################################

	private void singleEntity_HubAuthority(ArrayList keywordQuery) {

		int size = keywordQuery.size();
		int indexStart = 0;
		int indexEnd = size - 1;
		InstanceRetrieval er = new InstanceRetrieval();

		for (int i = 0; i < stateSpace.size(); i++) {
			StateInfo Entity = stateSpace.get(i);
			int Start = (stateBorders.get(Entity)).get(0);
			int End = (stateBorders.get(Entity)).get(1);

			if (Start == indexStart && End == indexEnd) {

				Entity.sethub(0);
				if (Entity.getType() == Constants.TYPE_INSTANCE) {
					Entity.setauthorithy(er.getDegree(Entity));
				}
			}

		}

		for (StateInfo p : stateSpace) // update the auth scores
		{

			System.out.println(" state == " + p.getUri() + " hub = "
					+ p.gethub() + " authorithy = " + p.getauthorithy()
					+ " type = " + p.getType());

		}

	}

}
