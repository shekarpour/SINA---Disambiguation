package extension;

import java.util.List;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.OWLFacet;

public class OWLDataFactory implements org.semanticweb.owlapi.model.OWLDataFactory{
	
	private volatile static OWLDataFactory instance = null;
	private volatile static org.semanticweb.owlapi.model.OWLDataFactory dataFactory = null;
	
	public static synchronized OWLDataFactory getInstance() throws OWLOntologyCreationException {
		if(instance == null) {
			OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
			dataFactory = manager.getOWLDataFactory();
			instance = new OWLDataFactory();
		}
		return instance;
	}

	public synchronized OWLClass getOWLClass(IRI iriClass) {
		return dataFactory.getOWLClass(iriClass);
	}

	public synchronized OWLClass getOWLNothing() {
		return dataFactory.getOWLNothing();
	}

	public synchronized OWLClass getOWLThing() {
		return dataFactory.getOWLThing();
	}

	@Override
	public synchronized SWRLBuiltInAtom getSWRLBuiltInAtom(IRI arg0, List<SWRLDArgument> arg1) {
		return dataFactory.getSWRLBuiltInAtom(arg0, arg1);
	}

	@Override
	public synchronized SWRLClassAtom getSWRLClassAtom(OWLClassExpression arg0,
			SWRLIArgument arg1) {
		return dataFactory.getSWRLClassAtom(arg0, arg1);
	}

	@Override
	public synchronized SWRLDataPropertyAtom getSWRLDataPropertyAtom(
			OWLDataPropertyExpression arg0, SWRLIArgument arg1,
			SWRLDArgument arg2) {
		return dataFactory.getSWRLDataPropertyAtom(arg0, arg1, arg2);
	}

	@Override
	public synchronized SWRLDataRangeAtom getSWRLDataRangeAtom(OWLDataRange arg0,
			SWRLDArgument arg1) {
		return dataFactory.getSWRLDataRangeAtom(arg0, arg1);
	}

	@Override
	public synchronized SWRLDifferentIndividualsAtom getSWRLDifferentIndividualsAtom(
			SWRLIArgument arg0, SWRLIArgument arg1) {
		return dataFactory.getSWRLDifferentIndividualsAtom(arg0, arg1);
	}

	@Override
	public synchronized SWRLIndividualArgument getSWRLIndividualArgument(OWLIndividual arg0) {
		return dataFactory.getSWRLIndividualArgument(arg0);
	}

	@Override
	public synchronized SWRLLiteralArgument getSWRLLiteralArgument(OWLLiteral arg0) {
		return dataFactory.getSWRLLiteralArgument(arg0);
	}

	@Override
	public synchronized SWRLObjectPropertyAtom getSWRLObjectPropertyAtom(
			OWLObjectPropertyExpression arg0, SWRLIArgument arg1,
			SWRLIArgument arg2) {
		return dataFactory.getSWRLObjectPropertyAtom(arg0, arg1, arg2);
	}

	@Override
	public synchronized SWRLRule getSWRLRule(Set<? extends SWRLAtom> arg0,
			Set<? extends SWRLAtom> arg1) {
		return dataFactory.getSWRLRule(arg0, arg1);
	}

	@Override
	@Deprecated
	public synchronized SWRLRule getSWRLRule(IRI arg0, Set<? extends SWRLAtom> arg1,
			Set<? extends SWRLAtom> arg2) {
		return dataFactory.getSWRLRule(arg0, arg1, arg2);
	}

	@Override
	@Deprecated
	public synchronized SWRLRule getSWRLRule(NodeID arg0, Set<? extends SWRLAtom> arg1,
			Set<? extends SWRLAtom> arg2) {
		return dataFactory.getSWRLRule(arg0, arg1, arg2);
	}

	@Override
	public synchronized SWRLRule getSWRLRule(Set<? extends SWRLAtom> arg0,
			Set<? extends SWRLAtom> arg1, Set<OWLAnnotation> arg2) {
		return dataFactory.getSWRLRule(arg0, arg1, arg2);
	}

	@Override
	public synchronized SWRLSameIndividualAtom getSWRLSameIndividualAtom(SWRLIArgument arg0,
			SWRLIArgument arg1) {
		return dataFactory.getSWRLSameIndividualAtom(arg0, arg1);
	}

	@Override
	public synchronized SWRLVariable getSWRLVariable(IRI arg0) {
		return dataFactory.getSWRLVariable(arg0);
	}

	@Override
	public synchronized OWLDatatype getBooleanOWLDatatype() {
		return dataFactory.getBooleanOWLDatatype();
	}

	@Override
	public synchronized OWLAnnotationAssertionAxiom getDeprecatedOWLAnnotationAssertionAxiom(
			IRI arg0) {
		return dataFactory.getDeprecatedOWLAnnotationAssertionAxiom(arg0);
	}

	@Override
	public synchronized OWLDatatype getDoubleOWLDatatype() {
		return dataFactory.getBooleanOWLDatatype();
	}

	@Override
	public synchronized OWLDatatype getFloatOWLDatatype() {
		return dataFactory.getBooleanOWLDatatype();
	}

	@Override
	public synchronized OWLDatatype getIntegerOWLDatatype() {
		return dataFactory.getIntegerOWLDatatype();
	}

	@Override
	public synchronized OWLAnnotation getOWLAnnotation(OWLAnnotationProperty arg0,
			OWLAnnotationValue arg1) {
		return dataFactory.getOWLAnnotation(arg0, arg1);
	}

	@Override
	public synchronized OWLAnnotation getOWLAnnotation(OWLAnnotationProperty arg0,
			OWLAnnotationValue arg1, Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLAnnotation(arg0, arg1);
	}

