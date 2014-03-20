package be.rd.mvc;

import be.rd.beans.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.Attribute;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ruben on 3/16/14.
 */
@Controller
@RequestMapping("/meta")
public class MetaModelController {

    @PersistenceContext
    EntityManager emf;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public SelectionNode getRoot()
    {
        return new SelectionNode(Todo.class.getName());
    }

    @RequestMapping(value = "/{className}", method = RequestMethod.GET)
    @ResponseBody
    public SelectionNode getRoot(@PathVariable String className)
    {
        return new SelectionNode(className);
    }

    @RequestMapping(value = "/child/{className}", method = RequestMethod.GET)
    @ResponseBody
    public Set<ClosedNode> getAttributesForClass(@PathVariable String className)
    {
        return new SelectionNode(className).getChildren();
    }

    /**
     *
     */
    class ClosedNode implements  Serializable
    {
        private String text;
        private String data;
        private boolean children = false;

        // node for field
        public ClosedNode(String data, String text){
            this.data = data;
            this.text = text;
        }

        // node for link (has children and can be opened)
        public ClosedNode(String className){

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

    /**
     *
     */
    class SelectionNode implements Serializable
    {
        private String text;
        private String data;
        /*private Set<String> fields;*/
        private Set<ClosedNode> children;

        public SelectionNode(String data, String text){
            this.data = data;
            this.text = text;
        }

        public SelectionNode(String className){

            children = new HashSet<ClosedNode>();
            try {
                javax.persistence.metamodel.EntityType t = emf.getMetamodel().entity(Class.forName(className));
                this.text = className;
                this.data = t.getName();
                Iterator<Attribute> it = t.getAttributes().iterator();
                for (int i = 0; it.hasNext(); i++)
                {
                    Attribute att= it.next();
                    if(isAssociation(att)){
                        children.add(new ClosedNode(att.getJavaType().getName()));
                    }else{
                        children.add(new ClosedNode(att.getName(), att.getName()));
                    }

                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public String getText() {
            return text;
        }

        public String getData() {
            return data;
        }

/*        public Set<String> getFields() {
            return fields;
        }*/

        public Set<ClosedNode> getChildren() {
            return children;
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