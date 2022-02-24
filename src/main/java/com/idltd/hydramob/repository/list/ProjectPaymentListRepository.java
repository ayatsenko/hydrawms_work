package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.ProjectPaymentTypeList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectPaymentListRepository extends CrudRepository<ProjectPaymentTypeList, Long> {

}
