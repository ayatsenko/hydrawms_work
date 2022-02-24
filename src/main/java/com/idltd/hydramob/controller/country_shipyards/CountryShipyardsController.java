package com.idltd.hydramob.controller.country_shipyards;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.Country_ListRepository;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.country_shipyards.DetailCountryShipyardsMainRepository;
import com.idltd.hydramob.repository.country_shipyards.MenuCountryShipyardsMainRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

@RestController
@RequestMapping("/country_shipyards")
public class CountryShipyardsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuCountryShipyardsMainRepository menuCountryShipyardsMainRepository;
    private Country_ListRepository country_ListRepository;
    private DetailCountryShipyardsMainRepository detailCountryShipyardsMainRepository;
    
    public CountryShipyardsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuCountryShipyardsMainRepository menuCountryShipyardsMainRepository,
            Country_ListRepository country_ListRepository,
            DetailCountryShipyardsMainRepository detailCountryShipyardsMainRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuCountryShipyardsMainRepository = menuCountryShipyardsMainRepository;
        this.country_ListRepository = country_ListRepository;
        this.detailCountryShipyardsMainRepository = detailCountryShipyardsMainRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "id", required = false) Long id
    ){
        model.addObject("id", id);

        model.setViewName("country_shipyards/cover");
        return model;
    }

    @PostMapping("/get_main_table")
    public JSONDatatable get_main_table(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuCountryShipyardsMainRepository.queryCountryShipyardsMainByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id
        ));
        return result;
    }

    @GetMapping("/get_main_detail")
    public JSONDatatable get_main_detail(
            @RequestParam(name="shipyard_id", defaultValue = "0") Long shipyard_id,
            @RequestParam(name="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(shipyard_id != null && shipyard_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(detailCountryShipyardsMainRepository.queryDetailCountryShipyardsMainByUserID(
                    user_List.get(0).id,user_Role_List.get(0).role_id, shipyard_id
            ));
        }
        return result;
    }

    @GetMapping("/get_country")
    public JSONDatatable get_country(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="country_id", required = false) Long country_id
    ) {
        JSONDatatable result = new JSONDatatable();
        if(mode == 0 || mode == 1) {
            result.setData(country_ListRepository.queryAllContryList());
        }
        else{
            result.setData(country_ListRepository.queryCurrentContryList(country_id));
        }
        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/add_country_shipyard")
    public ModelAndView add_country_shipyard(
            @RequestParam(name = "shipyard_name") String shipyard_name,
            @RequestParam(name = "shipyard_namerus") String shipyard_namerus,
            @RequestParam(name = "shipyard_code") String shipyard_code,
            @RequestParam(name = "country_id") Long country_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddKPIMeetQuery = entityManager
                    .createStoredProcedureQuery("PKG_LISTS_ACTION.pr_addCountryShipyards")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, shipyard_name)
                    .setParameter(4, shipyard_namerus)
                    .setParameter(5, shipyard_code)
                    .setParameter(6, country_id)
                    ;
            AddKPIMeetQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_country_shipyard")
    public ModelAndView edit_country_shipyard(
            @RequestParam(name = "shipyard_id") Long shipyard_id,
            @RequestParam(name = "shipyard_name") String shipyard_name,
            @RequestParam(name = "shipyard_namerus") String shipyard_namerus,
            @RequestParam(name = "shipyard_code") String shipyard_code,
            @RequestParam(name = "country_id") Long country_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditKPIMeetQuery = entityManager
                    .createStoredProcedureQuery("PKG_LISTS_ACTION.pr_editCountryShipyards")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, shipyard_id)
                    .setParameter(4, shipyard_name)
                    .setParameter(5, shipyard_namerus)
                    .setParameter(6, shipyard_code)
                    .setParameter(7, country_id)
                    ;
            EditKPIMeetQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_country_shipyard")
    public ModelAndView del_country_shipyard(
            @RequestParam(name = "shipyard_id") Long shipyard_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelKPIMeetQuery = entityManager
                    .createStoredProcedureQuery("PKG_LISTS_ACTION.pr_delCountryShipyards")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, shipyard_id)
                    ;
            DelKPIMeetQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
}
