package be.rd.jstree.util;

import be.rd.jstree.model.IAttributeNode;
import be.rd.jstree.model.IRootNode;
import org.hibernate.metamodel.domain.AbstractAttributeContainer;
import org.hibernate.metamodel.source.hbm.ManyToManyPluralAttributeElementSourceImpl;
import org.springframework.stereotype.Component;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.PluralAttribute;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ruben on 3/20/14.
 * In conclusion:
 * The JPA meta classes are build for one specific purpose: building queries with the criteriabuilder.
 * When using them for other purposes you soon run into various problems, some of them can be solved
 * with a little creativity others can't. For example you cannot add extra information like sequenceNrs
 * to the meta info.
 * If you want meta info for creation selections (root + conditionItems + displayItems) it is a good idea
 * to just create your own meta info classes.
 * They could be generated using a combination of the JPA annotations and your own annotations.
 */
@Component
public class AttributeResolver implements IAttributeResolver{


    @Override
    public IAttributeNode resolveAttributeNode(IAttributeNode att, String className) {
        return null;
    }

    @Override
    public IRootNode resolveRootNode(IRootNode rootNode, String className, Set<Attribute> atts) {

            rootNode.setText(className);
            rootNode.setData(className);

            Iterator<Attribute> it = atts.iterator();
            for (int i = 0; it.hasNext(); i++)
            {
                Attribute att= it.next();
                if(isAssociation(att)){
                    String text = att.getJavaType().getName();
                    if(Attribute.PersistentAttributeType.MANY_TO_MANY
                                    ==  att.getPersistentAttributeType()
                        || Attribute.PersistentAttributeType.ONE_TO_MANY
                            ==  att.getPersistentAttributeType())
                    {
                        text = ((PluralAttribute) att).getElementType().getJavaType().getName();
                    }

                    String data = att.getName();
                    rootNode.addLink(data, text);
                }else{
                    rootNode.addAttribute(att.getName(), att.getName());
                }

            }
        return rootNode;
    }

    /** need to check this myself since org.hibernate.ejb.metamodel.SingularAttributeImpl.isAssocation()
     * is "implemented" to always return false.
     * ==> possible to do something with AOP here?? dunno since class is not loaded as spring bean?
     * TOOD what about type EMBEDDED??
     * @return
     */
    private boolean isAssociation(Attribute att){
        if(Attribute.PersistentAttributeType.BASIC ==  att.getPersistentAttributeType()){
            return false;
        }
        return true;
    }
}
