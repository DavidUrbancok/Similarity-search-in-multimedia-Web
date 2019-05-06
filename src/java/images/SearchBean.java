package images;

import java.io.IOException;
import java.util.Iterator;
import messif.algorithms.Algorithm;
import messif.algorithms.AlgorithmMethodException;
import messif.objects.LocalAbstractObject;
import messif.operations.query.GetObjectByLocatorOperation;
import messif.operations.query.KNNQueryOperation;


public class SearchBean {
    static final Algorithm alg;
    static {
        try {
            alg = Algorithm.restoreFromFile("C:\\Users\\David\\Documents\\SimilaritySearch-Web\\images-collection.ser");
        } catch (IOException | ClassCastException | ClassNotFoundException | NullPointerException e) {
            throw new InternalError(e);
        }
    }
    
    private LocalAbstractObject query;
    private int k = 10;
    
    public void setQuery(String locator) throws AlgorithmMethodException, NoSuchMethodException {
        GetObjectByLocatorOperation operation = alg.executeOperation(new GetObjectByLocatorOperation(locator));
        this.query = (LocalAbstractObject)operation.getAnswerObject();
    }
    
    public Iterator getResults() throws Exception {
        if (query == null) {
            return null;
        }
        
        KNNQueryOperation op = alg.executeOperation(new KNNQueryOperation(query, k));
        return op.getAnswer();
    }
}
