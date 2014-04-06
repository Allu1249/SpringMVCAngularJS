package be.rd.jstree.util;

import be.rd.jstree.model.IAttributeNode;
import be.rd.jstree.model.IRootNode;

import javax.persistence.metamodel.Attribute;
import java.util.Set;

/**
 * Created by ruben on 3/20/14.
 */
public interface IAttributeResolver {

    /**
     * This method must resolve the attributes for a classname todo signature and wahat todo exactly ...
     *
     * @param att
     * @param className
     * @return
     */
    public IAttributeNode resolveAttributeNode(IAttributeNode att, String className);


    public IRootNode resolveRootNode(IRootNode rootNode, String className, Set<Attribute> atts);
}
