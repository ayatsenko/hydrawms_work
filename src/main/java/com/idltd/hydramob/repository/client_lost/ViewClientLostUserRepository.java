package com.idltd.hydramob.repository.client_lost;

import com.idltd.hydramob.entity.client_lost.ViewClientLostUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewClientLostUserRepository extends CrudRepository<ViewClientLostUser, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM TABLE(PKG_CONTRAGENT_VIEW.VLOST_CONT_USER(?1,?2,?3))")
    List<ViewClientLostUser> queryClientLostByUserID(Long user_id, Long year, Long month);
}