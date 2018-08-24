package disambiguation.sina.org;

import java.util.HashSet;
import java.util.Set;

public class StateInfo {

	/**
	 * @author Saedeeh Shekarpour
	 */

	private String keyword;
	private int type;
	private int connectivityDegree = -1;
	private String label;
	private String uri;

	private double stringSimilarityScore;
	private double logConnectivityDegree = 0;
	private double normalizedDegree;

	private boolean mainSegment;
	private Set<String> PropertiesSet = new HashSet();
	private Set<String> ObjectSet = new HashSet();

	private double hub = 1;
	private double authorithy = 1;

	public StateInfo() {

		this.type = Constants.TYPE_INSTANCE;
		this.connectivityDegree = -1;
		this.logConnectivityDegree = 0;
	}

	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	public double gethub() {
		return hub;

	}

	public double getauthorithy() {
		return authorithy;

	}

	public int getType() {
		return type;

	}

	public void setType(int t) {
		type = t;

	}

	public void sethub(double s) {
		hub = s;

	}

	public void setauthorithy(double s) {
		authorithy = s;

	}

	/**
	 * @return the label
	 */
	public String getkeyword() {
		return keyword;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setkeyword(String k) {
		this.keyword = k;
	}

	/**
	 * @return the ClassSet
	 */
	public Set<String> getPropertiesSet() {
		return PropertiesSet;
	}

	public Set<String> getObjectSet() {
		return ObjectSet;
	}

	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setPropertiesSet(HashSet<String> CS) {
		this.PropertiesSet.addAll(CS);
	}

	public void setObjectSet(HashSet<String> CS) {
		this.ObjectSet.addAll(CS);
	}

	/**
	 * @return the rowNumber
	 */
	/**
	 * @return the connectivityDegree
	 */
	public int getConnectivityDegree() {
		return connectivityDegree;
	}

	/**
	 * @param connectivityDegree
	 *            the connectivityDegree to set
	 */
	public void setConnectivityDegree(int connectivityDegree) {
		this.connectivityDegree = connectivityDegree;
	}

	/**
	 * @return the stringSimilarityScore
	 */
	public double getStringSimilarityScore() {
		return stringSimilarityScore;
	}

	/**
	 * @param stringSimilarityScore
	 *            the stringSimilarityScore to set
	 */
	public void setStringSimilarityScore(double stringSimilarityScore) {
		this.stringSimilarityScore = stringSimilarityScore;
	}

	/**
	 * @return the logConnectivityDegree
	 */
	public double getLogConnectivityDegree() {
		logConnectivityDegree = Math.log10((double) connectivityDegree);
		return logConnectivityDegree;
	}

	/**
	 * @param logConnectivityDegree
	 *            to set
	 * 
	 */
	public void setLogConnectivityDegree(double logConnectivityDegree) {
		this.logConnectivityDegree = logConnectivityDegree;
	}

	/**
	 * @return the normalizedDegree
	 */
	public double getNormalizedDegree() {
		return normalizedDegree;
	}

	/**
	 * @param normalizedDegree
	 *            to set
	 * 
	 */
	public void setNormalizedDegree(double normalizedDegree) {
		this.normalizedDegree = normalizedDegree;
	}

}
