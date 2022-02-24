package com.idltd.hydramob.repository.users_kpi_meets;

import com.idltd.hydramob.entity.users_kpi_meets.UserKPIMeetTypesList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserKPIMeetTypesListRepository extends CrudRepository<UserKPIMeetTypesList, Long> {

}