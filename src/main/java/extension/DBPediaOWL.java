package extension;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDatatypeDefinitionAxiom;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointUnionAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLHasKeyAxiom;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLIndividualAxiom;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLLogicalAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLNegativeDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLNegativeObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLObjectVisitor;
import org.semanticweb.owlapi.model.OWLObjectVisitorEx;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSameIndividualAxiom;
import org.semanticweb.owlapi.model.OWLSubAnnotationPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubDataPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.UnknownOWLOntologyException;

public class DBPediaOWL implements OWLOntology{
	private static OWLOntology dBpediaOWL = null;
	private static DBPediaOWL instance = null;
	
	public static synchronized DBPediaOWL getInstance() throws OWLOntologyCreationException {
		if(instance == null) {
			OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
			URL ontologyURL = Static.class.getResource("/dbpedia_3.9.owl");
			IRI iri = null;
			try {
				iri = IRI.create(ontologyURL);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			dBpediaOWL = manager.loadOntologyFromOntologyDocument(iri);
			instance =  new DBPediaOWL();
		}
		return instance;
	}

	@Override
	public synchronized void accept(OWLObjectVisitor arg0) {
		dBpediaOWL.accept(arg0);
	}

	@Override
	public synchronized <O> O accept(OWLObjectVisitorEx<O> arg0) {
		return dBpediaOWL.accept(arg0);
	}

	@Override
	public synchronized Set<OWLClassExpression> getNestedClassExpressions() {
		return dBpediaOWL.getNestedClassExpressions();
	}

	@Override
	public synchronized boolean isBottomEntity() {
		return dBpediaOWL.isBottomEntity();
	}

	@Override
	public synchronized boolean isTopEntity() {
		return dBpediaOWL.isTopEntity();
	}

	@Override
	public synchronized int compareTo(OWLObject o) {
		return dBpediaOWL.compareTo(o);
	}

	@Override
	public synchronized boolean containsAnnotationPropertyInSignature(IRI arg0) {
		return dBpediaOWL.containsAnnotationPropertyInSignature(arg0);
	}

	@Override
	public synchronized boolean containsAnnotationPropertyInSignature(IRI arg0, boolean arg1) {
		return dBpediaOWL.containsAnnotationPropertyInSignature(arg0, arg1);
	}

	@Override
	public synchronized boolean containsAxiom(OWLAxiom arg0) {
		return dBpediaOWL.containsAxiom(arg0);
	}

	@Override
	public synchronized boolean containsAxiom(OWLAxiom arg0, boolean arg1) {
		return dBpediaOWL.containsAxiomIgnoreAnnotations(arg0, arg1);
	}

	@Override
	public synchronized boolean containsAxiomIgnoreAnnotations(OWLAxiom arg0) {
		return dBpediaOWL.containsAxiomIgnoreAnnotations(arg0);
	}

	@Override
	public synchronized boolean containsAxiomIgnoreAnnotations(OWLAxiom arg0, boolean arg1) {
		return dBpediaOWL.containsAxiomIgnoreAnnotations(arg0, arg1);
	}

	@Override
	public synchronized boolean containsClassInSignature(IRI arg0) {
		return dBpediaOWL.containsClassInSignature(arg0);
	}

	@Override
	public synchronized boolean containsClassInSignature(IRI arg0, boolean arg1) {
		return dBpediaOWL.containsClassInSignature(arg0, arg1);
	}

	@Override
	public synchronized boolean containsDataPropertyInSignature(IRI arg0) {
		return dBpediaOWL.containsDataPropertyInSignature(arg0);
	}

	@Override
	public synchronized boolean containsDataPropertyInSignature(IRI arg0, boolean arg1) {
		return dBpediaOWL.containsDatatypeInSignature(arg0, arg1);
	}

	@Override
	public synchronized boolean containsDatatypeInSignature(IRI arg0) {
		return dBpediaOWL.containsDataPropertyInSignature(arg0);
	}

	@Override
	public synchronized boolean containsDatatypeInSignature(IRI arg0, boolean arg1) {
		return dBpediaOWL.containsDataPropertyInSignature(arg0, arg1);
	}

	@Override
	public synchronized boolean containsEntityInSignature(OWLEntity arg0) {
		return dBpediaOWL.containsEntityInSignature(arg0);
	}

	@Override
	public synchronized boolean containsEntityInSignature(IRI arg0) {
		return dBpediaOWL.containsEntityInSignature(arg0);
	}

	@Override
	public synchronized boolean containsEntityInSignature(OWLEntity arg0, boolean arg1) {
		return dBpediaOWL.containsEntityInSignature(arg0, arg1);
	}

	@Override
	public synchronized boolean containsEntityInSignature(IRI arg0, boolean arg1) {
		return dBpediaOWL.containsEntityInSignature(arg0, arg1);
	}

	@Override
	public synchronized boolean containsIndividualInSignature(IRI arg0) {
		return dBpediaOWL.containsIndividualInSignature(arg0);
	}

	@Override
	public synchronized boolean containsIndividualInSignature(IRI arg0, boolean arg1) {
		return dBpediaOWL.containsIndividualInSignature(arg0, arg1);
	}

	@Override
	public synchronized boolean containsObjectPropertyInSignature(IRI arg0) {
		return dBpediaOWL.containsObjectPropertyInSignature(arg0);
	}

	@Override
	public synchronized boolean containsObjectPropertyInSignature(IRI arg0, boolean arg1) {
		return dBpediaOWL.containsObjectPropertyInSignature(arg0, arg1);
	}

	@Override
	public synchronized Set<OWLAxiom> getABoxAxioms(boolean arg0) {
		return dBpediaOWL.getABoxAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLAnnotationAssertionAxiom> getAnnotationAssertionAxioms(
			OWLAnnotationSubject arg0) {
		return dBpediaOWL.getAnnotationAssertionAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLAnnotationProperty> getAnnotationPropertiesInSignature() {
		return dBpediaOWL.getAnnotationPropertiesInSignature();
	}

	@Override
	public synchronized Set<OWLAnnotationPropertyDomainAxiom> getAnnotationPropertyDomainAxioms(
			OWLAnnotationProperty arg0) {
		return dBpediaOWL.getAnnotationPropertyDomainAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLAnnotationPropertyRangeAxiom> getAnnotationPropertyRangeAxioms(
			OWLAnnotationProperty arg0) {
		return dBpediaOWL.getAnnotationPropertyRangeAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLAnnotation> getAnnotations() {
		return dBpediaOWL.getAnnotations();
	}

	@Override
	public synchronized Set<OWLAsymmetricObjectPropertyAxiom> getAsymmetricObjectPropertyAxioms(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getAsymmetricObjectPropertyAxioms(arg0);
	}

	@Override
	public synchronized int getAxiomCount() {
		return dBpediaOWL.getAxiomCount();
	}

	@Override
	public synchronized <T extends OWLAxiom> int getAxiomCount(AxiomType<T> arg0) {
		return dBpediaOWL.getAxiomCount(arg0);
	}

	@Override
	public synchronized <T extends OWLAxiom> int getAxiomCount(AxiomType<T> arg0,
			boolean arg1) {
		return dBpediaOWL.getAxiomCount(arg0, arg1);
	}

	@Override
	public synchronized Set<OWLAxiom> getAxioms() {
		return dBpediaOWL.getAxioms();
	}

	@Override
	public synchronized <T extends OWLAxiom> Set<T> getAxioms(AxiomType<T> arg0) {
		return dBpediaOWL.getAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLClassAxiom> getAxioms(OWLClass arg0) {
		return dBpediaOWL.getAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLObjectPropertyAxiom> getAxioms(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLDataPropertyAxiom> getAxioms(OWLDataProperty arg0) {
		return dBpediaOWL.getAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLIndividualAxiom> getAxioms(OWLIndividual arg0) {
		return dBpediaOWL.getAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLAnnotationAxiom> getAxioms(OWLAnnotationProperty arg0) {
		return dBpediaOWL.getAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLDatatypeDefinitionAxiom> getAxioms(OWLDatatype arg0) {
		return dBpediaOWL.getAxioms(arg0);
	}

	@Override
	public synchronized <T extends OWLAxiom> Set<T> getAxioms(AxiomType<T> arg0, boolean arg1) {
		return dBpediaOWL.getAxioms(arg0, arg1);
	}

	@Override
	public synchronized Set<OWLAxiom> getAxiomsIgnoreAnnotations(OWLAxiom arg0) {
		return dBpediaOWL.getAxiomsIgnoreAnnotations(arg0);
	}

	@Override
	public synchronized Set<OWLAxiom> getAxiomsIgnoreAnnotations(OWLAxiom arg0, boolean arg1) {
		return dBpediaOWL.getAxiomsIgnoreAnnotations(arg0, arg1);
	}

	@Override
	public synchronized Set<OWLClassAssertionAxiom> getClassAssertionAxioms(
			OWLIndividual arg0) {
		return dBpediaOWL.getClassAssertionAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLClassAssertionAxiom> getClassAssertionAxioms(
			OWLClassExpression arg0) {
		return dBpediaOWL.getClassAssertionAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLClass> getClassesInSignature() {
		return dBpediaOWL.getClassesInSignature();
	}

	@Override
	public synchronized Set<OWLClass> getClassesInSignature(boolean arg0) {
		return dBpediaOWL.getClassesInSignature(arg0);
	}

	@Override
	public synchronized Set<OWLDataProperty> getDataPropertiesInSignature() {
		return dBpediaOWL.getDataPropertiesInSignature();
	}

	@Override
	public synchronized Set<OWLDataProperty> getDataPropertiesInSignature(boolean arg0) {
		return dBpediaOWL.getDataPropertiesInSignature(arg0);
	}

	@Override
	public synchronized Set<OWLDataPropertyAssertionAxiom> getDataPropertyAssertionAxioms(
			OWLIndividual arg0) {
		return dBpediaOWL.getDataPropertyAssertionAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLDataPropertyDomainAxiom> getDataPropertyDomainAxioms(
			OWLDataProperty arg0) {
		return dBpediaOWL.getDataPropertyDomainAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLDataPropertyRangeAxiom> getDataPropertyRangeAxioms(
			OWLDataProperty arg0) {
		return dBpediaOWL.getDataPropertyRangeAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLSubDataPropertyOfAxiom> getDataSubPropertyAxiomsForSubProperty(
			OWLDataProperty arg0) {
		return dBpediaOWL.getDataSubPropertyAxiomsForSubProperty(arg0);
	}

	@Override
	public synchronized Set<OWLSubDataPropertyOfAxiom> getDataSubPropertyAxiomsForSuperProperty(
			OWLDataPropertyExpression arg0) {
		return dBpediaOWL.getDataSubPropertyAxiomsForSuperProperty(arg0);
	}

	@Override
	public synchronized Set<OWLDatatypeDefinitionAxiom> getDatatypeDefinitions(
			OWLDatatype arg0) {
		return dBpediaOWL.getDatatypeDefinitions(arg0);
	}

	@Override
	public synchronized Set<OWLDatatype> getDatatypesInSignature() {
		return dBpediaOWL.getDatatypesInSignature();
	}

	@Override
	public synchronized Set<OWLDatatype> getDatatypesInSignature(boolean arg0) {
		return dBpediaOWL.getDatatypesInSignature(arg0);
	}

	@Override
	public synchronized Set<OWLDeclarationAxiom> getDeclarationAxioms(OWLEntity arg0) {
		return dBpediaOWL.getDeclarationAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLDifferentIndividualsAxiom> getDifferentIndividualAxioms(
			OWLIndividual arg0) {
		return dBpediaOWL.getDifferentIndividualAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLOntology> getDirectImports()
			throws UnknownOWLOntologyException {
		return dBpediaOWL.getDirectImports();
	}

	@Override
	public synchronized Set<IRI> getDirectImportsDocuments()
			throws UnknownOWLOntologyException {
		return dBpediaOWL.getDirectImportsDocuments();
	}

	@Override
	public synchronized Set<OWLDisjointClassesAxiom> getDisjointClassesAxioms(OWLClass arg0) {
		return dBpediaOWL.getDisjointClassesAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLDisjointDataPropertiesAxiom> getDisjointDataPropertiesAxioms(
			OWLDataProperty arg0) {
		return dBpediaOWL.getDisjointDataPropertiesAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLDisjointObjectPropertiesAxiom> getDisjointObjectPropertiesAxioms(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getDisjointObjectPropertiesAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLDisjointUnionAxiom> getDisjointUnionAxioms(OWLClass arg0) {
		return dBpediaOWL.getDisjointUnionAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLEntity> getEntitiesInSignature(IRI arg0) {
		return dBpediaOWL.getEntitiesInSignature(arg0);
	}

	@Override
	public synchronized Set<OWLEntity> getEntitiesInSignature(IRI arg0, boolean arg1) {
		return dBpediaOWL.getEntitiesInSignature(arg0, arg1);
	}

	@Override
	public synchronized Set<OWLEquivalentClassesAxiom> getEquivalentClassesAxioms(
			OWLClass arg0) {
		return dBpediaOWL.getEquivalentClassesAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLEquivalentDataPropertiesAxiom> getEquivalentDataPropertiesAxioms(
			OWLDataProperty arg0) {
		return dBpediaOWL.getEquivalentDataPropertiesAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLEquivalentObjectPropertiesAxiom> getEquivalentObjectPropertiesAxioms(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getEquivalentObjectPropertiesAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLFunctionalDataPropertyAxiom> getFunctionalDataPropertyAxioms(
			OWLDataPropertyExpression arg0) {
		return dBpediaOWL.getFunctionalDataPropertyAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLFunctionalObjectPropertyAxiom> getFunctionalObjectPropertyAxioms(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getFunctionalObjectPropertyAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLClassAxiom> getGeneralClassAxioms() {
		return dBpediaOWL.getGeneralClassAxioms();
	}

	@Override
	public synchronized Set<OWLHasKeyAxiom> getHasKeyAxioms(OWLClass arg0) {
		return dBpediaOWL.getHasKeyAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLOntology> getImports() throws UnknownOWLOntologyException {
		return dBpediaOWL.getImports();
	}

	@Override
	public synchronized Set<OWLOntology> getImportsClosure()
			throws UnknownOWLOntologyException {
		return dBpediaOWL.getImportsClosure();
	}

	@Override
	public synchronized Set<OWLImportsDeclaration> getImportsDeclarations() {
		return dBpediaOWL.getImportsDeclarations();
	}

	@Override
	public synchronized Set<OWLNamedIndividual> getIndividualsInSignature() {
		return dBpediaOWL.getIndividualsInSignature();
	}

	@Override
	public synchronized Set<OWLNamedIndividual> getIndividualsInSignature(boolean arg0) {
		return dBpediaOWL.getIndividualsInSignature(arg0);
	}

	@Override
	public synchronized Set<OWLInverseFunctionalObjectPropertyAxiom> getInverseFunctionalObjectPropertyAxioms(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getInverseFunctionalObjectPropertyAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLInverseObjectPropertiesAxiom> getInverseObjectPropertyAxioms(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getInverseObjectPropertyAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLIrreflexiveObjectPropertyAxiom> getIrreflexiveObjectPropertyAxioms(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getIrreflexiveObjectPropertyAxioms(arg0);
	}

	@Override
	public synchronized int getLogicalAxiomCount() {
		return dBpediaOWL.getLogicalAxiomCount();
	}

	@Override
	public synchronized Set<OWLLogicalAxiom> getLogicalAxioms() {
		return dBpediaOWL.getLogicalAxioms();
	}

	@Override
	public synchronized Set<OWLNegativeDataPropertyAssertionAxiom> getNegativeDataPropertyAssertionAxioms(
			OWLIndividual arg0) {
		return dBpediaOWL.getNegativeDataPropertyAssertionAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLNegativeObjectPropertyAssertionAxiom> getNegativeObjectPropertyAssertionAxioms(
			OWLIndividual arg0) {
		return dBpediaOWL.getNegativeObjectPropertyAssertionAxioms(arg0);
	}

	@Override
	public synchronized OWLOntologyManager getOWLOntologyManager() {
		return dBpediaOWL.getOWLOntologyManager();
	}

	@Override
	public synchronized Set<OWLObjectProperty> getObjectPropertiesInSignature() {
		return dBpediaOWL.getObjectPropertiesInSignature();
	}

	@Override
	public synchronized Set<OWLObjectProperty> getObjectPropertiesInSignature(boolean arg0) {
		return dBpediaOWL.getObjectPropertiesInSignature(arg0);
	}

	@Override
	public synchronized Set<OWLObjectPropertyAssertionAxiom> getObjectPropertyAssertionAxioms(
			OWLIndividual arg0) {
		return dBpediaOWL.getObjectPropertyAssertionAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLObjectPropertyDomainAxiom> getObjectPropertyDomainAxioms(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getObjectPropertyDomainAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLObjectPropertyRangeAxiom> getObjectPropertyRangeAxioms(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getObjectPropertyRangeAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLSubObjectPropertyOfAxiom> getObjectSubPropertyAxiomsForSubProperty(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getObjectSubPropertyAxiomsForSubProperty(arg0);
	}

	@Override
	public synchronized Set<OWLSubObjectPropertyOfAxiom> getObjectSubPropertyAxiomsForSuperProperty(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getObjectSubPropertyAxiomsForSubProperty(arg0);
	}

	@Override
	public synchronized OWLOntologyID getOntologyID() {
		return dBpediaOWL.getOntologyID();
	}

	@Override
	public synchronized Set<OWLAxiom> getRBoxAxioms(boolean arg0) {
		return dBpediaOWL.getRBoxAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLAnonymousIndividual> getReferencedAnonymousIndividuals() {
		return dBpediaOWL.getReferencedAnonymousIndividuals();
	}

	@Override
	public synchronized Set<OWLAxiom> getReferencingAxioms(OWLEntity arg0) {
		return dBpediaOWL.getReferencingAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLAxiom> getReferencingAxioms(OWLAnonymousIndividual arg0) {
		return dBpediaOWL.getReferencingAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLAxiom> getReferencingAxioms(OWLEntity arg0, boolean arg1) {
		return dBpediaOWL.getReferencingAxioms(arg0, arg1);
	}

	@Override
	public synchronized Set<OWLReflexiveObjectPropertyAxiom> getReflexiveObjectPropertyAxioms(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getReflexiveObjectPropertyAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLSameIndividualAxiom> getSameIndividualAxioms(
			OWLIndividual arg0) {
		return dBpediaOWL.getSameIndividualAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLEntity> getSignature() {
		return dBpediaOWL.getSignature();
	}

	@Override
	public synchronized Set<OWLEntity> getSignature(boolean arg0) {
		return dBpediaOWL.getSignature(arg0);
	}

	@Override
	public synchronized Set<OWLSubAnnotationPropertyOfAxiom> getSubAnnotationPropertyOfAxioms(
			OWLAnnotationProperty arg0) {
		return dBpediaOWL.getSubAnnotationPropertyOfAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLSubClassOfAxiom> getSubClassAxiomsForSubClass(OWLClass arg0) {
		return dBpediaOWL.getSubClassAxiomsForSubClass(arg0);
	}

	@Override
	public synchronized Set<OWLSubClassOfAxiom> getSubClassAxiomsForSuperClass(OWLClass arg0) {
		return dBpediaOWL.getSubClassAxiomsForSuperClass(arg0);
	}

	@Override
	public synchronized Set<OWLSymmetricObjectPropertyAxiom> getSymmetricObjectPropertyAxioms(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getSymmetricObjectPropertyAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLAxiom> getTBoxAxioms(boolean arg0) {
		return dBpediaOWL.getTBoxAxioms(arg0);
	}

	@Override
	public synchronized Set<OWLTransitiveObjectPropertyAxiom> getTransitiveObjectPropertyAxioms(
			OWLObjectPropertyExpression arg0) {
		return dBpediaOWL.getTransitiveObjectPropertyAxioms(arg0);
	}

	@Override
	public synchronized boolean isAnonymous() {
		return dBpediaOWL.isAnonymous();
	}

	@Override
	public synchronized boolean isDeclared(OWLEntity arg0) {
		return dBpediaOWL.isDeclared(arg0);
	}

	@Override
	public synchronized boolean isDeclared(OWLEntity arg0, boolean arg1) {
		return dBpediaOWL.isDeclared(arg0, arg1);
	}

	@Override
	public synchronized boolean isEmpty() {
		return dBpediaOWL.isEmpty();
	}
}
