package com.idltd.hydramob.repository.todo_list;

import com.idltd.hydramob.entity.todo_list.ToDoList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListRepository extends CrudRepository<ToDoList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_ACTION_VIEW.VACTION_TODOLIST(?1))")
    List<ToDoList> queryToDoListByUserID(Long user_id);
}