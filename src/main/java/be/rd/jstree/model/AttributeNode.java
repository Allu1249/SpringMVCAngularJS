package be.rd.jstree.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 * Created by ruben on 3/20/14.
 */
public class AttributeNode implements IAttributeNode {

    @PersistenceContext
    EntityManagerFactory emf; // must be a spring managed bean...

    private String text;
    private String data;
    private boolean children = false;

    // node for field
    public AttributeNode(String data, String text){
        this.data = data;
        this.text = text;
    }

    // node for link (has children and can be opened)
    public AttributeNode(String className){

        try{
            javax.persistence.metamodel.EntityType t = emf.getMetamodel().entity(Class.forName(className));
            this.text = className;
            this.data = t.getName();
            children = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isChildren() {
        return children;
    }

    public void setChildren(boolean children) {
        this.children = children;
    }
}
