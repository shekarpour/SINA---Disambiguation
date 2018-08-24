package disambiguation.sina.org;


public class Constants {

	
	public static final int TYPE_INSTANCE       = 0;
	public static final int TYPE_CLASS          = 2;
	public static final int TYPE_VARIABLE       = 3;
	public static final int TYPE_LITERAL        = 4;
	public static final int TYPE_PROPERTY       = 1;
	
	public static final int TYPE_NE             = 1; // Named Entity
	public static final int TYPE_TE             = 2; // Term Expression
	public static final int TYPE_Single         = 3; // alone keyword
	public static final int TYPE_NP             = 4; // Noun Phrase
	
	public static final double MaximumDegree_Class       = 5.54; // for normalizing values  maximum value = 347442
	public static final double MinimumDegree_Class       = 0; // for normalizing values  minimum value = 1
	
	public static final double MaximumDegree_instance       = 5.1; // for normalizing values maximum indegree= 145797 , maximum outdegree = 3336.... sum = 149133
	public static final double MinimumDegree_instance       = 0; // for normalizing values  
	
	public static final double MaximumDegree_property       = 7; // for normalizing values maximum value = 10916554
	public static final double MinimumDegree_property       = 0; // for normalizing values  
	
	public static final int LimitOfList         = 4;
	
		
	public static final String DefaultGraphString ="http://dbpedia.org"; 
	public static final int MaximumDistance	= 100;
	
	public static final String SPARQLEndPoint="http://dbpedia.org/sparql";//"http://139.18.2.96:8910/sparql";//"http://139.18.2.96:8910/sparql";//"http://dbpedia.org/sparql";//"http://139.18.2.96:8910/sparql";//"http://dbpedia.org/sparql";//"http://139.18.2.96:8910/sparql";//"http://dbpedia.org/sparql";//"http://139.18.2.96:8910/sparql";//"http://dbpedia.org/sparql";//"http://live.dbpedia.org/sparql";//"http://dbpedia.org/sparql";//"http://live.dbpedia.org/sparql";//"http://dbpedia.org/sparql";//"//"http://139.18.2.96:8910/sparql"; //
	public static final String TaggerAddress= "left3words-wsj-0-18.tagger";
	public static final String ontologyAddress = "http://downloads.dbpedia.org/3.9/dbpedia_3.9.owl";
	
	
	
}
