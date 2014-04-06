package be.rd.jstree.model;

import java.util.Set;

/**
 * Created by ruben on 3/20/14.
 */
public interface IRootNode {

    public String getText();
    public void setText(String text);

    public String getData();
    public void setData(String data);

    public Set<IAttributeNode> getChildren();

    public void addAttribute(String data, String text);
    public void addLink(String data, String text);
}
