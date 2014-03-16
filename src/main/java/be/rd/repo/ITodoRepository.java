package be.rd.repo;

import be.rd.beans.Todo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ruben on 3/13/14.
 */
public interface ITodoRepository extends CrudRepository<Todo, Long>
{

}
