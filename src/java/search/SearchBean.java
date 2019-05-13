package search;

import java.util.List;
import messif.algorithms.Algorithm;
import messif.operations.query.KNNQueryOperation;

public class SearchBean {
    private static Algorithm algorithm;
    
    public List<KNNQueryOperation> getResults() throws Exception {
        if (algorithm == null) {
            algorithm = Engine.createEngine();
        }
        
        return Engine.search(algorithm);
    } 
}
