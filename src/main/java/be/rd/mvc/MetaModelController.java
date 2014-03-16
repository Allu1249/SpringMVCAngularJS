package be.rd.mvc;

import be.rd.beans.Todo;
import be.rd.beans.Todo_;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ruben on 3/16/14.
 */
@Controller
@RequestMapping("/meta")
public class MetaModelController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public SelectionNode getRoot()
    {
        return new SelectionNode();
    }

    class SelectionNode implements Serializable
    {
        private String nodeName;
        private String[] fields;
        private SelectionNode[] links;

        public SelectionNode(){

            this.nodeName = Todo_.id.getDeclaringType().getJavaType().getSimpleName();
            Set<Attribute<Todo, ?>> atts = Todo_.id.getDeclaringType().getDeclaredAttributes();
            Iterator<Attribute<Todo, ?>> it = atts.iterator();
            fields = new String[atts.size()];
            for (int i = 0; it.hasNext(); i++)
            {
                Attribute<Todo, ?> att= it.next();
                att.getJavaMember();

               if(isAssociation(att)){
                    fields[i] = "link-" + att.getName();
                }else{
                    fields[i] = att.getName();
                }

            }
        }

        public String getNodeName() {
            return nodeName;
        }

        public String[] getFields() {
            return fields;
        }

        public SelectionNode[] getLinks() {
            return links;
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
}