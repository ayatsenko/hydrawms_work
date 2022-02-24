package com.idltd.hydramob.repository.sheduler;

import com.idltd.hydramob.entity.sheduler.ShedulerFileArch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileArchRepository extends CrudRepository<ShedulerFileArch, Long> {

    @Query(nativeQuery = true, value = "select * from VFILE_ARCH where fa_id=?1")
    List<ShedulerFileArch> queryByFA_ID(Long fa_id);
}
