package disambiguation;


/**
 * @author Saedeeh Shekarpour
 */


/*
 * This class inheriting from Hidden Markov Model generates the observation list based on the input query.
 * Observations are valid segments, a valid segment is a segment which has a match in the underlying knowledge base. 
 * The list of potential segments can be generated using two approaches i.e. Greedy and Moderate 
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.didion.jwnl.JWNLException;

import org.aksw.sina.thread.DistanceRunnable;
import org.aksw.sina.thread.MultiRunnerDam;
import org.aksw.sina.thread.ObservationRunnable;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class Observation extends HiddenMarkovModel {

	
	
// #############################################################################
// This method recognizes the set of observations as the valid segments
// #############################################################################
			

/*	public void buildGreedyObservationState(ArrayList keywordQuery){
		int QuerySize	  				 = keywordQuery.size();
		int Start		 				 = 0;
		InstanceRetrieval ER 				 = new InstanceRetrieval();
		OntologyRetrieval owlR = new OntologyRetrieval();
		List<StateInfo> foundRessources = new ArrayList<StateInfo>();
		
		Start			    =	0;
		int Startpointer	=	0;
		int Endpointer		=	0;
		String lastPattern ="";
		while ( Start !=  QuerySize ){
			for(int i = Start; i <  QuerySize ; i++){				
				Startpointer =i;
				String PatternAsk = "";
				String PatternSelect = "";				
				
				for (int j = Start ; j <= i ; j++){
					if( j != Start){
						PatternAsk = PatternAsk +"  and  "+ keywordQuery.get(j);
						PatternSelect = PatternSelect +"  and  "+ keywordQuery.get(j);						
					}
					
					else if( j == Start ){
						PatternAsk = PatternAsk +"  "+ keywordQuery.get(j);
						PatternSelect = PatternSelect +"  "+ keywordQuery.get(j);
					}
				}
				
				//System.out.println("from " + Start + " to " + i + PatternAsk);
				List<StateInfo> Resources = null;
				
				if(ER.checkExistenceOfPatterns(PatternAsk)){
					//System.out.println("  exist  " +  PatternAsk  + "  ");
					lastPattern = PatternAsk;
					if( i ==  QuerySize-1 ){						
						ResourceRetrivalforPattern(PatternAsk, keywordQuery,Start,i+1);
						Start = QuerySize;						
					}										
				}
				else{
					if( i ==  QuerySize-1 ){
						lastPattern = PatternAsk; 	
					}					
					ResourceRetrivalforPattern(lastPattern , keywordQuery, Start,i);						
					Start = i;
					i = QuerySize;
				}// end of else
			}
		}
	}*/
	
	
	
	
	
	
	public void buildModerateObservationState(ArrayList keywordQuery) {
		int querySize = keywordQuery.size();
		int start = 0;
		InstanceRetrieval er = new InstanceRetrieval();
		OntologyRetrieval owlR = new OntologyRetrieval();
		List<Runnable> queryRunnables = new ArrayList<Runnable>();
		while (start != querySize) {
			for (int i = start; i < querySize; i++) {
				String patternAsk = "";
				String patternSelect = "";
				for (int j = start; j <= i; j++) {
					if (j < i && j != start) {

						String keyword = (String) keywordQuery.get(j);
						if (keyword.length() > 3) {
							keyword = keyword + "*";
						}
						patternAsk = patternAsk + "  and  \"" + keyword + "\" ";
						patternSelect = patternSelect + "  and  \"" + keyword
								+ "\" ";
					}

					else if (j == start) {
						String keyword = (String) keywordQuery.get(j);
						String[] words = keyword.split(" ");
						int size = words.length;
						String lastword = words[size - 1];

						if (lastword.length() > 3)
						// if (keyword.length()>3)
						{
							keyword = keyword + "*";
						}
						patternAsk = patternAsk + "   \"" + keyword + "\" ";

						if (j < i) {
							patternSelect = patternSelect + "   \"" + keyword
									+ "\" ";
						}

						else {
							if (i + 1 < querySize) {
								patternSelect = patternSelect + "   \""
										+ keyword + "\" " + "  and not \""
										+ keywordQuery.get(j + 1) + "\"";
							} else {
								patternSelect = patternSelect + "   \""
										+ keyword + "\" ";
							}
						}
					} else if (j == i) {
						String keyword = (String) keywordQuery.get(j);
						String[] words = keyword.split(" ");
						int size = words.length;
						String lastword = words[size - 1];

						if (lastword.length() > 3) {
							keyword = keyword + "*";
						}
						patternAsk = patternAsk + "  and  \"" + keyword + "\" ";
						if (i + 1 < querySize) {
							patternSelect = patternSelect + " and  \""
									+ keyword + "\" " + "  and not \""
									+ keywordQuery.get(j + 1) + "\"";
						} else {
							patternSelect = patternSelect + " and  \""
									+ keyword + "\" ";
						}

					}

					if (start == querySize - 1) {
						if (j - 1 >= 0) {
							patternAsk = patternAsk + " and not \""
									+ keywordQuery.get(j - 1) + "\"";
							patternSelect = patternSelect + " and not  \""
									+ keywordQuery.get(j - 1) + "\"";
						}
					}

				}

				System.out.println("Ask:   " + patternAsk + "  ");
				System.out.println("Select:   " + patternSelect + "  ");

				List<xResourceInfo> resources = null;

				if (!er.checkExistenceOfPatterns(patternAsk)) {
					System.out.println(" noooo exist  " + patternAsk + "  ");
					i = querySize;
				} else {
					// ObservationRunnable obRunnable = new ObservationRunnable(
					// patternAsk, patternSelect, start, i, keywordQuery,
					// observedMap, observationList, owlR, er);
					// queryRunnables.add(obRunnable);
					ArrayList segment = new ArrayList();
					for (int j = start; j <= i; j++) {
						segment.add(keywordQuery.get(j));
					}
					// List<StateInfo> statesList = null;
					// observedMap.put(segment, resources);
					observationList.add(segment);
				}
			}
			start++;
		}

		// Runnable[] runnables = new Runnable[queryRunnables.size()];
		// MultiRunnerDam runnerDam = new MultiRunnerDam(
		// queryRunnables.toArray(runnables));
		// runnerDam.run();

		System.out.println(" list of segments from observation list  ");

		for (int i = 0; i < observationList.size(); i++) {

			System.out.println(observationList.get(i));
			/*
			 * List<ResourceInfo> r = observedMap.get(observationList.get(i));
			 * 
			 * System.out.println(" size of retrieved iris =   " + r.size());
			 * for (int j = 0; j < r.size(); j++) {
			 * System.out.println(r.get(j).getUri() + "     " +
			 * r.get(j).getLabel() + "  " +
			 * r.get(j).getStringSimilarityScore()); }
			 */
		}
	}

	
}
