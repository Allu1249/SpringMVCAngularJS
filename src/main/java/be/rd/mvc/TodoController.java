package be.rd.mvc;

import be.rd.beans.Todo;
import be.rd.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ruben on 3/13/14.
 */
@RequestMapping("/todo")
@Controller
public class TodoController
{
    @Autowired
    private ITodoService service;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Todo> findAll()
    {
        return service.findAll();
    }

}
