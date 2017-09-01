package org.aksw.sina.thread;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.collections15.ListUtils;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import disambiguation.InstanceRetrieval;
import disambiguation.OntologyRetrieval;
import disambiguation.StateInfo;
import disambiguation.StateInfo;

public class ObservationRunnable implements Runnable{
	
	private String patternAsk;
	private String patternSelect;
    private int start;
    private int i;
    private ArrayList keywordQuery;
	private Hashtable<ArrayList, List<StateInfo>> observedMap;	
	private List<ArrayList> observationList;
    private OntologyRetrieval owlR;
    private InstanceRetrieval er;
    
    public ObservationRunnable(String patternAsk, String patternSelect, 
    		int start, int i, 
    		ArrayList keywordQuery,
    		Hashtable<ArrayList,
    		List<StateInfo>> observedMap,
    		List<ArrayList> observationList,
    		OntologyRetrieval owlR,    		
    		InstanceRetrieval er) {
		this.patternAsk = patternAsk;
		this.patternSelect = patternSelect;
		this.start = start;
		this.i = i;
		this.keywordQuery = keywordQuery;
		this.observedMap = observedMap;
		this.observationList = observationList;
		this.owlR = owlR;
		this.er = er;		
	}
    
	@Override
	public void run() {
		System.out.println("Ask:   " +  patternAsk  + "  ");
		System.out.println("Select:   " +  patternSelect  + "  ");
		
		List<StateInfo> resources = null;
				
	    ArrayList segment = new ArrayList();
		for (int j=start; j<= i; j++) {
			segment.add(keywordQuery.get(j));
		}
		
		try {						
			resources = owlR.getMappingOwlClasses(segment);
			resources.addAll(owlR.getMappingOwlObjectProperties(segment));
			resources.addAll(owlR.getMappingOwlDataTypeProperties(segment));
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
		resources.addAll(er.getIRIsForPatterns(patternSelect ,segment) );
		
		System.out.println("resource size " + resources.size());
		
		if(resources.size() !=0) {			
			synchronized (observedMap) {
				observedMap.put(segment, resources);
			}			
			synchronized (observationList) {
				observationList.add(segment);
			}			
		}		
	}

}
