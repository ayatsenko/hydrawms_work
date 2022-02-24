package com.idltd.hydramob.repository;

import com.idltd.hydramob.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM roles WHERE id = ?1")
    Role queryRoleByID(long id);
}
