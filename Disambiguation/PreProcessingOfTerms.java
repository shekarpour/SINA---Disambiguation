package disambiguation;

/*
 * This class is responsible for the pre-processing functions on an string input which we call this input as query input.
 * The stop-words are removed out of Query input.
 *  The Stanford Core NLP is used for lemmatization function
 *  
 */



/**

 * @author Saedeeh Shekarpour
 */

import java.io.*;
import java.util.*;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import javatools.parsers.*;

public class PreProcessingOfTerms {

	protected static StanfordCoreNLP pipeline;
	private static final ArrayList<String> StopWords = new ArrayList<String>();
	private static PreProcessingOfTerms instance;

	private PreProcessingOfTerms() {
		initialization();
	}

	public static PreProcessingOfTerms getInstance() {
		if (instance == null)
			instance = new PreProcessingOfTerms();
		return instance;
	}

	public void StanfordLemmatizer() {
		this.pipeline = newPipeline();
	}

	private StanfordCoreNLP newPipeline() {
		Properties props;
		props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos, lemma");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		return pipeline;
	}

	private List<String> lemmatize(String documentText) {
		List<String> lemmas = new LinkedList<String>();
		// create an empty Annotation just with the given text
		Annotation document = new Annotation(documentText);

		this.pipeline = newPipeline();

		// run all Annotators on this text
		this.pipeline.annotate(document);

		// Iterate over all of the sentences found
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			// Iterate over all tokens in a sentence
			for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
				// Retrieve and add the lemma for each word into the
				// list of lemmas
				String t = token.get(LemmaAnnotation.class);
				lemmas.add(t);
			}
		}

		return lemmas;
	}

	public void initialization()

	{
		StanfordLemmatizer();
		// *************** initialization of stopwords ********************

		try {

			InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream("Stopwords635.txt");
			
		
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String s;
			while ((s = br.readLine()) != null) {
				StopWords.add(s);

			}
			in.close();

		}

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String[] StopWords = StopWordsFile.
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("initialization was done");
	}

	public ArrayList removeStopWordswithlem(String inputString) {
		inputString = inputString.replace(".", "");
		inputString = inputString.replace("?", "");
		inputString = inputString.replace(",", "");
		inputString = inputString.replace("'", " ");

		// StanfordLemmatizer();
		ArrayList<String> OutputList = new ArrayList<String>();
		List<String> splitArray = lemmatize(inputString);

		/*
		 * for(String str:splitArray) { str=str.trim(); if ( !
		 * StopWords.contains(str.toLowerCase()) ) { str =
		 * str.replaceAll("[^a-zA-Z]", " "); if ( str.trim().length()>= 1 ){
		 * OutputList.add(str.trim() ); } }
		 * 
		 * }
		 */

		// System.out.println(" attention  " +
		// PorterStemmer.stemWord("longest"));

		for (String str : splitArray) {
			str = str.trim();
			if (!StopWords.contains(str.toLowerCase())) {
				str = str.replaceAll("[^a-zA-Z0-9]", " ");
				if (str.trim().length() >= 1) {

					boolean numberonly = true;

					for (int i = 0; i < str.length(); i++) {
						// If we find a non-digit character we return false.
						if (!Character.isDigit(str.charAt(i)))
							numberonly = false;
					}

					if (numberonly) {
						int index = OutputList.size();
						if (index > 0) {
							OutputList.set(index - 1, OutputList.get(index - 1)
									+ " " + str);
						} else {
							OutputList.add(str.trim());
						}
					} else {
						OutputList.add(str.trim());
					}

				}
			}

		}

		return OutputList;
	}

	public ArrayList removeStopWordswithStemming(String inputString) {
		String[] splitArray = inputString.split(" ");
		ArrayList OutputList = new ArrayList();
		for (String str : splitArray) {
			str = str.trim();
			if (!StopWords.contains(str.toLowerCase())) {

				str = PlingStemmer.stem(str);
				// str = PorterStemmer.stemWord(str);
				if (str.length() >= 1) {
					OutputList.add(str.trim());
				}
			}

		}

		return OutputList;
	}
}
