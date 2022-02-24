package com.idltd.hydramob.controller;

import com.idltd.hydramob.entity.*;
import com.idltd.hydramob.entity.sale_funnel.vSaleFunnel;
import com.idltd.hydramob.entity.template.*;
import com.idltd.hydramob.repository.*;
import com.idltd.hydramob.repository.client_lost.ViewClientLostUserRepository;
import com.idltd.hydramob.repository.main.*;
import com.idltd.hydramob.repository.ops_request.MenuOpsRequestsRepository;
import com.idltd.hydramob.repository.requests_outtime.MenuRequestsOuttimeListRepository;
import com.idltd.hydramob.repository.sale_funnel.ViewSaleFunnelRepository;
import com.idltd.hydramob.repository.template.MenuContentPropRepository;
import com.idltd.hydramob.repository.template.ViewMainIndexRepository;
import com.idltd.hydramob.repository.tenders_outtime.MenuTendersOuttimeListRepository;
import com.idltd.hydramob.repository.todo_list.ToDoListRepository;
import com.idltd.hydramob.repository.user_result.ViewClientFinResultRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {

    private final LocationRepository locationRepository;
    private final RoleRepository roleRepository;
    private final UsersRepository usersRepository;
    private final WorkplaceRepository workplaceRepository;
    private final Currentworkplace currentworkplace;
    private final User_ListRepository user_ListRepository;
    private final ViewMainIndexRepository viewMainIndexRepository;
    private final ViewSaleResultGraphRepository viewSaleResultGraphRepository;
    private final ViewIndexGraphRepository viewIndexGraphRepository;
    private final ViewProdResultGraphRepository viewProdResultGraphRepository;
    private final ViewClientLostUserRepository viewClientLostUserRepository;
    private final ViewClientFinResultRepository viewClientFinResultRepository;
    private final ViewContragentWorldMapRepository viewContragentWorldMapRepository;
    private final User_Role_ListRepository user_Role_ListRepository;
    private final MenuContentPropRepository menuContentPropRepository;
    private final ViewSaleFunnelRepository viewSaleFunnelRepository;
    private final ToDoListRepository toDoListRepository;
    private final Service_Type_ListRepository service_Type_ListRepository;
    private final ViewRequestBarGraphRepository viewRequestBarGraphRepository;
    private final ViewTenderBarGraphRepository viewTenderBarGraphRepository;
    private final MenuOpsRequestsRepository menuOpsRequestsRepository;
    private final MenuRequestsOuttimeListRepository menuRequestsOuttimeListRepository;
    private final MenuTendersOuttimeListRepository menuTendersOuttimeListRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public IndexController(
            LocationRepository locationRepository,
            RoleRepository roleRepository,
            UsersRepository usersRepository,
            WorkplaceRepository workplaceRepository,
            Currentworkplace currentworkplace,
            User_ListRepository user_ListRepository,
            ViewMainIndexRepository viewMainIndexRepository,
            ViewSaleResultGraphRepository viewSaleResultGraphRepository,
            ViewIndexGraphRepository viewIndexGraphRepository,
            ViewProdResultGraphRepository viewProdResultGraphRepository,
            ViewClientLostUserRepository viewClientLostUserRepository,
            ViewClientFinResultRepository viewClientFinResultRepository,
            ViewContragentWorldMapRepository viewContragentWorldMapRepository,
            User_Role_ListRepository user_Role_ListRepository,
            MenuContentPropRepository menuContentPropRepository,
            ViewSaleFunnelRepository viewSaleFunnelRepository,
            ToDoListRepository toDoListRepository,
            Service_Type_ListRepository service_Type_ListRepository,
            ViewRequestBarGraphRepository viewRequestBarGraphRepository,
            ViewTenderBarGraphRepository viewTenderBarGraphRepository,
            MenuOpsRequestsRepository menuOpsRequestsRepository,
            MenuRequestsOuttimeListRepository menuRequestsOuttimeListRepository,
            MenuTendersOuttimeListRepository menuTendersOuttimeListRepository
    ) {
        this.locationRepository = locationRepository;
        this.roleRepository = roleRepository;
        this.usersRepository = usersRepository;
        this.workplaceRepository = workplaceRepository;
        this.currentworkplace = currentworkplace;
        this.user_ListRepository = user_ListRepository;
        this.viewMainIndexRepository = viewMainIndexRepository;
        this.viewSaleResultGraphRepository = viewSaleResultGraphRepository;
        this.viewIndexGraphRepository = viewIndexGraphRepository;
        this.viewProdResultGraphRepository = viewProdResultGraphRepository;
        this.viewClientLostUserRepository = viewClientLostUserRepository;
        this.viewClientFinResultRepository = viewClientFinResultRepository;
        this.viewContragentWorldMapRepository = viewContragentWorldMapRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.menuContentPropRepository = menuContentPropRepository;
        this.viewSaleFunnelRepository = viewSaleFunnelRepository;
        this.toDoListRepository = toDoListRepository;
        this.service_Type_ListRepository = service_Type_ListRepository;
        this.viewRequestBarGraphRepository = viewRequestBarGraphRepository;
        this.viewTenderBarGraphRepository = viewTenderBarGraphRepository;
        this.menuOpsRequestsRepository = menuOpsRequestsRepository;
        this.menuRequestsOuttimeListRepository = menuRequestsOuttimeListRepository;
        this.menuTendersOuttimeListRepository = menuTendersOuttimeListRepository;
    }

    @GetMapping("/403")
    public ModelAndView error403(ModelAndView model) {
        model.setViewName("/error/page-error-403");
        return model;
    }
    /*public String error403() {
        return "/error/page-error-403";
    }*/

    private String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        return userDetail.getUsername();
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView pageLogin(ModelAndView model) {
        model.setViewName("login");
        return model;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = {"/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView index(ModelAndView model
    ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            if (!currentworkplace.isLoad()) {
                currentworkplace.setWorkplace(workplaceRepository.queryByUsername(userDetail.getUsername()));

                List<User_List> user_List;
                String authname = auth.getName();

                user_List = user_ListRepository.queryUserByName(authname);

                StoredProcedureQuery AddInLogQuery = entityManager
                        .createStoredProcedureQuery("PKG_ADMIN.pf_AddEnterTime")
                        .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                        .setParameter(1, user_List.get(0).id);
                AddInLogQuery.execute();
            }
        }
        model.setViewName("index");
        return model;
    }

    @RequestMapping("/userdetail")
    public ModelAndView userdetail(@RequestParam(name = "id", required = false, defaultValue = "0") long id,
                                   @RequestParam(name = "username", required = false) String username,
                                   @RequestParam(name = "password", required = false, defaultValue = "changeit") String password,
                                   @RequestParam(name = "roleid", required = false, defaultValue = "0") long roleid,
                                   @RequestParam(name = "email", required = false) String email,
                                   @RequestParam(value="telegram_chatid", required=false) Long telegram_chatid,
                                   @RequestParam(name = "loc_id", required = false, defaultValue = "0") long loc_id,
                                   @RequestParam(name = "mode", required = false, defaultValue = "0") int mode,
                                   @RequestParam(name = "parent_username", required = false) String parent_username,
                                   @RequestParam(name = "parent_selected_id", required = false) String parent_selected_id,
                                   @RequestParam(name = "parent_selected_loc_id", required = false) String parent_selected_loc_id,
                                   @RequestParam(name = "parent_selected_wp_id", required = false) String parent_selected_wp_id
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/user/detail");
        mav.addObject("parent_username", parent_username);
        mav.addObject("parent_selected_id", parent_selected_id);
        mav.addObject("parent_selected_loc_id", parent_selected_loc_id);
        mav.addObject("parent_selected_wp_id", parent_selected_wp_id);
        List<Location> locationList = (List<Location>) locationRepository.findAll();
        mav.addObject("locations", locationList);
        List<Role> roleList = (List<Role>) roleRepository.findAll();
        mav.addObject("roles", roleList);
        mav.addObject("email", email);
        mav.addObject("telegram_chatid", telegram_chatid);
        mav.addObject("mode", mode);

        if (mode == 0) {
            mav.addObject("username", username);
            mav.addObject("password", "");
            mav.addObject("roleid", roleid);
            mav.addObject("loc_id", loc_id);
            mav.addObject("token", null);

        } else {
            User user = (User) usersRepository.queryUserByID(id);
            mav.addObject("id", user.getId());
            mav.addObject("username", user.getUsername());
            mav.addObject("password", user.getPassword());
            mav.addObject("roleid", roleid);
            mav.addObject("loc_id", loc_id);
            mav.addObject("token", user.getJwt());
        }
        return mav;
    }

    @RequestMapping("/usertokendetail")
    public ModelAndView usertokendetail(@RequestParam(name = "id", required = false, defaultValue = "0") long id,
                                        @RequestParam(name = "username", required = false) String username,
                                        @RequestParam(name = "password", required = false, defaultValue = "changeit") String password,
                                        @RequestParam(name = "roleid", required = false, defaultValue = "0") long roleid,
                                        @RequestParam(name = "loc_id", required = false, defaultValue = "0") long loc_id,
                                        @RequestParam(name = "mode", required = false, defaultValue = "0") int mode,
                                        @RequestParam(name = "parent_username", required = false) String parent_username,
                                        @RequestParam(name = "parent_selected_id", required = false) String parent_selected_id,
                                        @RequestParam(name = "parent_selected_loc_id", required = false) String parent_selected_loc_id,
                                        @RequestParam(name = "parent_selected_wp_id", required = false) String parent_selected_wp_id
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("usertokendetail");
        mav.addObject("parent_username", parent_username);
        mav.addObject("parent_selected_id", parent_selected_id);
        mav.addObject("parent_selected_loc_id", parent_selected_loc_id);
        mav.addObject("parent_selected_wp_id", parent_selected_wp_id);
        List<Location> locationList = (List<Location>) locationRepository.findAll();
        mav.addObject("locations", locationList);
        List<Role> roleList = (List<Role>) roleRepository.findAll();
        mav.addObject("roles", roleList);
        mav.addObject("mode", mode);
        mav.addObject("id", id);
        mav.addObject("username", username);
        mav.addObject("password", password);
        mav.addObject("roleid", roleid);
        mav.addObject("loc_id", loc_id);

        User curr_user = (User) usersRepository.queryUserByID(id);
        String token = curr_user.getJwt();
        mav.addObject("token", token);
        return mav;
    }

    @RequestMapping("/userlocationdetail")
    public ModelAndView userlocationdetail(@RequestParam(name = "id", required = false, defaultValue = "0") long id,
                                           @RequestParam(name = "username", required = false) String username,
                                           @RequestParam(name = "loc_id", required = false, defaultValue = "0") long loc_id,
                                           @RequestParam(name = "mode", required = false, defaultValue = "0") int mode,
                                           @RequestParam(name = "parent_username", required = false) String parent_username,
                                           @RequestParam(name = "parent_selected_id", required = false) String parent_selected_id,
                                           @RequestParam(name = "parent_selected_loc_id", required = false) String parent_selected_loc_id,
                                           @RequestParam(name = "parent_selected_wp_id", required = false) String parent_selected_wp_id
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("userlocationdetail");
        mav.addObject("parent_username", parent_username);
        mav.addObject("parent_selected_id", parent_selected_id);
        mav.addObject("parent_selected_loc_id", parent_selected_loc_id);
        mav.addObject("parent_selected_wp_id", parent_selected_wp_id);
        List<Location> locationList = new ArrayList<>();
        switch (mode) {
            case 0:
                locationList = locationRepository.queryWithoutUserLocation(id);
                break;
            case 1:
                locationList = (List<Location>) locationRepository.findAll();
                break;
            case 2:
                locationList.add(locationRepository.queryLocByID(loc_id));
                break;
        }

        mav.addObject("mode", mode);
        mav.addObject("user_id", id);
        mav.addObject("username", username);
        mav.addObject("loc_id", loc_id);
        mav.addObject("locations", locationList);
        return mav;
    }

    @RequestMapping("/userworkplacedetail")
    public ModelAndView workplacedetail(@RequestParam(name = "mode", required = false, defaultValue = "0") int mode,
                                        @RequestParam(name = "loc_id") long loc_id,
                                        @RequestParam(name = "wp_id", required = false, defaultValue = "0") long wp_id,
                                        @RequestParam(name = "user_id") long user_id,
                                        @RequestParam(name = "parent_username", required = false) String parent_username,
                                        @RequestParam(name = "parent_selected_id", required = false) String parent_selected_id,
                                        @RequestParam(name = "parent_selected_loc_id", required = false) String parent_selected_loc_id,
                                        @RequestParam(name = "parent_selected_wp_id", required = false) String parent_selected_wp_id
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("userworkplacedetail");
        mav.addObject("parent_username", parent_username);
        mav.addObject("parent_selected_id", parent_selected_id);
        mav.addObject("parent_selected_loc_id", parent_selected_loc_id);
        mav.addObject("parent_selected_wp_id", parent_selected_wp_id);
        mav.addObject("mode", mode);
        List<Location> locationList = new ArrayList<>();
        locationList.add(locationRepository.queryLocByID(loc_id));
        mav.addObject("loc_id", loc_id);
        mav.addObject("locations", locationList);
        List<User> userList = new ArrayList<>();
        userList.add(usersRepository.queryCurrUserByID(user_id));
        mav.addObject("user_id", user_id);
        mav.addObject("users", userList);
        List<Workplace> workplaceList;
        if (mode == 0) {
            workplaceList = workplaceRepository.AllWorkplaceWithoutUserHaveByUser_idandLoc_id(user_id, loc_id);
        } else {
            workplaceList = new ArrayList<>();
            workplaceList.add(workplaceRepository.queryByID(wp_id));
        }
        mav.addObject("wp_id", wp_id);
        mav.addObject("workplaces", workplaceList);
        return mav;
    }

    @RequestMapping("/location")
    public ModelAndView location(@RequestParam(name = "loc_partnership", required = false, defaultValue = "%") String loc_partnership,
                                 @RequestParam(name = "loc_id", required = false) String loc_id,
                                 @RequestParam(name = "wp_id", required = false) String wp_id
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("loc_partnership", loc_partnership);
        mav.addObject("loc_id", loc_id);
        mav.addObject("wp_id", wp_id);
        mav.setViewName("location");
        return mav;
    }

    @RequestMapping("/locationdetail")
    public ModelAndView locationdetail(@RequestParam(name = "mode", required = false, defaultValue = "0") int mode,
                                       @RequestParam(name = "loc_id", required = false, defaultValue = "0") long loc_id,
                                       @RequestParam(name = "loc_location", required = false) String loc_location,
                                       @RequestParam(name = "loc_partnership", required = false) String loc_partnership,
                                       @RequestParam(name = "loc_address", required = false) String loc_address,
                                       @RequestParam(name = "loc_city", required = false) String loc_city,
                                       @RequestParam(name = "loc_zipcode", required = false) String loc_zipcode,
                                       @RequestParam(name = "loc_country_iso2", required = false) String loc_country_iso2,
                                       @RequestParam(name = "loc_phone", required = false) String loc_phone,

                                       @RequestParam(name = "parent_loc_partnership", required = false) String parent_loc_partnership,
                                       @RequestParam(name = "parent_loc_id", required = false) String parent_loc_id
    ) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("locationdetail");
        mav.addObject("mode", mode);
        mav.addObject("loc_id", loc_id);
        mav.addObject("loc_location", loc_location);
        mav.addObject("loc_partnership", loc_partnership);
        mav.addObject("loc_address", loc_address);
        mav.addObject("loc_city", loc_city);
        mav.addObject("loc_zipcode", loc_zipcode);
        mav.addObject("loc_country_iso2", loc_country_iso2);
        mav.addObject("loc_phone", loc_phone);
        mav.addObject("parent_loc_partnership", parent_loc_partnership);
        mav.addObject("parent_loc_id", parent_loc_id);
        return mav;
    }

    @RequestMapping("/workplace")
    public ModelAndView workplace(@RequestParam(name = "loc_id", required = false, defaultValue = "-1") Long loc_id,
                                  @RequestParam(name = "wp_id", required = false, defaultValue = "-1") Long wp_id) {
        ModelAndView mav = new ModelAndView();
        List<Location> locationList = (List<Location>) locationRepository.findAll();
        mav.addObject("loc_id", loc_id);
        mav.addObject("wp_id", wp_id);
        mav.addObject("locations", locationList);
        mav.setViewName("workplace");
        return mav;
    }

    @RequestMapping("/workplacedetail")
    public ModelAndView workplacedetail(@RequestParam(name = "mode", required = false, defaultValue = "0") int mode,
                                        @RequestParam(name = "loc_id") long loc_id,
                                        @RequestParam(name = "wp_id", required = false, defaultValue = "-1") long wp_id,
                                        @RequestParam(name = "wp_name", required = false) String wp_name,
                                        @RequestParam(name = "wp_texttemplate", required = false) String wp_texttemplate,
                                        @RequestParam(name = "return_form", required = false, defaultValue = "workplace") String return_form,
                                        @RequestParam(name = "parent_loc_partnership", required = false) String parent_loc_partnership
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        mav.addObject("loc_id", loc_id);
        mav.addObject("wp_id", wp_id);
        mav.addObject("wp_name", wp_name);
        mav.addObject("wp_texttemplate", wp_texttemplate);
        mav.addObject("return_form", return_form);
        mav.addObject("parent_loc_partnership", parent_loc_partnership);
        mav.setViewName("workplacedetail");
        return mav;
    }

    @GetMapping(value = {"/get_main_index"})
    public  List<MainPageSign> get_main_index(
            @RequestParam(name = "start_date", required = false) String start_date,
            @RequestParam(name = "end_date", required = false) String end_date
    ) {
        List<MainPageSign> result = new ArrayList<>();
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String authname = auth.getName();
            List<User_List> user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result = viewMainIndexRepository.queryMainIndexByID(user_List.get(0).id, user_Role_List.get(0).role_id, start_date, end_date);
        }
        return result;
    }

    @GetMapping(value = {"/get_index_list_graph"})
    public  List<OneLineGraph> get_index_list_graph(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "start_date", required = false) String start_date,
            @RequestParam(name = "end_date", required = false) String end_date
    ) {
        List<OneLineGraph> result = new ArrayList<>();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String authname = auth.getName();
            user_List =  user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result = viewIndexGraphRepository.queryIndexListGraphGraphByID(user_List.get(0).id, user_Role_List.get(0).role_id, id, start_date, end_date);
        }
        return result;
    }

    @GetMapping(value = {"/get_request_bar"})
    public  List<FiveLineBarGraph> get_request_bar(
            @RequestParam(name = "start_date", required = false) String start_date,
            @RequestParam(name = "end_date", required = false) String end_date
    ) {
        List<FiveLineBarGraph> result = new ArrayList<>();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String authname = auth.getName();
            user_List =  user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result = viewRequestBarGraphRepository.queryRequestBarGraphByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, start_date, end_date
            );
        }
        return result;
    }

    @GetMapping(value = {"/get_tender_bar"})
    public  List<FiveLineBarGraph> get_tender_bar(
            @RequestParam(name = "start_date", required = false) String start_date,
            @RequestParam(name = "end_date", required = false) String end_date
    ) {
        List<FiveLineBarGraph> result = new ArrayList<>();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String authname = auth.getName();
            user_List =  user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result = viewTenderBarGraphRepository.queryTenderBarGraphByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, start_date, end_date
            );
        }
        return result;
    }

    @GetMapping(value = {"/get_sale_result_chart"})
    public  List<LineGraph> get_sale_result_chart(
            @RequestParam(name = "year", required = false, defaultValue = "2018") int year
    ) {
        List<LineGraph> result = new ArrayList<>();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result = viewSaleResultGraphRepository.querySaleResultGraphGraphByID(user_List.get(0).id, user_Role_List.get(0).role_id, new Long(year));
        }
        return result;
    }

    @GetMapping(value = {"/get_prod_result_bar"})
    public  List<BarGraph> get_prod_result_bar(
            @RequestParam(name = "year", required = false, defaultValue = "2018") int year
    ) {
        List<BarGraph> result = new ArrayList<>();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result = viewProdResultGraphRepository.queryProdResultGraphByID(user_List.get(0).id, user_Role_List.get(0).role_id, new Long(year));
        }
        return result;
    }

    @PostMapping("/get_lost")
    public JSONDatatable get_lost(
            @RequestParam(name = "year", required = false) int year,
            @RequestParam(name = "month", required = false) int month
    ) {
        JSONDatatable result = new JSONDatatable();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        List<User_List> User_List = user_ListRepository.queryUserByName(authname);
        result.setData( viewClientLostUserRepository.queryClientLostByUserID(User_List.get(0).id, new Long(year), new Long(month)));
        return result;
    }

    @PostMapping("/get_user_result")
    public JSONDatatable get_user_result(
            @RequestParam(name = "year", required = false) int year
    ) {
        JSONDatatable result = new JSONDatatable();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        List<User_List> User_List = user_ListRepository.queryUserByName(authname);
        List<User_Role_List> user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(User_List.get(0).id);

        result.setData( viewClientFinResultRepository.queryUserFinResultByCntID(
                User_List.get(0).id, user_Role_List.get(0).role_id, new Long(year)
        ));
        return result;
    }

    @GetMapping(value = {"/get_cont_world_map"})
    public  List<OneLine> get_cont_world_map(
    ) {
        List<OneLine> result = new ArrayList<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String authname = auth.getName();
            List<User_List> User_List = user_ListRepository.queryUserByName(authname);

            result = viewContragentWorldMapRepository.queryContWorldMapByID(User_List.get(0).id);
        }
        return result;
    }

    @GetMapping(value = {"/get_sale_funnel"})
    public  List<vSaleFunnel> get_sale_funnel(
            @RequestParam(name = "start_date", required = false) String start_date,
            @RequestParam(name = "end_date", required = false) String end_date
    ) {
        List<User_List> user_List;
        List<vSaleFunnel> result = new ArrayList<>();
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String authname = auth.getName();
            user_List =  user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result = viewSaleFunnelRepository.queryUserSaleFunnel(user_List.get(0).id, user_Role_List.get(0).role_id, start_date, end_date);
        }
        return result;
    }

    @GetMapping(value = {"/get_menu_content"})
    public  List<MenuContentProp> get_menu_content(
            @RequestParam(name = "menu_id", required = false, defaultValue = "1") Long menu_id
    ) {
        List<MenuContentProp> result = new ArrayList<>();
        List<User_List> User_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String authname = auth.getName();
        User_List = user_ListRepository.queryUserByName(authname);

        result = menuContentPropRepository.queryMenuContantPropByID(User_List.get(0).id, menu_id);

        return result;
    }

    @PostMapping("/get_todolist")
    public JSONDatatable get_todolist(
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        JSONDatatable result = new JSONDatatable();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(user_Role_List.get(0).role_id == 2) {
            result.setData(toDoListRepository.queryToDoListByUserID(user_List.get(0).id));
        }
        return result;
    }

    @GetMapping("/get_ops_menu")
    public Long get_ops_menu(
    ) {
        Long result;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result = service_Type_ListRepository.queryValSerIDByUserID(user_List.get(0).id, user_Role_List.get(0).role_id, new Long(1));
        return result;
    }

    @PostMapping("/get_ops_requests")
    public JSONDatatable get_ops_requests(
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        JSONDatatable result = new JSONDatatable();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(user_Role_List.get(0).role_id == 3 || user_Role_List.get(0).role_id == 6 || user_Role_List.get(0).role_id == 9) {
            result.setData(menuOpsRequestsRepository.queryOpsRequestByUserID(user_List.get(0).id, user_Role_List.get(0).role_id));
        }
        return result;
    }

    @PostMapping("/get_requests_outtime")
    public JSONDatatable get_requests_outtime(
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        JSONDatatable result = new JSONDatatable();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(user_Role_List.get(0).role_id == 2 || user_Role_List.get(0).role_id == 4 || user_Role_List.get(0).role_id == 5) {
            result.setData(menuRequestsOuttimeListRepository.queryRequestsOuttimeMenuByUserID(user_List.get(0).id, user_Role_List.get(0).role_id));
        }
        return result;
    }

    @PostMapping("/get_tenders_outtime")
    public JSONDatatable get_tenders_outtime(
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        JSONDatatable result = new JSONDatatable();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(user_Role_List.get(0).role_id == 2 || user_Role_List.get(0).role_id == 4 || user_Role_List.get(0).role_id == 5) {
            result.setData(menuTendersOuttimeListRepository.queryTendersMenuOuttimeListByUserID(user_List.get(0).id, user_Role_List.get(0).role_id));
        }
        return result;
    }
}
