package disambiguation;

/**
 * @author Saedeeh Shekarpour
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

import uk.ac.shef.wit.simmetrics.*;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.CosineSimilarity;
import uk.ac.shef.wit.simmetrics.similaritymetrics.EuclideanDistance;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaccardSimilarity;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;
import uk.ac.shef.wit.simmetrics.similaritymetrics.MatchingCoefficient;
import uk.ac.shef.wit.simmetrics.similaritymetrics.QGramsDistance;

public class SimilarityCalculation {

	private int maximumdegree = 0;
	private int minimumdegree = 1000;

	private int sumclass = 0;
	private int numclass = 0;

	private int sumproperty = 0;
	private int numproperty = 0;

	private int suminstance = 0;
	private int numinstance = 0;

	// public static final String SPARQL_ENDPOINT_URI =
	// "http://hanne.aksw.org:8910/sparql";

	/**
	 * 
	 * @param term
	 * @param label
	 * @param minimumlength
	 * @param maximumlength
	 * @return
	 */
	public double SimilarityString(String term, String label,
			int minimumlength, int maximumlength) {

		/*
		 * if(term.length() < minimumlength ) { minimumlength=term.length(); }
		 * return levenshtein(term,label); return (double)(1 -
		 * normalizeValue(minimumlength, maximumlength, label.length()));
		 */
		term = term.trim().toLowerCase();
		label = label.trim().toLowerCase();

		AbstractStringMetric metric = new Levenshtein();
		// AbstractStringMetric metric = new QGramsDistance();
		// AbstractStringMetric metric = new MatchingCoefficient();
		// AbstractStringMetric metric = new
		// uk.ac.shef.wit.simmetrics.similaritymetrics.();
		// AbstractStringMetric metric = new JaccardSimilarity();
		// AbstractStringMetric metric = new EuclideanDistance();
		// AbstractStringMetric metric = new CosineSimilarity();
		// QGramsDistance.java
		// AbstractStringMetric metric = new
		// uk.ac.shef.wit.simmetrics.similaritymetrics.
		// AbstractStringMetric metric = new EuclideanDistance();
		float result = metric.getSimilarity(term, label);
		return result;
	}

	/**
	 * @param minimumlength
	 * @param maximumlength
	 * @param value
	 * @return
	 */

	public double jaccardLevenstienSimilarity(ArrayList keyList,
			ArrayList label, String LabelOriginal) {
		AbstractStringMetric metric = new Levenshtein();
		double plus = 0;
		int numStopWords = 0;

		String[] splitArray = LabelOriginal.split(" ");
		numStopWords = splitArray.length - label.size();

		int intersection = 0;
		int union = keyList.size() + label.size();

		if (keyList.size() == 1) {
			double s = metric.getSimilarity(keyList.get(0).toString()
					.toLowerCase(), LabelOriginal.toLowerCase().trim());
			return s;
		}
		for (int i = 0; i < keyList.size(); i++) {
			double max = 0;
			for (int j = 0; j < label.size(); j++) {
				double s = metric.getSimilarity(keyList.get(i).toString()
						.toLowerCase(), label.get(j).toString().toLowerCase());
				if (s > max) {
					max = s;
				}

			}
			// System.out.println(" max similarity " + max);
			if (max >= 0.7) {
				intersection++;
				plus = plus + max;
			}

		}
		/*
		 * System.out.println(" LabelOriginal = " + LabelOriginal);
		 * System.out.println(" intersection = " + intersection);
		 * System.out.println(" union = " + union);
		 * System.out.println(" plus = " + plus);
		 */

		// double similarity = plus/label.size();
		double similarity = plus
				/ ((union - intersection) + (0.1 * numStopWords));
		// double similarity = plus/(union - intersection );
		return similarity;

	}

	// -----------------------------------------------

	public double normalizeValue(int minimumlength, int maximumlength, int value) {

		if ((value - minimumlength) == (maximumlength - minimumlength)) {
			return 0;
		} else if ((maximumlength - minimumlength) != 0) {

			return ((double) (value - minimumlength))
					/ ((double) (maximumlength - minimumlength));
		} else {
			return 0;
		}
	}

	// ----------------------

	public double minMaxnormalizationValue(int minimumlength,
			int maximumlength, int value) {

		double MinimumRange = 0.1;
		double MaximumRange = 0.5;
		if ((value - minimumlength) == (maximumlength - minimumlength)) {
			return 0;
		} else if ((maximumlength - minimumlength) != 0) {

			return ((double) (value - minimumlength))
					/ ((double) (maximumlength - minimumlength))
					* (MaximumRange - MinimumRange) + MinimumRange;
		} else {
			return 0;
		}
	}

	/**
	 * 
	 * @param resourceList
	 */

	public double normalizeValue(double minimumdegree, double maximumdegree,
			double degree) {

		if ((degree - minimumdegree) == (maximumdegree - minimumdegree)) {
			return 0;
		} else if ((maximumdegree - minimumdegree) != 0) {

			return ((double) (degree - minimumdegree))
					/ ((double) (maximumdegree - minimumdegree));
		} else {
			return 0;
		}

	}

}
