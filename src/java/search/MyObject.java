package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import messif.objects.LocalAbstractObject;
import messif.objects.impl.MetaObjectMap;
import messif.objects.nio.BinaryInput;
import messif.objects.nio.BinarySerializator;

public class MyObject extends MetaObjectMap {

    public MyObject(String locatorURI, Map<String, LocalAbstractObject> objects, boolean cloneObjects) throws CloneNotSupportedException {
        super(locatorURI, objects, cloneObjects);
    }

    public MyObject(String locatorURI, Map<String, LocalAbstractObject> objects) {
        super(locatorURI, objects);
    }

    public MyObject(BufferedReader stream, Set<String> restrictNames) throws IOException {
        super(stream, restrictNames);
    }

    public MyObject(BufferedReader stream, String[] restrictNames) throws IOException {
        super(stream, restrictNames);
    }

    public MyObject(BufferedReader stream) throws IOException {
        super(stream);
    }

    public MyObject(BinaryInput input, BinarySerializator serializator) throws IOException {
        super(input, serializator);
    }
    
    
    @Override
    protected float getDistanceImpl(LocalAbstractObject obj, float[] metaDistances, float distThreshold) {
        MyObject o = (MyObject)obj;
        
        float dist1 = this.getObject("ColorStructureType").getDistance(o.getObject("ColorStructureType"));
        float dist2 = this.getObject("ScalableColorType").getDistance(o.getObject("ScalableColorType"));
        
        return dist1 + dist2;
    }
}
