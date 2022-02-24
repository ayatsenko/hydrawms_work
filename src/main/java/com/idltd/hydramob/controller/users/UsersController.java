package com.idltd.hydramob.controller.users;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.positions.Positions;
import com.idltd.hydramob.entity.roles.Roles;
import com.idltd.hydramob.entity.service_types.ServiceType;
import com.idltd.hydramob.entity.users.DetailUserList;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.positions.ListPositionsRepository;
import com.idltd.hydramob.repository.roles.ListRolesRepository;
import com.idltd.hydramob.repository.service_types.ListServiceTypeRepository;
import com.idltd.hydramob.repository.users.*;
import com.idltd.hydramob.utils.JSONDatatable;
import com.idltd.hydramob.utils.JSONWraper;
import com.idltd.hydramob.utils.security.Jwt_token_util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final User_ListRepository user_ListRepository;
    private final Users_ListRepository users_ListRepository;
    private final User_Role_ListRepository user_Role_ListRepository;
    private final MenuUserListRepository menuUserListRepository;
    private final DetailUserListRepository detailUserListRepository;
    private final RoleRepository roleRepository;
    private final UsersRepository usersRepository;

    private final MenuUserServiceListRepository menuUserServiceListRepository;
    private final MenuUserSubUsersListRepository menuUserSubUsersListRepository;
    private final MenuUserContragentsListRepository menuUserContragentsListRepository;
    private final ListPositionsRepository listPositionsRepository;
    private final ListRolesRepository listRolesRepository;
    private final User_Status_ListRepository user_Status_ListRepository;
    private final ListServiceTypeRepository listServiceTypeRepository;
    private final Users_Roles_ListRepository users_Roles_ListRepository;
    private final Contragent_ListRepository contragent_ListRepository;

    private final MenuUserSystemListRepository menuUserSystemListRepository;

    public UsersController(
            User_ListRepository user_ListRepository,
            Users_ListRepository users_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuUserListRepository menuUserListRepository,
            DetailUserListRepository detailUserListRepository,
            RoleRepository roleRepository,
            UsersRepository usersRepository,

            MenuUserServiceListRepository menuUserServiceListRepository,
            MenuUserSubUsersListRepository menuUserSubUsersListRepository,
            MenuUserContragentsListRepository menuUserContragentsListRepository,
            ListPositionsRepository listPositionsRepository,
            ListRolesRepository listRolesRepository,
            User_Status_ListRepository user_Status_ListRepository,
            ListServiceTypeRepository listServiceTypeRepository,
            Users_Roles_ListRepository users_Roles_ListRepository,
            Contragent_ListRepository contragent_ListRepository,

            MenuUserSystemListRepository menuUserSystemListRepository
     ) {
        this.user_ListRepository = user_ListRepository;
        this.users_ListRepository = users_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuUserListRepository = menuUserListRepository;
        this.detailUserListRepository = detailUserListRepository;
        this.roleRepository = roleRepository;
        this.usersRepository = usersRepository;

        this.menuUserServiceListRepository = menuUserServiceListRepository;
        this.menuUserSubUsersListRepository = menuUserSubUsersListRepository;
        this.menuUserContragentsListRepository = menuUserContragentsListRepository;
        this.listPositionsRepository = listPositionsRepository;
        this.listRolesRepository = listRolesRepository;
        this.user_Status_ListRepository = user_Status_ListRepository;
        this.listServiceTypeRepository = listServiceTypeRepository;
        this.users_Roles_ListRepository = users_Roles_ListRepository;
        this.contragent_ListRepository = contragent_ListRepository;

        this.menuUserSystemListRepository = menuUserSystemListRepository;
    }

    @RequestMapping(value = {"","/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView user_interface(
            @RequestParam(name = "user_id", required = false) Long user_id,
            @RequestParam(name = "usrserl_id", required = false) Long usrserl_id,
            @RequestParam(name = "usrusrl_id", required = false) Long usrusrl_id,
            @RequestParam(name = "cntul_id", required = false) Long cntul_id,

            @RequestParam(name = "users_list_table_order_column", required = false) Long table_order_column,
            @RequestParam(name = "users_list_table_order_type", required = false) String table_order_type,
            @RequestParam(name = "users_list_table_search", required = false) String table_search,
            @RequestParam(name = "users_list_table_pagelen", required = false) Long table_pagelen,
            @RequestParam(name = "users_list_table_page", required = false) Long table_page
    ){
        ModelAndView mav = new ModelAndView();

        mav.addObject("user_id", user_id);
        mav.addObject("usrserl_id", usrserl_id);
        mav.addObject("usrusrl_id", usrusrl_id);
        mav.addObject("cntul_id", cntul_id);

        mav.addObject("users_list_table_order_column", table_order_column);
        mav.addObject("users_list_table_order_type", table_order_type);
        mav.addObject("users_list_table_search", table_search);
        mav.addObject("users_list_table_pagelen", table_pagelen);
        mav.addObject("users_list_table_page", table_page);

        mav.setViewName("/users/cover");
        return mav;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserListRepository.queryUserMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id));
        return result;
    }

    @PostMapping("/get_service_table")
    public JSONDatatable get_service_table(
            @RequestParam(name = "user_id", required = false) Long cur_user_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(cur_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserServiceListRepository.queryUserSerByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cur_user_id));
        }
        return result;
    }

    @PostMapping("/get_subusers_table")
    public JSONDatatable get_subusers_table(
            @RequestParam(name = "user_id", required = false) Long main_user_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(main_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserSubUsersListRepository.queryUserSubUsersByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, main_user_id));
        }
        return result;
    }

    @PostMapping("/get_contragents_table")
    public JSONDatatable get_contragents_table(
            @RequestParam(name = "user_id", required = false) Long cur_user_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(cur_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserContragentsListRepository.queryUserContragentsByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cur_user_id));
        }
        return result;
    }

    @RequestMapping("/list_detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="user_id") Long cur_user_id,

                                   @RequestParam(value="users_list_table_order_column") Long order_column,
                                   @RequestParam(value="users_list_table_order_type") String order_type,
                                   @RequestParam(value="users_list_table_search") String table_search,
                                   @RequestParam(value="users_list_table_pagelen") Long pagelen,
                                   @RequestParam(value="users_list_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<DetailUserList> detailUserList;
        List<DetailUserList> detailDelUserList;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<Positions> positions;
        List<Roles> roles;
        List<User_Status_List> user_Status_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            positions = listPositionsRepository.queryPositionListByID(user_List.get(0).id, user_Role_List.get(0).role_id);
            mav.addObject("pos_list", positions);

            roles = listRolesRepository.queryRoleListByID(user_List.get(0).id, user_Role_List.get(0).role_id);
            mav.addObject("roles_list", roles);

            mav.addObject("user_status_id", 0);
            user_Status_List =  user_Status_ListRepository.queryUserOneStatusListByID(user_List.get(0).id, user_Role_List.get(0).role_id, new Long(0));
            mav.addObject("status_list", user_Status_List);
        }
        else if(mode == 1) {
            detailUserList = detailUserListRepository.queryUserDetailByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cur_user_id);

            mav.addObject("user_id", detailUserList.get(0).user_id);
            mav.addObject("user_name", detailUserList.get(0).user_name);
            mav.addObject("user_surname", detailUserList.get(0).user_surname);

            mav.addObject("pos_id", detailUserList.get(0).pos_id);
            positions = listPositionsRepository.queryPositionListByID(user_List.get(0).id, user_Role_List.get(0).role_id);
            mav.addObject("pos_list", positions);

            mav.addObject("role_id", detailUserList.get(0).role_id);
            roles = listRolesRepository.queryRoleListByID(user_List.get(0).id, user_Role_List.get(0).role_id);
            mav.addObject("roles_list", roles);

            mav.addObject("user_email", detailUserList.get(0).user_email);
            mav.addObject("user_mainphone", detailUserList.get(0).user_mainphone);
            mav.addObject("user_nonmainphone", detailUserList.get(0).user_nonmainphone);

            mav.addObject("user_status_id", detailUserList.get(0).user_status_id);
            user_Status_List = user_Status_ListRepository.queryUserStatusListByID(user_List.get(0).id, user_Role_List.get(0).role_id);
            mav.addObject("status_list", user_Status_List);

            mav.addObject("user_note", detailUserList.get(0).user_note);
            mav.addObject("user_token", detailUserList.get(0).user_token);
            mav.addObject("user_password", detailUserList.get(0).user_password);

            mav.addObject("telegram_chatid", detailUserList.get(0).telegram_chatid);
        }
        else{
            detailDelUserList = detailUserListRepository.queryUserDetailByUserID(user_List.get(0).id,user_Role_List.get(0).role_id, cur_user_id);

            mav.addObject("user_id", detailDelUserList.get(0).user_id);
            mav.addObject("user_name", detailDelUserList.get(0).user_name);
            mav.addObject("user_surname", detailDelUserList.get(0).user_surname);

            mav.addObject("pos_id", detailDelUserList.get(0).pos_id);
            positions = listPositionsRepository.queryOnePositionListByID(user_List.get(0).id,user_Role_List.get(0).role_id, detailDelUserList.get(0).pos_id);
            mav.addObject("pos_list", positions);

            mav.addObject("role_id", detailDelUserList.get(0).role_id);
            roles = listRolesRepository.queryOneRoleListByID(user_List.get(0).id,user_Role_List.get(0).role_id, detailDelUserList.get(0).role_id);
            mav.addObject("roles_list", roles);

            mav.addObject("user_email", detailDelUserList.get(0).user_email);
            mav.addObject("user_mainphone", detailDelUserList.get(0).user_mainphone);
            mav.addObject("user_nonmainphone", detailDelUserList.get(0).user_nonmainphone);

            mav.addObject("user_status_id", detailDelUserList.get(0).user_status_id);
            user_Status_List = user_Status_ListRepository.queryUserOneStatusListByID(user_List.get(0).id,user_Role_List.get(0).role_id, detailDelUserList.get(0).user_status_id);
            mav.addObject("status_list", user_Status_List);

            mav.addObject("user_note", detailDelUserList.get(0).user_note);
            mav.addObject("user_token", detailDelUserList.get(0).user_token);
            mav.addObject("user_password", detailDelUserList.get(0).user_password);

            mav.addObject("telegram_chatid", detailDelUserList.get(0).telegram_chatid);
        }

        mav.addObject("users_list_table_order_column", order_column);
        mav.addObject("users_list_table_order_type", order_type);
        mav.addObject("users_list_table_search", table_search);
        mav.addObject("users_list_table_pagelen", pagelen);
        mav.addObject("users_list_table_page", page);

        mav.setViewName("/users/user_detail");
        return mav;
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/list_model")
    public ModelAndView list_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="user_id", required = false) Long user_id,
            @RequestParam(value="user_name", required = false) String user_name,
            @RequestParam(value="user_surname", required = false) String user_surname,
            @RequestParam(value="pos_id", required = false) Long pos_id,
            @RequestParam(value="role_id", required = false) Long role_id,

            @RequestParam(value="user_email", required = false) String user_email,
            @RequestParam(value="user_mainphone", required = false) String user_mainphone,
            @RequestParam(value="user_nonmainphone", required = false) String user_nonmainphone,
            @RequestParam(value="user_status_id", required = false) Long user_status_id,
            @RequestParam(value="user_note", required = false) String user_note,
            @RequestParam(value="pass", required = false) String pass,

            @RequestParam(value="telegram_chatid", required = false) Long telegram_chatid,

            @RequestParam(value="users_list_table_order_column", required = false) Long order_column,
            @RequestParam(value="users_list_table_order_type", required = false) String order_type,
            @RequestParam(value="users_list_table_search", required = false) String table_search,
            @RequestParam(value="users_list_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="users_list_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        User user;
        HashSet roles;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    String Newpass = "123456";
                    user = new User(user_name,bCryptPasswordEncoder.encode(Newpass));
                    roles = new HashSet();

                    roles.add(roleRepository.queryRoleByID(role_id));
                    user.setRoles(roles);

                    user.setJwt(Jwt_token_util.createJWT(user,"Zammler"));
                    user.setUsername(user_name);
                    user.setUser_surname(user_surname);
                    user.setUser_email(user_email);
                    user.setUser_mainphone(user_mainphone);
                    user.setUser_nonmainphone(user_nonmainphone);
                    user.setPos_id(pos_id);
                    user.setUser_note(user_note);
                    user.setUser_status_id(user_status_id);
                    user.setTelegram_chatid(telegram_chatid);

                    usersRepository.save(user);
                    StoredProcedureQuery AddNewUserShedullerQuery = entityManager
                            .createStoredProcedureQuery("PKG_SHEDULER.pr_ShedulerMailNewUser")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, user_name);
                    AddNewUserShedullerQuery.execute();
                    break;
                case 1:
                    user = usersRepository.queryCurrUserByID(user_id);
                    roles = new HashSet();
                    roles.add(roleRepository.queryRoleByID(role_id));

                    user.setRoles(roles);
                    user.setUser_surname(user_surname);
                    user.setUser_email(user_email);
                    user.setUser_mainphone(user_mainphone);
                    user.setUser_nonmainphone(user_nonmainphone);
                    user.setPos_id(pos_id);
                    user.setUser_note(user_note);
                    user.setUser_status_id(user_status_id);
                    user.setTelegram_chatid(telegram_chatid);

                    usersRepository.save(user);
                    break;
                case 2:
                    user = usersRepository.queryCurrUserByID(user_id);
                    roles = new HashSet();
                    roles.add(roleRepository.queryRoleByID(role_id));

                    user.setRoles(roles);
                    user.setUser_surname(user_surname);
                    user.setUser_email(user_email);
                    user.setUser_mainphone(user_mainphone);
                    user.setUser_nonmainphone(user_nonmainphone);
                    user.setPos_id(pos_id);
                    user.setUser_note(user_note);
                    user.setUser_status_id(new Long(-2));

                    usersRepository.save(user);
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }
        //Параметры на форме
        mav.addObject("mode", mode);

        mav.addObject("user_id", user_id);

        mav.addObject("users_list_table_order_column", order_column);
        mav.addObject("users_list_table_order_type", order_type);
        mav.addObject("users_list_table_search", table_search);
        mav.addObject("users_list_table_pagelen", pagelen);
        mav.addObject("users_list_table_page", page);

        mav.setViewName("/users/user_detail");
        return mav;
    }

    @RequestMapping("/pass_detail")
    public ModelAndView pass_detail(
                                   @RequestParam(value="user_id") Long cur_user_id
    ) {
        ModelAndView mav = new ModelAndView();
        List<DetailUserList> detailUserList;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        detailUserList = detailUserListRepository.queryUserDetailByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cur_user_id);

        mav.addObject("user_id", detailUserList.get(0).user_id);
        mav.addObject("user_name", detailUserList.get(0).user_name);
        mav.addObject("user_surname", detailUserList.get(0).user_surname);

        mav.setViewName("/users/pass_detail");
        return mav;
    }

    @PostMapping("/pass_model")
    public ModelAndView pass_model(
            @RequestParam(value="user_id") Long user_id,
            @RequestParam(value="user_name") String user_name,
            @RequestParam(value="user_surname") String user_surname,
            @RequestParam(value="user_password") String password
    ) {
        ModelAndView mav = new ModelAndView();

        try {
            JSONWraper result = new JSONWraper();
            User u=usersRepository.queryCurrUserByID(user_id);
            u.setPassword(bCryptPasswordEncoder.encode(password));
            u=usersRepository.save(u);
            result.result=true;
            result.data=u.getPassword();

            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        mav.addObject("user_id", user_id);
        mav.addObject("user_name", user_name);
        mav.addObject("user_surname", user_surname);

        mav.setViewName("/users/pass_detail");
        return mav;
    }

    @PostMapping("/pass_standart")
    public ModelAndView pass_standart(
            @RequestParam(value="user_id") Long user_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        User user;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            JSONWraper result = new JSONWraper();
            user = usersRepository.queryCurrUserByID(user_id);
            user.setPassword(bCryptPasswordEncoder.encode("123456"));
            user=usersRepository.save(user);

        StoredProcedureQuery AddNewUserShedullerQuery = entityManager
                .createStoredProcedureQuery("PKG_SHEDULER.pr_ShedulerMailUserChPass")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, user_id);
        AddNewUserShedullerQuery.execute();
     return null;
    }

    @RequestMapping("/service_detail")
    public ModelAndView service_detail(@RequestParam(value="mode") Long mode,
                                       @RequestParam(value="user_id") Long cur_user_id,
                                       @RequestParam(value="usrserl_id") Long usrserl_id,

                                       @RequestParam(value="user_services_table_order_column") Long order_column,
                                       @RequestParam(value="user_services_table_order_type") String order_type,
                                       @RequestParam(value="user_services_table_search") String table_search,
                                       @RequestParam(value="user_services_table_pagelen") Long pagelen,
                                       @RequestParam(value="user_services_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_List> cur_User_List;
        List<User_Role_List> user_Role_List;
        List<ServiceType> serviceType;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        cur_User_List = user_ListRepository.queryUserByID(cur_user_id);

        if(mode == 0){
            mav.addObject("user_id", cur_user_id);
            mav.addObject("user_surname", cur_User_List.get(0).user_surname);

            serviceType = listServiceTypeRepository.queryNewUserServiceListTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id,cur_user_id);
            mav.addObject("ser_list", serviceType);
        }
        else{
            mav.addObject("usrserl_id", usrserl_id);

            mav.addObject("user_id", cur_user_id);
            mav.addObject("user_surname", cur_User_List.get(0).user_surname);

            serviceType = listServiceTypeRepository.queryServiceDetailTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id, usrserl_id);
            mav.addObject("ser_list", serviceType);
        }

        mav.addObject("user_service_detail_table_order_column", order_column);
        mav.addObject("user_service_detail_table_order_type", order_type);
        mav.addObject("user_service_detail_table_search", table_search);
        mav.addObject("user_service_detail_table_pagelen", pagelen);
        mav.addObject("user_service_detail_table_page", page);

        mav.setViewName("/users/service_detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/service_model")
    public ModelAndView service_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="user_id") Long cur_user_id,
            @RequestParam(value="usrserl_id") Long usrserl_id,
            @RequestParam(value="ser_id", defaultValue = "0") Long ser_id,

            @RequestParam(value="pos_name", required = false) String pos_name,
            @RequestParam(value="pos_sname", required = false) String pos_sname,
            @RequestParam(value="pos_description", required = false) String pos_description,

            @RequestParam(value="user_service_detail_table_order_column", required = false) Long order_column,
            @RequestParam(value="user_service_detail_table_order_type", required = false) String order_type,
            @RequestParam(value="user_service_detail_table_search", required = false) String table_search,
            @RequestParam(value="user_service_detail_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="user_service_detail_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_List> cur_User_List;
        List<User_Role_List> user_Role_List;
        List<ServiceType> serviceType;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        cur_User_List = user_ListRepository.queryUserByID(cur_user_id);

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddQuery = entityManager
                            .createStoredProcedureQuery("PKG_USERS.pr_AddOpsService")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .setParameter(1, cur_user_id)
                            .setParameter(2, ser_id);
                    AddQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelQuery = entityManager
                            .createStoredProcedureQuery("PKG_USERS.pr_DelOpsService")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .setParameter(1, usrserl_id);
                    DelQuery.execute();
                    usrserl_id = null;
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        //Параметры на форме
        mav.addObject("mode", mode);
        mav.addObject("user_id", cur_user_id);

        if(mode == 0){
            mav.addObject("user_surname", cur_User_List.get(0).user_surname);

            mav.addObject("ser_id", ser_id);
            serviceType = listServiceTypeRepository.queryNewUserServiceListTypeByID(user_List.get(0).id,user_Role_List.get(0).role_id,cur_user_id);
            mav.addObject("ser_list", serviceType);
        }
        else{
            mav.addObject("usrserl_id", usrserl_id);

            mav.addObject("user_surname", cur_User_List.get(0).user_surname);

            if(usrserl_id != null) {
                mav.addObject("ser_id", ser_id);
                serviceType = listServiceTypeRepository.queryServiceDetailTypeByID(user_List.get(0).id, user_Role_List.get(0).role_id, usrserl_id);
                mav.addObject("ser_list", serviceType);
            }
        }

        mav.addObject("user_service_detail_table_order_column", order_column);
        mav.addObject("user_service_detail_table_order_type", order_type);
        mav.addObject("user_service_detail_table_search", table_search);
        mav.addObject("user_service_detail_table_pagelen", pagelen);
        mav.addObject("user_service_detail_table_page", page);

        mav.setViewName("/users/service_detail");
        return mav;
    }

    @RequestMapping("/subuser_detail")
    public ModelAndView subuser_detail(@RequestParam(value="mode") Long mode,
                                       @RequestParam(value="user_id") Long cur_user_id,
                                       @RequestParam(value="cur_role_id") Long cur_role_id,
                                       @RequestParam(value="usrusrl_id", required = false) Long usrusrl_id,
                                       @RequestParam(value="sub_user_id", required = false) Long sub_user_id,

                                       @RequestParam(value="user_subusers_table_order_column") Long order_column,
                                       @RequestParam(value="user_subusers_table_order_type") String order_type,
                                       @RequestParam(value="user_subusers_table_search") String table_search,
                                       @RequestParam(value="user_subusers_table_pagelen") Long pagelen,
                                       @RequestParam(value="user_subusers_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_List> cur_User_List;
        List<Users_List> sub_User_List;
        List<User_Role_List> user_Role_List;
        List<Users_Roles_List> users_Roles_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        cur_User_List = user_ListRepository.queryUserByID(cur_user_id);

        mav.addObject("cur_role_id", cur_role_id);

        if(mode == 0){
            mav.addObject("user_id", cur_user_id);
            mav.addObject("user_surname", cur_User_List.get(0).user_surname);

            users_Roles_List = users_Roles_ListRepository.queryGetSubUsersListByID(user_List.get(0).id,user_Role_List.get(0).role_id,cur_user_id,cur_role_id);
            mav.addObject("subuser_list", users_Roles_List);
        }
        else{
            mav.addObject("usrusrl_id", usrusrl_id);

            mav.addObject("user_id", cur_user_id);
            mav.addObject("user_surname", cur_User_List.get(0).user_surname);

            sub_User_List = users_ListRepository.queryUserByID(sub_user_id);
            mav.addObject("subuser_list", sub_User_List);
        }

        mav.addObject("user_subuser_detail_table_order_column", order_column);
        mav.addObject("user_subuser_detail_table_order_type", order_type);
        mav.addObject("user_subuser_detail_table_search", table_search);
        mav.addObject("user_subuser_detail_table_pagelen", pagelen);
        mav.addObject("user_subuser_detail_table_page", page);

        mav.setViewName("/users/subuser_detail");
        return mav;
    }

    @PostMapping("/subuser_model")
    public ModelAndView subuser_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="user_id") Long cur_user_id,
            @RequestParam(value="cur_role_id") Long cur_role_id,
            @RequestParam(value="usrusrl_id", required = false) Long usrusrl_id,
            @RequestParam(value="sub_user_id") Long sub_user_id,

            @RequestParam(value="user_service_detail_table_order_column", required = false) Long order_column,
            @RequestParam(value="user_service_detail_table_order_type", required = false) String order_type,
            @RequestParam(value="user_service_detail_table_search", required = false) String table_search,
            @RequestParam(value="user_service_detail_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="user_service_detail_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_List> cur_User_List;
        List<User_Role_List> user_Role_List;
        List<Users_List> sub_User_List;
        List<Users_Roles_List> users_Roles_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        cur_User_List = user_ListRepository.queryUserByID(cur_user_id);

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddQuery = entityManager
                            .createStoredProcedureQuery("PKG_USERS.pr_AddUserSubUser")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, cur_user_id)
                            .setParameter(2, cur_role_id)
                            .setParameter(3, sub_user_id);
                    AddQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelQuery = entityManager
                            .createStoredProcedureQuery("PKG_USERS.pr_DelUserSubUser")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .setParameter(1, usrusrl_id);
                    DelQuery.execute();
                    usrusrl_id = null;
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        //Параметры на форме
        mav.addObject("mode", mode);

        mav.addObject("user_id", cur_user_id);
        mav.addObject("cur_role_id", cur_role_id);

        if(mode == 0){
            mav.addObject("user_surname", cur_User_List.get(0).user_surname);

            users_Roles_List = users_Roles_ListRepository.queryGetSubUsersListByID(user_List.get(0).id,user_Role_List.get(0).role_id,cur_user_id,cur_role_id);
            mav.addObject("subuser_list", users_Roles_List);
        }
        else{
            mav.addObject("usrusrl_id", usrusrl_id);

            mav.addObject("user_surname", cur_User_List.get(0).user_surname);

            sub_User_List = users_ListRepository.queryUserByID(sub_user_id);
            mav.addObject("subuser_list", sub_User_List);
        }

        mav.addObject("user_subuser_detail_table_order_column", order_column);
        mav.addObject("user_subuser_detail_table_order_type", order_type);
        mav.addObject("user_subuser_detail_table_search", table_search);
        mav.addObject("user_subuser_detail_table_pagelen", pagelen);
        mav.addObject("user_subuser_detail_table_page", page);

        mav.setViewName("/users/subuser_detail");
        return mav;
    }

    @RequestMapping("/contragent_detail")
    public ModelAndView contragent_detail(@RequestParam(value="mode") Long mode,
                                       @RequestParam(value="user_id") Long cur_user_id,
                                       @RequestParam(value="cntul_id", required = false) Long cntul_id,
                                       @RequestParam(value="cnt_id", required = false) Long cnt_id,
                                       @RequestParam(value="cntul_main_id", required = false) Long cntul_main_id,

                                       @RequestParam(value="user_contragents_table_order_column") Long order_column,
                                       @RequestParam(value="user_contragents_table_order_type") String order_type,
                                       @RequestParam(value="user_contragents_table_search") String table_search,
                                       @RequestParam(value="user_contragents_table_pagelen") Long pagelen,
                                       @RequestParam(value="user_contragents_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<User_List> cur_User_List;
        List<Contragent_List> contragent_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        cur_User_List = user_ListRepository.queryUserByID(cur_user_id);

        mav.addObject("user_id", cur_user_id);
        mav.addObject("user_surname", cur_User_List.get(0).user_surname);

        if(mode == 0){
            contragent_List = contragent_ListRepository.queryUserListNewCntByUserID(user_List.get(0).id,user_Role_List.get(0).role_id,cur_user_id);
            mav.addObject("contragent_list", contragent_List);
        }
        else{
            mav.addObject("cntul_id", cntul_id);

            mav.addObject("cnt_id", cnt_id);
            contragent_List = contragent_ListRepository.queryUserCntByUserID(user_List.get(0).id,user_Role_List.get(0).role_id,cnt_id);
            mav.addObject("contragent_list", contragent_List);

            mav.addObject("cntul_main_id", cntul_main_id);
        }

        mav.addObject("user_contragents_detail_table_order_column", order_column);
        mav.addObject("user_contragents_detail_table_order_type", order_type);
        mav.addObject("user_contragents_detail_table_search", table_search);
        mav.addObject("user_contragents_detail_table_pagelen", pagelen);
        mav.addObject("user_contragents_detail_table_page", page);

        mav.setViewName("/users/contragent_detail");
        return mav;
    }

    @PostMapping("/contragent_model")
    public ModelAndView contragent_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="user_id") Long cur_user_id,
            @RequestParam(value="cntul_id", required = false) Long cntul_id,
            @RequestParam(value="cnt_id", required = false) Long cnt_id,
            @RequestParam(value="cntul_main_id", required = false) Boolean cntul_main_id,

            @RequestParam(value="user_contragents_detail_table_order_column", required = false) Long order_column,
            @RequestParam(value="user_contragents_detail_table_order_type", required = false) String order_type,
            @RequestParam(value="user_contragents_detail_table_search", required = false) String table_search,
            @RequestParam(value="user_contragents_detail_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="user_contragents_detail_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_List> cur_User_List;
        List<User_Role_List> user_Role_List;
        List<Contragent_List> contragent_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);
        cur_User_List = user_ListRepository.queryUserByID(cur_user_id);

        Long CntulMain;
        if(cntul_main_id == null){ CntulMain = new Long("0");}
        else { CntulMain = new Long("1");}

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddUserClintQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_AddContragentUserLink")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cnt_id)
                            .setParameter(3, cur_user_id)
                            .setParameter(4, CntulMain);
                    AddUserClintQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditUserClintQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_EditContragentUserLink")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cntul_id)
                            .setParameter(3, CntulMain);
                    EditUserClintQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelUserClintQuery = entityManager
                            .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContragentUserLink")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, cntul_id);
                    DelUserClintQuery.execute();
                    cntul_id = null;
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        //Параметры на форме
        mav.addObject("mode", mode);

        mav.addObject("user_id", cur_user_id);
        mav.addObject("user_surname", cur_User_List.get(0).user_surname);

        if(mode == 0){
            mav.addObject("cnt_id", cnt_id);
            contragent_List = contragent_ListRepository.queryUserListNewCntByUserID(user_List.get(0).id,user_Role_List.get(0).role_id,cur_user_id);
            mav.addObject("contragent_list", contragent_List);

            mav.addObject("cntul_main_id", cntul_main_id);
        }
        else{
            mav.addObject("cntul_id", cntul_id);

            mav.addObject("cnt_id", cnt_id);
            contragent_List = contragent_ListRepository.queryUserCntByUserID(user_List.get(0).id,user_Role_List.get(0).role_id,cnt_id);
            mav.addObject("contragent_list", contragent_List);

            mav.addObject("cntul_main_id", cntul_main_id);
        }

        mav.addObject("user_contragents_detail_table_order_column", order_column);
        mav.addObject("user_contragents_detail_table_order_type", order_type);
        mav.addObject("user_contragents_detail_table_search", table_search);
        mav.addObject("user_contragents_detail_table_pagelen", pagelen);
        mav.addObject("user_contragents_detail_table_page", page);

        mav.setViewName("/users/contragent_detail");
        return mav;
    }

    @PostMapping("/get_system_table")
    public JSONDatatable get_system_table(
            @RequestParam(name = "user_id", required = false) Long cur_user_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        if(cur_user_id != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuUserSystemListRepository.queryUserSystemMenuListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, cur_user_id));
        }
        return result;
    }
}
