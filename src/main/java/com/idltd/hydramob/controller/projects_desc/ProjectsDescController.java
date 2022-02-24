package com.idltd.hydramob.controller.projects_desc;

import com.idltd.hydramob.entity.Contragent_List;
import com.idltd.hydramob.entity.User_List;
import com.idltd.hydramob.entity.User_Role_List;
import com.idltd.hydramob.entity.list.*;
import com.idltd.hydramob.entity.projects_desc.*;
import com.idltd.hydramob.repository.Contragent_ListRepository;
import com.idltd.hydramob.repository.User_ListRepository;
import com.idltd.hydramob.repository.User_Role_ListRepository;
import com.idltd.hydramob.repository.list.*;
import com.idltd.hydramob.repository.projects_desc.*;
import com.idltd.hydramob.utils.JSONDatatable;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.MimeUtility;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/projects_desc")
public class ProjectsDescController {

    private User_ListRepository user_ListRepository;
    private User_Role_ListRepository user_Role_ListRepository;

    private MenuProjectsMainListRepository menuProjectsMainListRepository;
    private DetailProjectsMainListRepository detailProjectsMainListRepository;
    private ProjectsTypeListRepository projectsTypeListRepository;
    private ProjectsStatusListRepository projectsStatusListRepository;
    private ProjectsPriorListRepository projectsPriorListRepository;
    private ProjectsParentListRepository projectsParentListRepository;

    private MenuProjectsDetailListRepository menuProjectsDetailListRepository;

    private MenuProjectsStagesListRepository menuProjectsStagesListRepository;

    private MenuProjectsUsersListRepository menuProjectsUsersListRepository;
    private Contragent_ListRepository contragent_ListRepository;
    private ContragentContactListRepository contragentContactListRepository;
    private ProjectRolesListRepository projectRolesListRepository;
    private DetailProjectsUsersListRepository detailProjectsUsersListRepository;

    private MenuProjectsChatListRepository menuProjectsChatListRepository;
    private DetailProjectsChatListRepository detailProjectsChatListRepository;
    private ProjectFileRepository projectFileRepository;

    private MenuProjectsFilesListRepository menuProjectsFilesListRepository;
    private DetailProjectsFilesListRepository detailProjectsFilesListRepository;

    private MenuProjectsPaymentsListRepository menuProjectsPaymentsListRepository;
    private ProjectPaymentListRepository projectPaymentListRepository;
    private Currency_Use_ListRepository currency_Use_ListRepository;
    private DetailProjectsPaymentsListRepository detailProjectsPaymentsListRepository;

    private MenuProjectsSchemeListRepository menuProjectsSchemeListRepository;
    private DetailProjectsSchemeListRepository detailProjectsSchemeListRepository;
    public ProjectsDescController(
            User_ListRepository user_ListRepository,
            User_Role_ListRepository user_Role_ListRepository,

            MenuProjectsMainListRepository menuProjectsMainListRepository,
            DetailProjectsMainListRepository detailProjectsMainListRepository,
            ProjectsTypeListRepository projectsTypeListRepository,
            ProjectsStatusListRepository projectsStatusListRepository,
            ProjectsPriorListRepository projectsPriorListRepository,
            ProjectsParentListRepository projectsParentListRepository,

            MenuProjectsDetailListRepository menuProjectsDetailListRepository,

            MenuProjectsStagesListRepository menuProjectsStagesListRepository,

            MenuProjectsUsersListRepository menuProjectsUsersListRepository,
            Contragent_ListRepository contragent_ListRepository,
            ContragentContactListRepository contragentContactListRepository,
            ProjectRolesListRepository projectRolesListRepository,
            DetailProjectsUsersListRepository detailProjectsUsersListRepository,

            MenuProjectsChatListRepository menuProjectsChatListRepository,
            DetailProjectsChatListRepository detailProjectsChatListRepository,
            ProjectFileRepository projectFileRepository,

            MenuProjectsFilesListRepository menuProjectsFilesListRepository,
            DetailProjectsFilesListRepository detailProjectsFilesListRepository,

            MenuProjectsPaymentsListRepository menuProjectsPaymentsListRepository,
            ProjectPaymentListRepository projectPaymentListRepository,
            Currency_Use_ListRepository currency_Use_ListRepository,
            DetailProjectsPaymentsListRepository detailProjectsPaymentsListRepository,

            MenuProjectsSchemeListRepository menuProjectsSchemeListRepository,
            DetailProjectsSchemeListRepository detailProjectsSchemeListRepository
    ) {
        this.user_ListRepository = user_ListRepository;
        this.user_Role_ListRepository = user_Role_ListRepository;

        this.menuProjectsMainListRepository = menuProjectsMainListRepository;
        this.detailProjectsMainListRepository = detailProjectsMainListRepository;
        this.projectsTypeListRepository = projectsTypeListRepository;
        this.projectsStatusListRepository = projectsStatusListRepository;
        this.projectsPriorListRepository = projectsPriorListRepository;
        this.projectsParentListRepository = projectsParentListRepository;

        this.menuProjectsDetailListRepository = menuProjectsDetailListRepository;

        this.menuProjectsStagesListRepository = menuProjectsStagesListRepository;

        this.menuProjectsUsersListRepository = menuProjectsUsersListRepository;
        this.contragent_ListRepository = contragent_ListRepository;
        this.contragentContactListRepository = contragentContactListRepository;
        this.projectRolesListRepository = projectRolesListRepository;
        this.detailProjectsUsersListRepository = detailProjectsUsersListRepository;

        this.menuProjectsChatListRepository = menuProjectsChatListRepository;
        this.detailProjectsChatListRepository = detailProjectsChatListRepository;
        this.projectFileRepository = projectFileRepository;

        this.menuProjectsFilesListRepository = menuProjectsFilesListRepository;
        this.detailProjectsFilesListRepository = detailProjectsFilesListRepository;

        this.menuProjectsPaymentsListRepository = menuProjectsPaymentsListRepository;
        this.projectPaymentListRepository = projectPaymentListRepository;
        this.currency_Use_ListRepository = currency_Use_ListRepository;
        this.detailProjectsPaymentsListRepository = detailProjectsPaymentsListRepository;

        this.menuProjectsSchemeListRepository = menuProjectsSchemeListRepository;
        this.detailProjectsSchemeListRepository = detailProjectsSchemeListRepository;
    }

    @RequestMapping
    public ModelAndView index(ModelAndView model,
                              @RequestParam(name = "pr_id", required = false) Long pr_id,
                              @RequestParam(name = "parent_pr_id", required = false) Long parent_pr_id,

                              @RequestParam(name = "projects_desc_main_table_order_column", required = false) Long projects_desc_main_table_order_column,
                              @RequestParam(name = "projects_desc_main_table_order_type", required = false) String projects_desc_main_table_order_type,
                              @RequestParam(name = "projects_desc_main_table_search", required = false) String projects_desc_main_table_search,
                              @RequestParam(name = "projects_desc_main_table_pagelen", required = false) Long projects_desc_main_table_pagelen,
                              @RequestParam(name = "projects_desc_main_table_page", required = false) Long projects_desc_main_table_page,

                              @RequestParam(name = "projects_desc_tab", required = false) String projects_desc_tab,

                              @RequestParam(name = "projects_desc_stages_table_order_column", required = false) Long projects_desc_stages_table_order_column,
                              @RequestParam(name = "projects_desc_stages_table_order_type", required = false) String projects_desc_stages_table_order_type,
                              @RequestParam(name = "projects_desc_stages_table_search", required = false) String projects_desc_stages_table_search,
                              @RequestParam(name = "projects_desc_stages_table_pagelen", required = false) Long projects_desc_stages_table_pagelen,
                              @RequestParam(name = "projects_desc_stages_table_page", required = false) Long projects_desc_stages_table_page,

                              @RequestParam(name = "prrl_id", required = false) Long prrl_id,

                              @RequestParam(name = "projects_desc_users_table_order_column", required = false) Long projects_desc_users_table_order_column,
                              @RequestParam(name = "projects_desc_users_table_order_type", required = false) String projects_desc_users_table_order_type,
                              @RequestParam(name = "projects_desc_users_table_search", required = false) String projects_desc_users_table_search,
                              @RequestParam(name = "projects_desc_users_table_pagelen", required = false) Long projects_desc_users_table_pagelen,
                              @RequestParam(name = "projects_desc_users_table_page", required = false) Long projects_desc_users_table_page,

                              @RequestParam(name = "pr_pay_id", required = false) Long pr_pay_id,

                              @RequestParam(name = "projects_desc_payments_table_order_column", required = false) Long projects_desc_payments_table_order_column,
                              @RequestParam(name = "projects_desc_payments_table_order_type", required = false) String projects_desc_payments_table_order_type,
                              @RequestParam(name = "projects_desc_payments_table_search", required = false) String projects_desc_payments_table_search,
                              @RequestParam(name = "projects_desc_payments_table_pagelen", required = false) Long projects_desc_payments_table_pagelen,
                              @RequestParam(name = "projects_desc_payments_table_page", required = false) Long projects_desc_payments_table_page

    ){
        model.addObject("pr_id", pr_id);
        model.addObject("parent_pr_id", parent_pr_id);

        model.addObject("projects_desc_main_table_order_column", projects_desc_main_table_order_column);
        model.addObject("projects_desc_main_table_order_type", projects_desc_main_table_order_type);
        model.addObject("projects_desc_main_table_search", projects_desc_main_table_search);
        model.addObject("projects_desc_main_table_pagelen", projects_desc_main_table_pagelen);
        model.addObject("projects_desc_main_table_page", projects_desc_main_table_page);

        model.addObject("projects_desc_tab", projects_desc_tab);

        model.addObject("projects_desc_stages_table_order_column", projects_desc_stages_table_order_column);
        model.addObject("projects_desc_stages_table_order_type", projects_desc_stages_table_order_type);
        model.addObject("projects_desc_stages_table_search", projects_desc_stages_table_search);
        model.addObject("projects_desc_stages_table_pagelen", projects_desc_stages_table_pagelen);
        model.addObject("projects_desc_stages_table_page", projects_desc_stages_table_page);

        model.addObject("prrl_id", prrl_id);

        model.addObject("projects_desc_users_table_order_column", projects_desc_users_table_order_column);
        model.addObject("projects_desc_users_table_order_type", projects_desc_users_table_order_type);
        model.addObject("projects_desc_users_table_search", projects_desc_users_table_search);
        model.addObject("projects_desc_users_table_pagelen", projects_desc_users_table_pagelen);
        model.addObject("projects_desc_users_table_page", projects_desc_users_table_page);

        model.addObject("pr_pay_id", pr_pay_id);

        model.addObject("projects_desc_payments_table_order_column", projects_desc_payments_table_order_column);
        model.addObject("projects_desc_payments_table_order_type", projects_desc_payments_table_order_type);
        model.addObject("projects_desc_payments_table_search", projects_desc_payments_table_search);
        model.addObject("projects_desc_payments_table_pagelen", projects_desc_payments_table_pagelen);
        model.addObject("projects_desc_payments_table_page", projects_desc_payments_table_page);

        model.setViewName("projects_desc/cover");
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

        result.setData(menuProjectsMainListRepository.queryMenuProjectMainListByID(user_List.get(0).id, user_Role_List.get(0).role_id));

        return result;
    }

    @GetMapping("/get_detail")
    public JSONDatatable get_detail(
            @RequestParam(value="pr_id", defaultValue = "1") Long pr_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        result.setData(menuProjectsDetailListRepository.queryMenuProjectDetailListByID(user_List.get(0).id, user_Role_List.get(0).role_id, pr_id));
        return result;
    }

