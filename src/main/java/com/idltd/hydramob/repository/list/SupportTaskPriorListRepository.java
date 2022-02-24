package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.SupportTaskPriorList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportTaskPriorListRepository extends CrudRepository<SupportTaskPriorList, Long> {
    @Query(nativeQuery = true, value = "select SPTP_ID, SPTP_NAME, SPTP_SNAME, SPTP_DESCRIPTION, SPTP_COLOR from SUPPORT_TASK_PRIOR WHERE SPTP_ID = ?1")
    List<SupportTaskPriorList> querySupportTaskPriorListByID(Long sptp_id);

    @Query(nativeQuery = true, value = "select SPTP_ID, SPTP_NAME, SPTP_SNAME, SPTP_DESCRIPTION, SPTP_COLOR from SUPPORT_TASK_PRIOR")
    List<SupportTaskPriorList> querySupportTaskPriorListAll();
}
