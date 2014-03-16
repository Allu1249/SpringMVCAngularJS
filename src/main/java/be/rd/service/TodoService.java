package be.rd.service;

import be.rd.beans.Todo;
import be.rd.repo.ITodoRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ruben on 3/13/14.
 */
@Transactional
@Service
@Repository // this makes sure you use dataaccessexceptions
public class TodoService implements ITodoService
{
    @Autowired
    private ITodoRepository repo;

    @Override
    public List<Todo> findAll() {
        return Lists.newArrayList(repo.findAll());
    }

    @Override
    public Todo findById(Long id) {
        return repo.findOne(id);
    }

    @Override
    public Todo save(Todo todo) {
        return repo.save(todo);
    }
}
