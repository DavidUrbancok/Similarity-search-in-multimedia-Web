package search;

import java.util.ArrayList;
import java.util.List;
import messif.algorithms.Algorithm;
import messif.algorithms.impl.SequentialScan;
import messif.objects.util.StreamGenericAbstractObjectIterator;
import messif.operations.data.InsertOperation;
import messif.operations.query.KNNQueryOperation;

public class Engine {
    private static final String COLLECTION_DATA_FILE = "C:\\Users\\David\\Documents\\SimilaritySearch-Web\\images-collection.data";
    private static final String QUERIES_DATA_FILE = "C:\\Users\\David\\Documents\\SimilaritySearch-Web\\images-collection.data";
    private static final int ANSWER_SIZE = 10;
    
    public static Algorithm createEngine() throws Exception {
        Algorithm algorithm = new SequentialScan();
        
        try (StreamGenericAbstractObjectIterator<MyObject> objects = 
                new StreamGenericAbstractObjectIterator<>(
                    MyObject.class,
                    COLLECTION_DATA_FILE
                );
        ) {
            while (objects.hasNext()) {
                algorithm.executeOperation(new InsertOperation(objects.next()));
            }
        }
        return algorithm;
    }
    
    public static List<KNNQueryOperation> search(Algorithm algorithm) throws Exception {
        List<KNNQueryOperation> list = new ArrayList<>();
        
        try (StreamGenericAbstractObjectIterator<MyObject> queries = 
                new StreamGenericAbstractObjectIterator<>(
                        MyObject.class,
                        QUERIES_DATA_FILE);
        ) {
            int count = 0;
            while (queries.hasNext()) {
                MyObject query = queries.next();
                KNNQueryOperation op = algorithm.executeOperation(new KNNQueryOperation(query, ANSWER_SIZE));
            
                list.add(op);
                if (count++ > 5) // Will go away
                    break;
            }
        }
        
        return list;
    } 
}
