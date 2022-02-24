package com.idltd.hydramob.repository.requests_outtime;

import com.idltd.hydramob.entity.requests_outtime.MenuRequestsOuttimeList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRequestsOuttimeListRepository extends CrudRepository<MenuRequestsOuttimeList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vUSERS_SIMP_REQ_OUTTIME(?1,?2))")
    List<MenuRequestsOuttimeList> queryRequestsOuttimeMenuByUserID(Long user_id, Long role_id);
}