package disambiguation.sina.org;

import java.util.*;


/**
 * 
 * @author Daniel Gerber
 * @author Saedeeh Shekarpour
 */
public class StringSimilarityComparator implements Comparator<StateInfo> {

	/**
	 * 
	 */
	public int compare(StateInfo emp1, StateInfo emp2) {

		double similarityScore1 = emp1.getStringSimilarityScore();
		double similarityScore2 = emp2.getStringSimilarityScore();

		if ( similarityScore1 > similarityScore2 )

			return -1;

		else if ( similarityScore1 < similarityScore2 )

			return 1;

		else

			return 0;

	}
}
