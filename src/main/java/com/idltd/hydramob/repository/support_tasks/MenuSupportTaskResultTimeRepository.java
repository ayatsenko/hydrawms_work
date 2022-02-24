package com.idltd.hydramob.repository.support_tasks;

import com.idltd.hydramob.entity.support_tasks.MenuSupportTaskResultTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuSupportTaskResultTimeRepository extends CrudRepository<MenuSupportTaskResultTime, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_SUPPORT_VIEW.VSUPPORT_TIME_RESULT(?1,?2,?3,?4))")
    List<MenuSupportTaskResultTime> queryGetSupportTaskResultTime(Long user_id, Long role_id, String start_date, String end_date);
}