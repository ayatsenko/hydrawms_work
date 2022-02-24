package com.idltd.hydramob.repository.auto_tasks;

import com.idltd.hydramob.entity.auto_tasks.MenuAutoDKFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuAutoDKFilesRepository extends CrudRepository<MenuAutoDKFile, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM SHEDULER_DK_DETAIL")
    List<MenuAutoDKFile> queryMenuDKFileAll();
}