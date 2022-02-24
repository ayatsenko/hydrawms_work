package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.CurrentUserDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrentUserDetailRepository extends CrudRepository<CurrentUserDetail, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_LISTS_VIEW.vCURRENT_USER_DETAIL(?1,?2))")
    List<CurrentUserDetail> queryCurrentUserDetailByID(
            Long user_id, Long role_id
    );
}
