package com.idltd.hydramob.repository.tms_locals;

import com.idltd.hydramob.entity.tms_locals.FLDClaimFileTemp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FLDClaimFileTempRepository extends CrudRepository<FLDClaimFileTemp, Long> {
}
