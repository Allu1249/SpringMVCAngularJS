package be.rd.service;

import be.rd.beans.Todo;

import java.util.List;

/**
 * Created by ruben on 3/13/14.
 */
public interface ITodoService
{
    public List<Todo> findAll();
    public Todo findById(Long id);
    public Todo save(Todo todo);

}
