package com.idltd.hydramob.repository.sheduler;

import com.idltd.hydramob.Sheduler.entity.Sheduler_Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Sheduler_EventRepository extends CrudRepository<Sheduler_Event, Long> {

    @Query(nativeQuery = true, value = "select * from SHEDULER_EVENT where se_state is null")
    List<Sheduler_Event> queryByStateisNull();

}
