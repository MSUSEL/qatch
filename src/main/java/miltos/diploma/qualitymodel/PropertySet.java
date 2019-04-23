package miltos.diploma.qualitymodel;

import java.util.Vector;
import java.util.Iterator;

public class PropertySet implements Cloneable{

    /**
     * This class is just a vector of Property objects.
     */

    private Vector<Property> properties;


    public PropertySet(){
        properties = new Vector<>();
    }
    public PropertySet(PropertySet p){
        this.properties = p.properties;
    }
    public PropertySet(Vector<Property> properties) {
        this.properties = properties;
    }


    public Vector<Property> getPropertyVector(){
        return properties;
    }

    public void setPropertyVector(Vector<Property> properties){
        this.properties=properties;
    }

    public void addProperty(Property p){
        properties.add(p);
    }

    public void addProperty(int index, Property p){
        properties.add(index, p);
    }

    public void clearProperties(){
        properties.clear();
    }

    public boolean containsProperty(Property p){
        return properties.contains(p);
    }

    public Property get(int index){
        return properties.get(index);
    }

    public Property get(String name) {
        for (Property p : properties) {
            if (p.getName().equals(name)) { return p; }
        }
        throw new RuntimeException("Unable to find property named " + name);
    }

    public boolean isEmpty(){
        return properties.isEmpty();
    }

    public Iterator<Property> iterator(){
        return properties.iterator();
    }

    public int indexOfProperty(Property p){
        return properties.indexOf(p);
    }

    public void removeProperty(int index){
        properties.remove(index);
    }

    public void removeProperty(Property p){
        properties.remove(p);
    }

    public int size(){
        return properties.size();
    }

    public Property[] toArray(){
        return (Property[]) properties.toArray();
    }

    public String toString(){
        return properties.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
