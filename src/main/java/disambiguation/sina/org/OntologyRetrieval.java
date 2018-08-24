package disambiguation.sina.org;

/**
 * @author Saedeeh Shekarpour
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// import javatools.parsers.*; //parsers.porterstemmer;

import extension.DBPediaOWL;
import opennlp.tools.stemmer.PorterStemmer;

import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

public class OntologyRetrieval {

	private SimilarityCalculation f = new SimilarityCalculation();
	private PreProcessingOfTerms p = PreProcessingOfTerms
			.getInstance();// = new PreProcessing();
	private double thresholdtheta = 0.5;

	public List<StateInfo> getMappingOwlClasses(ArrayList Segment)
			throws OWLOntologyCreationException {
		// term = stemmer.stemWord(term.trim());
		PorterStemmer stemmer = new PorterStemmer();
		Set<OWLClass> ClassSet = new HashSet();
		List<StateInfo> foundResources = new ArrayList<StateInfo>();
		OWLOntology DBpediaOWL = DBPediaOWL.getInstance();
		OWLDataFactory dataFactory = extension.OWLDataFactory.getInstance();
		String base = "http://dbpedia.org/ontology/";
		PrefixManager pm = new DefaultPrefixManager(base);
		int minimumlength = 100;
		int maximumlength = 0;

		OWLAnnotationProperty label = dataFactory
				.getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI());

		for (OWLClass cls : DBpediaOWL.getClassesInSignature()) {

			// Get the annotations on the class that use the label property

			for (OWLAnnotation annotation : cls.getAnnotations(DBpediaOWL,
					label)) {

				if (annotation.getValue() instanceof OWLLiteral) {

					OWLLiteral val = (OWLLiteral) annotation.getValue();
					if (val.hasLang("en")) {

						String Label = val.getLiteral();
						if (Label.contains("(") && Label.contains(")")) {
							int indexStart = Label.indexOf("(");
							int indexEnd = Label.indexOf(")");
							StringBuffer s = new StringBuffer(Label);
							s = s.delete(indexStart, indexEnd + 1);
							Label = s.toString();

						}
						String StemmedLabel = stemmer.stem(Label.trim());

						// if (Pattern.compile(Pattern.quote(term),
						// Pattern.CASE_INSENSITIVE).matcher(val.getLiteral()).find()
						// ) {
						// if (Pattern.compile(Pattern.quote(term),
						// Pattern.CASE_INSENSITIVE).matcher(StemmedLabel).find()
						// ) {

						StateInfo object = new StateInfo();
						String URIName = cls.getIRI().toString();
						object.setUri(URIName);

						object.setLabel(Label.trim());

						ArrayList LabelList = p.removeStopWordswithlem(Label);
						object.setType(Constants.TYPE_CLASS);
						object.setStringSimilarityScore(f
								.jaccardLevenstienSimilarity(Segment,
										LabelList, Label.trim()));

						foundResources.add(object);

						
					}

				}

			}

		}

		List<StateInfo> finalfoundRessources = new ArrayList<StateInfo>();

		if (foundResources.size() != 0) {

			for (StateInfo resourceInfo : foundResources) {
				if (resourceInfo.getStringSimilarityScore() >= thresholdtheta) {
					finalfoundRessources.add(resourceInfo);
				}

			}
			Collections.sort(finalfoundRessources,
					new StringSimilarityComparator());

		}

		return finalfoundRessources;

	}

	// ####################################################################
	public List<StateInfo> getMappingOwlObjectProperties(ArrayList Segment)
			throws OWLOntologyCreationException {

		// term = stemmer.stemWord(term.trim());
		PorterStemmer stemmer = new PorterStemmer();
		List<StateInfo> foundResources = new ArrayList<StateInfo>();
		Set<OWLObjectProperty> PropertySet = new HashSet();
		OWLOntology DBpediaOWL = DBPediaOWL.getInstance();
		OWLDataFactory dataFactory = org.aksw.sina.OWLDataFactory.getInstance();
		String base = "http://dbpedia.org/ontology/";
		PrefixManager pm = new DefaultPrefixManager(base);
		int minimumlength = 100;
		int maximumlength = 0;

		OWLAnnotationProperty label = dataFactory
				.getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI());

		for (OWLObjectProperty cls : DBpediaOWL
				.getObjectPropertiesInSignature()) {

			// Get the annotations on the class that use the label property

			for (OWLAnnotation annotation : cls.getAnnotations(DBpediaOWL,
					label)) {

				if (annotation.getValue() instanceof OWLLiteral) {

					OWLLiteral val = (OWLLiteral) annotation.getValue();
					if (val.hasLang("en")) {

						String Label = val.getLiteral();
						if (Label.contains("(") && Label.contains(")")) {
							int indexStart = Label.indexOf("(");
							int indexEnd = Label.indexOf(")");
							StringBuffer s = new StringBuffer(Label);
							s = s.delete(indexStart, indexEnd + 1);
							Label = s.toString();

						}

						String StemmedLabel = stemmer.stem(Label.trim());
						// if ( Pattern.compile(Pattern.quote(term),
						// Pattern.CASE_INSENSITIVE).matcher(StemmedLabel).find()
						// )
						// val.getLiteral().contains(term) )
						// {
						StateInfo object = new StateInfo();
						String URIName = cls.getIRI().toString();
						object.setUri(URIName);
						object.setLabel(Label.trim());
						ArrayList LabelList = p.removeStopWordswithlem(Label);
						object.setType(Constants.TYPE_PROPERTY);
						object.setStringSimilarityScore(f
								.jaccardLevenstienSimilarity(Segment,
										LabelList, Label.trim()));
						foundResources.add(object);

						// PropertySet.add(cls);
						// System.out.println(cls + " -> " + Label + "language "
						// + val.getLang() + "  label " + StemmedLabel +
						// "  term  " + term);

						// }
					}

				}

			}

		}

		List<StateInfo> finalfoundRessources = new ArrayList<StateInfo>();

		if (foundResources.size() != 0) {

			for (StateInfo resourceInfo : foundResources) {
				if (resourceInfo.getStringSimilarityScore() >= thresholdtheta) {
					finalfoundRessources.add(resourceInfo);
				}

			}
			Collections.sort(finalfoundRessources,
					new StringSimilarityComparator());

		}

		return finalfoundRessources;

	}

	// #########################################################

	public List<StateInfo> getMappingOwlDataTypeProperties(ArrayList Segment)
			throws OWLOntologyCreationException {

		// term = stemmer.stemWord(term.trim());
		List<StateInfo> foundResources = new ArrayList<StateInfo>();
		Set<OWLDataProperty> PropertySet = new HashSet();
		OWLOntology DBpediaOWL = DBPediaOWL.getInstance();
		OWLDataFactory dataFactory = extension.OWLDataFactory.getInstance();
		int minimumlength = 100;
		int maximumlength = 0;

		String base = "http://dbpedia.org/ontology/";
		PrefixManager pm = new DefaultPrefixManager(base);

		OWLAnnotationProperty label = dataFactory
				.getOWLAnnotationProperty(OWLRDFVocabulary.RDFS_LABEL.getIRI());

		for (OWLDataProperty cls : DBpediaOWL.getDataPropertiesInSignature()) {

			// Get the annotations on the class that use the label property

			for (OWLAnnotation annotation : cls.getAnnotations(DBpediaOWL,
					label)) {

				if (annotation.getValue() instanceof OWLLiteral) {

					OWLLiteral val = (OWLLiteral) annotation.getValue();

					// val.getLiteral(). .contains(term)
					if (val.hasLang("en")) {

						String Label = val.getLiteral();
						if (Label.contains("(") && Label.contains(")")) {
							int indexStart = Label.indexOf("(");
							int indexEnd = Label.indexOf(")");
							StringBuffer s = new StringBuffer(Label);
							s = s.delete(indexStart, indexEnd + 1);
							Label = s.toString();

						}
						// String StemmedLabel=stemmer.stemWord(Label.trim());
						// if ( Pattern.compile(Pattern.quote(term),
						// Pattern.CASE_INSENSITIVE).matcher(StemmedLabel).find()
						// )
						// {
						StateInfo object = new StateInfo();
						String URIName = cls.getIRI().toString();
						object.setUri(URIName);

						object.setLabel(Label.trim());

						ArrayList LabelList = p.removeStopWordswithlem(Label
								.trim());
						// System.out.println("Segment "+ Segment);
						// System.out.println("LabelList" + LabelList);
						// System.out.println("Label" +Label);
						object.setType(Constants.TYPE_PROPERTY);
						object.setStringSimilarityScore(f
								.jaccardLevenstienSimilarity(Segment,
										LabelList, Label.trim()));

						foundResources.add(object);
						// PropertySet.add(cls);
					}

				}

			}

		}

		List<StateInfo> finalfoundResources = new ArrayList<StateInfo>();

		if (foundResources.size() != 0) {

			for (StateInfo resourceInfo : foundResources) {
				// System.out.println(resourceInfo.getUri() + " ....... " +
				// resourceInfo.getLabel() + "....score...." +
				// resourceInfo.getStringSimilarityScore());
				if (resourceInfo.getStringSimilarityScore() >= thresholdtheta) {
					finalfoundResources.add(resourceInfo);
				}

			}

		}

		// Collections.sort(finalfoundResources, new
		// StringSimilarityComparator());

		return finalfoundResources;

		// #################################################################

	}

}