    @RequestMapping("/main_detail")
    public ModelAndView main_detail(@RequestParam(value="mode") Long mode,
                                    @RequestParam(value="pr_id") Long pr_id,

                                    @RequestParam(name = "projects_desc_main_table_order_column", required = false) Long projects_desc_main_table_order_column,
                                    @RequestParam(name = "projects_desc_main_table_order_type", required = false) String projects_desc_main_table_order_type,
                                    @RequestParam(name = "projects_desc_main_table_search", required = false) String projects_desc_main_table_search,
                                    @RequestParam(name = "projects_desc_main_table_pagelen", required = false) Long projects_desc_main_table_pagelen,
                                    @RequestParam(name = "projects_desc_main_table_page", required = false) Long projects_desc_main_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_List> author_user_List;
        List<User_Role_List> user_Role_List;
        List<ProjectsParentList> projectsParentList;
        List<DetailProjectsMainList> detailProjectsMainList;
        List<ProjectsTypeList> projectsTypeList;
        List<ProjectsStatusList> projectsStatusList;
        List<ProjectsPriorList> projectsPriorList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("parent_pr_id", pr_id);
            projectsParentList = projectsParentListRepository.queryProjectPriorListAll(user_List.get(0).id, user_Role_List.get(0).role_id, new Long(0));
            mav.addObject("parent_pr_list", projectsParentList);

            if(pr_id == null || pr_id < 0) {
                mav.addObject("pr_type_id", 1);
            }
            else{
                mav.addObject("pr_type_id", 2);
            }
            projectsTypeList = projectsTypeListRepository.queryProjectTypeListAll();
            mav.addObject("pr_type_list", projectsTypeList);

            mav.addObject("pr_status_id",  1);
            projectsStatusList = projectsStatusListRepository.queryProjectsStatusNewByID();
            mav.addObject("pr_status_list",  projectsStatusList);

            mav.addObject("author_id",  user_List.get(0).id);
            author_user_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("author_list", author_user_List);

            mav.addObject("pr_prior_id",  2);
            projectsPriorList = projectsPriorListRepository.queryProjectPriorListAll();
            mav.addObject("pr_prior_list",  projectsPriorList);
        }
        else{
            detailProjectsMainList = detailProjectsMainListRepository.queryDetailProjectMainListByID(user_List.get(0).id, user_Role_List.get(0).role_id, pr_id);

            mav.addObject("pr_id", detailProjectsMainList.get(0).pr_id);

            mav.addObject("parent_pr_id", detailProjectsMainList.get(0).parent_pr_id);
            projectsParentList = projectsParentListRepository.queryProjectPriorListAll(user_List.get(0).id, user_Role_List.get(0).role_id, pr_id);
            mav.addObject("parent_pr_list", projectsParentList);

            mav.addObject("pr_number",  detailProjectsMainList.get(0).pr_number);

            mav.addObject("pr_sname",  detailProjectsMainList.get(0).pr_sname);

            mav.addObject("pr_create_date",  detailProjectsMainList.get(0).pr_create_date);

            mav.addObject("pr_type_id",  detailProjectsMainList.get(0).pr_type_id);
            projectsTypeList = projectsTypeListRepository.queryProjectTypeListAll();
            mav.addObject("pr_type_list",  projectsTypeList);

            mav.addObject("pr_status_id",  detailProjectsMainList.get(0).pr_status_id);
            projectsStatusList = projectsStatusListRepository.queryProjectsStatusListAll();
            mav.addObject("pr_status_list",  projectsStatusList);

            mav.addObject("author_id",  detailProjectsMainList.get(0).author_id);
            author_user_List = user_ListRepository.queryUserByID(detailProjectsMainList.get(0).author_id);
            mav.addObject("author_list", author_user_List);

            mav.addObject("pr_description",  detailProjectsMainList.get(0).pr_description);

            mav.addObject("pr_prior_id",  detailProjectsMainList.get(0).pr_prior_id);
            projectsPriorList = projectsPriorListRepository.queryProjectPriorListAll();
            mav.addObject("pr_prior_list",  projectsPriorList);

            mav.addObject("pr_plan_start_date",  detailProjectsMainList.get(0).pr_plan_start_date);
            mav.addObject("pr_plan_end_date",  detailProjectsMainList.get(0).pr_plan_end_date);
            mav.addObject("pr_fact_start_date",  detailProjectsMainList.get(0).pr_fact_start_date);
            mav.addObject("pr_fact_end_date",  detailProjectsMainList.get(0).pr_fact_end_date);

            mav.addObject("pr_result",  detailProjectsMainList.get(0).pr_result);
        }

        mav.addObject("projects_desc_main_table_order_column", projects_desc_main_table_order_column);
        mav.addObject("projects_desc_main_table_order_type", projects_desc_main_table_order_type);
        mav.addObject("projects_desc_main_table_search", projects_desc_main_table_search);
        mav.addObject("projects_desc_main_table_pagelen", projects_desc_main_table_pagelen);
        mav.addObject("projects_desc_main_table_page", projects_desc_main_table_page);        
        
