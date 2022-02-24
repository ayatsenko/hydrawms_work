package com.idltd.hydramob.repository.list;

import com.idltd.hydramob.entity.list.PalletList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PalletListRepository extends CrudRepository<PalletList, Long> {
    @Query(nativeQuery = true, value = "select * from PALLET_TYPE ORDER BY PAL_TYPE_ID")
    List<PalletList> queryPalletTypeListBy();
}