	@Override
	public synchronized OWLAnnotationAssertionAxiom getOWLAnnotationAssertionAxiom(
			OWLAnnotationSubject arg0, OWLAnnotation arg1) {
		return dataFactory.getOWLAnnotationAssertionAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLAnnotationAssertionAxiom getOWLAnnotationAssertionAxiom(
			OWLAnnotationProperty arg0, OWLAnnotationSubject arg1,
			OWLAnnotationValue arg2) {
		return dataFactory.getOWLAnnotationAssertionAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLAnnotationAssertionAxiom getOWLAnnotationAssertionAxiom(
			OWLAnnotationSubject arg0, OWLAnnotation arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLAnnotationAssertionAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLAnnotationAssertionAxiom getOWLAnnotationAssertionAxiom(
			OWLAnnotationProperty arg0, OWLAnnotationSubject arg1,
			OWLAnnotationValue arg2, Set<? extends OWLAnnotation> arg3) {
		return dataFactory.getOWLAnnotationAssertionAxiom(arg0, arg1, arg2, arg3);
	}

	@Override
	public synchronized OWLAnnotationProperty getOWLAnnotationProperty(IRI arg0) {
		return dataFactory.getOWLAnnotationProperty(arg0);
	}

	@Override
	public synchronized OWLAnnotationProperty getOWLAnnotationProperty(String arg0,
			PrefixManager arg1) {
		return dataFactory.getOWLAnnotationProperty(arg0, arg1);
	}

	@Override
	public synchronized OWLAnnotationPropertyDomainAxiom getOWLAnnotationPropertyDomainAxiom(
			OWLAnnotationProperty arg0, IRI arg1) {
		return dataFactory.getOWLAnnotationPropertyDomainAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLAnnotationPropertyDomainAxiom getOWLAnnotationPropertyDomainAxiom(
			OWLAnnotationProperty arg0, IRI arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLAnnotationPropertyDomainAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLAnnotationPropertyRangeAxiom getOWLAnnotationPropertyRangeAxiom(
			OWLAnnotationProperty arg0, IRI arg1) {
		return dataFactory.getOWLAnnotationPropertyRangeAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLAnnotationPropertyRangeAxiom getOWLAnnotationPropertyRangeAxiom(
			OWLAnnotationProperty arg0, IRI arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLAnnotationPropertyRangeAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLAnonymousIndividual getOWLAnonymousIndividual() {
		return dataFactory.getOWLAnonymousIndividual();
	}

	@Override
	public synchronized OWLAnonymousIndividual getOWLAnonymousIndividual(String arg0) {
		return dataFactory.getOWLAnonymousIndividual(arg0);
	}

	@Override
	public synchronized OWLAsymmetricObjectPropertyAxiom getOWLAsymmetricObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0) {
		return dataFactory.getOWLAsymmetricObjectPropertyAxiom(arg0);
	}

	@Override
	public synchronized OWLAsymmetricObjectPropertyAxiom getOWLAsymmetricObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0, Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLAsymmetricObjectPropertyAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLAnnotationProperty getOWLBackwardCompatibleWith() {
		return dataFactory.getOWLBackwardCompatibleWith();
	}

	@Override
	public synchronized OWLDataProperty getOWLBottomDataProperty() {
		return dataFactory.getOWLBottomDataProperty();
	}

	@Override
	public synchronized OWLObjectProperty getOWLBottomObjectProperty() {
		return dataFactory.getOWLBottomObjectProperty();
	}

	@Override
	public synchronized OWLClass getOWLClass(String arg0, PrefixManager arg1) {
		return dataFactory.getOWLClass(arg0, arg1);
	}

	@Override
	public synchronized OWLClassAssertionAxiom getOWLClassAssertionAxiom(
			OWLClassExpression arg0, OWLIndividual arg1) {
		return dataFactory.getOWLClassAssertionAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLClassAssertionAxiom getOWLClassAssertionAxiom(
			OWLClassExpression arg0, OWLIndividual arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLClassAssertionAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLDataAllValuesFrom getOWLDataAllValuesFrom(
			OWLDataPropertyExpression arg0, OWLDataRange arg1) {
		return dataFactory.getOWLDataAllValuesFrom(arg0, arg1);
	}

	@Override
	public synchronized OWLDataComplementOf getOWLDataComplementOf(OWLDataRange arg0) {
		return dataFactory.getOWLDataComplementOf(arg0);
	}

	@Override
	public synchronized OWLDataExactCardinality getOWLDataExactCardinality(int arg0,
			OWLDataPropertyExpression arg1) {
		return dataFactory.getOWLDataExactCardinality(arg0, arg1);
	}

	@Override
	public synchronized OWLDataExactCardinality getOWLDataExactCardinality(int arg0,
			OWLDataPropertyExpression arg1, OWLDataRange arg2) {
		return dataFactory.getOWLDataExactCardinality(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLDataHasValue getOWLDataHasValue(OWLDataPropertyExpression arg0,
			OWLLiteral arg1) {
		return dataFactory.getOWLDataHasValue(arg0, arg1);
	}

	@Override
	public synchronized OWLDataIntersectionOf getOWLDataIntersectionOf(
			Set<? extends OWLDataRange> arg0) {
		return dataFactory.getOWLDataIntersectionOf(arg0);
	}

	@Override
	public synchronized OWLDataIntersectionOf getOWLDataIntersectionOf(OWLDataRange... arg0) {
		return dataFactory.getOWLDataIntersectionOf(arg0);
	}

	@Override
	public synchronized OWLDataMaxCardinality getOWLDataMaxCardinality(int arg0,
			OWLDataPropertyExpression arg1) {
		return dataFactory.getOWLDataMaxCardinality(arg0, arg1);
	}

	@Override
	public synchronized OWLDataMaxCardinality getOWLDataMaxCardinality(int arg0,
			OWLDataPropertyExpression arg1, OWLDataRange arg2) {
		return dataFactory.getOWLDataMaxCardinality(arg0, arg1);
	}

	@Override
	public synchronized OWLDataMinCardinality getOWLDataMinCardinality(int arg0,
			OWLDataPropertyExpression arg1) {
		return dataFactory.getOWLDataMinCardinality(arg0, arg1);
	}

	@Override
	public synchronized OWLDataMinCardinality getOWLDataMinCardinality(int arg0,
			OWLDataPropertyExpression arg1, OWLDataRange arg2) {
		return dataFactory.getOWLDataMinCardinality(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLDataOneOf getOWLDataOneOf(Set<? extends OWLLiteral> arg0) {
		return dataFactory.getOWLDataOneOf(arg0);
	}

	@Override
	public synchronized OWLDataOneOf getOWLDataOneOf(OWLLiteral... arg0) {
		return dataFactory.getOWLDataOneOf(arg0);
	}

	@Override
	public synchronized OWLDataProperty getOWLDataProperty(IRI arg0) {
		return dataFactory.getOWLDataProperty(arg0);
	}

	@Override
	public OWLDataProperty getOWLDataProperty(String arg0, PrefixManager arg1) {
		return dataFactory.getOWLDataProperty(arg0, arg1);
	}

	@Override
	public synchronized OWLDataPropertyAssertionAxiom getOWLDataPropertyAssertionAxiom(
			OWLDataPropertyExpression arg0, OWLIndividual arg1, OWLLiteral arg2) {
		return dataFactory.getOWLDataPropertyAssertionAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLDataPropertyAssertionAxiom getOWLDataPropertyAssertionAxiom(
			OWLDataPropertyExpression arg0, OWLIndividual arg1, int arg2) {
		return dataFactory.getOWLDataPropertyAssertionAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLDataPropertyAssertionAxiom getOWLDataPropertyAssertionAxiom(
			OWLDataPropertyExpression arg0, OWLIndividual arg1, double arg2) {
		return dataFactory.getOWLDataPropertyAssertionAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLDataPropertyAssertionAxiom getOWLDataPropertyAssertionAxiom(
			OWLDataPropertyExpression arg0, OWLIndividual arg1, float arg2) {
		return dataFactory.getOWLDataPropertyAssertionAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLDataPropertyAssertionAxiom getOWLDataPropertyAssertionAxiom(
			OWLDataPropertyExpression arg0, OWLIndividual arg1, boolean arg2) {
		return dataFactory.getOWLDataPropertyAssertionAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLDataPropertyAssertionAxiom getOWLDataPropertyAssertionAxiom(
			OWLDataPropertyExpression arg0, OWLIndividual arg1, String arg2) {
		return dataFactory.getOWLDataPropertyAssertionAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLDataPropertyAssertionAxiom getOWLDataPropertyAssertionAxiom(
			OWLDataPropertyExpression arg0, OWLIndividual arg1,
			OWLLiteral arg2, Set<? extends OWLAnnotation> arg3) {
		return dataFactory.getOWLDataPropertyAssertionAxiom(arg0, arg1, arg2, arg3);
	}

	@Override
	public synchronized OWLDataPropertyDomainAxiom getOWLDataPropertyDomainAxiom(
			OWLDataPropertyExpression arg0, OWLClassExpression arg1) {
		return dataFactory.getOWLDataPropertyDomainAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLDataPropertyDomainAxiom getOWLDataPropertyDomainAxiom(
			OWLDataPropertyExpression arg0, OWLClassExpression arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLDataPropertyDomainAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLDataPropertyRangeAxiom getOWLDataPropertyRangeAxiom(
			OWLDataPropertyExpression arg0, OWLDataRange arg1) {
		return dataFactory.getOWLDataPropertyRangeAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLDataPropertyRangeAxiom getOWLDataPropertyRangeAxiom(
			OWLDataPropertyExpression arg0, OWLDataRange arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLDataPropertyRangeAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLDataSomeValuesFrom getOWLDataSomeValuesFrom(
			OWLDataPropertyExpression arg0, OWLDataRange arg1) {
		return dataFactory.getOWLDataSomeValuesFrom(arg0, arg1);
	}

	@Override
	public synchronized OWLDataUnionOf getOWLDataUnionOf(Set<? extends OWLDataRange> arg0) {
		return dataFactory.getOWLDataUnionOf(arg0);
	}

	@Override
	public synchronized OWLDataUnionOf getOWLDataUnionOf(OWLDataRange... arg0) {
		return dataFactory.getOWLDataUnionOf(arg0);
	}

	@Override
	public OWLDatatype getOWLDatatype(IRI arg0) {
		return dataFactory.getOWLDatatype(arg0);
	}

	@Override
	public synchronized OWLDatatype getOWLDatatype(String arg0, PrefixManager arg1) {
		return dataFactory.getOWLDatatype(arg0, arg1);
	}

	@Override
	public OWLDatatypeDefinitionAxiom getOWLDatatypeDefinitionAxiom(
			OWLDatatype arg0, OWLDataRange arg1) {
		return dataFactory.getOWLDatatypeDefinitionAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLDatatypeDefinitionAxiom getOWLDatatypeDefinitionAxiom(
			OWLDatatype arg0, OWLDataRange arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLDatatypeDefinitionAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeMaxExclusiveRestriction(int arg0) {
		return dataFactory.getOWLDatatypeMaxExclusiveRestriction(arg0);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeMaxExclusiveRestriction(
			double arg0) {
		return dataFactory.getOWLDatatypeMaxExclusiveRestriction(arg0);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeMaxInclusiveRestriction(int arg0) {
		return dataFactory.getOWLDatatypeMaxInclusiveRestriction(arg0);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeMaxInclusiveRestriction(
			double arg0) {
		return dataFactory.getOWLDatatypeMaxInclusiveRestriction(arg0);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeMinExclusiveRestriction(int arg0) {
		return dataFactory.getOWLDatatypeMinExclusiveRestriction(arg0);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeMinExclusiveRestriction(
			double arg0) {
		return dataFactory.getOWLDatatypeMinExclusiveRestriction(arg0);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeMinInclusiveRestriction(int arg0) {
		return dataFactory.getOWLDatatypeMinInclusiveRestriction(arg0);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeMinInclusiveRestriction(
			double arg0) {
		return dataFactory.getOWLDatatypeMinInclusiveRestriction(arg0);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeMinMaxExclusiveRestriction(
			int arg0, int arg1) {
		return dataFactory.getOWLDatatypeMinMaxExclusiveRestriction(arg0, arg1);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeMinMaxExclusiveRestriction(
			double arg0, double arg1) {
		return dataFactory.getOWLDatatypeMinMaxExclusiveRestriction(arg0, arg1);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeMinMaxInclusiveRestriction(
			int arg0, int arg1) {
		return dataFactory.getOWLDatatypeMinMaxInclusiveRestriction(arg0, arg1);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeMinMaxInclusiveRestriction(
			double arg0, double arg1) {
		return dataFactory.getOWLDatatypeMinMaxInclusiveRestriction(arg0, arg1);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeRestriction(OWLDatatype arg0,
			Set<OWLFacetRestriction> arg1) {
		return dataFactory.getOWLDatatypeRestriction(arg0, arg1);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeRestriction(OWLDatatype arg0,
			OWLFacetRestriction... arg1) {
		return dataFactory.getOWLDatatypeRestriction(arg0, arg1);
	}

	@Override
	public synchronized OWLDatatypeRestriction getOWLDatatypeRestriction(OWLDatatype arg0,
			OWLFacet arg1, OWLLiteral arg2) {
		return dataFactory.getOWLDatatypeRestriction(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLDeclarationAxiom getOWLDeclarationAxiom(OWLEntity arg0) {
		return dataFactory.getOWLDeclarationAxiom(arg0);
	}

	@Override
	public synchronized OWLDeclarationAxiom getOWLDeclarationAxiom(OWLEntity arg0,
			Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLDeclarationAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLAnnotationProperty getOWLDeprecated() {
		return dataFactory.getOWLDeprecated();
	}

	@Override
	public synchronized OWLDifferentIndividualsAxiom getOWLDifferentIndividualsAxiom(
			Set<? extends OWLIndividual> arg0) {
		return dataFactory.getOWLDifferentIndividualsAxiom(arg0);
	}

	@Override
	public synchronized OWLDifferentIndividualsAxiom getOWLDifferentIndividualsAxiom(
			OWLIndividual... arg0) {
		return dataFactory.getOWLDifferentIndividualsAxiom(arg0);
	}

	@Override
	public synchronized OWLDifferentIndividualsAxiom getOWLDifferentIndividualsAxiom(
			Set<? extends OWLIndividual> arg0, Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLDifferentIndividualsAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLDisjointClassesAxiom getOWLDisjointClassesAxiom(
			Set<? extends OWLClassExpression> arg0) {
		return dataFactory.getOWLDisjointClassesAxiom(arg0);
	}

	@Override
	public synchronized OWLDisjointClassesAxiom getOWLDisjointClassesAxiom(
			OWLClassExpression... arg0) {
		return dataFactory.getOWLDisjointClassesAxiom(arg0);
	}

	@Override
	public synchronized OWLDisjointClassesAxiom getOWLDisjointClassesAxiom(
			Set<? extends OWLClassExpression> arg0,
			Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLDisjointClassesAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLDisjointDataPropertiesAxiom getOWLDisjointDataPropertiesAxiom(
			OWLDataPropertyExpression... arg0) {
		return dataFactory.getOWLDisjointDataPropertiesAxiom(arg0);
	}

	@Override
	public synchronized OWLDisjointDataPropertiesAxiom getOWLDisjointDataPropertiesAxiom(
			Set<? extends OWLDataPropertyExpression> arg0) {
		return dataFactory.getOWLDisjointDataPropertiesAxiom(arg0);
	}

	@Override
	public synchronized OWLDisjointDataPropertiesAxiom getOWLDisjointDataPropertiesAxiom(
			Set<? extends OWLDataPropertyExpression> arg0,
			Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLDisjointDataPropertiesAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLDisjointObjectPropertiesAxiom getOWLDisjointObjectPropertiesAxiom(
			Set<? extends OWLObjectPropertyExpression> arg0) {
		return dataFactory.getOWLDisjointObjectPropertiesAxiom(arg0);
	}

	@Override
	public synchronized OWLDisjointObjectPropertiesAxiom getOWLDisjointObjectPropertiesAxiom(
			OWLObjectPropertyExpression... arg0) {
		return dataFactory.getOWLDisjointObjectPropertiesAxiom(arg0);
	}

	@Override
	public synchronized OWLDisjointObjectPropertiesAxiom getOWLDisjointObjectPropertiesAxiom(
			Set<? extends OWLObjectPropertyExpression> arg0,
			Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLDisjointObjectPropertiesAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLDisjointUnionAxiom getOWLDisjointUnionAxiom(OWLClass arg0,
			Set<? extends OWLClassExpression> arg1) {
		return dataFactory.getOWLDisjointUnionAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLDisjointUnionAxiom getOWLDisjointUnionAxiom(OWLClass arg0,
			Set<? extends OWLClassExpression> arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLDisjointUnionAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized <E extends OWLEntity> E getOWLEntity(EntityType<E> arg0, IRI arg1) {
		return dataFactory.getOWLEntity(arg0, arg1);
	}

	@Override
	public synchronized OWLEquivalentClassesAxiom getOWLEquivalentClassesAxiom(
			Set<? extends OWLClassExpression> arg0) {
		return dataFactory.getOWLEquivalentClassesAxiom(arg0);
	}

	@Override
	public synchronized OWLEquivalentClassesAxiom getOWLEquivalentClassesAxiom(
			OWLClassExpression... arg0) {
		return dataFactory.getOWLEquivalentClassesAxiom(arg0);
	}

	@Override
	public synchronized OWLEquivalentClassesAxiom getOWLEquivalentClassesAxiom(
			Set<? extends OWLClassExpression> arg0,
			Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLEquivalentClassesAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLEquivalentClassesAxiom getOWLEquivalentClassesAxiom(
			OWLClassExpression arg0, OWLClassExpression arg1) {
		return dataFactory.getOWLEquivalentClassesAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLEquivalentClassesAxiom getOWLEquivalentClassesAxiom(
			OWLClassExpression arg0, OWLClassExpression arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLEquivalentClassesAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLEquivalentDataPropertiesAxiom getOWLEquivalentDataPropertiesAxiom(
			Set<? extends OWLDataPropertyExpression> arg0) {
		return dataFactory.getOWLEquivalentDataPropertiesAxiom(arg0);
	}

	@Override
	public synchronized OWLEquivalentDataPropertiesAxiom getOWLEquivalentDataPropertiesAxiom(
			OWLDataPropertyExpression... arg0) {
		return dataFactory.getOWLEquivalentDataPropertiesAxiom(arg0);
	}

	@Override
	public synchronized OWLEquivalentDataPropertiesAxiom getOWLEquivalentDataPropertiesAxiom(
			Set<? extends OWLDataPropertyExpression> arg0,
			Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLEquivalentDataPropertiesAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLEquivalentDataPropertiesAxiom getOWLEquivalentDataPropertiesAxiom(
			OWLDataPropertyExpression arg0, OWLDataPropertyExpression arg1) {
		return dataFactory.getOWLEquivalentDataPropertiesAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLEquivalentDataPropertiesAxiom getOWLEquivalentDataPropertiesAxiom(
			OWLDataPropertyExpression arg0, OWLDataPropertyExpression arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLEquivalentDataPropertiesAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLEquivalentObjectPropertiesAxiom getOWLEquivalentObjectPropertiesAxiom(
			Set<? extends OWLObjectPropertyExpression> arg0) {
		return dataFactory.getOWLEquivalentObjectPropertiesAxiom(arg0);
	}

	@Override
	public synchronized OWLEquivalentObjectPropertiesAxiom getOWLEquivalentObjectPropertiesAxiom(
			OWLObjectPropertyExpression... arg0) {
		return dataFactory.getOWLEquivalentObjectPropertiesAxiom(arg0);
	}

	@Override
	public synchronized OWLEquivalentObjectPropertiesAxiom getOWLEquivalentObjectPropertiesAxiom(
			Set<? extends OWLObjectPropertyExpression> arg0,
			Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLEquivalentObjectPropertiesAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLEquivalentObjectPropertiesAxiom getOWLEquivalentObjectPropertiesAxiom(
			OWLObjectPropertyExpression arg0, OWLObjectPropertyExpression arg1) {
		return dataFactory.getOWLEquivalentObjectPropertiesAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLEquivalentObjectPropertiesAxiom getOWLEquivalentObjectPropertiesAxiom(
			OWLObjectPropertyExpression arg0, OWLObjectPropertyExpression arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLEquivalentObjectPropertiesAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLFacetRestriction getOWLFacetRestriction(OWLFacet arg0,
			OWLLiteral arg1) {
		return dataFactory.getOWLFacetRestriction(arg0, arg1);
	}

	@Override
	public synchronized OWLFacetRestriction getOWLFacetRestriction(OWLFacet arg0, int arg1) {
		return dataFactory.getOWLFacetRestriction(arg0, arg1);
	}

	@Override
	public synchronized OWLFacetRestriction getOWLFacetRestriction(OWLFacet arg0, double arg1) {
		return dataFactory.getOWLFacetRestriction(arg0, arg1);
	}

	@Override
	public synchronized OWLFacetRestriction getOWLFacetRestriction(OWLFacet arg0, float arg1) {
		return dataFactory.getOWLFacetRestriction(arg0, arg1);
	}

	@Override
	public synchronized OWLFunctionalDataPropertyAxiom getOWLFunctionalDataPropertyAxiom(
			OWLDataPropertyExpression arg0) {
		return dataFactory.getOWLFunctionalDataPropertyAxiom(arg0);
	}

	@Override
	public synchronized OWLFunctionalDataPropertyAxiom getOWLFunctionalDataPropertyAxiom(
			OWLDataPropertyExpression arg0, Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLFunctionalDataPropertyAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLFunctionalObjectPropertyAxiom getOWLFunctionalObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0) {
		return dataFactory.getOWLFunctionalObjectPropertyAxiom(arg0);
	}

	@Override
	public synchronized OWLFunctionalObjectPropertyAxiom getOWLFunctionalObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0, Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLFunctionalObjectPropertyAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLHasKeyAxiom getOWLHasKeyAxiom(OWLClassExpression arg0,
			Set<? extends OWLPropertyExpression<?, ?>> arg1) {
		return dataFactory.getOWLHasKeyAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLHasKeyAxiom getOWLHasKeyAxiom(OWLClassExpression arg0,
			OWLPropertyExpression<?, ?>... arg1) {
		return dataFactory.getOWLHasKeyAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLHasKeyAxiom getOWLHasKeyAxiom(OWLClassExpression arg0,
			Set<? extends OWLPropertyExpression<?, ?>> arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLHasKeyAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLImportsDeclaration getOWLImportsDeclaration(IRI arg0) {
		return dataFactory.getOWLImportsDeclaration(arg0);
	}

	@Override
	public synchronized OWLAnnotationProperty getOWLIncompatibleWith() {
		return dataFactory.getOWLIncompatibleWith();
	}

	@Override
	public synchronized OWLInverseFunctionalObjectPropertyAxiom getOWLInverseFunctionalObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0) {
		return dataFactory.getOWLInverseFunctionalObjectPropertyAxiom(arg0);
	}

	@Override
	public synchronized OWLInverseFunctionalObjectPropertyAxiom getOWLInverseFunctionalObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0, Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLInverseFunctionalObjectPropertyAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLInverseObjectPropertiesAxiom getOWLInverseObjectPropertiesAxiom(
			OWLObjectPropertyExpression arg0, OWLObjectPropertyExpression arg1) {
		return dataFactory.getOWLInverseObjectPropertiesAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLInverseObjectPropertiesAxiom getOWLInverseObjectPropertiesAxiom(
			OWLObjectPropertyExpression arg0, OWLObjectPropertyExpression arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLInverseObjectPropertiesAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLIrreflexiveObjectPropertyAxiom getOWLIrreflexiveObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0) {
		return dataFactory.getOWLIrreflexiveObjectPropertyAxiom(arg0);
	}

	@Override
	public synchronized OWLIrreflexiveObjectPropertyAxiom getOWLIrreflexiveObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0, Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLIrreflexiveObjectPropertyAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLLiteral getOWLLiteral(int arg0) {
		return dataFactory.getOWLLiteral(arg0);
	}

	@Override
	public synchronized OWLLiteral getOWLLiteral(double arg0) {
		return dataFactory.getOWLLiteral(arg0);
	}

	@Override
	public synchronized OWLLiteral getOWLLiteral(boolean arg0) {
		return dataFactory.getOWLLiteral(arg0);
	}

	@Override
	public synchronized OWLLiteral getOWLLiteral(float arg0) {
		return dataFactory.getOWLLiteral(arg0);
	}

	@Override
	public synchronized OWLLiteral getOWLLiteral(String arg0) {
		return dataFactory.getOWLLiteral(arg0);
	}

	@Override
	public synchronized OWLLiteral getOWLLiteral(String arg0, OWLDatatype arg1) {
		return dataFactory.getOWLLiteral(arg0, arg1);
	}

	@Override
	public synchronized OWLLiteral getOWLLiteral(String arg0, OWL2Datatype arg1) {
		return dataFactory.getOWLLiteral(arg0, arg1);
	}

	@Override
	public synchronized OWLLiteral getOWLLiteral(String arg0, String arg1) {
		return dataFactory.getOWLLiteral(arg0, arg1);
	}

	@Override
	public synchronized OWLNamedIndividual getOWLNamedIndividual(IRI arg0) {
		return dataFactory.getOWLNamedIndividual(arg0);
	}

	@Override
	public synchronized OWLNamedIndividual getOWLNamedIndividual(String arg0,
			PrefixManager arg1) {
		return dataFactory.getOWLNamedIndividual(arg0, arg1);
	}

	@Override
	public synchronized OWLNegativeDataPropertyAssertionAxiom getOWLNegativeDataPropertyAssertionAxiom(
			OWLDataPropertyExpression arg0, OWLIndividual arg1, OWLLiteral arg2) {
		return dataFactory.getOWLNegativeDataPropertyAssertionAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLNegativeDataPropertyAssertionAxiom getOWLNegativeDataPropertyAssertionAxiom(
			OWLDataPropertyExpression arg0, OWLIndividual arg1,
			OWLLiteral arg2, Set<? extends OWLAnnotation> arg3) {
		return dataFactory.getOWLNegativeDataPropertyAssertionAxiom(arg0, arg1, arg2, arg3);
	}

	@Override
	public synchronized OWLNegativeObjectPropertyAssertionAxiom getOWLNegativeObjectPropertyAssertionAxiom(
			OWLObjectPropertyExpression arg0, OWLIndividual arg1,
			OWLIndividual arg2) {
		return dataFactory.getOWLNegativeObjectPropertyAssertionAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLNegativeObjectPropertyAssertionAxiom getOWLNegativeObjectPropertyAssertionAxiom(
			OWLObjectPropertyExpression arg0, OWLIndividual arg1,
			OWLIndividual arg2, Set<? extends OWLAnnotation> arg3) {
		return dataFactory.getOWLNegativeObjectPropertyAssertionAxiom(arg0, arg1, arg2, arg3);
	}

	@Override
	public synchronized OWLObjectAllValuesFrom getOWLObjectAllValuesFrom(
			OWLObjectPropertyExpression arg0, OWLClassExpression arg1) {
		return dataFactory.getOWLObjectAllValuesFrom(arg0, arg1);
	}

	@Override
	public synchronized OWLObjectComplementOf getOWLObjectComplementOf(
			OWLClassExpression arg0) {
		return dataFactory.getOWLObjectComplementOf(arg0);
	}

	@Override
	public synchronized OWLObjectExactCardinality getOWLObjectExactCardinality(int arg0,
			OWLObjectPropertyExpression arg1) {
		return dataFactory.getOWLObjectExactCardinality(arg0, arg1);
	}

	@Override
	public synchronized OWLObjectExactCardinality getOWLObjectExactCardinality(int arg0,
			OWLObjectPropertyExpression arg1, OWLClassExpression arg2) {
		return dataFactory.getOWLObjectExactCardinality(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLObjectHasSelf getOWLObjectHasSelf(OWLObjectPropertyExpression arg0) {
		return dataFactory.getOWLObjectHasSelf(arg0);
	}

	@Override
	public synchronized OWLObjectHasValue getOWLObjectHasValue(
			OWLObjectPropertyExpression arg0, OWLIndividual arg1) {
		return dataFactory.getOWLObjectHasValue(arg0, arg1);
	}

	@Override
	public synchronized OWLObjectIntersectionOf getOWLObjectIntersectionOf(
			Set<? extends OWLClassExpression> arg0) {
		return dataFactory.getOWLObjectIntersectionOf(arg0);
	}

	@Override
	public synchronized OWLObjectIntersectionOf getOWLObjectIntersectionOf(
			OWLClassExpression... arg0) {
		return dataFactory.getOWLObjectIntersectionOf(arg0);
	}

	@Override
	public synchronized OWLObjectInverseOf getOWLObjectInverseOf(
			OWLObjectPropertyExpression arg0) {
		return dataFactory.getOWLObjectInverseOf(arg0);
	}

	@Override
	public synchronized OWLObjectMaxCardinality getOWLObjectMaxCardinality(int arg0,
			OWLObjectPropertyExpression arg1) {
		return dataFactory.getOWLObjectMaxCardinality(arg0, arg1);
	}

	@Override
	public synchronized OWLObjectMaxCardinality getOWLObjectMaxCardinality(int arg0,
			OWLObjectPropertyExpression arg1, OWLClassExpression arg2) {
		return dataFactory.getOWLObjectMaxCardinality(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLObjectMinCardinality getOWLObjectMinCardinality(int arg0,
			OWLObjectPropertyExpression arg1) {
		return dataFactory.getOWLObjectMinCardinality(arg0, arg1);
	}

	@Override
	public synchronized OWLObjectMinCardinality getOWLObjectMinCardinality(int arg0,
			OWLObjectPropertyExpression arg1, OWLClassExpression arg2) {
		return dataFactory.getOWLObjectMinCardinality(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLObjectOneOf getOWLObjectOneOf(Set<? extends OWLIndividual> arg0) {
		return dataFactory.getOWLObjectOneOf(arg0);
	}

	@Override
	public synchronized OWLObjectOneOf getOWLObjectOneOf(OWLIndividual... arg0) {
		return dataFactory.getOWLObjectOneOf(arg0);
	}

	@Override
	public synchronized OWLObjectProperty getOWLObjectProperty(IRI arg0) {
		return dataFactory.getOWLObjectProperty(arg0);
	}

	@Override
	public synchronized OWLObjectProperty getOWLObjectProperty(String arg0,
			PrefixManager arg1) {
		return dataFactory.getOWLObjectProperty(arg0, arg1);
	}

	@Override
	public synchronized OWLObjectPropertyAssertionAxiom getOWLObjectPropertyAssertionAxiom(
			OWLObjectPropertyExpression arg0, OWLIndividual arg1,
			OWLIndividual arg2) {
		return dataFactory.getOWLObjectPropertyAssertionAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLObjectPropertyAssertionAxiom getOWLObjectPropertyAssertionAxiom(
			OWLObjectPropertyExpression arg0, OWLIndividual arg1,
			OWLIndividual arg2, Set<? extends OWLAnnotation> arg3) {
		return dataFactory.getOWLObjectPropertyAssertionAxiom(arg0, arg1, arg2, arg3);
	}

	@Override
	public synchronized OWLObjectPropertyDomainAxiom getOWLObjectPropertyDomainAxiom(
			OWLObjectPropertyExpression arg0, OWLClassExpression arg1) {
		return dataFactory.getOWLObjectPropertyDomainAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLObjectPropertyDomainAxiom getOWLObjectPropertyDomainAxiom(
			OWLObjectPropertyExpression arg0, OWLClassExpression arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLObjectPropertyDomainAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLObjectPropertyRangeAxiom getOWLObjectPropertyRangeAxiom(
			OWLObjectPropertyExpression arg0, OWLClassExpression arg1) {
		return dataFactory.getOWLObjectPropertyRangeAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLObjectPropertyRangeAxiom getOWLObjectPropertyRangeAxiom(
			OWLObjectPropertyExpression arg0, OWLClassExpression arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLObjectPropertyRangeAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLObjectSomeValuesFrom getOWLObjectSomeValuesFrom(
			OWLObjectPropertyExpression arg0, OWLClassExpression arg1) {
		return dataFactory.getOWLObjectSomeValuesFrom(arg0, arg1);
	}

	@Override
	public synchronized OWLObjectUnionOf getOWLObjectUnionOf(
			Set<? extends OWLClassExpression> arg0) {
		return dataFactory.getOWLObjectUnionOf(arg0);
	}

	@Override
	public synchronized OWLObjectUnionOf getOWLObjectUnionOf(OWLClassExpression... arg0) {
		return dataFactory.getOWLObjectUnionOf(arg0);
	}

	@Override
	public synchronized OWLReflexiveObjectPropertyAxiom getOWLReflexiveObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0) {
		return dataFactory.getOWLReflexiveObjectPropertyAxiom(arg0);
	}

	@Override
	public synchronized OWLReflexiveObjectPropertyAxiom getOWLReflexiveObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0, Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLReflexiveObjectPropertyAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLSameIndividualAxiom getOWLSameIndividualAxiom(
			Set<? extends OWLIndividual> arg0) {
		return dataFactory.getOWLSameIndividualAxiom(arg0);
	}

	@Override
	public synchronized OWLSameIndividualAxiom getOWLSameIndividualAxiom(
			OWLIndividual... arg0) {
		return dataFactory.getOWLSameIndividualAxiom(arg0);
	}

	@Override
	public synchronized OWLSameIndividualAxiom getOWLSameIndividualAxiom(
			Set<? extends OWLIndividual> arg0, Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLSameIndividualAxiom(arg0, arg1);
	}

	@Override
	@Deprecated
	public synchronized OWLLiteral getOWLStringLiteral(String arg0) {
		return dataFactory.getOWLStringLiteral(arg0);
	}

	@Override
	@Deprecated
	public synchronized OWLLiteral getOWLStringLiteral(String arg0, String arg1) {
		return dataFactory.getOWLStringLiteral(arg0, arg1);
	}

	@Override
	public synchronized OWLSubAnnotationPropertyOfAxiom getOWLSubAnnotationPropertyOfAxiom(
			OWLAnnotationProperty arg0, OWLAnnotationProperty arg1) {
		return dataFactory.getOWLSubAnnotationPropertyOfAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLSubAnnotationPropertyOfAxiom getOWLSubAnnotationPropertyOfAxiom(
			OWLAnnotationProperty arg0, OWLAnnotationProperty arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLSubAnnotationPropertyOfAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLSubClassOfAxiom getOWLSubClassOfAxiom(OWLClassExpression arg0,
			OWLClassExpression arg1) {
		return dataFactory.getOWLSubClassOfAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLSubClassOfAxiom getOWLSubClassOfAxiom(OWLClassExpression arg0,
			OWLClassExpression arg1, Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLSubClassOfAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLSubDataPropertyOfAxiom getOWLSubDataPropertyOfAxiom(
			OWLDataPropertyExpression arg0, OWLDataPropertyExpression arg1) {
		return dataFactory.getOWLSubDataPropertyOfAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLSubDataPropertyOfAxiom getOWLSubDataPropertyOfAxiom(
			OWLDataPropertyExpression arg0, OWLDataPropertyExpression arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLSubDataPropertyOfAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLSubObjectPropertyOfAxiom getOWLSubObjectPropertyOfAxiom(
			OWLObjectPropertyExpression arg0, OWLObjectPropertyExpression arg1) {
		return dataFactory.getOWLSubObjectPropertyOfAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLSubObjectPropertyOfAxiom getOWLSubObjectPropertyOfAxiom(
			OWLObjectPropertyExpression arg0, OWLObjectPropertyExpression arg1,
			Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLSubObjectPropertyOfAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLSubPropertyChainOfAxiom getOWLSubPropertyChainOfAxiom(
			List<? extends OWLObjectPropertyExpression> arg0,
			OWLObjectPropertyExpression arg1) {
		return dataFactory.getOWLSubPropertyChainOfAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLSubPropertyChainOfAxiom getOWLSubPropertyChainOfAxiom(
			List<? extends OWLObjectPropertyExpression> arg0,
			OWLObjectPropertyExpression arg1, Set<? extends OWLAnnotation> arg2) {
		return dataFactory.getOWLSubPropertyChainOfAxiom(arg0, arg1, arg2);
	}

	@Override
	public synchronized OWLSymmetricObjectPropertyAxiom getOWLSymmetricObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0) {
		return dataFactory.getOWLSymmetricObjectPropertyAxiom(arg0);
	}

	@Override
	public synchronized OWLSymmetricObjectPropertyAxiom getOWLSymmetricObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0, Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLSymmetricObjectPropertyAxiom(arg0, arg1);
	}

	@Override
	public synchronized OWLDataProperty getOWLTopDataProperty() {
		return dataFactory.getOWLTopDataProperty();
	}

	@Override
	public synchronized OWLObjectProperty getOWLTopObjectProperty() {
		return dataFactory.getOWLTopObjectProperty();
	}

	@Override
	public synchronized OWLTransitiveObjectPropertyAxiom getOWLTransitiveObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0) {
		return dataFactory.getOWLTransitiveObjectPropertyAxiom(arg0);
	}

	@Override
	public synchronized OWLTransitiveObjectPropertyAxiom getOWLTransitiveObjectPropertyAxiom(
			OWLObjectPropertyExpression arg0, Set<? extends OWLAnnotation> arg1) {
		return dataFactory.getOWLTransitiveObjectPropertyAxiom(arg0);
	}

	@Override
	@Deprecated
	public synchronized OWLLiteral getOWLTypedLiteral(int arg0) {
		return dataFactory.getOWLTypedLiteral(arg0);
	}

	@Override
	@Deprecated
	public synchronized OWLLiteral getOWLTypedLiteral(double arg0) {
		return dataFactory.getOWLTypedLiteral(arg0);
	}

	@Override
	@Deprecated
	public synchronized OWLLiteral getOWLTypedLiteral(boolean arg0) {
		return dataFactory.getOWLTypedLiteral(arg0);
	}

	@Override
	@Deprecated
	public synchronized OWLLiteral getOWLTypedLiteral(float arg0) {
		return dataFactory.getOWLTypedLiteral(arg0);
	}

	@Override
	@Deprecated
	public synchronized OWLLiteral getOWLTypedLiteral(String arg0) {
		return dataFactory.getOWLTypedLiteral(arg0);
	}

	@Override
	@Deprecated
	public synchronized OWLLiteral getOWLTypedLiteral(String arg0, OWLDatatype arg1) {
		return dataFactory.getOWLTypedLiteral(arg0, arg1);
	}

	@Override
	@Deprecated
	public synchronized OWLLiteral getOWLTypedLiteral(String arg0, OWL2Datatype arg1) {
		return dataFactory.getOWLTypedLiteral(arg0, arg1);
	}

	@Override
	public synchronized OWLAnnotationProperty getOWLVersionInfo() {
		return dataFactory.getOWLVersionInfo();
	}

	@Override
	public synchronized OWLDatatype getRDFPlainLiteral() {
		return dataFactory.getRDFPlainLiteral();
	}

	@Override
	public synchronized OWLAnnotationProperty getRDFSComment() {
		return dataFactory.getRDFSComment();
	}

	@Override
	public synchronized OWLAnnotationProperty getRDFSIsDefinedBy() {
		return dataFactory.getRDFSIsDefinedBy();
	}

	@Override
	public synchronized OWLAnnotationProperty getRDFSLabel() {
		return dataFactory.getRDFSLabel();
	}

	@Override
	public synchronized OWLAnnotationProperty getRDFSSeeAlso() {
		return dataFactory.getRDFSSeeAlso();
	}

	@Override
	public synchronized OWLDatatype getTopDatatype() {
		return dataFactory.getTopDatatype();
	}

	@Override
	public synchronized void purge() {
		dataFactory.purge();
	}
}
