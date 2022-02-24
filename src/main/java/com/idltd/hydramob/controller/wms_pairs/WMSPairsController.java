package com.idltd.hydramob.controller.wms_pairs;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.list.ActionPlanTypeList;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.wms_pairs.MenuWMSPairsMainRepository;
import com.idltd.hydramob.repository.wms_pairs.MenuWMSPairsUsersRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

import static com.idltd.hydramob.utils.StaticUtils.ConvertTraceExceptionToText;

@RestController
@RequestMapping("/wms_pairs")
public class WMSPairsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuWMSPairsMainRepository menuWMSPairsMainRepository;

    private MenuWMSPairsUsersRepository menuWMSPairsUsersRepository;

    public WMSPairsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuWMSPairsMainRepository menuWMSPairsMainRepository,

            MenuWMSPairsUsersRepository menuWMSPairsUsersRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuWMSPairsMainRepository = menuWMSPairsMainRepository;

        this.menuWMSPairsUsersRepository = menuWMSPairsUsersRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "act_id", required = false) Long act_id,

                              @RequestParam(name = "table_order_column", required = false) Long table_order_column,
                              @RequestParam(name = "table_order_type", required = false) String table_order_type,
                              @RequestParam(name = "table_search", required = false) String table_search,
                              @RequestParam(name = "table_pagelen", required = false) Long table_pagelen,
                              @RequestParam(name = "table_page", required = false) Long table_page
    ){
        List<User_Report_List> user_Report_List;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Client_Report_List> client_Report_List;
        List<Action_Type_List> action_Type_List;
        List<Action_Status_List> action_Status_List;
        List<Action_Result_List> action_Result_List;
        List<ActionPlanTypeList> actionPlanType;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        model.addObject("act_id", act_id);

        model.addObject("table_order_column", table_order_column);
        model.addObject("table_order_type", table_order_type);
        model.addObject("table_search", table_search);
        model.addObject("table_pagelen", table_pagelen);
        model.addObject("table_page", table_page);

         model.setViewName("wms_pairs/cover");
        return model;
    }

    @PostMapping("/get_menu")
    public JSONDatatable gettable(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuWMSPairsMainRepository.queryWMSPairMainByID(
                user_List.get(0).id,
                user_Role_List.get(0).role_id
        ));

        return result;
    }

    @PostMapping("/add_main_pair")
    public ModelAndView add_main_pair(
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_addWHPairs")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    ;
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/check_main_pair")
    public ModelAndView check_main_doc(
            @RequestParam(name = "pair_id", required = false) Long pair_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_checkWHPairs")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, pair_id)
                    ;
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/uncheck_main_pair")
    public ModelAndView uncheck_main_doc(
            @RequestParam(name = "pair_id", required = false) Long pair_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_unCheckWHPairs")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, pair_id)
                    ;
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/get_detail_menu")
    public JSONDatatable get_detail_menu(
            @RequestParam(name = "pair_id") Long pair_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        if (pair_id != null && pair_id > 0){
            result.setData(menuWMSPairsUsersRepository.queryWMSPairUsersByID(
                    user_List.get(0).id,
                    user_Role_List.get(0).role_id,
                    pair_id
            ));
        }
        return result;
    }

    @PostMapping("/add_user_to_pair")
    public ResponseEntity<?> add_user_to_pair(
            @RequestParam(name = "pair_id", required = false) Long pair_id,
            @RequestParam(name = "user_number", required = false) String user_number
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Integer ErrorID = 0;
        String ErrorValue = new String();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        ResponseEntity<?> error_result;

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_addUserToWHPairs")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.OUT)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, pair_id)
                    .setParameter(4, user_number)
                    ;
            DelActQuery.execute();

            ErrorID = (Integer) DelActQuery.getOutputParameterValue(5);
            ErrorValue = (String) DelActQuery.getOutputParameterValue(6);

            if(ErrorID == 0){
                error_result = new ResponseEntity<>("Сотрудник добавлен", HttpStatus.OK);
            }
            else {
                error_result = new ResponseEntity<>(ErrorValue, HttpStatus.CONFLICT);
            }
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
            error_result = new ResponseEntity<>(ConvertTraceExceptionToText(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return error_result;
    }

    @PostMapping("/check_detail_pair")
    public ModelAndView check_detail_pair(
            @RequestParam(name = "pair_user_link_id", required = false) Long pair_user_link_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_checkWHPairsUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, pair_user_link_id)
                    ;
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/uncheck_detail_pair")
    public ModelAndView uncheck_detail_pair(
            @RequestParam(name = "pair_user_link_id", required = false) Long pair_user_link_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_unCheckWHPairsUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, pair_user_link_id)
                    ;
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/add_detail_pair")
    public ModelAndView add_detail_pair(
            @RequestParam(name = "pair_id", required = false) Long pair_id,
            @RequestParam(name = "user_id", required = false) Long user_id,
            @RequestParam(name = "add_date", required = false) String add_date,
            @RequestParam(name = "add_time", required = false) String add_time,
            @RequestParam(name = "close_date", required = false) String close_date,
            @RequestParam(name = "close_time", required = false) String close_time
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_addWHPairsUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, pair_id)
                    .setParameter(4, user_id)
                    .setParameter(5, add_date)
                    .setParameter(6, add_time)
                    .setParameter(7, close_date)
                    .setParameter(8, close_time)
                    ;
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_detail_pair")
    public ModelAndView edit_detail_pair(
            @RequestParam(name = "pair_user_link_id", required = false) Long pair_user_link_id,
            @RequestParam(name = "user_id", required = false) Long user_id,
            @RequestParam(name = "add_date", required = false) String add_date,
            @RequestParam(name = "add_time", required = false) String add_time,
            @RequestParam(name = "close_date", required = false) String close_date,
            @RequestParam(name = "close_time", required = false) String close_time
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_editWHPairsUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, pair_user_link_id)
                    .setParameter(4, user_id)
                    .setParameter(5, add_date)
                    .setParameter(6, add_time)
                    .setParameter(7, close_date)
                    .setParameter(8, close_time)
                    ;
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_detail_pair")
    public ModelAndView del_detail_pair(
            @RequestParam(name = "pair_user_link_id", required = false) Long pair_user_link_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelActQuery = entityManager
                    .createStoredProcedureQuery("PKG_WMS.pr_delWHPairsUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, pair_user_link_id)
                    ;
            DelActQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

        @GetMapping("/get_pair_users")
    public JSONDatatable get_pair_users(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="pair_id") Long pair_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(mode != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            if(mode == 0){
                result.setData(user_ListRepository.queryNewPairUserByUserID(
                        user_List.get(0).id, user_Role_List.get(0).role_id, pair_id
                ));
            }
            else {
                result.setData(user_ListRepository.queryUserAll());
            }
        }
        return result;
    }
}
