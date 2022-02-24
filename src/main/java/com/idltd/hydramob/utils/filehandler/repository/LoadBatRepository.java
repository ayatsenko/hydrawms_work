package com.idltd.hydramob.utils.filehandler.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.idltd.hydramob.utils.filehandler.entity.LoadBat;

import java.util.List;

@Repository
public interface LoadBatRepository extends CrudRepository<LoadBat, Long> {

    // Не экспортированные в Quguar
    @Query(nativeQuery = true, value = "select distinct lt_part from LOAD_BAT where LT_EXPORTED=0")
    List<Long> getnotexportedLt_part();

    // Получить все по Lt_part
    @Query(nativeQuery = true, value = "select * from LOAD_BAT where lt_part=:lt_part")
    List<LoadBat> getbyLt_part(@Param("lt_part") Long lt_part);

    // Заказы в файле
    @Query(nativeQuery = true, value = "select distinct lt_delivery from LOAD_BAT where lt_part=:lt_part")
    List<String> getDistDeliverybyLt_part(@Param("lt_part") Long lt_part);

    // Заголовок по заказу Приход
    @Query(nativeQuery = true, value = "select *  from LOAD_BAT where lt_part=:lt_part and lt_delivery=:delivery and lt_sheet=1 and rownum=1")
    LoadBat getHeaderbyDelivery1(@Param("lt_part") Long lt_part, @Param("delivery") String delivery );
    // Строчки по заказу Приход
    @Query(nativeQuery = true, value = "select *  from LOAD_BAT where lt_part=:lt_part and lt_delivery=:delivery and lt_sheet=1 and lt_batch is not null")
    List<LoadBat> getItembyDelivery1(@Param("lt_part") Long lt_part, @Param("delivery") String delivery );

    // Заголовок по заказу Расход
    @Query(nativeQuery = true, value = "select *  from LOAD_BAT where lt_part=:lt_part and lt_delivery=:delivery and lt_sheet=0 and rownum=1")
    LoadBat getHeaderbyDelivery0(@Param("lt_part") Long lt_part, @Param("delivery") String delivery );
    // Строчки по заказу Расход
    @Query(nativeQuery = true, value = "select *  from LOAD_BAT where lt_part=:lt_part and lt_delivery=:delivery and lt_sheet=0")
    List<LoadBat> getItembyDelivery0(@Param("lt_part") Long lt_part, @Param("delivery") String delivery );

    // Sar файл (только уникальные по материалу)
    @Query(nativeQuery = true, value = "select * from LOAD_BAT where lt_id in (select  distinct first_value(lt_id) over (partition by lt_material order by lt_id) from LOAD_BAT where lt_part=:lt_part and lt_sheet=1)")
    List<LoadBat> getSarbyLt_part(@Param("lt_part") Long lt_part);

    // Ser файл (только уникальные по материалу и серии
    @Query(nativeQuery = true, value = "select * from LOAD_BAT where lt_id in (select  distinct first_value(lt_id) over (partition by lt_batch, lt_material order by lt_id) from LOAD_BAT where lt_part=:lt_part and lt_batch is not null)")
    List<LoadBat> getSerbyLt_part(@Param("lt_part") Long lt_part);

    // Skh файл (только уникальные по материалу)
    @Query(nativeQuery = true, value = "select * from LOAD_BAT where lt_id in (select  distinct first_value(lt_id) over (partition by lt_soldtoparty order by lt_id) from LOAD_BAT where lt_part=:lt_part and lt_sheet=0 and lt_soldtoparty is not null)")
    List<LoadBat> getSkhbyLt_part(@Param("lt_part") Long lt_part);

    // Получить Клиента которому грузим
    @Query(nativeQuery = true, value = "select distinct lt_client from LOAD_BAT where lt_part=:lt_part")
    String getClientbyLt_part(@Param("lt_part") Long lt_part);
}
