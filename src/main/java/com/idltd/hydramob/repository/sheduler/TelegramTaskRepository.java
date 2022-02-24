package com.idltd.hydramob.repository.sheduler;

import com.idltd.hydramob.entity.TelegramTask;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelegramTaskRepository extends CrudRepository<TelegramTask, Long> {

    // Сообщения на отправку
    @Query(nativeQuery = true, value = "select * from TELEGRAMTASK where tt_state is null")
    List<TelegramTask> queryByStateisNull();

}
