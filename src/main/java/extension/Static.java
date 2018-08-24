package extension;


import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.reasoner.NodeSet;



import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;


public class Static {
	private volatile static PelletReasoner reasoner = null;
	
	public static synchronized NodeSet<OWLClass> getSubClasses(String classTarget) throws OWLOntologyCreationException {
		
		if(reasoner == null) {
			reasoner = PelletReasonerFactory.getInstance().createReasoner(DBPediaOWL.getInstance());
		}
		
		IRI iriClass = IRI.create(classTarget);
		OWLClass owlClassTarget	= OWLDataFactory.getInstance().getOWLClass(iriClass);		
		NodeSet<OWLClass> NS = reasoner.getSubClasses(owlClassTarget, false);
		
		return NS;
	}

	
}
