package com.idltd.hydramob.controller.support_tasks;

import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.list.SupportTaskPriorList;
import com.idltd.hydramob.entity.list.SupportTaskStatusList;
import com.idltd.hydramob.entity.list.SupportTaskTypeList;
import com.idltd.hydramob.entity.support_tasks.DetailSupportTaskList;
import com.idltd.hydramob.entity.support_tasks.MenuSupportTaskResultGraph;
import com.idltd.hydramob.entity.support_tasks.MenuSupportTaskTimeGraph;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.list.SupportTaskPriorListRepository;
import com.idltd.hydramob.repository.list.SupportTaskStatusListRepository;
import com.idltd.hydramob.repository.list.SupportTaskTypeListRepository;
import com.idltd.hydramob.repository.support_tasks.*;
import com.idltd.hydramob.utils.JSONDatatable;
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
@RequestMapping("/support_tasks")
public class SupportTasksController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;
    private MenuSupportTaskListRepository menuSupportTaskListRepository;
    private DetailSupportTaskListRepository detailSupportTaskListRepository;
    private SupportTaskPriorListRepository supportTaskPriorListRepository;
    private SupportTaskTypeListRepository supportTaskTypeListRepository;
    private SupportTaskStatusListRepository supportTaskStatusListRepository;

    private MenuSupportTaskResultRepository menuSupportTaskResultRepository;
    private MenuSupportTaskTimeRepository menuSupportTaskTimeRepository;
    private MenuSupportTaskResultGraphRepository menuSupportTaskResultGraphRepository;
    private MenuSupportTaskTimeGraphRepository menuSupportTaskTimeGraphRepository;

    private MenuSupportTaskResultTimeRepository menuSupportTaskResultTimeRepository;
    public SupportTasksController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuSupportTaskListRepository menuSupportTaskListRepository,
            DetailSupportTaskListRepository detailSupportTaskListRepository,
            SupportTaskPriorListRepository supportTaskPriorListRepository,
            SupportTaskTypeListRepository supportTaskTypeListRepository,
            SupportTaskStatusListRepository supportTaskStatusListRepository,

            MenuSupportTaskResultRepository menuSupportTaskResultRepository,
            MenuSupportTaskTimeRepository menuSupportTaskTimeRepository,
            MenuSupportTaskResultGraphRepository menuSupportTaskResultGraphRepository,
            MenuSupportTaskTimeGraphRepository menuSupportTaskTimeGraphRepository,

            MenuSupportTaskResultTimeRepository menuSupportTaskResultTimeRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuSupportTaskListRepository = menuSupportTaskListRepository;
        this.detailSupportTaskListRepository = detailSupportTaskListRepository;
        this.supportTaskPriorListRepository = supportTaskPriorListRepository;
        this.supportTaskTypeListRepository = supportTaskTypeListRepository;
        this.supportTaskStatusListRepository = supportTaskStatusListRepository;

        this.menuSupportTaskResultRepository = menuSupportTaskResultRepository;
        this.menuSupportTaskTimeRepository = menuSupportTaskTimeRepository;
        this.menuSupportTaskResultGraphRepository = menuSupportTaskResultGraphRepository;
        this.menuSupportTaskTimeGraphRepository = menuSupportTaskTimeGraphRepository;

        this.menuSupportTaskResultTimeRepository = menuSupportTaskResultTimeRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "spt_id", required = false) Long spt_id,

                              @RequestParam(name = "support_tasks_tab_name", required = false) String tab_name,
                              @RequestParam(name = "show_all", required = false, defaultValue = "1") Long show_all,

                              @RequestParam(name = "support_tasks_list_table_order_column", required = false) Long support_tasks_list_table_order_column,
                              @RequestParam(name = "support_tasks_list_table_order_type", required = false) String support_tasks_list_table_order_type,
                              @RequestParam(name = "support_tasks_list_table_search", required = false) String support_tasks_list_table_search,
                              @RequestParam(name = "support_tasks_list_table_pagelen", required = false) Long support_tasks_list_table_pagelen,
                              @RequestParam(name = "support_tasks_list_table_page", required = false) Long support_tasks_list_table_page
    ){
        model.addObject("spt_id", spt_id);
        model.addObject("show_all", show_all);

        model.addObject("support_tasks_list_table_order_column", support_tasks_list_table_order_column);
        model.addObject("support_tasks_list_table_order_type", support_tasks_list_table_order_type);
        model.addObject("support_tasks_list_table_search", support_tasks_list_table_search);
        model.addObject("support_tasks_list_table_pagelen", support_tasks_list_table_pagelen);
        model.addObject("support_tasks_list_table_page", support_tasks_list_table_page);

        model.addObject("support_tasks_tab_name", tab_name);

        model.setViewName("support_tasks/cover");
        return model;
    }

    @PostMapping("/get_list_table")
    public JSONDatatable get_list_table(
            @RequestParam(value="show_all") Long show_all
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuSupportTaskListRepository.queryGetSupportTaskList(user_List.get(0).id,user_Role_List.get(0).role_id, show_all));

        return result;
    }

    @PostMapping("/list_upper")
    public ModelAndView main_check(
            @RequestParam(name = "spt_id") long spt_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery MainCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_SUPPORT.pr_UpperSupportFull")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, spt_id);
        MainCheckQuery.execute();
        return null;
    }

    @PostMapping("/list_lower")
    public ModelAndView main_uncheck(
            @RequestParam(name = "spt_id") long spt_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery MainUnCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_SUPPORT.pr_LowSupportFull")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, spt_id);
        MainUnCheckQuery.execute();
        return null;
    }

    @PostMapping("/list_check")
    public ModelAndView list_check(
            @RequestParam(name = "spt_id") long spt_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery MainCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_SUPPORT.pr_UpSupportStatus")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, spt_id);
        MainCheckQuery.execute();
        return null;
    }

    @PostMapping("/list_uncheck")
    public ModelAndView list_uncheck(
            @RequestParam(name = "spt_id") long spt_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery MainUnCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_SUPPORT.pr_LowSupportStatus")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, spt_id);
        MainUnCheckQuery.execute();
        return null;
    }

    @RequestMapping("/list_detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="spt_id") Long spt_id,
                                   @RequestParam(value="show_all") Long show_all,

                                   @RequestParam(value="support_tasks_list_table_order_column") Long order_column,
                                   @RequestParam(value="support_tasks_list_table_order_type") String order_type,
                                   @RequestParam(value="support_tasks_list_table_search") String table_search,
                                   @RequestParam(value="support_tasks_list_table_pagelen") Long pagelen,
                                   @RequestParam(value="support_tasks_list_table_page") Long page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<DetailSupportTaskList> detailSupportTaskList;
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<SupportTaskPriorList> supportTaskPriorList;
        List<SupportTaskTypeList> supportTaskTypeList;
        List<SupportTaskStatusList> supportTaskStatusList;
        List<User_List> authorList;
        List<User_List> workerList;
        List<User_List> controlerList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("support_id", user_List.get(0).support_id);

        if(mode == 0){
            mav.addObject("sptp_id", 2);
            supportTaskPriorList = supportTaskPriorListRepository.querySupportTaskPriorListAll();
            mav.addObject("sptp_list", supportTaskPriorList);

            mav.addObject("sptt_id", 1);
            supportTaskTypeList = supportTaskTypeListRepository.querySupportTaskTypeListAll();
            mav.addObject("sptt_list", supportTaskTypeList);

            mav.addObject("sptt_plan_time", "0");
            mav.addObject("sptt_fact_time", "0");

            if(user_List.get(0).support_id == 1){
                mav.addObject("spts_id", 2);
                supportTaskStatusList = supportTaskStatusListRepository.querySupportTaskStatusListAll();
                mav.addObject("spts_list", supportTaskStatusList);

                mav.addObject("spt_author_id", 503);
                authorList = (List<User_List>) user_ListRepository.findAll();

                mav.addObject("spt_work_id", 801);
                workerList = (List<User_List>) user_ListRepository.findAll();
                mav.addObject("spt_work_list", workerList);

                controlerList = (List<User_List>) user_ListRepository.findAll();
                mav.addObject("spt_cont_list", controlerList);
            }else{
                mav.addObject("spts_id", 2);
                supportTaskStatusList = supportTaskStatusListRepository.querySupportTaskTypeStatusByID(new Long(2));
                mav.addObject("spts_list", supportTaskStatusList);

                mav.addObject("spt_author_id", user_List.get(0).id);
                authorList = (List<User_List>) user_ListRepository.findAll();

                workerList = (List<User_List>) user_ListRepository.findAll();
                mav.addObject("spt_work_list", workerList);

                controlerList = (List<User_List>) user_ListRepository.findAll();
                mav.addObject("spt_cont_list", controlerList);
            }
            mav.addObject("spt_author_list", authorList);
        }
        else{
            detailSupportTaskList = detailSupportTaskListRepository.queryGetSupportTaskListDetail(user_List.get(0).id,user_Role_List.get(0).role_id, spt_id);

            mav.addObject("spt_id", detailSupportTaskList.get(0).spt_id);

            mav.addObject("spt_name", detailSupportTaskList.get(0).spt_name);
            mav.addObject("spt_description", detailSupportTaskList.get(0).spt_description);
            
            mav.addObject("sptp_id", detailSupportTaskList.get(0).sptp_id);
            supportTaskPriorList = supportTaskPriorListRepository.querySupportTaskPriorListAll();
            mav.addObject("sptp_list", supportTaskPriorList);

            mav.addObject("sptt_id", detailSupportTaskList.get(0).sptt_id);
            supportTaskTypeList = supportTaskTypeListRepository.querySupportTaskTypeListAll();
            mav.addObject("sptt_list", supportTaskTypeList);

            mav.addObject("sptt_number", detailSupportTaskList.get(0).sptt_number);
            mav.addObject("sptt_plan_time", detailSupportTaskList.get(0).sptt_plan_time);
            mav.addObject("sptt_fact_time", detailSupportTaskList.get(0).sptt_fact_time);

            mav.addObject("spts_id", detailSupportTaskList.get(0).spts_id);
            supportTaskStatusList = supportTaskStatusListRepository.querySupportTaskStatusListAll();
            mav.addObject("spts_list", supportTaskStatusList);

            mav.addObject("spt_author_id", detailSupportTaskList.get(0).spt_author_id);
            authorList = (List<User_List>) user_ListRepository.findAll();
            mav.addObject("spt_author_list", authorList);

            mav.addObject("spt_work_id", detailSupportTaskList.get(0).spt_work_id);
            workerList = (List<User_List>) user_ListRepository.findAll();
            mav.addObject("spt_work_list", workerList);

            mav.addObject("spt_cont_id", detailSupportTaskList.get(0).spt_cont_id);
            controlerList = (List<User_List>) user_ListRepository.findAll();
            mav.addObject("spt_cont_list", controlerList);

            mav.addObject("spt_create_date", detailSupportTaskList.get(0).spt_create_date);
            mav.addObject("spt_plan_date", detailSupportTaskList.get(0).spt_plan_date);
            mav.addObject("spt_test_date", detailSupportTaskList.get(0).spt_test_date);
            mav.addObject("spt_end_date", detailSupportTaskList.get(0).spt_end_date);
        }

        mav.addObject("support_tasks_tab_name", "tab-2");
        mav.addObject("show_all", show_all);

        mav.addObject("support_tasks_list_table_order_column", order_column);
        mav.addObject("support_tasks_list_table_order_type", order_type);
        mav.addObject("support_tasks_list_table_search", table_search);
        mav.addObject("support_tasks_list_table_pagelen", pagelen);
        mav.addObject("support_tasks_list_table_page", page);

        mav.setViewName("/support_tasks/list_detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/list_model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,

            @RequestParam(value="spt_id") Long spt_id,
            @RequestParam(value="spt_name", required = false) String spt_name,
            @RequestParam(value="spt_description", required = false) String spt_description,

            @RequestParam(value="sptp_id") Long sptp_id,
            @RequestParam(value="sptt_id") Long sptt_id,
            @RequestParam(value="sptt_number", required = false) String sptt_number,
            @RequestParam(value="sptt_plan_time", required = false) Long sptt_plan_time,
            @RequestParam(value="sptt_fact_time", required = false) Long sptt_fact_time,

            @RequestParam(value="spts_id", required = false) Long spts_id,
            @RequestParam(value="spt_author_id", required = false, defaultValue = "0") Long spt_author_id,
            @RequestParam(value="spt_cont_id", required = false, defaultValue = "0") Long spt_cont_id,
            @RequestParam(value="spt_work_id", required = false, defaultValue = "0") Long spt_work_id,

            @RequestParam(value="spt_create_date", required = false) String spt_create_date,
            @RequestParam(value="spt_plan_date", required = false) String spt_plan_date,
            @RequestParam(value="spt_test_date", required = false) String spt_test_date,
            @RequestParam(value="spt_end_date", required = false) String spt_end_date,

            @RequestParam(value="show_all", required = false) Long show_all,

            @RequestParam(value="support_tasks_list_table_order_column", required = false) Long order_column,
            @RequestParam(value="support_tasks_list_table_order_type", required = false) String order_type,
            @RequestParam(value="support_tasks_list_table_search", required = false) String table_search,
            @RequestParam(value="support_tasks_list_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="support_tasks_list_table_page", required = false) Long page
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<SupportTaskPriorList> supportTaskPriorList;
        List<SupportTaskTypeList> supportTaskTypeList;
        List<SupportTaskStatusList> supportTaskStatusList;
        List<User_List> authorList;
        List<User_List> workerList;
        List<User_List> controlerList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    if(user_List.get(0).support_id == 1) {
                        StoredProcedureQuery AddSuppTaskQuery = entityManager
                                .createStoredProcedureQuery("PKG_SUPPORT.pr_AddSupportFull")
                                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                                .setParameter(1, user_List.get(0).id)
                                .setParameter(2, user_Role_List.get(0).role_id)
                                .setParameter(3, spt_name)
                                .setParameter(4, spt_description)
                                .setParameter(5, sptp_id)
                                .setParameter(6, sptt_id)
                                .setParameter(7, sptt_number)
                                .setParameter(8, sptt_plan_time)
                                .setParameter(9, sptt_fact_time)
                                .setParameter(10, spts_id)
                                .setParameter(11, spt_author_id)
                                .setParameter(12, spt_cont_id)
                                .setParameter(13, spt_work_id)
                                .setParameter(14, spt_create_date)
                                .setParameter(15, spt_plan_date)
                                .setParameter(16, spt_test_date)
                                .setParameter(17, spt_end_date)
                                ;
                        AddSuppTaskQuery.execute();
                    }else{
                        StoredProcedureQuery AddSuppShortTaskQuery = entityManager
                                .createStoredProcedureQuery("PKG_SUPPORT.pr_AddSupportShort")
                                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                                .setParameter(1, user_List.get(0).id)
                                .setParameter(2, user_Role_List.get(0).role_id)
                                .setParameter(3, spt_name)
                                .setParameter(4, spt_description)
                                .setParameter(5, sptp_id)
                                .setParameter(6, sptt_id)
                                .setParameter(7, spts_id)
                                .setParameter(8, spt_author_id)
                                .setParameter(9, spt_create_date)
                                ;
                        AddSuppShortTaskQuery.execute();
                    }
                    break;
                case 1:
                    if(user_List.get(0).support_id == 1) {
                        StoredProcedureQuery EditSuppTaskQuery = entityManager
                                .createStoredProcedureQuery("PKG_SUPPORT.pr_EditSupportFull")
                                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(13, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(14, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(16, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(17, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(18, String.class, ParameterMode.IN)
                                .setParameter(1, user_List.get(0).id)
                                .setParameter(2, user_Role_List.get(0).role_id)
                                .setParameter(3, spt_id)
                                .setParameter(4, spt_name)
                                .setParameter(5, spt_description)
                                .setParameter(6, sptp_id)
                                .setParameter(7, sptt_id)
                                .setParameter(8, sptt_number)
                                .setParameter(9, sptt_plan_time)
                                .setParameter(10, sptt_fact_time)
                                .setParameter(11, spts_id)
                                .setParameter(12, spt_author_id)
                                .setParameter(13, spt_cont_id)
                                .setParameter(14, spt_work_id)
                                .setParameter(15, spt_create_date)
                                .setParameter(16, spt_plan_date)
                                .setParameter(17, spt_test_date)
                                .setParameter(18, spt_end_date)
                                ;
                        EditSuppTaskQuery.execute();
                    }else{
                        StoredProcedureQuery EditSuppShortTaskQuery = entityManager
                                .createStoredProcedureQuery("PKG_SUPPORT.pr_EditSupportShort")
                                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                                .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                                .setParameter(1, user_List.get(0).id)
                                .setParameter(2, user_Role_List.get(0).role_id)
                                .setParameter(3, spt_id)
                                .setParameter(4, spt_name)
                                .setParameter(5, spt_description)
                                .setParameter(6, sptp_id)
                                .setParameter(7, sptt_id)
                                .setParameter(8, spts_id)
                                .setParameter(9, spt_author_id)
                                .setParameter(10, spt_create_date)
                                ;
                        EditSuppShortTaskQuery.execute();
                    }
                    break;
                case 2:
                    StoredProcedureQuery DelSuppTaskQuery = entityManager
                            .createStoredProcedureQuery("PKG_SUPPORT.pr_DelSupportFull")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, spt_id)
                            ;
                    DelSuppTaskQuery.execute();
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

        mav.addObject("spt_id", spt_id);

        mav.addObject("spt_name", spt_name);
        mav.addObject("spt_description", spt_description);

        mav.addObject("sptp_id", sptp_id);
        supportTaskPriorList = supportTaskPriorListRepository.querySupportTaskPriorListAll();
        mav.addObject("sptp_list", supportTaskPriorList);

        mav.addObject("sptt_id", sptt_id);
        supportTaskTypeList = supportTaskTypeListRepository.querySupportTaskTypeListAll();
        mav.addObject("sptt_list", supportTaskTypeList);

        mav.addObject("sptt_number", sptt_number);
        mav.addObject("sptt_plan_time", sptt_plan_time);
        mav.addObject("sptt_fact_time", sptt_fact_time);

        mav.addObject("spts_id", spts_id);
        supportTaskStatusList = supportTaskStatusListRepository.querySupportTaskStatusListAll();
        mav.addObject("spts_list", supportTaskStatusList);

        mav.addObject("spt_author_id", spt_author_id);
        authorList = (List<User_List>) user_ListRepository.findAll();
        mav.addObject("spt_author_list", authorList);

        mav.addObject("spt_work_id", spt_work_id);
        workerList = (List<User_List>) user_ListRepository.findAll();
        mav.addObject("spt_work_list", workerList);

        mav.addObject("spt_cont_id", spt_cont_id);
        controlerList = (List<User_List>) user_ListRepository.findAll();
        mav.addObject("spt_cont_list", controlerList);

        mav.addObject("spt_create_date", spt_create_date);
        mav.addObject("spt_plan_date", spt_plan_date);
        mav.addObject("spt_test_date", spt_test_date);
        mav.addObject("spt_end_date", spt_end_date);

        mav.addObject("support_tasks_tab_name", "tab-2");
        mav.addObject("show_all", show_all);

        mav.addObject("support_tasks_list_table_order_column", order_column);
        mav.addObject("support_tasks_list_table_order_type", order_type);
        mav.addObject("support_tasks_list_table_search", table_search);
        mav.addObject("support_tasks_list_table_pagelen", pagelen);
        mav.addObject("support_tasks_list_table_page", page);

        mav.setViewName("/support_tasks/list_detail");
        return mav;
    }

    @PostMapping("/get_result_table")
    public JSONDatatable get_result_table(
            @RequestParam(value="start_date", required = false, defaultValue = "01.01.2018") String start_date,
            @RequestParam(value="end_date", required = false, defaultValue = "31.12.2019") String end_date
    ) throws ParseException {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");
        String StartResult = "";
        String EndResult = "";
        Date Date1 ;
        Date Date2;

        Date1 = df.parse(start_date);
        StartResult = newdf.format(Date1);

        Date2 = df.parse(end_date);
        EndResult = newdf.format(Date2);

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuSupportTaskResultRepository.queryGetSupportTaskResult(user_List.get(0).id,user_Role_List.get(0).role_id, StartResult, EndResult));

        return result;
    }

    @PostMapping("/get_time_table")
    public JSONDatatable get_time_table(
            @RequestParam(value="start_date", required = false, defaultValue = "01.01.2018") String start_date,
            @RequestParam(value="end_date", required = false, defaultValue = "31.12.2019") String end_date
    ) throws ParseException {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");
        String StartResult = "";
        String EndResult = "";
        Date Date1 ;
        Date Date2;

        Date1 = df.parse(start_date);
        StartResult = newdf.format(Date1);

        Date2 = df.parse(end_date);
        EndResult = newdf.format(Date2);

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuSupportTaskTimeRepository.queryGetSupportTaskTime(user_List.get(0).id,user_Role_List.get(0).role_id, StartResult, EndResult));

        return result;
    }

    @GetMapping("/get_result_graph")
    public List<MenuSupportTaskResultGraph> get_result_graph(
            @RequestParam(value="start_date", required = false, defaultValue = "01.01.2018") String start_date,
            @RequestParam(value="end_date", required = false, defaultValue = "31.12.2019") String end_date
    ) throws ParseException {
        List<MenuSupportTaskResultGraph> result = new ArrayList<>();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");
        String StartResult = "";
        String EndResult = "";
        Date Date1 ;
        Date Date2;

        Date1 = df.parse(start_date);
        StartResult = newdf.format(Date1);

        Date2 = df.parse(end_date);
        EndResult = newdf.format(Date2);

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result = menuSupportTaskResultGraphRepository.queryGetSupportTaskResultGraph(user_List.get(0).id,user_Role_List.get(0).role_id, StartResult, EndResult);

        return result;
    }

    @GetMapping("/get_time_graph")
    public List<MenuSupportTaskTimeGraph> get_time_graph(
            @RequestParam(value="start_date", required = false, defaultValue = "01.01.2018") String start_date,
            @RequestParam(value="end_date", required = false, defaultValue = "31.12.2019") String end_date
    ) throws ParseException {
        List<MenuSupportTaskTimeGraph> result = new ArrayList<>();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");
        String StartResult = "";
        String EndResult = "";
        Date Date1 ;
        Date Date2;

        Date1 = df.parse(start_date);
        StartResult = newdf.format(Date1);

        Date2 = df.parse(end_date);
        EndResult = newdf.format(Date2);

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result = menuSupportTaskTimeGraphRepository.queryGetSupportTaskTimeGraph(user_List.get(0).id,user_Role_List.get(0).role_id, StartResult, EndResult);

        return result;
    }

    @PostMapping("/get_result_time_table")
    public JSONDatatable get_result_time_table(
            @RequestParam(value="start_date", required = false, defaultValue = "01.01.2019") String start_date,
            @RequestParam(value="end_date", required = false, defaultValue = "01.01.2020") String end_date
    ) throws ParseException {
        JSONDatatable result = new JSONDatatable();
        List<User_Role_List> user_Role_List;
        List<User_List> user_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");
        String StartResult = "";
        String EndResult = "";
        Date Date1 ;
        Date Date2;

        Date1 = df.parse(start_date);
        StartResult = newdf.format(Date1);

        Date2 = df.parse(end_date);
        EndResult = newdf.format(Date2);

        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuSupportTaskResultTimeRepository.queryGetSupportTaskResultTime(user_List.get(0).id,user_Role_List.get(0).role_id, StartResult, EndResult));

        return result;
    }
}
