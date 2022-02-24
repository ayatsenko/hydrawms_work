package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.User_List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface User_ListRepository extends CrudRepository<User_List, Long> {
    @Query(nativeQuery = true, value = "select * from USERS where username = ?1 ORDER BY USERNAME")
    List<User_List> queryUserByName(String username);

    @Query(nativeQuery = true, value = "select * from USERS where id = ?1 ORDER BY USERNAME")
    List<User_List> queryUserByID(Long user_id);

    @Query(nativeQuery = true, value = "select * from USERS where USER_STATUS_ID not in (-1,-2) AND TEST_ID <> 1 ORDER BY USERNAME")
    List<User_List> queryUserAll();

    @Query(nativeQuery = true, value = "select * from USERS where USER_STATUS_ID not in (-1,-2) AND TEST_ID <> 1 ORDER BY USER_SURNAME")
    List<User_List> queryUserAllOrderDetail();

    @Query(nativeQuery = true, value = "select * from USERS where USER_STATUS_ID not in (-1,-2) AND TEST_ID <> 1 " +
            "AND( ID in (SELECT SUB_USER_ID FROM USER_USERS_LINK WHERE USER_ID = ?1) or ID = ?1) ORDER BY USER_SURNAME" +
            "")
    List<User_List> queryUserAndSubsAllOrderDetail(Long user_id);

    @Query(nativeQuery = true, value = "select * from USERS where USER_STATUS_ID not in (-2) AND TEST_ID <> 1 AND ID in (SELECT USER_ID FROM USERS_ROLES WHERE ROLE_ID in (2,4,5,8)) ORDER BY USER_SURNAME")
    List<User_List> querySaleUserAll();

    @Query(nativeQuery = true, value = "select * from USERS where USER_STATUS_ID not in (-2) AND TEST_ID <> 1 AND ID in (SELECT USER_ID FROM USERS_ROLES WHERE ROLE_ID in (3,6,9)) ORDER BY USER_SURNAME")
    List<User_List> queryOPSUserAll();

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vUSER_SALE_LIST(?1,?2)) ORDER BY USER_SURNAME")
    List<User_List> querySaleUserByUserID(Long user_id, Long role_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.vUSER_OPS_LIST(?1,?2)) ORDER BY USER_SURNAME")
    List<User_List> queryOpsUserByUserID(Long user_id, Long role_id);

    @Query(nativeQuery = true, value = "select * from TABLE(PKG_WMS_VIEW.vPAIR_NEW_USER(?1,?2,?3)) ORDER BY USER_SURNAME")
    List<User_List> queryNewPairUserByUserID(Long user_id, Long role_id, Long pair_id);
}
