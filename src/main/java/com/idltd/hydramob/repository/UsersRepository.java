package com.idltd.hydramob.repository;


import com.idltd.hydramob.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    //User findByUsername(String username);

    @Query(nativeQuery = true, value = "select * from USERS where username = ?1 and USER_STATUS_ID not in (-1,-2) order by username")
    User findByUsername(String username);

    @Query(nativeQuery = true, value = "select * from USERS where username like ?1 order by username")
    List<User> queryByLikeUsername(String username);

    @Query(nativeQuery = true, value = "select * from USERS where id = ?1")
    List<User> queryUserByID(Long id);

    @Query(nativeQuery = true, value = "select * from USERS where id = ?1")
    User queryCurrUserByID(Long id);
}
