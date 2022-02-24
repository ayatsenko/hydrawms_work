package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.ProjectPaymentTypeList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectPaymentTypeRepository extends CrudRepository<ProjectPaymentTypeList, Long> {
    @Query(nativeQuery = true, value = "select * from PROJECT_PAYMENTS_TYPE")
    List<ProjectPaymentTypeList> queryProjectPaymentTypeByID();
}
