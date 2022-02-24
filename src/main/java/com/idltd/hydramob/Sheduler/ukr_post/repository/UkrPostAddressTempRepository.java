package com.idltd.hydramob.Sheduler.ukr_post.repository;

import com.idltd.hydramob.Sheduler.ukr_post.entity.UkrPostAddressTemp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UkrPostAddressTempRepository extends CrudRepository<UkrPostAddressTemp, Long> {
}
