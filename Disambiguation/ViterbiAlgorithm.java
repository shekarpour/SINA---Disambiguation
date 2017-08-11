package disambiguation;

/**
 * @author Saedeeh Shekarpour
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class ViterbiAlgorithm extends HiddenMarkovModel {

	public void startMarkovModel(ArrayList keywordQuery)

	{

		Object[] ret = forward_viterbi(keywordQuery);

		bestPath.clear();
		bestPath = ((List) ret[3]);
		System.out.println("total probability: "
				+ ((Double) ret[0]).doubleValue());
		System.out.println("path of maximum: " + (String) ret[1]);
		System.out.println("maximum probability: "
				+ ((Double) ret[2]).doubleValue());
		System.out.println("Best Path Entites: " + bestPath);

		printTraceTree();
		sortTraceTree();

	}

	// ############################################################

	private void printTraceTree() {

		System.out
				.println("  **********************  TraceTree ******************");

		for (StateInfo s : stateSpace) {
			if (traceTree.containsKey(s)) {
				System.out.println("$$$$$$$$$$$$$$$$" + s.getkeyword()
						+ "$$$$$$$$$$$$$$$$$$$$$$$");
				List<Object[]> y = traceTree.get(s);

				System.out.println(" TraceTree SIZE " + y.size());
				for (int i = 0; i < y.size(); i++) {

					String path_state = "";
					double probability_state = 1;

					Object[] Traceobjs = y.get(i);
					probability_state = ((Double) Traceobjs[0]).doubleValue();
					path_state = (String) Traceobjs[1];
					// List pathEntityList_source_state = ((List) Traceobjs[2]);

					System.out.println(" probability_state "
							+ probability_state);
					System.out.println(" path_state " + path_state);
					// System.out.println(" pathEntityList " +
					// pathEntityList_source_state);

				}
			}
		}

	}

	// ############################################################
	private void sortTraceTree() {

		for (StateInfo s : stateSpace) {
			if (traceTree.containsKey(s)) {

				List<Object[]> y = traceTree.get(s);

				// System.out.println(" TraceTree SIZE "+ y.size());

				for (int i = 0; i < y.size(); i++) {
					listSortedPaths.add(y.get(i));
				}
			}
		}

		Collections.sort(listSortedPaths, new Comparator<Object[]>() {
			public int compare(Object[] o1, Object[] o2) {
				double p1 = ((Double) o1[0]).doubleValue();
				double p2 = ((Double) o2[0]).doubleValue();

				if (p1 > p2) {
					return 1;
				} else if (p1 < p2) {
					return -1;
				} else {
					return 0;
				}
			}

		});

		if (listSortedPaths.size() == 0)
			return;

		for (int i = 0; i < listSortedPaths.size(); i++) {
			Object[] y = listSortedPaths.get(i);
			double p = ((Double) y[0]).doubleValue();
			String path_state = (String) y[1];

			System.out.print(p + ": ");
			System.out.println(path_state);
		}

		Object[] y = listSortedPaths.getLast();
		double p = ((Double) y[0]).doubleValue();
		String path_state = (String) y[1];

	}

	// #################################################################
	public String getFirstbestpath() {
		if (listSortedPaths.size() == 0)
			return null;

		Object[] y = listSortedPaths.getLast();
		double p = ((Double) y[0]).doubleValue();
		String path_state = (String) y[1];
		// System.out.println("first path == p=" + p + " path_state=" +
		// path_state);

		return path_state;
	}

	// #################################################################

	public String getSecondbestpath() {
		int index = listSortedPaths.size() - 2;
		if (index < 0)
			return null;

		Object[] y = listSortedPaths.get(index);
		double p = ((Double) y[0]).doubleValue();
		String path_state = (String) y[1];
		// System.out.println("first path == p=" + p + " path_state=" +
		// path_state);

		return path_state;
	}

	// ####################################################################

	public LinkedList<Object[]> getSortedList() {
		return listSortedPaths;
	}

	// #################################################################

	private static Object[] forward_viterbi(ArrayList keywordQuery) {

		ArrayList obs = keywordQuery;
		List<StateInfo> states = stateSpace;
		Hashtable<StateInfo, Double> start_p = startProbabilityMatrix;
		Hashtable<StateInfo, Hashtable<StateInfo, Double>> trans_p = transMatrix;
		Hashtable<StateInfo, Hashtable<Integer, Double>> emit_p = emissionMatrix;
		Hashtable<StateInfo, Object[]> T = new Hashtable<StateInfo, Object[]>();

		for (StateInfo state : states) {
			List pathEntities = new ArrayList();
			pathEntities.add(state);
			Object[] Obj = new Object[] { start_p.get(state),
					state.getkeyword(), start_p.get(state), pathEntities };
			T.put(state, Obj);

			Object[] Obj2 = new Object[] { start_p.get(state),
					state.getkeyword(), pathEntities };

			List<Object[]> TraceList = new ArrayList();
			TraceList.add(Obj2);
			traceTree.put(state, TraceList);

		}

		System.out
				.println("**********************  Initialization Step ******************");

		for (StateInfo s : states) {
			if (traceTree.containsKey(s)) {

				System.out.println("$$$$$$$$$$$$$$$$" + s.getkeyword()
						+ "$$$$$$$$$$$$$$$$$$$$$$$");
				List<Object[]> t = traceTree.get(s);
				System.out.println(" TraceList size " + t.size());
				for (int i = 0; i < t.size(); i++) {
					System.out.println("hello saeedeh  Initialization Step ");
					String path_state = "";
					double probability_state = 1;

					Object[] Traceobjs = t.get(i);
					probability_state = ((Double) Traceobjs[0]).doubleValue();
					path_state = (String) Traceobjs[1];

					System.out.println(" probability_state "
							+ probability_state);
					System.out.println(" path_state " + path_state);

				}
			}

			else {
				System.out.println(" does not contain ");
			}
		}

		for (int i = 0; i < obs.size() - 1; i++) {
			Hashtable<StateInfo, Object[]> U = new Hashtable<StateInfo, Object[]>();
			Hashtable<StateInfo, List<Object[]>> TemporaryTraceTree = new Hashtable<StateInfo, List<Object[]>>();

			double valmax_QueryCleaning = 0;

			for (StateInfo next_state : states) {
				double total = 0;
				String argmax = "";
				double valmax = 0;
				List BPath = new ArrayList();

				double prob = 1;
				String v_path = "";
				double v_prob = 1;

				List<Object[]> TracePaths_next_state = new ArrayList();

				System.out.println(".... next_state ..."
						+ next_state.getkeyword());

				for (StateInfo source_state : states) {
					Object[] objs = T.get(source_state);
					prob = ((Double) objs[0]).doubleValue();
					v_path = (String) objs[1];
					v_prob = ((Double) objs[2]).doubleValue();
					List path = ((List) objs[3]);

					int Start = stateBorders.get(source_state).get(0);
					int End = stateBorders.get(source_state).get(1);
					double trans = 0;

					if (i == End) {

						if (trans_p.containsKey(source_state)) {
							if (trans_p.get(source_state).get(next_state) != null) {

								trans = trans_p.get(source_state).get(
										next_state);

							}
						}

						double p = emit_p.get(source_state).get(i) * trans;

						prob *= p;
						v_prob *= p;
						total += prob;
						if (v_prob > valmax) {
							argmax = v_path + " , " + next_state.getkeyword();
							valmax = v_prob;
							BPath.clear();
							BPath.addAll(path);
							BPath.add(next_state);
						}

						if (p != 0 && traceTree.containsKey(source_state)) {
							List<Object[]> TracePaths_source_state = traceTree
									.get(source_state);

							for (int j = 0; j < TracePaths_source_state.size(); j++) {
								// double probalityTotal = 1;
								String path_source_state = "";
								double probability_source_state = 1;
								Object[] Traceobjs = TracePaths_source_state
										.get(j);
								probability_source_state = ((Double) Traceobjs[0])
										.doubleValue();
								path_source_state = (String) Traceobjs[1];
								List pathEntityList_source_state = ((List) Traceobjs[2]);

								probability_source_state *= p;
								path_source_state = path_source_state + " , "
										+ next_state.getkeyword();
								pathEntityList_source_state.add(next_state);
								Object[] Obj = new Object[] {
										probability_source_state,
										path_source_state,
										pathEntityList_source_state };
								if (p != 0) {
									TracePaths_next_state.add(Obj);
								}

							}

						}

					}

					else if (Start <= i && i < End) {

						if (next_state.equals(source_state)) {

							if (v_prob > valmax) {
								argmax = v_path;// + " , " +
												// next_state.getUri();
								valmax = v_prob;
								BPath.clear();
								BPath.addAll(path);

							}

							if (traceTree.containsKey(next_state)) {
								TracePaths_next_state.addAll(traceTree
										.get(next_state));

							}

						} else {

						}

					} else if (i > End || i < Start) {

						double p = 0;
						prob *= p;
						v_prob *= p;
						total += prob;
						if (v_prob > valmax) {
							// argmax = v_path + " , " + next_state.getUri();
							valmax = v_prob;
							BPath.clear();
							BPath.addAll(path);
							// BPath.add(next_state);
						}

					}

				}

				if (TracePaths_next_state.size() != 0) {
					TemporaryTraceTree.put(next_state, TracePaths_next_state);
				}
				U.put(next_state, new Object[] { total, argmax, valmax, BPath });
			}

			T = U;

			traceTree = TemporaryTraceTree;

			// }

		}

		for (StateInfo s : states) {
			if (traceTree.containsKey(s)) {

				System.out.println("$$$$$$$$$$$$$$$$" + s.getkeyword()
						+ "$$$$$$$$$$$$$$$$$$$$$$$");

				List<Object[]> t = traceTree.get(s);
				System.out.println(" TraceList size " + t.size());
				for (int i = 0; i < t.size(); i++) {

					String path_state = "";
					double probability_state = 1;

					Object[] Traceobjs = t.get(i);
					probability_state = ((Double) Traceobjs[0]).doubleValue();
					path_state = (String) Traceobjs[1];

					System.out.println(" probability_state "
							+ probability_state);
					System.out.println(" path_state " + path_state);

				}
			}

			else {

			}
		}

		int i = obs.size() - 1;
		Hashtable<StateInfo, Object[]> U = new Hashtable<StateInfo, Object[]>();
		double total = 0;
		String argmax = "";
		double valmax = 0;

		double prob = 1;
		String v_path = "";
		double v_prob = 1;
		List BPath = new ArrayList();

		Hashtable<StateInfo, List<Object[]>> TemporaryTraceTree = new Hashtable<StateInfo, List<Object[]>>();

		for (StateInfo source_state : states) {
			List<Object[]> TracePaths_source_state = new ArrayList();
			List<Object[]> TracePaths_next_state = new ArrayList();

			Object[] objs = T.get(source_state);
			prob = ((Double) objs[0]).doubleValue();
			v_path = (String) objs[1];
			v_prob = ((Double) objs[2]).doubleValue();
			List path = ((List) objs[3]);

			int Start = stateBorders.get(source_state).get(0);
			int End = stateBorders.get(source_state).get(1);

			if (End == i) {
				double p = emit_p.get(source_state).get(i);

				prob *= p;
				v_prob *= p;
				total += prob;

				if (v_prob > valmax) {
					argmax = v_path;
					valmax = v_prob;
					BPath.clear();
					BPath.addAll(path);

				}

				if (p != 0) {

					if (traceTree.containsKey(source_state)) {
						TracePaths_source_state = traceTree.get(source_state);

						for (int j = 0; j < TracePaths_source_state.size(); j++) {

							String path_source_state = "";
							double probability_source_state = 1;

							Object[] Traceobjs = TracePaths_source_state.get(j);
							probability_source_state = ((Double) Traceobjs[0])
									.doubleValue();
							path_source_state = (String) Traceobjs[1];
							List pathEntityList_source_state = ((List) Traceobjs[2]);

							probability_source_state *= p;
							Object[] Obj = new Object[] {
									probability_source_state,
									path_source_state,
									pathEntityList_source_state };
							TracePaths_next_state.add(Obj);

						}
					} else {
						System.out
								.println("......source node does not exist .... ");
					}

					if (TracePaths_next_state.size() != 0) {
						TemporaryTraceTree.put(source_state,
								TracePaths_next_state);

						System.out.println("Start position " + Start);
						System.out.println("End position " + End);

					}

					TracePaths_source_state.clear();

				}

				U.put(source_state,
						new Object[] { total, argmax, valmax, BPath });

			}

			else if (i != End) {
				double p = 0;
				prob *= p;
				v_prob *= p;
				total += prob;
				if (v_prob > valmax) {
					argmax = v_path;
					valmax = v_prob;
					BPath.clear();
					BPath.addAll(path);

				}
				TracePaths_next_state.clear();
			}

		}
		T = U;
		traceTree.clear();
		traceTree = TemporaryTraceTree;
		System.out.println(" size of TraceTree " + traceTree.size());

		System.out.println("saeedeh pay attention Best Path Entites: "
				+ bestPath);

		for (StateInfo state : states) {

			if (T.containsKey(state)) {
				Object[] objs = T.get(state);
				prob = ((Double) objs[0]).doubleValue();
				v_path = (String) objs[1];
				v_prob = ((Double) objs[2]).doubleValue();
				List path = ((List) objs[3]);
				total += prob;
				if (v_prob > valmax) {
					argmax = v_path;
					valmax = v_prob;
					BPath.clear();
					BPath.addAll(path);

				}
			}
		}

		return new Object[] { total, argmax, valmax, BPath };

	}

	

}