        mav.setViewName("/projects_desc/main_detail");
        return mav;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/main_model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="pr_id") Long pr_id,
            @RequestParam(value="parent_pr_id", required = false, defaultValue = "0") Long parent_pr_id,

            @RequestParam(value="pr_number", required = false) String pr_number,
            @RequestParam(value="pr_sname", required = false) String pr_sname,

            @RequestParam(value="pr_type_id", required = false) Long pr_type_id,
            @RequestParam(value="pr_status_id", required = false) Long pr_status_id,
            @RequestParam(value="author_id", required = false) Long author_id,

            @RequestParam(value="pr_create_date", required = false) String pr_create_date,
            @RequestParam(value="pr_description", required = false) String pr_description,
            @RequestParam(value="pr_prior_id", required = false) Long pr_prior_id,

            @RequestParam(value="pr_persent", required = false, defaultValue = "0") Long pr_persent,
            @RequestParam(value="pr_plan_start_date", required = false) String pr_plan_start_date,
            @RequestParam(value="pr_plan_end_date", required = false) String pr_plan_end_date,
            @RequestParam(value="pr_fact_start_date", required = false) String pr_fact_start_date,
            @RequestParam(value="pr_fact_end_date", required = false) String pr_fact_end_date,

            @RequestParam(value="pr_result", required = false) String pr_result,

            @RequestParam(value="projects_desc_main_table_order_column", required = false) Long order_column,
            @RequestParam(value="projects_desc_main_table_order_type", required = false) String order_type,
            @RequestParam(value="projects_desc_main_table_search", required = false) String table_search,
            @RequestParam(value="projects_desc_main_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="projects_desc_main_table_page", required = false) Long page
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_List> author_user_List;
        List<User_Role_List> user_Role_List;
        List<ProjectsParentList> projectsParentList;
        List<DetailProjectsMainList> detailProjectsMainList;
        List<ProjectsTypeList> projectsTypeList;
        List<ProjectsStatusList> projectsStatusList;
        List<ProjectsPriorList> projectsPriorList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");

        String FormatPlanStartDate = "";
        Date ParsePlanStartDate = null;

        if (pr_plan_start_date != "" && pr_plan_start_date != null) {
            String PlanStartDate = pr_plan_start_date;
            ParsePlanStartDate = df.parse(PlanStartDate);
            FormatPlanStartDate = newdf.format(ParsePlanStartDate);
        }

        String FormatPlanEndDate = "";
        Date ParsePlanEndDate = null;

        if (pr_plan_end_date != "" && pr_plan_end_date != null) {
            String PlanEndDate = pr_plan_end_date;
            ParsePlanEndDate = df.parse(PlanEndDate);
            FormatPlanEndDate = newdf.format(ParsePlanEndDate);
        }

        String FormatFactStartDate = "";
        Date ParseFactStartDate = null;

        if (pr_fact_start_date != "" && pr_fact_start_date != null) {
            String FactStartDate = pr_fact_start_date;
            ParseFactStartDate = df.parse(FactStartDate);
            FormatFactStartDate = newdf.format(ParseFactStartDate);
        }

        String FormatFactEndDate = "";
        Date ParseFactEndDate = null;

        if (pr_fact_end_date != "" && pr_fact_end_date != null) {
            String FactEndDate = pr_fact_end_date;
            ParseFactEndDate = df.parse(FactEndDate);
            FormatFactEndDate = newdf.format(ParseFactEndDate);
        }

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddProjectQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_AddProject")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(15, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, parent_pr_id)
                            .setParameter(4, pr_sname)
                            .setParameter(5, pr_type_id)
                            .setParameter(6, pr_status_id)
                            .setParameter(7, author_id)
                            .setParameter(8, pr_description)
                            .setParameter(9, pr_prior_id)
                            .setParameter(10, FormatPlanStartDate)
                            .setParameter(11, FormatPlanEndDate)
                            .setParameter(12, FormatFactStartDate)
                            .setParameter(13, FormatFactEndDate)
                            .setParameter(14, pr_result)
                            .setParameter(15, pr_persent)
                            ;
                    AddProjectQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditProjectQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_EditProject")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(16, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, pr_id)
                            .setParameter(4, parent_pr_id)
                            .setParameter(5, pr_sname)
                            .setParameter(6, pr_type_id)
                            .setParameter(7, pr_status_id)
                            .setParameter(8, author_id)
                            .setParameter(9, pr_description)
                            .setParameter(10, pr_prior_id)
                            .setParameter(11, FormatPlanStartDate)
                            .setParameter(12, FormatPlanEndDate)
                            .setParameter(13, FormatFactStartDate)
                            .setParameter(14, FormatFactEndDate)
                            .setParameter(15, pr_result)
                            .setParameter(16, pr_persent)
                            ;
                    EditProjectQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelProjectQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_DelProject")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, pr_id)
                            ;
                    DelProjectQuery.execute();
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

        mav.addObject("pr_id", pr_id);

        if(mode == 0){
            mav.addObject("parent_pr_id", pr_id);
            projectsParentList = projectsParentListRepository.queryProjectPriorListAll(user_List.get(0).id, user_Role_List.get(0).role_id, new Long(0));
            mav.addObject("parent_pr_list", projectsParentList);

            if(pr_id == null || pr_id < 0) {
                mav.addObject("pr_type_id", 1);
            }
            else{
                mav.addObject("pr_type_id", 2);
            }
            projectsTypeList = projectsTypeListRepository.queryProjectTypeListAll();
            mav.addObject("pr_type_list", projectsTypeList);

            mav.addObject("pr_status_id",  1);
            projectsStatusList = projectsStatusListRepository.queryProjectsStatusNewByID();
            mav.addObject("pr_status_list",  projectsStatusList);

            mav.addObject("author_id",  user_List.get(0).id);
            author_user_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("author_list", author_user_List);

            mav.addObject("pr_prior_id",  2);
            projectsPriorList = projectsPriorListRepository.queryProjectPriorListAll();
            mav.addObject("pr_prior_list",  projectsPriorList);
        }
        else{
            mav.addObject("pr_id", pr_id);

            mav.addObject("parent_pr_id", parent_pr_id);
            projectsParentList = projectsParentListRepository.queryProjectPriorListAll(user_List.get(0).id, user_Role_List.get(0).role_id, pr_id);
            mav.addObject("parent_pr_list", projectsParentList);

            mav.addObject("pr_number", pr_number);

            mav.addObject("pr_sname", pr_sname);

            mav.addObject("pr_create_date",  pr_create_date);

            mav.addObject("pr_type_id", pr_type_id);
            projectsTypeList = projectsTypeListRepository.queryProjectTypeListAll();
            mav.addObject("pr_type_list", projectsTypeList);

            mav.addObject("pr_status_id", pr_status_id);
            projectsStatusList = projectsStatusListRepository.queryProjectsStatusListAll();
            mav.addObject("pr_status_list", projectsStatusList);

            mav.addObject("author_id", author_id);
            author_user_List = user_ListRepository.queryUserByID(author_id);
            mav.addObject("author_list", author_user_List);

            mav.addObject("pr_description", pr_description);

            mav.addObject("pr_prior_id", pr_prior_id);
            projectsPriorList = projectsPriorListRepository.queryProjectPriorListAll();
            mav.addObject("pr_prior_list", projectsPriorList);

            mav.addObject("pr_plan_start_date", pr_plan_start_date);
            mav.addObject("pr_plan_end_date", pr_plan_end_date);
            mav.addObject("pr_fact_start_date", pr_fact_start_date);
            mav.addObject("pr_fact_end_date", pr_fact_end_date);

            mav.addObject("pr_result", pr_result);
        }

        mav.addObject("projects_desc_main_table_order_column", order_column);
        mav.addObject("projects_desc_main_table_order_type", order_type);
        mav.addObject("projects_desc_main_table_search", table_search);
        mav.addObject("projects_desc_main_table_pagelen", pagelen);
        mav.addObject("projects_desc_main_table_page", page);

        mav.setViewName("/projects_desc/main_detail");
        return mav;
    }

    @PostMapping("/main_up_prior")
    public ModelAndView main_up_prior(
            @RequestParam(name = "pr_id") Long pr_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery MainCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_PROJECTS.pr_UpProjectPrior")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, pr_id);
        MainCheckQuery.execute();
        return null;
    }

    @PostMapping("/main_down_prior")
    public ModelAndView main_down_prior(
            @RequestParam(name = "pr_id") Long pr_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery MainCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_PROJECTS.pr_DownProjectPrior")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, pr_id);
        MainCheckQuery.execute();
        return null;
    }

    @PostMapping("/main_up_status")
    public ModelAndView main_up_status(
            @RequestParam(name = "pr_id") Long pr_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery MainCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_PROJECTS.pr_UpProjectStatus")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, pr_id);
        MainCheckQuery.execute();
        return null;
    }

    @PostMapping("/main_down_status")
    public ModelAndView main_down_status(
            @RequestParam(name = "pr_id") Long pr_id
    ) {
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        StoredProcedureQuery MainCheckQuery = entityManager
                .createStoredProcedureQuery("PKG_PROJECTS.pr_DownProjectStatus")
                .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                .setParameter(1, user_List.get(0).id)
                .setParameter(2, user_Role_List.get(0).role_id)
                .setParameter(3, pr_id);
        MainCheckQuery.execute();
        return null;
    }


    @PostMapping("/get_stages_table")
    public JSONDatatable get_stages_table(
            @RequestParam(value="parent_pr_id", required = false) Long parent_pr_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(parent_pr_id != null && parent_pr_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuProjectsStagesListRepository.queryMenuProjectStagesListByID(user_List.get(0).id, user_Role_List.get(0).role_id, parent_pr_id));
        }
        return result;
    }

    @RequestMapping("/stages_detail")
    public ModelAndView stages_detail(@RequestParam(value="mode") Long mode,

                                      @RequestParam(value="child_pr_id") Long child_pr_id,
                                      @RequestParam(value="parent_pr_id") Long parent_pr_id,

                                      @RequestParam(name = "projects_desc_main_table_order_column", required = false) Long projects_desc_main_table_order_column,
                                      @RequestParam(name = "projects_desc_main_table_order_type", required = false) String projects_desc_main_table_order_type,
                                      @RequestParam(name = "projects_desc_main_table_search", required = false) String projects_desc_main_table_search,
                                      @RequestParam(name = "projects_desc_main_table_pagelen", required = false) Long projects_desc_main_table_pagelen,
                                      @RequestParam(name = "projects_desc_main_table_page", required = false) Long projects_desc_main_table_page,                                      
                                      
                                      @RequestParam(name = "projects_desc_stages_table_order_column", required = false) Long projects_desc_stages_table_order_column,
                                      @RequestParam(name = "projects_desc_stages_table_order_type", required = false) String projects_desc_stages_table_order_type,
                                      @RequestParam(name = "projects_desc_stages_table_search", required = false) String projects_desc_stages_table_search,
                                      @RequestParam(name = "projects_desc_stages_table_pagelen", required = false) Long projects_desc_stages_table_pagelen,
                                      @RequestParam(name = "projects_desc_stages_table_page", required = false) Long projects_desc_stages_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_List> author_user_List;
        List<User_Role_List> user_Role_List;
        List<ProjectsParentList> projectsParentList;
        List<DetailProjectsMainList> detailProjectsMainList;
        List<ProjectsTypeList> projectsTypeList;
        List<ProjectsStatusList> projectsStatusList;
        List<ProjectsPriorList> projectsPriorList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode == 0){
            mav.addObject("parent_pr_id", parent_pr_id);
            projectsParentList = projectsParentListRepository.queryProjectPriorListAll(user_List.get(0).id, user_Role_List.get(0).role_id, new Long(0));
            mav.addObject("parent_pr_list", projectsParentList);

            if(parent_pr_id == null || parent_pr_id < 0) {
                mav.addObject("pr_type_id", 1);
            }
            else{
                mav.addObject("pr_type_id", 3);
            }
            projectsTypeList = projectsTypeListRepository.queryProjectTypeListAll();
            mav.addObject("pr_type_list", projectsTypeList);

            mav.addObject("pr_status_id",  1);
            projectsStatusList = projectsStatusListRepository.queryProjectsStatusNewByID();
            mav.addObject("pr_status_list",  projectsStatusList);

            mav.addObject("author_id",  user_List.get(0).id);
            author_user_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("author_list", author_user_List);

            mav.addObject("pr_prior_id",  2);
            projectsPriorList = projectsPriorListRepository.queryProjectPriorListAll();
            mav.addObject("pr_prior_list",  projectsPriorList);
        }
        else{
            detailProjectsMainList = detailProjectsMainListRepository.queryDetailProjectMainListByID(user_List.get(0).id, user_Role_List.get(0).role_id, child_pr_id);

            mav.addObject("child_pr_id", detailProjectsMainList.get(0).pr_id);

            mav.addObject("parent_pr_id", detailProjectsMainList.get(0).parent_pr_id);
            projectsParentList = projectsParentListRepository.queryProjectPriorListAll(user_List.get(0).id, user_Role_List.get(0).role_id, child_pr_id);
            mav.addObject("parent_pr_list", projectsParentList);

            mav.addObject("pr_number",  detailProjectsMainList.get(0).pr_number);

            mav.addObject("pr_sname",  detailProjectsMainList.get(0).pr_sname);

            mav.addObject("pr_create_date",  detailProjectsMainList.get(0).pr_create_date);

            mav.addObject("pr_type_id",  detailProjectsMainList.get(0).pr_type_id);
            projectsTypeList = projectsTypeListRepository.queryProjectTypeListAll();
            mav.addObject("pr_type_list",  projectsTypeList);

            mav.addObject("pr_status_id",  detailProjectsMainList.get(0).pr_status_id);
            projectsStatusList = projectsStatusListRepository.queryProjectsStatusListAll();
            mav.addObject("pr_status_list",  projectsStatusList);

            mav.addObject("author_id",  detailProjectsMainList.get(0).author_id);
            author_user_List = user_ListRepository.queryUserByID(detailProjectsMainList.get(0).author_id);
            mav.addObject("author_list", author_user_List);

            mav.addObject("pr_description",  detailProjectsMainList.get(0).pr_description);

            mav.addObject("pr_prior_id",  detailProjectsMainList.get(0).pr_prior_id);
            projectsPriorList = projectsPriorListRepository.queryProjectPriorListAll();
            mav.addObject("pr_prior_list",  projectsPriorList);

            mav.addObject("pr_plan_start_date",  detailProjectsMainList.get(0).pr_plan_start_date);
            mav.addObject("pr_plan_end_date",  detailProjectsMainList.get(0).pr_plan_end_date);
            mav.addObject("pr_fact_start_date",  detailProjectsMainList.get(0).pr_fact_start_date);
            mav.addObject("pr_fact_end_date",  detailProjectsMainList.get(0).pr_fact_end_date);

            mav.addObject("pr_result",  detailProjectsMainList.get(0).pr_result);
        }
        mav.addObject("pr_id", parent_pr_id);

        mav.addObject("projects_desc_main_table_order_column", projects_desc_main_table_order_column);
        mav.addObject("projects_desc_main_table_order_type", projects_desc_main_table_order_type);
        mav.addObject("projects_desc_main_table_search", projects_desc_main_table_search);
        mav.addObject("projects_desc_main_table_pagelen", projects_desc_main_table_pagelen);
        mav.addObject("projects_desc_main_table_page", projects_desc_main_table_page);        
        
        mav.addObject("projects_desc_stages_table_order_column", projects_desc_stages_table_order_column);
        mav.addObject("projects_desc_stages_table_order_type", projects_desc_stages_table_order_type);
        mav.addObject("projects_desc_stages_table_search", projects_desc_stages_table_search);
        mav.addObject("projects_desc_stages_table_pagelen", projects_desc_stages_table_pagelen);
        mav.addObject("projects_desc_stages_table_page", projects_desc_stages_table_page);

        mav.addObject("projects_desc_tab", "tab-2");

        mav.setViewName("/projects_desc/stages_detail");
        return mav;
    }

    @PostMapping("/stages_model")
    public ModelAndView stages_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="child_pr_id") Long child_pr_id,
            @RequestParam(value="parent_pr_id", required = false, defaultValue = "0") Long parent_pr_id,

            @RequestParam(value="pr_number", required = false) String pr_number,
            @RequestParam(value="pr_sname", required = false) String pr_sname,

            @RequestParam(value="pr_type_id", required = false) Long pr_type_id,
            @RequestParam(value="pr_status_id", required = false) Long pr_status_id,
            @RequestParam(value="author_id", required = false) Long author_id,

            @RequestParam(value="pr_create_date", required = false) String pr_create_date,
            @RequestParam(value="pr_description", required = false) String pr_description,
            @RequestParam(value="pr_prior_id", required = false) Long pr_prior_id,

            @RequestParam(value="pr_persent", required = false, defaultValue = "0") Long pr_persent,
            @RequestParam(value="pr_plan_start_date", required = false) String pr_plan_start_date,
            @RequestParam(value="pr_plan_end_date", required = false) String pr_plan_end_date,
            @RequestParam(value="pr_fact_start_date", required = false) String pr_fact_start_date,
            @RequestParam(value="pr_fact_end_date", required = false) String pr_fact_end_date,

            @RequestParam(value="pr_result", required = false) String pr_result,

            @RequestParam(name = "projects_desc_main_table_order_column", required = false) Long projects_desc_main_table_order_column,
            @RequestParam(name = "projects_desc_main_table_order_type", required = false) String projects_desc_main_table_order_type,
            @RequestParam(name = "projects_desc_main_table_search", required = false) String projects_desc_main_table_search,
            @RequestParam(name = "projects_desc_main_table_pagelen", required = false) Long projects_desc_main_table_pagelen,
            @RequestParam(name = "projects_desc_main_table_page", required = false) Long projects_desc_main_table_page,

            @RequestParam(value="projects_desc_stages_table_order_column", required = false) Long projects_desc_stages_table_order_column,
            @RequestParam(value="projects_desc_stages_table_order_type", required = false) String projects_desc_stages_table_order_type,
            @RequestParam(value="projects_desc_stages_table_search", required = false) String projects_desc_stages_table_search,
            @RequestParam(value="projects_desc_stages_table_pagelen", required = false) Long projects_desc_stages_table_pagelen,
            @RequestParam(value="projects_desc_stages_table_page", required = false) Long projects_desc_stages_table_page
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_List> author_user_List;
        List<User_Role_List> user_Role_List;
        List<ProjectsParentList> projectsParentList;
        List<DetailProjectsMainList> detailProjectsMainList;
        List<ProjectsTypeList> projectsTypeList;
        List<ProjectsStatusList> projectsStatusList;
        List<ProjectsPriorList> projectsPriorList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");

        String FormatPlanStartDate = "";
        Date ParsePlanStartDate = null;

        if (pr_plan_start_date != "" && pr_plan_start_date != null) {
            String PlanStartDate = pr_plan_start_date;
            ParsePlanStartDate = df.parse(PlanStartDate);
            FormatPlanStartDate = newdf.format(ParsePlanStartDate);
        }

        String FormatPlanEndDate = "";
        Date ParsePlanEndDate = null;

        if (pr_plan_end_date != "" && pr_plan_end_date != null) {
            String PlanEndDate = pr_plan_end_date;
            ParsePlanEndDate = df.parse(PlanEndDate);
            FormatPlanEndDate = newdf.format(ParsePlanEndDate);
        }

        String FormatFactStartDate = "";
        Date ParseFactStartDate = null;

        if (pr_fact_start_date != "" && pr_fact_start_date != null) {
            String FactStartDate = pr_fact_start_date;
            ParseFactStartDate = df.parse(FactStartDate);
            FormatFactStartDate = newdf.format(ParseFactStartDate);
        }

        String FormatFactEndDate = "";
        Date ParseFactEndDate = null;

        if (pr_fact_end_date != "" && pr_fact_end_date != null) {
            String FactEndDate = pr_fact_end_date;
            ParseFactEndDate = df.parse(FactEndDate);
            FormatFactEndDate = newdf.format(ParseFactEndDate);
        }

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddProjectStagesQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_AddProject")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(15, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, parent_pr_id)
                            .setParameter(4, pr_sname)
                            .setParameter(5, pr_type_id)
                            .setParameter(6, pr_status_id)
                            .setParameter(7, author_id)
                            .setParameter(8, pr_description)
                            .setParameter(9, pr_prior_id)
                            .setParameter(10, FormatPlanStartDate)
                            .setParameter(11, FormatPlanEndDate)
                            .setParameter(12, FormatFactStartDate)
                            .setParameter(13, FormatFactEndDate)
                            .setParameter(14, pr_result)
                            .setParameter(15, pr_persent)
                            ;
                    AddProjectStagesQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditStagesProjectQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_EditProject")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(13, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(14, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(15, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(16, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, child_pr_id)
                            .setParameter(4, parent_pr_id)
                            .setParameter(5, pr_sname)
                            .setParameter(6, pr_type_id)
                            .setParameter(7, pr_status_id)
                            .setParameter(8, author_id)
                            .setParameter(9, pr_description)
                            .setParameter(10, pr_prior_id)
                            .setParameter(11, FormatPlanStartDate)
                            .setParameter(12, FormatPlanEndDate)
                            .setParameter(13, FormatFactStartDate)
                            .setParameter(14, FormatFactEndDate)
                            .setParameter(15, pr_result)
                            .setParameter(16, pr_persent)
                            ;
                    EditStagesProjectQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelProjectStagesQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_DelProject")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, child_pr_id)
                            ;
                    DelProjectStagesQuery.execute();
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

        mav.addObject("child_pr_id", child_pr_id);

        if(mode == 0){
            mav.addObject("parent_pr_id", parent_pr_id);
            projectsParentList = projectsParentListRepository.queryProjectPriorListAll(user_List.get(0).id, user_Role_List.get(0).role_id, new Long(0));
            mav.addObject("parent_pr_list", projectsParentList);

            if(parent_pr_id == null || parent_pr_id < 0) {
                mav.addObject("pr_type_id", 1);
            }
            else{
                mav.addObject("pr_type_id", 2);
            }
            projectsTypeList = projectsTypeListRepository.queryProjectTypeListAll();
            mav.addObject("pr_type_list", projectsTypeList);

            mav.addObject("pr_status_id",  1);
            projectsStatusList = projectsStatusListRepository.queryProjectsStatusNewByID();
            mav.addObject("pr_status_list",  projectsStatusList);

            mav.addObject("author_id",  user_List.get(0).id);
            author_user_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("author_list", author_user_List);

            mav.addObject("pr_prior_id",  2);
            projectsPriorList = projectsPriorListRepository.queryProjectPriorListAll();
            mav.addObject("pr_prior_list",  projectsPriorList);
        }
        else{
            mav.addObject("child_pr_id", child_pr_id);

            mav.addObject("parent_pr_id", parent_pr_id);
            projectsParentList = projectsParentListRepository.queryProjectPriorListAll(user_List.get(0).id, user_Role_List.get(0).role_id, parent_pr_id);
            mav.addObject("parent_pr_list", projectsParentList);

            mav.addObject("pr_number", pr_number);

            mav.addObject("pr_sname", pr_sname);

            mav.addObject("pr_create_date",  pr_create_date);

            mav.addObject("pr_type_id", pr_type_id);
            projectsTypeList = projectsTypeListRepository.queryProjectTypeListAll();
            mav.addObject("pr_type_list", projectsTypeList);

            mav.addObject("pr_status_id", pr_status_id);
            projectsStatusList = projectsStatusListRepository.queryProjectsStatusListAll();
            mav.addObject("pr_status_list", projectsStatusList);

            mav.addObject("author_id", author_id);
            author_user_List = user_ListRepository.queryUserByID(author_id);
            mav.addObject("author_list", author_user_List);

            mav.addObject("pr_description", pr_description);

            mav.addObject("pr_prior_id", pr_prior_id);
            projectsPriorList = projectsPriorListRepository.queryProjectPriorListAll();
            mav.addObject("pr_prior_list", projectsPriorList);

            mav.addObject("pr_plan_start_date", pr_plan_start_date);
            mav.addObject("pr_plan_end_date", pr_plan_end_date);
            mav.addObject("pr_fact_start_date", pr_fact_start_date);
            mav.addObject("pr_fact_end_date", pr_fact_end_date);

            mav.addObject("pr_result", pr_result);
        }
        mav.addObject("pr_id", parent_pr_id);

        mav.addObject("projects_desc_main_table_order_column", projects_desc_main_table_order_column);
        mav.addObject("projects_desc_main_table_order_type", projects_desc_main_table_order_type);
        mav.addObject("projects_desc_main_table_search", projects_desc_main_table_search);
        mav.addObject("projects_desc_main_table_pagelen", projects_desc_main_table_pagelen);
        mav.addObject("projects_desc_main_table_page", projects_desc_main_table_page);

        mav.addObject("projects_desc_stages_table_order_column", projects_desc_stages_table_order_column);
        mav.addObject("projects_desc_stages_table_order_type", projects_desc_stages_table_order_type);
        mav.addObject("projects_desc_stages_table_search", projects_desc_stages_table_search);
        mav.addObject("projects_desc_stages_table_pagelen", projects_desc_stages_table_pagelen);
        mav.addObject("projects_desc_stages_table_page", projects_desc_stages_table_page);

        mav.addObject("projects_desc_tab", "tab-2");

        mav.setViewName("/projects_desc/stages_detail");
        return mav;
    }

    @PostMapping("/get_users_table")
    public JSONDatatable get_users_table(
            @RequestParam(value="parent_pr_id", required = false) Long parent_pr_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(parent_pr_id != null && parent_pr_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuProjectsUsersListRepository.queryMenuProjectUsersListByID(user_List.get(0).id, user_Role_List.get(0).role_id, parent_pr_id));
        }
        return result;
    }

    @RequestMapping("/users_detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="parent_pr_id") Long parent_pr_id,
                                   @RequestParam(value="parent_pr_name") String parent_pr_name,
                                   @RequestParam(value="prrl_id", required = false) Long prrl_id,
                                   
                                   @RequestParam(value="projects_desc_users_table_order_column") Long projects_desc_users_table_order_column,
                                   @RequestParam(value="projects_desc_users_table_order_type") String projects_desc_users_table_order_type,
                                   @RequestParam(value="projects_desc_users_table_search") String projects_desc_users_table_search,
                                   @RequestParam(value="projects_desc_users_table_pagelen") Long projects_desc_users_table_pagelen,
                                   @RequestParam(value="projects_desc_users_table_page") Long projects_desc_users_table_page,

                                   @RequestParam(value="projects_desc_main_table_order_column") Long projects_desc_main_table_order_column,
                                   @RequestParam(value="projects_desc_main_table_order_type") String projects_desc_main_table_order_type,
                                   @RequestParam(value="projects_desc_main_table_search") String projects_desc_main_table_search,
                                   @RequestParam(value="projects_desc_main_table_pagelen") Long projects_desc_main_table_pagelen,
                                   @RequestParam(value="projects_desc_main_table_page") Long projects_desc_main_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_List> deatil_user_List;

        List<User_Role_List> user_Role_List;
        List<Contragent_List> contragent_List;
        List<ContragentContactList> contragentContactList;
        List<ProjectRolesList> projectRolesList;
        List<DetailProjectsUsersList> detailProjectsUsersList;
        List<DetailProjectsUsersList> detailDelProjectsUsersList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("parent_pr_id", parent_pr_id);
        mav.addObject("parent_pr_name", parent_pr_name);

        if(mode == 0){
            mav.addObject("employee_id", 1);

            mav.addObject("user_id", user_List.get(0).id);
            deatil_user_List = user_ListRepository.queryUserAll();
            mav.addObject("users_list", deatil_user_List);

            mav.addObject("partner_id", 0);

            contragent_List = contragent_ListRepository.queryCntByUserID(user_List.get(0).id);
            mav.addObject("cnt_list", contragent_List);

            mav.addObject("pr_role_id", 1);
            projectRolesList = (List<ProjectRolesList>) projectRolesListRepository.findAll();
            mav.addObject("pr_role_list", projectRolesList);
        }
        else if(mode == 1){
            detailProjectsUsersList = detailProjectsUsersListRepository.queryDetailProjectUsersListByID(user_List.get(0).id, user_Role_List.get(0).role_id, prrl_id);

            mav.addObject("prrl_id",  detailProjectsUsersList.get(0).prrl_id);

            mav.addObject("employee_id",  detailProjectsUsersList.get(0).prrl_employee_id);

            mav.addObject("user_id", detailProjectsUsersList.get(0).user_id);
            deatil_user_List = user_ListRepository.queryUserAll();
            mav.addObject("users_list", deatil_user_List);

            mav.addObject("partner_id", detailProjectsUsersList.get(0).prrl_partner_id);

            if(detailProjectsUsersList.get(0).prrl_partner_id != null && detailProjectsUsersList.get(0).prrl_partner_id > 0) {
                mav.addObject("cnt_id", detailProjectsUsersList.get(0).cnt_id);
            }
            contragent_List = contragent_ListRepository.queryCntByUserID(user_List.get(0).id);
            mav.addObject("cnt_list", contragent_List);

            if(detailProjectsUsersList.get(0).prrl_partner_id != null && detailProjectsUsersList.get(0).prrl_partner_id > 0) {
                mav.addObject("cc_id", detailProjectsUsersList.get(0).cc_id);
                contragentContactList = contragentContactListRepository.queryCntContactByID(user_List.get(0).id, user_Role_List.get(0).role_id, detailProjectsUsersList.get(0).cnt_id, parent_pr_id, detailProjectsUsersList.get(0).prrl_id);
                mav.addObject("contacts_list", contragentContactList);
            }

            mav.addObject("pr_role_id", detailProjectsUsersList.get(0).pr_role_id);
            projectRolesList = (List<ProjectRolesList>) projectRolesListRepository.findAll();
            mav.addObject("pr_role_list", projectRolesList);
        }
        else{
            detailDelProjectsUsersList = detailProjectsUsersListRepository.queryDetailProjectUsersListByID(user_List.get(0).id, user_Role_List.get(0).role_id, prrl_id);

            mav.addObject("prrl_id",  detailDelProjectsUsersList.get(0).prrl_id);

            mav.addObject("employee_id",  detailDelProjectsUsersList.get(0).prrl_employee_id);

            if(detailDelProjectsUsersList.get(0).prrl_employee_id != null && detailDelProjectsUsersList.get(0).prrl_employee_id > 0) {
                mav.addObject("user_id", detailDelProjectsUsersList.get(0).user_id);
                deatil_user_List = user_ListRepository.queryUserByID(detailDelProjectsUsersList.get(0).user_id);
                mav.addObject("users_list", deatil_user_List);
            }

            mav.addObject("partner_id", detailDelProjectsUsersList.get(0).prrl_partner_id);
            if(detailDelProjectsUsersList.get(0).prrl_partner_id != null && detailDelProjectsUsersList.get(0).prrl_partner_id > 0) {
                mav.addObject("cnt_id", detailDelProjectsUsersList.get(0).cnt_id);
                contragent_List = contragent_ListRepository.queryCntByID(detailDelProjectsUsersList.get(0).cnt_id);
                mav.addObject("cnt_list", contragent_List);

                mav.addObject("cc_id", detailDelProjectsUsersList.get(0).cc_id);
                contragentContactList = contragentContactListRepository.queryCntContactByID(user_List.get(0).id, user_Role_List.get(0).role_id, detailDelProjectsUsersList.get(0).cnt_id, parent_pr_id, detailDelProjectsUsersList.get(0).prrl_id);
                mav.addObject("contacts_list", contragentContactList);
            }
;
            mav.addObject("pr_role_id", detailDelProjectsUsersList.get(0).pr_role_id);
            projectRolesList = (List<ProjectRolesList>) projectRolesListRepository.findAll();
            mav.addObject("pr_role_list", projectRolesList);
        }

        mav.addObject("projects_desc_users_table_order_column", projects_desc_users_table_order_column);
        mav.addObject("projects_desc_users_table_order_type", projects_desc_users_table_order_type);
        mav.addObject("projects_desc_users_table_search", projects_desc_users_table_search);
        mav.addObject("projects_desc_users_table_pagelen", projects_desc_users_table_pagelen);
        mav.addObject("projects_desc_users_table_page", projects_desc_users_table_page);

        mav.addObject("projects_desc_main_table_order_column", projects_desc_main_table_order_column);
        mav.addObject("projects_desc_main_table_order_type", projects_desc_main_table_order_type);
        mav.addObject("projects_desc_main_table_search", projects_desc_main_table_search);
        mav.addObject("projects_desc_main_table_pagelen", projects_desc_main_table_pagelen);
        mav.addObject("projects_desc_main_table_page", projects_desc_main_table_page);

        mav.addObject("projects_desc_tab", "tab-3");

        mav.setViewName("/projects_desc/users_detail");
        return mav;
    }

    @PostMapping("/users_model")
    public ModelAndView model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="parent_pr_id") Long parent_pr_id,
            @RequestParam(value="parent_pr_name") String parent_pr_name,

            @RequestParam(value="prrl_id", required = false) Long prrl_id,
            @RequestParam(value="pr_role_id", required = false, defaultValue = "0") Long pr_role_id,
            @RequestParam(value="employee_id", required = false) Boolean employee_id,
            @RequestParam(value="user_id", required = false, defaultValue = "0") Long user_id,
            @RequestParam(value="partner_id", required = false) Boolean partner_id,
            @RequestParam(value="cnt_id", required = false, defaultValue = "0") Long cnt_id,
            @RequestParam(value="cc_id", required = false, defaultValue = "0") Long cc_id,
            @RequestParam(value="prrl_description", required = false) String prrl_description,

            @RequestParam(value="projects_desc_users_table_order_column", required = false) Long order_column,
            @RequestParam(value="projects_desc_users_table_order_type", required = false) String order_type,
            @RequestParam(value="projects_desc_users_table_search", required = false) String table_search,
            @RequestParam(value="projects_desc_users_table_pagelen", required = false) Long pagelen,
            @RequestParam(value="projects_desc_users_table_page", required = false) Long page,

            @RequestParam(value="projects_desc_main_table_order_column") Long projects_desc_main_table_order_column,
            @RequestParam(value="projects_desc_main_table_order_type") String projects_desc_main_table_order_type,
            @RequestParam(value="projects_desc_main_table_search") String projects_desc_main_table_search,
            @RequestParam(value="projects_desc_main_table_pagelen") Long projects_desc_main_table_pagelen,
            @RequestParam(value="projects_desc_main_table_page") Long projects_desc_main_table_page
    ) {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_List> deatil_user_List;

        List<User_Role_List> user_Role_List;
        List<Contragent_List> contragent_List;
        List<ContragentContactList> contragentContactList;
        List<ProjectRolesList> projectRolesList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        Long employeeID;
        Long partnerID;

        if(employee_id == null){ employeeID = new Long("0");}
        else { employeeID = new Long("1");}

        if(partner_id == null){ partnerID = new Long("0");}
        else { partnerID = new Long("1");}

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddProjectUserQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_AddProjectUser")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, parent_pr_id)
                            .setParameter(4, pr_role_id)
                            .setParameter(5, employeeID)
                            .setParameter(6, user_id)
                            .setParameter(7, partnerID)
                            .setParameter(8, cnt_id)
                            .setParameter(9, cc_id)
                            .setParameter(10, prrl_description)
                            ;
                    AddProjectUserQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditProjectUserQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_EditProjectUser")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, parent_pr_id)
                            .setParameter(4, prrl_id)
                            .setParameter(5, pr_role_id)
                            .setParameter(6, employeeID)
                            .setParameter(7, user_id)
                            .setParameter(8, partnerID)
                            .setParameter(9, cnt_id)
                            .setParameter(10, cc_id)
                            .setParameter(11, prrl_description)
                            ;
                    EditProjectUserQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelProjectUserQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_DelProjectUser")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, prrl_id)
                            ;
                    DelProjectUserQuery.execute();
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

        mav.addObject("parent_pr_id", parent_pr_id);
        mav.addObject("parent_pr_name", parent_pr_name);

        if(mode == 0){
            mav.addObject("employee_id", employeeID);

            mav.addObject("user_id", user_id);
            deatil_user_List = user_ListRepository.queryUserAll();
            mav.addObject("users_list", deatil_user_List);

            mav.addObject("partner_id", partnerID);

            mav.addObject("cnt_id", cnt_id);
            contragent_List = contragent_ListRepository.queryCntByUserID(user_List.get(0).id);
            mav.addObject("cnt_list", contragent_List);

            mav.addObject("cc_id", cc_id);
            contragentContactList = contragentContactListRepository.queryCntContactNewByID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id, parent_pr_id);
            mav.addObject("cnt_list", contragent_List);

            mav.addObject("pr_role_id", pr_role_id);
            projectRolesList = (List<ProjectRolesList>) projectRolesListRepository.findAll();
            mav.addObject("pr_role_list", projectRolesList);
        }
        else{

        }

        mav.addObject("projects_desc_users_table_order_column", order_column);
        mav.addObject("projects_desc_users_table_order_type", order_type);
        mav.addObject("projects_desc_users_table_search", table_search);
        mav.addObject("projects_desc_users_table_pagelen", pagelen);
        mav.addObject("projects_desc_users_table_page", page);

        mav.addObject("projects_desc_main_table_order_column", projects_desc_main_table_order_column);
        mav.addObject("projects_desc_main_table_order_type", projects_desc_main_table_order_type);
        mav.addObject("projects_desc_main_table_search", projects_desc_main_table_search);
        mav.addObject("projects_desc_main_table_pagelen", projects_desc_main_table_pagelen);
        mav.addObject("projects_desc_main_table_page", projects_desc_main_table_page);

        mav.addObject("projects_desc_tab", "tab-3");

        mav.setViewName("/projects_desc/users_detail");
        return mav;
    }

    @GetMapping("/get_contacts")
    public JSONDatatable get_contacts(
            @RequestParam(value="cnt_id") Long cnt_id,
            @RequestParam(value="parent_pr_id") Long parent_pr_id,
            @RequestParam(value="prrl_id") Long prrl_id,
            @RequestParam(value="mode_id") Long mode_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        if(mode_id == 0) {
            result.setData(contragentContactListRepository.queryCntContactNewByID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id, parent_pr_id));
        }
        else{
            result.setData(contragentContactListRepository.queryCntContactByID(user_List.get(0).id, user_Role_List.get(0).role_id, cnt_id, parent_pr_id, prrl_id));
        }
        return result;
    }

    @PostMapping("/get_chat_table")
    public JSONDatatable get_chat_table(
            @RequestParam(value="parent_pr_id", required = false) Long parent_pr_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(parent_pr_id != null && parent_pr_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuProjectsChatListRepository.queryMenuProjectChatListByID(user_List.get(0).id, user_Role_List.get(0).role_id, parent_pr_id));
        }
        return result;
    }

    @RequestMapping("/chat_detail")
    public ModelAndView detailview(@RequestParam(value="mode") Long mode,
                                   @RequestParam(value="parent_pr_id") Long parent_pr_id,
                                   @RequestParam(value="pr_chat_id") Long pr_chat_id,
                                   
                                   @RequestParam(value="projects_desc_chat_table_order_column") Long projects_desc_chat_table_order_column,
                                   @RequestParam(value="projects_desc_chat_table_order_type") String projects_desc_chat_table_order_type,
                                   @RequestParam(value="projects_desc_chat_table_search") String projects_desc_chat_table_search,
                                   @RequestParam(value="projects_desc_chat_table_pagelen") Long projects_desc_chat_table_pagelen,
                                   @RequestParam(value="projects_desc_chat_table_page") Long projects_desc_chat_table_page,

                                   @RequestParam(value="projects_desc_main_table_order_column") Long projects_desc_main_table_order_column,
                                   @RequestParam(value="projects_desc_main_table_order_type") String projects_desc_main_table_order_type,
                                   @RequestParam(value="projects_desc_main_table_search") String projects_desc_main_table_search,
                                   @RequestParam(value="projects_desc_main_table_pagelen") Long projects_desc_main_table_pagelen,
                                   @RequestParam(value="projects_desc_main_table_page") Long projects_desc_main_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<User_List> users_List;
        List<ProjectsParentList> projectsParentList;
        List<DetailProjectsChatList> detailProjectsChatList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("parent_pr_id", parent_pr_id);
        projectsParentList = projectsParentListRepository.queryProjectPriorListAll(user_List.get(0).id, user_Role_List.get(0).role_id, new Long(0));
        mav.addObject("parent_pr_list", projectsParentList);

        if(mode == 0){
            mav.addObject("user_id",  user_List.get(0).id);
            users_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("users_list", users_List);
        }
        else{
            detailProjectsChatList = detailProjectsChatListRepository.queryDetailProjectChatListByID(user_List.get(0).id, user_Role_List.get(0).role_id, pr_chat_id);

            mav.addObject("pr_chat_id", detailProjectsChatList.get(0).pr_chat_id);

            mav.addObject("user_id",  detailProjectsChatList.get(0).user_id);
            users_List = user_ListRepository.queryUserByID(detailProjectsChatList.get(0).user_id);
            mav.addObject("users_list", users_List);

            mav.addObject("pr_chat_text", detailProjectsChatList.get(0).pr_chat_text);
        }

        mav.addObject("projects_desc_chat_table_order_column", projects_desc_chat_table_order_column);
        mav.addObject("projects_desc_chat_table_order_type", projects_desc_chat_table_order_type);
        mav.addObject("projects_desc_chat_table_search", projects_desc_chat_table_search);
        mav.addObject("projects_desc_chat_table_pagelen", projects_desc_chat_table_pagelen);
        mav.addObject("projects_desc_chat_table_page", projects_desc_chat_table_page);

        mav.addObject("projects_desc_main_table_order_column", projects_desc_main_table_order_column);
        mav.addObject("projects_desc_main_table_order_type", projects_desc_main_table_order_type);
        mav.addObject("projects_desc_main_table_search", projects_desc_main_table_search);
        mav.addObject("projects_desc_main_table_pagelen", projects_desc_main_table_pagelen);
        mav.addObject("projects_desc_main_table_page", projects_desc_main_table_page);

        mav.addObject("projects_desc_tab", "tab-4");

        mav.setViewName("/projects_desc/chat_detail");
        return mav;
    }

    @PostMapping("/chat_model")
    public ModelAndView chat_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(value="parent_pr_id") Long parent_pr_id,

            @RequestParam(value="pr_chat_id", required = false) Long pr_chat_id,
            @RequestParam(value="pr_chat_text", required = false) String pr_chat_text,

            @RequestParam(value="projects_desc_chat_table_order_column", required = false) Long projects_desc_chat_table_order_column,
            @RequestParam(value="projects_desc_chat_table_order_type", required = false) String projects_desc_chat_table_order_type,
            @RequestParam(value="projects_desc_chat_table_search", required = false) String projects_desc_chat_table_search,
            @RequestParam(value="projects_desc_chat_table_pagelen", required = false) Long projects_desc_chat_table_pagelen,
            @RequestParam(value="projects_desc_chat_table_page", required = false) Long projects_desc_chat_table_page,

            @RequestParam(value="projects_desc_main_table_order_column", required = false) Long projects_desc_main_table_order_column,
            @RequestParam(value="projects_desc_main_table_order_type", required = false) String projects_desc_main_table_order_type,
            @RequestParam(value="projects_desc_main_table_search", required = false) String projects_desc_main_table_search,
            @RequestParam(value="projects_desc_main_table_pagelen", required = false) Long projects_desc_main_table_pagelen,
            @RequestParam(value="projects_desc_main_table_page", required = false) Long projects_desc_main_table_page
    ) {
        ModelAndView mav = new ModelAndView();

        List<User_List> user_List;
        List<User_List> deatil_user_List;

        List<User_Role_List> user_Role_List;
        List<Contragent_List> contragent_List;
        List<ContragentContactList> contragentContactList;
        List<ProjectRolesList> projectRolesList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddProjectChatQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_AddProjectChat")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, parent_pr_id)
                            .setParameter(4, user_List.get(0).id)
                            .setParameter(5, pr_chat_text)
                            ;
                    AddProjectChatQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditProjectChatQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_EditProjectChat")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, pr_chat_id)
                            .setParameter(4, user_List.get(0).id)
                            .setParameter(5, pr_chat_text)
                            ;
                    EditProjectChatQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelProjectChatQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_DelProjectChat")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, pr_chat_id)
                            ;
                    DelProjectChatQuery.execute();
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

        mav.addObject("parent_pr_id", parent_pr_id);

        mav.addObject("projects_desc_chat_table_order_column", projects_desc_chat_table_order_column);
        mav.addObject("projects_desc_chat_table_order_type", projects_desc_chat_table_order_type);
        mav.addObject("projects_desc_chat_table_search", projects_desc_chat_table_search);
        mav.addObject("projects_desc_chat_table_pagelen", projects_desc_chat_table_pagelen);
        mav.addObject("projects_desc_chat_table_page", projects_desc_chat_table_page);

        mav.addObject("projects_desc_main_table_order_column", projects_desc_main_table_order_column);
        mav.addObject("projects_desc_main_table_order_type", projects_desc_main_table_order_type);
        mav.addObject("projects_desc_main_table_search", projects_desc_main_table_search);
        mav.addObject("projects_desc_main_table_pagelen", projects_desc_main_table_pagelen);
        mav.addObject("projects_desc_main_table_page", projects_desc_main_table_page);

        mav.addObject("projects_desc_tab", "tab-4");

        mav.setViewName("/projects_desc/chat_detail");
        return mav;
    }

    @PostMapping("/get_files_table")
    public JSONDatatable get_files_table(
            @RequestParam(value="parent_pr_id", required = false) Long parent_pr_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(parent_pr_id != null && parent_pr_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuProjectsFilesListRepository.queryDetailProjectFilesListByID(user_List.get(0).id, user_Role_List.get(0).role_id, parent_pr_id));
        }
        return result;
    }

    @RequestMapping("/files_detail")
    public ModelAndView files_detail(@RequestParam(value="mode") Long mode,
                                     @RequestParam(value="parent_pr_id") Long parent_pr_id,
                                     @RequestParam(value="pr_file_id") Long pr_file_id,

                                     @RequestParam(value="projects_desc_files_table_order_column", required = false) Long projects_desc_files_table_order_column,
                                     @RequestParam(value="projects_desc_files_table_order_type", required = false) String projects_desc_files_table_order_type,
                                     @RequestParam(value="projects_desc_files_table_search", required = false) String projects_desc_files_table_search,
                                     @RequestParam(value="projects_desc_files_table_pagelen", required = false) Long projects_desc_files_table_pagelen,
                                     @RequestParam(value="projects_desc_files_table_page", required = false) Long projects_desc_files_table_page,

                                     @RequestParam(value="projects_desc_main_table_order_column", required = false) Long projects_desc_main_table_order_column,
                                     @RequestParam(value="projects_desc_main_table_order_type", required = false) String projects_desc_main_table_order_type,
                                     @RequestParam(value="projects_desc_main_table_search", required = false) String projects_desc_main_table_search,
                                     @RequestParam(value="projects_desc_main_table_pagelen", required = false) Long projects_desc_main_table_pagelen,
                                     @RequestParam(value="projects_desc_main_table_page", required = false) Long projects_desc_main_table_page
    ) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<ProjectsParentList> projectsParentList;
        List<DetailProjectsFilesList> detailProjectsFilesList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("parent_pr_id", parent_pr_id);
        projectsParentList = projectsParentListRepository.queryProjectPriorListAll(user_List.get(0).id, user_Role_List.get(0).role_id, new Long(0));
        mav.addObject("parent_pr_list", projectsParentList);

        if(mode == 0){

        }
        else{
            detailProjectsFilesList = detailProjectsFilesListRepository.queryDetailProjectFilesListByID(user_List.get(0).id, user_Role_List.get(0).role_id, pr_file_id);
            mav.addObject("pr_file_id", detailProjectsFilesList.get(0).pr_file_id);

            mav.addObject("pr_file_name", detailProjectsFilesList.get(0).pr_file_name);
            mav.addObject("pr_file_date", detailProjectsFilesList.get(0).pr_file_date);
            mav.addObject("pr_file_name", detailProjectsFilesList.get(0).pr_file_name);
        }

        mav.addObject("projects_desc_files_table_order_column", projects_desc_files_table_order_column);
        mav.addObject("projects_desc_files_table_order_type", projects_desc_files_table_order_type);
        mav.addObject("projects_desc_files_table_search", projects_desc_files_table_search);
        mav.addObject("projects_desc_files_table_pagelen", projects_desc_files_table_pagelen);
        mav.addObject("projects_desc_files_table_page", projects_desc_files_table_page);

        mav.addObject("projects_desc_main_table_order_column", projects_desc_main_table_order_column);
        mav.addObject("projects_desc_main_table_order_type", projects_desc_main_table_order_type);
        mav.addObject("projects_desc_main_table_search", projects_desc_main_table_search);
        mav.addObject("projects_desc_main_table_pagelen", projects_desc_main_table_pagelen);
        mav.addObject("projects_desc_main_table_page", projects_desc_main_table_page);

        mav.addObject("projects_desc_tab", "tab-5");

        mav.setViewName("/projects_desc/files_detail");
        return mav;
    }

    @PostMapping("/files_model")
    public ModelAndView files_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(name = "parent_pr_id", required = false) Long parent_pr_id,
            @RequestParam(name = "pr_file_id", required = false) Long pr_file_id,
            @RequestParam("file") MultipartFile file,

            @RequestParam(value="projects_desc_files_table_order_column", required = false) Long projects_desc_files_table_order_column,
            @RequestParam(value="projects_desc_files_table_order_type", required = false) String projects_desc_files_table_order_type,
            @RequestParam(value="projects_desc_files_table_search", required = false) String projects_desc_files_table_search,
            @RequestParam(value="projects_desc_files_table_pagelen", required = false) Long projects_desc_files_table_pagelen,
            @RequestParam(value="projects_desc_files_table_page", required = false) Long projects_desc_files_table_page,

            @RequestParam(value="projects_desc_main_table_order_column", required = false) Long projects_desc_main_table_order_column,
            @RequestParam(value="projects_desc_main_table_order_type", required = false) String projects_desc_main_table_order_type,
            @RequestParam(value="projects_desc_main_table_search", required = false) String projects_desc_main_table_search,
            @RequestParam(value="projects_desc_main_table_pagelen", required = false) Long projects_desc_main_table_pagelen,
            @RequestParam(value="projects_desc_main_table_page", required = false) Long projects_desc_main_table_page
    ) {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        try {
            switch (mode.intValue()) {
                case 0:
                    ProjectFile projectFile = new ProjectFile();

                    projectFile.pr_id = parent_pr_id;
                    projectFile.pr_file_name = file.getOriginalFilename();
                    projectFile.pr_file_contenttype = file.getContentType();
                    projectFile.user_id = user_List.get(0).id;

                    try (InputStream inputStream = file.getInputStream()) {
                        byte[] obj_in = IOUtils.toByteArray(inputStream);
                        projectFile.pr_file_body = new javax.sql.rowset.serial.SerialBlob(obj_in);

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SerialException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    projectFile = projectFileRepository.save(projectFile);
                    break;
                case 2:
                    StoredProcedureQuery DelDocFileQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_DelProjectFile")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, pr_file_id);
                    DelDocFileQuery.execute();
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        mav.addObject("parent_pr_id", parent_pr_id);

        mav.addObject("projects_desc_files_table_order_column", projects_desc_files_table_order_column);
        mav.addObject("projects_desc_files_table_order_type", projects_desc_files_table_order_type);
        mav.addObject("projects_desc_files_table_search", projects_desc_files_table_search);
        mav.addObject("projects_desc_files_table_pagelen", projects_desc_files_table_pagelen);
        mav.addObject("projects_desc_files_table_page", projects_desc_files_table_page);

        mav.addObject("projects_desc_main_table_order_column", projects_desc_main_table_order_column);
        mav.addObject("projects_desc_main_table_order_type", projects_desc_main_table_order_type);
        mav.addObject("projects_desc_main_table_search", projects_desc_main_table_search);
        mav.addObject("projects_desc_main_table_pagelen", projects_desc_main_table_pagelen);
        mav.addObject("projects_desc_main_table_page", projects_desc_main_table_page);

        mav.addObject("projects_desc_tab", "tab-5");

        mav.setViewName("/projects_desc/files_detail");
        return mav;
    }

    @RequestMapping("/files_download")
    public ResponseEntity<byte[]> download(
            @RequestParam(name = "pr_file_id") Long pr_file_id
    ) throws SQLException {

        ProjectFile projectFile = projectFileRepository.queryByID(pr_file_id);

        int blobLength = (int) projectFile.pr_file_body.length();
        byte[] output = projectFile.pr_file_body.getBytes(1, blobLength);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("charset", "utf-8");
        responseHeaders.setContentType(MediaType.valueOf(projectFile.pr_file_contenttype));
        responseHeaders.setContentLength(output.length);
        try {
            responseHeaders.set("Content-disposition", "attachment; filename="+
                    MimeUtility.encodeWord(projectFile.pr_file_name, "utf-8", "Q")
            );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(output, responseHeaders, HttpStatus.OK);
    }

    @PostMapping("/get_payments_table")
    public JSONDatatable get_payments_table(
            @RequestParam(value="parent_pr_id", required = false) Long parent_pr_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(parent_pr_id != null && parent_pr_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuProjectsPaymentsListRepository.queryMenuProjectPaymentsListByID(user_List.get(0).id, user_Role_List.get(0).role_id, parent_pr_id));
        }
        return result;
    }

    @RequestMapping("/payments_detail")
    public ModelAndView payments_detail(@RequestParam(value="mode") Long mode,
                                     @RequestParam(value="parent_pr_id") Long parent_pr_id,
                                     @RequestParam(value="pr_pay_id") Long pr_pay_id,

                                     @RequestParam(value="projects_desc_payments_table_order_column", required = false) Long projects_desc_payments_table_order_column,
                                     @RequestParam(value="projects_desc_payments_table_order_type", required = false) String projects_desc_payments_table_order_type,
                                     @RequestParam(value="projects_desc_payments_table_search", required = false) String projects_desc_payments_table_search,
                                     @RequestParam(value="projects_desc_payments_table_pagelen", required = false) Long projects_desc_payments_table_pagelen,
                                     @RequestParam(value="projects_desc_payments_table_page", required = false) Long projects_desc_payments_table_page,

                                     @RequestParam(value="projects_desc_main_table_order_column", required = false) Long projects_desc_main_table_order_column,
                                     @RequestParam(value="projects_desc_main_table_order_type", required = false) String projects_desc_main_table_order_type,
                                     @RequestParam(value="projects_desc_main_table_search", required = false) String projects_desc_main_table_search,
                                     @RequestParam(value="projects_desc_main_table_pagelen", required = false) Long projects_desc_main_table_pagelen,
                                     @RequestParam(value="projects_desc_main_table_page", required = false) Long projects_desc_main_table_page
    ) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<User_List> users_List;
        List<ProjectsParentList> projectsParentList;
        List<ProjectPaymentTypeList> projectPaymentTypeList;
        List<Currency_Use_List> currency_List;
        List<DetailProjectsPaymentsList> detailProjectsPaymentsList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("parent_pr_id", parent_pr_id);
        projectsParentList = projectsParentListRepository.queryProjectPriorListAll(user_List.get(0).id, user_Role_List.get(0).role_id, new Long(0));
        mav.addObject("parent_pr_list", projectsParentList);

        if(mode == 0){
            mav.addObject("pr_pay_type_id", 1);
            projectPaymentTypeList = (List<ProjectPaymentTypeList>) projectPaymentListRepository.findAll();
            mav.addObject("pr_pay_type_list", projectPaymentTypeList);

            mav.addObject("user_id",  user_List.get(0).id);
            users_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("users_list", users_List);

            mav.addObject("currency_id", 980);
            currency_List = currency_Use_ListRepository.queryAllCurrencyListByID();
            mav.addObject("currency_list", currency_List);

            mav.addObject("pr_pay_sum", "0,00");
        }
        else{
            detailProjectsPaymentsList = detailProjectsPaymentsListRepository.queryDetailProjectPaymentsListByID(user_List.get(0).id, user_Role_List.get(0).role_id, pr_pay_id);

            mav.addObject("pr_pay_id", detailProjectsPaymentsList.get(0).pr_pay_id);
            mav.addObject("pr_pay_name", detailProjectsPaymentsList.get(0).pr_pay_name);

            mav.addObject("pr_pay_type_id", detailProjectsPaymentsList.get(0).pr_pay_type_id);
            projectPaymentTypeList = (List<ProjectPaymentTypeList>) projectPaymentListRepository.findAll();
            mav.addObject("pr_pay_type_list", projectPaymentTypeList);

            mav.addObject("user_id", user_List.get(0).id);
            users_List = user_ListRepository.queryUserByID(user_List.get(0).id);
            mav.addObject("users_list", users_List);

            mav.addObject("currency_id", detailProjectsPaymentsList.get(0).currency_id);
            currency_List = currency_Use_ListRepository.queryAllCurrencyListByID();
            mav.addObject("currency_list", currency_List);

            mav.addObject("pr_pay_sum", detailProjectsPaymentsList.get(0).pr_pay_sum);

            mav.addObject("pr_pay_plan_start_date", detailProjectsPaymentsList.get(0).pr_pay_plan_start_date);
            mav.addObject("pr_pay_plan_end_date", detailProjectsPaymentsList.get(0).pr_pay_plan_end_date);

            mav.addObject("pr_pay_day", detailProjectsPaymentsList.get(0).pr_pay_day);
        }

        mav.addObject("projects_desc_payments_table_order_column", projects_desc_payments_table_order_column);
        mav.addObject("projects_desc_payments_table_order_type", projects_desc_payments_table_order_type);
        mav.addObject("projects_desc_payments_table_search", projects_desc_payments_table_search);
        mav.addObject("projects_desc_payments_table_pagelen", projects_desc_payments_table_pagelen);
        mav.addObject("projects_desc_payments_table_page", projects_desc_payments_table_page);

        mav.addObject("projects_desc_main_table_order_column", projects_desc_main_table_order_column);
        mav.addObject("projects_desc_main_table_order_type", projects_desc_main_table_order_type);
        mav.addObject("projects_desc_main_table_search", projects_desc_main_table_search);
        mav.addObject("projects_desc_main_table_pagelen", projects_desc_main_table_pagelen);
        mav.addObject("projects_desc_main_table_page", projects_desc_main_table_page);

        mav.addObject("projects_desc_tab", "tab-6");

        mav.setViewName("/projects_desc/payments_detail");
        return mav;
    }

    @PostMapping("/payments_model")
    public ModelAndView payments_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(name = "parent_pr_id", required = false) Long parent_pr_id,
            @RequestParam(name = "pr_pay_id", required = false) Long pr_pay_id,
            @RequestParam(name = "pr_pay_type_id", required = false) Long pr_pay_type_id,
            @RequestParam(name = "pr_pay_name", required = false) String pr_pay_name,

            @RequestParam(name = "currency_id", required = false) Long currency_id,
            @RequestParam(name = "pr_pay_sum", required = false) String pr_pay_sum,
            @RequestParam(name = "user_id", required = false) Long user_id,
            @RequestParam(name = "pr_pay_plan_start_date", required = false) String pr_pay_plan_start_date,
            @RequestParam(name = "pr_pay_plan_end_date", required = false) String pr_pay_plan_end_date,
            @RequestParam(name = "pr_pay_day", required = false, defaultValue = "0") Long pr_pay_day,

            @RequestParam(value="projects_desc_payments_table_order_column", required = false) Long projects_desc_payments_table_order_column,
            @RequestParam(value="projects_desc_payments_table_order_type", required = false) String projects_desc_payments_table_order_type,
            @RequestParam(value="projects_desc_payments_table_search", required = false) String projects_desc_payments_table_search,
            @RequestParam(value="projects_desc_payments_table_pagelen", required = false) Long projects_desc_payments_table_pagelen,
            @RequestParam(value="projects_desc_payments_table_page", required = false) Long projects_desc_payments_table_page,

            @RequestParam(value="projects_desc_main_table_order_column", required = false) Long projects_desc_main_table_order_column,
            @RequestParam(value="projects_desc_main_table_order_type", required = false) String projects_desc_main_table_order_type,
            @RequestParam(value="projects_desc_main_table_search", required = false) String projects_desc_main_table_search,
            @RequestParam(value="projects_desc_main_table_pagelen", required = false) Long projects_desc_main_table_pagelen,
            @RequestParam(value="projects_desc_main_table_page", required = false) Long projects_desc_main_table_page
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");

        String FormatPlaneDate = "";
        String FormatEndDate = "";

        Date Date1 = null;
        Date Date2 = null;

        if (pr_pay_plan_end_date != "" && pr_pay_plan_end_date != null) {
            FormatEndDate = pr_pay_plan_start_date;
            Date1 = df.parse(FormatEndDate);
            FormatEndDate = newdf.format(Date1);
        }

        if (pr_pay_plan_start_date != "" && pr_pay_plan_start_date != null) {
            FormatPlaneDate = pr_pay_plan_start_date;
            Date2 = df.parse(FormatPlaneDate);
            FormatPlaneDate = newdf.format(Date2);
        }

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddPaymentQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_AddProjectPayment")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, parent_pr_id)
                            .setParameter(4, pr_pay_type_id)
                            .setParameter(5, pr_pay_name)
                            .setParameter(6, currency_id)
                            .setParameter(7, pr_pay_sum)
                            .setParameter(8, user_id)
                            .setParameter(9, FormatPlaneDate)
                            .setParameter(10, FormatEndDate)
                            .setParameter(11, pr_pay_day)
                            ;
                    AddPaymentQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditPaymentQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_EditProjectPayment")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(11, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(12, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, parent_pr_id)
                            .setParameter(4, pr_pay_id)
                            .setParameter(5, pr_pay_type_id)
                            .setParameter(6, pr_pay_name)
                            .setParameter(7, currency_id)
                            .setParameter(8, pr_pay_sum)
                            .setParameter(9, user_id)
                            .setParameter(10, FormatPlaneDate)
                            .setParameter(11, FormatEndDate)
                            .setParameter(12, pr_pay_day)
                            ;
                    EditPaymentQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelPaymentQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_DelProjectPayment")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, pr_pay_id);
                    DelPaymentQuery.execute();
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        mav.addObject("parent_pr_id", parent_pr_id);

        mav.addObject("projects_desc_payments_table_order_column", projects_desc_payments_table_order_column);
        mav.addObject("projects_desc_payments_table_order_type", projects_desc_payments_table_order_type);
        mav.addObject("projects_desc_payments_table_search", projects_desc_payments_table_search);
        mav.addObject("projects_desc_payments_table_pagelen", projects_desc_payments_table_pagelen);
        mav.addObject("projects_desc_payments_table_page", projects_desc_payments_table_page);

        mav.addObject("projects_desc_main_table_order_column", projects_desc_main_table_order_column);
        mav.addObject("projects_desc_main_table_order_type", projects_desc_main_table_order_type);
        mav.addObject("projects_desc_main_table_search", projects_desc_main_table_search);
        mav.addObject("projects_desc_main_table_pagelen", projects_desc_main_table_pagelen);
        mav.addObject("projects_desc_main_table_page", projects_desc_main_table_page);

        mav.addObject("projects_desc_tab", "tab-6");

        mav.setViewName("/projects_desc/payments_detail");
        return mav;
    }

    @PostMapping("/get_scheme_table")
    public JSONDatatable get_scheme_table(
            @RequestParam(value="parent_pr_id", required = false) Long parent_pr_id,
            @RequestParam(value="pr_pay_id", required = false) Long pr_pay_id
    ) {
        JSONDatatable result = new JSONDatatable();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        if(parent_pr_id != null && parent_pr_id > 0 && pr_pay_id != null && pr_pay_id > 0) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String authname = auth.getName();

            user_List = user_ListRepository.queryUserByName(authname);
            user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

            result.setData(menuProjectsSchemeListRepository.queryMenuProjectSchemeListByID(user_List.get(0).id, user_Role_List.get(0).role_id, parent_pr_id, pr_pay_id));
        }
        return result;
    }

    @RequestMapping("/scheme_detail")
    public ModelAndView scheme_detail(@RequestParam(value="mode") Long mode,
                                        @RequestParam(value="parent_pr_id") Long parent_pr_id,
                                        @RequestParam(value="pr_pay_id") Long pr_pay_id,
                                        @RequestParam(value="pr_pay_name") String pr_pay_name,
                                        @RequestParam(value="currency_id") Long currency_id,

                                        @RequestParam(value="pr_pay_scheme_id") Long pr_pay_scheme_id,

                                        @RequestParam(value="projects_desc_payments_table_order_column", required = false) Long projects_desc_payments_table_order_column,
                                        @RequestParam(value="projects_desc_payments_table_order_type", required = false) String projects_desc_payments_table_order_type,
                                        @RequestParam(value="projects_desc_payments_table_search", required = false) String projects_desc_payments_table_search,
                                        @RequestParam(value="projects_desc_payments_table_pagelen", required = false) Long projects_desc_payments_table_pagelen,
                                        @RequestParam(value="projects_desc_payments_table_page", required = false) Long projects_desc_payments_table_page,

                                        @RequestParam(value="projects_desc_main_table_order_column", required = false) Long projects_desc_main_table_order_column,
                                        @RequestParam(value="projects_desc_main_table_order_type", required = false) String projects_desc_main_table_order_type,
                                        @RequestParam(value="projects_desc_main_table_search", required = false) String projects_desc_main_table_search,
                                        @RequestParam(value="projects_desc_main_table_pagelen", required = false) Long projects_desc_main_table_pagelen,
                                        @RequestParam(value="projects_desc_main_table_page", required = false) Long projects_desc_main_table_page,

                                        @RequestParam(value="projects_desc_scheme_table_order_column", required = false) Long projects_desc_scheme_table_order_column,
                                        @RequestParam(value="projects_desc_scheme_table_order_type", required = false) String projects_desc_scheme_table_order_type,
                                        @RequestParam(value="projects_desc_scheme_table_search", required = false) String projects_desc_scheme_table_search,
                                        @RequestParam(value="projects_desc_scheme_table_pagelen", required = false) Long projects_desc_scheme_table_pagelen,
                                        @RequestParam(value="projects_desc_scheme_table_page", required = false) Long projects_desc_scheme_table_page
    ) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("mode", mode);
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;
        List<ProjectsParentList> projectsParentList;
        List<Currency_Use_List> currency_List;
        List<DetailProjectsSchemeList> detailProjectsSchemeList;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        mav.addObject("parent_pr_id", parent_pr_id);
        projectsParentList = projectsParentListRepository.queryProjectPriorListAll(user_List.get(0).id, user_Role_List.get(0).role_id, new Long(0));
        mav.addObject("parent_pr_list", projectsParentList);

        mav.addObject("pr_pay_id", pr_pay_id);
        mav.addObject("pr_pay_name", pr_pay_name);

        if(mode == 0){
            mav.addObject("currency_id", currency_id);
            currency_List = currency_Use_ListRepository.queryAllCurrencyListByID();
            mav.addObject("currency_list", currency_List);

            mav.addObject("pr_pay_scheme_plan_sum", "0,00");

            mav.addObject("pr_pay_scheme_fact_sum", "0,00");
        }
        else{
            detailProjectsSchemeList = detailProjectsSchemeListRepository.queryDetailProjectPaymentsListByID(user_List.get(0).id, user_Role_List.get(0).role_id,pr_pay_scheme_id);

            mav.addObject("currency_id", currency_id);
            currency_List = currency_Use_ListRepository.queryAllCurrencyListByID();
            mav.addObject("currency_list", currency_List);

            mav.addObject("pr_pay_scheme_id", detailProjectsSchemeList.get(0).pr_pay_scheme_id);

            mav.addObject("pr_pay_scheme_plan_sum", detailProjectsSchemeList.get(0).pr_pay_scheme_plan_sum);
            mav.addObject("pr_pay_scheme_plan_date", detailProjectsSchemeList.get(0).pr_pay_scheme_plan_date);

            mav.addObject("pr_pay_scheme_fact_sum", detailProjectsSchemeList.get(0).pr_pay_scheme_fact_sum);
            mav.addObject("pr_pay_scheme_fact_date", detailProjectsSchemeList.get(0).pr_pay_scheme_fact_date);
        }

        mav.addObject("projects_desc_payments_table_order_column", projects_desc_payments_table_order_column);
        mav.addObject("projects_desc_payments_table_order_type", projects_desc_payments_table_order_type);
        mav.addObject("projects_desc_payments_table_search", projects_desc_payments_table_search);
        mav.addObject("projects_desc_payments_table_pagelen", projects_desc_payments_table_pagelen);
        mav.addObject("projects_desc_payments_table_page", projects_desc_payments_table_page);

        mav.addObject("projects_desc_main_table_order_column", projects_desc_main_table_order_column);
        mav.addObject("projects_desc_main_table_order_type", projects_desc_main_table_order_type);
        mav.addObject("projects_desc_main_table_search", projects_desc_main_table_search);
        mav.addObject("projects_desc_main_table_pagelen", projects_desc_main_table_pagelen);
        mav.addObject("projects_desc_main_table_page", projects_desc_main_table_page);

        mav.addObject("projects_desc_scheme_table_order_column", projects_desc_scheme_table_order_column);
        mav.addObject("projects_desc_scheme_table_order_type", projects_desc_scheme_table_order_type);
        mav.addObject("projects_desc_scheme_table_search", projects_desc_scheme_table_search);
        mav.addObject("projects_desc_scheme_table_pagelen", projects_desc_scheme_table_pagelen);
        mav.addObject("projects_desc_scheme_table_page", projects_desc_scheme_table_page);

        mav.addObject("projects_desc_tab", "tab-6");

        mav.setViewName("/projects_desc/scheme_detail");
        return mav;
    }

    @PostMapping("/scheme_model")
    public ModelAndView scheme_model(
            @RequestParam(value="mode") Long mode,
            @RequestParam(name = "parent_pr_id", required = false) Long parent_pr_id,
            @RequestParam(name = "pr_pay_id", required = false) Long pr_pay_id,
            @RequestParam(name = "pr_pay_name", required = false) String pr_pay_name,
            @RequestParam(name = "currency_id", required = false) Long currency_id,

            @RequestParam(name = "pr_pay_scheme_id", required = false) Long pr_pay_scheme_id,
            @RequestParam(name = "pr_pay_scheme_plan_sum", required = false) String pr_pay_scheme_plan_sum,
            @RequestParam(name = "pr_pay_scheme_plan_date", required = false) String pr_pay_scheme_plan_date,
            @RequestParam(name = "pr_pay_scheme_fact_sum", required = false) String pr_pay_scheme_fact_sum,
            @RequestParam(name = "pr_pay_scheme_fact_date", required = false) String pr_pay_scheme_fact_date,

            @RequestParam(value="projects_desc_scheme_table_order_column", required = false) Long projects_desc_scheme_table_order_column,
            @RequestParam(value="projects_desc_scheme_table_order_type", required = false) String projects_desc_scheme_table_order_type,
            @RequestParam(value="projects_desc_scheme_table_search", required = false) String projects_desc_scheme_table_search,
            @RequestParam(value="projects_desc_scheme_table_pagelen", required = false) Long projects_desc_scheme_table_pagelen,
            @RequestParam(value="projects_desc_scheme_table_page", required = false) Long projects_desc_scheme_table_page,

            @RequestParam(value="projects_desc_payments_table_order_column", required = false) Long projects_desc_payments_table_order_column,
            @RequestParam(value="projects_desc_payments_table_order_type", required = false) String projects_desc_payments_table_order_type,
            @RequestParam(value="projects_desc_payments_table_search", required = false) String projects_desc_payments_table_search,
            @RequestParam(value="projects_desc_payments_table_pagelen", required = false) Long projects_desc_payments_table_pagelen,
            @RequestParam(value="projects_desc_payments_table_page", required = false) Long projects_desc_payments_table_page,

            @RequestParam(value="projects_desc_main_table_order_column", required = false) Long projects_desc_main_table_order_column,
            @RequestParam(value="projects_desc_main_table_order_type", required = false) String projects_desc_main_table_order_type,
            @RequestParam(value="projects_desc_main_table_search", required = false) String projects_desc_main_table_search,
            @RequestParam(value="projects_desc_main_table_pagelen", required = false) Long projects_desc_main_table_pagelen,
            @RequestParam(value="projects_desc_main_table_page", required = false) Long projects_desc_main_table_page
    ) throws ParseException {
        ModelAndView mav = new ModelAndView();
        List<User_List> user_List;
        List<User_Role_List> user_Role_List;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authname = auth.getName();
        user_List = user_ListRepository.queryUserByName(authname);
        user_Role_List = user_Role_ListRepository.queryGetUserRoleByID(user_List.get(0).id);

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat newdf = new SimpleDateFormat("dd.MM.yyyy");

        String FormatPlaneDate = "";
        String FormatEndDate = "";

        Date Date1 = null;
        Date Date2 = null;

        if (pr_pay_scheme_fact_date != "" && pr_pay_scheme_fact_date != null) {
            FormatEndDate = pr_pay_scheme_fact_date;
            Date1 = df.parse(FormatEndDate);
            FormatEndDate = newdf.format(Date1);
        }

        if (pr_pay_scheme_plan_date != "" && pr_pay_scheme_plan_date != null) {
            FormatPlaneDate = pr_pay_scheme_plan_date;
            Date2 = df.parse(FormatPlaneDate);
            FormatPlaneDate = newdf.format(Date2);
        }

        try {
            switch (mode.intValue()) {
                case 0:
                    StoredProcedureQuery AddPaymentQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_AddProjectPaymentScheme")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, parent_pr_id)
                            .setParameter(4, pr_pay_id)
                            .setParameter(5, currency_id)
                            .setParameter(6, pr_pay_scheme_plan_sum)
                            .setParameter(7, FormatPlaneDate)
                            .setParameter(8, pr_pay_scheme_fact_sum)
                            .setParameter(9, FormatEndDate)
                            ;
                    AddPaymentQuery.execute();
                    break;
                case 1:
                    StoredProcedureQuery EditPaymentQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_EditProjectPaymentScheme")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(4, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(5, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(6, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(7, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(8, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(9, String.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(10, String.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, parent_pr_id)
                            .setParameter(4, pr_pay_id)
                            .setParameter(5, pr_pay_scheme_id)
                            .setParameter(6, currency_id)
                            .setParameter(7, pr_pay_scheme_plan_sum)
                            .setParameter(8, FormatPlaneDate)
                            .setParameter(9, pr_pay_scheme_fact_sum)
                            .setParameter(10, FormatEndDate)
                            ;
                    EditPaymentQuery.execute();
                    break;
                case 2:
                    StoredProcedureQuery DelPaymentQuery = entityManager
                            .createStoredProcedureQuery("PKG_PROJECTS.pr_DelProjectPaymentScheme")
                            .registerStoredProcedureParameter(1, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(2, Long.class, ParameterMode.IN)
                            .registerStoredProcedureParameter(3, Long.class, ParameterMode.IN)
                            .setParameter(1, user_List.get(0).id)
                            .setParameter(2, user_Role_List.get(0).role_id)
                            .setParameter(3, pr_pay_scheme_id);
                    DelPaymentQuery.execute();
                    break;
                default:
                    throw new Exception("Mode:"+mode.toString()+" undefined");
            }
            mav.addObject("error_code",0);
        } catch (Exception e) {
            mav.addObject("error_code",500);
            mav.addObject("exception",e.getLocalizedMessage());
        }

        mav.addObject("parent_pr_id", parent_pr_id);
        mav.addObject("pr_pay_id", pr_pay_id);

        mav.addObject("projects_desc_scheme_table_order_column", projects_desc_scheme_table_order_column);
        mav.addObject("projects_desc_scheme_table_order_type", projects_desc_scheme_table_order_type);
        mav.addObject("projects_desc_scheme_table_search", projects_desc_scheme_table_search);
        mav.addObject("projects_desc_scheme_table_pagelen", projects_desc_scheme_table_pagelen);
        mav.addObject("projects_desc_scheme_table_page", projects_desc_scheme_table_page);

        mav.addObject("projects_desc_payments_table_order_column", projects_desc_payments_table_order_column);
        mav.addObject("projects_desc_payments_table_order_type", projects_desc_payments_table_order_type);
        mav.addObject("projects_desc_payments_table_search", projects_desc_payments_table_search);
        mav.addObject("projects_desc_payments_table_pagelen", projects_desc_payments_table_pagelen);
        mav.addObject("projects_desc_payments_table_page", projects_desc_payments_table_page);

        mav.addObject("projects_desc_main_table_order_column", projects_desc_main_table_order_column);
        mav.addObject("projects_desc_main_table_order_type", projects_desc_main_table_order_type);
        mav.addObject("projects_desc_main_table_search", projects_desc_main_table_search);
        mav.addObject("projects_desc_main_table_pagelen", projects_desc_main_table_pagelen);
        mav.addObject("projects_desc_main_table_page", projects_desc_main_table_page);

        mav.addObject("projects_desc_tab", "tab-6");

        mav.setViewName("/projects_desc/scheme_detail");
        return mav;
    }
}
