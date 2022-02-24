package com.idltd.hydramob.repository.projects_desc;

import com.idltd.hydramob.entity.projects_desc.ProjectFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectFileRepository extends CrudRepository<ProjectFile, Long> {
    @Query(nativeQuery = true, value = "select * from PROJECT_FILES where pr_file_id = ?1")
    ProjectFile queryByID(Long pr_file_id);
}
