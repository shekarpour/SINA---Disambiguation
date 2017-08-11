package disambiguation;

/**
 * @author Saedeeh Shekarpour
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.hp.hpl.jena.query.DatasetFactory;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.Syntax;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;
import com.hp.hpl.jena.*;

public class InstanceRetrieval {

	// /################################################
	// ########### retrieval of Entities ##############
	// #################################################
	private double thresholdtheta = 0.4;

	public List<StateInfo> getIRIsForPatterns(String Pattern, ArrayList Segment) {

		PreProcessingOfTerms p = PreProcessingOfTerms.getInstance();
		// PreProcessing p = new PreProcessing();
		System.out.println(" before initialization ");
		// p.initialization();
		System.out.println(" after initialization ");

		List<StateInfo> foundRessources = new ArrayList<StateInfo>();
		String querystring;

		String option = " OPTIONAL {?iri  <http://dbpedia.org/ontology/wikiPageRedirects> ?mainpage . ?mainpage rdfs:label ?mLabel. FILTER( langMatches(lang(?mLabel), \"en\")).} ";
		querystring = " PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ " SELECT distinct * WHERE { ?iri rdfs:label ?label.   ?label  bif:contains  '"
				+ Pattern + "'  "
				+ " FILTER( langMatches(lang(?label), \"en\")).  " + option
				+ "   }  ";

		System.out.println("-------retrieved query is:" + querystring);
		String urlsever = Constants.SPARQLEndPoint;
		;
		// Query query=QueryFactory.create(querystring);
		QueryEngineHTTP qexec = new QueryEngineHTTP(urlsever, querystring);
		// QueryExecution qexec =
		// QueryExecutionFactory.sparqlService(urlsever,query);
		qexec.addDefaultGraph(Constants.DefaultGraphString);
		Set<String> setiri = new TreeSet<String>();

		try {
			ResultSet results = qexec.execSelect();
			while (results.hasNext()) {

				QuerySolution solution = results.nextSolution();

				boolean lanflag = false;
				StateInfo object = new StateInfo();
				String URIName = null, Label = null, mLabel = null;
				if (solution.get("mainpage") != null) {
					URIName = solution.get("mainpage").toString();
					Label = solution.get("mLabel").asLiteral().getLexicalForm();// .toString();

					if (Label.contains("@")) {

						if (Label.endsWith("@en")) {

							lanflag = true;
							Label = Label.replace("@en", "");

							if (Label.endsWith("\"")) {
								Label = Label.replace("\"", "");
							}

							if (Label.startsWith("\"")) {
								Label = Label.replace("\"", "");
							}

						}

					}

					else {
						lanflag = true;
						if (Label.endsWith("\"")) {
							Label = Label.replace("\"", "");
						}

						if (Label.startsWith("\"")) {
							Label = Label.replace("\"", "");
						}

					}

				}

				else {
					URIName = solution.get("iri").toString();
					Label = solution.get("label").asLiteral().getLexicalForm();

					if (Label.contains("@")) {

						if (Label.endsWith("@en")) {

							lanflag = true;
							Label = Label.replace("@en", "");

							if (Label.endsWith("\"")) {
								Label = Label.replace("\"", "");
							}

							if (Label.startsWith("\"")) {
								Label = Label.replace("\"", "");
							}

						}

					}

					else {
						lanflag = true;
						if (Label.endsWith("\"")) {
							Label = Label.replace("\"", "");
						}

						if (Label.startsWith("\"")) {
							Label = Label.replace("\"", "");
						}

					}

				}

				if (URIName.startsWith("http://dbpedia.org/property/")) {
					object.setType(Constants.TYPE_PROPERTY);
				} else if (URIName.startsWith("http://dbpedia.org/resource/")) {

					object.setType(Constants.TYPE_INSTANCE);
				}

				object.setUri(URIName);
				object.setLabel(Label.trim());

				if (!URIName.startsWith("http://dbpedia.org/ontology/")
						&& !setiri.contains(URIName)) {
					foundRessources.add(object);
					setiri.add(URIName);

				}

			}// end of second while

		} catch (Exception e) {

			System.out.println("finish by exeption" + e.getMessage());

		} finally {
		}

		SimilarityCalculation f = new SimilarityCalculation();

		ArrayList LabelList;

		for (StateInfo resourceInfo : foundRessources) {

			if (resourceInfo.getUri().startsWith("http://dbpedia.org/resource")) {

				LabelList = p.removeStopWordswithStemming(resourceInfo
						.getLabel());
				resourceInfo.setStringSimilarityScore(f
						.jaccardLevenstienSimilarity(Segment, LabelList,
								resourceInfo.getLabel()));
				}

			else if (resourceInfo.getUri().startsWith(
					"http://dbpedia.org/property")) {
				LabelList = p.removeStopWordswithlem(resourceInfo.getLabel());
				resourceInfo.setStringSimilarityScore(f
						.jaccardLevenstienSimilarity(Segment, LabelList,
								resourceInfo.getLabel()));

			}
		}

		List<StateInfo> finalfoundResources = new ArrayList<StateInfo>();
		for (StateInfo resourceInfo : foundRessources) {
			if (resourceInfo.getStringSimilarityScore() >= thresholdtheta) {
				finalfoundResources.add(resourceInfo);
			}

		}

		// sort the final list
		Collections.sort(finalfoundResources, new StringSimilarityComparator());
		// cut the final list if it is so big
		if (finalfoundResources.size() > Constants.LimitOfList) {
			finalfoundResources = finalfoundResources.subList(0,
					Constants.LimitOfList);
		}

	
		return finalfoundResources;
	}

	// /################################################ ##############
	// ########### computing lenght of path between 2 entities ##############
	// ################################################# ##############

	public int getDistance(StateInfo e1, StateInfo e2) {

		String query1 = "";
		String query2 = "";
		String uery3 = "";

		int Firstdistance = Constants.MaximumDistance;
		int SecodDistance = Constants.MaximumDistance;

		if (e1.getType() == Constants.TYPE_CLASS
				&& e2.getType() == Constants.TYPE_CLASS) {
			// this query compute lenght = 6
			query1 = " ask WHERE {  {?v1 a <" + e1.getUri() + ">.  ?v2 a <"
					+ e2.getUri() + ">. ?v1 ?p1 ?v2.} union {?v1 a <"
					+ e2.getUri() + ">. ?v2 a <" + e1.getUri()
					+ ">. ?v2 ?p1 ?v1. } }  ";

			query2 = " ask WHERE { ?v1 a <"
					+ e1.getUri()
					+ ">.  ?v2 a <"
					+ e2.getUri()
					+ ">. { ?v1 ?p1  ?x. ?x ?p2 ?v2.} union {"
					+ " ?v2 ?p2  ?x. ?x ?p1 ?v1.} union { ?v1 ?p1 ?x. ?v2 ?p2 ?x. } "
					+ " union {?x ?p1 ?v1. ?x ?p2 ?v2 .} } ";

			Firstdistance = 2;
			SecodDistance = 4;
		}

		else if (e1.getType() == Constants.TYPE_INSTANCE
				&& e2.getType() == Constants.TYPE_INSTANCE) {
			// this query compute lenght = 2
			query1 = " ask WHERE {  {<" + e1.getUri() + "> ?p1 <" + e2.getUri()
					+ ">.} union {<" + e2.getUri() + "> ?p2 <" + e1.getUri()
					+ ">. } }  ";

			// this query compute lenght = 4
			query2 = " ask WHERE {  {<" + e1.getUri() + ">  ?p1  ?x. ?x ?p2 <"
					+ e2.getUri() + ">.} union {<" + e2.getUri()
					+ "> ?p2  ?x. ?x ?p1 <" + e1.getUri() + ">.} union {<"
					+ e1.getUri() + "> ?p1 ?x. <" + e2.getUri()
					+ "> ?p2 ?x. } " + " union {?x ?p1 <" + e1.getUri()
					+ ">. ?x ?p2 <" + e2.getUri() + "> .} } ";

			Firstdistance = 2;
			SecodDistance = 4;

		}

		else if (e1.getType() == Constants.TYPE_PROPERTY
				&& e2.getType() == Constants.TYPE_PROPERTY) {
			Firstdistance = 2;

			// this query compute lenght = 2
			query1 = " ask WHERE {  {?x <" + e1.getUri() + ">  ?y. ?y  <"
					+ e2.getUri() + "> ?z.} union {?z <" + e2.getUri()
					+ "> ?y. ?y <" + e1.getUri() + "> ?x.} union {?x <"
					+ e1.getUri() + ">  ?y. ?z <" + e2.getUri() + "> ?y. } "
					+ " union {?y <" + e1.getUri() + "> ?x. ?y <" + e2.getUri()
					+ "> ?z. } } ";

			/*
			 * // this query compute lenght = 3 query2 = " ask WHERE {  {?x <"+
			 * e1.getUri() + ">  ?y. ?y ?p ?s. ?s  <"+ e2.getUri() +
			 * "> ?z.} union {?z <"+ e2.getUri()+ "> ?y. ?y <"+ e1.getUri() +
			 * "> ?x. union {?x <"+ e1.getUri()+">  ?y. ?z <"+
			 * e2.getUri()+"> ?y. } "+ " union {?y <"+ e1.getUri()+"> ?x. ?y <"+
			 * e2.getUri()+"> ?z. } ";
			 */

		}

		else if (e1.getType() == Constants.TYPE_CLASS
				&& e2.getType() == Constants.TYPE_PROPERTY) {
			Firstdistance = 1;
			SecodDistance = 3;

			// this query compute lenght = 3
			query1 = " ask WHERE {  {?v a <" + e1.getUri() + ">. ?v  <"
					+ e2.getUri() + "> ?x.} union { ?x <" + e2.getUri()
					+ "> ?v. ?v a <" + e1.getUri() + ">.} } ";

			// this query compute lenght = 5
			query2 = " ask WHERE {  {?v a <" + e1.getUri()
					+ ">. ?v ?p1 ?x. ?x  <" + e2.getUri()
					+ "> ?y.} union {?y <" + e2.getUri()
					+ ">  ?x. ?x ?p1 ?v. ?v a <" + e1.getUri()
					+ ">.} union {?v a <" + e1.getUri() + ">. ?v ?p1 ?x. ?y <"
					+ e2.getUri() + "> ?x. } " + " union {?x ?p1 ?v. ?v a<"
					+ e1.getUri() + ">. ?x <" + e2.getUri() + "> ?y.} } ";

		}

		else if (e1.getType() == Constants.TYPE_PROPERTY
				&& e2.getType() == Constants.TYPE_CLASS) {
			Firstdistance = 1;
			SecodDistance = 3;

			// this query compute lenght = 3
			query1 = " ask WHERE {  {?v a <" + e2.getUri() + ">. ?v  <"
					+ e1.getUri() + "> ?x.} union { ?x <" + e1.getUri()
					+ "> ?v. ?v a <" + e2.getUri() + ">.} } ";

			// this query compute lenght = 5
			query2 = " ask WHERE {  {?v a <" + e2.getUri()
					+ ">. ?v ?p1 ?x. ?x  <" + e1.getUri()
					+ "> ?y.} union {?y <" + e1.getUri()
					+ ">  ?x. ?x ?p1 ?v. ?v a <" + e2.getUri()
					+ ">. } union {?v a <" + e2.getUri() + ">. ?v ?p1 ?x. ?y <"
					+ e1.getUri() + "> ?x. } " + " union {?x ?p1 ?v. ?v a<"
					+ e2.getUri() + ">. ?x <" + e1.getUri() + "> ?y.} } ";

		}

		else if (e1.getType() == Constants.TYPE_INSTANCE
				&& e2.getType() == Constants.TYPE_CLASS) {
			Firstdistance = 2;
			SecodDistance = 4;

			// this query compute lenght = 4
			query1 = " ask WHERE {  {?v a <" + e2.getUri() + ">. ?v ?p1 <"
					+ e1.getUri() + ">.} union { <" + e1.getUri()
					+ "> ?p1 ?v. ?v a <" + e2.getUri() + ">.}  }";

			// this query compute lenght = 6
			query2 = " ask WHERE {  {?v a <" + e2.getUri()
					+ ">. ?v ?p1 ?x. ?x ?p2 <" + e1.getUri() + ">.} union {<"
					+ e1.getUri() + "> ?p2  ?x. ?x ?p1 ?v. ?v a <"
					+ e2.getUri() + ">.} union {?v a <" + e2.getUri()
					+ ">. ?v ?p1 ?x. <" + e1.getUri() + "> ?p2 ?x. } "
					+ " union {?x ?p1 ?v. ?v a<" + e2.getUri() + ">. ?x ?p2 <"
					+ e1.getUri() + "> . } } ";

		}

		else if (e1.getType() == Constants.TYPE_CLASS
				&& e2.getType() == Constants.TYPE_INSTANCE) {
			Firstdistance = 2;
			SecodDistance = 4;

			// this query compute lenght = 4
			query1 = " ask WHERE {  {?v a <" + e1.getUri() + ">. ?v ?p1 <"
					+ e2.getUri() + ">.} union { <" + e2.getUri()
					+ "> ?p1 ?v. ?v a <" + e1.getUri() + ">.} } ";

			// this query compute lenght = 6
			query2 = " ask WHERE {  {?v a <" + e1.getUri()
					+ ">. ?v ?p1 ?x. ?x ?p2 <" + e2.getUri() + ">.} union {<"
					+ e2.getUri() + "> ?p2  ?x. ?x ?p1 ?v. ?v a <"
					+ e1.getUri() + ">.} union {?v a <" + e1.getUri()
					+ ">. ?v ?p1 ?x. <" + e2.getUri() + "> ?p2 ?x. } "
					+ " union {?x ?p1 ?v. ?v a<" + e1.getUri() + ">. ?x ?p2 <"
					+ e2.getUri() + "> . } } ";
		}

		else if (e1.getType() == Constants.TYPE_INSTANCE
				&& e2.getType() == Constants.TYPE_PROPERTY) {
			Firstdistance = 1;
			SecodDistance = 3;
			// this query compute lenght = 3
			query1 = " ask WHERE {  { <" + e1.getUri() + ">  <" + e2.getUri()
					+ "> ?x.} union { ?x <" + e2.getUri() + ">  <"
					+ e1.getUri() + ">.} } ";

			// this query compute lenght = 5
			query2 = " ask WHERE {  { <" + e1.getUri() + ">  ?p1 ?x. ?x  <"
					+ e2.getUri() + "> ?y.} union {?y <" + e2.getUri()
					+ ">  ?x. ?x ?p1  <" + e1.getUri() + ">.} union { <"
					+ e1.getUri() + "> ?p1 ?x. ?y <" + e2.getUri() + "> ?x. } "
					+ " union {?x ?p1 <" + e1.getUri() + ">. ?x <"
					+ e2.getUri() + "> ?y.} } ";

		}

		else if (e1.getType() == Constants.TYPE_PROPERTY
				&& e2.getType() == Constants.TYPE_INSTANCE) {
			Firstdistance = 1;
			SecodDistance = 3;
			// this query compute lenght = 3
			query1 = " ask WHERE {  { <" + e2.getUri() + ">  <" + e1.getUri()
					+ "> ?x.} union { ?x <" + e1.getUri() + ">  <"
					+ e2.getUri() + ">.} } ";

			// this query compute lenght = 5
			query2 = " ask WHERE {  { <" + e2.getUri() + "> ?p1 ?x. ?x  <"
					+ e1.getUri() + "> ?y.} union {?y <" + e1.getUri()
					+ ">  ?x. ?x ?p1  <" + e2.getUri() + ">.} union { <"
					+ e2.getUri() + "> ?p1 ?x. ?y <" + e1.getUri() + "> ?x. } "
					+ " union {?x ?p1 <" + e2.getUri() + ">. ?x <"
					+ e1.getUri() + "> ?y.} } ";

		}

		// System.out.println("-------retrieved query is:"+query1);
		// System.out.println("-------retrieved query is:"+query2);
		String urlsever = Constants.SPARQLEndPoint;
		;
		// Query query=QueryFactory.create(querystring);
		QueryEngineHTTP qexec = new QueryEngineHTTP(urlsever, query1);
		// QueryExecution qexec =
		// QueryExecutionFactory.sparqlService(urlsever,query);
		qexec.addDefaultGraph(Constants.DefaultGraphString);
		int Distance = Constants.MaximumDistance;
		boolean result = false;
		try {
			result = qexec.execAsk();

			if (result) {
				Distance = Firstdistance;
				// System.out.println("in first neighborhood found");
			} else if (!(e1.getType() == Constants.TYPE_PROPERTY && e2
					.getType() == Constants.TYPE_PROPERTY)) {
				qexec = new QueryEngineHTTP(urlsever, query2);
				qexec.addDefaultGraph(Constants.DefaultGraphString);
				result = qexec.execAsk();
				if (result) {
					Distance = SecodDistance;
					// System.out.println("in second neighborhood found");
				} else {
					// System.out.println("nothing found");
				}

			}

		} catch (Exception e) {

			System.out.println("finish by exeption" + e.getMessage());
		} finally {
		}

		return Distance;

	}

	// ####################################################################################

	public boolean checkExistenceOfPatterns(String Pattern) {

		List<StateInfo> foundRessources = new ArrayList<StateInfo>();

		String querystring;
		querystring = " PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ " ask WHERE { ?iri rdfs:label ?label.   ?label  bif:contains  '"
				+ Pattern + "'  "
				+ " FILTER( langMatches(lang(?label), \"en\")).  } ";

		System.out.println("-------retrieved query is:" + querystring);
		String urlsever = Constants.SPARQLEndPoint;

		// Query query=QueryFactory.create(querystring);
		QueryEngineHTTP qexec = new QueryEngineHTTP(urlsever, querystring);
		// QueryExecution qexec = QueryExecutionFactory.create(querystring);

		// QueryExecution qexec = QueryExecutionFactory.create(querystring,
		// Syntax.syntaxSPARQL_11, DatasetFactory.create());

		// QueryExecution qexec =
		// QueryExecutionFactory.sparqlService(urlsever,querystring);
		// qexec.addDefaultGraph(Constants.DefaultGraphString);

		boolean result = false;
		try {
			result = qexec.execAsk();
		} catch (Exception e) {

			System.out.println("finish by exeption" + e.getMessage());
		} finally {
		}
		return result;
	}

	// #################################
	// ###### get the degree for single entities
	// #########################################
	public double getDegree(StateInfo r) {

		List<StateInfo> foundRessources = new ArrayList<StateInfo>();
		String SparqlQueryString;

		if (r.getType() == Constants.TYPE_CLASS) {
			SparqlQueryString = " PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
					+ " select count (distinct ?s) as ?count WHERE { ?s a  <"
					+ r.getUri() + "> .} ";

		} else if (r.getType() == Constants.TYPE_PROPERTY) {
			SparqlQueryString = " PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
					+ " select count (distinct * ) as ?count  WHERE { ?s  <"
					+ r.getUri() + ">  ?o.} ";

		} else {
			SparqlQueryString = " select count (distinct ?s) as ?count where { {?s ?p <"
					+ r.getUri() + ">.} union {<" + r.getUri() + "> ?p ?s.} } ";
		}

		SimilarityCalculation functionalityObject = new SimilarityCalculation();
		String querystring;
		String urlsever = "jdbc:virtuoso://139.18.2.96:1130/";
		double score = 0;

		if (r.getType() == Constants.TYPE_PROPERTY) {

			String query = "SELECT Degree  FROM  DB.DBA.DBPedia_PredicateDegree "
					+ " where Predicate like '" + r.getUri() + "'";

			Statement st;
			try {
				Class.forName("virtuoso.jdbc4.Driver");
				Connection conn = DriverManager.getConnection(urlsever, "dba",
						"dba123");

				st = conn.createStatement();
				java.sql.ResultSet rs = st.executeQuery(query);
				while (rs.next()) {
					double degree = Math.log(rs.getInt("Degree"))
							/ Math.log(10);
					score = functionalityObject.normalizeValue(
							Constants.MinimumDegree_property,
							Constants.MaximumDegree_property, degree);

				}
				st.close();
				conn.close();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (r.getType() == Constants.TYPE_CLASS) {

			String query = "SELECT OutDegree  FROM  DB.DBA.DBPedia_ObjectInDegree "
					+ " where Object like '" + r.getUri() + "'";

			Statement st;
			try {
				Class.forName("virtuoso.jdbc4.Driver");
				Connection conn = DriverManager.getConnection(urlsever, "dba",
						"dba123");
				st = conn.createStatement();
				java.sql.ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					double degree = Math.log(rs.getInt("InDegree"))
							/ Math.log(10);
					score = functionalityObject.normalizeValue(
							Constants.MinimumDegree_Class,
							Constants.MaximumDegree_Class, degree);

				}
				st.close();
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (r.getType() == Constants.TYPE_INSTANCE) {

			String query = " select  InDegree+OutDegree as Degree from  DBPedia_ObjectInDegree  inner join DBPedia_SubjectOutDegree on Subject=Object "
					+ " where Object like '" + r.getUri().trim() + "'";

			Statement st;
			boolean checkflag = false;
			try {
				Class.forName("virtuoso.jdbc4.Driver");
				Connection conn = DriverManager.getConnection(urlsever, "dba",
						"dba123");
				st = conn.createStatement();
				java.sql.ResultSet rs = st.executeQuery(query);
				int degree1 = 0;
				if (rs.next()) {
					degree1 = rs.getInt("Degree");
					double degree = Math.log(degree1) / Math.log(10);
					score = functionalityObject.normalizeValue(
							Constants.MinimumDegree_instance,
							Constants.MaximumDegree_instance, degree);
				} else {
					checkflag = true;
				}

				if (degree1 == 0) {
					checkflag = true;
				}

				st.close();
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (checkflag) {

				QueryEngineHTTP qexec = new QueryEngineHTTP(
						Constants.SPARQLEndPoint, SparqlQueryString);
				System.out.println(" sparql query " + SparqlQueryString);
				// qexec.addDefaultGraph(Constants.DefaultGraphString);

				try {
					ResultSet results = qexec.execSelect();
					// ResultSet results = q.execSelect();
					if (results.hasNext()) {

						QuerySolution solution = results.nextSolution();

						if (solution.get("count") != null) {
							System.out.println(" counting point "
									+ solution.get("count").asLiteral()
											.getValue());

							System.out.println(" integer value :) "
									+ solution.get("count").asLiteral()
											.getValue());
							double degree = Math.log(Integer.parseInt(solution
									.get("count").asLiteral().getValue()
									.toString()))
									/ Math.log(10);
							score = functionalityObject.normalizeValue(
									Constants.MinimumDegree_instance,
									Constants.MaximumDegree_instance, degree);

							System.out.println(" counting point :) " + score);

						}
					}

				} catch (Exception e) {

					System.out.println(" counting exception"
							+ e.getLocalizedMessage());

				}

			}

		}

		return score;

	}

}
