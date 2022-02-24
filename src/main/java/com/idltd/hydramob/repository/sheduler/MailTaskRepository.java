package com.idltd.hydramob.repository.sheduler;

import com.idltd.hydramob.entity.MailTask;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailTaskRepository extends CrudRepository<MailTask, Long> {

    // Письма на отправку
    @Query(nativeQuery = true, value = "select * from MAILTASK where mt_state is null")
    List<MailTask> queryByStateisNull();

}
