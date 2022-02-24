package com.idltd.hydramob.repository.user_result;

import com.idltd.hydramob.entity.user_result.ViewUserFinResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ViewClientFinResultRepository extends CrudRepository<ViewUserFinResult, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.VUSER_FIN_DETAIL(?1,?2,?3))")
    List<ViewUserFinResult> queryUserFinResultByCntID(Long user_id, Long role_id, Long year);
}
