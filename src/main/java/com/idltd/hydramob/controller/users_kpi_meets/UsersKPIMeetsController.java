package com.idltd.hydramob.controller.users_kpi_meets;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.users_kpi_meets.MenuUserKPIMeetUsersListRepository;
import com.idltd.hydramob.repository.users_kpi_meets.MenuUserKPIMeetsListRepository;
import com.idltd.hydramob.repository.users_kpi_meets.UserKPIMeetTypesListRepository;
import com.idltd.hydramob.repository.users_kpi_meets.UserKPIMeetsUsersListRepository;
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
@RequestMapping("/users_kpi_meets")
public class UsersKPIMeetsController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuUserKPIMeetsListRepository menuUserKPIMeetsListRepository;
    private UserKPIMeetTypesListRepository userKPIMeetTypesListRepository;

    private MenuUserKPIMeetUsersListRepository menuUserKPIMeetUsersListRepository;
    private UserKPIMeetsUsersListRepository userKPIMeetsUsersListRepository;

    public UsersKPIMeetsController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuUserKPIMeetsListRepository menuUserKPIMeetsListRepository,
            UserKPIMeetTypesListRepository userKPIMeetTypesListRepository,

            MenuUserKPIMeetUsersListRepository menuUserKPIMeetUsersListRepository,
            UserKPIMeetsUsersListRepository userKPIMeetsUsersListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuUserKPIMeetsListRepository = menuUserKPIMeetsListRepository;
        this.userKPIMeetTypesListRepository = userKPIMeetTypesListRepository;

        this.menuUserKPIMeetUsersListRepository = menuUserKPIMeetUsersListRepository;
        this.userKPIMeetsUsersListRepository = userKPIMeetsUsersListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "user_kpi_meet_id", required = false) Long user_kpi_meet_id
    ){
        model.addObject("user_kpi_meet_id", user_kpi_meet_id);

        model.setViewName("users_kpi_meets/cover");
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

        result.setData(menuUserKPIMeetsListRepository.queryUserKPIMeetUsersListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id));
        return result;
    }

    @GetMapping("/get_meeting_type_list")
    public JSONDatatable get_meeting_type_list(
            @RequestParam(name="mode") Long mode
    ) {
        JSONDatatable result = new JSONDatatable();

        result.setData(userKPIMeetTypesListRepository.findAll());
        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/add_kpi_meet")
    public ModelAndView del_kpi_meet(
            @RequestParam(name = "user_kpi_meet_name") String user_kpi_meet_name,
            @RequestParam(name = "user_kpi_date") String user_kpi_date,
            @RequestParam(name = "user_kpi_meet_type_id") Long user_kpi_meet_type_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddKPIMeetQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddKPIMeeting")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, user_kpi_meet_name)
                    .setParameter(4, user_kpi_date)
                    .setParameter(5, user_kpi_meet_type_id)
                    ;
            AddKPIMeetQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/clone_kpi_meet")
    public ModelAndView clone_kpi_meet(
            @RequestParam(name = "user_kpi_meet_id") Long user_kpi_meet_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery CloneKPIMeetQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_CloneKPIMeeting")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, user_kpi_meet_id)
                    ;
            CloneKPIMeetQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit_kpi_meet")
    public ModelAndView edit_kpi_meet(
            @RequestParam(name = "user_kpi_meet_id") Long user_kpi_meet_id,
            @RequestParam(name = "user_kpi_meet_name") String user_kpi_meet_name,
            @RequestParam(name = "user_kpi_date") String user_kpi_date,
            @RequestParam(name = "user_kpi_meet_type_id") Long user_kpi_meet_type_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery EditKPIMeetQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_EditKPIMeeting")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, user_kpi_meet_id)
                    .setParameter(4, user_kpi_meet_name)
                    .setParameter(5, user_kpi_date)
                    .setParameter(6, user_kpi_meet_type_id)
                    ;
            EditKPIMeetQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_kpi_meet")
    public ModelAndView del_kpi_meet(
            @RequestParam(name = "user_kpi_meet_id") Long user_kpi_meet_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery DelKPIMeetQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelKPIMeeting")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, user_kpi_meet_id)
                    ;
            DelKPIMeetQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/get_users_list")
    public JSONDatatable get_users_list(
            @RequestParam(name="mode") Long mode,
            @RequestParam(name="user_kpi_meet_id") Long user_kpi_meet_id,
            @RequestParam(name="user_id") Long user_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0) {
            result.setData(userKPIMeetsUsersListRepository.queryUserKPIMeetsUsersListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, user_kpi_meet_id));
        }
        else{
            result.setData(userKPIMeetsUsersListRepository.queryUserKPIMeetsUserOneByUserID(user_List.get(0).id, user_Role_List.get(0).role_id,user_id));
        }
        return result;
    }

    @PostMapping("/get_users_table")
    public JSONDatatable get_users_table(
            @RequestParam(name = "user_kpi_meet_id", required = false) Long user_kpi_meet_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if (user_kpi_meet_id != null && user_kpi_meet_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserKPIMeetUsersListRepository.queryUserKPIMeetsListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, user_kpi_meet_id));
        }
        return result;
    }

    @PostMapping("/add_kpi_meet_user")
    public ModelAndView add_kpi_meet_user(
            @RequestParam(name = "user_kpi_meet_id") Long user_kpi_meet_id,
            @RequestParam(name = "user_id") Long user_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery AddKPIMeetUserQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_AddKPIMeetingUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, user_kpi_meet_id)
                    .setParameter(4, user_id)
                    ;
            AddKPIMeetUserQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/check_kpi_meet_user")
    public ModelAndView check_kpi_meet_user(
            @RequestParam(name = "user_kpi_meet_user_id") Long user_kpi_meet_user_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery CheckKPIMeetUserQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_CheckKPIMeetingUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, user_kpi_meet_user_id)
                    ;
            CheckKPIMeetUserQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/uncheck_kpi_meet_user")
    public ModelAndView uncheck_kpi_meet_user(
            @RequestParam(name = "user_kpi_meet_user_id") Long user_kpi_meet_user_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery UnCheckKPIMeetUserQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_UnCheckKPIMeetingUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, user_kpi_meet_user_id)
                    ;
            UnCheckKPIMeetUserQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/del_kpi_meet_user")
    public ModelAndView del_kpi_meet_user(
            @RequestParam(name = "user_kpi_meet_user_id") Long user_kpi_meet_user_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try{
            StoredProcedureQuery UnCheckKPIMeetUserQuery = entityManager
                    .createStoredProcedureQuery("PKG_KPI.pr_DelKPIMeetingUser")
                    .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                    .setParameter(1, user_List.get(0).id)
                    .setParameter(2, user_Role_List.get(0).role_id)
                    .setParameter(3, user_kpi_meet_user_id)
                    ;
            UnCheckKPIMeetUserQuery.execute();
        }
        catch (Exception e) {
            System.out.println("Error:> "+e);
            e.printStackTrace();
        }
        return null;
    }
}
