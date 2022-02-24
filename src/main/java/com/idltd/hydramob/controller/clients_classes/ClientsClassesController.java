package com.idltd.hydramob.controller.clients_classes;

import com.idltd.hydramob.entity.Contragent_Status_List;
import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.list.ClientClassList;
import com.idltd.hydramob.entity.template.OneLineGraph;
import com.idltd.hydramob.repository.Contragent_Status_ListRepository;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.clients_classes.*;
import com.idltd.hydramob.repository.list.ClientClassListRepository;
import com.idltd.hydramob.utils.JSONDatatable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/clients_classes")
public class ClientsClassesController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private ClientClassListRepository clientClassListRepository;
    private Contragent_Status_ListRepository contragent_Status_ListRepository;

    private MenuClientsClassesListRepository menuClientsClassesListRepository;
    private MenuClientsClassesFullListRepository menuClientsClassesFullListRepository;

    private MenuClientClassesStatListRepository menuClientClassesStatListRepository;
    private GraphClientClassesRepository graphClientClassesRepository;

    private MenuClientClassesFinanceListRepository menuClientClassesFinanceListRepository;
    private MenuClientClassesTendersListRepository menuClientClassesTendersListRepository;
    private MenuClientClassesRequestsListRepository menuClientClassesRequestsListRepository;
    private MenuClientClassesMeetsRepository menuClientClassesMeetsRepository;
    private MenuClientClassesCallsRepository menuClientClassesCallsRepository;
    public ClientsClassesController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,
            ClientClassListRepository clientClassListRepository,
            Contragent_Status_ListRepository contragent_Status_ListRepository,

            MenuClientsClassesListRepository menuClientsClassesListRepository,
            MenuClientsClassesFullListRepository menuClientsClassesFullListRepository,

            MenuClientClassesStatListRepository menuClientClassesStatListRepository,
            GraphClientClassesRepository graphClientClassesRepository,

            MenuClientClassesFinanceListRepository menuClientClassesFinanceListRepository,
            MenuClientClassesTendersListRepository menuClientClassesTendersListRepository,
            MenuClientClassesRequestsListRepository menuClientClassesRequestsListRepository,
            MenuClientClassesMeetsRepository menuClientClassesMeetsRepository,
            MenuClientClassesCallsRepository menuClientClassesCallsRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;
        this.clientClassListRepository = clientClassListRepository;
        this.contragent_Status_ListRepository = contragent_Status_ListRepository;
        
        this.menuClientsClassesListRepository = menuClientsClassesListRepository;
        this.menuClientsClassesFullListRepository = menuClientsClassesFullListRepository;

        this.menuClientClassesStatListRepository = menuClientClassesStatListRepository;
        this.graphClientClassesRepository = graphClientClassesRepository;

        this.menuClientClassesFinanceListRepository = menuClientClassesFinanceListRepository;
        this.menuClientClassesTendersListRepository = menuClientClassesTendersListRepository;
        this.menuClientClassesRequestsListRepository = menuClientClassesRequestsListRepository;
        this.menuClientClassesMeetsRepository = menuClientClassesMeetsRepository;
        this.menuClientClassesCallsRepository = menuClientClassesCallsRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "cnt_id", required = false) Long cnt_id,

                              @RequestParam(value="clients_classes_filter_check", required = false, defaultValue = "1") Long clients_classes_filter_check,
                              @RequestParam(value="clients_classes_filters_user_id", required = false) Long clients_classes_filters_user_id,
                              @RequestParam(value="clients_classes_filters_status_id", required = false) Long clients_classes_filters_status_id,
                              @RequestParam(value="clients_classes_filters_class_id", required = false, defaultValue = "1") Long clients_classes_filters_class_id,

                              @RequestParam(value="clients_classes_filters_start_date", required = false, defaultValue = "01.01.2019") String clients_classes_filters_start_date,
                              @RequestParam(value="clients_classes_filters_end_date", required = false, defaultValue = "31.12.2019") String clients_classes_filters_end_date,

                              @RequestParam(value="clients_classes_list_table_order_column", required = false) Long clients_classes_list_table_order_column,
                              @RequestParam(value="clients_classes_list_table_order_type", required = false) String clients_classes_list_table_order_type,
                              @RequestParam(value="clients_classes_list_table_search", required = false) String clients_classes_list_table_search,
                              @RequestParam(value="clients_classes_list_table_pagelen", required = false) Long clients_classes_list_table_pagelen,
                              @RequestParam(value="clients_classes_list_table_page", required = false) Long clients_classes_list_table_page
    ) {
        List<User_List> user_List;
        List<User_List> filter_User_List;
        List<User_Role_List> user_Role_List;
        List<Contragent_Status_List> contragent_Status_List;
        List<ClientClassList> clientClassList;
        List<ClientClassList> editClientClassList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        user_List = user_ListRepository.queryUserByName(name);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        model.addObject("cnt_id", cnt_id);

        model.addObject("clients_classes_list_table_order_column", clients_classes_list_table_order_column);
        model.addObject("clients_classes_list_table_order_type", clients_classes_list_table_order_type);
        model.addObject("clients_classes_list_table_search", clients_classes_list_table_search);
        model.addObject("clients_classes_list_table_pagelen", clients_classes_list_table_pagelen);
        model.addObject("clients_classes_list_table_page", clients_classes_list_table_page);

        model.addObject("clients_classes_filter_check", clients_classes_filter_check);
        model.addObject("clients_classes_filters_user_id", clients_classes_filters_user_id);
        model.addObject("clients_classes_filters_status_id", clients_classes_filters_status_id);
        model.addObject("clients_classes_filters_class_id", clients_classes_filters_class_id);
        model.addObject("clients_classes_filters_start_date", clients_classes_filters_start_date);
        model.addObject("clients_classes_filters_end_date", clients_classes_filters_end_date);

        filter_User_List = user_ListRepository.queryUserAllOrderDetail();
        model.addObject("filter_user_list", filter_User_List);

        contragent_Status_List = contragent_Status_ListRepository.queryGetAllStatus();
        model.addObject("filter_statuses_list", contragent_Status_List);

        clientClassList = (List<ClientClassList>) clientClassListRepository.findAll();
        model.addObject("filter_classes_list", clientClassList);

        editClientClassList = clientClassListRepository.queryAllClientClassListBy();
        model.addObject("edit_classes_list", editClientClassList);

        model.setViewName("clients_classes/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(value="clients_classes_filter_check") Long clients_classes_filter_check,
            @RequestParam(value="clients_classes_filters_user_id", defaultValue = "0") Long clients_classes_filters_user_id,
            @RequestParam(value="clients_classes_filters_status_id", defaultValue = "-10") Long clients_classes_filters_status_id,
            @RequestParam(value="clients_classes_filters_class_id", defaultValue = "0") Long clients_classes_filters_class_id,
            @RequestParam(value="clients_classes_filters_start_date") String clients_classes_filters_start_date,
            @RequestParam(value="clients_classes_filters_end_date") String clients_classes_filters_end_date
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuClientsClassesFullListRepository.queryClientClassesMenuFilterListByUserID(
                user_List.get(0).id, user_Role_List.get(0).role_id));
        return result;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/list_edit")
    public ModelAndView list_edit(
            @RequestParam(name = "cnt_id") Long cnt_id,
            @RequestParam(name = "edit_class_id") Long edit_class_id

    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery EditClientClassQuery = entityManager
                .createStoredProcedureQuery("PKG_CONTRAGENT.pr_EditContClass")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, cnt_id)
                .setParameter(4, edit_class_id)
                ;
        EditClientClassQuery.execute();
        return null;
    }

    @PostMapping("/list_del")
    public ModelAndView list_del(
            @RequestParam(name = "cnt_id") Long cnt_id

    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery DelClientClassQuery = entityManager
                .createStoredProcedureQuery("PKG_CONTRAGENT.pr_DelContClass")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, cnt_id)
                ;
        DelClientClassQuery.execute();
        return null;
    }

    @PostMapping("/get_statistics_table")
    public JSONDatatable get_statistics_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,
            @RequestParam(name = "start_date", required = false) String start_date,
            @RequestParam(name = "end_date", required = false) String end_date,

            @RequestParam(name = "filter_check", required = false) Long filter_check,
            @RequestParam(name = "filters_user_id", required = false, defaultValue = "0") Long filters_user_id,
            @RequestParam(name = "filters_status_id", required = false, defaultValue = "-10") Long filters_status_id,
            @RequestParam(name = "filters_class_id", required = false, defaultValue = "0") Long filters_class_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if (cnt_id != null && cnt_id > 0 && start_date != null && end_date != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientClassesStatListRepository.queryClientMenuStatisticsListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id, start_date, end_date
            ));
        }
        return result;
    }

    @GetMapping(value = {"/get_classes_graph"})
    public  List<OneLineGraph> get_index_list_graph(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "start_date", required = false) String start_date,
            @RequestParam(name = "end_date", required = false) String end_date,
            @RequestParam(name = "cnt_id", required = false) Long cnt_id,

            @RequestParam(name = "filter_check", required = false) Long filter_check,
            @RequestParam(name = "filters_user_id", required = false, defaultValue = "0") Long filters_user_id,
            @RequestParam(name = "filters_status_id", required = false, defaultValue = "-10") Long filters_status_id,
            @RequestParam(name = "filters_class_id", required = false, defaultValue = "0") Long filters_class_id
    ) {
        List<OneLineGraph> result = new ArrayList<>();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            String authname = auth.getName();
            user_List =  user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result = graphClientClassesRepository.queryIndexListGraphGraphByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, id, cnt_id, start_date, end_date, filter_check, filters_user_id, filters_status_id, filters_class_id
            );
        }
        return result;
    }

    @PostMapping("/get_finance_menu")
    public JSONDatatable get_finance_menu(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        if(cnt_id != null && cnt_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientClassesFinanceListRepository.queryClientClassesFinanceMenuByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
            ));
        }
        return result;
    }

    @PostMapping("/get_tenders_table")
    public JSONDatatable get_list_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        if(cnt_id != null && cnt_id >= 0) {

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientClassesTendersListRepository.queryClientClassesTendersMenuListByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
            ));
        }
        return result;
    }

    @PostMapping("/get_requests_table")
    public JSONDatatable get_requests_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(cnt_id != null && cnt_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();
            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientClassesRequestsListRepository.queryClientClassesRequestsMenuByUserID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
            ));
        }
        return result;
    }

    @PostMapping("/get_meets_table")
    public JSONDatatable get_meets_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;
        if(cnt_id != null && cnt_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientClassesMeetsRepository.queryMenuClientActionsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id

            ));
        }
        return result;
    }

    @PostMapping("/get_calls_table")
    public JSONDatatable get_calls_table(
            @RequestParam(name = "cnt_id", required = false) Long cnt_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;
        if(cnt_id != null && cnt_id >= 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuClientClassesCallsRepository.queryMenuClientActionsByID(
                    user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id
            ));
        }
        return result;
    }

    @PostMapping("/create_temp")
    public ModelAndView create_temp(
            @RequestParam(name = "start_date", required = false) String start_date,
            @RequestParam(name = "end_date", required = false) String end_date,

            @RequestParam(name = "filter_check", required = false) Long filter_check,
            @RequestParam(name = "filters_user_id", required = false, defaultValue = "0") Long filters_user_id,
            @RequestParam(name = "filters_status_id", required = false, defaultValue = "-10") Long filters_status_id,
            @RequestParam(name = "filters_class_id", required = false, defaultValue = "0") Long filters_class_id

    ) throws ParseException {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");

        String StartDate = "";
        String EndDate = "";

        Date Date = null;
        Date Date2 = null;

        if (start_date != "" && start_date != null) {
            String StartDateStr = start_date;
            Date = df.parse(StartDateStr);
            StartDate = newdf.format(Date);
        }

        if (end_date != "" && end_date != null) {
            String EndDateStr = end_date;
            Date2 = df.parse(EndDateStr);
            EndDate = newdf.format(Date2);
        }

        StoredProcedureQuery DelClientClassQuery = entityManager
                .createStoredProcedureQuery("PKG_ANALYTICS_VIEW.pr_FillDashBoardTemps")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, filter_check)
                .setParameter(4, filters_user_id)
                .setParameter(5, filters_status_id)
                .setParameter(6, filters_class_id)
                .setParameter(7, StartDate)
                .setParameter(8, EndDate)
                ;
        DelClientClassQuery.execute();
        return null;
    }
}