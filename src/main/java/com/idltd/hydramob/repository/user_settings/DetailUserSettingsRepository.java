package com.idltd.hydramob.repository.user_settings;

import com.idltd.hydramob.entity.user_settings.DetailUserSettings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailUserSettingsRepository extends CrudRepository<DetailUserSettings, Long> {
    @Query(nativeQuery = true, value = "select * from TABLE(PKG_USERS_VIEW.VUSER_SETTINGS_DETAIL(?1,?2,?3))")
    List<DetailUserSettings> queryUserSettingsDetailByID(Long user_id, Long role_id, String user_name);
}