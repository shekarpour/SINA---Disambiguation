package disambiguation.sina.org;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        
        String inputQuery = "capital of Germany";
        HiddenMarkovModel model = new HiddenMarkovModel();
        
        model.runModel(inputQuery);
    }
}
