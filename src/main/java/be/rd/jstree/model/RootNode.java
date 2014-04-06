package be.rd.jstree.model;

import javax.persistence.metamodel.Attribute;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ruben on 3/20/14.
 */
public class RootNode implements Serializable , IRootNode{
    private String text;
    private String data;
    /*private Set<String> fields;*/
    private Set<IAttributeNode> children;

    public RootNode(){
        children = new HashSet<IAttributeNode>();
    }

    public RootNode(String data, String text){
        this();
        this.data = data;
        this.text = text;
    }

    public String getText() {
        return text;
    }
    public void setText(String text){this.text = text;}

    public String getData() {
        return data;
    }
    public void setData(String data){this.data = data;}

    public Set<IAttributeNode> getChildren() {
        return children;
    }

    @Override
    public void addAttribute(String data, String text) {
        children.add(new AttributeNode(data, text));
    }

    @Override
    public void addLink(String data, String text) {
        AttributeNode att = new AttributeNode(data, text);
        att.setChildren(true);
        children.add(att);
    }
}
