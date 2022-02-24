package com.idltd.hydramob.repository.clients;

import com.idltd.hydramob.entity.clients.ClientUser_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientUser_ListRepository extends CrudRepository<ClientUser_List, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM USERS WHERE ID NOT IN (SELECT USER_ID FROM VCONTRAGENT_USER_LINK where cnt_id = ?1) AND USER_STATUS_ID not in (-1,-2) AND TEST_ID <> 1 ORDER BY USER_SURNAME")
    List<ClientUser_List> queryNewCntUserByID(Long cnt_id);
}
