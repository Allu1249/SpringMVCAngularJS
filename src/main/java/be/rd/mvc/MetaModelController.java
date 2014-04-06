package be.rd.mvc;

import be.rd.beans.Todo;
import be.rd.jstree.model.IAttributeNode;
import be.rd.jstree.model.RootNode;
import be.rd.jstree.util.IAttributeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

/**
 * Created by ruben on 3/16/14.
 */
@Controller
@RequestMapping("/meta")
public class MetaModelController {

    @PersistenceContext
    EntityManager emf;

    @Autowired
    IAttributeResolver resolver;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public RootNode getRoot() {
        return resolveRootNode(Todo.class.getName());
    }

    @RequestMapping(value = "/{className}", method = RequestMethod.GET)
    @ResponseBody
    public RootNode getRoot(@PathVariable String className) {
        return resolveRootNode(className);
    }

    @RequestMapping(value = "/child/{className}", method = RequestMethod.GET)
    @ResponseBody
    public Set<IAttributeNode> getAttributesForClass(@PathVariable String className) {
        return resolveRootNode(className).getChildren();
    }

    private RootNode resolveRootNode(String className){
        RootNode result = new RootNode();
        try {
            javax.persistence.metamodel.EntityType t = emf.getMetamodel().entity(Class.forName(className));
            t.getAttributes();
            resolver.resolveRootNode(result, className, t.getAttributes());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return result;
    }

}