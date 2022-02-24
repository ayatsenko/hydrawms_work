package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.ProjectRolesList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRolesListRepository extends CrudRepository<ProjectRolesList, Long> {

}
