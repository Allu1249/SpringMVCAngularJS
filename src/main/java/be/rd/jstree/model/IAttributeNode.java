package be.rd.jstree.model;

/**
 * Created by ruben on 3/20/14.
 */
public interface IAttributeNode {

    public String getText();
    public void setText(String text);

    public String getData();
    public void setData(String data);

    public boolean isChildren();
    public void setChildren(boolean children);
}
