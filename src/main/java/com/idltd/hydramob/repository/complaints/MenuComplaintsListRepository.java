package com.idltd.hydramob.repository.complaints;

import com.idltd.hydramob.entity.complaints.MenuComplaintsList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuComplaintsListRepository extends CrudRepository<MenuComplaintsList, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_CONTRAGENT_VIEW.vCONTRAGENT_COMPLAINTS_MENU(?1,?2))")
    List<MenuComplaintsList> queryUserKPIMeetUsersListByUserID(Long user_id, Long role_id);
}